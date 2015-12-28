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
import org.nuxeo.theme.elements.ElementType;
import org.nuxeo.theme.nodes.NodeTypeFamily;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;

public class TestElementConfiguration extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "element-config.xml");
    }

    @Test
    public void testRegisterElement1() {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        // element 1
        ElementType element1 = (ElementType) typeRegistry.lookup(TypeFamily.ELEMENT, "test element 1");
        assertNotNull(element1);
        assertEquals("test element 1", element1.getTypeName());
        assertEquals(NodeTypeFamily.INNER, element1.getNodeTypeFamily());
        assertEquals("org.nuxeo.theme.test.DummyElement", element1.getClassName());
    }

    @Test
    public void testRegisterElement2() {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        // element 2
        ElementType element2 = (ElementType) typeRegistry.lookup(TypeFamily.ELEMENT, "test element 2");
        assertNotNull(element2);
        assertEquals("test element 2", element2.getTypeName());
        assertEquals(NodeTypeFamily.LEAF, element2.getNodeTypeFamily());
        assertEquals("org.nuxeo.theme.test.DummyElement", element2.getClassName());
    }

}
