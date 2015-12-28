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

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Collections;
import java.util.Map;

import org.nuxeo.common.utils.URLStreamHandlerFactoryInstaller;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.theme.perspectives.PerspectiveManager;
import org.nuxeo.theme.protocol.nxtheme.Handler;
import org.nuxeo.theme.relations.RelationStorage;
import org.nuxeo.theme.resources.ResourceManager;
import org.nuxeo.theme.services.ThemeService;
import org.nuxeo.theme.themes.ThemeManager;
import org.nuxeo.theme.types.TypeRegistry;
import org.nuxeo.theme.uids.UidManager;
import org.nuxeo.theme.vocabularies.VocabularyManager;

public final class Manager {

    private static final String PROTOCOL_HANDLER_PKG = "org.nuxeo.theme.protocol";

    private Manager() {
    }

    public static ThemeService getThemeService() {
        return (ThemeService) Framework.getRuntime().getComponent(ThemeService.ID);
    }

    private static Map<String, Registrable> getRegistries() {
        // avoid error when clearing registries at shutdown
        ThemeService service = getThemeService();
        if (service != null) {
            return service.getRegistries();
        } else {
            return Collections.emptyMap();
        }
    }

    public static Registrable getRegistry(final String name) {
        return getRegistries().get(name);
    }

    public static RelationStorage getRelationStorage() {
        return (RelationStorage) getRegistry("relations");
    }

    public static UidManager getUidManager() {
        return (UidManager) getRegistry("uids");
    }

    public static ThemeManager getThemeManager() {
        return (ThemeManager) getRegistry("themes");
    }

    public static TypeRegistry getTypeRegistry() {
        return (TypeRegistry) getRegistry("types");
    }

    public static ResourceManager getResourceManager() {
        return (ResourceManager) getRegistry("resources");
    }

    public static PerspectiveManager getPerspectiveManager() {
        return (PerspectiveManager) getRegistry("perspectives");
    }

    public static VocabularyManager getVocabularyManager() {
        return (VocabularyManager) getRegistry("vocabularies");
    }

    protected static URLStreamHandlerFactory shf;

    public static void initializeProtocols() {
        shf = new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                if ("nxtheme".equals(protocol)) {
                    return new Handler();
                }
                return null;
            }
        };
        URLStreamHandlerFactoryInstaller.installURLStreamHandlerFactory(shf);
    }

    public static void resetProtocols() {
        URLStreamHandlerFactoryInstaller.uninstallURLStreamHandlerFactory(shf);
        shf = null;
    }
}
