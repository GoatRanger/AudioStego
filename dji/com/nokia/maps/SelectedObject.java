package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.streetlevel.StreetLevelSelectedObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class SelectedObject extends BaseNativeObject {
    private static k<StreetLevelSelectedObject, SelectedObject> a = null;
    private static am<StreetLevelSelectedObject, SelectedObject> b = null;
    private ViewObject c;

    private native void destroySelectedObjectNative();

    private native float[] getNormalNative();

    private native GeoCoordinateImpl getPositionNative();

    static {
        ce.a(StreetLevelSelectedObject.class);
    }

    public static void a(k<StreetLevelSelectedObject, SelectedObject> kVar, am<StreetLevelSelectedObject, SelectedObject> amVar) {
        a = kVar;
        b = amVar;
    }

    static StreetLevelSelectedObject a(SelectedObject selectedObject, PanoramaModelImpl panoramaModelImpl) {
        if (selectedObject == null) {
            return null;
        }
        selectedObject.a(panoramaModelImpl);
        return (StreetLevelSelectedObject) b.a(selectedObject);
    }

    @HybridPlusNative
    private SelectedObject(ViewObject viewObject, int i) {
        this.c = viewObject;
        this.nativeptr = i;
    }

    private SelectedObject() {
        this.nativeptr = 0;
        this.c = null;
    }

    protected void finalize() {
        destroySelectedObjectNative();
    }

    public ViewObject a() {
        return this.c;
    }

    public GeoCoordinate b() {
        return GeoCoordinateImpl.create(getPositionNative());
    }

    public Vector3f c() {
        float[] normalNative = getNormalNative();
        return new Vector3f(normalNative[0], normalNative[1], normalNative[2]);
    }

    public void a(PanoramaModelImpl panoramaModelImpl) {
        if (this.c != null && (ViewObjectImpl.a(this.c) instanceof PanoramaLink)) {
            ((PanoramaLink) ViewObjectImpl.a(this.c)).a(panoramaModelImpl);
        }
    }
}
