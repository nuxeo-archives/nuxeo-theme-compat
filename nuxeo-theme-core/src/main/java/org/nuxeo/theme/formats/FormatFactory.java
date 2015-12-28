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

package org.nuxeo.theme.formats;

import org.nuxeo.theme.Manager;
import org.nuxeo.theme.themes.ThemeException;
import org.nuxeo.theme.types.TypeFamily;
import org.nuxeo.theme.uids.UidManager;

public final class FormatFactory {

    // This class is not supposed to be instantiated.
    private FormatFactory() {
    }

    public static Format create(final String typeName) throws ThemeException {
        Format format = null;
        final FormatType formatType = (FormatType) Manager.getTypeRegistry().lookup(TypeFamily.FORMAT, typeName);
        if (formatType == null) {
            throw new ThemeException("Unknown format type: " + typeName);
        }

        final UidManager uidManager = Manager.getUidManager();
        try {
            format = (Format) Class.forName(formatType.getFormatClass()).newInstance();
        } catch (InstantiationException e) {
            throw new ThemeException(e);
        } catch (IllegalAccessException e) {
            throw new ThemeException(e);
        } catch (ClassNotFoundException e) {
            throw new ThemeException("Format creation failed: " + typeName, e);
        }
        format.setFormatType(formatType);
        uidManager.register(format);
        return format;
    }

}
