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

package org.nuxeo.theme.webengine.negotiation;

import javax.servlet.http.HttpServletRequest;

import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.theme.negotiation.AbstractNegotiator;

public final class WebNegotiator extends AbstractNegotiator {

    public String getTemplateEngineName() {
        return "freemarker";
    }

    public WebNegotiator(final String strategy, final WebContext context, final HttpServletRequest request) {
        super(strategy, context, request);
    }

}
