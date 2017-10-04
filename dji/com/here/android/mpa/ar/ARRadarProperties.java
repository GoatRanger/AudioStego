package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import com.nokia.maps.ARRadar;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
@HybridPlus
public final class ARRadarProperties {
    private ARRadar a;

    private ARRadarProperties(ARRadar aRRadar) {
        this.a = aRRadar;
    }

    public double getAngle() {
        return this.a.getAngle();
    }

    public float getFrontPlaneStart() {
        return this.a.getFrontPlaneStart();
    }

    public float getFrontPlaneEnd() {
        return this.a.getFrontPlaneEnd();
    }

    public float getDimmingLimit() {
        return this.a.getDimmingLimit();
    }

    public float getBackPlaneStart() {
        return this.a.getBackPlaneStart();
    }

    public List<ARRadarItem> getItems() {
        List<ARRadarItem> a = this.a.a();
        return a == null ? new ArrayList() : a;
    }

    static {
        ARRadar.a(new k<ARRadarProperties, ARRadar>() {
            public ARRadar a(ARRadarProperties aRRadarProperties) {
                return aRRadarProperties != null ? aRRadarProperties.a : null;
            }
        }, new am<ARRadarProperties, ARRadar>() {
            public ARRadarProperties a(ARRadar aRRadar) {
                if (aRRadar != null) {
                    return new ARRadarProperties(aRRadar);
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
        ARRadarProperties aRRadarProperties = (ARRadarProperties) obj;
        if (this.a == null) {
            if (aRRadarProperties.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aRRadarProperties.a)) {
            return true;
        } else {
            return false;
        }
    }
}
