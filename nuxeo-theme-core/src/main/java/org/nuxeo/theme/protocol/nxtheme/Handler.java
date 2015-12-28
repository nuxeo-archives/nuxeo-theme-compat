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

package org.nuxeo.theme.protocol.nxtheme;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public final class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL url) {
        return new Connection(url);
    }

    /**
     * Theme URL do not reference any networked resource. This method is called by URL.equals and URL.hashCode so that
     * we need override it to avoid DNS lookup for the theme host.
     * 
     * @param u a URL object
     * @return null for url with nxtheme protocol, URLStreamHandler.getHostAddress(u) otherwise
     */
    @Override
    protected synchronized InetAddress getHostAddress(URL u) {
        if ("nxtheme".equals(u.getProtocol())) {
            // do not do a DNS lookup for a theme resource
            return null;
        }
        return super.getHostAddress(u);
    }

}
