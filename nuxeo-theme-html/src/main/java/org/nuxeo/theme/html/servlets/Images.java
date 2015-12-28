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
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.common.utils.FileUtils;
import org.nuxeo.ecm.core.io.download.BufferingServletOutputStream;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.html.Utils;
import org.nuxeo.theme.themes.ThemeException;

public final class Images extends HttpServlet implements Serializable {

    private static final Log log = LogFactory.getLog(Images.class);

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String path = request.getPathInfo().substring(1);
        byte[] data = null;
        try {
            data = Manager.getThemeManager().getImageResource(path);
        } catch (ThemeException e) {
            log.error("Image not found: " + path);
        }
        if (data != null) {
            OutputStream os = response.getOutputStream();
            BufferingServletOutputStream.stopBuffering(os);
            String ext = FileUtils.getFileExtension(path);
            String mimeType = Utils.getImageMimeType(ext);
            response.addHeader("content-type", mimeType);

            // Cache headers
            final String lifetime = "604800"; // 1 week
            final long now = System.currentTimeMillis();
            response.addHeader("Cache-Control", "max-age=" + lifetime);
            response.addHeader("Cache-Control", "must-revalidate");
            response.setDateHeader("Last-Modified", now);
            response.setDateHeader("Expires", now + new Long(lifetime) * 1000L);

            os.write(data);
            os.close();
        }
    }
}
