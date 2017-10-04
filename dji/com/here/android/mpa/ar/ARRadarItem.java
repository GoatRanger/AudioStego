package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.graphics.RectF;
import com.nokia.maps.ARRadarItemImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@TargetApi(14)
@HybridPlus
public final class ARRadarItem {
    private ARRadarItemImpl a;

    private ARRadarItem(ARRadarItemImpl aRRadarItemImpl) {
        this.a = aRRadarItemImpl;
    }

    public int getUid() {
        return (int) this.a.getUid();
    }

    public ARObject getARObject() {
        return this.a.a();
    }

    public boolean isVisible() {
        return this.a.isVisible();
    }

    public float getBearing() {
        return this.a.getBearing();
    }

    public float getDistance() {
        return this.a.getDistance();
    }

    public float getSpreadDistance() {
        return this.a.getSpreadDistance();
    }

    public float getPanDistance() {
        return this.a.getPanDistance();
    }

    public RectF getScreenRect() {
        return this.a.b();
    }

    public boolean isOccluded() {
        return this.a.isOccluded();
    }

    static {
        ARRadarItemImpl.a(new k<ARRadarItem, ARRadarItemImpl>() {
            public ARRadarItemImpl a(ARRadarItem aRRadarItem) {
                return aRRadarItem != null ? aRRadarItem.a : null;
            }
        }, new am<ARRadarItem, ARRadarItemImpl>() {
            public ARRadarItem a(ARRadarItemImpl aRRadarItemImpl) {
                if (aRRadarItemImpl != null) {
                    return new ARRadarItem(aRRadarItemImpl);
                }
                return null;
            }
        });
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
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
        ARRadarItem aRRadarItem = (ARRadarItem) obj;
        if (this.a == null) {
            if (aRRadarItem.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aRRadarItem.a)) {
            return true;
        } else {
            return false;
        }
    }
}
