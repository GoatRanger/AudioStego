package com.here.android.mpa.ar;

import com.here.android.mpa.common.GeoPolyline;
import com.nokia.maps.ARPolylineObjectImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public final class ARPolylineObject {
    private ARPolylineObjectImpl a;

    public ARPolylineObject(GeoPolyline geoPolyline) {
        this.a = new ARPolylineObjectImpl(geoPolyline, new LineAttributes());
    }

    public ARPolylineObject(GeoPolyline geoPolyline, LineAttributes lineAttributes) {
        this.a = new ARPolylineObjectImpl(geoPolyline, lineAttributes);
    }

    public GeoPolyline getGeoPolyLine() {
        return this.a.a();
    }

    public void setGeoPolyline(GeoPolyline geoPolyline) {
        this.a.a(geoPolyline);
    }

    public LineAttributes getLineAttributes() {
        return this.a.b();
    }

    public void setLineAttributes(LineAttributes lineAttributes) {
        this.a.a(lineAttributes);
    }

    static {
        ARPolylineObjectImpl.a(new k<ARPolylineObject, ARPolylineObjectImpl>() {
            public ARPolylineObjectImpl a(ARPolylineObject aRPolylineObject) {
                return aRPolylineObject != null ? aRPolylineObject.a : null;
            }
        });
    }
}
