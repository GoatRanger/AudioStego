package com.here.android.mpa.mapping.customization;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class SchemeColorProperty {
    String a;

    SchemeColorProperty(String str) {
        this.a = str;
    }

    public String getName() {
        return this.a;
    }

    public String getTypeName() {
        return "Integer";
    }
}
