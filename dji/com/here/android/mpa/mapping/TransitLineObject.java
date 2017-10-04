package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.nokia.maps.TransitLineObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class TransitLineObject extends MapProxyObject {
    private TransitLineObjectImpl b;

    @OnlineNative
    private TransitLineObject(TransitLineObjectImpl transitLineObjectImpl) {
        super(transitLineObjectImpl);
        this.b = transitLineObjectImpl;
    }

    public Identifier getLineId() {
        return this.b.b();
    }

    static {
        TransitLineObjectImpl.a(new am<TransitLineObject, TransitLineObjectImpl>() {
            public TransitLineObject a(TransitLineObjectImpl transitLineObjectImpl) {
                if (transitLineObjectImpl != null) {
                    return new TransitLineObject(transitLineObjectImpl);
                }
                return null;
            }
        });
    }
}
