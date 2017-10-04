package com.nokia.maps;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.TransitLineObject;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class TransitLineObjectImpl extends MapProxyObjectImpl {
    private static am<TransitLineObject, TransitLineObjectImpl> b;
    private cq a = new cq(TransitLineObjectImpl.class.getName());

    private native void destroyTransitLineObjectNative();

    private final native IdentifierImpl getLineIdNative();

    @OnlineNative
    private TransitLineObjectImpl(int i) {
        super(i);
    }

    public final Identifier b() {
        return IdentifierImpl.a(getLineIdNative());
    }

    public static void a(am<TransitLineObject, TransitLineObjectImpl> amVar) {
        b = amVar;
    }

    static {
        ce.a(TransitLineObject.class);
    }
}
