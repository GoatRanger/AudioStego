package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.HybridPlus;
import java.util.EnumSet;
import java.util.Iterator;

@HybridPlus
public enum Map$FleetFeature {
    TRUCK_RESTRICTIONS(1),
    ENVIRONMENTAL_ZONES(2),
    CONGESTION_ZONES(4);
    
    private int a;

    private Map$FleetFeature(int i) {
        this.a = i;
    }

    static EnumSet<Map$FleetFeature> a(int i) {
        EnumSet<Map$FleetFeature> noneOf = EnumSet.noneOf(Map$FleetFeature.class);
        for (Map$FleetFeature map$FleetFeature : values()) {
            if ((map$FleetFeature.a & i) != 0) {
                noneOf.add(map$FleetFeature);
            }
        }
        return noneOf;
    }

    static int a(EnumSet<Map$FleetFeature> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((Map$FleetFeature) it.next()).a | i;
        }
        return i;
    }
}
