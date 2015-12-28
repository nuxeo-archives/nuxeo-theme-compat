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

package org.nuxeo.theme.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.theme.Registrable;

public final class TypeRegistry implements Registrable {

    private static final Log log = LogFactory.getLog(TypeRegistry.class);

    private final Map<String, Type> registry = new ConcurrentHashMap<String, Type>();

    private final Map<TypeFamily, List<String>> typeNames = new ConcurrentHashMap<TypeFamily, List<String>>();

    public synchronized void register(final Type type) {
        String typeName = type.getTypeName();
        TypeFamily typeFamily = type.getTypeFamily();
        String key = computeKey(typeFamily, typeName);
        if (registry.containsKey(key)) {
            log.debug("Overriding theme " + typeFamily + ": " + typeName);
        }
        registry.put(key, type);
        if (!typeNames.containsKey(typeFamily)) {
            typeNames.put(typeFamily, new ArrayList<String>());
        }
        List<String> typeNamesByFamily = typeNames.get(typeFamily);
        if (!typeNamesByFamily.contains(typeName)) {
            typeNamesByFamily.add(typeName);
        }
        log.debug("Registered theme " + typeFamily + ": " + typeName);
    }

    public synchronized void unregister(final Type type) {
        String typeName = type.getTypeName();
        TypeFamily typeFamily = type.getTypeFamily();
        String key = computeKey(typeFamily, typeName);
        registry.remove(key);
        if (typeNames.containsKey(typeFamily)) {
            typeNames.get(typeFamily).remove(typeName);
        }
        log.debug("Unregistered theme " + typeFamily + ": " + typeName);
    }

    public Type lookup(final TypeFamily typeFamily, final String name) {
        String key = computeKey(typeFamily, name);
        return registry.get(key);
    }

    public Type lookup(final TypeFamily typeFamily, final String... names) {
        for (String name : names) {
            if (name == null) {
                continue;
            }
            String key = computeKey(typeFamily, name);
            Type type = registry.get(key);
            if (type != null) {
                return type;
            }
        }
        return null;

    }

    public List<String> getTypeNames(final TypeFamily typeFamily) {
        if (!typeNames.containsKey(typeFamily)) {
            return new ArrayList<String>();
        }
        return typeNames.get(typeFamily);
    }

    public List<Type> getTypes(final TypeFamily typeFamily) {
        List<Type> types = new ArrayList<Type>();
        if (typeNames.containsKey(typeFamily)) {
            for (String typeName : typeNames.get(typeFamily)) {
                types.add(lookup(typeFamily, typeName));
            }
        }
        return types;
    }

    private static String computeKey(final TypeFamily family, final String name) {
        return String.format("%s/%s", family, name);
    }

    @Override
    public synchronized void clear() {
        registry.clear();
        typeNames.clear();
    }

}
