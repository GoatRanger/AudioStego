package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import com.here.android.mpa.ar.ARObject;
import com.here.android.mpa.ar.ARObject.IconType;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ar.a;

@HybridPlus
public class ARObjectImpl extends BaseNativeObject {
    private static k<ARObject, ARObjectImpl> e = null;
    c a;
    c b;
    c c;
    private ARObject d = null;

    private native void createNative();

    private native void destroyNative();

    public native RectF getBoundingBox();

    public native GeoCoordinateImpl getCoordinate();

    public native ImageImpl getIcon(int i);

    public native PointF getIconAnchor(int i);

    public native float getIconSizeScale(int i);

    public native int getInfoMaxHeight();

    public native int getInfoMaxWidth();

    public native float getMaxViewAngle();

    public native float getOpacity();

    public native int getProjectionType();

    public native long getUid();

    public native boolean isInfoExtended();

    public native void setBoundingBox(RectF rectF);

    public native void setCoordinate(GeoCoordinateImpl geoCoordinateImpl);

    public native void setIcon(int i, ImageImpl imageImpl);

    public native void setIconAnchor(int i, PointF pointF);

    public native void setIconSizeScale(int i, float f);

    public native void setIconTexture(int i, int i2, int i3, int i4);

    public native void setInfoMaxHeight(int i);

    public native void setInfoMaxWidth(int i);

    public native void setMaxViewAngle(float f);

    public native void setOpacity(float f);

    public native void setProjectionType(int i);

    public native void setStartStopSizeOnMap(int i, int i2);

    public ARObjectImpl() {
        createNative();
        b();
    }

    public ARObjectImpl(Image image) {
        createNative();
        if (image == null || !image.isValid()) {
            b();
            return;
        }
        setIcon(IconType.FRONT.ordinal(), ImageImpl.a(image));
        setIcon(IconType.DOWN.ordinal(), ImageImpl.a(image));
        setIcon(IconType.BACK.ordinal(), ImageImpl.a(image));
    }

    public ARObjectImpl(int i) {
        createNative();
        Image image = new Image();
        if (i == 0) {
            b();
            return;
        }
        try {
            image.setImageResource(i);
            setIcon(IconType.FRONT.ordinal(), ImageImpl.a(image));
            setIcon(IconType.DOWN.ordinal(), ImageImpl.a(image));
            setIcon(IconType.BACK.ordinal(), ImageImpl.a(image));
        } catch (Exception e) {
            b();
        }
    }

    public ARObjectImpl(Image image, Image image2, Image image3) {
        createNative();
        if (image == null || !image.isValid()) {
            c();
        } else {
            setIcon(IconType.FRONT.ordinal(), ImageImpl.a(image));
        }
        if (image2 == null || !image2.isValid()) {
            d();
        } else {
            setIcon(IconType.DOWN.ordinal(), ImageImpl.a(image2));
        }
        if (image3 == null || !image3.isValid()) {
            e();
        } else {
            setIcon(IconType.BACK.ordinal(), ImageImpl.a(image3));
        }
    }

    public ARObjectImpl(int i, int i2, int i3) {
        createNative();
        Image image = new Image();
        if (i == 0) {
            c();
        } else {
            try {
                image.setImageResource(i);
                setIcon(IconType.FRONT.ordinal(), ImageImpl.a(image));
            } catch (Exception e) {
                c();
            }
        }
        image = new Image();
        if (i2 == 0) {
            d();
        } else {
            try {
                image.setImageResource(i2);
                setIcon(IconType.DOWN.ordinal(), ImageImpl.a(image));
            } catch (Exception e2) {
                d();
            }
        }
        image = new Image();
        if (i3 == 0) {
            e();
            return;
        }
        try {
            image.setImageResource(i3);
            setIcon(IconType.BACK.ordinal(), ImageImpl.a(image));
        } catch (Exception e3) {
            e();
        }
    }

    void a(ARObject aRObject) {
        this.d = aRObject;
    }

    public void a(GeoCoordinate geoCoordinate) {
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCoordinate());
    }

    public void a(IconType iconType, Image image) {
        setIcon(iconType.ordinal(), ImageImpl.a(image));
    }

    public void a(IconType iconType, View view) {
        setIcon(iconType.ordinal(), ImageImpl.a(bc.a(view)));
    }

    public void a(IconType iconType, Bitmap bitmap) {
        setIcon(iconType.ordinal(), ImageImpl.a(bc.a(bitmap)));
    }

    public void a(IconType iconType, int i, int i2, int i3) {
        setIconTexture(iconType.ordinal(), i, i2, i3);
    }

    public Image a(IconType iconType) {
        return ImageImpl.a(getIcon(iconType.ordinal()));
    }

    void a(String str, a aVar) {
        if (this.a == null) {
            this.a = new c();
            this.a.a.a(aVar);
        } else {
            this.a.b();
        }
        this.a.b(str);
    }

    void b(String str, a aVar) {
        if (this.b == null) {
            this.b = new c();
            this.b.a.a(aVar);
        } else {
            this.b.b();
        }
        this.b.b(str);
    }

    void c(String str, a aVar) {
        if (this.c == null) {
            this.c = new c();
            this.c.a.a(aVar);
        } else {
            this.c.b();
        }
        this.c.b(str);
    }

    private void b() {
        c();
        d();
        e();
    }

    private void c() {
        ImageImpl a = ImageImpl.a(new Image());
        a.a(d.a(), d.d(), d.e());
        setIcon(IconType.FRONT.ordinal(), a);
    }

    private void d() {
        ImageImpl a = ImageImpl.a(new Image());
        a.a(d.b(), d.f(), d.g());
        setIcon(IconType.DOWN.ordinal(), a);
    }

    private void e() {
        ImageImpl a = ImageImpl.a(new Image());
        a.a(d.c(), d.h(), d.i());
        setIcon(IconType.BACK.ordinal(), a);
    }

    public static ARObjectImpl b(ARObject aRObject) {
        return e != null ? (ARObjectImpl) e.a(aRObject) : null;
    }

    public static void a(k<ARObject, ARObjectImpl> kVar) {
        e = kVar;
    }

    protected void finalize() {
        if (this.a != null) {
            this.a.b();
        }
        if (this.b != null) {
            this.b.b();
        }
        if (this.c != null) {
            this.c.b();
        }
        destroyNative();
    }
}
