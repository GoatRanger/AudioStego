package com.here.android.mpa.customlocation2;

import com.nokia.maps.CLE2ResultImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class CLE2Result {
    CLE2ResultImpl a;

    @HybridPlusNative
    CLE2Result(CLE2ResultImpl cLE2ResultImpl) {
        this.a = cLE2ResultImpl;
    }

    public List<CLE2Geometry> getGeometries() {
        return this.a.a();
    }

    static {
        CLE2ResultImpl.a(new k<CLE2Result, CLE2ResultImpl>() {
            public CLE2ResultImpl a(CLE2Result cLE2Result) {
                return cLE2Result.a;
            }
        }, new am<CLE2Result, CLE2ResultImpl>() {
            public CLE2Result a(CLE2ResultImpl cLE2ResultImpl) {
                if (cLE2ResultImpl != null) {
                    return new CLE2Result(cLE2ResultImpl);
                }
                return null;
            }
        });
    }
}
