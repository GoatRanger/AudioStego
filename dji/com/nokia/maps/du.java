package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.dd.a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

public class du {
    public static a a() {
        BaseNativeObject.u();
        a aVar = a.ONLINE;
        try {
            return MapsEngine.c().isOnline() ? a.ONLINE : a.OFFLINE;
        } catch (Exception e) {
            e.printStackTrace();
            return aVar;
        }
    }

    public static String a(GeoBoundingBox geoBoundingBox) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(geoBoundingBox.getTopLeft().getLongitude());
        stringBuilder.append(",");
        stringBuilder.append(geoBoundingBox.getBottomRight().getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(geoBoundingBox.getBottomRight().getLongitude());
        stringBuilder.append(",");
        stringBuilder.append(geoBoundingBox.getTopLeft().getLatitude());
        return stringBuilder.toString();
    }

    static Class<?> a(Object obj) {
        dy.a(obj, "Generic instance is null");
        Type genericSuperclass = obj.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            genericSuperclass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            if (genericSuperclass != null) {
                if (genericSuperclass instanceof Class) {
                    return (Class) genericSuperclass;
                }
                if (genericSuperclass instanceof ParameterizedType) {
                    return (Class) ((ParameterizedType) genericSuperclass).getRawType();
                }
            }
        }
        return null;
    }

    static String b() {
        return a(null);
    }

    static String a(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale.getCountry().isEmpty()) {
            return locale.getLanguage();
        }
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    static String a(List<dj> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (dj djVar : list) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            int a = djVar.a();
            int b = djVar.b();
            if (a > 0) {
                stringBuilder.append("w");
                stringBuilder.append(a);
            }
            if (a > 0 && b > 0) {
                stringBuilder.append("-");
            }
            if (b > 0) {
                stringBuilder.append("h");
                stringBuilder.append(b);
            }
        }
        return stringBuilder.toString();
    }
}
