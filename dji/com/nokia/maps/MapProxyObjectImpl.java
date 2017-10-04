package com.nokia.maps;

import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.MapProxyObject;
import com.here.android.mpa.mapping.MapProxyObject.Type;
import com.nokia.maps.annotation.Online;

@Online
public class MapProxyObjectImpl extends ViewObjectImpl {
    private static k<MapProxyObject, MapProxyObjectImpl> a = null;

    private native Type getTypeNative();

    public static void a(k<MapProxyObject, MapProxyObjectImpl> kVar) {
        a = kVar;
    }

    protected MapProxyObjectImpl() {
    }

    protected MapProxyObjectImpl(int i) {
        super(i);
    }

    public ViewObject.Type k() {
        return ViewObject.Type.PROXY_OBJECT;
    }

    public Type a() {
        return getTypeNative();
    }
}
