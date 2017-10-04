package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Maneuver {
    @SerializedName("_type")
    @Expose
    private String Type;
    @Expose
    private String action;
    @Expose
    private Double baseTime;
    @Expose
    private BoundingBox boundingBox;
    @Expose
    private String id;
    @Expose
    private String instruction;
    @Expose
    private Double length;
    @Expose
    private String line;
    @Expose
    private String nextRoadName;
    @Expose
    private String nextRoadNumber;
    @Expose
    private List<Note> note = new ArrayList();
    @Expose
    private GeoCoordinate position;
    @Expose
    private String roadName;
    @Expose
    private String roadNumber;
    @Expose
    private Integer startAngle;
    @Expose
    private String stopName;
    @Expose
    private String time;
    @Expose
    private String toLine;
    @Expose
    private String toLink;
    @Expose
    private Double trafficTime;
    @Expose
    private Double travelTime;
    @Expose
    private Double waitTime;

    public String a() {
        return a.a(this.Type);
    }

    public String b() {
        return a.a(this.action);
    }

    public Double c() {
        return a.a(this.baseTime);
    }

    public GeoCoordinate d() {
        return this.position;
    }

    public Double e() {
        return a.a(this.travelTime);
    }

    public Double f() {
        return a.a(this.length);
    }

    public String g() {
        return a.a(this.time);
    }

    public BoundingBox h() {
        return this.boundingBox;
    }

    public String i() {
        return a.a(this.id);
    }

    public String j() {
        return a.a(this.nextRoadName);
    }

    public String k() {
        return a.a(this.roadName);
    }

    public String l() {
        return a.a(this.nextRoadNumber);
    }

    public List<Note> m() {
        return this.note;
    }

    public String n() {
        return a.a(this.roadNumber);
    }

    public Integer o() {
        return a.a(this.startAngle);
    }

    public String p() {
        return a.a(this.stopName);
    }

    public String q() {
        return a.a(this.line);
    }

    public String r() {
        return a.a(this.toLine);
    }

    public Double s() {
        return a.a(this.waitTime);
    }
}
