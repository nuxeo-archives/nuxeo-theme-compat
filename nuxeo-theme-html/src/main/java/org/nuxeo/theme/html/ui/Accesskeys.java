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

package org.nuxeo.theme.html.ui;

import java.util.List;

import org.nuxeo.theme.Manager;
import org.nuxeo.theme.ShortcutType;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;

public class Accesskeys {

    public static String render() {
        StringBuilder sb = new StringBuilder();
        final TypeRegistry typeRegistry = Manager.getTypeRegistry();
        final List<Type> shortcuts = typeRegistry.getTypes(TypeFamily.SHORTCUT);
        if (shortcuts == null) {
            return "";
        }
        sb.append("<div>");
        for (Type type : shortcuts) {
            final ShortcutType shortcut = (ShortcutType) type;
            sb.append(String.format("<a href=\"%s\" accesskey=\"%s\"></a>", shortcut.getTarget(), shortcut.getKey()));
        }
        sb.append("</div>");
        return sb.toString();
    }

}
