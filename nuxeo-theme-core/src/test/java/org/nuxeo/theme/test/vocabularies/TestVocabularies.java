/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and others.
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

package org.nuxeo.theme.test.vocabularies;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.vocabularies.VocabularyItem;
import org.nuxeo.theme.vocabularies.VocabularyManager;

public class TestVocabularies extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "vocabulary-config.xml");
    }

    @Test
    public void testClassVocabulary() {
        VocabularyManager vocabularyManager = Manager.getVocabularyManager();
        List<VocabularyItem> items = vocabularyManager.getItems("test vocabulary as class");
        assertEquals(2, items.size());
        assertEquals("value1", items.get(0).getValue());
        assertEquals("label1", items.get(0).getLabel());
        assertEquals("value2", items.get(1).getValue());
        assertEquals("label2", items.get(1).getLabel());
    }

    @Test
    public void testResourceVocabulary() {
        VocabularyManager vocabularyManager = Manager.getVocabularyManager();
        List<VocabularyItem> items = vocabularyManager.getItems("test vocabulary as csv resource");
        assertEquals(3, items.size());
        assertEquals("value1", items.get(0).getValue());
        assertEquals("label1", items.get(0).getLabel());
        assertEquals("value2", items.get(1).getValue());
        assertEquals("label2", items.get(1).getLabel());
        assertEquals("value3", items.get(2).getValue());
        assertEquals("value3", items.get(2).getLabel());
    }

}
