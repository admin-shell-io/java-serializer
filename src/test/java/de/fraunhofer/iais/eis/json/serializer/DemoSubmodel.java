package de.fraunhofer.iais.eis.json.serializer;

import java.util.Arrays;
import java.util.List;

import de.fraunhofer.iais.eis.DefaultSubmodel;
import de.fraunhofer.iais.eis.Identifier;
import de.fraunhofer.iais.eis.IdentifierBuilder;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.KeyBuilder;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.KeyType;
import de.fraunhofer.iais.eis.Reference;
import de.fraunhofer.iais.eis.ReferenceBuilder;
import de.fraunhofer.iais.eis.SubmodelElement;

public class DemoSubmodel extends DefaultSubmodel {
	private final String ID_SHORT = "MySubmodelName";
	private final String ID = "MySubmodelId";

	public DemoSubmodel() {
		this.idShort = ID_SHORT;
		this.identification = createIdentifier();
		this.submodelElements = createSubmodelElements();
	}

	private Identifier createIdentifier() {
		return new IdentifierBuilder().identifier(ID).idType(IdentifierType.CUSTOM).build();
	}

	private List<SubmodelElement> createSubmodelElements() {
		return Arrays.asList(new DemoStringProperty());
	}

	protected Reference getReference() {
		return new ReferenceBuilder()
				.keys(Arrays
						.asList(new KeyBuilder().type(KeyElements.SUBMODEL).idType(KeyType.CUSTOM).value(ID).build()))
				.build();
	}
}
