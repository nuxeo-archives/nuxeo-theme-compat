/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Anahide Tchertchian
 */
package org.nuxeo.theme.jsf.renderer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

import org.nuxeo.ecm.platform.ui.web.util.BaseURL;
import org.nuxeo.theme.html.Utils;
import org.nuxeo.theme.html.ui.Resources;
import org.nuxeo.theme.jsf.component.UIResources;

import com.sun.faces.renderkit.html_basic.ScriptStyleBaseRenderer;

/**
 * Renderer for {@link UIResources} component.
 *
 * @since 6.0
 */
@ResourceDependencies({ @ResourceDependency(library = "javax.faces", name = "jsf.js"),
        @ResourceDependency(library = "org.richfaces", name = "jquery.js"),
        @ResourceDependency(library = "org.richfaces", name = "richfaces.js"),
        @ResourceDependency(library = "org.richfaces", name = "richfaces-queue.js"),
        @ResourceDependency(library = "org.nuxeo", name = "widget-utils.js"),
        @ResourceDependency(library = "org.nuxeo.select2", name = "select2.js") })
public class ResourcesRenderer extends ScriptStyleBaseRenderer {

    @Override
    protected void startElement(ResponseWriter writer, UIComponent component) throws IOException {
    }

    @Override
    protected void endElement(ResponseWriter writer) throws IOException {
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final ExternalContext externalContext = context.getExternalContext();

        Map<String, String> params = new HashMap<String, String>();

        Map<String, Object> requestMap = externalContext.getRequestMap();
        URL themeUrl = (URL) requestMap.get("org.nuxeo.theme.url");
        final Map<String, Object> attributes = component.getAttributes();

        String contextPath = BaseURL.getContextPath();
        params.put("contextPath", contextPath);
        params.put("themeUrl", themeUrl.toString());
        params.put("path", contextPath);
        params.put("ignoreLocal", (String) attributes.get("ignoreLocal"));

        String basePath = contextPath + "/site";
        params.put("basepath", basePath);

        Boolean virtualHosting = Utils.isVirtualHosting((HttpServletRequest) externalContext.getRequest());
        writer.write(Resources.render(params, virtualHosting));
    }

}
