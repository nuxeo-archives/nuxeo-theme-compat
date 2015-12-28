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

package org.nuxeo.theme.test.configuration;

import java.net.URL;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.theme.rendering.RendererType;

public class TestRendererConfiguration {

    @Test
    public void testRendererType() throws Exception {
        XMap xmap = new XMap();
        xmap.register(RendererType.class);

        URL url = Thread.currentThread().getContextClassLoader().getResource("renderer-xmap.xml");

        RendererType renderer = (RendererType) xmap.load(url);
        assertEquals("theme", renderer.getTypeName());
        assertEquals("[widget, style]", renderer.getFilters().toString());
    }

}
