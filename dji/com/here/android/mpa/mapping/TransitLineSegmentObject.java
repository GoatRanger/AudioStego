package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.nokia.maps.TransitLineSegmentObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class TransitLineSegmentObject extends MapProxyObject {
    private TransitLineSegmentObjectImpl b;

    @OnlineNative
    private TransitLineSegmentObject(TransitLineSegmentObjectImpl transitLineSegmentObjectImpl) {
        super(transitLineSegmentObjectImpl);
        this.b = transitLineSegmentObjectImpl;
    }

    public Identifier getLineId() {
        return this.b.b();
    }

    public Identifier getLineSegmentId() {
        return this.b.c();
    }

    static {
        TransitLineSegmentObjectImpl.a(new am<TransitLineSegmentObject, TransitLineSegmentObjectImpl>() {
            public TransitLineSegmentObject a(TransitLineSegmentObjectImpl transitLineSegmentObjectImpl) {
                if (transitLineSegmentObjectImpl != null) {
                    return new TransitLineSegmentObject(transitLineSegmentObjectImpl);
                }
                return null;
            }
        });
    }
}
