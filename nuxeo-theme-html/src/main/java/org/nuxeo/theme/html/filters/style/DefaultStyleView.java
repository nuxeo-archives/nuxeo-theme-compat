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

package org.nuxeo.theme.html.filters.style;

import java.util.List;

import org.nuxeo.theme.elements.Element;
import org.nuxeo.theme.elements.ElementFormatter;
import org.nuxeo.theme.formats.Format;
import org.nuxeo.theme.formats.styles.Style;
import org.nuxeo.theme.formats.widgets.Widget;
import org.nuxeo.theme.html.CSSUtils;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.themes.ThemeManager;
import org.nuxeo.theme.views.AbstractView;

public class DefaultStyleView extends AbstractView {

    @Override
    public String render(final RenderingInfo info) {

        final Style style = (Style) info.getFormat();
        final StringBuilder sb = new StringBuilder();

        // add inherited styles first
        final List<Format> ancestors = ThemeManager.listAncestorFormatsOf(style);
        for (Format ancestor : ancestors) {
            sb.insert(0, String.format("%s ", CSSUtils.computeCssClassName((Style) ancestor)));
        }
        sb.append(CSSUtils.computeCssClassName(style));

        // get the widget view name
        final Element element = info.getElement();
        final Widget widget = (Widget) ElementFormatter.getFormatFor(element, "widget");
        if (widget != null) {
            final String className = CSSUtils.toUpperCamelCase(widget.getName());
            sb.append(className);
        }

        return CSSUtils.insertCssClass(info.getMarkup(), sb.toString());
    }
}
