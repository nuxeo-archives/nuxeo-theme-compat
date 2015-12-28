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

package org.nuxeo.theme.test.rendering;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.elements.Element;
import org.nuxeo.theme.elements.ElementFactory;
import org.nuxeo.theme.models.InfoPool;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.test.DummyHtml;

public class TestRenderingInfo extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "engine-config.xml");
        deployContrib("org.nuxeo.theme.core.tests", "template-engine-config.xml");
    }

    @Test
    public void testInfoUid() {
        Element page = ElementFactory.create("page");
        RenderingInfo info = new RenderingInfo(page, null);
        assertNotNull(info.getUid());
    }

    @Test
    public void testInfoClone() throws MalformedURLException {
        Element page = ElementFactory.create("page");
        URL themeUrl = new URL("nxtheme://theme/test-engine/mode/theme/page/perspective/test-template-engine");
        RenderingInfo info = new RenderingInfo(page, themeUrl);

        assertEquals("test-engine", info.getEngine().getName());

        info.setModel(new DummyHtml("some data"));
        info.setMarkup("some markup");

        // clone the info structure
        RenderingInfo copy = info.createCopy();

        // make sure that a new object is created
        assertNotSame(copy, info);

        // make sure that the settings are preserved
        assertEquals(copy.getUid(), info.getUid());
        assertEquals(copy.getEngine().getName(), info.getEngine().getName());
        assertEquals(copy.getThemeUrl(), info.getThemeUrl());

        // the model and markup are not copied
        assertNull(copy.getModel());
        assertEquals("", copy.getMarkup());
    }

    @Test
    public void testInfoPool() {
        Element page = ElementFactory.create("page");
        RenderingInfo info = new RenderingInfo(page, null);

        String infoId = InfoPool.computeInfoId(info);
        InfoPool.register(info);
        assertSame(info, InfoPool.get(infoId));
        assertTrue(InfoPool.getInfoMap().containsValue(info));
    }

}
