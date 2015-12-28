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

package org.nuxeo.theme.test.fragments;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.fragments.Fragment;
import org.nuxeo.theme.fragments.FragmentFactory;
import org.nuxeo.theme.perspectives.PerspectiveType;

public class TestDummyFragment extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
        deployContrib("org.nuxeo.theme.core.tests", "fragment-config.xml");
    }

    @Test
    public void testVisibility() {
        Fragment fragment = FragmentFactory.create("dummy fragment");
        PerspectiveType perspective1 = new PerspectiveType("view_mode", "View mode");
        PerspectiveType perspective2 = new PerspectiveType("edit_mode", "Edit mode");

        // fragments are visible in all perspectives by default
        assertTrue(fragment.isVisibleInPerspective(perspective1));
        assertTrue(fragment.isVisibleInPerspective(perspective2));
        assertTrue(fragment.getVisibilityPerspectives().isEmpty());

        // make the fragment visible in perspective 1
        fragment.setVisibleInPerspective(perspective1);
        assertTrue(fragment.isVisibleInPerspective(perspective1));
        assertFalse(fragment.isVisibleInPerspective(perspective2));
        assertTrue(fragment.getVisibilityPerspectives().contains(perspective1));
        assertFalse(fragment.getVisibilityPerspectives().contains(perspective2));

        // make the fragment visible in perspective 2
        fragment.setVisibleInPerspective(perspective2);
        assertTrue(fragment.isVisibleInPerspective(perspective1));
        assertTrue(fragment.isVisibleInPerspective(perspective2));
        assertTrue(fragment.getVisibilityPerspectives().contains(perspective1));
        assertTrue(fragment.getVisibilityPerspectives().contains(perspective2));

        // make the fragment always visible
        fragment.setAlwaysVisible();
        assertTrue(fragment.isVisibleInPerspective(perspective1));
        assertTrue(fragment.isVisibleInPerspective(perspective2));
        assertTrue(fragment.getVisibilityPerspectives().isEmpty());
    }

}
