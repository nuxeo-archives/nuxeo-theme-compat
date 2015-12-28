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
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.theme.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class OrderedProperties extends Properties {

    private static final long serialVersionUID = 1L;

    private final List<Object> order;

    public OrderedProperties() {
        order = new ArrayList<Object>();
    }

    @Override
    public Enumeration<Object> propertyNames() {
        return Collections.enumeration(order);
    }

    @Override
    public Object put(Object key, Object value) {
        if (order.contains(key)) {
            order.remove(key);
        }
        order.add(key);
        return super.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        order.remove(key);
        return super.remove(key);
    }

}
