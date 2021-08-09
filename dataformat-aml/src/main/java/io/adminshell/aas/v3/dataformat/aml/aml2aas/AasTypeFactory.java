package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class AasTypeFactory {

    private Map<Class<?>, Class<?>> typeMapping;

    public AasTypeFactory() {
        ReflectionHelper.DEFAULT_IMPLEMENTATIONS.forEach(x -> typeMapping.put(x.getInterfaceType(), x.getImplementationType()));
    }

    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        typeMapping.put(aasInterface, implementation);
    }

    public <T> T newInstance(Class<T> aasInterface) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> classToInstantiate = aasInterface;
        if (typeMapping.containsKey(aasInterface)) {
            classToInstantiate = typeMapping.get(aasInterface);
        }
        Constructor<?> constructor = classToInstantiate.getConstructor();
        constructor.setAccessible(true);
        return (T) constructor.newInstance();
    }
}
