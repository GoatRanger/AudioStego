package com.nokia.maps;

import com.here.android.mpa.odml.MapPackage;
import com.here.android.mpa.odml.MapPackage.InstallationState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bu {
    private static k<MapPackage, bu> i;
    private static am<MapPackage, bu> j;
    MapPackage a;
    List<MapPackage> b = new ArrayList();
    int c;
    int d;
    String e;
    String f;
    long g;
    InstallationState h;

    public static void a(k<MapPackage, bu> kVar, am<MapPackage, bu> amVar) {
        i = kVar;
        j = amVar;
    }

    static bu a(MapPackage mapPackage) {
        if (mapPackage == null || i == null) {
            return null;
        }
        return (bu) i.a(mapPackage);
    }

    static MapPackage a(bu buVar) {
        if (buVar != null) {
            return (MapPackage) j.a(buVar);
        }
        return null;
    }

    static {
        ce.a(MapPackage.class);
    }

    bu() {
    }

    public MapPackage a() {
        return this.a;
    }

    public List<MapPackage> b() {
        return Collections.unmodifiableList(this.b);
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public long f() {
        return this.g;
    }

    public InstallationState g() {
        return this.h;
    }

    int h() {
        return this.d;
    }

    void a(InstallationState installationState) {
        this.h = installationState;
    }
}
