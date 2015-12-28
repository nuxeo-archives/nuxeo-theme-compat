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

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.theme.Manager;

public abstract class AbstractModel implements Model {

    private final List<Model> items = new ArrayList<Model>();

    public String getModelTypeName() {
        ModelType modelType = getModelType();
        if (modelType == null) {
            return null;
        }
        return modelType.getTypeName();
    }

    public ModelType getModelType() {
        final String className = this.getClass().getCanonicalName();
        return Manager.getThemeManager().getModelByClassname(className);
    }

    public Model addItem(Model model) throws ModelException {
        ModelType modelType = getModelType();
        if (modelType == null) {
            throw new ModelException("Model type not found: " + getModelTypeName());
        }
        if (!getModelType().getAllowedTypes().contains(model.getModelTypeName())) {
            throw new ModelException("Model type: " + model.getModelTypeName() + " not allowed in: "
                    + getModelTypeName());
        }
        items.add(model);
        return model;
    }

    public Model insertItem(int index, Model model) throws ModelException {
        if (!getModelType().getAllowedTypes().contains(model.getModelTypeName())) {
            throw new ModelException("Model type: " + model.getModelTypeName() + " not allowed in: "
                    + getModelTypeName());
        }
        items.add(index, model);
        return model;
    }

    public List<Model> getItems() {
        return items;
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }
}
