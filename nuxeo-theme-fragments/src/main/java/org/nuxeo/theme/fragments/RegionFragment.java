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

import org.nuxeo.theme.models.Model;
import org.nuxeo.theme.models.Region;
import org.nuxeo.theme.properties.FieldInfo;

public final class RegionFragment extends AbstractFragment {

    @FieldInfo(type = "string", label = "region name", description = "The name of the region from which content is inserted.")
    public String name = "";

    @FieldInfo(type = "text area", label = "default body", description = "The default HTML content to insert if the region is not filled.")
    public String defaultBody = "";

    @FieldInfo(type = "string", label = "default source", description = "The source of the HTML content to insert if the region is not filled.")
    public String defaultSrc = "";

    public RegionFragment() {
    }

    public RegionFragment(String name, String defaultBody, String defaultSrc) {
        this.name = name;
        this.defaultBody = defaultBody;
        this.defaultSrc = defaultSrc;
    }

    @Override
    public Model getModel() {
        return new Region(name, defaultBody, defaultSrc);
    }

}
