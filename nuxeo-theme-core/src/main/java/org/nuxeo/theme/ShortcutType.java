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

package org.nuxeo.theme;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("shortcut")
public final class ShortcutType implements Type {

    @XNode("@key")
    private String key;

    private String target;

    public TypeFamily getTypeFamily() {
        return TypeFamily.SHORTCUT;
    }

    public String getTypeName() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public String getTarget() {
        return target;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    @XNode("@target")
    public void setTarget(final String target) {
        this.target = Framework.expandVars(target);
    }

}
