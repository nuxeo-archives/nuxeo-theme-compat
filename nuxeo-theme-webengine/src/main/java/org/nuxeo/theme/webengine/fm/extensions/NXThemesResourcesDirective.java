/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and others.
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

package org.nuxeo.theme.webengine.fm.extensions;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.theme.html.ui.Resources;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author <a href="mailto:jmo@chalmers.se">Jean-Marc Orliaguet</a>
 */
public class NXThemesResourcesDirective implements TemplateDirectiveModel {

    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        if (loopVars.length != 0) {
            throw new TemplateModelException("This directive doesn't allow loop variables.");
        }
        if (body != null) {
            throw new TemplateModelException("Didn't expect a body");
        }

        Writer writer = env.getOut();
        WebContext context = WebEngine.getActiveContext();
        HttpServletRequest request = context.getRequest();
        final URL themeUrl = (URL) request.getAttribute("org.nuxeo.theme.url");

        Map<String, String> attributes = Utils.getTemplateDirectiveParameters(params);
        attributes.put("themeUrl", themeUrl.toString());
        attributes.put("path", context.getModulePath());
        attributes.put("basepath", context.getBasePath());
        attributes.put("contextPath", VirtualHostHelper.getContextPath(request));

        // why not use Utils.isWebEngineVirtualHosting(request) here?
        Boolean virtualHosting = org.nuxeo.theme.html.Utils.isVirtualHosting(request);
        writer.write(Resources.render(attributes, virtualHosting));
    }
}
