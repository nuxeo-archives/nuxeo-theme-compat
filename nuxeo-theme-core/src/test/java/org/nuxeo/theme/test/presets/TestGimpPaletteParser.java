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

package org.nuxeo.theme.test.presets;

import java.net.URL;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.presets.PaletteParser;

public class TestGimpPaletteParser {

    @Test
    public void testParser() {
        URL url = getClass().getClassLoader().getResource("gimp-palette.gpl");
        Map<String, String> entries = PaletteParser.parse(url);
        Object[] keys = entries.keySet().toArray();
        assertEquals(4, keys.length);
        assertEquals("Plum", keys[0]);
        assertEquals("Chocolate", keys[1]);
        assertEquals("Color 3", keys[2]);
        assertEquals("Double", keys[3]);
        assertEquals("#ad7fa8", entries.get(keys[0]));
        assertEquals("#e9b96e", entries.get(keys[1]));
        assertEquals("#010203", entries.get(keys[2]));
        assertEquals("#010203", entries.get(keys[3]));
    }

}
