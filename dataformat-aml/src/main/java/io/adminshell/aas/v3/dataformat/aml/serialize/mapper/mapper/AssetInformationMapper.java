package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.FileConverterUtil;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.AssetInformation;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.ArrayList;

public class AssetInformationMapper extends CustomMapper<AssetInformation, InternalElement> {

    @Override
    public void mapAtoB(AssetInformation assetInformation, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.AssetInformation.getRefBaseRoleClassPath()));

        // globalAssetId
        internalElement.getAttributes().add(new Attribute(
                "globalAssetId",
                null,
                null,
                ReferenceConverterUtil.convert(assetInformation.getGlobalAssetId()),
                new RefSemantic(AASNamespace.Asset_GlobalAssetId.getRefSemantic()),
                null
        ));

        // specificAssetId
        Attribute attributeSpecificAssetId = new Attribute(
                "specificAssetId",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.Asset_GlobalAssetId.getRefSemantic()),
                null
        );
        attributeSpecificAssetId.setAttributes(new ArrayList<>());
        assetInformation.getSpecificAssetIds().forEach(specificAssetId -> {
            attributeSpecificAssetId.getAttributes().add(new Attribute(
                    "key",
                    null,
                    null,
                    specificAssetId.getKey(),
                    new RefSemantic(AASNamespace.IdentifierKeyValuePair_Key.getRefSemantic()),
                    null
            ));
            attributeSpecificAssetId.getAttributes().add(new Attribute(
                    "value",
                    null,
                    null,
                    specificAssetId.getValue(),
                    new RefSemantic(AASNamespace.IdentifierKeyValuePair_Value.getRefSemantic()),
                    null
            ));
            internalElement.getAttributes().add(attributeSpecificAssetId);
        });

        // bill of material
        Attribute attributeBillOfMaterial = new Attribute(
                "billOfMaterial",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.Asset_BillOfMaterial.getRefSemantic()),
                null
        );
        attributeBillOfMaterial.setAttributes(new ArrayList<>());
        assetInformation.getBillOfMaterials().forEach(bill -> {
            attributeBillOfMaterial.getAttributes().add(new Attribute(
                    "reference",
                    null,
                    null,
                    ReferenceConverterUtil.convert(bill),
                    new RefSemantic(AASNamespace.BillOfMaterial_Reference.getRefSemantic()),
                    null
            ));
            internalElement.getAttributes().add(attributeBillOfMaterial);
        });

        // file
        internalElement.getAttributes().add(FileConverterUtil.convert(
                assetInformation.getDefaultThumbnail(),
                "defaultThumbnail",
                new RefSemantic(AASNamespace.Asset_DefaultThumbnail.getRefSemantic())
        ));

    }
}
