package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ListOfLocalizedText;
import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.LangString;

public class LangStringPropertyMapper extends I4AASMapper<List<LangString>, UAVariable> {

	private String key;
	private ObjectFactory objectFactory = new ObjectFactory();

	public LangStringPropertyMapper(String key, List<LangString> src, MappingContext ctx) {
		super(src, ctx);
		this.key = key;
	}

	@Override
	protected UAVariable createTargetObject() {
		ListOfLocalizedText list = new ListOfLocalizedText();
		for (LangString langString : source) {
			org.opcfoundation.ua._2008._02.types.LocalizedText localText = new org.opcfoundation.ua._2008._02.types.LocalizedText();
			localText.setLocale(objectFactory.createString(langString.getLanguage()));
			localText.setText(objectFactory.createString(langString.getValue()));
			list.getLocalizedText().add(localText);
		}
		JAXBElement<ListOfLocalizedText> listOfLocalizedText = objectFactory.createListOfLocalizedText(list);
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder()
				.withValue().withAny(listOfLocalizedText).end().withDisplayName(I4AASUtils.createLocalizedText(key))
				.withDataType(UaId.LocalizedText.getName()).withValueRank(1).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf(key)).withAccessLevel(3L);
		target = idVarBuilder.build();
		addTypeReference(target, UaId.PropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		// TODO Auto-generated method stub
	}

}
