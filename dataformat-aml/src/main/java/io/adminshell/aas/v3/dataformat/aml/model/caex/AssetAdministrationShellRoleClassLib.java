package io.adminshell.aas.v3.dataformat.aml.model.caex;

public enum AssetAdministrationShellRoleClassLib {
    AssetAdministrationShell("AssetAdministrationShell"),
    Asset("Asset"),
    AssetInformation("AssetInformation"),
    File("File"),
    Range("Range"),
    Entity("Entity"),
    View("View"),
    BasicEvent("BasicEvent"),
    Capability("Capability"),
    Operation("Operation"),
    OperationInputVar("OperationInputVariables"),
    OperationOutputVar("OperationOutputVariables"),
    OperationInOutputVar("OperationInoutputVariables"),
    Property("Property"),
    SubmodelElementCollection("SubmodelElementCollection"),
    Submodel("Submodel"),
    ReferenceElement("ReferenceElement"),
    MultiLanguageProperty("MultiLanguageProperty"),
    RelationshipElementMapper("RelationshipElement"),
    AnnotatedRelationshipElement("AnnotatedRelationshipElement"),
    Blob("Blob");

    private String refBaseRoleClassPath;

    AssetAdministrationShellRoleClassLib(String refBaseRoleClassPath) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
    }

    public String getRefBaseRoleClassPath() {
        return "AssetAdministrationShellRoleClassLib/" + refBaseRoleClassPath;
    }
}
