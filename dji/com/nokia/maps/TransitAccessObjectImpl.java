package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.TransitAccessInfo;
import com.here.android.mpa.mapping.TransitAccessObject;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class TransitAccessObjectImpl extends MapProxyObjectImpl {
    private static am<TransitAccessObject, TransitAccessObjectImpl> b;
    private cq a = new cq(TransitAccessObjectImpl.class.getName());

    private final native GeoCoordinateImpl getCoordinateNative();

    private final native ImageImpl[] getIconsNative();

    private final native TransitAccessInfoImpl getTransitAccessInfoNative();

    @OnlineNative
    private TransitAccessObjectImpl(int i) {
        super(i);
    }

    public final GeoCoordinate b() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public TransitAccessInfo c() {
        return TransitAccessInfoImpl.a(getTransitAccessInfoNative());
    }

    public final List<Image> d() {
        return ImageImpl.a(getIconsNative());
    }

    public static void a(am<TransitAccessObject, TransitAccessObjectImpl> amVar) {
        b = amVar;
    }

    static {
        ce.a(TransitAccessObject.class);
    }
}
