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

package org.nuxeo.theme.jsf.taglib;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public abstract class BaseMVCTag extends UIComponentELTag {

    private String resource;

    private String url;

    @Override
    public abstract String getComponentType();

    @Override
    public String getRendererType() {
        // null means the component renders itself
        return null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        if (null != url && null != resource) {
            throw new IllegalArgumentException("Cannot specify both a URL and a resource.");
        }

        if (null != resource) {
            component.getAttributes().put("resource", resource);
        }

        if (null != url) {
            if (!url.startsWith("/")) {
                throw new IllegalArgumentException("The URL must begin with /");
            }
            component.getAttributes().put("url", url);
        }

    }

    @Override
    public void release() {
        super.release();
        resource = null;
        url = null;
    }

    /* property accessors */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
