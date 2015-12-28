/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and others.
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

package org.nuxeo.theme.webengine.fm.filters.widgets;

import org.nuxeo.theme.models.Region;
import org.nuxeo.theme.rendering.RenderingInfo;
import org.nuxeo.theme.views.AbstractView;

public final class BlockRegionView extends AbstractView {

    @Override
    public String render(final RenderingInfo info) {
        final Region region = (Region) info.getModel();
        final StringBuilder s = new StringBuilder();
        if ("".equals(region.name)) {
            s.append("<div class=\"nxthemesRegionNotSet\">").append("No region name is set...").append("</div>");
        } else {
            s.append("<div class=\"themeRegion\">");
            s.append("<@block name=\"").append(region.name).append("\">");
            if ("".equals(region.defaultSrc)) {
                s.append(region.defaultBody);
            } else {
                s.append("<#include \"").append(region.defaultSrc).append("\" />");
            }
            s.append("</@block>");
            s.append("</div>");
        }
        return s.toString();
    }

}
