package com.here.android.mpa.venues3d;

import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class CombinedRoute extends BaseNativeObject {
    private List<IRouteSection> a;

    @HybridPlusNative
    private native List<IRouteSection> getRouteSectionsNative();

    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    native int getCombinationType();

    @HybridPlusNative
    native VenueRouteOptions getOptions();

    @HybridPlusNative
    private CombinedRoute(int i) {
        this.nativeptr = i;
    }

    public List<IRouteSection> getRouteSections() {
        if (this.a == null) {
            this.a = getRouteSectionsNative();
        }
        return this.a != null ? this.a : new ArrayList();
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
