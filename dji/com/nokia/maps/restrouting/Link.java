package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Link {
    @Expose
    private Address address;
    @Expose
    private DynamicSpeedInfo dynamicSpeedInfo;
    @Expose
    private List<String> flags = new ArrayList();
    @Expose
    private Double length;
    @Expose
    private String line;
    @Expose
    private String linkId;
    @Expose
    private String maneuver;
    @Expose
    private String nextStopName;
    @Expose
    private Double remainDistance;
    @Expose
    private Double remainTime;
    @Expose
    private String roadName;
    @Expose
    private String roadNumber;
    @Expose
    private List<Double> shape = new ArrayList();
    @Expose
    private String speedCategory;
    @Expose
    private Double speedLimit;
    @Expose
    private TruckRestrictions truckRestrictions;

    public DynamicSpeedInfo a() {
        return this.dynamicSpeedInfo;
    }

    public List<String> b() {
        return this.flags;
    }

    public String c() {
        return a.a(this.linkId);
    }

    public List<Double> d() {
        return this.shape;
    }

    public Double e() {
        return a.a(this.length);
    }

    public Double f() {
        return a.a(this.remainDistance);
    }

    public Double g() {
        return a.a(this.remainTime);
    }

    public String h() {
        return a.a(this.maneuver);
    }

    public Double i() {
        return a.a(this.speedLimit);
    }

    public String j() {
        return a.a(this.roadName);
    }

    public String k() {
        return a.a(this.roadNumber);
    }

    public String l() {
        return a.a(this.nextStopName);
    }

    public String m() {
        return a.a(this.line);
    }
}
