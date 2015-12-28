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

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.relations.DefaultPredicate;
import org.nuxeo.theme.relations.Predicate;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("format")
public final class FormatType implements Type {

    @XNode("@name")
    public String name;

    @XNode("class")
    public String className;

    @XNode("predicate")
    public String predicateName;

    private Predicate predicate;

    public FormatType() {
    }

    public FormatType(String name, String predicateName, String className) {
        this.name = name;
        this.predicateName = predicateName;
        this.className = className;
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.FORMAT;
    }

    public String getTypeName() {
        return name;
    }

    public String getFormatClass() {
        return className;
    }

    public Predicate getPredicate() {
        if (predicate == null) {
            predicate = new DefaultPredicate(predicateName);
        }
        return predicate;
    }

}
