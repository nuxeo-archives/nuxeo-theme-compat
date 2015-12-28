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

package org.nuxeo.theme.fragments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.ElementType;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;
import org.nuxeo.theme.uids.UidManager;

public final class FragmentFactory {

    private static final Log log = LogFactory.getLog(FragmentFactory.class);

    public static Fragment create(String typeName) {
        TypeRegistry typeRegistry = Manager.getTypeRegistry();
        ElementType elementType = (ElementType) typeRegistry.lookup(TypeFamily.ELEMENT, "fragment");
        FragmentType fragmentType = (FragmentType) typeRegistry.lookup(TypeFamily.FRAGMENT, typeName);

        if (fragmentType == null) {
            log.error("Fragment type not found: " + typeName);
            return null;
        }

        String className = fragmentType.getClassName();
        UidManager uidManager = Manager.getUidManager();

        Fragment fragment = null;
        try {
            fragment = (Fragment) Class.forName(className).newInstance();
            fragment.setElementType(elementType);
            fragment.setFragmentType(fragmentType);

            uidManager.register(fragment);
        } catch (ReflectiveOperationException e) {
            log.error(e, e);
        }
        return fragment;
    }

}
