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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.properties.FieldIO;
import org.nuxeo.theme.test.DummyObject;

public class TestFieldIO {

    @Test
    public void testUpdateStringFieldsFromProperties() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("width", "200px");
        properties.setProperty("height", "100px");

        DummyObject object = new DummyObject();
        FieldIO.updateFieldsFromProperties(object, properties);
        assertEquals("200px", object.width);
        assertEquals("100px", object.height);
    }

    @Test
    public void testUpdateBooleanFieldsFromProperties() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("selected", "true");

        DummyObject object = new DummyObject();
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.selected);

        properties.setProperty("selected", "false");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertFalse(object.selected);

        properties.setProperty("booleanClass", "true");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.booleanClass);

        properties.setProperty("booleanClass", "false");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertFalse(object.booleanClass);
    }

    @Test
    public void testUpdateIntegerFieldsFromProperties() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("maxItems", "2");

        DummyObject object = new DummyObject();
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.maxItems == 2);

        properties.setProperty("maxItems", "3");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.maxItems == 3);

        properties.setProperty("integerClass", "2");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.integerClass == 2);

        properties.setProperty("integerClass", "3");
        FieldIO.updateFieldsFromProperties(object, properties);
        assertTrue(object.integerClass == 3);
    }

    @Test
    public void testUpdateListFieldsFromProperties() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("stringSequence", "1,2,3");

        List<String> expected = new ArrayList<String>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        DummyObject object = new DummyObject();
        FieldIO.updateFieldsFromProperties(object, properties);
        assertEquals(expected, object.stringSequence);
    }

}
