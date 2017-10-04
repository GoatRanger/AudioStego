package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Leg {
    @Expose
    private Double length;
    @Expose
    private List<Link> link = new ArrayList();
    @Expose
    private List<Maneuver> maneuver = new ArrayList();
    @Expose
    private Double travelTime;

    public List<Maneuver> a() {
        return this.maneuver;
    }

    public List<Link> b() {
        return this.link;
    }

    public Double c() {
        return a.a(this.travelTime);
    }
}
