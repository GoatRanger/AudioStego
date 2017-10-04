package com.here.android.mpa.mapping.customization;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class SchemeFloatProperty {
    String a;

    SchemeFloatProperty(String str) {
        this.a = str;
    }

    public String getName() {
        return this.a;
    }

    public String getTypeName() {
        return "Float";
    }
}
