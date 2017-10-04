package com.ut.mini.internal;

import com.ut.mini.UTHitBuilders.UTHitBuilder;
import com.ut.mini.d.n;
import java.util.Map;

public class UTOriginalCustomHitBuilder extends UTHitBuilder {
    public UTOriginalCustomHitBuilder(String str, int i, String str2, String str3, String str4, Map<String, String> map) {
        if (!n.a(str)) {
            super.setProperty(UTHitBuilder.FIELD_PAGE, str);
        }
        super.setProperty(UTHitBuilder.FIELD_EVENT_ID, "" + i);
        if (!n.a(str2)) {
            super.setProperty(UTHitBuilder.FIELD_ARG1, str2);
        }
        if (!n.a(str3)) {
            super.setProperty(UTHitBuilder.FIELD_ARG2, str3);
        }
        if (!n.a(str4)) {
            super.setProperty(UTHitBuilder.FIELD_ARG3, str4);
        }
        super.setProperties(map);
    }
}
