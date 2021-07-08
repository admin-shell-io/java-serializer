package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util;

//import de.fraunhofer.iosb.ilt.aas.service.model.common.types.reference.Key;
//import de.fraunhofer.iosb.ilt.aas.service.model.common.types.reference.Reference;
import io.adminshell.aas.v3.model.*;

import java.util.Iterator;

public class ReferenceConverterUtil {

    public static String convert(Reference reference) {
        if(reference == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        Iterator<Key> stringIterator = reference.getKeys().iterator();
        while (stringIterator.hasNext()) {
            Key key = stringIterator.next();
            builder.append("(");
            builder.append(key.getType());
            builder.append(")");

            // No local attr. in v3
//            builder.append("(");
//            builder.append(key.isLocal() ? "local" : "no-local");
//            builder.append(")");

            builder.append("[");
            builder.append(key.getIdType());
            builder.append("]");

            builder.append(key.getValue());

            builder.append(stringIterator.hasNext() ? "," : "");
        }

        return builder.toString();
    }
}
