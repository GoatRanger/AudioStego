package com.google.api.client.util.store;

import com.google.api.client.util.Beta;

@Beta
public final class DataStoreUtils {
    public static String toString(DataStore<?> dataStore) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('{');
            Object obj = 1;
            for (String str : dataStore.keySet()) {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(str).append('=').append(dataStore.get(str));
            }
            return stringBuilder.append('}').toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private DataStoreUtils() {
    }
}
