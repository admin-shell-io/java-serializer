package de.fraunhofer.iais.eis.json;

import java.io.FileWriter;
import java.io.IOException;

public class JsonAasFileWriter {
	protected JsonAasSerializer serializer = new JsonAasSerializer();

	public void serializeToFile(Object object, String fileName) throws IOException {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			String serializedJson = serializer.serialize(object);
			fileWriter.write(serializedJson);
		}
	}
}
