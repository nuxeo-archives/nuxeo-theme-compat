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
import org.nuxeo.theme.negotiation.NegotiationType;
import org.nuxeo.theme.test.Scheme1;
import org.nuxeo.theme.test.Scheme2;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;

public class TestNegotiationConfiguration extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "negotiation-config.xml");
    }

    @Test
    public void testRegisterNegotiation() {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        // negotiation
        NegotiationType negotiation1 = (NegotiationType) typeRegistry.lookup(TypeFamily.NEGOTIATION,
                "strategy1/test negotiation");
        assertNotNull(negotiation1);
        assertEquals("strategy1", negotiation1.getStrategy());

        assertSame(Scheme1.class, negotiation1.getSchemes().get(0).getClass());
        assertSame(Scheme2.class, negotiation1.getSchemes().get(1).getClass());
    }

}
