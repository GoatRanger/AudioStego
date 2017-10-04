package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.view.View;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.f;

@TargetApi(14)
@HybridPlus
public final class ARIconObject extends ARObject {
    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap) {
        super(new f(geoCoordinate, bitmap));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap, Image image) {
        super(new f(geoCoordinate, bitmap, image));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap, int i) {
        super(new f(geoCoordinate, bitmap, i));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap, Image image, Image image2, Image image3) {
        super(new f(geoCoordinate, bitmap, image, image2, image3));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap, int i, int i2, int i3) {
        super(new f(geoCoordinate, bitmap, i, i2, i3));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, Bitmap bitmap, String str, String str2, String str3) {
        super(new f(geoCoordinate, bitmap, str, str2, str3));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view) {
        super(new f(geoCoordinate, view));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view, Image image) {
        super(new f(geoCoordinate, view, image));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view, int i) {
        super(new f(geoCoordinate, view, i));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view, Image image, Image image2, Image image3) {
        super(new f(geoCoordinate, view, image, image2, image3));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view, int i, int i2, int i3) {
        super(new f(geoCoordinate, view, i, i2, i3));
    }

    public ARIconObject(GeoCoordinate geoCoordinate, View view, String str, String str2, String str3) {
        super(new f(geoCoordinate, view, str, str2, str3));
    }
}
