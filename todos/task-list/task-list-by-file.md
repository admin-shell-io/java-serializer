## aml/deserialization/mappers/EmbeddedDataSpecificationCollectionMapper.java

[Line 46](
https://github.com/admin-shell-io/java-serializer/blob/74b9a5de98e3dcb219180c29a39a9f2d607df7be/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/EmbeddedDataSpecificationCollectionMapper.java#L46
),
Jens Mueller

    use TypeFactory instead of hardcoded DefaultEmbeddedDataSpecification.class

[Line 49](https://github.com/admin-shell-io/java-serializer/blob/74b9a5de98e3dcb219180c29a39a9f2d607df7be/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/EmbeddedDataSpecificationCollectionMapper.java#L49),
Jens Mueller

    get dataSpecificationClass from DataSpecificationManager

## dataformat/rdf/SerializerTest.java

[Line 32](https://github.com/admin-shell-io/java-serializer/blob/b529d2a684386544047743ae6a221fdd3c1ff82e/dataformat-rdf/src/test/java/io/adminshell/aas/v3/dataformat/rdf/SerializerTest.java#L32),
Sebastian Bader

    Optional: Prefixes instead of full URIs

[Line 33](https://github.com/admin-shell-io/java-serializer/blob/b529d2a684386544047743ae6a221fdd3c1ff82e/dataformat-rdf/src/test/java/io/adminshell/aas/v3/dataformat/rdf/SerializerTest.java#L33),
Sebastian Bader

    Optional: Do not serialize empty collections

## model/validator/TestAASd_026.java

[Line 27](https://github.com/admin-shell-io/java-serializer/blob/d3996a65b4d6401dc2c1ce76cf9708a93fd14f12/validator/src/test/java/io/adminshell/aas/v3/model/validator/TestAASd_026.java#L27),
Chang Qin

    please write the test

## dataformat/i4aas/mappers/sme/CapabilityMapper.java

[Line 28](https://github.com/admin-shell-io/java-serializer/blob/a11b41574fcd280952803200dfbd1c91a2d7ba5d/dataformat-uanodeset/src/main/java/io/adminshell/aas/v3/dataformat/i4aas/mappers/sme/CapabilityMapper.java#L28),
br-iosb

    Auto-generated constructor stub

## dataformat/i4aas/DeserializerTest.java

[Line 39](https://github.com/admin-shell-io/java-serializer/blob/fcef0f9f379d7711365a5f26ccdeec18dc938d47/dataformat-uanodeset/src/test/java/io/adminshell/aas/v3/dataformat/i4aas/DeserializerTest.java#L39),
Arno Weiss

    assert

## model/validator/TestAASd_003.java

[Line 50](https://github.com/admin-shell-io/java-serializer/blob/d3996a65b4d6401dc2c1ce76cf9708a93fd14f12/validator/src/test/java/io/adminshell/aas/v3/model/validator/TestAASd_003.java#L50),
Sebastian Bader

    should be true but requires adjustments in the Java Model

## dataformat/aml/deserialization/mappers/ConstraintCollectionMapper.java

[Line 38](https://github.com/admin-shell-io/java-serializer/blob/74b9a5de98e3dcb219180c29a39a9f2d607df7be/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/ConstraintCollectionMapper.java#L38),
Jens Mueller

    Need to register specific type Qualifiable

## dataformat/aml/deserialization/mappers/QualifierMapper.java

[Line 59](https://github.com/admin-shell-io/java-serializer/blob/74b9a5de98e3dcb219180c29a39a9f2d607df7be/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/QualifierMapper.java#L59),
Jens Mueller

    use Type Factory instead of hardcoded DefaultQualifier.class

## dataformat/i4aas/mappers/HasDataSpecificationMapper.java

[Line 42-43](https://github.com/admin-shell-io/java-serializer/blob/fcef0f9f379d7711365a5f26ccdeec18dc938d47/dataformat-uanodeset/src/main/java/io/adminshell/aas/v3/dataformat/i4aas/mappers/HasDataSpecificationMapper.java#L42-L43),
Arno Weiss

    The embedding is not uniquely bound to the reference. Naming convention
    fixes this partially, but a wrapper object should be specified in I4AAS.

## model/validator/TestAASd_077.java

[Line 44](https://github.com/admin-shell-io/java-serializer/blob/d3996a65b4d6401dc2c1ce76cf9708a93fd14f12/validator/src/test/java/io/adminshell/aas/v3/model/validator/TestAASd_077.java#L44),
Sebastian Bader

    Add HasExtensions to Referables in the Java Model and then uncomment the lines in the tests.

## model/validator/TestAASd_020.java
[Line 60](https://github.com/admin-shell-io/java-serializer/blob/d3996a65b4d6401dc2c1ce76cf9708a93fd14f12/validator/src/test/java/io/adminshell/aas/v3/model/validator/TestAASd_020.java#L60),
Sebastian Bader

    non-strings can not be passed as Qualifier.value --> Strings will always pass, others are not recognized.

## dataformat/aml/deserialization/mappers/ConceptDescriptionMapper.java
[Line 54](https://github.com/admin-shell-io/java-serializer/blob/74b9a5de98e3dcb219180c29a39a9f2d607df7be/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/ConceptDescriptionMapper.java#L54),
Jens Mueller

    How to handle multiple references in isCaseOf?

## dataformat/i4aas/parsers/DataSpecificationsParser.java
[Line 49](https://github.com/admin-shell-io/java-serializer/blob/fcef0f9f379d7711365a5f26ccdeec18dc938d47/dataformat-uanodeset/src/main/java/io/adminshell/aas/v3/dataformat/i4aas/parsers/DataSpecificationsParser.java#L49),
br-iosb

    split("_") used for naming convention to fix I4AAS uniqueness issues

## dataformat/i4aas/mappers/LangStringPropertyMapper.java
[Line 62](https://github.com/admin-shell-io/java-serializer/blob/fcef0f9f379d7711365a5f26ccdeec18dc938d47/dataformat-uanodeset/src/main/java/io/adminshell/aas/v3/dataformat/i4aas/mappers/LangStringPropertyMapper.java#L62),
Arno Weiss

    Auto-generated method stub

## model/validator/TestAASd_015.java
[Line 71](https://github.com/admin-shell-io/java-serializer/blob/d3996a65b4d6401dc2c1ce76cf9708a93fd14f12/validator/src/test/java/io/adminshell/aas/v3/model/validator/TestAASd_015.java#L71),
Sebastian Bader

    I really have no clue what this constraint shall check...

## dataformat/xml/serialization/EmbeddedDataSpecificationSerializer.java
[Line 84](https://github.com/admin-shell-io/java-serializer/blob/9b728f129538135142b6a336e6e6b5233438c689/dataformat-xml/src/main/java/io/adminshell/aas/v3/dataformat/xml/serialization/EmbeddedDataSpecificationSerializer.java#L84),
Michael Jacoby

    Add field name according to template type

## dataformat/aml/serialization/mappers/EmbeddedDataSpecificationCollectionMapper.java
[Line 82-84](https://github.com/admin-shell-io/java-serializer/blob/9b728f129538135142b6a336e6e6b5233438c689/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/serialization/mappers/EmbeddedDataSpecificationCollectionMapper.java#L82-L84),
Michael Jacoby

    should be resolved using DataSpecificationManager but this requires fundamental changes to
    DataSpecificationManager as it currently is based on Reference instead of name
    workaround: go up superclasses/interfaces and find most-specific interface that extends DataSpecificationContent

## dataformat/aml/deserialization/mappers/AssetAdministrationShellEnvironmentMapper.java
[Line 43](https://github.com/admin-shell-io/java-serializer/blob/529d659f9cdfd66e310e3a50906ff42913825b75/dataformat-aml/src/main/java/io/adminshell/aas/v3/dataformat/aml/deserialization/mappers/AssetAdministrationShellEnvironmentMapper.java#L43),
Michael Jacoby

    use typeFactory instead of explicitly using Default... classes

## dataformat/rdf/preprocessing/TypeNamePreprocessor.java
[Line 139](https://github.com/admin-shell-io/java-serializer/blob/40ec29c3e78ca66624d7c87c40d8bc6b34a9093c/dataformat-rdf/src/main/java/io/adminshell/aas/v3/dataformat/rdf/preprocessing/TypeNamePreprocessor.java#L139),
Sebastian Bader

    What happens with an Array inside the Array?

## dataformat/rdf/Parser.java
[Line 496-497](https://github.com/admin-shell-io/java-serializer/blob/c0710df3980a787daa19c9c3410d103053bb383f/dataformat-rdf/src/main/java/io/adminshell/aas/v3/dataformat/rdf/Parser.java#L496-L497),
Sebastian Bader

    Note: This would not yield full results yet in case some of the values are encapsulated
    in blank nodes and some are not, for the same property
