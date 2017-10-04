package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class Stop {
    @Expose
    private String line;
    @Expose
    private GeoCoordinate position;
    @Expose
    private String stopName;
    @Expose
    private Double travelTime;

    public GeoCoordinate a() {
        return this.position;
    }

    public String b() {
        return a.a(this.stopName);
    }

    public Double c() {
        return a.a(this.travelTime);
    }
}
