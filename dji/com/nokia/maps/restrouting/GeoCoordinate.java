package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class GeoCoordinate {
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public Double a() {
        return a.a(this.latitude);
    }

    public Double b() {
        return a.a(this.longitude);
    }
}
