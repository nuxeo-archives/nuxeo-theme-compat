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

package org.nuxeo.theme.test.resources;

import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.themes.ThemeManager;

public class TestResourceOrdering extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "resource-ordering.xml");
    }

    @Test
    public void testResourceOrdering() {
        ThemeManager themeManager = Manager.getThemeManager();
        List<String> ordering = themeManager.getResourceOrdering();
        assertTrue(ordering.indexOf("1") > ordering.indexOf("5"));
        assertTrue(ordering.indexOf("2") > ordering.indexOf("1"));
        assertTrue(ordering.indexOf("2") > ordering.indexOf("3"));
        assertTrue(ordering.indexOf("3") > ordering.indexOf("4"));
        assertTrue(ordering.indexOf("5") > ordering.indexOf("6"));
        assertTrue(ordering.indexOf("7") > ordering.indexOf("2"));
    }
}
