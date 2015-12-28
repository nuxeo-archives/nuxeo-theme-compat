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

package org.nuxeo.theme.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.ResourceResolver;
import org.nuxeo.theme.rendering.RenderingInfo;

public class TemplateView extends AbstractView {

    private static final Log log = LogFactory.getLog(TemplateView.class);

    @Override
    public String render(final RenderingInfo info) {
        final ViewType viewType = getViewType();
        final String template = viewType.getTemplate();
        return getTemplateContent(template);
    }

    public String getTemplateContent(final String template) {
        String result = "";
        InputStream is = null;
        try {
            // checks through FacesResourceResolver
            is = ResourceResolver.getInstance().getResourceAsStream(template);
            if (is == null) {
                log.warn("Template file not found: " + template);
            } else {
                Reader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(is));
                    StringBuilder rendered = new StringBuilder();
                    int ch;
                    while ((ch = in.read()) > -1) {
                        rendered.append((char) ch);
                    }
                    result = rendered.toString();
                } catch (IOException e) {
                    log.error(e, e);
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }
        } catch (IOException e) {
            log.error(e, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e, e);
                } finally {
                    is = null;
                }
            }
        }
        return result.trim();
    }
}
