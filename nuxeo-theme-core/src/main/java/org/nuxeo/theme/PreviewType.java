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

package org.nuxeo.theme;

import java.util.Arrays;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("preview")
public class PreviewType implements Type {

    @XNode("@category")
    protected String category;

    @XNode("class")
    protected String className;

    @XNode("properties")
    protected String properties = "";

    public TypeFamily getTypeFamily() {
        return TypeFamily.PREVIEW;
    }

    public String getTypeName() {
        return category;
    }

    public PreviewType() {
    }

    public PreviewType(String category, String className, String properties) {
        this.category = category;
        this.className = className;
        this.properties = properties;
    }

    public String getCategory() {
        return category;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getProperties() {
        return Arrays.asList(properties.split(","));
    }

}
