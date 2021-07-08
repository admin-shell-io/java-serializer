package io.adminshell.aas.v3.dataformat.aml.model.caex;

public enum AssetAdministrationShellInterfaceClassLib {
    FileDataReference("FileDataReference");


    private String refBaseRoleClassPath;

    AssetAdministrationShellInterfaceClassLib(String refBaseRoleClassPath) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
    }

    public String getRefBaseRoleClassPath() {
        return "AssetAdministrationShellInterfaceClassLib/" + refBaseRoleClassPath;
    }
}
