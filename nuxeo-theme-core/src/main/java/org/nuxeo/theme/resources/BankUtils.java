/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and others.
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
 */
package org.nuxeo.theme.resources;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nuxeo.common.utils.FileUtils;

public class BankUtils {

    final static Pattern filenamePattern = Pattern.compile("^\\p{IsAlnum}+[a-z0-9_\\-\\. ]*\\p{IsAlnum}+$|^\\p{IsAlnum}$");

    public static String getFileContent(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }
        return FileUtils.readFile(file);
    }

    public static String getDomId(String id) {
        return id.replaceAll("[\\s\\.]+", "-");
    }

    public static File[] listFilesSorted(File folder) {
        if (!folder.isDirectory()) {
            return null;
        }
        File files[] = folder.listFiles();
        Arrays.sort(files, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                return new Long(((File) o1).lastModified()).compareTo(new Long(((File) o2).lastModified()));
            }
        });
        return files;
    }

    public static boolean checkFilePath(String path) {
        for (String f : path.split("/")) {
            if ("".equals(f)) {
                continue;
            }
            Matcher m = filenamePattern.matcher(f);
            if (!m.find()) {
                return false;
            }
        }
        return true;
    }
}
