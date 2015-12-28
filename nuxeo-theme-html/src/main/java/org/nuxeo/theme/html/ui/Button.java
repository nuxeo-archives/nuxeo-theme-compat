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

import org.nuxeo.theme.html.Utils;

public class Button {

    public static String render(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        String identifier = params.get("identifier");
        String controlledBy = params.get("controlledBy");
        String switchTo = params.get("switchTo");
        String link = params.get("link");
        String menu = params.get("menu");
        String label = params.get("label");
        String classNames = params.get("classNames");
        String icon = params.get("icon");

        // view
        Map<String, Object> view = new HashMap<String, Object>();
        view.put("id", identifier);
        Map<String, Object> widget = new HashMap<String, Object>();
        widget.put("type", "button");
        view.put("widget", widget);
        if (null != switchTo) {
            String[] p = switchTo.split("/");
            if (p.length > 1) {
                view.put("perspectiveController", p[0]);
                view.put("toPerspective", p[1]);
            }
        }
        if (null != controlledBy) {
            view.put("controllers", controlledBy.split(","));
        }
        if (null != link) {
            view.put("link", link);
        }
        if (null != menu) {
            view.put("menu", menu);
        }
        if (null != classNames) {
            view.put("classNames", classNames);
        }
        if (null != icon) {
            view.put("icon", icon);
        }
        view.put("label", label);

        sb.append(String.format("<ins class=\"view\">%s</ins>", Utils.toJson(view)));
        return sb.toString();
    }

}
