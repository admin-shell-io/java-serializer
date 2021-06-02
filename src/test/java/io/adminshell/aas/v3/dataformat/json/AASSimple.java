package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.builder.*;
import java.util.Arrays;

import de.fraunhofer.iais.eis.util.LangString;

public class AASSimple {

    public static final java.io.File FILE = new java.io.File("src/test/resources/jsonExample.json");

    //AAS
    public static final String AAS_ID = "ExampleMotor";
    public static final String AAS_IDENTIFIER = "http://customer.com/aas/9175_7013_7091_9168";

    //SUBMODEL_TECHNICAL_DATA
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_ID_SHORT = "MaxRotationSpeed";
    public static final String SUBMODEL_TECHNICAL_DATA_ID_SHORT = "TechnicalData";
    public static final String SUBMODEL_TECHNICAL_DATA_ID = "http://i40.customer.com/type/1/1/7A7104BDAB57E184";
    public static final String SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID = "0173-1#01-AFZ615#016";
    public static final String SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID_PROPERTY = "0173-1#02-BAA120#008";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_CATEGORY = "PARAMETER";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUE = "5000";
    public static final String SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUETYPE = "integer";

    //SUBMODEL_DOCUMENTATION
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

    //SUBMODEL_OPERATIONAL_DATA
    private static final String SUBMODEL_OPERATIONAL_DATA_ID_SHORT = "OperationalData";
    private static final String SUBMODEL_OPERATIONAL_DATA_ID = "http://i40.customer.com/instance/1/1/AC69B1CB44F07935";
    private static final String SUBMODEL_OPERATIONAL_DATA_SEMANTIC_ID_PROPERTY = "http://customer.com/cd/1/1/18EBD56F6B43D895";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_ID_SHORT = "RotationSpeed";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_CATEGORY = "VARIABLE";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUE = "4370";
    private static final String SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUETYPE = "integer";

    public AASSimple() {
    }

