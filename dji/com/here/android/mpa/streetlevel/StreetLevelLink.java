package com.here.android.mpa.streetlevel;

import com.here.android.mpa.streetlevel.StreetLevelProxyObject.Type;
import com.nokia.maps.PanoramaLink;
import com.nokia.maps.annotation.HybridPlusNative;

public final class StreetLevelLink extends StreetLevelProxyObject {
    PanoramaLink a;

    @HybridPlusNative
    private StreetLevelLink(PanoramaLink panoramaLink) {
        super(panoramaLink);
        this.a = panoramaLink;
    }

    public StreetLevel getStreetLevel() {
        return this.a.b();
    }

    public Type getType() {
        return this.a.a();
    }
}
