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

package org.nuxeo.theme.compat.negotiators;

import org.jboss.seam.Component;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.PageElement;
import org.nuxeo.theme.localconfiguration.LocalThemeConfig;
import org.nuxeo.theme.localconfiguration.LocalThemeHelper;
import org.nuxeo.theme.negotiation.Scheme;

/**
 * Negotiation scheme for obtaining the local theme from the current space.
 *
 * @author <a href="mailto:jmo@chalmers.se">Jean-Marc Orliaguet</a>
 * @deprecated since 5.5: use local theme flavor instead
 */
@Deprecated
public class LocalTheme implements Scheme {

    /**
     * Called by the theme negotiation module.
     *
     * @return the local theme associated to the current space (workspace, section, ...) as a 'theme/page' string.
     *         Return null otherwise.
     */
    public String getOutcome(Object context) {
        Boolean useOldThemeConf = Boolean.valueOf(Framework.getProperty(LocalThemeConfig.OLD_THEME_CONFIGURATION_PROPERTY));
        if (Boolean.FALSE.equals(useOldThemeConf)) {
            return null;
        }

        DocumentModel currentSuperSpace = (DocumentModel) Component.getInstance("currentSuperSpace");
        if (currentSuperSpace == null) {
            return null;
        }

        // Get the placeful local theme configuration for the current
        // workspace.
        LocalThemeConfig localThemeConfig = LocalThemeHelper.getLocalThemeConfig(currentSuperSpace);
        if (localThemeConfig == null) {
            return null;
        }

        // Extract the page path
        String path = localThemeConfig.computePagePath();
        if (path == null) {
            return null;
        }

        // Look up the page
        PageElement page = Manager.getThemeManager().getPageByPath(path);
        if (page != null) {
            return path;
        }
        return null;
    }

}
