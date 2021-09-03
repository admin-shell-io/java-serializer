package io.adminshell.aas.v3.model.validator;


import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests the following constraint:
 * <p>
 * <i> AssetInformation/globalAssetId either is a reference to an Asset object or a global reference. </i>
 * </p>
 *
 * @author schnicke
 *
 */

public class TestAASd_023 {
    @Test
    public void correctReferenceToAsset() throws ValidationException, IOException {
        AssetInformation assetInformation = createAssetInformation(KeyElements.ASSET);
        assertEquals(assetInformation.getGlobalAssetId().getKeys().get(0).getType(), KeyElements.ASSET);
        ShaclValidator.getInstance().validate(assetInformation);

    }

    @Test
    public void correctReferenceToGobalRe() throws ValidationException {
        AssetInformation assetInformation = createAssetInformation(KeyElements.GLOBAL_REFERENCE);
        assertEquals(assetInformation.getGlobalAssetId().getKeys().get(0).getType(), KeyElements.GLOBAL_REFERENCE);
        ShaclValidator.getInstance().validate(assetInformation);

    }

    @Test
    public void wrongReferenceToCD() throws ValidationException {
        AssetInformation assetInformation = createAssetInformation(KeyElements.CONCEPT_DESCRIPTION);
        assertEquals(assetInformation.getGlobalAssetId().getKeys().get(0).getType(), KeyElements.CONCEPT_DESCRIPTION);
        try {
            ShaclValidator.getInstance().validate(assetInformation);
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().endsWith("AssetInformation/globalAssetId either is a reference to an Asset object or a global reference."));
        }
    }


    private AssetInformation createAssetInformation(KeyElements keyElements) {
        return new DefaultAssetInformation.Builder()
                .assetKind(AssetKind.INSTANCE)
                .globalAssetId(new DefaultReference.Builder()
                        .key(new DefaultKey.Builder()
                                .idType(KeyType.CUSTOM)
                                .value("1234")
                                .type(keyElements)
                                .build())

                        .build())
                .build();
    }

}


