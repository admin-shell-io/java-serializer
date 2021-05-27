package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.DefaultAssetAdministrationShellBuilder;
import de.fraunhofer.iais.eis.DefaultAssetAdministrationShellEnvironmentBuilder;
import de.fraunhofer.iais.eis.DefaultIdentifierBuilder;
import de.fraunhofer.iais.eis.DefaultKeyBuilder;
import de.fraunhofer.iais.eis.DefaultPropertyBuilder;
import de.fraunhofer.iais.eis.DefaultReferenceBuilder;
import de.fraunhofer.iais.eis.DefaultSubmodelBuilder;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.KeyType;
import de.fraunhofer.iais.eis.Submodel;
import java.util.Arrays;

public class DemoAAS {

    public static final String AAS_ID = "MyAasId";
    public static final String PROPERTY_ID_SHORT = "DummyProp";
    public static final String SUBMODEL_ID_SHORT = "MySubmodelName";
    public static final String SUBMODEL_ID = "MySubmodelId";

    private DemoAAS() {
    }
    public static final Submodel SUBMODEL = new DefaultSubmodelBuilder()
            .idShort(SUBMODEL_ID_SHORT)
            .identification(new DefaultIdentifierBuilder()
                    .identifier(SUBMODEL_ID)
                    .idType(IdentifierType.CUSTOM)
                    .build())
            .submodelElements(Arrays.asList(new DefaultPropertyBuilder()
                    .idShort(PROPERTY_ID_SHORT)
                    .value("test")
                    .valueType("String")
                    .build()))
            .build();

    public static final AssetAdministrationShell AAS = new DefaultAssetAdministrationShellBuilder()
            .identification(new DefaultIdentifierBuilder()
                    .identifier(AAS_ID)
                    .idType(IdentifierType.CUSTOM)
                    .build())
            .submodels(Arrays.asList(new DefaultReferenceBuilder()
                    .keys(Arrays
                            .asList(new DefaultKeyBuilder()
                                    .type(KeyElements.SUBMODEL)
                                    .idType(KeyType.CUSTOM)
                                    .value(SUBMODEL_ID).build()))
                    .build()))
            .build();

    public static final AssetAdministrationShellEnvironment ENVIRONMENT = new DefaultAssetAdministrationShellEnvironmentBuilder()
            .assetAdministrationShells(Arrays.asList(AAS))
            .submodels(Arrays.asList(SUBMODEL))
            .build();

}
