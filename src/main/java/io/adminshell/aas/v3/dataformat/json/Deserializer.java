package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public interface Deserializer {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public AssetAdministrationShellEnvironment read(String value) throws DeserializationException;

    public default AssetAdministrationShellEnvironment read(InputStream src) throws DeserializationException {
        return read(src, DEFAULT_CHARSET);
    }

    public default AssetAdministrationShellEnvironment read(InputStream src, Charset charset) throws DeserializationException {
        return read(new BufferedReader(
                new InputStreamReader(src, charset))
                .lines()
                .collect(Collectors.joining(System.lineSeparator())));
    }

    public default AssetAdministrationShellEnvironment read(File file, Charset charset) throws FileNotFoundException, DeserializationException {
        return read(new FileInputStream(file), charset);
    }

    public default AssetAdministrationShellEnvironment read(File file) throws FileNotFoundException, DeserializationException {
        return read(file, DEFAULT_CHARSET);
    }

    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation);
}
