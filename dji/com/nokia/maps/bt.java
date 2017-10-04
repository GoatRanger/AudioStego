package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewParent;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapOverlay;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class bt {
    private static k<MapOverlay, bt> f = null;
    private View a;
    private MapMarker b;
    private Image c;
    private GeoCoordinate d;
    private PointF e;

    static bt a(MapOverlay mapOverlay) {
        if (f != null) {
            return (bt) f.a(mapOverlay);
        }
        return null;
    }

    public static void a(k<MapOverlay, bt> kVar) {
        f = kVar;
    }

    public bt(View view, GeoCoordinate geoCoordinate) {
        if (view == null) {
            throw new NullPointerException("Cannot accept null View reference.");
        } else if (geoCoordinate == null) {
            throw new NullPointerException("Cannot accept null GeoCoordinate reference.");
        } else if (geoCoordinate.isValid()) {
            this.a = view;
            this.d = geoCoordinate;
            this.c = new Image();
            this.b = new MapMarker();
            this.b.setCoordinate(geoCoordinate);
            view.setDrawingCacheEnabled(true);
        } else {
            throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
        }
    }

    public PointF a() {
        return this.e;
    }

    public void a(PointF pointF) {
        boolean equals = this.e == null ? pointF == null : this.e.equals(pointF);
        if (!equals) {
            this.e = pointF;
            this.b.setAnchorPoint(pointF);
            f();
        }
    }

    public View b() {
        return this.a;
    }

    public GeoCoordinate c() {
        return this.d;
    }

    public void a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate == null) {
            throw new NullPointerException("Cannot accept null GeoCoordinate reference.");
        } else if (!geoCoordinate.isValid()) {
            throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
        } else if (!this.d.equals(geoCoordinate)) {
            this.d = geoCoordinate;
            this.b.setCoordinate(this.d);
            f();
        }
    }

    MapMarker d() {
        return this.b;
    }

    void e() {
        Bitmap drawingCache = this.a.getDrawingCache();
        if (drawingCache != null) {
            this.c.setBitmap(drawingCache);
            this.b.setIcon(this.c);
        }
    }

    private void f() {
        ViewParent parent = this.a.getParent();
        if (parent != null) {
            parent.requestLayout();
        }
    }
}
