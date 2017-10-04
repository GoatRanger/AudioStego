package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.MapBuildingGroup;
import com.here.android.mpa.mapping.MapBuildingGroup.BuildingFace;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

@Online
public class MapBuildingGroupImpl extends BaseNativeObject {
    private static k<MapBuildingGroup, MapBuildingGroupImpl> a;
    private static am<MapBuildingGroup, MapBuildingGroupImpl> b;
    private MapImpl c;
    private int d = 0;
    private boolean e = false;

    private native boolean addBuildingNative(String str);

    private native boolean addBuildingsNative(IdentifierImpl[] identifierImplArr);

    private native boolean addBuildingsNative(String[] strArr);

    private native void destroyNative();

    private native int getColorNative(int i);

    private native boolean removeAllBuildingsNative();

    private native boolean removeBuildingNative(String str);

    private native void setColorNative(short s, short s2, short s3, short s4, int i);

    private native void setVerticalScaleNative(float f);

    public native float getVerticalScale();

    public native boolean removeBuildings(IdentifierImpl[] identifierImplArr);

    public native boolean removeBuildings(String[] strArr);

    public static void a(k<MapBuildingGroup, MapBuildingGroupImpl> kVar, am<MapBuildingGroup, MapBuildingGroupImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static MapBuildingGroupImpl a(MapBuildingGroup mapBuildingGroup) {
        if (a != null) {
            return (MapBuildingGroupImpl) a.a(mapBuildingGroup);
        }
        return null;
    }

    static MapBuildingGroup a(MapBuildingGroupImpl mapBuildingGroupImpl) {
        if (mapBuildingGroupImpl != null) {
            return (MapBuildingGroup) b.a(mapBuildingGroupImpl);
        }
        return null;
    }

    static {
        ce.a(MapBuildingGroup.class);
    }

    private MapBuildingGroupImpl() {
    }

    @OnlineNative
    private MapBuildingGroupImpl(int i) {
        this.nativeptr = i;
    }

    public void a(MapImpl mapImpl) {
        this.c = mapImpl;
    }

    public boolean a() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public int b() {
        return this.d;
    }

    public boolean a(Identifier identifier) {
        IdentifierImpl a = IdentifierImpl.a(identifier);
        if (a.a() == a.STRING_ID) {
            return a(a.b());
        }
        return false;
    }

    public boolean a(String str) {
        boolean addBuildingNative = addBuildingNative(str);
        if (this.c != null) {
            this.c.redraw();
        }
        if (addBuildingNative) {
            this.d++;
        }
        return addBuildingNative;
    }

    public boolean a(List<Identifier> list) {
        boolean z = false;
        IdentifierImpl[] a = IdentifierImpl.a((List) list);
        if (a != null) {
            z = addBuildingsNative(a);
            if (this.c != null) {
                this.c.redraw();
            }
            if (z) {
                this.d += list.size();
            }
        }
        return z;
    }

    public boolean b(Identifier identifier) {
        IdentifierImpl a = IdentifierImpl.a(identifier);
        if (a.a() == a.STRING_ID) {
            return b(a.b());
        }
        return false;
    }

    public boolean b(String str) {
        boolean removeBuildingNative = removeBuildingNative(str);
        if (this.c != null) {
            this.c.redraw();
        }
        if (removeBuildingNative) {
            this.d--;
        }
        return removeBuildingNative;
    }

    public boolean b(List<Identifier> list) {
        boolean removeBuildings = removeBuildings(IdentifierImpl.a((List) list));
        if (this.c != null) {
            this.c.redraw();
        }
        if (removeBuildings) {
            this.d -= list.size();
        }
        return removeBuildings;
    }

    public boolean c() {
        boolean removeAllBuildingsNative = removeAllBuildingsNative();
        if (removeAllBuildingsNative) {
            this.d = 0;
            if (this.c != null) {
                this.c.redraw();
            }
        }
        return removeAllBuildingsNative;
    }

    public void a(int i, EnumSet<BuildingFace> enumSet) {
        Iterator it = enumSet.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 |= ((BuildingFace) it.next()).mask();
        }
        setColorNative((short) Color.red(i), (short) Color.green(i), (short) Color.blue(i), (short) Color.alpha(i), i2);
        if (this.c != null) {
            this.c.redraw();
        }
    }

    public int a(BuildingFace buildingFace) {
        return getColorNative(buildingFace.mask());
    }

    public void a(float f) {
        setVerticalScaleNative(f);
        if (this.c != null) {
            this.c.redraw();
        }
    }

    public Identifier c(String str) {
        return IdentifierImpl.a(new IdentifierImpl(a.STRING_ID, "0 0 |" + str));
    }
}
