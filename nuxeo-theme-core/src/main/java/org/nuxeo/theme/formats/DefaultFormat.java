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

import java.util.Enumeration;
import java.util.Properties;

import org.nuxeo.theme.relations.Predicate;

public class DefaultFormat implements Format {

    private String description;

    private FormatType formatType;

    private Properties properties = new Properties();

    private Integer uid;

    private String name;

    private boolean remote = false;

    private boolean customized = false;

    private boolean external;

    public Integer getUid() {
        return uid;
    }

    public void setUid(final Integer uid) {
        this.uid = uid;
    }

    public String hash() {
        if (uid == null) {
            return null;
        }
        return uid.toString();
    }

    public Predicate getPredicate() {
        return formatType.getPredicate();
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(final FormatType formatType) {
        this.formatType = formatType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /* Properties */

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }

    public Enumeration<?> getPropertyNames() {
        return properties.propertyNames();
    }

    public boolean hasProperties() {
        return !properties.isEmpty();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void clonePropertiesOf(final Format source) {
        Properties sourceProperties = source.getProperties();
        for (Object key : sourceProperties.keySet()) {
            String propertyName = (String) key;
            setProperty(propertyName, sourceProperties.getProperty(propertyName));
        }
    }

    public boolean isNamed() {
        return false;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public boolean isCustomized() {
        return customized;
    }

    public void setCustomized(boolean customized) {
        this.customized = customized;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public boolean isExternal() {
        return external;
    }
}
