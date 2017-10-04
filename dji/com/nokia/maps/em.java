package com.nokia.maps;

import java.util.Collection;

public class em {
    static String a(String str) {
        return str != null ? str : "";
    }

    static String a(Collection<? extends Object> collection, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (collection != null) {
            int i = 0;
            for (Object append : collection) {
                stringBuilder.append(append);
                if (i < collection.size() - 1) {
                    stringBuilder.append(str);
                }
                i++;
            }
        }
        return stringBuilder.toString();
    }
}
