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

package org.nuxeo.theme.test.formats;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.runtime.test.NXRuntimeTestCase;
import org.nuxeo.theme.elements.ElementFormatter;
import org.nuxeo.theme.formats.Format;
import org.nuxeo.theme.formats.FormatFactory;
import org.nuxeo.theme.test.DummyFragment;
import org.nuxeo.theme.themes.ThemeException;

public class TestFormatter extends NXRuntimeTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-service.xml");
        deployContrib("org.nuxeo.theme.core", "OSGI-INF/nxthemes-core-contrib.xml");
    }

    @Test
    public void testFormatter() throws ThemeException {
        DummyFragment fragment = new DummyFragment();
        Format widget = FormatFactory.create("widget");
        widget.setName("vertical menu");

        // Make the objects identifiable
        fragment.setUid(1);
        widget.setUid(2);

        assertTrue(ElementFormatter.getFormatsFor(fragment).isEmpty());
        assertNull(ElementFormatter.getFormatFor(fragment, "widget"));
        assertNull(ElementFormatter.getFormatByType(fragment, widget.getFormatType()));
        assertFalse(ElementFormatter.getElementsFor(widget).contains(fragment));

        // Add format
        ElementFormatter.setFormat(fragment, widget);

        assertTrue(ElementFormatter.getFormatsFor(fragment).contains(widget));
        assertSame(widget, ElementFormatter.getFormatFor(fragment, "widget"));
        assertEquals(ElementFormatter.getFormatByType(fragment, widget.getFormatType()), widget);
        assertTrue(ElementFormatter.getElementsFor(widget).contains(fragment));

        // Replace format
        Format widget2 = FormatFactory.create("widget");
        widget2.setName("horizontal menu");
        widget2.setUid(3);
        assertSame(widget, ElementFormatter.getFormatFor(fragment, "widget"));
        ElementFormatter.setFormat(fragment, widget2);
        assertTrue(ElementFormatter.getElementsFor(widget2).contains(fragment));
        assertTrue(ElementFormatter.getElementsFor(widget).isEmpty());
        assertSame(widget2, ElementFormatter.getFormatFor(fragment, "widget"));

        // Remove format
        ElementFormatter.removeFormat(fragment, widget2);

        assertTrue(ElementFormatter.getFormatsFor(fragment).isEmpty());
        assertNull(ElementFormatter.getFormatFor(fragment, "widget"));
        assertNull(ElementFormatter.getFormatByType(fragment, widget2.getFormatType()));
        assertFalse(ElementFormatter.getElementsFor(widget2).contains(fragment));
    }

}
