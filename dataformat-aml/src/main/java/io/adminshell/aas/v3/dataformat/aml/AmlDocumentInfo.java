package io.adminshell.aas.v3.dataformat.aml;

public class AmlDocumentInfo {

    public static final String DEFAULT_ASSET_ADMINISTRATION_SHELL_INSTANCE_HIERARCHY = "AssetAdministrationShellInstanceHierarchy";
    public static final String DEFAULT_ASSET_ADMINISTRATION_SHELL_SYSTEM_UNIT_CLASS_LIB = "AssetAdministrationShellSystemUnitClasses";
    public static final String DEFAULT_ASSET_ADMINISTRATION_SHELL_ROLE_CLASS_LIB = "AssetAdministrationShellRoleClassLib";
    public static final String DEFAULT_ASSET_ADMINISTRATION_SHELL_INTERFACE_CLASS_LIB = "AssetAdministrationShellInterfaceClassLib";
    public static final String DEFAULT_DATA_SPECIFICATION_TEMPLATES_UNIT_CLASS_LIB = "AssetAdministrationShellDataSpecificationTemplates";
    public static final String DEFAULT_CONCEPT_DESCRIPTION_INSTANCE_HIERARCHY = "ConceptDescriptionInstanceHierarchy";

    private final String assetAdministrationShellSystemUnitClassLib;
    private final String dataSpecificationTemplatesSystemUnitClassLib;
    private final String assetAdministrationShellInstanceHierarchy;
    private final String conceptDescriptionInstanceHierarchy;
    private final String assetAdministrationShellRoleClassLib;
    private final String assetAdministrationShellInterfaceClassLib;

    public AmlDocumentInfo() {
        this.assetAdministrationShellSystemUnitClassLib = DEFAULT_ASSET_ADMINISTRATION_SHELL_SYSTEM_UNIT_CLASS_LIB;
        this.dataSpecificationTemplatesSystemUnitClassLib = DEFAULT_DATA_SPECIFICATION_TEMPLATES_UNIT_CLASS_LIB;
        this.assetAdministrationShellInstanceHierarchy = DEFAULT_ASSET_ADMINISTRATION_SHELL_INSTANCE_HIERARCHY;
        this.conceptDescriptionInstanceHierarchy = DEFAULT_CONCEPT_DESCRIPTION_INSTANCE_HIERARCHY;
        this.assetAdministrationShellRoleClassLib = DEFAULT_ASSET_ADMINISTRATION_SHELL_ROLE_CLASS_LIB;
        this.assetAdministrationShellInterfaceClassLib = DEFAULT_ASSET_ADMINISTRATION_SHELL_INTERFACE_CLASS_LIB;
    }

    public AmlDocumentInfo(
            String assetAdministrationShellInstanceHierarchy,
            String conceptDescriptionInstanceHierarchy,
            String assetAdministrationShellSystemUnitClassLib) {
        this.assetAdministrationShellSystemUnitClassLib = assetAdministrationShellSystemUnitClassLib;
        this.assetAdministrationShellInstanceHierarchy = assetAdministrationShellInstanceHierarchy;
        this.conceptDescriptionInstanceHierarchy = conceptDescriptionInstanceHierarchy;
        this.dataSpecificationTemplatesSystemUnitClassLib = DEFAULT_DATA_SPECIFICATION_TEMPLATES_UNIT_CLASS_LIB;
        this.assetAdministrationShellRoleClassLib = DEFAULT_ASSET_ADMINISTRATION_SHELL_ROLE_CLASS_LIB;
        this.assetAdministrationShellInterfaceClassLib = DEFAULT_ASSET_ADMINISTRATION_SHELL_INTERFACE_CLASS_LIB;
    }

    public String getAssetAdministrationShellSystemUnitClassLib() {
        return assetAdministrationShellSystemUnitClassLib;
    }

    public String getDataSpecificationTemplatesSystemUnitClassLib() {
        return dataSpecificationTemplatesSystemUnitClassLib;
    }

    public String getAssetAdministrationShellInstanceHierarchy() {
        return assetAdministrationShellInstanceHierarchy;
    }

    public String getConceptDescriptionInstanceHierarchy() {
        return conceptDescriptionInstanceHierarchy;
    }

    public String getAssetAdministrationShellRoleClassLib() {
        return assetAdministrationShellRoleClassLib;
    }

    public String getAssetAdministrationShellInterfaceClassLib() {
        return assetAdministrationShellInterfaceClassLib;
    }
}
