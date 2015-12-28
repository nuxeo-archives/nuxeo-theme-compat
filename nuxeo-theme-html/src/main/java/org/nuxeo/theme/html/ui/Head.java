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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.ElementFormatter;
import org.nuxeo.theme.elements.ThemeElement;
import org.nuxeo.theme.formats.widgets.Widget;

public class Head {

    private static final Log log = LogFactory.getLog(Head.class);

    protected static Map<String, String> iconsMime = new HashMap<>();

    public static String render(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        String themeName = params.get("themeName");
        final ThemeElement theme = Manager.getThemeManager().getThemeByName(themeName);
        final Widget widget = (Widget) ElementFormatter.getFormatFor(theme, "widget");

        if (widget == null) {
            log.warn("Theme " + themeName + " has no widget.");
        } else {
            final Properties properties = widget.getProperties();

            // Charset
            final String charset = properties.getProperty("charset", "utf-8");
            sb.append(String.format("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=%s\"/>", charset));

            // Site icon
            String icon = properties.getProperty("icon", "/favicon.ico");
            String mimeType = getMimeType(icon);
            sb.append(String.format("<link rel=\"icon\" href=\"%s\" type=\"" + mimeType + "\"/>", icon));

            // If specified use a real .ico file for IE
            final String iconIco = properties.getProperty("iconIco", null);
            if (StringUtils.isNotEmpty(iconIco)) {
                icon = iconIco;
            }
            sb.append(String.format("<link rel=\"shortcut icon\" href=\"%s\" type=\"image/x-icon\"/>", icon));
        }

        // Base URL
        final String baseUrl = params.get("baseUrl");
        if (baseUrl != null) {
            sb.append(String.format("<base href=\"%s\" />", baseUrl));
        }

        return sb.toString();
    }

    protected static String getMimeType(String ico) {
        // Use a map to cache mimetype for ico
        if (!iconsMime.containsKey(ico)) {
            iconsMime.put(ico, resolveMimeType(ico));
        }
        return iconsMime.get(ico);
    }

    protected static String resolveMimeType(String ico) {
        int index = ico.lastIndexOf(".");
        if (index > 0) {
            // Handle only gif and png
            String ext = ico.substring(1 + index);
            switch (ext) {
            case "gif":
                return "image/gif";
            case "png":
                return "image/png";
            }
        }
        return "image/x-icon";
    }

}
