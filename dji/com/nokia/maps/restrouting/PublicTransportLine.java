package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class PublicTransportLine {
    @Expose
    private String destination;
    @Expose
    private String id;
    @Expose
    private String lineBackground;
    @Expose
    private String lineForeground;
    @Expose
    private String lineName;
    @Expose
    private String lineStyle;
    @Expose
    private List<Stop> stop = new ArrayList();
    @Expose
    private String type;
    @Expose
    private String typeName;

    public String a() {
        return a.a(this.lineName);
    }

    public String b() {
        return a.a(this.lineForeground);
    }

    public String c() {
        return a.a(this.lineBackground);
    }

    public String d() {
        return a.a(this.lineStyle);
    }

    public String e() {
        return a.a(this.destination);
    }

    public String f() {
        return a.a(this.type);
    }

    public String g() {
        return a.a(this.typeName);
    }

    public List<Stop> h() {
        return this.stop;
    }

    public String i() {
        return a.a(this.id);
    }
}
