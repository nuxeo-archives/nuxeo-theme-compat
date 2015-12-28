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

package org.nuxeo.theme.test;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.theme.vocabularies.Vocabulary;
import org.nuxeo.theme.vocabularies.VocabularyItem;

public final class DummyVocabulary implements Vocabulary {

    public List<VocabularyItem> getItems() {
        List<VocabularyItem> items = new ArrayList<VocabularyItem>();
        items.add(new VocabularyItem("value1", "label1"));
        items.add(new VocabularyItem("value2", "label2"));
        return items;
    }

}
