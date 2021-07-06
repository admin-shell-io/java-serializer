package io.adminshell.aas.v3.dataformat.aasx.serialization;

import java.util.Arrays;

import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultAdministrativeInformation;
import io.adminshell.aas.v3.model.impl.DefaultAsset;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import io.adminshell.aas.v3.model.impl.DefaultConceptDescription;
import io.adminshell.aas.v3.model.impl.DefaultDataSpecificationIEC61360;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;
import io.adminshell.aas.v3.model.impl.DefaultFile;
import io.adminshell.aas.v3.model.impl.DefaultIdentifier;
import io.adminshell.aas.v3.model.impl.DefaultIdentifierKeyValuePair;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;
import io.adminshell.aas.v3.model.impl.DefaultSubmodelElementCollection;

public class AASSimple {

    public static final java.io.File FILE = new java.io.File("src/test/resources/xmlExample.xml");

    // AAS
    public static final String AAS_ID = "ExampleMotor";
    public static final String AAS_IDENTIFIER = "http://customer.com/aas/9175_7013_7091_9168";

    // SUBMODEL_TECHNICAL_DATA
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_ID_SHORT = "MaxRotationSpeed";
    public static final String SUBMODEL_TECHNICAL_DATA_ID_SHORT = "TechnicalData";
    public static final String SUBMODEL_TECHNICAL_DATA_ID = "http://i40.customer.com/type/1/1/7A7104BDAB57E184";
    public static final String SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID = "0173-1#01-AFZ615#016";
    public static final String SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID_PROPERTY = "0173-1#02-BAA120#008";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_CATEGORY = "Parameter";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUE = "5000";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUETYPE = "integer";

    // SUBMODEL_DOCUMENTATION
    private static final String SUBMODEL_DOCUMENTATION_ID_SHORT = "Documentation";
    private static final String SUBMODEL_DOCUMENTATION_ID = "http://i40.customer.com/type/1/1/1A7B62B529F19152";
    private static final String SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_SEMANTIC_ID = "www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Document";
    private static final String SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_ID_SHORT = "OperatingManual";
    private static final String SUBMODEL_DOCUMENTATION_PROPERTY_SEMANTIC_ID = "www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Description/Title";
    private static final String SUBMODEL_DOCUMENTATION_PROPERTY_ID_SHORT = "Title";
    private static final String SUBMODEL_DOCUMENTATION_PROPERTY_VALUE = "OperatingManual";
    private static final String SUBMODEL_DOCUMENTATION_PROPERTY_VALUETYPE = "langString";
    private static final String SUBMODEL_DOCUMENTATION_FILE_SEMANTIC_ID = "www.vdi2770.com/blatt1/Entwurf/Okt18/cd/StoredDocumentRepresentation/DigitalFile";
    private static final String SUBMODEL_DOCUMENTATION_FILE_ID_SHORT = "DigitalFile_PDF";
    private static final String SUBMODEL_DOCUMENTATION_FILE_MIMETYPE = "application/pdf";
    private static final String SUBMODEL_DOCUMENTATION_FILE_VALUE = "/aasx/OperatingManual.pdf";

    // SUBMODEL_OPERATIONAL_DATA
    private static final String SUBMODEL_OPERATIONAL_DATA_ID_SHORT = "OperationalData";
    private static final String SUBMODEL_OPERATIONAL_DATA_ID = "http://i40.customer.com/instance/1/1/AC69B1CB44F07935";
    private static final String SUBMODEL_OPERATIONAL_DATA_SEMANTIC_ID_PROPERTY = "http://customer.com/cd/1/1/18EBD56F6B43D895";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_ID_SHORT = "RotationSpeed";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_CATEGORY = "Variable";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUE = "4370";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUETYPE = "integer";

    public AASSimple() {
    }

