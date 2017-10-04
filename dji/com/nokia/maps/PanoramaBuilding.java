package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.streetlevel.StreetLevelBuilding;
import com.here.android.mpa.streetlevel.StreetLevelProxyObject;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class PanoramaBuilding extends ViewObjectImpl {
    private static k<StreetLevelBuilding, PanoramaBuilding> b = null;
    private static am<StreetLevelBuilding, PanoramaBuilding> c = null;
    private cq a = new cq(PanoramaBuilding.class.getName());

    private native long getId();

    private native GeoCoordinateImpl getPositionNative();

    private native boolean setHighlightNative(float f);

    public native float getHighlight();

    static {
        ce.a(StreetLevelBuilding.class);
    }

    public static void a(k<StreetLevelBuilding, PanoramaBuilding> kVar, am<StreetLevelBuilding, PanoramaBuilding> amVar) {
        b = kVar;
        c = amVar;
    }

    @HybridPlusNative
    private PanoramaBuilding(int i) {
        super(i);
    }

    public Type k() {
        return Type.PROXY_OBJECT;
    }

    public StreetLevelProxyObject.Type a() {
        return StreetLevelProxyObject.Type.BUILDING_OBJECT;
    }

    public Identifier b() {
        return IdentifierImpl.a(new IdentifierImpl(a.STRING, String.valueOf(getId())));
    }

    public GeoCoordinate c() {
        return GeoCoordinateImpl.create(getPositionNative());
    }

    public boolean a(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        dy.a(z, "intensity arguement is not [0..1]");
        return setHighlightNative(f);
    }
}
