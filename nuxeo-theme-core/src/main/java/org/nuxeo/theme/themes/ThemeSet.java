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

package org.nuxeo.theme.themes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeMap;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("themeset")
public class ThemeSet implements Type {

    @XNode("@name")
    public String name;

    @XNodeMap(value = "theme", key = "@name", type = HashMap.class, componentType = ThemeSetEntry.class)
    public Map<String, ThemeSetEntry> themes;

    public ThemeSet() {
    }

    public TypeFamily getTypeFamily() {
        return TypeFamily.THEMESET;
    }

    public String getTypeName() {
        return name;
    }

    public String getName() {
        return name;
    }

    /*
     * Theme entries
     */
    public void setTheme(ThemeSetEntry theme) {
        themes.put(theme.getName(), theme);
    }

    public ThemeSetEntry getTheme(String themeName) {
        return themes.get(themeName);
    }

    public List<ThemeSetEntry> getThemes() {
        return new ArrayList<ThemeSetEntry>(themes.values());
    }

    /*
     * Features
     */
    public String getThemeForFeature(String feature) {
        for (ThemeSetEntry theme : getThemes()) {
            if (theme.getFeatures().contains(feature)) {
                return theme.getName();
            }
        }
        return null;
    }

    public void addFeatureToTheme(String themeName, String feature) {
        for (ThemeSetEntry theme : getThemes()) {
            if (theme.getName().equals(themeName)) {
                theme.addFeature(feature);
            } else {
                theme.removeFeature(feature);
            }
        }
    }

}
