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

package org.nuxeo.theme.models;

public final class Region extends AbstractModel {

    public final String name;

    public final String defaultBody;

    public final String defaultSrc;

    public Region(String name, String defaultBody, String defaultSrc) {
        this.name = name;
        this.defaultBody = defaultBody;
        this.defaultSrc = defaultSrc;
    }

    public Region() {
        this(null, null, null);
    }

    public Region(String name, String defaultBody) {
        this(name, defaultBody, null);
    }

}
