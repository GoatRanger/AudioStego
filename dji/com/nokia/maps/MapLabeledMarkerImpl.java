package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.IconCategory;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapLabeledMarker;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapLabeledMarkerImpl extends MapMarkerImpl {
    private static am<MapLabeledMarker, MapLabeledMarkerImpl> d = null;

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl, ImageImpl imageImpl);

    private native String getLabelTextNative(String str);

    private native boolean setFontScalingFactorNative(float f);

    private native boolean setLabelTextNative(String str, String str2);

    public native float getFontScalingFactor();

    public native boolean isFadingAnimationEnabled();

    public native boolean isOverlappingEnabled();

    public native boolean setFadingAnimationEnabled(boolean z);

    public native boolean setIconNative(int i);

    public native boolean setOverlappingEnabled(boolean z);

    static {
        ce.a(MapLabeledMarker.class);
    }

    public static void a(am<MapLabeledMarker, MapLabeledMarkerImpl> amVar) {
        d = amVar;
    }

    @OnlineNative
    private MapLabeledMarkerImpl(int i) {
        super(i);
    }

    public MapLabeledMarkerImpl(GeoCoordinate geoCoordinate) {
        super(0);
        if (geoCoordinate == null || !geoCoordinate.isValid()) {
            createNative(new GeoCoordinateImpl());
            try {
                if (!geoCoordinate.isValid()) {
                    throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
                }
            } catch (NullPointerException e) {
                throw e;
            }
        }
        byte[] a = ResourceManager.a(MapsEngine.e(), "./res/images/default_maplabeledmarker.png");
        ImageImpl imageImpl = new ImageImpl();
        imageImpl.a(a);
        createNative(GeoCoordinateImpl.get(geoCoordinate), imageImpl);
    }

    public MapLabeledMarkerImpl(GeoCoordinate geoCoordinate, Image image) {
        super(0);
        if (geoCoordinate == null || !geoCoordinate.isValid() || image == null || !image.isValid()) {
            createNative(new GeoCoordinateImpl());
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

    public void a(String str, String str2) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Marc code provided is invalid");
        }
        if (str2 == null) {
            str2 = "";
        }
        if (setLabelTextNative(str, str2)) {
            o();
        }
    }

    public String a(String str) {
        if (!str.isEmpty()) {
            return getLabelTextNative(str);
        }
        throw new IllegalArgumentException("Marc code provided is invalid");
    }

    public void a(IconCategory iconCategory) {
        if (setIconNative(bb.a(iconCategory))) {
            o();
        }
    }

    public boolean a(float f) {
        if (f > 2.0f || f < 1.0f) {
            throw new IllegalArgumentException("Scaling factor has to be between 1.0 and 2.0.");
        }
        boolean fontScalingFactorNative = setFontScalingFactorNative(f);
        if (fontScalingFactorNative) {
            o();
        }
        return fontScalingFactorNative;
    }
}
