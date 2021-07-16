package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.Collection;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;

public class SubmodelElementCollectionMapper extends ReferableMapper<SubmodelElementCollection> {

	public SubmodelElementCollectionMapper(SubmodelElementCollection submodelElement, MappingContext ctx) {
		super(submodelElement, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASSubmodelElementCollectionType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		Collection<SubmodelElement> values = source.getValues();
		for (SubmodelElement submodelElement : values) {
			if (submodelElement instanceof io.adminshell.aas.v3.model.Capability) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());
			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Blob) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.File) {
				UAObject UAprop = new FileMapper((io.adminshell.aas.v3.model.File) submodelElement, ctx).map();
				attachAsComponent(target, UAprop);

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.MultiLanguageProperty) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Property) {
				UAObject UAprop = new PropertyMapper((Property) submodelElement, ctx).map();
				attachAsComponent(target, UAprop);
			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Range) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.ReferenceElement) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Entity) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Event) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.EventElement) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}

			if (submodelElement instanceof io.adminshell.aas.v3.model.EventMessage) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Operation) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.RelationshipElement) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.SubmodelElementCollection) {
				UAObject UAprop = new SubmodelElementCollectionMapper((SubmodelElementCollection) submodelElement, ctx)
						.map();
				attachAsComponent(target, UAprop);
			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Capability) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Capability) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Capability) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
			if (submodelElement instanceof io.adminshell.aas.v3.model.Capability) {
				throw new UnsupportedOperationException(
						"mapping not implemented for " + submodelElement.getClass().getName());

			}
		}
	}

}
