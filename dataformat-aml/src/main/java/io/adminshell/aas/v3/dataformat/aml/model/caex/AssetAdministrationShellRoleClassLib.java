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
