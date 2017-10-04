package com.nokia.maps;

import com.here.android.mpa.customlocation2.CLE2Geometry;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.Map;

@HybridPlus
public class CLE2GeometryImpl extends BaseNativeObject {
    private static k<CLE2Geometry, CLE2GeometryImpl> a;
    private static am<CLE2Geometry, CLE2GeometryImpl> b;

    private native Map<String, String> getAttributesNative();

    public native double getDistanceNative();

    public native String getGeometryNative();

    public static void a(k<CLE2Geometry, CLE2GeometryImpl> kVar, am<CLE2Geometry, CLE2GeometryImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static {
        ce.a(CLE2Geometry.class);
    }

    @HybridPlusNative
    public CLE2GeometryImpl(int i) {
        this.nativeptr = i;
    }

    public Map<String, String> a() {
        return getAttributesNative();
    }
}
