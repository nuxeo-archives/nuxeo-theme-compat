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

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("negotiation")
public final class NegotiationDef {

    @XNode("strategy")
    private String strategy;

    @XNode("default-engine")
    private String defaultEngine;

    @XNode("default-theme")
    private String defaultTheme;

    @XNode("default-perspective")
    private String defaultPerspective;

    public String getDefaultEngine() {
        return defaultEngine;
    }

    public void setDefaultEngine(final String defaultEngine) {
        this.defaultEngine = defaultEngine;
    }

    public String getDefaultPerspective() {
        return defaultPerspective;
    }

    public void setDefaultPerspective(final String defaultPerspective) {
        this.defaultPerspective = defaultPerspective;
    }

    public String getDefaultTheme() {
        return defaultTheme;
    }

    public void setDefaultTheme(final String defaultTheme) {
        this.defaultTheme = defaultTheme;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }

}
