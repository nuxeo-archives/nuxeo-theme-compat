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

package org.nuxeo.theme.fragments;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.models.ModelType;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("fragment")
public final class FragmentType implements Type {

    @XNode("@name")
    public String name;

    @XNode("class")
    public String className;

    @XNode("model-type")
    public String modelName;

    @XNode("dynamic")
    public boolean dynamic = true;

    public FragmentType() {
    }

    public FragmentType(String name, String className, String modelName, boolean dynamic) {
        this.name = name;
        this.className = className;
        this.modelName = modelName;
        this.dynamic = dynamic;
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.FRAGMENT;
    }

    public String getTypeName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getModelName() {
        return modelName;
    }

    public ModelType getModelType() {
        return (ModelType) Manager.getTypeRegistry().lookup(TypeFamily.MODEL, modelName);
    }

    public boolean isDynamic() {
        return dynamic;
    }

}
