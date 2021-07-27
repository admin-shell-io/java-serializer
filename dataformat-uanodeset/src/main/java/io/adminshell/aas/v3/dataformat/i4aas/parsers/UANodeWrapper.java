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
package io.adminshell.aas.v3.dataformat.i4aas.parsers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfExtensions;
import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.BasicIdentifier;

public class UANodeWrapper {

	private UANode node;
	private TypeResolver typeResolver;
	private ParserContext ctx;
	
	private List<UANodeWrapper> components;
	private List<UANodeWrapper> properties;
	private BasicIdentifier  type;

	public UANodeWrapper(UANode node, NodeIdResolver nodeIdResolver, ParserContext parserContext) {
		this.node = node;
		this.ctx = parserContext;
		typeResolver = new TypeResolver(parserContext.getI4aasNsIdx());
		//parse components
		components = ctx.getComponents(getReferences()).map(nodeIdResolver::getUANode).filter(Objects::nonNull).map(uaNode -> new UANodeWrapper(uaNode, nodeIdResolver, ctx)).collect(Collectors.toList());
		//parse properties
		properties = ctx.getProperties(getReferences()).map(nodeIdResolver::getUANode).filter(Objects::nonNull).map(uaNode -> new UANodeWrapper(uaNode, nodeIdResolver, ctx)).collect(Collectors.toList());
		//parse type definition
		ctx.getTypeDefinitons(getReferences()).map(typeResolver::getType).findFirst().ifPresent(id -> type = id.get());
	}


	public List<LocalizedText> getDisplayName() {
		return node.getDisplayName();
	}

	public List<LocalizedText> getDescription() {
		return node.getDescription();
	}

	public ListOfReferences getReferences() {
		return node.getReferences();
	}

	public ListOfExtensions getExtensions() {
		return node.getExtensions();
	}

	public String getNodeId() {
		return node.getNodeId();
	}

	public String getBrowseName() {
		return node.getBrowseName();
	}

	public List<UANodeWrapper> getComponents() {
		return components;
	}
	
	public List<UANodeWrapper> getProperties() {
		return properties;
	}

	public BasicIdentifier getType() {
		return type;
	}
}
