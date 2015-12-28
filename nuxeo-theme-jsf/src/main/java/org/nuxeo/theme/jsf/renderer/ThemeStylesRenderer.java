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

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

import org.nuxeo.runtime.api.Framework;
import org.nuxeo.theme.html.Utils;
import org.nuxeo.theme.html.ui.ThemeStyles;
import org.nuxeo.theme.themes.ThemeManager;

import com.sun.faces.renderkit.html_basic.ScriptStyleBaseRenderer;

/**
 * @since 6.0
 */
public class ThemeStylesRenderer extends ScriptStyleBaseRenderer {

    @Override
    protected void startElement(ResponseWriter writer, UIComponent component) throws IOException {
    }

    @Override
    protected void endElement(ResponseWriter writer) throws IOException {
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

        Map<String, Object> attributes = component.getAttributes();
        String cache = (String) attributes.get("cache");
        String inline = (String) attributes.get("inline");
        String theme = (String) attributes.get("theme");

        final ResponseWriter writer = context.getResponseWriter();
        final ExternalContext externalContext = context.getExternalContext();

        Map<String, Object> requestMap = externalContext.getRequestMap();
        final URL themeUrl = (URL) requestMap.get("org.nuxeo.theme.url");
        if (theme == null) {
            theme = ThemeManager.getThemeNameByUrl(themeUrl);
        }

        Map<String, String> params = new HashMap<String, String>();

        params.put("themeName", theme);
        params.put("path", externalContext.getRequestContextPath());
        // FIXME: use configuration
        String basePath = Framework.getProperty("org.nuxeo.ecm.contextPath", "/nuxeo");
        params.put("basepath", basePath);
        String collectionName = ThemeManager.getCollectionNameByUrl(themeUrl);
        params.put("collection", collectionName);

        Boolean virtualHosting = Utils.isVirtualHosting((HttpServletRequest) externalContext.getRequest());
        writer.write(ThemeStyles.render(params, Boolean.parseBoolean(cache), Boolean.parseBoolean(inline),
                virtualHosting));
    }

}
