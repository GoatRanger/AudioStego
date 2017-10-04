package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.streetlevel.StreetLevelBillboard;
import com.here.android.mpa.streetlevel.StreetLevelBillboardOrientation;
import com.here.android.mpa.streetlevel.StreetLevelBillboardOrientation.Orientation;
import com.here.android.mpa.streetlevel.StreetLevelObject.Type;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class PanoramaBillboard extends PanoramaIconBase {
    private static String a = PanoramaBillboard.class.getSimpleName();
    private static k<StreetLevelBillboard, PanoramaBillboard> e = null;
    private static am<StreetLevelBillboard, PanoramaBillboard> f = null;
    private cq b = new cq(PanoramaBillboard.class.getName());
    private float c;
    private float d;

    private native void createPanoramaBillboardNative(GeoCoordinateImpl geoCoordinateImpl, ImageImpl imageImpl);

    private native float[] getOrientationNative();

    private native float[] getSize();

    private native boolean setFacadePlacementSizeNative(float f, float f2, float f3, float f4);

    private native boolean setOrientationNative(int i, float f, float f2, float f3, float f4, float f5, float f6);

    private native boolean setSizeNative(float f, float f2);

    static {
        ce.a(StreetLevelBillboard.class);
    }

    public static void a(k<StreetLevelBillboard, PanoramaBillboard> kVar, am<StreetLevelBillboard, PanoramaBillboard> amVar) {
        e = kVar;
        f = amVar;
    }

    @HybridPlusNative
    private PanoramaBillboard(int i) {
        super(i);
    }

    public PanoramaBillboard(GeoCoordinate geoCoordinate, Image image) {
        dy.a((Object) geoCoordinate, "coordinate arguement is null");
        dy.a((Object) image, "image arguement is null");
        dy.a(geoCoordinate.isValid(), "coordinate arguement is invalid");
        dy.a(image.isValid(), "image arguement is invalid");
        createPanoramaBillboardNative(GeoCoordinateImpl.get(geoCoordinate), ImageImpl.a(image));
    }

    public Type a() {
        return Type.BILLBOARD_OBJECT;
    }

    public boolean a(StreetLevelBillboardOrientation streetLevelBillboardOrientation) {
        boolean z = false;
        if (streetLevelBillboardOrientation != null) {
            try {
                int a = cr.a(streetLevelBillboardOrientation.getOrientation());
                z = setOrientationNative(a, streetLevelBillboardOrientation.getNormalVector().getX(), streetLevelBillboardOrientation.getNormalVector().getY(), streetLevelBillboardOrientation.getNormalVector().getZ(), streetLevelBillboardOrientation.getUpVector().getX(), streetLevelBillboardOrientation.getUpVector().getY(), streetLevelBillboardOrientation.getUpVector().getZ());
                if (z) {
                    h();
                }
            } catch (Throwable e) {
                bj.c(a, "Exception \n%s", new Object[]{bj.a(e)});
            }
        }
        return z;
    }

    public StreetLevelBillboardOrientation b() {
        cr crVar;
        float[] orientationNative = getOrientationNative();
        if (orientationNative == null || orientationNative.length != 7) {
            Vector3f vector3f = new Vector3f();
            crVar = new cr(Orientation.BILLBOARD, vector3f, vector3f);
        } else {
            Orientation orientation = Orientation.BILLBOARD;
            try {
                orientation = cr.a((int) orientationNative[0]);
            } catch (Throwable e) {
                bj.c(a, "Exception \n%s", new Object[]{bj.a(e)});
                orientation = Orientation.BILLBOARD;
            }
            crVar = new cr(orientation, new Vector3f(orientationNative[1], orientationNative[2], orientationNative[3]), new Vector3f(orientationNative[4], orientationNative[5], orientationNative[6]));
        }
        return cr.a(crVar);
    }

    public void a(float f, float f2, float f3, float f4) {
        if (setFacadePlacementSizeNative(f, f2, f3, f4)) {
            this.c = f;
            this.d = f2;
            h();
        }
    }

    public boolean a(float f) {
        boolean sizeNative = setSizeNative(f, this.d);
        if (sizeNative) {
            this.c = f;
            h();
        }
        return sizeNative;
    }

    public float c() {
        return this.c;
    }

    public void b(float f) {
        if (setSizeNative(this.c, f)) {
            this.d = f;
            h();
        }
    }

    public float d() {
        return this.d;
    }

    public boolean a(float f, float f2) {
        boolean sizeNative = setSizeNative(f, f2);
        if (sizeNative) {
            this.c = f;
            this.d = f2;
            h();
        }
        return sizeNative;
    }
}
