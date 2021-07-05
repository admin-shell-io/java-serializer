package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

public class AssetAdministrationShellEnvironmentSerializer extends JsonSerializer<AssetAdministrationShellEnvironment> {

    private static final String[] SCHEMA_LOCATION = {"xsi:schemaLocation",
        "http://www.admin-shell.io/aas/3/0 AAS.xsd http://www.admin-shell.io/IEC61360/3/0 IEC61360.xsd http://www.admin-shell.io/aas/abac/3/0 AAS_ABAC.xsd"};

    private static final QName AASENV_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "aasenv");
    private static final QName AASLIST_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "assetAdministrationShells");
    private static final QName AAS_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "assetAdministrationShell");
    private static final QName CONCEPTDICTIONARYLIST_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "conceptDescriptions");
    private static final QName CONCEPTDICTIONARY_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "conceptDescription");
    private static final QName SUBMODELLIST_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "submodels");
    private static final QName SUBMODEL_TAGNAME = new QName(AasXmlNamespaceContext.AAS_URI, "submodel");

    private Map<String, String> namespacePrefixes;

    public AssetAdministrationShellEnvironmentSerializer(Map<String, String> namespacePrefixes) {
        this.namespacePrefixes = namespacePrefixes;
    }

    public AssetAdministrationShellEnvironmentSerializer() {
        this.namespacePrefixes = AasXmlNamespaceContext.PREFERRED_PREFIX_CONTEXT;
    }

    @Override
    public void serialize(AssetAdministrationShellEnvironment value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        ToXmlGenerator xgen = (ToXmlGenerator) gen;
        XMLStreamWriter streamWriter = xgen.getStaxWriter();
        setPrefixes(streamWriter);
        writeOpeningTag(xgen, streamWriter);
        writeContent(value, xgen);
        closeOpeningTag(xgen);
    }

    private void setPrefixes(XMLStreamWriter streamWriter) {
        try {
            for (Entry<String, String> namespacePrefixEntry : namespacePrefixes.entrySet()) {
                streamWriter.setPrefix(namespacePrefixEntry.getKey(), namespacePrefixEntry.getValue());
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeOpeningTag(ToXmlGenerator xgen, XMLStreamWriter streamWriter) throws IOException {
        xgen.setNextName(AASENV_TAGNAME);
        xgen.writeStartObject();
        try {
            for (Entry<String, String> namespacePrefixEntry : namespacePrefixes.entrySet()) {
                streamWriter.writeNamespace(namespacePrefixEntry.getKey(), namespacePrefixEntry.getValue());
            }
            streamWriter.writeAttribute(SCHEMA_LOCATION[0], SCHEMA_LOCATION[1]);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeContent(AssetAdministrationShellEnvironment value, ToXmlGenerator xgen) throws IOException {
        writeAssetAdministrationShells(xgen, value.getAssetAdministrationShells());
        writeConceptDescriptions(xgen, value.getConceptDescriptions());
        writeSubmodels(xgen, value.getSubmodels());
    }

    private void writeAssetAdministrationShells(ToXmlGenerator xgen, List<AssetAdministrationShell> aasList)
            throws IOException {
        if (aasList.isEmpty()) {
            return;
        }
        writeWrappedArray(xgen, AASLIST_TAGNAME, AAS_TAGNAME, aasList);
    }

    private void writeConceptDescriptions(ToXmlGenerator xgen, List<ConceptDescription> conceptDescriptions)
            throws IOException {
        if (conceptDescriptions.isEmpty()) {
            return;
        }
        writeWrappedArray(xgen, CONCEPTDICTIONARYLIST_TAGNAME, CONCEPTDICTIONARY_TAGNAME, conceptDescriptions);
    }

    private void writeSubmodels(ToXmlGenerator xgen, List<Submodel> submodels) throws IOException {
        if (submodels.isEmpty()) {
            return;
        }
        writeWrappedArray(xgen, SUBMODELLIST_TAGNAME, SUBMODEL_TAGNAME, submodels);
    }

    private void writeWrappedArray(ToXmlGenerator xgen, QName wrapper, QName wrapped, List<?> list)
            throws IOException {
        xgen.writeFieldName(wrapper.getLocalPart());
        xgen.writeStartArray();
        xgen.startWrappedValue(wrapper, wrapped);
        for (Object aas : list) {
            xgen.writeObject(aas);
        }
        xgen.finishWrappedValue(wrapper, wrapped);
        xgen.writeEndArray();
    }

    private void closeOpeningTag(ToXmlGenerator xgen) throws IOException {
        xgen.writeEndObject();
    }
}
