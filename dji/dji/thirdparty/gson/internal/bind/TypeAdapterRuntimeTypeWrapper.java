package dji.thirdparty.gson.internal.bind;

import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.TypeAdapter;
import dji.thirdparty.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
import dji.thirdparty.gson.reflect.TypeToken;
import dji.thirdparty.gson.stream.JsonReader;
import dji.thirdparty.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.context = gson;
        this.delegate = typeAdapter;
        this.type = type;
    }

    public T read(JsonReader jsonReader) throws IOException {
        return this.delegate.read(jsonReader);
    }

    public void write(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter typeAdapter = this.delegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.type, t);
        if (runtimeTypeIfMoreSpecific != this.type) {
            typeAdapter = this.context.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if ((typeAdapter instanceof Adapter) && !(this.delegate instanceof Adapter)) {
                typeAdapter = this.delegate;
            }
        }
        typeAdapter.write(jsonWriter, t);
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
