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
package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UAInstance;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.BasicId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.LangString;

public abstract class I4AASMapper<SOURCE, TARGET extends UANode> {

	protected MappingContext ctx;
	protected SOURCE source;
	protected TARGET target;

	public I4AASMapper(SOURCE src, MappingContext ctx) {
		this.source = src;
		this.ctx = ctx;
	}

	public final TARGET map() {
		target = createTargetObject();
		addToNodeset();
		mapAndAttachChildren();
		return target;
	}

	protected abstract TARGET createTargetObject();

	protected abstract void mapAndAttachChildren();

	private void addToNodeset() {
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(target);
	}

	protected final void addToNodeset(UANode node) {
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(node);
	}

	protected final void addTypeReference(BasicId idForType) {
		addTypeReferenceFor(target, idForType);
	}

	protected final void addTypeReferenceFor(UANode anyNode, BasicId idForType) {
		addTypeReferenceFor(anyNode, idForType, ctx);
	}

	private static final void addTypeReferenceFor(UANode anyNode, BasicId idForType, MappingContext ctx) {
		if (anyNode.getReferences() == null) {
			anyNode.setReferences(new ListOfReferences());
		}
		ListOfReferences references = anyNode.getReferences();
		if (idForType instanceof I4aasId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getI4aasNodeIdAsString((I4aasId) idForType)).build());
		}
		if (idForType instanceof UaId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getUaBaseNodeIdAsString((UaId) idForType)).build());
		}
	}

	protected static final LocalizedText createLocalizedText(String value) {
		return LocalizedText.builder().withValue(value).build();
	}

	protected final String createBrowseName(String name, int namespaceIndx) {
		return namespaceIndx + ":" + name;
	}
	
	protected final String createModelBrowseName(String name) {
		return ctx.getModelNsIndex() + ":" + name;
	}
	
	protected final String createI4AASBrowseName(String name) {
		return ctx.getI4aasNsIndex() + ":" + name;
	}

	protected final UAObject createFolder(String folderName) {
		return createFolder((UAObject) target, folderName, ctx);
	}

	private static final String createI4AASBrowseName(String name, MappingContext ctx) {
		return ctx.getI4aasNsIndex() + ":" + name;
	}

	public static final UAObject createFolder(UAObject target, String folderName, MappingContext ctx) {
		UAObject folder = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createI4AASBrowseName(folderName, ctx)).withDisplayName(createLocalizedText(folderName))
				.build();
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(folder);
		addTypeReferenceFor(folder, UaId.FolderType, ctx);
		attachAsComponent((UAObject) target, folder);
		return folder;
	}


	protected static void attachAsType(UAInstance parent, UAInstance child, BasicId typeId) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(typeId.getName())
				.withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(typeId.getName()).withValue(child.getNodeId())
				.build();
		parent.getReferences().getReference().add(childRef);
	}

	protected static final void attachAsProperty(UAObject parent, UAVariable child) {
		attachAsType(parent, child, UaId.HasProperty);
	}
	
	protected static final void attachAsComponent(UAObject parent, UAObject child) {
		attachAsType(parent, child, UaId.HasComponent);
	}

	protected static final void attachAsOrderedComponent(UAObject parent, UAObject child) {
		attachAsType(parent, child, UaId.HasOrderedComponent);

	}
	protected static final void attachAsDictionaryEntry(UAObject parent, UAObject child) {
		attachAsType(parent, child, UaId.HasDictionaryEntry);
	}

	protected static final LocalizedText mapLangString(LangString description) {
		return LocalizedText.builder().withLocale(description.getLanguage()).withValue(description.getValue()).build();
	}
}
