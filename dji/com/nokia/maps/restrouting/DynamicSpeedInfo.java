package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class DynamicSpeedInfo {
    @Expose
    private Double baseSpeed;
    @Expose
    private Double trafficSpeed;

    public Double a() {
        return a.a(this.baseSpeed);
    }

    public Double b() {
        return a.a(this.trafficSpeed);
    }
}
