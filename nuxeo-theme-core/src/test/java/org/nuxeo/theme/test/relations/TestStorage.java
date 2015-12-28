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

package org.nuxeo.theme.test.relations;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.relations.DefaultPredicate;
import org.nuxeo.theme.relations.DefaultRelate;
import org.nuxeo.theme.relations.DyadicRelation;
import org.nuxeo.theme.relations.MonadicRelation;
import org.nuxeo.theme.relations.Predicate;
import org.nuxeo.theme.relations.Relate;
import org.nuxeo.theme.relations.Relation;
import org.nuxeo.theme.relations.RelationStorage;
import org.nuxeo.theme.relations.TriadicRelation;

public class TestStorage {

    private RelationStorage storage;

    @Before
    public void setUp() {
        storage = new RelationStorage();
    }

    @Test
    public void testMonadicRelations() {
        Predicate predicate = new DefaultPredicate("_ is white");
        Relate r1 = new DefaultRelate("snow");
        Relation relation = new MonadicRelation(predicate, r1);

        assertFalse(storage.search(predicate, r1).contains(relation));
        assertFalse(storage.list().contains(relation));

        storage.add(relation);
        assertTrue(storage.list().contains(relation));
        assertTrue(storage.search(predicate, r1).contains(relation));

        storage.remove(relation);
        assertFalse(storage.search(predicate, r1).contains(relation));
        assertFalse(storage.list().contains(relation));
    }

    @Test
    public void testDyadicRelations() {
        Predicate predicate = new DefaultPredicate("_ loves _");
        Relate r1 = new DefaultRelate("Romeo");
        Relate r2 = new DefaultRelate("Juliet");
        Relation relation = new DyadicRelation(predicate, r1, r2);

        assertFalse(storage.search(predicate, r1, r2).contains(relation));
        assertFalse(storage.list().contains(relation));

        storage.add(relation);
        assertTrue(storage.list().contains(relation));
        assertTrue(storage.list().contains(relation));
        assertTrue(storage.search(predicate, r1, r2).contains(relation));
        assertTrue(storage.search(predicate, null, r2).contains(relation));
        assertTrue(storage.search(predicate, r1, null).contains(relation));

        storage.remove(relation);
        assertFalse(storage.search(predicate, r1, r2).contains(relation));
        assertFalse(storage.list().contains(relation));
    }

    @Test
    public void testTriadicRelations() {
        Predicate predicate = new DefaultPredicate("_ connects _ to _");
        Relate r1 = new DefaultRelate("A");
        Relate r2 = new DefaultRelate("B");
        Relate r3 = new DefaultRelate("C");
        Relation relation = new TriadicRelation(predicate, r1, r2, r3);

        assertFalse(storage.search(predicate, r1, r2, r3).contains(relation));
        assertFalse(storage.list().contains(relation));

        storage.add(relation);
        assertTrue(storage.search(predicate, r1, r2, r3).contains(relation));
        assertTrue(storage.search(predicate, null, r2, r3).contains(relation));
        assertTrue(storage.search(predicate, r1, null, r3).contains(relation));
        assertTrue(storage.search(predicate, r1, r2, null).contains(relation));
        assertTrue(storage.search(predicate, null, null, r3).contains(relation));
        assertTrue(storage.search(predicate, null, r2, null).contains(relation));
        assertTrue(storage.search(predicate, r1, null, null).contains(relation));
        assertTrue(storage.search(predicate, null, r2, null).contains(relation));

        storage.remove(relation);
        assertFalse(storage.search(predicate, r1, r2, r3).contains(relation));
        assertFalse(storage.list().contains(relation));
    }

}
