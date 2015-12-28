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

package org.nuxeo.theme.test.jsf.filters;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.PageElement;
import org.nuxeo.theme.engines.EngineType;
import org.nuxeo.theme.jsf.filters.standalone.FragmentTag;
import org.nuxeo.theme.rendering.Filter;
import org.nuxeo.theme.services.ThemeService;
import org.nuxeo.theme.types.TypeRegistry;

public class TestFragmentTagFilter extends NXRuntimeTestCase {

    private ThemeService themeService;

    private TypeRegistry typeRegistry;

    DummyRenderingInfo info;

    Filter filter;

    PageElement page;

    EngineType engine;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Manager.initializeProtocols();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");

        // create the elements to render
        page = new PageElement();
        page.setUid(1);

        // register test engine
        engine = new EngineType();
        engine.setName("engine");

        themeService = (ThemeService) Framework.getRuntime().getComponent(ThemeService.ID);
        typeRegistry = (TypeRegistry) themeService.getRegistry("types");

        typeRegistry.register(engine);
        info = new DummyRenderingInfo(page, new URL("nxtheme://element/engine/mode/1234"));
        filter = new FragmentTag();
    }

    @Test
    public void testFilter1() {
        info.setMarkup("<div>orginal markup</div>");
        info.setDirty(true);
        filter.process(info, true);
        assertEquals("<nxthemes:fragment xmlns:nxthemes=\"http://nuxeo.org/nxthemes\" "
                + "uid=\"1\" engine=\"engine\" mode=\"mode\" />", info.getMarkup());
    }

    @Test
    public void testFilter2() {
        info.setMarkup("<div>orginal markup</div>");
        info.setDirty(false);
        filter.process(info, true);
        assertEquals("<div>orginal markup</div>", info.getMarkup());
    }

}
