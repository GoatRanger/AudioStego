package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.Online;

@Online
public class MapMarkerBase extends MapObjectImpl {
    private static byte[] d = null;
    private Image a;

    private native int getTransparencyNative();

    private native void setAnchorPointNative(float f, float f2);

    private native void setIconNative(ImageImpl imageImpl);

    private native boolean setTransparencyNative(int i);

    public native PointF getAnchorPoint();

    public native float getSVGScaleFactor();

    public native boolean setSVGScaleFactor(float f);

    protected native void setSizeNative(float f, float f2);

    protected MapMarkerBase(int i) {
        super(i);
    }

    protected MapMarkerBase() {
    }

    protected MapMarkerBase(Image image) {
        this.a = image;
    }

    protected void a(Image image) {
        if (image.isValid()) {
            setIconNative(ImageImpl.a(image));
            this.a = image;
            return;
        }
        throw new IllegalArgumentException("Image provided is invalid.");
    }

    public Image a() {
        return this.a;
    }

    protected void a(PointF pointF) throws IllegalArgumentException {
        if (pointF == null) {
            throw new NullPointerException("Cannot setAnchorPoint on null PointF object");
        }
        setAnchorPointNative(pointF.x, pointF.y);
    }

    protected boolean b(float f) {
        if (f < 0.0f || f > 1.0f) {
            return false;
        }
        return setTransparencyNative((int) (255.0f * f));
    }

    public float b() {
        return ((float) getTransparencyNative()) / 255.0f;
    }

    private byte[] c() {
        if (d == null) {
            String str = "";
            int i = MapsEngine.e().getResources().getDisplayMetrics().densityDpi;
            switch (i) {
                case 120:
                    str = "default_icon_ldpi.png";
                    break;
                case 160:
                    str = "default_icon_mdpi.png";
                    break;
                case 240:
                    str = "default_icon_hdpi.png";
                    break;
                case 320:
                    str = "default_icon_xhdpi.png";
                    break;
                default:
                    if (i <= 320) {
                        str = "default_icon_mdpi.png";
                        break;
                    }
                    str = "default_icon_xhdpi.png";
                    break;
            }
            d = ResourceManager.a(MapsEngine.e(), "./res/images/" + str);
        }
        return d;
    }

    protected void c(float f) {
        byte[] c = c();
        if (c.length > 0) {
            Image image = new Image();
            if (f < 0.0f || f > 360.0f) {
                image.setImageData(c);
            } else {
                Bitmap copy = BitmapFactory.decodeByteArray(c, 0, c.length).copy(Config.ARGB_8888, true);
                a(copy, f, copy.getWidth(), copy.getHeight());
                image.setBitmap(copy);
            }
            a(image);
        }
    }

    private void a(Bitmap bitmap, float f, int i, int i2) {
        int i3 = i * i2;
        int[] iArr = new int[i3];
        bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        float[] fArr = new float[3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            int alpha = Color.alpha(i5);
            Color.colorToHSV(i5, fArr);
            fArr[0] = f;
            fArr[1] = 1.0f;
            iArr[i4] = Color.HSVToColor(alpha, fArr);
        }
        bitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
    }
}
