package de.fraunhofer.iais.eis.json.serializer;

import de.fraunhofer.iais.eis.DefaultProperty;
import de.fraunhofer.iais.eis.util.TypedLiteral;

public class DemoStringProperty extends DefaultProperty {
	public DemoStringProperty() {
		this.idShort = "DummyProp";
		this.value = new TypedLiteral("test");
		this.valueType = "String";
	}
}
