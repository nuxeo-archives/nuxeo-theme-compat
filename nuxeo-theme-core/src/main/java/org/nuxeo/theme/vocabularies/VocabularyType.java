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

package org.nuxeo.theme.vocabularies;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("vocabulary")
public final class VocabularyType implements Type {

    @XNode("@name")
    public String name;

    @XNode("class")
    public String className;

    @XNode("path")
    public String path;

    public VocabularyType() {
    }

    public VocabularyType(String name, String className, String path) {
        this.name = name;
        this.className = className;
        this.path = path;
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.VOCABULARY;
    }

    public String getTypeName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

}
