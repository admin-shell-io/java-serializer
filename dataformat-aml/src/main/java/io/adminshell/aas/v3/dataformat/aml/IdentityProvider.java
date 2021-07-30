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
package io.adminshell.aas.v3.dataformat.aml;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IdentityProvider {

    private Map<Object, String> ids = new HashMap<>();

    public String getId(Object obj) {
//        if (ids.containsKey(obj)) {
//            return ids.get(obj);
//        }
        String result = generateId();
//        ids.put(obj, result);
        return result;
    }

    public String getCachedId(Object obj) {
        if (ids.containsKey(obj)) {
            return ids.get(obj);
        }
        String result = generateId();
        ids.put(obj, result);
        return result;
    }

    public String generateId() {
        String result = null;
        do {
            result = UUID.randomUUID().toString();
        } while (ids.entrySet().contains(result));
        return result;
    }
}
