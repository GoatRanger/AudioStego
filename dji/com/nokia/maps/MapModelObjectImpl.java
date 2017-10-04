package com.nokia.maps;

import com.here.android.mpa.mapping.MapModelObject;
import com.nokia.maps.annotation.Online;

@Online
public class MapModelObjectImpl extends MapObjectImpl {
    public native boolean addDirectionalLight(double d, double d2, double d3);

    public native int getNumberLightsSupported();

    public native int[] getPhongMaterial();

    public native boolean removeAllLightsNative();

    public native boolean setDirectionalLightNative(int i, double d, double d2, double d3);

    public native boolean setPhongMaterialNative(int i, int i2);

    static {
        ce.a(MapModelObject.class);
    }

    protected MapModelObjectImpl() {
    }

    protected MapModelObjectImpl(int i) {
        super(i);
    }

    public boolean a(int i, int i2) {
        boolean phongMaterialNative = setPhongMaterialNative(i, i2);
        if (phongMaterialNative) {
            o();
        }
        return phongMaterialNative;
    }

    public boolean a(int i, double d, double d2, double d3) {
        boolean directionalLightNative = setDirectionalLightNative(i, d, d2, d3);
        if (directionalLightNative) {
            o();
        }
        return directionalLightNative;
    }

    public boolean d() {
        boolean removeAllLightsNative = removeAllLightsNative();
        if (removeAllLightsNative) {
            o();
        }
        return removeAllLightsNative;
    }
}
