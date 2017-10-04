package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.graphics.PointF;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.ARModelObjectImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@TargetApi(14)
@HybridPlus
public abstract class ARModelObject {
    protected ARModelObjectImpl m_basePimpl;

    @HybridPlus
    public enum ShadingMode {
        FLAT_TEXTURED,
        DIFFUSE_TEXTURED
    }

    protected ARModelObject(ARModelObjectImpl aRModelObjectImpl) {
        this.m_basePimpl = aRModelObjectImpl;
    }

    public void setOpacity(float f) {
        this.m_basePimpl.setOpacity(f);
    }

    public float getOpacity() {
        return this.m_basePimpl.getOpacity();
    }

    public void setVisibilityRange(PointF pointF) {
        this.m_basePimpl.setVisibilityRange(pointF);
    }

    public PointF getVisibilityRange() {
        return this.m_basePimpl.getVisibilityRange();
    }

    public void setDynamicScale(PointF pointF, PointF pointF2) {
        this.m_basePimpl.setDynamicScale(pointF, pointF2);
    }

    public void getDynamicScale(PointF pointF, PointF pointF2) {
        this.m_basePimpl.getDynamicScale(pointF, pointF2);
    }

    public void setTexture(Image image) {
        this.m_basePimpl.a(image);
    }

    public Image getTexture() {
        return this.m_basePimpl.d();
    }

    public void setShadingMode(ShadingMode shadingMode) {
        this.m_basePimpl.setShadingMode(shadingMode.ordinal());
    }

    public ShadingMode getShadingMode() {
        return this.m_basePimpl.e();
    }

    public void setTransformation(float[] fArr) {
        this.m_basePimpl.setTransformation(fArr);
    }

    public float[] getTransformation() {
        return this.m_basePimpl.getTransformation();
    }

    public void clearTransformation() {
        this.m_basePimpl.clearTransformation();
    }

    public void scale(float f, float f2, float f3) {
        this.m_basePimpl.scale(f, f2, f3);
    }

    public void rotate(Vector3f vector3f, float f) {
        this.m_basePimpl.rotate(vector3f, f);
    }

    public void rotate(float f, float f2, float f3) {
        this.m_basePimpl.rotate(f, f2, f3);
    }

    public void translate(float f, float f2, float f3) {
        this.m_basePimpl.translate(f, f2, f3);
    }

    static {
        ARModelObjectImpl.a(new k<ARModelObject, ARModelObjectImpl>() {
            public ARModelObjectImpl a(ARModelObject aRModelObject) {
                return aRModelObject != null ? aRModelObject.m_basePimpl : null;
            }
        });
    }

    public int hashCode() {
        return (this.m_basePimpl == null ? 0 : this.m_basePimpl.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ARModelObject aRModelObject = (ARModelObject) obj;
        if (this.m_basePimpl == null) {
            if (aRModelObject.m_basePimpl != null) {
                return false;
            }
            return true;
        } else if (this.m_basePimpl.equals(aRModelObject.m_basePimpl)) {
            return true;
        } else {
            return false;
        }
    }
}
