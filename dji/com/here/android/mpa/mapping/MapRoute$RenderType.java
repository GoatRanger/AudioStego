package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;

@Online
public enum MapRoute$RenderType {
    PRIMARY(1),
    SECONDARY(2),
    TRAVELED(3),
    CUSTOM(1);
    
    private int a;

    private MapRoute$RenderType(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }

    public void setValue(int i) {
        this.a = i;
    }
}
