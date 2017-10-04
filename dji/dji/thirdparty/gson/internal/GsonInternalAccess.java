package dji.thirdparty.gson.internal;

import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.TypeAdapterFactory;
import dji.thirdparty.gson.reflect.TypeToken;

public abstract class GsonInternalAccess {
    public static GsonInternalAccess INSTANCE;

    public abstract <T> TypeAdapter<T> getNextAdapter(Gson gson, TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken);
}
