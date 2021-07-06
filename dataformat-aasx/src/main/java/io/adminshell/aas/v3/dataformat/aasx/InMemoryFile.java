package io.adminshell.aas.v3.dataformat.aasx;

import java.util.Arrays;

/**
 * Container class for the content of a File and its Path
 *
 */
public class InMemoryFile {

    private byte[] fileContent;
    private String path;

    public InMemoryFile(byte[] fileContent, String path) {
        this.fileContent = fileContent;
        this.path = path;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "InMemoryFile [fileContent=" + Arrays.toString(fileContent) + ", path=" + path + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(fileContent);
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InMemoryFile other = (InMemoryFile) obj;
        if (!Arrays.equals(fileContent, other.fileContent))
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }

}
