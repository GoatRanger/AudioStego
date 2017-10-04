package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.ar.ARModelObject;
import com.here.android.mpa.ar.ARModelObject.ShadingMode;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class ARModelObjectImpl extends BaseNativeObject {
    private static k<ARModelObject, ARModelObjectImpl> a = null;

    private native int getShadingModeNative();

    public native void clearTransformation();

    public native void getDynamicScale(PointF pointF, PointF pointF2);

    public native float getOpacity();

    public native ImageImpl getTextureNative();

    public native float[] getTransformation();

    public native PointF getVisibilityRange();

    public native void rotate(float f, float f2, float f3);

    public native void rotate(Vector3f vector3f, float f);

    public native void scale(float f, float f2, float f3);

    public native void setDynamicScale(PointF pointF, PointF pointF2);

    public native void setOpacity(float f);

    public native void setShadingMode(int i);

    public native void setTextureNative(ImageImpl imageImpl);

    public native void setTransformation(float[] fArr);

    public native void setVisibilityRange(PointF pointF);

    public native void translate(float f, float f2, float f3);

    static {
        ce.a(ARModelObject.class);
    }

    protected ARModelObjectImpl() {
    }

    public void a(Image image) {
        if (image.isValid()) {
            setTextureNative(ImageImpl.a(image));
            return;
        }
        throw new IllegalArgumentException("Image provided is invalid.");
    }

    public Image d() {
        return ImageImpl.a(getTextureNative());
    }

    public ShadingMode e() {
        switch (getShadingModeNative()) {
            case 0:
                return ShadingMode.FLAT_TEXTURED;
            case 1:
                return ShadingMode.DIFFUSE_TEXTURED;
            default:
                return ShadingMode.FLAT_TEXTURED;
        }
    }

    static ARModelObjectImpl a(ARModelObject aRModelObject) {
        return a != null ? (ARModelObjectImpl) a.a(aRModelObject) : null;
    }

    public static void a(k<ARModelObject, ARModelObjectImpl> kVar) {
        a = kVar;
    }
}
