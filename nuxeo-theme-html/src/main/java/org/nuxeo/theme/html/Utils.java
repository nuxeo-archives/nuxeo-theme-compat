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

package org.nuxeo.theme.html;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.CachingDef;

public final class Utils {

    private static final Log log = LogFactory.getLog(Utils.class);

    private static final String[] lengthUnits = { "%", "em", "px", "ex", "pt", "in", "cm", "mm", "pc" };

    private Utils() {
        // This class is not supposed to be instantiated.
    }

    public static String toJson(final Object object) {
        return JSONObject.fromObject(object).toString();
    }

    /* web lengths */

    public static String addWebLengths(final String length1, final String length2) {
        final WebLength webLength1 = getWebLength(length1);
        final WebLength webLength2 = getWebLength(length2);
        if (!webLength1.unit.equals(webLength2.unit)) {
            return null;
        }
        return new WebLength(webLength1.value + webLength2.value, webLength1.unit).toString();
    }

    public static String substractWebLengths(final String length1, final String length2) {
        final WebLength webLength1 = getWebLength(length1);
        final WebLength webLength2 = getWebLength(length2);
        if (!webLength1.unit.equals(webLength2.unit)) {
            return null;
        }
        return new WebLength(webLength1.value - webLength2.value, webLength1.unit).toString();
    }

    public static String divideWebLength(final String length, final int divider) {
        if (divider <= 0) {
            return null;
        }
        final WebLength webLength = getWebLength(length);
        if (webLength != null) {
            return new WebLength(webLength.value / divider, webLength.unit).toString();
        }
        return null;
    }

    private static WebLength getWebLength(final String length) {
        Integer value = null;
        String unit = null;
        for (String lengthUnit : lengthUnits) {
            if (length.endsWith(lengthUnit)) {
                unit = lengthUnit;
                try {
                    value = Integer.valueOf(length.substring(0, length.length() - lengthUnit.length()));
                } catch (NumberFormatException e) {
                    log.error("Could not convert web lengths to integers", e);
                }
                break;
            }
        }
        if (value != null && unit != null) {
            return new WebLength(value, unit);
        }
        return null;
    }

    public static boolean supportsGzip(final HttpServletRequest request) {
        final String encoding = request.getHeader("Accept-Encoding");
        return encoding != null && encoding.toLowerCase(Locale.ENGLISH).contains("gzip");
    }

    public static void setCacheHeaders(final HttpServletResponse response, final CachingDef caching) {
        if (caching != null) {
            final String lifetime = caching.getLifetime();
            if (lifetime != null) {
                final long now = System.currentTimeMillis();
                response.addHeader("Cache-Control", "max-age=" + lifetime);
                response.addHeader("Cache-Control", "must-revalidate");
                response.setDateHeader("Last-Modified", now);
                response.setDateHeader("Expires", now + new Long(lifetime) * 1000L);
            }
        }
    }

    public static boolean isVirtualHosting(final HttpServletRequest request) {
        if (request.getHeader("X-Forwarded-Host") != null) {
            return true;
        }
        return false;
    }

    public static String getImageMimeType(String ext) {
        ext = ext.toLowerCase();
        if ("png".equals(ext)) {
            return "image/png";
        } else if ("gif".equals(ext)) {
            return "image/gif";
        } else if ("jpg".equals(ext) || "jpeg".equals(ext) || "jpe".equals(ext)) {
            return "image/jpeg";
        }
        return "application/octet-stream";
    }
}
