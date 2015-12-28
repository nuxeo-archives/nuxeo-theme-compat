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

package org.nuxeo.theme.negotiation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nuxeo.theme.Manager;
import org.nuxeo.theme.types.TypeFamily;

public abstract class AbstractNegotiator implements Negotiator {

    private static final String SPEC_PREFIX = "nxtheme://theme";

    // FIXME: can be called 'web' under webengine
    private static final String DEFAULT_NEGOTIATION_STRATEGY = "default";

    protected final String strategy;

    protected final Object context;

    protected final HttpServletRequest request;

    public abstract String getTemplateEngineName();

    protected AbstractNegotiator(String strategy, Object context, HttpServletRequest request) {
        this.strategy = strategy;
        this.context = context;
        this.request = request;
    }

    public final String getSpec() throws NegotiationException {
        return String.format("%s/%s/%s/%s/%s/%s/%s", SPEC_PREFIX, negotiate("engine"), negotiate("mode"),
                getTemplateEngineName(), negotiate("theme"), negotiate("perspective"), negotiate("collection"));
    }

    public final synchronized String negotiate(String object) throws NegotiationException {
        if (strategy == null) {
            throw new NegotiationException("No negotiation strategy is set.");
        }
        NegotiationType negotiation = (NegotiationType) Manager.getTypeRegistry().lookup(TypeFamily.NEGOTIATION,
                String.format("%s/%s", strategy, object));
        // Try with the 'default' strategy
        if (negotiation == null) {
            negotiation = (NegotiationType) Manager.getTypeRegistry().lookup(TypeFamily.NEGOTIATION,
                    String.format("%s/%s", DEFAULT_NEGOTIATION_STRATEGY, object));
        }
        if (negotiation == null) {
            throw new NegotiationException("Could not obtain negotiation for: " + strategy + " (strategy) " + object
                    + " (object)");
        }
        final List<Scheme> schemes = negotiation.getSchemes();

        String outcome = null;
        if (schemes != null) {
            for (Scheme scheme : negotiation.getSchemes()) {
                outcome = scheme.getOutcome(context);
                if (outcome != null) {
                    break;
                }
            }
        }
        if (outcome == null) {
            throw new NegotiationException("No negotiation outcome found for:  " + strategy + " (strategy) " + object
                    + " (object)");
        } else {
            // add result to the request
            request.setAttribute(NEGOTIATION_RESULT_PREFIX + object, outcome);
        }
        return outcome;
    }
}
