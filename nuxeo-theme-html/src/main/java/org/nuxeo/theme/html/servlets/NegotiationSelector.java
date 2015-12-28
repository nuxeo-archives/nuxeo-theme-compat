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

package org.nuxeo.theme.html.servlets;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class NegotiationSelector extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String referrer = request.getHeader("referer");
        if (referrer == null) {
            response.getWriter().write("no referrer found");
            return;
        }

        final String engine = request.getParameter("engine");
        if (engine != null) {
            response.addCookie(createCookie("nxthemes.engine", engine));
        }

        final String mode = request.getParameter("mode");
        if (mode != null) {
            response.addCookie(createCookie("nxthemes.mode", mode));
        }

        final String theme = request.getParameter("theme");
        if (theme != null) {
            response.addCookie(createCookie("nxthemes.theme", theme));
        }

        final String perspective = request.getParameter("perspective");
        if (perspective != null) {
            response.addCookie(createCookie("nxthemes.perspective", perspective));
        }

        response.sendRedirect(referrer);
    }

    private Cookie createCookie(final String name, final String value) {
        final Cookie cookie = new Cookie(name, value);

        // remove the cookie of the value is an empty string
        if (value.equals("")) {
            cookie.setMaxAge(0);
        }
        cookie.setPath("/");
        return cookie;
    }
}
