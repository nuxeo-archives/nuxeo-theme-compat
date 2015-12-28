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

package org.nuxeo.theme.test.models;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.theme.models.Html;
import org.nuxeo.theme.models.Menu;
import org.nuxeo.theme.models.MenuItem;
import org.nuxeo.theme.models.ModelException;
import org.nuxeo.theme.models.Text;

public class TestModels {

    @Test
    public void testText() {
        Text text = new Text("content here");
        assertEquals("content here", text.getBody());
    }

    @Test
    public void testHTML() {
        Html html = new Html("<p>content here</p>");
        assertEquals("<p>content here</p>", html.getBody());
    }

    public void Menu() throws ModelException {
        Menu menu = new Menu();
        MenuItem menuitem = new MenuItem("title sub-menu", "description sub-menu", "url sub-menu", false, "icon.png");
        menu.addItem(menuitem);
        assertSame(menuitem, menu.getItems().iterator().next());
    }

}
