package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.TransitStopInfo;
import com.here.android.mpa.mapping.TransitStopObject;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class TransitStopObjectImpl extends MapProxyObjectImpl {
    private static am<TransitStopObject, TransitStopObjectImpl> b;
    private cq a = new cq(TransitStopObjectImpl.class.getName());

    private final native GeoCoordinateImpl getCoordinateNative();

    private final native ImageImpl[] getIconsNative();

    private final native TransitStopInfoImpl getTransitStopInfoNative();

    @OnlineNative
    private TransitStopObjectImpl(int i) {
        super(i);
    }

    public final GeoCoordinate b() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public final TransitStopInfo c() {
        return TransitStopInfoImpl.a(getTransitStopInfoNative());
    }

    public final List<Image> d() {
        return ImageImpl.a(getIconsNative());
    }

    public static void a(am<TransitStopObject, TransitStopObjectImpl> amVar) {
        b = amVar;
    }

    static {
        ce.a(TransitStopObject.class);
    }
}
