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

package org.nuxeo.theme.test.uids;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.test.IdentifiableObject;
import org.nuxeo.theme.uids.Identifiable;
import org.nuxeo.theme.uids.UidManager;

public class TestUidManager {

    private UidManager uidManager;

    @Before
    public void setUp() {
        uidManager = new UidManager();
    }

    @After
    public void tearDown() {
        uidManager.clear();
    }

    @Test
    public void testRegisterUnregister() {
        Identifiable ob1 = new IdentifiableObject();
        Integer id1 = uidManager.register(ob1);
        assertEquals(id1, ob1.getUid());
        assertEquals(ob1, uidManager.getObjectByUid(id1));
        uidManager.unregister(ob1);
        assertNull(ob1.getUid());
    }

    @Test
    public void testClear() {
        Identifiable ob1 = new IdentifiableObject();
        Integer id1 = uidManager.register(ob1);
        assertEquals(id1, ob1.getUid());
        assertEquals(ob1, uidManager.getObjectByUid(id1));
        uidManager.clear();
        assertNull(uidManager.getObjectByUid(id1));
    }

    @Test
    public void testSequence() {
        Integer[] uids1 = new Integer[10];
        Integer[] uids2 = new Integer[10];

        uidManager.clear();
        for (int i = 0; i < 10; i++) {
            Identifiable ob = new IdentifiableObject();
            uidManager.register(ob);
            uids1[i] = ob.getUid();
        }

        uidManager.clear();
        for (int i = 0; i < 10; i++) {
            Identifiable ob = new IdentifiableObject();
            uidManager.register(ob);
            uids2[i] = ob.getUid();
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(uids1[i], uids2[i]);
        }

    }

}
