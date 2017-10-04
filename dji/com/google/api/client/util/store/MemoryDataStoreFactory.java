package com.google.api.client.util.store;

import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.Serializable;

@Beta
public class MemoryDataStoreFactory extends AbstractDataStoreFactory {

    @Beta
    static class InstanceHolder {
        static final MemoryDataStoreFactory INSTANCE = new MemoryDataStoreFactory();

        InstanceHolder() {
        }
    }

    @Beta
    static class MemoryDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        MemoryDataStore(MemoryDataStoreFactory memoryDataStoreFactory, String str) {
            super(memoryDataStoreFactory, str);
        }

        public MemoryDataStoreFactory getDataStoreFactory() {
            return (MemoryDataStoreFactory) super.getDataStoreFactory();
        }
    }

    protected <V extends Serializable> DataStore<V> createDataStore(String str) throws IOException {
        return new MemoryDataStore(this, str);
    }

    public static MemoryDataStoreFactory getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }
}