    public static final AssetAdministrationShell AAS = new DefaultAssetAdministrationShellBuilder()
            .idShort(AAS_ID)
            .identification(new DefaultIdentifierBuilder()
                    .idType(IdentifierType.IRI)
                    .identifier(AAS_IDENTIFIER)
                    .build())
            .assetInformation(new DefaultAssetInformationBuilder()
                    .assetKind(AssetKind.INSTANCE)
                    .globalAssetId(new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.ASSET)
                                    .value("http://customer.com/assets/KHBVZJSQKIY")
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build())
                    .specificAssetIds(Arrays.asList(
                            new DefaultIdentifierKeyValuePairBuilder()
                                    .key("EquipmentID")
                                    .value("538fd1b3-f99f-4a52-9c75-72e9fa921270")
                                    .externalSubjectId(new DefaultReferenceBuilder()
                                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                                    .type(KeyElements.GLOBAL_REFERENCE)
                                                    .value("http://customer.com/Systems/ERP/012")
                                                    .idType(KeyType.IRI)
                                                    .build()))
                                            .build())
                                    .build(),
                            new DefaultIdentifierKeyValuePairBuilder()
                                    .key("DeviceID")
                                    .value("QjYgPggjwkiHk4RrQiYSLg==")
                                    .externalSubjectId(new DefaultReferenceBuilder()
                                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                                    .type(KeyElements.GLOBAL_REFERENCE)
                                                    .value("http://customer.com/Systems/IoT/1")
                                                    .idType(KeyType.IRI)
                                                    .build()))
                                            .build())
                                    .build()
                    ))
                    .defaultThumbnail(new DefaultFileBuilder()
                            .kind(ModelingKind.INSTANCE)
                            .idShort("thumbnail")
                            .mimeType("image/png")
                            .value("https://github.com/admin-shell/io/blob/master/verwaltungsschale-detail-part1.png")
                            .build())
                    .build()
            )
            .submodels(Arrays.asList(
                    new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.SUBMODEL)
                                    .value("http.//i40.customer.com/type/1/1/7A7104BDAB57E184")
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build(),
                    new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.SUBMODEL)
                                    .value("http://i40.customer.com/instance/1/1/AC69B1CB44F07935")
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build(),
                    new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.SUBMODEL)
                                    .value("http://i40.customer.com/type/1/1/1A7B62B529F19152")
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build()))
            .build();

    public static final Asset ASSET = new DefaultAssetBuilder()
            .idShort("ServoDCMotor")
            .identification(new DefaultIdentifierBuilder()
                    .idType(IdentifierType.IRI)
                    .identifier("http://customer.com/assets/KHBVZJSQKIY")
                    .build())
            .build();

    public static final Submodel SUBMODEL_TECHNICAL_DATA = new DefaultSubmodelBuilder()
            .semanticId(new DefaultReferenceBuilder()
                    .keys(Arrays.asList(new DefaultKeyBuilder()
                            .type(KeyElements.GLOBAL_REFERENCE)
                            .value(SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID)
                            .idType(KeyType.IRDI)
                            .build()))
                    .build())
            .kind(ModelingKind.INSTANCE)
            .idShort(SUBMODEL_TECHNICAL_DATA_ID_SHORT)
            .identification(new DefaultIdentifierBuilder()
                    .identifier(SUBMODEL_TECHNICAL_DATA_ID)
                    .idType(IdentifierType.IRI)
                    .build())
            .submodelElements(Arrays.asList(new DefaultPropertyBuilder()
                    .kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.CONCEPT_DESCRIPTION)
                                    .value(SUBMODEL_TECHNICAL_DATA_SEMANTIC_ID_PROPERTY)
                                    .idType(KeyType.IRDI)
                                    .build()))
                            .build())
                    .idShort(SUBMODEL_TECHNICAL_DATA_PROPERTY_ID_SHORT)
                    .category(SUBMODEL_TECHNICAL_DATA_PROPERTY_CATEGORY)
                    .value(SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUE)
                    .valueType(SUBMODEL_TECHNICAL_DATA_PROPERTY_VALUETYPE)
                    .build()))
            .build();

    public static final Submodel SUBMODEL_OPERATIONAL_DATA = new DefaultSubmodelBuilder()
            .kind(ModelingKind.INSTANCE)
            .idShort(SUBMODEL_OPERATIONAL_DATA_ID_SHORT)
            .identification(new DefaultIdentifierBuilder()
                    .identifier(SUBMODEL_OPERATIONAL_DATA_ID)
                    .idType(IdentifierType.IRI)
                    .build())
            .submodelElements(Arrays.asList(new DefaultPropertyBuilder()
                    .kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.CONCEPT_DESCRIPTION)
                                    .value(SUBMODEL_OPERATIONAL_DATA_SEMANTIC_ID_PROPERTY)
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build())
                    .idShort(SUBMODEL_OPERATIONAL_DATA_PROPERTY_ID_SHORT)
                    .category(SUBMODEL_OPERATIONAL_DATA_PROPERTY_CATEGORY)
                    .value(SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUE)
                    .valueType(SUBMODEL_OPERATIONAL_DATA_PROPERTY_VALUETYPE)
                    .build()))
            .build();

    public static final Submodel SUBMODEL_DOCUMENTATION = new DefaultSubmodelBuilder()
            .kind(ModelingKind.INSTANCE)
            .idShort(SUBMODEL_DOCUMENTATION_ID_SHORT)
            .identification(new DefaultIdentifierBuilder()
                    .identifier(SUBMODEL_DOCUMENTATION_ID)
                    .idType(IdentifierType.IRI)
                    .build())
            .submodelElements(Arrays.asList(new DefaultSubmodelElementCollectionBuilder()
                    .kind(ModelingKind.INSTANCE)
                    .semanticId(new DefaultReferenceBuilder()
                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                    .type(KeyElements.CONCEPT_DESCRIPTION)
                                    .value(SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_SEMANTIC_ID)
                                    .idType(KeyType.IRI)
                                    .build()))
                            .build())
                    .idShort(SUBMODEL_DOCUMENTATION_ELEMENTCOLLECTION_ID_SHORT)
                    .values(Arrays.asList(
                            new DefaultPropertyBuilder()
                                    .kind(ModelingKind.INSTANCE)
                                    .semanticId(new DefaultReferenceBuilder()
                                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                                    .type(KeyElements.CONCEPT_DESCRIPTION)
                                                    .value(SUBMODEL_DOCUMENTATION_PROPERTY_SEMANTIC_ID)
                                                    .idType(KeyType.IRI)
                                                    .build()))
                                            .build())
                                    .idShort(SUBMODEL_DOCUMENTATION_PROPERTY_ID_SHORT)
                                    .value(SUBMODEL_DOCUMENTATION_PROPERTY_VALUE)
                                    .valueType(SUBMODEL_DOCUMENTATION_PROPERTY_VALUETYPE)
                                    .build(),
                            new DefaultFileBuilder()
                                    .kind(ModelingKind.INSTANCE)
                                    .semanticId(new DefaultReferenceBuilder()
                                            .keys(Arrays.asList(new DefaultKeyBuilder()
                                                    .type(KeyElements.CONCEPT_DESCRIPTION)
                                                    .value(SUBMODEL_DOCUMENTATION_FILE_SEMANTIC_ID)
                                                    .idType(KeyType.IRI)
                                                    .build()))
                                            .build())
                                    .idShort(SUBMODEL_DOCUMENTATION_FILE_ID_SHORT)
                                    .mimeType(SUBMODEL_DOCUMENTATION_FILE_MIMETYPE)
                                    .value(SUBMODEL_DOCUMENTATION_FILE_VALUE)
                                    .build()))
                    .ordered(false)
                    .allowDuplicates(false)
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_TITLE = new DefaultConceptDescriptionBuilder()
            .idShort("Title")
            .identification(new DefaultIdentifierBuilder()
                    .identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Description/Title")
                    .idType(IdentifierType.IRI)
                    .build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultDataSpecificationBuilder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360Builder()
                            .preferredNames(Arrays.asList(
                                    new LangString("Title", "EN"),
                                    new LangString("Titel", "DE")))
                            .shortNames(Arrays.asList(
                                    new LangString("Title", "EN"),
                                    new LangString("Titel", "DE")))
                            .unit("")
                            .sourceOfDefinition("")
                            .dataType(DataTypeIEC61360.STRING_TRANSLATABLE)
                            .definitions(Arrays.asList(
                                    new LangString("SprachabhängigerTiteldesDokuments.", "DE")))
                            .build())
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_DIGITALFILE = new DefaultConceptDescriptionBuilder()
            .idShort("DigitalFile")
            .identification(new DefaultIdentifierBuilder()
                    .identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/StoredDocumentRepresentation/DigitalFile")
                    .idType(IdentifierType.IRI)
                    .build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultDataSpecificationBuilder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360Builder()
                            .preferredNames(Arrays.asList(
                                    new LangString("DigitalFile", "EN"),
                                    new LangString("DigitaleDatei", "DE")))
                            .shortNames(Arrays.asList(
                                    new LangString("DigitalFile", "EN"),
                                    new LangString("DigitaleDatei", "DE")))
                            .unit("")
                            .sourceOfDefinition("")
                            .dataType(DataTypeIEC61360.STRING)
                            .definitions(Arrays.asList(
                                    new LangString("Eine Datei, die die Document Version repräsentiert. Neben der obligatorischen PDF Datei können weitere Dateien angegeben werden.", "DE")))
                            .build())
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_MAXROTATIONSPEED = new DefaultConceptDescriptionBuilder()
            .idShort("MaxRotationSpeed")
            .category("PROPERTY")
            .administration(new DefaultAdministrativeInformationBuilder()
                    .version("")
                    .revision("2")
                    .build())
            .identification(new DefaultIdentifierBuilder()
                    .identifier("0173-1#02-BAA120#008")
                    .idType(IdentifierType.IRDI)
                    .build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultDataSpecificationBuilder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360Builder()
                            .preferredNames(Arrays.asList(
                                    new LangString("max.Drehzahl", "de"),
                                    new LangString("Max.rotationspeed", "en")))
                            .unit("1/min")
                            .unitId(new DefaultReferenceBuilder()
                                    .keys(Arrays.asList(new DefaultKeyBuilder()
                                            .type(KeyElements.GLOBAL_REFERENCE)
                                            .value("0173-1#05-AAA650#002")
                                            .idType(KeyType.IRDI)
                                            .build()))
                                    .build())
                            .sourceOfDefinition("")
                            .dataType(DataTypeIEC61360.REAL_MEASURE)
                            .definitions(Arrays.asList(
                                    new LangString("HöchstezulässigeDrehzahl,mitwelcherderMotoroderdieSpeiseinheitbetriebenwerdendarf", "de"),
                                    new LangString("Greatestpermissiblerotationspeedwithwhichthemotororfeedingunitmaybeoperated", "en")))
                            .build())
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_ROTATIONSPEED = new DefaultConceptDescriptionBuilder()
            .idShort("RotationSpeed")
            .category("PROPERTY")
            .identification(new DefaultIdentifierBuilder()
                    .identifier("http://customer.com/cd/1/1/18EBD56F6B43D895")
                    .idType(IdentifierType.IRI)
                    .build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultDataSpecificationBuilder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360Builder()
                            .preferredNames(Arrays.asList(
                                    new LangString("AktuelleDrehzahl", "DE"),
                                    new LangString("Actualrotationspeed", "EN")))
                            .shortNames(Arrays.asList(
                                    new LangString("AktuelleDrehzahl", "DE"),
                                    new LangString("ActualRotationSpeed", "EN")
                            ))
                            .unit("1/min")
                            .unitId(new DefaultReferenceBuilder()
                                    .keys(Arrays.asList(new DefaultKeyBuilder()
                                            .type(KeyElements.GLOBAL_REFERENCE)
                                            .value("0173-1#05-AAA650#002")
                                            .idType(KeyType.IRDI)
                                            .build()))
                                    .build())
                            .sourceOfDefinition("")
                            .dataType(DataTypeIEC61360.REAL_MEASURE)
                            .definitions(Arrays.asList(
                                    new LangString("Aktuelle Drehzahl, mitwelcher der Motor oder die Speiseinheit betrieben wird", "DE"),
                                    new LangString("Actual rotationspeed with which the motor or feedingunit is operated", "EN")))
                            .build())
                    .build()))
            .build();

    public static final ConceptDescription CONCEPT_DESCRIPTION_DOCUMENT = new DefaultConceptDescriptionBuilder()
            .idShort("Document")
            .identification(new DefaultIdentifierBuilder()
                    .identifier("www.vdi2770.com/blatt1/Entwurf/Okt18/cd/Document")
                    .idType(IdentifierType.IRI)
                    .build())
            .embeddedDataSpecifications(Arrays.asList(new DefaultDataSpecificationBuilder()
                    .dataSpecificationContent(new DefaultDataSpecificationIEC61360Builder()
                            .shortNames(Arrays.asList(
                                    new LangString("Document", "EN"),
                                    new LangString("Dokument", "DE")))
                            .unit("")
                            .sourceOfDefinition("[ISO15519-1:2010]")
                            .dataType(DataTypeIEC61360.STRING)
                            .definitions(Arrays.asList(
                                    new LangString("Feste und geordnete Menge von für die Verwendung durch Personen bestimmte Informationen, die verwaltet und als Einheit zwischen Benutzern und System ausgetauscht werden kann.", "DE")))
                            .build())
                    .build()))
            .build();

    public static final AssetAdministrationShellEnvironment ENVIRONMENT = new DefaultAssetAdministrationShellEnvironmentBuilder()
            .assetAdministrationShells(Arrays.asList(AAS))
            .submodels(Arrays.asList(SUBMODEL_TECHNICAL_DATA, SUBMODEL_DOCUMENTATION, SUBMODEL_OPERATIONAL_DATA))
            .conceptDescriptions(Arrays.asList(CONCEPT_DESCRIPTION_TITLE, CONCEPT_DESCRIPTION_DIGITALFILE, CONCEPT_DESCRIPTION_MAXROTATIONSPEED, CONCEPT_DESCRIPTION_ROTATIONSPEED, CONCEPT_DESCRIPTION_DOCUMENT))
            .assets(Arrays.asList(ASSET))
            .build();

}
