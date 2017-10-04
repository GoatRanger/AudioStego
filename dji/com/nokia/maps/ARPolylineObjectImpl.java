package com.nokia.maps;

import com.here.android.mpa.ar.ARPolylineObject;
import com.here.android.mpa.ar.LineAttributes;
import com.here.android.mpa.common.GeoPolyline;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class ARPolylineObjectImpl extends BaseNativeObject {
    private static k<ARPolylineObject, ARPolylineObjectImpl> a = null;

    private native void createNative(GeoPolylineImpl geoPolylineImpl, LineAttributesImpl lineAttributesImpl);

    private native void destroyNative();

    private native GeoPolylineImpl getNativeGeoPolyLine();

    private native LineAttributesImpl getNativeLineAttributes();

    private native void setGeoPolylineNative(GeoPolylineImpl geoPolylineImpl);

    private native void setLineAttributesNative(LineAttributesImpl lineAttributesImpl);

    @OnlineNative
    public ARPolylineObjectImpl(int i) {
        this.nativeptr = i;
    }

    public ARPolylineObjectImpl(GeoPolyline geoPolyline, LineAttributes lineAttributes) {
        dy.a((Object) geoPolyline, "GeoPolyline cannot be null");
        dy.a((Object) lineAttributes, "LineAttributes cannot be null");
        createNative(GeoPolylineImpl.a(geoPolyline), LineAttributesImpl.a(lineAttributes));
    }

    public GeoPolyline a() {
        GeoPolylineImpl nativeGeoPolyLine = getNativeGeoPolyLine();
        if (nativeGeoPolyLine != null) {
            return GeoPolylineImpl.a(nativeGeoPolyLine);
        }
        return null;
    }

    public LineAttributes b() {
        LineAttributesImpl nativeLineAttributes = getNativeLineAttributes();
        if (nativeLineAttributes != null) {
            return LineAttributesImpl.a(nativeLineAttributes);
        }
        return null;
    }

    public void a(GeoPolyline geoPolyline) {
        dy.a((Object) geoPolyline, "GeoPolyline cannot be null");
        setGeoPolylineNative(GeoPolylineImpl.a(geoPolyline));
    }

    public void a(LineAttributes lineAttributes) {
        dy.a((Object) lineAttributes, "LineAttributes cannot be null");
        setLineAttributesNative(LineAttributesImpl.a(lineAttributes));
    }

    public static ARPolylineObjectImpl a(ARPolylineObject aRPolylineObject) {
        return a != null ? (ARPolylineObjectImpl) a.a(aRPolylineObject) : null;
    }

    public static void a(k<ARPolylineObject, ARPolylineObjectImpl> kVar) {
        a = kVar;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }
}
