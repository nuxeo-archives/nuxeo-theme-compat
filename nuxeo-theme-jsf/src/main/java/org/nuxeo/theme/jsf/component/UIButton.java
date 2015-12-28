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

package org.nuxeo.theme.jsf.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.nuxeo.theme.html.ui.Button;

public class UIButton extends UIOutput {

    private String identifier;

    private String controlledBy;

    private String switchTo;

    private String link;

    private String menu;

    private String label;

    private String icon;

    private String classNames;

    @Override
    public void encodeAll(final FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        Map<String, String> params = new HashMap<String, String>();
        Map<String, Object> attributes = getAttributes();
        params.put("identifier", (String) attributes.get("identifier"));
        params.put("controlledBy", (String) attributes.get("controlledBy"));
        params.put("switchTo", (String) attributes.get("switchTo"));
        params.put("link", (String) attributes.get("link"));
        params.put("label", (String) attributes.get("label"));
        params.put("menu", (String) attributes.get("menu"));
        params.put("classNames", (String) attributes.get("classNames"));
        params.put("icon", (String) attributes.get("icon"));
        writer.write(Button.render(params));
    }

    public String getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(final String controlledBy) {
        this.controlledBy = controlledBy;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(final String menu) {
        this.menu = menu;
    }

    public String getSwitchTo() {
        return switchTo;
    }

    public void setSwitchTo(final String switchTo) {
        this.switchTo = switchTo;
    }

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(final String classNames) {
        this.classNames = classNames;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
