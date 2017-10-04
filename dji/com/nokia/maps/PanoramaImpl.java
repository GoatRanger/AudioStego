package com.nokia.maps;

import android.util.Log;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.streetlevel.StreetLevel;
import com.here.android.mpa.streetlevel.StreetLevelBuilding;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;
import java.util.List;

@HybridPlus
public class PanoramaImpl extends BaseNativeObject {
    private static k<StreetLevel, PanoramaImpl> d = null;
    private static am<StreetLevel, PanoramaImpl> e = null;
    public boolean a = false;
    WeakReference<PanoramaModelImpl> b;
    private cq c = new cq(PanoramaImpl.class.getName());
    private int f = -1;

    private native void createPanoramaNative();

    private static native void destroyPanoramaNative(int i);

    private native boolean isDownloadedNative();

    native long getId();

    native GeoCoordinateImpl getPosition();

    public native List<StreetLevelBuilding> getVisibleBuildings();

    native boolean isPositionAvailable();

    native boolean isValid();

    static {
        ce.a(StreetLevel.class);
    }

    public static void a(k<StreetLevel, PanoramaImpl> kVar, am<StreetLevel, PanoramaImpl> amVar) {
        d = kVar;
        e = amVar;
    }

    static PanoramaImpl a(StreetLevel streetLevel) {
        return (PanoramaImpl) d.a(streetLevel);
    }

    static StreetLevel a(PanoramaImpl panoramaImpl, PanoramaModelImpl panoramaModelImpl) {
        if (panoramaImpl == null) {
            return null;
        }
        panoramaImpl.a(panoramaModelImpl);
        return (StreetLevel) e.a(panoramaImpl);
    }

    @HybridPlusNative
    private PanoramaImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        final int i = this.nativeptr;
        if (this.b != null) {
            PanoramaModelImpl panoramaModelImpl = (PanoramaModelImpl) this.b.get();
            if (panoramaModelImpl != null) {
                panoramaModelImpl.a(new Runnable(this) {
                    final /* synthetic */ PanoramaImpl b;

                    public void run() {
                        PanoramaImpl.destroyPanoramaNative(i);
                    }
                });
            }
        } else if (this.a) {
            destroyPanoramaNative(this.nativeptr);
        } else {
            Log.wtf("Panorama", "BAD PROGRAMMING ERROR. Make sure to setModel + panoramaImpl");
        }
        this.nativeptr = 0;
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getPosition());
    }

    public boolean b() {
        if (this.f != 1) {
            if (isDownloadedNative()) {
                this.f = 1;
            } else {
                this.f = 0;
            }
        }
        if (this.f == 1) {
            return true;
        }
        return false;
    }

    void a(PanoramaModelImpl panoramaModelImpl) {
        this.b = new WeakReference(panoramaModelImpl);
    }

    public void c() {
        this.a = true;
    }
}
