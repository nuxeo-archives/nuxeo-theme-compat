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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletRequest;

import org.nuxeo.theme.html.ui.Head;
import org.nuxeo.theme.jsf.URLUtils;
import org.nuxeo.theme.themes.ThemeManager;

public class UIHead extends UIOutput {

    @Override
    public void encodeAll(final FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final ExternalContext externalContext = context.getExternalContext();

        final URL themeUrl = (URL) externalContext.getRequestMap().get("org.nuxeo.theme.url");
        Map<String, String> params = new HashMap<String, String>();

        params.put("themeName", ThemeManager.getThemeNameByUrl(themeUrl));
        params.put("path", externalContext.getRequestContextPath());
        String baseUrl = URLUtils.getBaseURL((ServletRequest) externalContext.getRequest());
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        params.put("baseUrl", baseUrl);

        writer.write(Head.render(params));
    }
}
