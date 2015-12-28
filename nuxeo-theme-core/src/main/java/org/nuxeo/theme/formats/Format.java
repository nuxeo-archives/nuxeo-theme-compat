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

import org.nuxeo.theme.properties.PropertySheet;
import org.nuxeo.theme.relations.Predicate;
import org.nuxeo.theme.relations.Relate;
import org.nuxeo.theme.uids.Identifiable;

public interface Format extends Relate, Identifiable, PropertySheet {

    FormatType getFormatType();

    void setFormatType(FormatType formatType);

    Predicate getPredicate();

    void setDescription(String description);

    String getDescription();

    void clonePropertiesOf(Format source);

    boolean isNamed();

    boolean isCustomized();

    boolean isRemote();

    void setRemote(boolean remote);

    void setCustomized(boolean customized);

}
