package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.streetlevel.StreetLevelIcon;
import com.here.android.mpa.streetlevel.StreetLevelIconSize;
import com.here.android.mpa.streetlevel.StreetLevelIconSize.ScalePolicy;
import com.here.android.mpa.streetlevel.StreetLevelObject.Type;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class PanoramaIcon extends PanoramaIconBase {
    private static k<StreetLevelIcon, PanoramaIcon> b = null;
    private static am<StreetLevelIcon, PanoramaIcon> c = null;
    private cq a = new cq(PanoramaIcon.class.getSimpleName());

    private native void createPanoramaIconNative(GeoCoordinateImpl geoCoordinateImpl, ImageImpl imageImpl);

    private native float[] getSizeNative();

    private native boolean setSizeNative(int i, int i2, int i3, float f, float f2, float f3, float f4);

    static {
        ce.a(StreetLevelIcon.class);
    }

    public static void a(k<StreetLevelIcon, PanoramaIcon> kVar, am<StreetLevelIcon, PanoramaIcon> amVar) {
        b = kVar;
        c = amVar;
    }

    @HybridPlusNative
    private PanoramaIcon(int i) {
        super(i);
    }

    public PanoramaIcon(GeoCoordinate geoCoordinate, Image image) {
        dy.a((Object) geoCoordinate, "position argument is null");
        dy.a((Object) image, "image argument is null");
        dy.a(geoCoordinate.isValid(), "position is invalid");
        dy.a(image.isValid(), "image is invalid");
        createPanoramaIconNative(GeoCoordinateImpl.get(geoCoordinate), ImageImpl.a(image));
    }

    public Type a() {
        return Type.ICON_OBJECT;
    }

    public void a(StreetLevelIconSize streetLevelIconSize) {
        dy.a((Object) streetLevelIconSize, "size argument is null");
        if (setSizeNative(streetLevelIconSize.getWidth(), streetLevelIconSize.getHeight(), streetLevelIconSize.getScalePolicy().ordinal(), streetLevelIconSize.getNearScale(), streetLevelIconSize.getNearDistance(), streetLevelIconSize.getFarScale(), streetLevelIconSize.getFarDistance())) {
            h();
        }
    }

    public StreetLevelIconSize b() {
        cw cwVar = new cw(0, 0);
        float[] sizeNative = getSizeNative();
        if (!(sizeNative == null || cwVar == null || sizeNative.length != 7)) {
            cwVar.a((int) sizeNative[0]);
            cwVar.b((int) sizeNative[1]);
            cwVar.a(ScalePolicy.values()[(int) sizeNative[2]]);
            cwVar.a(sizeNative[3]);
            cwVar.b(sizeNative[4]);
            cwVar.c(sizeNative[5]);
            cwVar.d(sizeNative[6]);
        }
        return cw.a(cwVar);
    }
}
