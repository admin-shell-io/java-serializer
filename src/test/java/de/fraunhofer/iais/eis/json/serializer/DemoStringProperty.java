package de.fraunhofer.iais.eis.json.serializer;

import de.fraunhofer.iais.eis.DefaultProperty;

public class DemoStringProperty extends DefaultProperty {

    public DemoStringProperty() {
        this.idShort = "DummyProp";
        this.value = "test";
        this.valueType = "String";
    }
}
