package com.here.android.mpa.venues3d;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public final class OuterArea extends Area {
    private List<Space> a = null;

    private native List<Space> getSpacesNative();

    @HybridPlusNative
    private OuterArea(int i) {
        super(i);
    }

    public List<Space> getSpaces() {
        if (this.a == null) {
            this.a = getSpacesNative();
        }
        return this.a != null ? this.a : new ArrayList();
    }
}
