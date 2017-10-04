package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.TransitAccessObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public final class TransitAccessObject extends MapProxyObject {
    private TransitAccessObjectImpl b;

    @OnlineNative
    private TransitAccessObject(TransitAccessObjectImpl transitAccessObjectImpl) {
        super(transitAccessObjectImpl);
        this.b = transitAccessObjectImpl;
    }

    public GeoCoordinate getCoordinate() {
        return this.b.b();
    }

    public TransitAccessInfo getTransitAccessInfo() {
        return this.b.c();
    }

    public List<Image> getIcons() {
        return this.b.d();
    }

    static {
        TransitAccessObjectImpl.a(new am<TransitAccessObject, TransitAccessObjectImpl>() {
            public TransitAccessObject a(TransitAccessObjectImpl transitAccessObjectImpl) {
                if (transitAccessObjectImpl != null) {
                    return new TransitAccessObject(transitAccessObjectImpl);
                }
                return null;
            }
        });
    }
}
