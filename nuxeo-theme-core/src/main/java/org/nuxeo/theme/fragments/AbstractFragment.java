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

package org.nuxeo.theme.fragments;

import java.util.List;

import org.nuxeo.theme.Manager;
import org.nuxeo.theme.elements.AbstractElement;
import org.nuxeo.theme.models.Model;
import org.nuxeo.theme.models.ModelException;
import org.nuxeo.theme.perspectives.PerspectiveManager;
import org.nuxeo.theme.perspectives.PerspectiveType;

public abstract class AbstractFragment extends AbstractElement implements Fragment {

    public abstract Model getModel() throws ModelException;

    private FragmentType fragmentType;

    public void setFragmentType(FragmentType fragmentType) {
        this.fragmentType = fragmentType;
    }

    public FragmentType getFragmentType() {
        return fragmentType;
    }

    public boolean isVisibleInPerspective(PerspectiveType perspective) {
        return Manager.getPerspectiveManager().isVisibleInPerspective(this, perspective);
    }

    public void setVisibleInPerspective(PerspectiveType perspective) {
        PerspectiveManager.setVisibleInPerspective(this, perspective);
    }

    public void setAlwaysVisible() {
        Manager.getPerspectiveManager().setAlwaysVisible(this);
    }

    public List<PerspectiveType> getVisibilityPerspectives() {
        return Manager.getPerspectiveManager().getPerspectivesFor(this);
    }

    public boolean isDynamic() {
        return fragmentType.isDynamic();
    }

}
