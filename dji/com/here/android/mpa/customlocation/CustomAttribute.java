package com.here.android.mpa.customlocation;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class CustomAttribute {
    private String a;
    private String b;

    public CustomAttribute(String str, String str2) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty");
        }
        this.a = str;
        this.b = str2;
    }

    public String getField() {
        return this.a;
    }

    public String getValue() {
        return this.b;
    }
}
