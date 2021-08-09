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
package io.adminshell.aas.v3.dataformat.aml.util;

import com.google.common.base.Objects;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.core.deserialization.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.core.serialization.EnumSerializer;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Identifiable;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AASUtils {

    private AASUtils() {
    }

    public static String asString(Reference reference) {
        if (reference == null) {
            return null;
        }
        return reference.getKeys().stream()
                .map(x -> String.format("(%s)[%s]%s", x.getType(), x.getIdType(), x.getValue()))
                .collect(Collectors.joining(","));
    }

    public static Optional<Submodel> resolveSubmodelReference(Reference reference, AssetAdministrationShellEnvironment environment) {
        return environment.getSubmodels().stream()
                .filter(sm -> Objects.equal(sm.getIdentification().getIdentifier(), reference.getKeys().get(0).getValue()))
                .findAny();
    }

    public static boolean isLocal(Reference reference, AssetAdministrationShellEnvironment environment) {
        // could/should additionally try resolving it
        return !reference.getKeys().stream().anyMatch(x -> x.getType() == KeyElements.GLOBAL_REFERENCE);
    }

    public static List<Submodel> getSubmodelTemplates(AssetAdministrationShell aas, AssetAdministrationShellEnvironment environment) {
        return aas.getSubmodels().stream()
                .map(ref -> resolveSubmodelReference(ref, environment))
                .filter(sm -> sm.isPresent())
                .map(sm -> sm.get())
                .filter(sm -> sm.getKind() == ModelingKind.TEMPLATE)
                .collect(Collectors.toList());
    }

    public static boolean hasTemplate(AssetAdministrationShell aas, AssetAdministrationShellEnvironment environment) {
        return !getSubmodelTemplates(aas, environment).isEmpty();
    }

    public static Reference identifiableToReference(Identifiable identifiable) {
        return new DefaultReference.Builder()
                .key(new DefaultKey.Builder()
                        .type(referableToKeyType(identifiable))
                        .idType(KeyType.valueOf(identifiable.getIdentification().getIdType().toString()))
                        .value(identifiable.getIdentification().getIdentifier())
                        .build())
                .build();
    }

    public static KeyElements referableToKeyType(Referable referable) {
        Class<?> aasInterface = ReflectionHelper.getAasInterface(referable.getClass());
        if (aasInterface != null) {
            return KeyElements.valueOf(EnumDeserializer.translate(aasInterface.getSimpleName()));
        }
        return null;
    }

    public static Optional<Class> keyTypeToClass(KeyElements key) {
        return Stream.concat(ReflectionHelper.INTERFACES.stream(), ReflectionHelper.INTERFACES_WITHOUT_DEFAULT_IMPLEMENTATION.stream())
                .filter(x -> x.getSimpleName().equals(EnumSerializer.translate(key.name())))
                .findAny();
    }

    public static Reference asReference(Reference parent, Referable element) {
//        if (element == null) {
//            return null;
//        }
//        Reference result = AASUtils.cloneReference(parent);
//        if (result == null && Identifiable.class.isAssignableFrom(element.getClass())) {
//            return AASUtils.identifiableToReference((Identifiable) element);
//        }
//        if (result != null) {
//            result.getKeys().add(new DefaultKey.Builder()
//                    .type(AASUtils.referableToKeyType(element))
//                    .idType(KeyType.ID_SHORT)
//                    .value(element.getIdShort())
//                    .build());
//        }
//        return result;

        if (element == null) {
            return null;
        } else if (Identifiable.class.isAssignableFrom(element.getClass())) {
            return AASUtils.identifiableToReference((Identifiable) element);
        } else {
            Reference result = AASUtils.cloneReference(parent);
            if (result != null) {
                result.getKeys().add(new DefaultKey.Builder()
                        .type(AASUtils.referableToKeyType(element))
                        .idType(KeyType.ID_SHORT)
                        .value(element.getIdShort())
                        .build());
            }
            return result;
        }
    }

    public static boolean equals(Reference ref1, Reference ref2) {
        boolean ref1Empty = ref1 == null || ref1.getKeys() == null || ref1.getKeys().isEmpty();
        boolean ref2Empty = ref2 == null || ref2.getKeys() == null || ref2.getKeys().isEmpty();
        if (ref1Empty && ref2Empty) {
            return true;
        }
        if (ref1Empty != ref2Empty) {
            return false;
        }
        int keyLength = Math.min(ref1.getKeys().size(), ref2.getKeys().size());
        for (int i = 0; i < keyLength; i++) {
            Key ref1Key = ref1.getKeys().get(ref1.getKeys().size() - (i + 1));
            Key ref2Key = ref2.getKeys().get(ref2.getKeys().size() - (i + 1));
            Optional<Class> ref1Type = keyTypeToClass(ref1Key.getType());
            Optional<Class> ref2Type = keyTypeToClass(ref2Key.getType());
            if (ref1Type.isPresent() != ref2Type.isPresent()) {
                return false;
            }
            if (ref1Type.isPresent()) {
                if (!(ref1Type.get().isAssignableFrom(ref2Type.get())
                        || ref2Type.get().isAssignableFrom(ref1Type.get()))) {
                    return false;
                }
            }
            if (!(Objects.equal(ref1Key.getIdType(), ref2Key.getIdType())
                    && Objects.equal(ref1Key.getValue(), ref2Key.getValue()))) {
                return false;
            }
            if ((ref1Key.getIdType() == KeyType.IRI)
                    || (ref1Key.getIdType() == KeyType.IRDI)
                    || (ref1Key.getIdType() == KeyType.CUSTOM)) {
                return true;
            }
        }
        return true;
    }

    public static Reference cloneReference(Reference reference) {
        if (reference == null || reference.getKeys() == null || reference.getKeys().isEmpty()) {
            return null;
        }
        return new DefaultReference.Builder()
                .keys(reference.getKeys().stream().map(x -> new DefaultKey.Builder()
                .idType(x.getIdType())
                .type(x.getType())
                .value(x.getValue())
                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static Referable resolve(Reference reference, AssetAdministrationShellEnvironment env) {
        if (reference == null || reference.getKeys() == null || reference.getKeys().isEmpty()) {
            return null;
        }
        List<Key> keys = reference.getKeys();

        //get reduced Key list from last identifiable key to end
        List<Key> reducedKeyList = new ArrayList<>();
        reduceKeyList(keys, reducedKeyList);

        //resolve the reference
        Referable searchedReferable = null;
        for (int i = 0; i < reducedKeyList.size(); i++) {

            Key actualKey = reducedKeyList.get(i);
            String className = EnumSerializer.translate(actualKey.getType().name());
            try {

                //get class from the key type and calculate the method name for getting a list of the elements
                //e.g. "getSubmodelElements"
                Class c = Class.forName(ReflectionHelper.MODEL_PACKAGE_NAME + "." + className);

                //TODO: visitor pattern? e.g. for Operation Variables
                String methodName = "get" + className + "s";

                Method method = null;

                if (i == 0) {
                    //first Key is identifiable

                    //get list of elements due to the key type
                    method = env.getClass().getMethod(methodName);
                    List<Object> list = (List<Object>) method.invoke(env);
                    searchedReferable = getIdentifiable(actualKey, c, list);

                } else {

                    //if searchedReferable is null then the first identifiable could not be found
                    if (searchedReferable == null) {
                        return null;
                    }

                    //get list of elements due to the key type
                    method = searchedReferable.getClass().getMethod(methodName);
                    List<Object> list = (List<Object>) method.invoke(searchedReferable);
                    searchedReferable = getReferable(actualKey, list);
                }

            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return searchedReferable;
    }

    private static void reduceKeyList(List<Key> keys, List<Key> reducedKeyList) {
        for (int i = keys.size() - 1; i >= 0; i--) {
            Key k = keys.get(i);
            reducedKeyList.add(0, k);
            String className = EnumSerializer.translate(k.getType().name());
            Class c = null;
            try {
                c = Class.forName(ReflectionHelper.MODEL_PACKAGE_NAME + "." + className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (Identifiable.class.isAssignableFrom(c)) {
                break;
            }
        }
    }

    private static Referable getReferable(Key actualKey, List<Object> list) {
        Referable searchedReferable = null;
        for (Object e : list) {
            Referable element = (Referable) e;
            if (actualKey.getIdType() == KeyType.ID_SHORT) {
                if (element.getIdShort().equals(actualKey.getValue())) {
                    searchedReferable = element;
                }
            }
        }
        return searchedReferable;
    }

    private static Identifiable getIdentifiable(Key lastKey, Class c, List<Object> list) {
        if (Identifiable.class.isAssignableFrom(c)) {
            for (Object e : list) {
                Identifiable element = (Identifiable) e;
                if (lastKey.getIdType() == KeyType.ID_SHORT) {
                    if (element.getIdShort().equals(lastKey.getValue())) {
                        return element;
                    }
                } else if (lastKey.getIdType() == KeyType.IRI || lastKey.getIdType() == KeyType.IRDI || lastKey.getIdType() == KeyType.CUSTOM) {
                    if (element.getIdentification().getIdentifier().equals(lastKey.getValue())) {
                        return element;
                    }
                }
            }
        }
        return null;
    }
}
