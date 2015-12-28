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
 *     Nuxeo - initial API and implementation
 *
 * $Id: JOOoConvertPluginImpl.java 18651 2007-05-13 20:28:53Z sfermigier $
 */

package org.nuxeo.theme.jsf;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.ui.web.util.BaseURL;
import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;

public final class URLUtils {

    private static final Log log = LogFactory.getLog(URLUtils.class);

    private URLUtils() {
    }

    public static String getServerURL() {
        return getServerURL(null);
    }

    /**
     * @return Server URL as : protocol://serverName:port/
     */
    public static String getServerURL(ServletRequest request) {
        if (request == null) {
            final FacesContext facesContext = FacesContext.getCurrentInstance();
            request = (ServletRequest) facesContext.getExternalContext().getRequest();
        }
        return VirtualHostHelper.getServerURL(request);
    }

    /**
     * @return WebApp name : ie : nuxeo
     */
    public static String getWebAppName() {
        return BaseURL.getWebAppName();
    }

    /**
     * @return base URL as protocol://serverName:port/webappName/
     */
    public static String getBaseURL() {
        return getBaseURL(null);
    }

    public static String getBaseURL(ServletRequest request) {
        String baseURL = null;
        String serverUrl = getServerURL(request);
        if (serverUrl != null) {
            baseURL = serverUrl + getWebAppName() + '/';
        }
        if (baseURL == null) {
            log.error("Could not retrieve base url correctly");
        }
        return baseURL;
    }

}
