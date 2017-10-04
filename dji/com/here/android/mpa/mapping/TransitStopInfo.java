package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.nokia.maps.TransitStopInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.EnumSet;
import java.util.List;

@Online
public final class TransitStopInfo {
    private TransitStopInfoImpl a;

    @Online
    public enum Attribute {
        CONNECTED_STOP,
        INTER_STOPS_TRANSFER,
        TERMINATING_STOP,
        DEPARTING_STOP,
        PAY_CAR_PARKING,
        FREE_CAR_PARKING,
        BICYCLE_PARKING,
        SMOKING_ALLOWED,
        TOILETS,
        WIRELESS_INTERNET,
        CELLULAR_SERVICE,
        TICKET_MACHINES,
        LUGGAGE_LOCKERS,
        LUGGAGE_CHECKS,
        ATTENDANT_BOOTH,
        SHOPS,
        OUTDOOR,
        COVERED,
        PEDESTRIAN_RAMPS,
        ELEVATORS,
        ESCALATORS,
        STAIRS
    }

    @Online
    public enum ParkingSize {
        UNKNOWN,
        FIVE_OR_LESS,
        TEN_OR_LESS,
        FIFTY_OR_LESS,
        TWO_HUNDRED_OR_LESS,
        MORE_THAN_200
    }

    private TransitStopInfo(TransitStopInfoImpl transitStopInfoImpl) {
        this.a = transitStopInfoImpl;
    }

    public Identifier getId() {
        return this.a.b();
    }

    public EnumSet<TransitType> getTransitTypes() {
        return this.a.c();
    }

    public String getOfficialName() {
        return this.a.getOfficialName();
    }

    public String getInformalName() {
        return this.a.getInformalName();
    }

    public List<Identifier> getLines() {
        return this.a.i();
    }

    public List<Identifier> getSystems() {
        return this.a.h();
    }

    public EnumSet<Attribute> getAttributes() {
        return this.a.e();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public List<Identifier> getDepartingLines() {
        return this.a.k();
    }

    public List<Identifier> getDepartingSystems() {
        return this.a.j();
    }

    public OperatingHours getOperatingHours() {
        return this.a.d();
    }

    public OperatingHours getParkingHours() {
        return this.a.g();
    }

    public ParkingSize getParkingSize() {
        return this.a.f();
    }

    public List<Identifier> getTerminatingLines() {
        return this.a.m();
    }

    public List<Identifier> getTerminatingSystems() {
        return this.a.l();
    }

    public List<Identifier> getTransfers() {
        return this.a.n();
    }

    static {
        TransitStopInfoImpl.a(new am<TransitStopInfo, TransitStopInfoImpl>() {
            public TransitStopInfo a(TransitStopInfoImpl transitStopInfoImpl) {
                if (transitStopInfoImpl != null) {
                    return new TransitStopInfo(transitStopInfoImpl);
                }
                return null;
            }
        });
    }
}
