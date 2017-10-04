package com.here.a.a.a;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends f<JsonObject> {
    protected /* synthetic */ Object d(String str) throws d {
        return c(str);
    }

    public c(String str) {
        super(str);
    }

    protected c(JsonObject jsonObject, f<JsonObject> fVar) {
        super(jsonObject, fVar);
    }

    public Object a(String str) throws e {
        if (((JsonObject) this.b).has(str)) {
            JsonElement jsonElement = ((JsonObject) this.b).get(str);
            if (jsonElement.isJsonObject()) {
                return new c(jsonElement.getAsJsonObject(), this);
            }
            if (jsonElement.isJsonPrimitive()) {
                return a(jsonElement.getAsJsonPrimitive());
            }
            if (jsonElement.isJsonArray()) {
                return b(str);
            }
            if (jsonElement.isJsonNull()) {
                return null;
            }
            return jsonElement.getAsString();
        }
        throw new e(str);
    }

    public Iterable<Object> b(String str) throws e {
        if (((JsonObject) this.b).has(str) && !((JsonObject) this.b).isJsonArray()) {
            return a(((JsonObject) this.b).getAsJsonArray(str));
        }
        throw new e(str);
    }

    protected JsonObject c(String str) throws d {
        return new JsonParser().parse(str).getAsJsonObject();
    }

    private Iterable<Object> a(JsonArray jsonArray) {
        Iterable arrayList = new ArrayList(jsonArray.size());
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) it.next();
            if (jsonElement.isJsonObject()) {
                arrayList.add(new c(jsonElement.getAsJsonObject(), this));
            } else if (jsonElement.isJsonPrimitive()) {
                arrayList.add(a(jsonElement.getAsJsonPrimitive()));
            } else if (jsonElement.isJsonArray()) {
                arrayList.add(a(jsonElement.getAsJsonArray()));
            } else {
                arrayList.add(jsonElement.getAsString());
            }
        }
        return arrayList;
    }

    private Object a(JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isBoolean()) {
            return Boolean.valueOf(jsonPrimitive.getAsBoolean());
        }
        if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        }
        return jsonPrimitive.getAsString();
    }

    public String toString() {
        return ((JsonObject) this.b).toString();
    }
}
