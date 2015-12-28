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

package org.nuxeo.theme.html.filters.layout;

import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nuxeo.theme.formats.Format;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.views.AbstractView;

public class DefaultLayoutView extends AbstractView {

    static final Pattern firstTagPattern = Pattern.compile("<(.*?)>", Pattern.DOTALL);

    static final Pattern otherTagsPattern = Pattern.compile("<.*?>(.*)", Pattern.DOTALL);

    static final Pattern styleAttrPattern = Pattern.compile(" style=\"(.*?)\"", Pattern.DOTALL);

    @Override
    public String render(final RenderingInfo info) {
        final String markup = info.getMarkup();

        final Format format = info.getFormat();
        if (!format.hasProperties()) {
            return markup;
        }

        final Matcher firstMatcher = firstTagPattern.matcher(markup);
        final Matcher othersMatcher = otherTagsPattern.matcher(markup);

        if (!(firstMatcher.find() && othersMatcher.find())) {
            return markup;
        }

        // find a 'style="...."' match
        String inBrackets = firstMatcher.group(1);
        final Matcher styleAttrMatcher = styleAttrPattern.matcher(inBrackets);

        // build a new 'style="..."' string
        final StringBuilder styleAttributes = new StringBuilder();
        if (styleAttrMatcher.find()) {
            styleAttributes.append(styleAttrMatcher.group(1));
            if (!styleAttributes.toString().endsWith(";")) {
                styleAttributes.append(';');
            }
        }

        // add new attributes
        for (Enumeration<?> propertyNames = format.getPropertyNames(); propertyNames.hasMoreElements();) {
            String propertyName = (String) propertyNames.nextElement();
            styleAttributes.append(propertyName);
            styleAttributes.append(':');
            styleAttributes.append(format.getProperty(propertyName));
            styleAttributes.append(';');
        }

        if (styleAttributes.length() == 0) {
            return markup;
        }
        // remove the old 'style="..."' attributes, if there were some
        inBrackets = inBrackets.replaceAll(styleAttrPattern.toString(), "");

        // write the final markup
        if (inBrackets.endsWith("/")) {
            return String.format("<%s style=\"%s\" />%s", inBrackets.replaceAll("/$", "").trim(),
                    styleAttributes.toString(), othersMatcher.group(1));
        }
        return String.format("<%s style=\"%s\">%s", inBrackets, styleAttributes.toString(), othersMatcher.group(1));
    }

}
