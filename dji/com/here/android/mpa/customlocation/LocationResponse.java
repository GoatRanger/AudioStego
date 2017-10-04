package com.here.android.mpa.customlocation;

import com.google.gson.annotations.Expose;
import java.util.List;

final class LocationResponse extends CLEResponse {
    @Expose
    int available;
    @Expose
    List<Location> bblocations;
    @Expose
    List<Location> corridorLocations;
    @Expose
    int layerId;
    @Expose
    String layerName;
    @Expose
    List<Location> locations;
    @Expose
    String message;
    @Expose
    List<Location> proximityLocations;

    static class Location {
        @Expose
        String city;
        @Expose
        Coordinate coordinate;
        @Expose
        String country;
        @Expose
        String county;
        @Expose
        List<CustomAttribute> customAttributes;
        @Expose
        String customerLocationId;
        @Expose
        String description;
        @Expose
        float distance;
        @Expose
        String fax;
        @Expose
        String houseNumber;
        @Expose
        String name1;
        @Expose
        String name2;
        @Expose
        String name3;
        @Expose
        String phone;
        @Expose
        String postalCode;
        @Expose
        RouteCoordinate routeCoordinate;
        @Expose
        String state;
        @Expose
        String street;
        @Expose
        String webURL;

        static class Coordinate {
            @Expose
            double latitude;
            @Expose
            double longitude;

            Coordinate() {
            }
        }

        static class CustomAttribute {
            @Expose
            String name;
            @Expose
            String value;

            CustomAttribute() {
            }
        }

        static class RouteCoordinate {
            @Expose
            double latitude;
            @Expose
            double longitude;

            RouteCoordinate() {
            }
        }

        Location() {
        }
    }

    LocationResponse() {
    }

    Result a() {
        return new Result(this);
    }
}
