package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JsonFileReader {

    protected JsonDeserializer deserializer = new JsonDeserializer();

    public AssetAdministrationShellEnvironment deserializeFromFile(String fileName) throws IOException {
        return deserializer.deserialize(Files.readString(new File(fileName).toPath(), StandardCharsets.UTF_8));
    }
}
