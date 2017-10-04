package com.google.api.client.util.store;

import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.Serializable;

@Beta
public interface DataStoreFactory {
    <V extends Serializable> DataStore<V> getDataStore(String str) throws IOException;
}
