package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import com.here.android.mpa.ar.ARController.ProjectionType;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.ARObjectImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@TargetApi(14)
@HybridPlus
public abstract class ARObject {
    protected ARObjectImpl m_basePimpl;

    @HybridPlus
    public enum IconType {
        DOWN,
        FRONT,
        BACK,
        INFO
    }

    protected ARObject(ARObjectImpl aRObjectImpl) {
        if (aRObjectImpl != null) {
            this.m_basePimpl = aRObjectImpl;
        }
    }

    public long getUid() {
        return this.m_basePimpl.getUid();
    }

    public void setCoordinate(GeoCoordinate geoCoordinate) {
        this.m_basePimpl.a(geoCoordinate);
    }

    public GeoCoordinate getCoordinate() {
        return this.m_basePimpl.a();
    }

    public void setIconTexture(IconType iconType, int i, int i2, int i3) {
        this.m_basePimpl.a(iconType, i, i2, i3);
    }

    public void setIcon(IconType iconType, Image image) {
        this.m_basePimpl.a(iconType, image);
    }

    public void setIcon(IconType iconType, View view) {
        this.m_basePimpl.a(iconType, view);
    }

    public void setIcon(IconType iconType, Bitmap bitmap) {
        this.m_basePimpl.a(iconType, bitmap);
    }

    public Image getIcon(IconType iconType) {
        return this.m_basePimpl.a(iconType);
    }

    public void setIconAnchor(IconType iconType, PointF pointF) {
        this.m_basePimpl.setIconAnchor(iconType.ordinal(), pointF);
    }

    public PointF getIconAnchor(IconType iconType) {
        return this.m_basePimpl.getIconAnchor(iconType.ordinal());
    }

    public void setInfoMaxWidth(int i) {
        this.m_basePimpl.setInfoMaxWidth(i);
    }

    public void setInfoMaxHeight(int i) {
        this.m_basePimpl.setInfoMaxHeight(i);
    }

    public boolean isInfoExtended() {
        return this.m_basePimpl.isInfoExtended();
    }

    public void setIconSizeScale(IconType iconType, float f) {
        this.m_basePimpl.setIconSizeScale(iconType.ordinal(), f);
    }

    public float getIconSizeScale(IconType iconType) {
        return this.m_basePimpl.getIconSizeScale(iconType.ordinal());
    }

    public void setBoundingBox(RectF rectF) {
        this.m_basePimpl.setBoundingBox(rectF);
    }

    public RectF getBoundingBox() {
        return this.m_basePimpl.getBoundingBox();
    }

    public void setMaxViewAngle(float f) {
        this.m_basePimpl.setMaxViewAngle(f);
    }

    public float getMaxViewAngle() {
        return this.m_basePimpl.getMaxViewAngle();
    }

    public void setProjectionType(ProjectionType projectionType) {
        this.m_basePimpl.setProjectionType(projectionType.ordinal());
    }

    public ProjectionType getProjectionType() {
        return ProjectionType.values()[this.m_basePimpl.getProjectionType()];
    }

    public void setOpacity(float f) {
        this.m_basePimpl.setOpacity(f);
    }

    public float getOpacity() {
        return this.m_basePimpl.getOpacity();
    }

    static {
        ARObjectImpl.a(new k<ARObject, ARObjectImpl>() {
            public ARObjectImpl a(ARObject aRObject) {
                return aRObject != null ? aRObject.m_basePimpl : null;
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
        ARObject aRObject = (ARObject) obj;
        if (this.m_basePimpl == null) {
            if (aRObject.m_basePimpl != null) {
                return false;
            }
            return true;
        } else if (this.m_basePimpl.equals(aRObject.m_basePimpl)) {
            return true;
        } else {
            return false;
        }
    }
}
