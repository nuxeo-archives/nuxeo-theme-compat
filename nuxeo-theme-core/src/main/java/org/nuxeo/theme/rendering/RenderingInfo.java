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

package org.nuxeo.theme.rendering;

import java.net.URL;

import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.Element;
import org.nuxeo.theme.elements.ThemeElement;
import org.nuxeo.theme.engines.EngineType;
import org.nuxeo.theme.formats.Format;
import org.nuxeo.theme.models.Info;
import org.nuxeo.theme.models.Model;
import org.nuxeo.theme.templates.TemplateEngineType;
import org.nuxeo.theme.themes.ThemeManager;
import org.nuxeo.theme.uids.Identifiable;

public class RenderingInfo implements Info, Identifiable {

    private String markup = "";

    private Model model;

    private Element element;

    private Format format;

    private Integer uid;

    private String name;

    private URL themeUrl;

    private boolean dirty = false;

    public RenderingInfo() {
    }

    public RenderingInfo(Element element, URL themeUrl) {
        this.element = element;
        this.themeUrl = themeUrl;
        uid = element.getUid();
    }

    public RenderingInfo createCopy() {
        RenderingInfo clone = new RenderingInfo(element, themeUrl);
        clone.setDirty(dirty);
        return clone;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public EngineType getEngine() {
        return ThemeManager.getEngineByUrl(themeUrl);
    }

    public String getViewMode() {
        return ThemeManager.getViewModeByUrl(themeUrl);
    }

    public String getCollection() {
        return ThemeManager.getCollectionNameByUrl(themeUrl);
    }

    public URL getThemeUrl() {
        return themeUrl;
    }

    public Element getElement() {
        return element;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Format getFormat() {
        return format;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemeElement getTheme() {
        return Manager.getThemeManager().getThemeByUrl(themeUrl);
    }

    public TemplateEngineType getTemplateEngine() {
        return ThemeManager.getTemplateEngineByUrl(themeUrl);
    }

    public boolean isRenderingPostponed(boolean cache) {
        return cache && isDirty();
    }

}
