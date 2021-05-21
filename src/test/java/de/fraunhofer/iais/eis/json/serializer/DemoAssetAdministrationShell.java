package de.fraunhofer.iais.eis.json.serializer;

import java.util.Arrays;
import java.util.List;

import de.fraunhofer.iais.eis.DefaultAssetAdministrationShell;
import de.fraunhofer.iais.eis.Identifier;
import de.fraunhofer.iais.eis.IdentifierBuilder;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.Reference;

public class DemoAssetAdministrationShell extends DefaultAssetAdministrationShell {

    public DemoAssetAdministrationShell() {
        this.identification = createIdentifier();
        this.submodels = createSubmodelReferences();
    }

    private Identifier createIdentifier() {
        return new IdentifierBuilder().identifier("MyAasId").idType(IdentifierType.CUSTOM).build();
    }

    private List<Reference> createSubmodelReferences() {
        return Arrays.asList(new DemoSubmodel().getReference());
    }
}
