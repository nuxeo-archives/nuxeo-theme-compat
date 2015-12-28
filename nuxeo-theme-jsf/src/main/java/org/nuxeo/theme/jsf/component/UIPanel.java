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

import org.nuxeo.theme.html.ui.Panel;

public class UIPanel extends UIOutput {

    private String identifier;

    private String url;

    private String loading;

    private String stylesheet;

    private String javascript;

    private String subviews;

    private String visibleInPerspectives;

    private String controlledBy;

    private String filter;

    @Override
    public void encodeAll(final FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        Map<String, String> params = new HashMap<String, String>();
        String applicationPath = context.getExternalContext().getRequestParameterMap().get(
                "org.nuxeo.theme.application.path");
        if (applicationPath == null) {
            applicationPath = context.getExternalContext().getRequestContextPath();
        }
        params.put("org.nuxeo.theme.application.path", applicationPath);
        params.put("org.nuxeo.theme.application.name", "");
        Map<String, Object> attributes = getAttributes();
        params.put("identifier", (String) attributes.get("identifier"));
        params.put("url", (String) attributes.get("url"));
        params.put("loading", (String) attributes.get("loading"));
        params.put("stylesheet", (String) attributes.get("stylesheet"));
        params.put("javascript", (String) attributes.get("javascript"));
        params.put("subviews", (String) attributes.get("subviews"));
        params.put("visibleInPerspectives", (String) attributes.get("visibleInPerspectives"));
        params.put("controlledBy", (String) attributes.get("controlledBy"));
        params.put("filter", (String) attributes.get("filter"));
        writer.write(Panel.render(params));
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

    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(final String javascript) {
        this.javascript = javascript;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(final String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public String getSubviews() {
        return subviews;
    }

    public void setSubviews(final String subviews) {
        this.subviews = subviews;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getVisibleInPerspectives() {
        return visibleInPerspectives;
    }

    public void setVisibleInPerspectives(final String visibleInPerspectives) {
        this.visibleInPerspectives = visibleInPerspectives;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
