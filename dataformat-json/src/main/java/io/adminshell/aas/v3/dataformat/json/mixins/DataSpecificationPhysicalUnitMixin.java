/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.LangString;
import java.util.List;

public interface DataSpecificationPhysicalUnitMixin {

    @JsonProperty("conversionFactor")
    public List<String> getConversionFactors();

    @JsonProperty("conversionFactor")
    public void setConversionFactors(List<String> conversionFactors);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("definition")
    public List<LangString> getDefinitions();

    @JsonProperty("definition")
    public void setDefinitions(List<LangString> definitions);

    @JsonProperty("dinNotation")
    public List<String> getDinNotations();

    @JsonProperty("dinNotation")
    public void setDinNotations(List<String> dinNotations);

    @JsonProperty("eceCode")
    public List<String> getEceCodes();

    @JsonProperty("eceCode")
    public void setEceCodes(List<String> eceCodes);

    @JsonProperty("eceName")
    public List<String> getEceNames();

    @JsonProperty("eceName")
    public void setEceNames(List<String> eceNames);

    @JsonProperty("nistName")
    public List<String> getNistNames();

    @JsonProperty("nistName")
    public void setNistNames(List<String> nistNames);

    @JsonProperty("siName")
    public List<String> getSiNames();

    @JsonProperty("siName")
    public void setSiNames(List<String> siNames);

    @JsonProperty("siNotation")
    public List<String> getSiNotations();

    @JsonProperty("siNotation")
    public void setSiNotations(List<String> siNotations);

    @JsonProperty("registrationAuthorityId")
    public List<String> getRegistrationAuthorityIds();

    @JsonProperty("registrationAuthorityId")
    public void setRegistrationAuthorityIds(List<String> registrationAuthorityIds);

    @JsonProperty("supplier")
    public List<String> getSuppliers();

    @JsonProperty("supplier")
    public void setSuppliers(List<String> suppliers);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("unitName")
    public List<String> getUnitNames();

    @JsonProperty("unitName")
    public void setUnitNames(List<String> unitNames);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("unitSymbol")
    public List<String> getUnitSymbols();

    @JsonProperty("unitSymbol")
    public void setUnitSymbols(List<String> unitSymbols);
}
