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

package org.nuxeo.theme.models;

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.theme.rendering.RenderingInfo;

public final class InfoPool {

    private static final String INFOID_PREFIX = "i";

    protected static final ThreadLocal<HashMap<String, Info>> threadInstance = new ThreadLocal<HashMap<String, Info>>() {
        @Override
        protected HashMap<String, Info> initialValue() {
            return new HashMap<String, Info>();
        }
    };

    public static Map<String, Info> getInfoMap() {
        return threadInstance.get();
    }

    public static void register(RenderingInfo info) {
        getInfoMap().put(computeInfoId(info), info);
    }

    public static Info get(String key) {
        return getInfoMap().get(key);
    }

    public static String computeInfoId(RenderingInfo info) {
        return INFOID_PREFIX + info.getUid().toString();
    }

}
