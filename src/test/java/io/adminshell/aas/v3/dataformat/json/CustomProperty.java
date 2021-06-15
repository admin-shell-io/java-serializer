package io.adminshell.aas.v3.dataformat.json;

import java.util.List;
import java.util.Objects;

import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Reference;

public class CustomProperty implements Property {

    protected List<Reference> dataSpecifications;

    protected ModelingKind kind;

    protected Reference semanticId;

    protected String value;

    protected Reference valueId;

    protected String valueType;

    protected List<Constraint> qualifiers;

    protected String category;

    protected List<LangString> descriptions;

    protected List<LangString> displayNames;

    protected String idShort;

    protected CustomProperty() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.valueType, this.value, this.valueId, this.category, this.descriptions,
            this.displayNames, this.idShort, this.qualifiers, this.dataSpecifications, this.kind,
            this.semanticId});
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            CustomProperty other = (CustomProperty) obj;
            return Objects.equals(this.valueType, other.valueType) && Objects.equals(this.value, other.value)
                    && Objects.equals(this.valueId, other.valueId) && Objects.equals(this.category, other.category)
                    && Objects.equals(this.descriptions, other.descriptions)
                    && Objects.equals(this.displayNames, other.displayNames)
                    && Objects.equals(this.idShort, other.idShort) && Objects.equals(this.qualifiers, other.qualifiers)
                    && Objects.equals(this.dataSpecifications, other.dataSpecifications)
                    && Objects.equals(this.kind, other.kind) && Objects.equals(this.semanticId, other.semanticId);
        }
    }

    @Override
    final public String getValueType() {
        return valueType;
    }

    @Override
    final public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    final public String getValue() {
        return value;
    }

    @Override
    final public void setValue(String value) {
        this.value = value;
    }

    @Override
    final public Reference getValueId() {
        return valueId;
    }

    @Override
    final public void setValueId(Reference valueId) {
        this.valueId = valueId;
    }

    @Override
    final public String getCategory() {
        return category;
    }

    @Override
    final public void setCategory(String category) {
        this.category = category;
    }

    @Override
    final public List<LangString> getDescriptions() {
        return descriptions;
    }

    @Override
    final public void setDescriptions(List<LangString> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    final public List<LangString> getDisplayNames() {
        return displayNames;
    }

    @Override
    final public void setDisplayNames(List<LangString> displayNames) {
        this.displayNames = displayNames;
    }

    @Override
    final public String getIdShort() {
        return idShort;
    }

    @Override
    final public void setIdShort(String idShort) {
        this.idShort = idShort;
    }

    @Override
    final public List<Constraint> getQualifiers() {
        return qualifiers;
    }

    @Override
    final public void setQualifiers(List<Constraint> qualifiers) {
        this.qualifiers = qualifiers;
    }

    @Override
    final public List<Reference> getDataSpecifications() {
        return dataSpecifications;
    }

    @Override
    final public void setDataSpecifications(List<Reference> dataSpecifications) {
        this.dataSpecifications = dataSpecifications;
    }

    @Override
    final public ModelingKind getKind() {
        return kind;
    }

    @Override
    final public void setKind(ModelingKind kind) {
        this.kind = kind;
    }

    @Override
    final public Reference getSemanticId() {
        return semanticId;
    }

    @Override
    final public void setSemanticId(Reference semanticId) {
        this.semanticId = semanticId;
    }
}
