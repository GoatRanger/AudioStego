package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.ViewObjectImpl;
import com.nokia.maps.annotation.HybridPlus;

public abstract class StreetLevelProxyObject extends ViewObject {

    @HybridPlus
    public enum Type {
        LINK_OBJECT,
        BUILDING_OBJECT,
        TERRAIN_OBJECT,
        UNKNOWN
    }

    @HybridPlus
    public abstract Type getType();

    StreetLevelProxyObject(ViewObjectImpl viewObjectImpl) {
        super(viewObjectImpl);
    }
}
