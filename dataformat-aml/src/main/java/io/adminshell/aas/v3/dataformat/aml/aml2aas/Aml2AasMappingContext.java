package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.AmlDocumentInfo;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.dataformat.mapping.TargetBasedMappingContext;

public class Aml2AasMappingContext extends TargetBasedMappingContext {

    private AmlDocumentInfo documentInfo;
    private AasTypeFactory typeFactory;
    private Class<?> type;

    public Aml2AasMappingContext(MappingProvider mappingProvider, AasTypeFactory typeFactory) {
        super(mappingProvider);
        this.typeFactory = typeFactory;
        this.type = null;
    }

    private Aml2AasMappingContext(MappingProvider mappingProvider, AasTypeFactory typeFactory, Class<?> type) {
        super(mappingProvider);
        this.typeFactory = typeFactory;
        this.type = type;
    }

    public Aml2AasMappingContext with(Class<?> type) {
        return new Aml2AasMappingContext(mappingProvider, typeFactory, type);
    }

    public AmlDocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    public void setDocumentInfo(AmlDocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public Class<?> getType() {
        return type;
    }

    public AasTypeFactory getTypeFactory() {
        return typeFactory;
    }

}
