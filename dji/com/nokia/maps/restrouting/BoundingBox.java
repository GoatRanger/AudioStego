package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class BoundingBox {
    @Expose
    private GeoCoordinate bottomRight;
    @Expose
    private GeoCoordinate topLeft;

    public GeoCoordinate a() {
        return this.topLeft;
    }

    public GeoCoordinate b() {
        return this.bottomRight;
    }
}
