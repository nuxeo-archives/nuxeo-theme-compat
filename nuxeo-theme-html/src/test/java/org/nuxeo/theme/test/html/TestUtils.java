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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.html.Utils;

public class TestUtils extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
    }

    @Test
    public void testAddWebLengths() {
        assertEquals("30px", Utils.addWebLengths("10px", "20px"));
        assertEquals("3em", Utils.addWebLengths("1em", "2em"));
        assertNull(Utils.addWebLengths("25%", "2em"));
    }

    @Test
    public void testSubstractWebLengths() {
        assertEquals("30px", Utils.substractWebLengths("40px", "10px"));
        assertEquals("3em", Utils.substractWebLengths("5em", "2em"));
        assertNull(Utils.substractWebLengths("25%", "2em"));
    }

    @Test
    public void testDivideWebLengths() {
        assertEquals("20px", Utils.divideWebLength("40px", 2));
        assertEquals("1em", Utils.divideWebLength("5em", 3));
        assertNull(Utils.divideWebLength("25%", 0));
    }

    @Test
    public void testGetMimeType() {
        assertEquals("image/png", Utils.getImageMimeType("png"));
        assertEquals("image/gif", Utils.getImageMimeType("gif"));
        assertEquals("image/jpeg", Utils.getImageMimeType("jpeg"));
        assertEquals("image/jpeg", Utils.getImageMimeType("jpg"));

        assertEquals("image/png", Utils.getImageMimeType("PNG"));
        assertEquals("image/gif", Utils.getImageMimeType("GIF"));
        assertEquals("image/jpeg", Utils.getImageMimeType("JPEG"));
        assertEquals("image/jpeg", Utils.getImageMimeType("JPG"));

        assertEquals("application/octet-stream", Utils.getImageMimeType("unknown"));
    }
}
