package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Response {
    @Expose
    private String language;
    @Expose
    private List<Route> route = new ArrayList();
    @Expose
    private SourceAttribution sourceAttribution;

    public List<Route> a() {
        return this.route;
    }

    public SourceAttribution b() {
        return this.sourceAttribution;
    }
}
