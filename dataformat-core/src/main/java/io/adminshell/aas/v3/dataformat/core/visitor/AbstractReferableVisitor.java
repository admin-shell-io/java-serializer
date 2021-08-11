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
package io.adminshell.aas.v3.dataformat.core.visitor;

import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import java.lang.reflect.Method;

public abstract class AbstractReferableVisitor {

    private static final String METHOD_NAME = "visit";

    public void visit(Object o) {
        Method visitMethod = getMethod(o.getClass());
        if (visitMethod == null) {
            defaultVisit(o);
        } else {
            try {
                visitMethod.invoke(this, new Object[]{o});
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public abstract void visit(Submodel submodel);

    public abstract void visit(SubmodelElement submodelElement);

    public abstract void visit(SubmodelElementCollection submodelElementCollection);

    public abstract void visit(Property property);

    public void defaultVisit(Object o) {

    }

    protected Method getMethod(Class source) {
        Class clazz = source;
        while (!clazz.equals(Object.class)) {
            try {
                return getClass().getMethod(METHOD_NAME, clazz);
            } catch (NoSuchMethodException ex) {
                clazz = clazz.getSuperclass();
            }
        }
        for (Class clsInterface : source.getInterfaces()) {
            try {
                return getClass().getMethod(METHOD_NAME, clsInterface);
            } catch (NoSuchMethodException ex) {
            }
        }
        return null;
    }
}
