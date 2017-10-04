package com.nokia.maps;

import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.RouteTta.Detail;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;

@Online
public class RouteTtaImpl extends BaseNativeObject {
    private static am<RouteTta, RouteTtaImpl> a;

    private native void destroyNative();

    private native int getDetailsNative();

    public native int getDuration();

    public native boolean isBlocked();

    public native boolean isValid();

    @OnlineNative
    RouteTtaImpl(int i) {
        this.nativeptr = i;
    }

    public static void a(am<RouteTta, RouteTtaImpl> amVar) {
        a = amVar;
    }

    static {
        ce.a(RouteTta.class);
    }

    public static RouteTta a(RouteTtaImpl routeTtaImpl) {
        if (routeTtaImpl == null || !routeTtaImpl.isValid()) {
            return null;
        }
        return (RouteTta) a.a(routeTtaImpl);
    }

    public EnumSet<Detail> a() {
        EnumSet<Detail> noneOf = EnumSet.noneOf(Detail.class);
        if (this.nativeptr != 0) {
            return a(getDetailsNative());
        }
        return noneOf;
    }

    static EnumSet<Detail> a(int i) {
        EnumSet<Detail> noneOf = EnumSet.noneOf(Detail.class);
        if ((i & 1) > 0) {
            noneOf.add(Detail.BLOCKED_ROAD);
        }
        if ((i & 2) > 0) {
            noneOf.add(Detail.CARPOOL);
        }
        if ((i & 4) > 0) {
            noneOf.add(Detail.RESTRICTED_TURN);
        }
        return noneOf;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }
}
