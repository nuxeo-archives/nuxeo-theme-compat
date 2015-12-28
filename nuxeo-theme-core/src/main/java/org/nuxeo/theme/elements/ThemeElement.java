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

package org.nuxeo.theme.elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.nodes.Node;

public class ThemeElement extends AbstractElement {

    private static final Log log = LogFactory.getLog(ThemeElement.class);

    @Override
    public List<Node> getChildrenInContext(URL themeURL) {
        List<Node> children = new ArrayList<Node>();
        Node page = Manager.getThemeManager().getThemePageByUrl(themeURL);
        if (page != null) {
            children.add(page);
        } else {
            log.error("No page found for URL " + themeURL);
        }
        return children;
    }

}
