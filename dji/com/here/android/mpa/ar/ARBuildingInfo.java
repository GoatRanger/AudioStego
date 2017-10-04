package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.mapping.LocalMesh;
import com.here.android.mpa.mapping.Location;
import com.nokia.maps.ARBuildingInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.k;

@TargetApi(14)
@HybridPlus
public class ARBuildingInfo {
    protected ARBuildingInfoImpl a;

    private ARBuildingInfo(ARBuildingInfoImpl aRBuildingInfoImpl) {
        this.a = aRBuildingInfoImpl;
    }

    public Identifier getIdentifier() {
        return this.a.a();
    }

    @Internal
    public String getLROId() {
        return this.a.getLROId();
    }

    public Location getLocation() {
        return this.a.c();
    }

    public GeoCoordinate getPosition() {
        return this.a.b();
    }

    public String getPlaceName() {
        return this.a.getPlaceName();
    }

    public float getHeight() {
        return this.a.getHeight();
    }

    public Vector3f getSelectedFacadeNormal() {
        return this.a.getSelectedFacadeNormalNative();
    }

    public GeoCoordinate getSelectedFacadeNormalOrigin() {
        return this.a.d();
    }

    @Internal
    public GeoCoordinate getMeshOrigin() {
        return this.a.e();
    }

    @Internal
    public LocalMesh getMesh() {
        return this.a.f();
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
        ARBuildingInfo aRBuildingInfo = (ARBuildingInfo) obj;
        if (this.a == null) {
            if (aRBuildingInfo.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aRBuildingInfo.a)) {
            return true;
        } else {
            return false;
        }
    }

    static {
        ARBuildingInfoImpl.a(new k<ARBuildingInfo, ARBuildingInfoImpl>() {
            public ARBuildingInfoImpl a(ARBuildingInfo aRBuildingInfo) {
                return aRBuildingInfo.a;
            }
        }, new am<ARBuildingInfo, ARBuildingInfoImpl>() {
            public ARBuildingInfo a(ARBuildingInfoImpl aRBuildingInfoImpl) {
                if (aRBuildingInfoImpl != null) {
                    return new ARBuildingInfo(aRBuildingInfoImpl);
                }
                return null;
            }
        });
    }
}
