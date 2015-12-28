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
package org.nuxeo.theme.html.servlets;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.nuxeo.theme.ResourceResolver;

/**
 * Resolver for resources that checks the servlet context first.
 *
 * @since 5.5
 */
public class ServletResourceResolver extends ResourceResolver implements ServletContextListener {

    protected ServletContext servletContext;

    @Override
    public URL getResource(String path) {
        try {
            URL url = servletContext.getResource("/" + path);
            if (url != null) {
                return url;
            }
        } catch (MalformedURLException e) {
            // continue
        }
        return super.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        InputStream is = servletContext.getResourceAsStream("/" + path);
        if (is != null) {
            return is;
        }
        return super.getResourceAsStream(path);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        ResourceResolver.setInstance(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext = null;
        ResourceResolver.setInstance(null);
    }

}