    public static final AssetAdministrationShell AAS = new DefaultAssetAdministrationShell.Builder().idShort(AAS_ID).identification(new DefaultIdentifier.Builder().idType(IdentifierType.IRI).identifier(AAS_IDENTIFIER).build())
            .assetInformation(new DefaultAssetInformation.Builder().assetKind(AssetKind.INSTANCE)
                    .globalAssetId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.ASSET).value("http://customer.com/assets/KHBVZJSQKIY").idType(KeyType.IRI).build()).build())
                    .specificAssetId(new DefaultIdentifierKeyValuePair.Builder().key("EquipmentID").value("538fd1b3-f99f-4a52-9c75-72e9fa921270")
                            .externalSubjectId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.GLOBAL_REFERENCE).value("http://customer.com/Systems/ERP/012").idType(KeyType.IRI).build()).build()).build())
                    .specificAssetId(new DefaultIdentifierKeyValuePair.Builder().key("DeviceID").value("QjYgPggjwkiHk4RrQiYSLg==")
                            .externalSubjectId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.GLOBAL_REFERENCE).value("http://customer.com/Systems/IoT/1").idType(KeyType.IRI).build()).build()).build())
                    .defaultThumbnail(new DefaultFile.Builder().kind(ModelingKind.INSTANCE).idShort("thumbnail").mimeType("image/png").value("https://github.com/admin-shell/io/blob/master/verwaltungsschale-detail-part1.png").build())
                    .build())
            .submodel(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.SUBMODEL).value("http.//i40.customer.com/type/1/1/7A7104BDAB57E184").idType(KeyType.IRI).build()).build())
            .submodel(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.SUBMODEL).value("http://i40.customer.com/instance/1/1/AC69B1CB44F07935").idType(KeyType.IRI).build()).build())
            .submodel(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.SUBMODEL).value("http://i40.customer.com/type/1/1/1A7B62B529F19152").idType(KeyType.IRI).build()).build()).build();

    public static final Asset ASSET = new DefaultAsset.Builder().idShort("ServoDCMotor").identification(new DefaultIdentifier.Builder().idType(IdentifierType.IRI).identifier("http://customer.com/assets/KHBVZJSQKIY").build()).build();

    public static final Submodel SUBMODEL_TECHNICAL_DATA = new DefaultSubmodel.Builder()
            .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.GLOBAL_REFERENCE).value(SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID).idType(KeyType.IRDI).build()).build()).kind(ModelingKind.INSTANCE)
            .idShort(SUBMODEL_TECHNICAL_DATA_ID_SHORT).identification(new DefaultIdentifier.Builder().identifier(SUBMODEL_TECHNICAL_DATA_ID).idType(IdentifierType.IRI).build())
            .submodelElement(new DefaultProperty.Builder().kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.CONCEPT_DESCRIPTION).value(SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID_PROPERTY).idType(KeyType.IRDI).build()).build())
                    .idShort(SUBMODEL_TECHNICAL_DATA_PROPERTY_ID_SHORT).category(SUBMODEL_TECHNICAL_DATA_PROPERTY_CATEGORY).value(SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUE).valueType(SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUETYPE).build())
            .build();

    public static final Submodel SUBMODEL_OPERATIONAL_DATA = new DefaultSubmodel.Builder().kind(ModelingKind.INSTANCE).idShort(SUBMODEL_OPERATIONAL_DATA_ID_SHORT)
            .identification(new DefaultIdentifier.Builder().identifier(SUBMODEL_OPERATIONAL_DATA_ID).idType(IdentifierType.IRI).build())
            .submodelElement(new DefaultProperty.Builder().kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.CONCEPT_DESCRIPTION).value(SUBMODEL_OPERATIONAL_DATA_SEMANTIC_ID_PROPERTY).idType(KeyType.IRI).build()).build())
                    .idShort(SUBMODEL_OPERATIONAL_DATA_PROPERTY_ID_SHORT).category(SUBMODEL_OPERATIONAL_DATA_PROPERTY_CATEGORY).value(SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUE).valueType(SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUETYPE).build())
            .build();

    public static final Submodel SUBMODEL_DOCUMENTATION = new DefaultSubmodel.Builder().kind(ModelingKind.INSTANCE).idShort(SUBMODEL_DOCUMENTATION_ID_SHORT)
            .identification(new DefaultIdentifier.Builder().identifier(SUBMODEL_DOCUMENTATION_ID).idType(IdentifierType.IRI).build())
            .submodelElement(new DefaultSubmodelElementCollection.Builder().kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.CONCEPT_DESCRIPTION).value(SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_SEMANTIC_ID).idType(KeyType.IRI).build()).build())
                    .idShort(SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_ID_SHORT)
                    .value(new DefaultProperty.Builder().kind(ModelingKind.INSTANCE)
                            .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.CONCEPT_DESCRIPTION).value(SUBMODEL_DOCUMENTATION_PROPERTY_SEMANTIC_ID).idType(KeyType.IRI).build()).build())
                            .idShort(SUBMODEL_DOCUMENTATION_PROPERTY_ID_SHORT).value(SUBMODEL_DOCUMENTATION_PROPERTY_VALUE).valueType(SUBMODEL_DOCUMENTATION_PROPERTY_VALUETYPE).build())
                    .value(new DefaultFile.Builder().kind(ModelingKind.INSTANCE)
                            .semanticId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.CONCEPT_DESCRIPTION).value(SUBMODEL_DOCUMENTATION_FILE_SEMANTIC_ID).idType(KeyType.IRI).build()).build())
                            .idShort(SUBMODEL_DOCUMENTATION_FILE_ID_SHORT).mimeType(SUBMODEL_DOCUMENTATION_FILE_MIMETYPE).value(SUBMODEL_DOCUMENTATION_FILE_VALUE).build())
                    .ordered(false).allowDuplicates(false).build())
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_TITLE = new DefaultConceptDescription.Builder().idShort("Title")
            .identification(new DefaultIdentifier.Builder().identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Description/Title").idType(IdentifierType.IRI).build())
            .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder().preferredName(new LangString("Title", "EN")).preferredName(new LangString("Titel", "DE")).shortName(new LangString("Title", "EN"))
                            .shortName(new LangString("Titel", "DE")).dataType(DataTypeIEC61360.STRING_TRANSLATABLE).definition(new LangString("SprachabhaengigerTiteldesDokuments.", "DE")).build())
                    .build())
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_DIGITALFILE = new DefaultConceptDescription.Builder().idShort("DigitalFile")
            .identification(new DefaultIdentifier.Builder().identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/StoredDocumentRepresentation/DigitalFile").idType(IdentifierType.IRI).build())
            .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder().preferredName(new LangString("DigitalFile", "EN"))
                    .preferredName(new LangString("DigitaleDatei", "DE")).shortName(new LangString("DigitalFile", "EN")).shortName(new LangString("DigitaleDatei", "DE")).dataType(DataTypeIEC61360.STRING)
                    .definition(new LangString("Eine Datei, die die Document Version repraesentiert. Neben der obligatorischen PDF Datei koennen weitere Dateien angegeben werden.", "DE")).build()).build())
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_MAXROTATIONSPEED = new DefaultConceptDescription.Builder().idShort("MaxRotationSpeed").category("Property")
            .administration(new DefaultAdministrativeInformation.Builder().version("1").revision("2").build()).identification(new DefaultIdentifier.Builder().identifier("0173-1#02-BAA120#008").idType(IdentifierType.IRDI).build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultEmbeddedDataSpecification.Builder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder().preferredName(new LangString("max.Drehzahl", "de")).preferredName(new LangString("Max.rotationspeed", "en")).unit("1/min")
                            .unitId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.GLOBAL_REFERENCE).value("0173-1#05-AAA650#002").idType(KeyType.IRDI).build()).build()).dataType(DataTypeIEC61360.REAL_MEASURE)
                            .definition(new LangString("HoechstezulaessigeDrehzahl,mitwelcherderMotoroderdieSpeiseinheitbetriebenwerdendarf", "de"))
                            .definition(new LangString("Greatestpermissiblerotationspeedwithwhichthemotororfeedingunitmaybeoperated", "en")).build())
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_ROTATIONSPEED = new DefaultConceptDescription.Builder().idShort("RotationSpeed").category("Property")
            .identification(new DefaultIdentifier.Builder().identifier("http://customer.com/cd/1/1/18EBD56F6B43D895").idType(IdentifierType.IRI).build())
            .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder().preferredName(new LangString("AktuelleDrehzahl", "DE"))
                    .preferredName(new LangString("Actualrotationspeed", "EN")).shortName(new LangString("AktuelleDrehzahl", "DE")).shortName(new LangString("ActualRotationSpeed", "EN")).unit("1/min")
                    .unitId(new DefaultReference.Builder().key(new DefaultKey.Builder().type(KeyElements.GLOBAL_REFERENCE).value("0173-1#05-AAA650#002").idType(KeyType.IRDI).build()).build()).dataType(DataTypeIEC61360.REAL_MEASURE)
                    .definition(new LangString("Aktuelle Drehzahl, mitwelcher der Motor oder die Speiseinheit betrieben wird", "DE")).definition(new LangString("Actual rotationspeed with which the motor or feedingunit is operated", "EN"))
                    .build()).build())
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_DOCUMENT = new DefaultConceptDescription.Builder().idShort("Document")
            .identification(new DefaultIdentifier.Builder().identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Document").idType(IdentifierType.IRI).build())
            .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder().shortName(new LangString("Document", "EN"))
                    .shortName(new LangString("Dokument", "DE")).sourceOfDefinition("[ISO15519-1:2010]").dataType(DataTypeIEC61360.STRING)
                    .definition(new LangString("Feste und geordnete Menge von fuer die Verwendung durch Personen bestimmte Informationen, die verwaltet und als Einheit zwischen Benutzern und System ausgetauscht werden kann.", "DE")).build())
                    .build())
            .build();

    public static final AssetAdministrationShellEnvironment ENVIRONMENT = new DefaultAssetAdministrationShellEnvironment.Builder().assetAdministrationShells(AAS).submodels(SUBMODEL_TECHNICAL_DATA).submodels(SUBMODEL_DOCUMENTATION)
            .submodels(SUBMODEL_OPERATIONAL_DATA).conceptDescriptions(CONCEPT_DESCRIPTION_TITLE).conceptDescriptions(CONCEPT_DESCRIPTION_DIGITALFILE).conceptDescriptions(CONCEPT_DESCRIPTION_MAXROTATIONSPEED)
            .conceptDescriptions(CONCEPT_DESCRIPTION_ROTATIONSPEED).conceptDescriptions(CONCEPT_DESCRIPTION_DOCUMENT).build();

}
