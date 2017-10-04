package com.here.android.mpa.customlocation2;

import com.nokia.maps.CLE2GeometryImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;
import java.util.Map;

@HybridPlus
public class CLE2Geometry {
    CLE2GeometryImpl a;

    @HybridPlusNative
    CLE2Geometry(CLE2GeometryImpl cLE2GeometryImpl) {
        this.a = cLE2GeometryImpl;
    }

    public double getDistance() {
        return this.a.getDistanceNative();
    }

    public Map<String, String> getAttributes() {
        return this.a.a();
    }

    static {
        CLE2GeometryImpl.a(new k<CLE2Geometry, CLE2GeometryImpl>() {
            public CLE2GeometryImpl a(CLE2Geometry cLE2Geometry) {
                return cLE2Geometry.a;
            }
        }, new am<CLE2Geometry, CLE2GeometryImpl>() {
            public CLE2Geometry a(CLE2GeometryImpl cLE2GeometryImpl) {
                if (cLE2GeometryImpl != null) {
                    return new CLE2Geometry(cLE2GeometryImpl);
                }
                return null;
            }
        });
    }
}
