/*
 * (C) Copyright 2006-2007 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Jean-Marc Orliaguet, Chalmers
 *
 * $Id$
 */

package org.nuxeo.theme.html.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.resources.ResourceManager;

public class Resources {

    private static final Log log = LogFactory.getLog(Resources.class);

    public static String render(Map<String, String> params, boolean virtualHosting) {
        StringBuilder sb = new StringBuilder();

        String resourcePath = VirtualHostHelper.getContextPathProperty() + "/nxthemes-lib/";
        final String themeUrl = params.get("themeUrl");
        final String path = params.get("path");
        final String basepath = params.get("basepath");
        String nxthemeBasePath = basepath;

        final ResourceManager resourceManager = Manager.getResourceManager();

        final StringBuilder combinedStyles = new StringBuilder();
        final StringBuilder combinedScripts = new StringBuilder();
        combinedStyles.append(resourcePath);
        combinedScripts.append(resourcePath);

        boolean hasScripts = false;
        boolean hasStyles = false;

        boolean ignoreLocal = false;
        if (params.containsKey("ignoreLocal")) {
            ignoreLocal = Boolean.parseBoolean(params.get("ignoreLocal"));
        }

        List<String> resourceNames;
        if (ignoreLocal) {
            resourceNames = resourceManager.getGlobalResourcesFor(themeUrl);
        } else {
            resourceNames = resourceManager.getResourcesFor(themeUrl);
        }

        for (String resourceName : resourceNames) {
            if (resourceName.endsWith(".css")) {
                combinedStyles.append(resourceName).append(",");
                hasStyles = true;
            } else if (resourceName.endsWith(".js")) {
                combinedScripts.append(resourceName).append(",");
                hasScripts = true;
            }
        }

        combinedStyles.deleteCharAt(combinedStyles.length() - 1);
        combinedScripts.deleteCharAt(combinedScripts.length() - 1);

        long timestamp = getTimestamp(themeUrl);
        combinedStyles.append("?path=").append(path).append("&amp;basepath=").append(basepath).append("&amp;timestamp=").append(
                timestamp);
        combinedScripts.append("?path=").append(path).append("&amp;basepath=").append(basepath).append(
                "&amp;timestamp=").append(timestamp);

        // styles
        if (hasStyles) {
            sb.append(String.format("<link type=\"text/css\" rel=\"stylesheet\" media=\"all\" href=\"%s\"/>",
                    combinedStyles.toString()));
        }

        final String contextPath = params.get("contextPath");
        // scripts
        sb.append(String.format("<script type=\"text/javascript\"><!--\n" + "var nxthemesPath = \"%s\";\n"
                + "var nxthemesBasePath = \"%s\";\n" + "var nxContextPath = \"%s\";\n" + "//--></script>\n", path,
                nxthemeBasePath, contextPath));
        if (hasScripts) {
            sb.append(String.format("<script type=\"text/javascript\" src=\"%s\"></script>", combinedScripts.toString()));
        }

        // Flush local resources
        resourceManager.flush();
        return sb.toString();
    }

    /**
     * Return the timestamp to use for resources URL, useful to work-around caching when hot-reloading theme resources.
     *
     * @since 5.6
     * @param themeUrl
     */
    protected static long getTimestamp(String themeUrl) {
        long timestamp = 0;
        if (themeUrl != null) {
            try {
                Long val = Manager.getThemeManager().getLastModified(new URL(themeUrl));
                if (val != null) {
                    timestamp = val.longValue();
                }
            } catch (MalformedURLException e) {
                log.warn(String.format("Error while generating last modified" + " timestamp for theme url '%s'",
                        themeUrl), e);
            }
        }
        return timestamp;
    }
}
