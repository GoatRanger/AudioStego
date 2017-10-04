package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.TrafficEventImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Date;
import java.util.List;

@HybridPlus
public final class TrafficEvent {
    public static final int DATA_UNAVAILABLE = -1;
    private TrafficEventImpl a;

    @HybridPlus
    public interface Listener<T> {
        void onComplete(T t);
    }

    @HybridPlus
    public enum Severity {
        UNDEFINED(0),
        NORMAL(1),
        HIGH(2),
        VERY_HIGH(4),
        BLOCKING(8);
        
        private final int a;

        private Severity(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    private TrafficEvent(TrafficEventImpl trafficEventImpl) {
        this.a = trafficEventImpl;
    }

    public String getEventText() {
        return this.a.getEventText();
    }

    public int getDistanceTo(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate);
    }

    public boolean isOnRoute(Route route) {
        return this.a.a(route);
    }

    public List<String> getAffectedStreets() {
        return this.a.a();
    }

    public void getAffectedStreets(Listener<List<String>> listener) {
        this.a.a((Listener) listener);
    }

    public String getFirstAffectedStreet() {
        return this.a.b();
    }

    public void getFirstAffectedStreet(Listener<String> listener) {
        this.a.b((Listener) listener);
    }

    public GeoBoundingBox getAffectedArea() {
        return this.a.e();
    }

    public Image getIconOnRoute() {
        return this.a.f();
    }

    public Image getIconOffRoute() {
        return this.a.g();
    }

    public List<RoadElement> getAffectedRoadElements() {
        return this.a.h();
    }

    public int getAffectedLength() {
        return this.a.getAffectedLength();
    }

    public int getSpeedLimit() {
        return this.a.getSpeedLimit();
    }

    public int getEstimatedSpeedLimit() {
        return this.a.getEstimatedSpeedLimit();
    }

    public Date getActivationDate() {
        return this.a.i();
    }

    public Date getUpdateDate() {
        return this.a.j();
    }

    public boolean isVisible() {
        return this.a.isVisible();
    }

    public boolean isActive() {
        return this.a.isActive();
    }

    public boolean isReroutable() {
        return this.a.isReroutable();
    }

    public boolean isFlow() {
        return this.a.isFlow();
    }

    public boolean isIncident() {
        return this.a.isIncident();
    }

    public Severity getSeverity() {
        return this.a.k();
    }

    public String getShortText() {
        return this.a.getShortText();
    }

    public short getPenalty() {
        return this.a.getPenalty();
    }

    public List<String> getFromStreets() {
        return this.a.d();
    }

    public void getFromStreets(Listener<List<String>> listener) {
        this.a.d((Listener) listener);
    }

    public List<String> getToStreets() {
        return this.a.c();
    }

    public void getToStreets(Listener<List<String>> listener) {
        this.a.c((Listener) listener);
    }

    public String toString() {
        return this.a.toString();
    }

    static {
        TrafficEventImpl.a(new k<TrafficEvent, TrafficEventImpl>() {
            public TrafficEventImpl a(TrafficEvent trafficEvent) {
                return trafficEvent.a;
            }
        }, new am<TrafficEvent, TrafficEventImpl>() {
            public TrafficEvent a(TrafficEventImpl trafficEventImpl) {
                if (trafficEventImpl != null) {
                    return new TrafficEvent(trafficEventImpl);
                }
                return null;
            }
        });
    }
}
