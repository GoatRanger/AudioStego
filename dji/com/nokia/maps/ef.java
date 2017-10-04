package com.nokia.maps;

import com.here.android.mpa.routing.RouteTta.Detail;
import com.nokia.maps.annotation.HybridPlus;
import java.util.EnumSet;

@HybridPlus
public class ef extends RouteTtaImpl {
    private EnumSet<Detail> a;
    private int b;

    ef() {
        super(0);
        this.a = null;
        this.b = 0;
    }

    public ef(EnumSet<Detail> enumSet, int i) {
        super(0);
        this.a = enumSet;
        this.b = i;
    }

    public EnumSet<Detail> a() {
        return this.a;
    }

    public int getDuration() {
        return this.b;
    }

    public boolean isBlocked() {
        return this.a.contains(Detail.BLOCKED_ROAD);
    }

    public boolean isValid() {
        return this.a != null;
    }
}
