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

package org.nuxeo.theme.nodes;

import java.util.List;

public interface Node {

    NodeTypeFamily getNodeTypeFamily();

    void clearParent();

    void setParent(Node node) throws NodeException;

    Node getParent();

    Node addChild(Node node) throws NodeException;

    void removeChild(Node node) throws NodeException;

    List<Node> getChildren();

    void setChildren(List<Node> children) throws NodeException;

    Integer getOrder();

    void setOrder(Integer order) throws NodeException;

    void moveTo(Node container, Integer order) throws NodeException;

    boolean isLeaf();

    void insertAfter(Node node) throws NodeException;

    boolean hasSiblings();

    Node getNextNode();

    Node getPreviousNode();

    boolean hasChildren();

    boolean isChildOf(Node node);

    void removeDescendants() throws NodeException;

    List<Node> getDescendants();

    void collectDescendants(List<Node> nodes);

}
