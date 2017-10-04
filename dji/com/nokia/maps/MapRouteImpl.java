package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.mapping.MapRoute.RenderType;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapRouteImpl extends MapObjectImpl {
    private static k<MapRoute, MapRouteImpl> a = null;
    private Route d;

    private native void createMapRouteNative();

    private native int getAlpha();

    private native int getBlue();

    private native int getGreen();

    private native int getRed();

    private native int getRenderTypeNative();

    private native void native_setColor(int i, int i2, int i3, int i4);

    private native void setRenderTypeNative(int i, MapImpl mapImpl);

    private native void setRoute_native(RouteImpl routeImpl);

    public native void enableTraffic(boolean z);

    public native int getColor();

    public native boolean isManeuverNumberVisible();

    public native boolean isTrafficEnabled();

    public native void setManeuverNumberVisible(boolean z);

    public static void b(k<MapRoute, MapRouteImpl> kVar) {
        a = kVar;
    }

    @OnlineNative
    protected MapRouteImpl(int i) {
        super(i);
    }

    public MapRouteImpl() {
        this(false);
    }

    protected MapRouteImpl(boolean z) {
        if (!z) {
            createMapRouteNative();
        }
    }

    public void a(int i) {
        native_setColor(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public Route a() {
        return this.d;
    }

    public void a(Route route) {
        if (route == null) {
            throw new NullPointerException("Route provided is null.");
        }
        this.d = route;
        setRoute_native(RouteImpl.a(route));
        o();
    }

    public void a(RenderType renderType) {
        setRenderTypeNative(renderType.value(), p());
        o();
    }

    public RenderType b() {
        int renderTypeNative = getRenderTypeNative();
        RenderType renderType = RenderType.PRIMARY;
        switch (renderTypeNative) {
            case 1:
                return RenderType.PRIMARY;
            case 2:
                return RenderType.SECONDARY;
            case 3:
                return RenderType.TRAVELED;
            default:
                renderType.setValue(renderTypeNative);
                return renderType;
        }
    }
}
