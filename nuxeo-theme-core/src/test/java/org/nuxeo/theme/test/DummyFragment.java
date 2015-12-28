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

package org.nuxeo.theme.test;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.theme.fragments.AbstractFragment;
import org.nuxeo.theme.models.Model;
import org.nuxeo.theme.models.ModelException;
import org.nuxeo.theme.properties.FieldInfo;

public final class DummyFragment extends AbstractFragment {

    public List<String> getField3() {
        return field3;
    }

    public void setField3(List<String> field3) {
        this.field3 = field3;
    }

    @FieldInfo
    public String field1 = "";

    @FieldInfo
    public String field2 = "";

    @FieldInfo(type = "lines")
    public List<String> field3 = new ArrayList<String>();

    @Override
    public Model getModel() throws ModelException {
        DummyMenu menu = new DummyMenu("Menu", "A menu");
        menu.addItem(new DummyMenuItem("Menu item 1", "A menu item", "http://www.some.url.org", true, ""));
        menu.addItem(new DummyMenuItem("Menu item 2", "A menu item", "http://www.some.url.org", true, ""));
        return menu;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

}
