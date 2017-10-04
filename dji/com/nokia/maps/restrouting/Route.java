package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Route {
    @Expose
    private BoundingBox boundingBox;
    @Expose
    private List<Leg> leg = new ArrayList();
    @Expose
    private Mode mode;
    @Expose
    private List<Note> note = new ArrayList();
    @Expose
    private List<PublicTransportLine> publicTransportLine = new ArrayList();
    @Expose
    private Summary summary;
    @Expose
    private List<SummaryByCountry> summaryByCountry = new ArrayList();
    @Expose
    private List<Waypoint> waypoint = new ArrayList();

    public BoundingBox a() {
        return this.boundingBox;
    }

    public List<Leg> b() {
        return this.leg;
    }

    public List<PublicTransportLine> c() {
        return this.publicTransportLine;
    }

    public List<Note> d() {
        return this.note;
    }

    public Summary e() {
        return this.summary;
    }

    public List<SummaryByCountry> f() {
        return this.summaryByCountry;
    }
}
