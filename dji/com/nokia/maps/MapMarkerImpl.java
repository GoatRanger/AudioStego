package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapMarker;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.concurrent.CopyOnWriteArrayList;

@Online
public class MapMarkerImpl extends MapMarkerBase {
    static CopyOnWriteArrayList<MapMarker> a = new CopyOnWriteArrayList();
    private static am<MapMarker, MapMarkerImpl> j = null;
    private String d;
    private String e;
    private boolean f;
    private int g;
    private boolean h;
    private a i;

    public interface a {
        void a(GeoCoordinate geoCoordinate);
    }

    private native void createNative();

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl, ImageImpl imageImpl);

    private native void setCoordinateNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setDecluterringNative(boolean z);

    native GeoCoordinateImpl getCoordinate();

    public native boolean isDeclutteringEnabled();

    static {
        ce.a(MapMarker.class);
    }

    static MapMarker a(MapMarkerImpl mapMarkerImpl) {
        if (mapMarkerImpl != null) {
            return (MapMarker) j.a(mapMarkerImpl);
        }
        return null;
    }

    public static void b(am<MapMarker, MapMarkerImpl> amVar) {
        j = amVar;
    }

    @OnlineNative
    MapMarkerImpl(int i) {
        super(i);
        this.f = false;
        this.g = 0;
        this.h = false;
    }

    public MapMarkerImpl() {
        this(-1.0f);
    }

    public MapMarkerImpl(float f) {
        this.f = false;
        this.g = 0;
        this.h = false;
        createNative();
        c(f);
    }

    public MapMarkerImpl(GeoCoordinate geoCoordinate, Image image) {
        super(image);
        this.f = false;
        this.g = 0;
        this.h = false;
        if (geoCoordinate == null || !geoCoordinate.isValid() || image == null || !image.isValid()) {
            createNative();
            try {
                if (!geoCoordinate.isValid()) {
                    throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
                } else if (!image.isValid()) {
                    throw new IllegalArgumentException("Image provided is invalid.");
                }
            } catch (NullPointerException e) {
                throw e;
            }
        }
        createNative(GeoCoordinateImpl.get(geoCoordinate), ImageImpl.a(image));
    }

    public void a(Image image) {
        super.a(image);
        if (this.f) {
            bd.c();
        } else {
            o();
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate.isValid()) {
            setCoordinateNative(GeoCoordinateImpl.get(geoCoordinate));
            if (this.i != null) {
                this.i.a(geoCoordinate);
            }
            if (this.f) {
                bd.a(geoCoordinate);
                return;
            } else {
                o();
                return;
            }
        }
        throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
    }

    public GeoCoordinate c() {
        return GeoCoordinateImpl.create(getCoordinate());
    }

    public void a(PointF pointF) throws IllegalArgumentException {
        super.a(pointF);
        if (this.f) {
            bd.c();
        } else {
            o();
        }
    }

    public void a(MapMarker mapMarker, boolean z) {
        this.h = z;
        if (this.h) {
            a.addIfAbsent(mapMarker);
        } else {
            a.remove(mapMarker);
        }
    }

    public boolean d() {
        return this.h;
    }

    public boolean d(float f) {
        float b = b();
        boolean b2 = b(f);
        if (b2 && b != b()) {
            if (this.f) {
                MapMarker b3 = bd.b();
                if (b3 != null) {
                    b3.setTransparency(f);
                }
                bd.c();
            } else {
                o();
            }
        }
        return b2;
    }

    public void a(boolean z) {
        if (isDeclutteringEnabled() != z) {
            setDecluterringNative(z);
            if (this.f) {
                MapMarker b = bd.b();
                if (b != null) {
                    b.setDeclutteringEnabled(z);
                }
                bd.c();
                return;
            }
            o();
        }
    }

    public void b(String str) {
        this.d = str;
        if (this.f) {
            g();
        }
    }

    public String e() {
        return this.d;
    }

    public void c(String str) {
        this.e = str;
        if (this.f) {
            g();
        }
    }

    public String f() {
        return this.e;
    }

    public void g() {
        MapImpl p = p();
        if (p == null) {
            return;
        }
        if (this.d != null || p.z() != null) {
            this.g = bd.a(MapsEngine.e(), p, a(this), GeoCoordinateImpl.create(getCoordinate()), this.d, this.e);
            if (this.g != 0) {
                this.f = true;
            }
        }
    }

    public void h() {
        if (this.f) {
            bd.a();
            this.f = false;
        }
        this.g = 0;
    }

    public boolean i() {
        return this.f;
    }

    public int j() {
        return this.g;
    }

    public void a(a aVar) {
        this.i = aVar;
    }
}
