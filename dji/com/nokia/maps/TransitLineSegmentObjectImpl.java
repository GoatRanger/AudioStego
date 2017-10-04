package com.nokia.maps;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.MapProxyObject.Type;
import com.here.android.mpa.mapping.TransitLineSegmentObject;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class TransitLineSegmentObjectImpl extends MapProxyObjectImpl {
    private static am<TransitLineSegmentObject, TransitLineSegmentObjectImpl> b;
    private cq a = new cq(TransitLineSegmentObjectImpl.class.getName());

    private final native IdentifierImpl getLineIdNative();

    private final native IdentifierImpl getLineSegmentIdNative();

    @OnlineNative
    private TransitLineSegmentObjectImpl(int i) {
        super(i);
    }

    public Type a() {
        return Type.TRANSIT_LINE_SEGMENT;
    }

    public final Identifier b() {
        return IdentifierImpl.a(getLineIdNative());
    }

    public final Identifier c() {
        return IdentifierImpl.a(getLineSegmentIdNative());
    }

    public static void a(am<TransitLineSegmentObject, TransitLineSegmentObjectImpl> amVar) {
        b = amVar;
    }

    static {
        ce.a(TransitLineSegmentObject.class);
    }
}
