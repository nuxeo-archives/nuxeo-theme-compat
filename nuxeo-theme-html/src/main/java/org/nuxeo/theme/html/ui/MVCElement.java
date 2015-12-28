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

package org.nuxeo.theme.html.ui;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.Utils;
import org.nuxeo.theme.resources.ResourceType;
import org.nuxeo.theme.types.TypeFamily;

public class MVCElement {

    private static final Log log = LogFactory.getLog(MVCElement.class);

    public static String render(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        String resource = params.get("resource");
        String url = params.get("url");
        String body = params.get("body");
        String className = params.get("className");

        sb.append(String.format("<ins class=\"%s\">", className));

        /* insert the content from a file source */
        if (null != resource) {
            ResourceType resourceType = (ResourceType) Manager.getTypeRegistry().lookup(TypeFamily.RESOURCE, resource);
            if (resourceType == null) {
                log.warn("Could not find resource: " + resource);
            } else {
                try {
                    sb.append(Utils.readResourceAsString(resourceType.getPath()));
                } catch (IOException e) {
                    log.warn("Could not find resource: " + resource);
                }
            }
        }

        /* get the content from a url */
        if (null != url) {
            sb.append(String.format(" cite=%s", url));
        }

        /* get the content from the body */
        if (null != body) {
            sb.append(body);
        }

        sb.append("</ins>");

        return sb.toString();
    }

}
