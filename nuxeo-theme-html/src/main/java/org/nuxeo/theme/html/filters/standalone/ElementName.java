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

package org.nuxeo.theme.html.filters.standalone;

import org.nuxeo.theme.elements.Element;
import org.nuxeo.theme.html.CSSUtils;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.rendering.StandaloneFilter;

public class ElementName extends StandaloneFilter {

    @Override
    public RenderingInfo process(final RenderingInfo info, final boolean cache) {
        final Element element = info.getElement();
        String name = element.getName();
        String typeName = element.getElementType().getTypeName();
        if (name != null) {
            String markup = CSSUtils.insertCssClass(info.getMarkup(),
                    CSSUtils.toCamelCase(String.format("%s %s", name, typeName)));
            info.setMarkup(markup);
        }
        return info;
    }
}
