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

package org.nuxeo.theme.jsf.negotiation;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public final class CookieManager {

    public static String getCookie(final String name, final ExternalContext context) {
        if (context == null) {
            return null;
        }
        final Map<String, Object> cookies = context.getRequestCookieMap();
        final Cookie cookie = (Cookie) cookies.get(name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public static void setCookie(String name, String value, final ExternalContext context) {
        if (context == null) {
            return;
        }
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        final Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }

    public static void expireCookie(String name, final ExternalContext context) {
        if (context == null) {
            return;
        }
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        final Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
