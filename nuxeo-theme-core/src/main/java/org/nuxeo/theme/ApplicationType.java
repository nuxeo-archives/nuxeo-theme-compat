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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeMap;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.theme.types.Type;
import org.nuxeo.theme.types.TypeFamily;

@XObject("application")
public final class ApplicationType implements Type {

    private String root;

    @XNode("@template-engine")
    private String templateEngine;

    @XNode("negotiation")
    private NegotiationDef negotiation;

    @XNode("resource-caching")
    private CachingDef resourceCaching;

    @XNode("style-caching")
    private CachingDef styleCaching;

    @XNodeMap(value = "view", key = "@id", type = HashMap.class, componentType = ViewDef.class)
    private Map<String, ViewDef> viewDefs;

    public TypeFamily getTypeFamily() {
        return TypeFamily.APPLICATION;
    }

    public String getTypeName() {
        return root;
    }

    public CachingDef getResourceCaching() {
        return resourceCaching;
    }

    public CachingDef getStyleCaching() {
        return styleCaching;
    }

    public Set<String> getViewIds() {
        return viewDefs.keySet();
    }

    public ViewDef getViewById(String id) {
        return viewDefs.get(id);
    }

    public NegotiationDef getNegotiation() {
        return negotiation;
    }

    public String getRoot() {
        return root;
    }

    @XNode("@root")
    public void setRoot(final String root) {
        this.root = Framework.expandVars(root);
    }

    public Map<String, ViewDef> getViewDefs() {
        return viewDefs;
    }

    public void setViewDefs(final Map<String, ViewDef> viewDefs) {
        this.viewDefs = viewDefs;
    }

    public void setNegotiation(final NegotiationDef negotiation) {
        this.negotiation = negotiation;
    }

    public void setResourceCaching(final CachingDef resourceCaching) {
        this.resourceCaching = resourceCaching;
    }

    public void setStyleCaching(final CachingDef styleCaching) {
        this.styleCaching = styleCaching;
    }

    public String getTemplateEngine() {
        return templateEngine;
    }

    public void setTemplateEngine(String templateEngine) {
        this.templateEngine = templateEngine;
    }

}
