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
