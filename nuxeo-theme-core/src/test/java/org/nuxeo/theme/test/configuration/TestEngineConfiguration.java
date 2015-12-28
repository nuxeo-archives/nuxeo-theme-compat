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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.engines.EngineType;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;

public class TestEngineConfiguration extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "engine-config.xml");
    }

    @Test
    public void testRegisterEngine1() {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        // engine 1
        EngineType engine1 = (EngineType) typeRegistry.lookup(TypeFamily.ENGINE, "test-engine");
        assertNotNull(engine1);
        assertEquals("test-engine", engine1.getTypeName());
        assertEquals("[widget, style]", engine1.getRenderers().get("theme").getFilters().toString());
    }

    @Test
    public void testRegisterEngine2() {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        // engine 2
        EngineType engine2 = (EngineType) typeRegistry.lookup(TypeFamily.ENGINE, "test-engine-2");
        assertNotNull(engine2);
        assertEquals("test-engine-2", engine2.getTypeName());
        assertEquals("[widget, style, page filter]", engine2.getRenderers().get("page").getFilters().toString());
    }

}
