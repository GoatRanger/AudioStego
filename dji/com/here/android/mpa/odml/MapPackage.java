package com.here.android.mpa.odml;

import com.nokia.maps.am;
import com.nokia.maps.annotation.Hybrid;
import com.nokia.maps.bu;
import com.nokia.maps.k;
import java.util.List;

public final class MapPackage {
    private bu a;

    @Hybrid
    public enum InstallationState {
        INSTALLED,
        PARTIALLY_INSTALLED,
        NOT_INSTALLED
    }

    @Hybrid
    public MapPackage getParent() {
        return this.a.a();
    }

    @Hybrid
    public List<MapPackage> getChildren() {
        return this.a.b();
    }

    @Hybrid
    public int getId() {
        return this.a.c();
    }

    @Hybrid
    public String getTitle() {
        return this.a.d();
    }

    @Hybrid
    public String getEnglishTitle() {
        return this.a.e();
    }

    @Hybrid
    public long getSize() {
        return this.a.f();
    }

    @Hybrid
    public InstallationState getInstallationState() {
        return this.a.g();
    }

    private MapPackage(bu buVar) {
        this.a = buVar;
    }

    static {
        bu.a(new k<MapPackage, bu>() {
            public bu a(MapPackage mapPackage) {
                return mapPackage.a;
            }
        }, new am<MapPackage, bu>() {
            public MapPackage a(bu buVar) {
                if (buVar != null) {
                    return new MapPackage(buVar);
                }
                throw new IllegalArgumentException("MapPackageImpl passed as null");
            }
        });
    }
}
