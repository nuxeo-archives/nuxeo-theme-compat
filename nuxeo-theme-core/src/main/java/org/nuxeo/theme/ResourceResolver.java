/*
 * (C) Copyright 2011 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Florent Guillaume
 */
package org.nuxeo.theme;

import java.io.InputStream;
import java.net.URL;

/**
 * Resolver for resources.
 * <p>
 * This default implementation uses the thread context ClassLoader.
 *
 * @since 5.5
 */
public class ResourceResolver {

    private static final ResourceResolver DEFAULT = new ResourceResolver();

    private static ResourceResolver instance = DEFAULT;

    /**
     * Gets the current resolver (thread local).
     */
    public static ResourceResolver getInstance() {
        return instance;
    }

    /**
     * Called by the framework to set the current resolver or clear it.
     */
    public static void setInstance(ResourceResolver resolver) {
        instance = resolver == null ? DEFAULT : resolver;
    }

    /**
     * Gets a resource URL at the given path.
     *
     * @param path the path, which must not start with a /
     * @see javax.servlet.ServletContext#getResource
     * @see java.lang.ClassLoader#getResource
     */
    public URL getResource(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(path);
        if (url == null) {
            url = cl.getResource("nuxeo.war/" + path);
        }
        return url;
    }

    /**
     * Gets a resource at the given path.
     *
     * @param path the path, which must not start with a /
     * @see javax.servlet.ServletContext#getResourceAsStream
     * @see java.lang.ClassLoader#getResourceAsStream
     */
    public InputStream getResourceAsStream(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(path);
        if (is == null) {
            is = cl.getResourceAsStream("nuxeo.war/" + path);
        }
        return is;
    }

}
