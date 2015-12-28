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

package org.nuxeo.theme.elements;

import java.net.URL;
import java.util.List;

import org.nuxeo.theme.nodes.AbstractNode;
import org.nuxeo.theme.nodes.Node;
import org.nuxeo.theme.nodes.NodeTypeFamily;

public abstract class AbstractElement extends AbstractNode implements Element {

    private ElementType elementType;

    private Integer uid;

    private String name;

    private String description;

    private String cssClassName;

    public Integer getUid() {
        return uid;
    }

    public void setUid(final Integer uid) {
        this.uid = uid;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(final ElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public NodeTypeFamily getNodeTypeFamily() {
        return elementType.getNodeTypeFamily();
    }

    public String hash() {
        if (uid == null) {
            return null;
        }
        return uid.toString();
    }

    public List<Node> getChildrenInContext(final URL themeURL) {
        return getChildren();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isEmpty() {
        return !hasChildren();
    }

    @Override
    public String getCssClassName() {
        return cssClassName;
    }

    @Override
    public void setCssClassName(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String computeXPath() {
        final StringBuilder s = new StringBuilder();
        String typeName = null;
        Element e = this;
        do {
            Integer order = e.getOrder();
            if (order != null) {
                order += 1;
                s.insert(0, "[" + order + ']');
            }
            typeName = e.getElementType().getTypeName();
            if (typeName.equals("theme")) {
                break;
            }
            s.insert(0, typeName);
            if (typeName.equals("page")) {
                break;
            }
            s.insert(0, '/');
            e = (Element) e.getParent();
        } while (e != null);
        return s.toString();
    }

}
