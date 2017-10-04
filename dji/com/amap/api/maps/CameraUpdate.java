package com.amap.api.maps;

import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;

public final class CameraUpdate {
    CameraUpdateFactoryDelegate a;

    CameraUpdate(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        this.a = cameraUpdateFactoryDelegate;
    }

    CameraUpdateFactoryDelegate a() {
        return this.a;
    }
}
