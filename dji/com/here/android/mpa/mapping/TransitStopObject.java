package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.TransitStopObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public final class TransitStopObject extends MapProxyObject {
    private TransitStopObjectImpl b;

    @OnlineNative
    private TransitStopObject(TransitStopObjectImpl transitStopObjectImpl) {
        super(transitStopObjectImpl);
        this.b = transitStopObjectImpl;
    }

    public GeoCoordinate getCoordinate() {
        return this.b.b();
    }

    public TransitStopInfo getTransitStopInfo() {
        return this.b.c();
    }

    public List<Image> getIcons() {
        return this.b.d();
    }

    static {
        TransitStopObjectImpl.a(new am<TransitStopObject, TransitStopObjectImpl>() {
            public TransitStopObject a(TransitStopObjectImpl transitStopObjectImpl) {
                if (transitStopObjectImpl != null) {
                    return new TransitStopObject(transitStopObjectImpl);
                }
                return null;
            }
        });
    }
}
