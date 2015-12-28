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

package org.nuxeo.theme.rendering;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.types.TypeFamily;

public final class StandaloneFilterFactory {

    private static final Log log = LogFactory.getLog(StandaloneFilterFactory.class);

    public static StandaloneFilter create(String typeName) {
        StandaloneFilter filter = null;
        StandaloneFilterType filterType = (StandaloneFilterType) Manager.getTypeRegistry().lookup(TypeFamily.FILTER,
                typeName);
        try {
            filter = (StandaloneFilter) Thread.currentThread().getContextClassLoader().loadClass(
                    filterType.getClassName()).newInstance();
        } catch (ReflectiveOperationException e) {
            log.error(e, e);
        }
        return filter;
    }

}
