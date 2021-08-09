package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.Aml2AasConfig;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import java.util.List;

public class Aml2AasObjectMapper {

    private Aml2AasConfig config;

    public Aml2AasObjectMapper(Aml2AasConfig config) {
        this.config = config;
    }

    public AssetAdministrationShellEnvironment map(CAEXFile aml) throws MappingException {
        // unclear how to handle additional information
        List<Object> additionalInformation = aml.getAdditionalInformation();
        // find AAS elements throughout all instance hierarchies
        AmlParser parser = new AmlParser(aml);
        MappingProvider mappingProvider = new MappingProvider(Aml2AasMapper.class, new DefaultMapper(), new DefaultMapper());
        mappingProvider.register(new AssetAdministrationShellEnvironmentMapper());
        AasTypeFactory typeFactory = new AasTypeFactory();
        Aml2AasMappingContext context = new Aml2AasMappingContext(mappingProvider, typeFactory);
        Object result = context.getMappingProvider().getMapper(AssetAdministrationShellEnvironment.class).map(parser, context);
        String foo = "";
        return (AssetAdministrationShellEnvironment) result;
    }
}
