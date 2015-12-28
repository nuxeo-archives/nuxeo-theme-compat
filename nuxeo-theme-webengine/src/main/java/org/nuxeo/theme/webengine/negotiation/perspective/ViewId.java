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

package org.nuxeo.theme.webengine.negotiation.perspective;

import org.nuxeo.ecm.webengine.model.Resource;
import org.nuxeo.ecm.webengine.model.WebContext;
import org.nuxeo.theme.ApplicationType;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.ViewDef;
import org.nuxeo.theme.negotiation.Scheme;
import org.nuxeo.theme.perspectives.PerspectiveManager;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.types.TypeRegistry;

public final class ViewId implements Scheme {

    public String getOutcome(final Object context) {
        WebContext webContext = (WebContext) context;
        final TypeRegistry typeRegistry = Manager.getTypeRegistry();
        final ApplicationType application = (ApplicationType) typeRegistry.lookup(TypeFamily.APPLICATION,
                webContext.getModulePath(), webContext.getModule().getName());
        if (application == null) {
            return null;
        }

        Resource targetObject = webContext.getTargetObject();
        if (targetObject == null) {
            return null;
        }
        final String rootPath = webContext.getRoot().getPath();
        final String viewId = targetObject.getPath().substring(rootPath.length());

        final ViewDef view = application.getViewById(viewId);
        if (view == null) {
            return null;
        }
        final String perspectiveName = view.getPerspective();
        if (PerspectiveManager.hasPerspective(perspectiveName)) {
            return perspectiveName;
        }
        return null;
    }
}
