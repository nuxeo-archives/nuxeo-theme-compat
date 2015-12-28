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

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.relations.DefaultPredicate;
import org.nuxeo.theme.relations.DefaultRelate;
import org.nuxeo.theme.relations.DyadicRelation;
import org.nuxeo.theme.relations.MonadicRelation;
import org.nuxeo.theme.relations.Predicate;
import org.nuxeo.theme.relations.Relate;
import org.nuxeo.theme.relations.Relation;
import org.nuxeo.theme.relations.RelationTypeFamily;
import org.nuxeo.theme.relations.TriadicRelation;

public class TestRelation {

    @Test
    public void testMonadicRelation() {
        Predicate predicate = new DefaultPredicate("_ is white");
        Relate r1 = new DefaultRelate("snow");
        Relation relation = new MonadicRelation(predicate, r1);
        assertEquals(RelationTypeFamily.MONADIC, relation.getRelationTypeFamily());

        assertTrue(relation.hasPredicate(predicate));
        assertEquals("snow is white", relation.hash());
        assertTrue(relation.getRelates().contains(r1));
        assertEquals(r1, relation.getRelate(1));
    }

    @Test
    public void testDyadicRelation() {
        Predicate predicate = new DefaultPredicate("_ loves _");
        Relate r1 = new DefaultRelate("Romeo");
        Relate r2 = new DefaultRelate("Juliet");
        Relation relation = new DyadicRelation(predicate, r1, r2);

        assertEquals(RelationTypeFamily.DYADIC, relation.getRelationTypeFamily());
        assertTrue(relation.hasPredicate(predicate));
        assertEquals("Romeo loves Juliet", relation.hash());
        assertTrue(relation.getRelates().contains(r1));
        assertTrue(relation.getRelates().contains(r2));
        assertEquals(r1, relation.getRelate(1));
        assertEquals(r2, relation.getRelate(2));
    }

    @Test
    public void testTriadicRelation() {
        Predicate predicate = new DefaultPredicate("_ connects _ to _");
        Relate r1 = new DefaultRelate("A");
        Relate r2 = new DefaultRelate("B");
        Relate r3 = new DefaultRelate("C");
        Relation relation = new TriadicRelation(predicate, r1, r2, r3);

        assertEquals(RelationTypeFamily.TRIADIC, relation.getRelationTypeFamily());
        assertTrue(relation.hasPredicate(predicate));
        assertEquals("A connects B to C", relation.hash());
        assertTrue(relation.getRelates().contains(r1));
        assertTrue(relation.getRelates().contains(r2));
        assertTrue(relation.getRelates().contains(r3));
        assertEquals(r1, relation.getRelate(1));
        assertEquals(r2, relation.getRelate(2));
        assertEquals(r3, relation.getRelate(3));
    }

}
