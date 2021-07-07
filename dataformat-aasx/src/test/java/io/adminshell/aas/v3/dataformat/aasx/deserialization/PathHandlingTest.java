/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
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
 */
package io.adminshell.aas.v3.dataformat.aasx.deserialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PathHandlingTest {

    private static final String[] empty = new String[] { "/", "" };
    private static final String[] single = new String[] { "element", "/element", "element/", "/element/" };
    private static final String[] pair = new String[] { "parent/child", "/parent/child", "parent/child/", "/parent/child/" };

    @Test
    public void testGetLastElement() {
        for (String test : empty) {
            assertEquals("", PathHandling.getLastElement(test));
        }
        for (String test : single) {
            assertEquals("element", PathHandling.getLastElement(test));
        }
        for (String test : pair) {
            assertEquals("child", PathHandling.getLastElement(test));
        }
        assertNull(PathHandling.getLastElement(null));
    }

    @Test
    public void testGetParentPath() {
        for (String test : empty) {
            assertEquals("", PathHandling.getParentPath(test));
        }
        for (String test : single) {
            assertEquals("", PathHandling.getParentPath(test));
        }
        for (String test : pair) {
            assertEquals("parent", PathHandling.getParentPath(test));
        }
        assertNull(PathHandling.getParentPath(null));
    }
}
