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

package org.nuxeo.theme.test.jsf.filters;

import java.net.URL;

import org.nuxeo.theme.elements.Element;
import org.nuxeo.theme.engines.EngineType;
import org.nuxeo.theme.formats.Format;
import org.nuxeo.theme.models.Model;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.themes.ThemeManager;

public class DummyRenderingInfo extends RenderingInfo {

    private final URL themeUrl;

    private final Element element;

    private String markup = "";

    private Model model;

    private Format format;

    private boolean dirty = false;

    public DummyRenderingInfo(Element element, URL themeUrl) {
        this.element = element;
        this.themeUrl = themeUrl;
    }

    @Override
    public DummyRenderingInfo createCopy() {
        return new DummyRenderingInfo(element, themeUrl);
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String getMarkup() {
        return markup;
    }

    @Override
    public void setMarkup(String markup) {
        this.markup = markup;
    }

    @Override
    public EngineType getEngine() {
        return ThemeManager.getEngineByUrl(themeUrl);
    }

    @Override
    public String getViewMode() {
        return ThemeManager.getViewModeByUrl(themeUrl);
    }

    @Override
    public Element getElement() {
        return element;
    }

    @Override
    public void setFormat(Format format) {
        this.format = format;
    }

    @Override
    public Format getFormat() {
        return format;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

}
