package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class Waypoint {
    @Expose
    private String label;
    @Expose
    private String linkId;
    @Expose
    private GeoCoordinate mappedPosition;
    @Expose
    private String mappedRoadName;
    @Expose
    private GeoCoordinate originalPosition;
    @Expose
    private Integer shapeIndex;
    @Expose
    private String sideOfStreet;
    @Expose
    private Double spot;
    @Expose
    private String type;
}
