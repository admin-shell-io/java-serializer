package io.adminshell.aas.v3.dataformat.jsonld.custom;

import com.fasterxml.jackson.databind.introspect.ClassIntrospector;

public class ReflectiveMixInResolver implements ClassIntrospector.MixInResolver {

    @Override
    public Class<?> findMixInClassFor(Class<?> cls) {
        if (cls.isEnum())
        {
            return JsonLdEnumMixin.class;
        }
        try {
            return Class.forName("io.adminshell.aas.v3.dataformat.jsonld.mixins." + cls.getSimpleName() + "Mixin");
        }
        catch (ClassNotFoundException ignored)
        {
            return null;
        }
    }

    @Override
    public ClassIntrospector.MixInResolver copy() {
        return new ReflectiveMixInResolver();
    }
}
