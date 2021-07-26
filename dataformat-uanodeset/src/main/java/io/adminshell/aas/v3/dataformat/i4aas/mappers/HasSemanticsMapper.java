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
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaIdentifier;
import io.adminshell.aas.v3.model.HasSemantics;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultConceptDescription;

public interface HasSemanticsMapper {

	default void mapSemantics(HasSemantics source, UAObject target, MappingContext ctx) {
		io.adminshell.aas.v3.model.Reference semanticId = source.getSemanticId();
		if (semanticId != null && !semanticId.getKeys().isEmpty()) {
						
			// get Dictionary Entry based on first key
			Key key = semanticId.getKeys().get(0);
			
			UANode nodeForIdentification = ctx.getNodeIdForIdentification(key.getValue());
			
			
			if (nodeForIdentification == null && key.getValue() != null && !key.getValue().isBlank()) {
				nodeForIdentification = fixedConceptDescription(ctx, key);
			}

			
			if (nodeForIdentification != null) {
				// add HasDictionaryEntry reference
				if (nodeForIdentification.getReferences() == null) {
					nodeForIdentification.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference parentRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withIsForward(false).withReferenceType(UaIdentifier.HasDictionaryEntry.getName())
						.withValue(target.getNodeId()).build();
				nodeForIdentification.getReferences().getReference().add(parentRef);
				if (target.getReferences() == null) {
					target.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference childRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withReferenceType(UaIdentifier.HasDictionaryEntry.getName()).withValue(nodeForIdentification.getNodeId())
						.build();
				target.getReferences().getReference().add(childRef);
			}
		}
	}

	default UAObject fixedConceptDescription(MappingContext ctx, Key key) {
		//if not found, a concept description must be created and added
		DefaultConceptDescription virtualCD = new DefaultConceptDescription();
		virtualCD.setIdShort(key.getValue());
		virtualCD.setIdentification(new Identifier() {
			@Override
			public void setIdentifier(String arg0) {
				throw new UnsupportedOperationException();
			}
			@Override
			public void setIdType(IdentifierType arg0) {
				throw new UnsupportedOperationException();
			}
			@Override
			public String getIdentifier() {
				return key.getValue();
			}
			@Override
			public IdentifierType getIdType() {
				return key.getIdType() == null ? null : IdentifierType.valueOf(key.getIdType().name());
			}
		});
		
		UAObject uaVirtualCD = new ConceptDescriptionMapper(virtualCD, ctx).map();
		org.opcfoundation.ua._2011._03.uanodeset.Reference orgaRef = new org.opcfoundation.ua._2011._03.uanodeset.Reference();
		orgaRef.setReferenceType(UaIdentifier.Organizes.getName());
		orgaRef.setIsForward(false);
		orgaRef.setValue("i=17594");
		uaVirtualCD.getReferences().getReference().add(orgaRef);
		return uaVirtualCD;
	}
}
