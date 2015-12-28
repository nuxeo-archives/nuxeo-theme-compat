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

package org.nuxeo.theme.presets;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("palette")
public class PaletteType implements Type {

    @XNode("@name")
    private String name;

    @XNode("@src")
    private String src;

    @XNode("@category")
    private String category = "";

    public PaletteType() {
    }

    public PaletteType(String name, String src, String category) {
        super();
        this.name = name;
        this.src = src;
        this.category = category;
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.PALETTE;
    }

    public String getCategory() {
        return category;
    }

    public String getTypeName() {
        return name;
    }

    public String getSrc() {
        return src;
    }

    public String getName() {
        return name;
    }

}
