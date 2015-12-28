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

package org.nuxeo.theme.rendering;

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("standalone-filter")
public final class StandaloneFilterType extends FilterType {

    @XNode("@name")
    public String name;

    @XNode("engine")
    public String engine = "*";

    @XNode("@template-engine")
    public String templateEngine = "*";

    @XNode("mode")
    public String mode = "*";

    @XNode("class")
    public String className;

    private final Map<String, Filter> filters = new HashMap<String, Filter>();

    @Override
    public Filter getFilter() {
        String typeName = getTypeName();
        if (filters.containsKey(typeName)) {
            return filters.get(typeName);
        }
        Filter filter = StandaloneFilterFactory.create(typeName);
        filters.put(typeName, filter);
        return filter;
    }

    @Override
    public FilterTypeFamily getFilterTypeFamily() {
        return FilterTypeFamily.STANDALONE;
    }

    @Override
    public String getTypeName() {
        return String.format("%s/%s/%s/%s", engine, templateEngine, mode, name);
    }

    @Override
    public String getClassName() {
        return className;
    }

}
