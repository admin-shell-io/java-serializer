package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface Serializer {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws IOException;

    public default void write(OutputStream out, AssetAdministrationShellEnvironment aasEnvironment) throws IOException {
        write(out, DEFAULT_CHARSET, aasEnvironment);
    }

    public default void write(OutputStream out, Charset charset, AssetAdministrationShellEnvironment aasEnvironment) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out, charset)) {
            writer.write(write(aasEnvironment));
        }
    }

    public default void write(File file, Charset charset, AssetAdministrationShellEnvironment aasEnvironment) throws FileNotFoundException, IOException {
        try (OutputStream out = new FileOutputStream(file)) {
            write(out, charset, aasEnvironment);
        }
    }

    public default void write(File file, AssetAdministrationShellEnvironment aasEnvironment) throws FileNotFoundException, IOException {
        write(file, DEFAULT_CHARSET, aasEnvironment);
    }
}
