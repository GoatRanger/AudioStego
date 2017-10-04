package dji.thirdparty.gson;

import dji.thirdparty.gson.reflect.TypeToken;

public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
