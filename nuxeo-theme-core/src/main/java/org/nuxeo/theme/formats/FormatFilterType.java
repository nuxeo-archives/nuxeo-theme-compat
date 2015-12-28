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

package org.nuxeo.theme.formats;

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.rendering.Filter;
import org.nuxeo.theme.rendering.FilterType;
import org.nuxeo.theme.rendering.FilterTypeFamily;
import org.nuxeo.theme.types.TypeFamily;

@XObject("format-filter")
public final class FormatFilterType extends FilterType {

    @XNode("@name")
    public String name;

    @XNode("engine")
    public String engine = "*";

    @XNode("template-engine")
    public String templateEngine = "*";

    @XNode("mode")
    public String mode = "*";

    @XNode("format-type")
    public String formatName;

    private final Map<String, Filter> filters = new HashMap<String, Filter>();

    public FormatFilterType() {
    }

    public FormatFilterType(@SuppressWarnings("unused") final String name, final String formatName) {
        this.formatName = formatName;
    }

    @Override
    public Filter getFilter() {
        if (filters.containsKey(formatName)) {
            return filters.get(formatName);
        }
        FormatType formatType = (FormatType) Manager.getTypeRegistry().lookup(TypeFamily.FORMAT, formatName);
        FormatFilter filter = new FormatFilter();
        filter.setFormatType(formatType);
        filters.put(formatName, filter);
        return filter;
    }

    @Override
    public FilterTypeFamily getFilterTypeFamily() {
        return FilterTypeFamily.FORMAT;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(final String formatName) {
        this.formatName = formatName;
    }

    @Override
    public String getTypeName() {
        return String.format("%s/%s/%s/%s", engine, templateEngine, mode, name);
    }

    @Override
    public String getClassName() {
        return FormatFilter.class.getName();
    }

}
