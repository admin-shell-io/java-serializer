package io.adminshell.aas.v3.dataformat.aasx.deserialization;

import java.util.ArrayList;
import java.util.List;

public class PathHandling {
    /**
     * Returns the last element of path separated by "/", e.g. a/b/c -> c
     * 
     * @param path
     * @return
     */
    public static String getLastElement(String path) {
        // Return null result for null argument
        if (path == null) {
            return null;
        }

        String[] elements = splitPath(path);
        if (elements.length > 0) {
            return elements[elements.length - 1];
        } else {
            return "";
        }
    }

    /**
     * Removes the last element from the path, e.g. a/b/c -> a/b
     * 
     * @param path
     * @return
     */
    public static String getParentPath(String path) {
        // Return null result for null argument
        if (path == null) {
            return null;
        }

        if (isEmptyPath(path)) {
            return "";
        }
        int lastIndex = path.lastIndexOf("/");
        if (lastIndex == path.length() - 1) {
            lastIndex = path.lastIndexOf("/", path.length() - 2);
        }
        if (lastIndex >= 0) {
            return removePrefix(path.substring(0, lastIndex), "/");
        } else {
            return "";
        }
    }

    /**
     * Splits a path separated by "/" into its parts, e.g. a/b/c -> {"a", "b", "c"}
     * 
     * @param path
     * @return
     */
    private static String[] splitPath(String path) {
        // Return null result for null argument
        if (path == null) {
            return null;
        }

        // includes null-values, "" and "/";
        if (isEmptyPath(path)) {
            return new String[] {};
        }

        // Remove leading separator, otherwise /a leads to {"", "a"}
        String fixedPath = removePrefix(path, "/");

        String[] splitted = fixedPath.split("/");
        List<String> nonEmptySplitted = new ArrayList<>();

        // Remove empty entries
        for (String s : splitted) {
            if (!s.isEmpty()) {
                nonEmptySplitted.add(s);
            }
        }
        return nonEmptySplitted.toArray(new String[nonEmptySplitted.size()]);
    }

    /**
     * Returns if a path is empty
     * 
     * @param path
     * @return
     */
    private static boolean isEmptyPath(String path) {
        return path.equals("") || path.equals("/");
    }

    /**
     * Removes a given prefix from a path, e.g. removePrefix("aaa/b/c", "aaa/") ->
     * "b/c"
     * 
     * @param path
     * @param prefix
     * @return
     */
    private static String removePrefix(String path, String prefix) {
        // Return null result for null argument
        if (path == null) {
            return null;
        }

        if (isEmptyPath(path)) {
            // same result as for any other "empty" path, like "" and "/"
            return "";
        }
        if (path.startsWith(prefix)) {
            return path.substring(prefix.length());
        } else {
            return path;
        }
    }
}
