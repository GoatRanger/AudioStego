package com.dji.frame.c;

import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.GsonBuilder;
import dji.thirdparty.gson.JsonParser;
import dji.thirdparty.gson.reflect.TypeToken;
import java.util.List;

public class h {
    public static Gson a;

    public static synchronized Gson a() {
        Gson gson;
        synchronized (h.class) {
            if (a == null) {
                a = new Gson();
            }
            gson = a;
        }
        return gson;
    }

    @Deprecated
    public static <T> T a(String str, Class<T> cls) {
        return b(str, cls);
    }

    public static <T> T b(String str, Class<T> cls) {
        T t = null;
        try {
            t = a().fromJson(str, cls);
        } catch (Exception e) {
        }
        return t;
    }

    public static <T> String a(T t) {
        return a().toJson(t);
    }

    public static <T> String a(List<T> list) {
        return a().toJson(list);
    }

    public static <T> List<T> a(String str, TypeToken<List<T>> typeToken) {
        return (List) a().fromJson(str, typeToken.getType());
    }

    public static String a(String str) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(str));
    }
}
