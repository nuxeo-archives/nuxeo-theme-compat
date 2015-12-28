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

package org.nuxeo.theme.jsf.taglib;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public class ButtonTag extends UIComponentELTag {
    private String identifier;

    private String controlledBy;

    private String switchTo;

    private String link;

    private String menu;

    private String classNames;

    private String icon;

    @Override
    public String getComponentType() {
        return "nxthemes.button";
    }

    @Override
    public String getRendererType() {
        return null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        // the panel's identifier
        component.getAttributes().put("identifier", identifier);

        // the perspective controller(s)
        if (controlledBy != null) {
            component.getAttributes().put("controlledBy", controlledBy);
        }

        // the perspective to switch to
        if (switchTo != null) {
            component.getAttributes().put("switchTo", switchTo);
        }

        // the link
        if (link != null) {
            component.getAttributes().put("link", link);
        }

        // the menu
        if (menu != null) {
            component.getAttributes().put("menu", menu);
        }

        // the icon
        if (icon != null) {
            component.getAttributes().put("icon", icon);
        }

        // CSS class names
        if (classNames != null) {
            component.getAttributes().put("classNames", classNames);
        }
    }

    @Override
    public void release() {
        super.release();
        identifier = null;
        controlledBy = null;
        switchTo = null;
        link = null;
        menu = null;
        icon = null;
        classNames = null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSwitchTo() {
        return switchTo;
    }

    public void setSwitchTo(String switchTo) {
        this.switchTo = switchTo;
    }

    public String getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(String controlledBy) {
        this.controlledBy = controlledBy;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(String classNames) {
        this.classNames = classNames;
    }

}
