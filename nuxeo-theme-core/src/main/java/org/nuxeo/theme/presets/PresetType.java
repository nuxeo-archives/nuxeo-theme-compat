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

@XObject("preset")
public class PresetType implements Type {

    @XNode("@name")
    protected String name;

    @XNode("@value")
    protected String value;

    @XNode("@group")
    protected String group;

    @XNode("@category")
    protected String category = "";

    @XNode("@label")
    protected String label = "";

    @XNode("@description")
    protected String description = "";

    public PresetType() {
    }

    public PresetType(String name, String value, String group, String category, String label, String description) {
        this.name = name;
        this.value = value;
        this.group = group;
        this.category = category;
        this.label = label;
        this.description = description;
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.PRESET;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        if (group != null && !"".equals(group)) {
            return String.format("%s (%s)", name, group);
        }
        return name;
    }

    public String getEffectiveName() {
        return getTypeName();
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public String getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
