package io.adminshell.aas.v3.dataformat.i4aas.mappers.utils;

import java.util.UUID;

import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;

import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.View;

public class I4AASUtils {

	public static String generateRandomNamespace(UANodeSet nodeset) {
		return "http://example.org/i4aasNs/" + UUID.randomUUID() + "/";
	}

	public static LocalizedText createLocalizedText(String value) {
		return LocalizedText.builder().withValue(value).build();
	}

	public static LocalizedText createDisplayName(Referable ref) {
		if (ref instanceof Submodel) {
			return LocalizedText.builder().withValue(I4AASConstants.SM_DISPLAYNAME_PREFIX + ref.getIdShort()).build();
		} else if (ref instanceof AssetAdministrationShell) {
			return LocalizedText.builder().withValue(I4AASConstants.AAS_DISPLAYNAME_PREFIX + ref.getIdShort()).build();
		} else if (ref instanceof Asset) {
			return LocalizedText.builder().withValue(I4AASConstants.ASSET_DISPLAYNAME_PREFIX + ref.getIdShort())
					.build();
		} else if (ref instanceof View) {
			return LocalizedText.builder().withValue(I4AASConstants.VIEW_DISPLAYNAME_PREFIX + ref.getIdShort()).build();
		} else {
			return LocalizedText.builder().withValue(ref.getIdShort()).build();
		}
	}
	
}
