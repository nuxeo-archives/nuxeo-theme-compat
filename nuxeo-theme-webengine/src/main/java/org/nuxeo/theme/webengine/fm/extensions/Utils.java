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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.TemplateModel;

/**
 * @author <a href="mailto:jmo@chalmers.se">Jean-Marc Orliaguet</a>
 */
public class Utils {

    // Utility class.
    private Utils() {
    }

    public static Map<String, String> getTemplateDirectiveParameters(Map<String, TemplateModel> params) {
        Map<String, String> attributes = new HashMap<String, String>();
        for (Map.Entry<String, TemplateModel> entry : params.entrySet()) {
            TemplateModel v = entry.getValue();
            attributes.put(entry.getKey(), v.toString());
        }
        return attributes;
    }

    public static boolean isWebEngineVirtualHosting(final HttpServletRequest request) {
        return request.getHeader("nuxeo-webengine-base-path") != null;
    }

}
