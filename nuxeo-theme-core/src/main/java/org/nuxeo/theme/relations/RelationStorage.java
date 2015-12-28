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

package org.nuxeo.theme.relations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.nuxeo.theme.Registrable;

public final class RelationStorage implements Registrable {

    private static final Map<Long, Relation> relationsMap = new HashMap<Long, Relation>();

    private static final Map<List<String>, List<Long>> relatesMap = new HashMap<List<String>, List<Long>>();

    private static final String[][] WILDCARDS_MONADIC = { { "" } };

    private static final String[][] WILDCARDS_DYADIC = { { "", "" }, { "*", "" }, { "", "*" } };

    private static final String[][] WILDCARDS_TRIADIC = { { "", "", "" }, { "*", "", "" }, { "", "*", "" },
            { "", "", "*" }, { "*", "*", "" }, { "", "*", "*" }, { "*", "", "*" } };

    public synchronized void add(Relation relation) {
        Long key = findFreeKey();
        relationsMap.put(key, relation);
        index(key, relation);
    }

    public synchronized void remove(Relation relation) {
        List<String> indexString = computeIndexString(relation.getRelates());
        for (Long id : relatesMap.get(indexString)) {
            relationsMap.remove(id);
        }
        relatesMap.remove(indexString);
    }

    public Collection<Relation> search(Predicate predicate, List<Relate> relates) {
        List<Relation> relations = new ArrayList<Relation>();
        List<String> indexString = computeIndexString(relates);
        if (!relatesMap.containsKey(indexString)) {
            return relations;
        }
        for (Long id : relatesMap.get(indexString)) {
            Relation relation = relationsMap.get(id);
            if (relation == null) {
                continue;
            }
            if (!relation.hasPredicate(predicate)) {
                continue;
            }
            relations.add(relationsMap.get(id));
        }
        return relations;
    }

    public Collection<Relation> search(Predicate predicate, Relate first) {
        List<Relate> relates = new ArrayList<Relate>();
        relates.add(first);
        return search(predicate, relates);
    }

    public Collection<Relation> search(Predicate predicate, Relate first, Relate second) {
        List<Relate> relates = new ArrayList<Relate>();
        relates.add(first);
        relates.add(second);
        return search(predicate, relates);
    }

    public Collection<Relation> search(Predicate predicate, Relate first, Relate second, Relate third) {
        List<Relate> relates = new ArrayList<Relate>();
        relates.add(first);
        relates.add(second);
        relates.add(third);
        return search(predicate, relates);
    }

    public Collection<Relation> list() {
        return relationsMap.values();
    }

    public synchronized void clear() {
        relationsMap.clear();
        relatesMap.clear();
    }

    private synchronized Long findFreeKey() {
        Long key;
        Random generator = new Random();
        do {
            key = generator.nextLong();
        } while (relationsMap.containsKey(key));
        return key;
    }

    private synchronized void index(Long id, Relation relation) {
        List<Relate> relates = relation.getRelates();
        String[][] wildcards = null;

        Integer arity = relates.size();
        if (arity == 1) {
            wildcards = WILDCARDS_MONADIC;
        } else if (arity == 2) {
            wildcards = WILDCARDS_DYADIC;
        } else if (arity == 3) {
            wildcards = WILDCARDS_TRIADIC;
        }
        assert wildcards != null;

        for (String[] wildcard : wildcards) {
            List<String> indexString = new ArrayList<String>();
            for (Integer j = 0; j < arity; j++) {
                if (wildcard[j].equals("*")) {
                    indexString.add("*");
                } else {
                    indexString.add(relates.get(j).hash());
                }
            }
            List<Long> ids;
            if (relatesMap.containsKey(indexString)) {
                ids = relatesMap.get(indexString);
            } else {
                ids = new ArrayList<Long>();
            }
            ids.add(id);
            relatesMap.put(indexString, ids);
        }
    }

    private List<String> computeIndexString(List<Relate> relates) {
        List<String> indexString = new ArrayList<String>();
        for (Relate relate : relates) {
            if (relate == null || relate.hash() == null) {
                indexString.add("*");
            } else {
                indexString.add(relate.hash());
            }
        }
        return indexString;
    }

}
