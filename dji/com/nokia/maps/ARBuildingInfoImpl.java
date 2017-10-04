package com.nokia.maps;

import android.annotation.TargetApi;
import com.here.android.mpa.ar.ARBuildingInfo;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.mapping.LocalMesh;
import com.here.android.mpa.mapping.Location;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@TargetApi(14)
@HybridPlus
public class ARBuildingInfoImpl extends BaseNativeObject {
    private static am<ARBuildingInfo, ARBuildingInfoImpl> a = null;
    private static k<ARBuildingInfo, ARBuildingInfoImpl> b = null;

    private native void destroyNative();

    private native IdentifierImpl getIdentifierNative();

    private native LocationImpl getLocationNative();

    private native LocalMeshImpl getMeshNative();

    private native GeoCoordinateImpl getMeshOriginNative();

    private native GeoCoordinateImpl getPositionNative();

    private native GeoCoordinateImpl getSelectedFacadeNormalOriginNative();

    public native float getHeight();

    public native String getLROId();

    public native String getPlaceName();

    public native Vector3f getSelectedFacadeNormalNative();

    static {
        ce.a(ARBuildingInfo.class);
    }

    public static void a(k<ARBuildingInfo, ARBuildingInfoImpl> kVar, am<ARBuildingInfo, ARBuildingInfoImpl> amVar) {
        b = kVar;
        a = amVar;
    }

    @HybridPlusNative
    ARBuildingInfoImpl(int i) {
        this.nativeptr = i;
    }

    public Identifier a() {
        return IdentifierImpl.a(getIdentifierNative());
    }

    public GeoCoordinate b() {
        return GeoCoordinateImpl.create(getPositionNative());
    }

    public Location c() {
        return LocationImpl.a(getLocationNative());
    }

    public GeoCoordinate d() {
        return GeoCoordinateImpl.create(getSelectedFacadeNormalOriginNative());
    }

    public GeoCoordinate e() {
        return GeoCoordinateImpl.create(getMeshOriginNative());
    }

    public LocalMesh f() {
        return LocalMeshImpl.a(getMeshNative());
    }

    protected void finalize() {
        destroyNative();
    }
}
