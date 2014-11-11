/*
 * (C) Copyright 2006-2007 Nuxeo SAS <http://nuxeo.com> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jean-Marc Orliaguet, Chalmers
 *
 * $Id$
 */

package org.nuxeo.theme.html.ui;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;

import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;
import org.nuxeo.theme.Manager;
import org.nuxeo.theme.formats.styles.Style;
import org.nuxeo.theme.html.CSSUtils;
import org.nuxeo.theme.themes.ThemeManager;

public class ThemeStyles {

    private static final boolean RESOLVE_PRESETS = true;

    private static final boolean IGNORE_VIEW_NAME = false;

    private static final boolean IGNORE_CLASSNAME = false;

    private static final boolean INDENT = false;

    public static String render(Map<String, String> params, boolean cache,
            boolean inline, boolean virtualHosting) {
        String themeName = params.get("themeName");
        String path = params.get("path");
        String basePath = params.get("basepath");

        String cssPath = VirtualHostHelper.getContextPathProperty()
                + "/nxthemes-css";

        if (inline) {
            final StringBuilder sb = new StringBuilder();
            sb.append("<style type=\"text/css\">");
            final ThemeManager themeManager = Manager.getThemeManager();
            for (Style style : themeManager.getNamedStyles(themeName)) {
                sb.append(CSSUtils.styleToCss(style, style.getSelectorViewNames(),
                        RESOLVE_PRESETS, IGNORE_VIEW_NAME, IGNORE_CLASSNAME,
                        INDENT));
            }
            for (Style style : themeManager.getStyles(themeName)) {
                sb.append(CSSUtils.styleToCss(style, style.getSelectorViewNames(),
                        RESOLVE_PRESETS, IGNORE_VIEW_NAME, IGNORE_CLASSNAME,
                        INDENT));
            }
            sb.append("</style>");
            String rendered = sb.toString();
            if (basePath != null) {
                rendered = rendered.replaceAll("\\$\\{basePath\\}",
                        Matcher.quoteReplacement(basePath));
            }
            return rendered;
        }
        if (cache) {
            return String.format(
                    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\""
                            + cssPath
                            + "/?theme=%s&amp;path=%s&amp;basepath=%s\" />",
                    themeName, path, basePath);
        } else {
            return String.format(
                    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\""
                            + cssPath
                            + "/?theme=%s&amp;path=%s&amp;basepath=%s&amp;timestamp=%s\" />",
                    themeName, path, basePath, new Date().getTime());
        }
    }
}
