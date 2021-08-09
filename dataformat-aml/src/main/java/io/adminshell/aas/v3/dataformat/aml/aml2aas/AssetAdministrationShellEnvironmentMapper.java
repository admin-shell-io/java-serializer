package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.AmlDocumentInfo;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AssetAdministrationShellEnvironmentMapper implements Aml2AasMapper<AssetAdministrationShellEnvironment> {

    @Override
    public AssetAdministrationShellEnvironment map(AmlParser parser, Aml2AasMappingContext context) throws MappingException {
        AssetAdministrationShellEnvironment result = new DefaultAssetAdministrationShellEnvironment.Builder().build();
        List<InternalElementType> shells = parser.getContent().getInstanceHierarchy().stream()
                .flatMap(x -> x.getInternalElement().stream().filter(filterByRole(AssetAdministrationShell.class)))
                .collect(Collectors.toList());
        shells.forEach(x -> {
            parser.setCurrent(x);
            try {
                AssetAdministrationShell aas = (AssetAdministrationShell) context.getMappingProvider().getMapper(AssetAdministrationShell.class).map(parser, context);
                result.getAssetAdministrationShells().add(aas);
            } catch (MappingException ex) {
                Logger.getLogger(AssetAdministrationShellEnvironmentMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return result;
    }

    private Predicate<InternalElementType> filterByRole(Class<?> type) {
        return x -> x.getRoleRequirements() != null
                && x.getRoleRequirements().getRefBaseRoleClassPath().equals(
                        AmlDocumentInfo.DEFAULT_ASSET_ADMINISTRATION_SHELL_ROLE_CLASS_LIB + "/" + type.getSimpleName());
    }

}
