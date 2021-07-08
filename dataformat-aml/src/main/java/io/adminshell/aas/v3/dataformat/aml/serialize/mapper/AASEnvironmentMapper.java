package io.adminshell.aas.v3.dataformat.aml.serialize.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.AdministrativeInformationToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.AllowDuplicatesToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.AssetKindToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.BillOfMaterialToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.CategoryToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.DerivedFromToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.DescriptionToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.EntityTypeToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.IdShortToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.IdShortToNameConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.IdentificationModelToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.IdentificationToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.ModelingKindToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.ObservedToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.OrderedToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.SemanticIdToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter.ValueIdToAttributeConverter;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.AnnotatedRelationshipElementMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.AssetAdministrationShellMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.BasicEventMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.BlobMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.CapabilityMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.EntityMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.FileMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.IdentifiableMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.MultiLanguagePropertyMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.OperationMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.PropertyMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.RangeMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.ReferableMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.ReferenceElementMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.RelationshipElementMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.SubmodelElementCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.SubmodelElementMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.SubmodelMapper;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper.ViewMapper;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


public class AASEnvironmentMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        // Register converter
        factory.getConverterFactory()
                .registerConverter(IdShortToNameConverter.class.getName(), new IdShortToNameConverter());
        factory.getConverterFactory()
                .registerConverter(IdentificationToAttributeConverter.class.getName(), new IdentificationToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(IdShortToAttributeConverter.class.getName(), new IdShortToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(AssetKindToAttributeConverter.class.getName(), new AssetKindToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(CategoryToAttributeConverter.class.getName(), new CategoryToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(IdentificationModelToAttributeConverter.class.getName(), new IdentificationModelToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(AdministrativeInformationToAttributeConverter.class.getName(), new AdministrativeInformationToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(DescriptionToAttributeConverter.class.getName(), new DescriptionToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(DerivedFromToAttributeConverter.class.getName(), new DerivedFromToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(ModelingKindToAttributeConverter.class.getName(), new ModelingKindToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(SemanticIdToAttributeConverter.class.getName(), new SemanticIdToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(ValueIdToAttributeConverter.class.getName(), new ValueIdToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(AllowDuplicatesToAttributeConverter.class.getName(), new AllowDuplicatesToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(OrderedToAttributeConverter.class.getName(), new OrderedToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(BillOfMaterialToAttributeConverter.class.getName(), new BillOfMaterialToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(EntityTypeToAttributeConverter.class.getName(), new EntityTypeToAttributeConverter());
        factory.getConverterFactory()
                .registerConverter(ObservedToAttributeConverter.class.getName(), new ObservedToAttributeConverter());

        // Referable mapping
        factory.classMap(Referable.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("idShort", "name")
                .converter(IdShortToNameConverter.class.getName()).add()
                .fieldMap("idShort", "attributes[0]")
                .converter(IdShortToAttributeConverter.class.getName()).add()
                .fieldMap("category", "attributes[1]")
                .converter(CategoryToAttributeConverter.class.getName()).add()
                .fieldMap("descriptions", "attributes[2]")
                .converter(DescriptionToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new ReferableMapper())
                .register();

        // Identifiable mapping
        factory.classMap(Identifiable.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("identification", "attributes[0]")
                .converter(IdentificationToAttributeConverter.class.getName()).add()
                .fieldMap("administration", "attributes[1]")
                .converter(AdministrativeInformationToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new IdentifiableMapper())
                .register();

        // AssetAdministrationShell mapping
        factory.classMap(AssetAdministrationShell.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("derivedFrom", "attributes[0]")
                .converter(DerivedFromToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new AssetAdministrationShellMapper())
                .register();

        // View mapping
        factory.classMap(View.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("semanticId", "attributes[0]")
                .converter(SemanticIdToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new ViewMapper())
                .register();

//        // Asset information mapping
//        factory.classMap(AssetInformation.class, InternalElement.class)
//                .mapNulls(true)
//                .fieldMap("assetKind", "attributes[0]")
//                .converter(AssetKindToAttributeConverter.class.getName()).add()
//                .byDefault()
//                .customize(new AssetInformationMapper())
//                .register();

        // Deprecated since v3
        // Asset mapping
//       factory.classMap(Asset.class, InternalElement.class)
//                .mapNulls(true)
//                .fieldMap("kind", "attributes[0]")
//                .converter(AssetKindToAttributeConverter.class.getName()).add()
//                .fieldMap("assetIdentificationModel", "attributes[1]")
//                .converter(IdentificationModelToAttributeConverter.class.getName()).add()
//                .fieldMap("billOfMaterial", "attributes[2]")
//                .converter(BillOfMaterialToAttributeConverter.class.getName()).add()
//                .byDefault()
//                .customize(new AssetMapper())
//                .register();

        // Submodel mapping
        factory.classMap(Submodel.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("kind", "attributes[0]")
                .converter(ModelingKindToAttributeConverter.class.getName()).add()
                .fieldMap("semanticId", "attributes[1]")
                .converter(SemanticIdToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new SubmodelMapper())
                .register();

        // SubmodelElement mapping
        factory.classMap(SubmodelElement.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("kind", "attributes[0]")
                .converter(ModelingKindToAttributeConverter.class.getName()).add()
                .fieldMap("semanticId", "attributes[1]")
                .converter(SemanticIdToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new SubmodelElementMapper())
                .register();

        // SubmodelElementCollection mapping
        factory.classMap(SubmodelElementCollection.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("ordered", "attributes[0]")
                .converter(OrderedToAttributeConverter.class.getName()).add()
                .fieldMap("allowDuplicates", "attributes[1]")
                .converter(AllowDuplicatesToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new SubmodelElementCollectionMapper())
                .register();

        // Operation mapping
        factory.classMap(Operation.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new OperationMapper())
                .register();

        // File mapping
        factory.classMap(File.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new FileMapper())
                .register();

        // Capability mapping
        factory.classMap(Capability.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new CapabilityMapper())
                .register();

        // Entity mapping
        factory.classMap(Entity.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("entityType", "attributes[0]")
                .converter(EntityTypeToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new EntityMapper())
                .register();

        // Property mapping
        factory.classMap(Property.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("valueId", "attributes[0]")
                .converter(ValueIdToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new PropertyMapper())
                .register();

        // Event mapping
        factory.classMap(BasicEvent.class, InternalElement.class)
                .mapNulls(true)
                .fieldMap("observed", "attributes[0]")
                .converter(ObservedToAttributeConverter.class.getName()).add()
                .byDefault()
                .customize(new BasicEventMapper())
                .register();

        // Blob mapping
        factory.classMap(Blob.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new BlobMapper())
                .register();

        // Range mapping
        factory.classMap(Range.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new RangeMapper())
                .register();

        // ReferenceElement mapping
        factory.classMap(ReferenceElement.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new ReferenceElementMapper())
                .register();

        // ReferenceElement mapping
        factory.classMap(MultiLanguageProperty.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new MultiLanguagePropertyMapper())
                .register();

        // RelationshipElement mapping
        factory.classMap(RelationshipElement.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new RelationshipElementMapper())
                .register();

        // AnnotatedRelationshipElement mapping
        factory.classMap(AnnotatedRelationshipElement.class, InternalElement.class)
                .mapNulls(true)
                .byDefault()
                .customize(new AnnotatedRelationshipElementMapper())
                .register();

    }
}
