/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.aml.model.caex;

public enum AASNamespace {
    Referable_IdShort("Referable/idShort"),
    Referable_Category("Referable/category"),
    Referable_Description("Referable/description"),
    Asset_Kind("AssetInformation/kind"),
    Asset_GlobalAssetId("AssetInformation/globalAssetId"),
    Asset_DefaultThumbnail("AssetInformation/defaultThumbnail"),
    Asset_SpecificAssetId("AssetInformation/specificAssetId"),
    Asset_BillOfMaterial("AssetInformation/billOfMaterial"),
    HasKind_Kind("HasKind/kind"),
    HasSemantics_SemanticId("HasSemantics/semanticId"),
    Identifiable_Identification("Identifiable/identification"),
    Identifier_IdType("Identifier/idType"),
    Identifier_Id("Identifier/id"),
    Identifiable_Administration("Identifiable/administration"),
    AdministrationInformation_Version("AdministrationInformation/version"),
    AdministrationInformation_Revision("AdministrationInformation/revision"),
    HasDataSpecification_DataSpecification("HasDataSpecification/dataSpecification"),
    Qualifiable_Qualifier("Qualifiable/qualifier"),
    Qualifier_Qualifier("Qualifier/qualifier"),
    Qualifier_Type("Qualifier/type"),
    Qualifier_Value("Qualifier/value"),
    Entity_Type("Entity/type"),
    Entity_Asset("Entity/asset"),
    BasicEvent_Observed("BasicEvent/observed"),
    Qualifier_ValueId("Qualifier/valueId"),
    AssetAdministrationShell_DerivedFrom("AssetAdministrationShell/derivedFrom"),
    Asset_AssetIdentificationModel("Asset/assetIdentificationModel"),
    AssetAdministrationShell_Submodels("AssetAdministrationShell/submodels"),
    Property_Value("Property/value"),
    MultiLanguageProperty_Value("MultiLanguageProperty/value"),
    MultiLanguageProperty_ValueId("MultiLanguageProperty/valueId"),
    Blob_Value("Blob/value"),
    Blob_MimeType("Blob/mimeType"),
    File_Value("File/value"),
    File_MimeType("File/mimeType"),
    Range_Max("Range/max"),
    Range_Min("Range/min"),
    ReferenceElement_Value("ReferenceElement/value"),
    Property_ValueId("Property/valueId"),
    AnnotationRelationshipElement_Annotations("AnnotationRelationshipElement/annotations"),
    ConceptDescription_IsCaseOf("ConceptDescription/isCaseOf"),
    ConceptDescription_DataSpecification("ConceptDescription/dataSpecification"),
    RelationshipElement_First("RelationshipElement/first"),
    RelationshipElement_Second("RelationshipElement/second"),
    IdentifierKeyValuePair_Key("IdentifierKeyValuePair/key"),
    IdentifierKeyValuePair_Value("IdentifierKeyValuePair/value"),
    BillOfMaterial_Reference("BillOfMaterial/reference");

    private String refSemantic;

    AASNamespace(String refSemantic) {
        this.refSemantic = refSemantic;
    }

    public String getRefSemantic() {
        return "AAS:" + refSemantic;
    }
}
