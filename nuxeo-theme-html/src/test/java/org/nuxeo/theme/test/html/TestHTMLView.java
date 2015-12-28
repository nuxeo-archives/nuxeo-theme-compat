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

package org.nuxeo.theme.test.html;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.elements.PageElement;
import org.nuxeo.theme.html.HTMLView;
import org.nuxeo.theme.views.ViewType;

public class TestHTMLView extends NXRuntimeTestCase {
    DummyRenderingInfo info;

    PageElement page;

    HTMLView view;

    ViewType viewType;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");

        info = new DummyRenderingInfo(new PageElement(), null);

        view = new HTMLView();
        viewType = new ViewType();

        List<String> resources = new ArrayList<String>();
        viewType.setResources(resources);
        view.setViewType(viewType);
    }

    @Test
    public void test1() {
        info.setUid(1);
        info.setMarkup("<div>content</div>");
        viewType.setTemplate("test-template.xml");
        String result = view.render(info).replaceAll("\r?\n", "");
        assertEquals(
                "<div>#{nxthemesInfo.element.uid}#{nxthemesInfo.format.properties.charset}#{ (nxthemesInfo.test) ? \"on\" : \"off\"}<div>content</div></div>",
                result);
    }

    @After
    public void tearDown() throws Exception {
        view = null;
        info = null;
        page = null;
        super.tearDown();
    }

}
