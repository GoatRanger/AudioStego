package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.customlocation.LocationResponse.Location.CustomAttribute;
import com.here.android.mpa.mapping.MapPolygon;
import com.here.android.mpa.mapping.MapPolyline;
import com.nokia.maps.annotation.HybridPlus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@HybridPlus
public final class Result {
    private List<Geometry> a = new ArrayList();
    private List<Location> b = new ArrayList();

    @HybridPlus
    public static abstract class Geometry {
        int a;
        String b;
        Map<String, String> c;
        String d;

        public int getGeometryId() {
            return this.a;
        }

        public String getName() {
            return this.b;
        }

        public Map<String, String> getProperties() {
            return this.c;
        }

        public String getFeatureId() {
            return this.d;
        }
    }

    @HybridPlus
    public static final class LineStringGeometry extends Geometry {
        GeoPolyline e;

        public GeoPolyline getLineString() {
            return this.e;
        }

        public MapPolyline createMapPolyline() {
            return new MapPolyline(this.e);
        }
    }

    @HybridPlus
    public static final class Location {
        private GeoCoordinate a;
        private List<CustomAttribute> b;
        private String c;
        private float d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String q;
        private String r;
        private GeoCoordinate s;

        public GeoCoordinate getGeoCoordinate() {
            return this.a;
        }

        public GeoCoordinate getRouteGeoCoordinate() {
            return this.s;
        }

        public String getCustomerLocationId() {
            return this.c;
        }

        public List<CustomAttribute> getCustomAttributes() {
            return this.b;
        }

        public String getDescription() {
            return this.r;
        }

        public float getDistance() {
            return this.d;
        }

        public String getEmail() {
            return this.e;
        }

        public String getFaxNumber() {
            return this.f;
        }

        public String getHouseNumber() {
            return this.k;
        }

        public String getStreetName() {
            return this.l;
        }

        public String getCity() {
            return this.m;
        }

        public String getCounty() {
            return this.n;
        }

        public String getState() {
            return this.o;
        }

        public String getCountry() {
            return this.p;
        }

        public String getPostalCode() {
            return this.q;
        }

        public String getPhone() {
            return this.j;
        }

        public String getName1() {
            return this.g;
        }

        public String getName2() {
            return this.h;
        }

        public String getName3() {
            return this.i;
        }
    }

    @HybridPlus
    public static final class MultiLineStringGeometry extends Geometry {
        List<GeoPolyline> e;

        public List<GeoPolyline> getMultiLineString() {
            return this.e;
        }

        public List<MapPolyline> createMapPolylines() {
            List<MapPolyline> linkedList = new LinkedList();
            for (GeoPolyline mapPolyline : this.e) {
                linkedList.add(new MapPolyline(mapPolyline));
            }
            return linkedList;
        }
    }

    @HybridPlus
    public static final class MultiPointGeometry extends Geometry {
        List<GeoCoordinate> e;

        public List<GeoCoordinate> getMultiPoint() {
            return this.e;
        }
    }

    @HybridPlus
    public static final class MultiPolygonGeometry extends Geometry {
        List<List<GeoPolygon>> e;

        public List<List<GeoPolygon>> getMultiPolygon() {
            return this.e;
        }

        public List<List<MapPolygon>> createMapPolygons() {
            List<List<MapPolygon>> linkedList = new LinkedList();
            for (List<GeoPolygon> list : this.e) {
                List linkedList2 = new LinkedList();
                for (GeoPolygon mapPolygon : list) {
                    linkedList2.add(new MapPolygon(mapPolygon));
                }
                linkedList.add(linkedList2);
            }
            return linkedList;
        }
    }

    @HybridPlus
    public static final class PointGeometry extends Geometry {
        GeoCoordinate e;

        public GeoCoordinate getPoint() {
            return this.e;
        }
    }

    @HybridPlus
    public static final class PolygonGeometry extends Geometry {
        List<GeoPolygon> e;

        public List<GeoPolygon> getPolygon() {
            return this.e;
        }

        public List<MapPolygon> createMapPolygons() {
            List<MapPolygon> linkedList = new LinkedList();
            for (GeoPolygon mapPolygon : this.e) {
                linkedList.add(new MapPolygon(mapPolygon));
            }
            return linkedList;
        }
    }

    private Result() {
    }

    Result(LocationResponse locationResponse) {
        if (locationResponse.locations != null) {
            for (com.here.android.mpa.customlocation.LocationResponse.Location location : locationResponse.locations) {
                Location location2 = new Location();
                location2.m = location.city;
                if (location.coordinate != null) {
                    location2.a = new GeoCoordinate(location.coordinate.latitude, location.coordinate.longitude);
                }
                location2.p = location.country;
                location2.n = location.county;
                location2.b = new ArrayList();
                if (location.customAttributes != null) {
                    for (CustomAttribute customAttribute : location.customAttributes) {
                        location2.b.add(new CustomAttribute(customAttribute.name, customAttribute.value));
                    }
                }
                location2.c = location.customerLocationId;
                location2.r = location.description;
                location2.d = location.distance;
                location2.e = location.webURL;
                location2.f = location.fax;
                location2.k = location.houseNumber;
                location2.g = location.name1;
                location2.h = location.name2;
                location2.i = location.name3;
                location2.j = location.phone;
                location2.q = location.postalCode;
                if (location.routeCoordinate != null) {
                    location2.s = new GeoCoordinate(location.routeCoordinate.latitude, location.routeCoordinate.longitude);
                }
                location2.o = location.state;
                location2.l = location.street;
                this.b.add(location2);
            }
        }
    }

    Result(d dVar) {
        if (dVar.a != null) {
            this.a = dVar.a;
        }
    }

    public List<Location> getLocations() {
        return this.b;
    }

    public List<Geometry> getGeometries() {
        return this.a;
    }
}
