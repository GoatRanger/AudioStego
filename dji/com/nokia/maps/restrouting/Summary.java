package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Summary {
    @Expose
    private Double baseTime;
    @Expose
    private Double distance;
    @Expose
    private List<String> flags = new ArrayList();
    @Expose
    private Double trafficTime;
    @Expose
    private Double travelTime;

    public Double a() {
        return a.a(this.distance);
    }

    public Double b() {
        return a.a(this.travelTime);
    }
}
