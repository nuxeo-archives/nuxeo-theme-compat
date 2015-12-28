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

package org.nuxeo.theme.test.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.properties.OrderedProperties;

public class TestOrderedProperties {

    @Test
    public void testPut() {
        Properties properties = new OrderedProperties();
        properties.put("1", "1");
        properties.put("2", "2");
        properties.put("3", "3");
        properties.put("4", "4");

        Enumeration<?> keys = properties.propertyNames();
        assertEquals("1", keys.nextElement());
        assertEquals("2", keys.nextElement());
        assertEquals("3", keys.nextElement());
        assertEquals("4", keys.nextElement());
    }

    @Test
    public void testSetPropertyt() {
        Properties properties = new OrderedProperties();
        properties.setProperty("1", "1");
        properties.setProperty("2", "2");
        properties.setProperty("3", "3");
        properties.setProperty("4", "4");

        Enumeration<?> keys = properties.propertyNames();
        assertEquals("1", keys.nextElement());
        assertEquals("2", keys.nextElement());
        assertEquals("3", keys.nextElement());
        assertEquals("4", keys.nextElement());
    }

    @Test
    public void testLoad() throws IOException {
        Properties properties = new OrderedProperties();

        InputStream in = getClass().getClassLoader().getResourceAsStream("ordered.properties");
        properties.load(in);

        Enumeration<?> keys = properties.propertyNames();
        assertEquals("1", keys.nextElement());
        assertEquals("2", keys.nextElement());
        assertEquals("3", keys.nextElement());
        assertEquals("4", keys.nextElement());

        in.close();
    }

}
