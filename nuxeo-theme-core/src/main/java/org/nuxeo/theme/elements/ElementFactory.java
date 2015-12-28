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

package org.nuxeo.theme.elements;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.uids.UidManager;

public final class ElementFactory {

    private static final Log log = LogFactory.getLog(ElementFactory.class);

    private ElementFactory() {
        // This class is not supposed to be instantiated.
    }

    public static Element create(final String typeName) {
        final ElementType elementType = (ElementType) Manager.getTypeRegistry().lookup(TypeFamily.ELEMENT, typeName);
        final String className = elementType.getClassName();
        final UidManager uidManager = Manager.getUidManager();

        if (className == null) {
            // throw an exception
            return null;
        }

        Element element = null;
        try {
            element = (Element) Class.forName(className).newInstance();
            element.setElementType(elementType);
            uidManager.register(element);
        } catch (ReflectiveOperationException e) {
            log.error("Could not create element", e);
        }
        return element;
    }

}
