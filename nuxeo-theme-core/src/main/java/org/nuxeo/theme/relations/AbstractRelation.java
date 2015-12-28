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
import java.util.List;

public abstract class AbstractRelation implements Relation {

    public List<Relate> relates;

    private Predicate predicate;

    protected AbstractRelation(Predicate predicate) {
        this.predicate = predicate;
        relates = new ArrayList<Relate>();
    }

    public abstract RelationTypeFamily getRelationTypeFamily();

    public Relate getRelate(Integer position) {
        if (position > relates.size()) {
            // TODO throw exception;
        }
        return relates.get(position - 1);
    }

    public String hash() {
        List<String> r = new ArrayList<String>();
        for (Relate relate : relates) {
            r.add(relate.hash());
        }
        return String.format(predicate.hash().replace("_", "%s"), r.toArray());
    }

    @Override
    public String toString() {
        return "Relation {'" + hash() + "'}";
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public final void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public boolean hasPredicate(Predicate predicate) {
        return this.predicate.hash().equals(predicate.hash());
    }

    public List<Relate> getRelates() {
        return relates;
    }

    public void setRelates(List<Relate> relates) {
        this.relates = relates;
    }

}
