package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {

    protected JsonSerializer serializer = new JsonSerializer();

    public void serializeToFile(AssetAdministrationShellEnvironment aasEnvironment, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            String serializedJson = serializer.serialize(aasEnvironment);
            fileWriter.write(serializedJson);
        }
    }
}
