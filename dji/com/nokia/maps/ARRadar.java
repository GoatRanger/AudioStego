package com.nokia.maps;

import com.here.android.mpa.ar.ARRadarItem;
import com.here.android.mpa.ar.ARRadarProperties;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class ARRadar extends BaseNativeObject {
    private static am<ARRadarProperties, ARRadar> b = null;
    private static k<ARRadarProperties, ARRadar> c = null;
    WeakReference<b> a = null;

    public native double getAngle();

    public native float getBackPlaneStart();

    public native float getDimmingLimit();

    public native float getFrontPlaneEnd();

    public native float getFrontPlaneStart();

    public native int getItemsCount();

    public native ARRadarItemImpl[] getItemsNative();

    static {
        ce.a(ARRadarProperties.class);
    }

    static ARRadarProperties a(ARRadar aRRadar) {
        return aRRadar != null ? (ARRadarProperties) b.a(aRRadar) : null;
    }

    static ARRadar a(ARRadarProperties aRRadarProperties) {
        return c != null ? (ARRadar) c.a(aRRadarProperties) : null;
    }

    public static void a(k<ARRadarProperties, ARRadar> kVar, am<ARRadarProperties, ARRadar> amVar) {
        c = kVar;
        b = amVar;
    }

    @HybridPlusNative
    private ARRadar(int i) {
        this.nativeptr = i;
    }

    public void a(b bVar) {
        this.a = new WeakReference(bVar);
    }

    public List<ARRadarItem> a() {
        if (getItemsCount() == 0) {
            return null;
        }
        ARRadarItemImpl[] itemsNative = getItemsNative();
        List<ARRadarItem> arrayList = new ArrayList();
        for (ARRadarItemImpl aRRadarItemImpl : itemsNative) {
            arrayList.add(ARRadarItemImpl.a(aRRadarItemImpl));
            b bVar = (b) this.a.get();
            if (bVar != null) {
                aRRadarItemImpl.a(bVar.b(aRRadarItemImpl.getUid()));
            }
        }
        return arrayList;
    }
}
