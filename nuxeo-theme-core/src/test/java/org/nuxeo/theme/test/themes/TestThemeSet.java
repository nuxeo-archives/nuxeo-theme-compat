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

package org.nuxeo.theme.test.themes;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.themes.ThemeManager;
import org.nuxeo.theme.themes.ThemeSet;

public class TestThemeSet extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "themeset-config.xml");

    }

    @Test
    public void testGetThemeSets() {
        ThemeManager themeManager = Manager.getThemeManager();
        List<ThemeSet> themeSets = themeManager.getThemeSets();
        assertEquals(1, themeSets.size());
        assertEquals("galaxy", themeSets.get(0).getName());
    }

    @Test
    public void testGetThemeSetByName() {
        ThemeManager themeManager = Manager.getThemeManager();
        ThemeSet themeSet = themeManager.getThemeSetByName("galaxy");
        assertEquals("galaxy", themeSet.getName());
        assertEquals("galaxy-blogs", themeSet.getThemeForFeature("blogs"));
        assertEquals("galaxy-dm", themeSet.getThemeForFeature("dm"));
        assertEquals("galaxy-sites", themeSet.getThemeForFeature("sites"));
        assertEquals("galaxy-default", themeSet.getThemeForFeature("default"));
        assertEquals("galaxy-default", themeSet.getThemeForFeature("dashboard"));
        assertEquals("galaxy-search", themeSet.getThemeForFeature("search"));
        assertNull(themeSet.getThemeForFeature("unknown"));
    }
}
