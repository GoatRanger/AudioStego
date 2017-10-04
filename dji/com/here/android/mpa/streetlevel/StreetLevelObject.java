package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.cx;
import com.nokia.maps.k;

public abstract class StreetLevelObject extends ViewObject {
    private cx a;

    @HybridPlus
    public enum Type {
        BILLBOARD_OBJECT,
        ICON_OBJECT,
        ROUTE_OBJECT,
        UNKNOWN
    }

    @HybridPlus
    public com.here.android.mpa.common.ViewObject.Type getBaseType() {
        return this.a.k();
    }

    @HybridPlus
    public Type getType() {
        return this.a.a();
    }

    StreetLevelObject(cx cxVar) {
        super(cxVar);
        this.a = cxVar;
    }

    static {
        cx.a(new k<StreetLevelObject, cx>() {
            public cx a(StreetLevelObject streetLevelObject) {
                return streetLevelObject.a;
            }
        });
    }
}
