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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.theme.models.InfoPool;
import org.nuxeo.theme.themes.ThemeException;
import org.nuxeo.theme.themes.ThemeManager;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author <a href="mailto:jmo@chalmers.se">Jean-Marc Orliaguet</a>
 */
public class NXThemesFragmentDirective implements TemplateDirectiveModel {

    private static final Log log = LogFactory.getLog(NXThemesFragmentDirective.class);

    final String templateEngine = "freemarker";

    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        if (loopVars.length != 0) {
            throw new TemplateModelException("This directive doesn't allow loop variables.");
        }
        if (body != null) {
            throw new TemplateModelException("Didn't expect a body");
        }

        WebContext ctx = WebEngine.getActiveContext();
        if (ctx == null) {
            throw new IllegalStateException("Not In a Web Context");
        }

        env.setVariable("nxthemesInfo", BeansWrapper.getDefaultInstance().wrap(InfoPool.getInfoMap()));

        Map<String, String> attributes = Utils.getTemplateDirectiveParameters(params);
        final URL elementUrl = new URL(String.format("nxtheme://element/%s/%s/%s/%s", attributes.get("engine"),
                attributes.get("mode"), templateEngine, attributes.get("uid")));

        String rendered = "";
        try {
            rendered = ThemeManager.renderElement(elementUrl);
        } catch (ThemeException e) {
            log.error("Element rendering failed: " + e.getMessage());
            return;
        }
        StringReader sr = new StringReader(rendered);
        BufferedReader reader = new BufferedReader(sr);
        Template tpl = new Template(elementUrl.toString(), reader, env.getConfiguration(),
                env.getTemplate().getEncoding());

        try {
            env.include(tpl);
        } catch (TemplateException | IOException e) {
            log.error("Rendering of Freemarker template failed: \n" + rendered, e);
        } finally {
            reader.close();
            sr.close();
        }

    }

}
