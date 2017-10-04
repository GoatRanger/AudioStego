package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.mapping.OperatingHours;
import com.here.android.mpa.mapping.TransitStopInfo;
import com.here.android.mpa.mapping.TransitStopInfo.Attribute;
import com.here.android.mpa.mapping.TransitStopInfo.ParkingSize;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;
import java.util.List;

@Online
public class TransitStopInfoImpl extends BaseNativeObject {
    private static am<TransitStopInfo, TransitStopInfoImpl> b;
    private cq a = new cq(TransitStopInfoImpl.class.getName());

    private native void destroyTransitStopInfoNative();

    private native int[] getAttributesNative();

    private final native GeoCoordinateImpl getCoordinateNative();

    private final native IdentifierImpl[] getDepartingLinesNative();

    private final native IdentifierImpl[] getDepartingSystemsNative();

    private final native IdentifierImpl getIdNative();

    private final native IdentifierImpl[] getLinesNative();

    private final native OperatingHoursImpl getOperatingHoursNative();

    private final native OperatingHoursImpl getParkingHoursNative();

    private final native int getParkingSizeNative();

    private final native IdentifierImpl[] getSystemsNative();

    private final native IdentifierImpl[] getTerminatingLinesNative();

    private final native IdentifierImpl[] getTerminatingSystemsNative();

    private final native IdentifierImpl[] getTransfersNative();

    private native int[] getTransitTypesNative();

    public final native String getInformalName();

    public final native String getOfficialName();

    public final native String getPlacesId();

    @OnlineNative
    private TransitStopInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitStopInfoNative();
    }

    public final GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public final Identifier b() {
        return IdentifierImpl.a(getIdNative());
    }

    public final EnumSet<TransitType> c() {
        EnumSet<TransitType> noneOf = EnumSet.noneOf(TransitType.class);
        for (int valueOf : getTransitTypesNative()) {
            noneOf.add(TransitTypeImpl.valueOf(valueOf));
        }
        return noneOf;
    }

    public final OperatingHours d() {
        return OperatingHoursImpl.a(getOperatingHoursNative());
    }

    public final EnumSet<Attribute> e() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int b : getAttributesNative()) {
            noneOf.add(b(b));
        }
        return noneOf;
    }

    public final ParkingSize f() {
        return a(getParkingSizeNative());
    }

    public final OperatingHours g() {
        return OperatingHoursImpl.a(getParkingHoursNative());
    }

    public final List<Identifier> h() {
        return IdentifierImpl.a(getSystemsNative());
    }

    public final List<Identifier> i() {
        return IdentifierImpl.a(getLinesNative());
    }

    public final List<Identifier> j() {
        return IdentifierImpl.a(getDepartingSystemsNative());
    }

    public final List<Identifier> k() {
        return IdentifierImpl.a(getDepartingLinesNative());
    }

    public final List<Identifier> l() {
        return IdentifierImpl.a(getTerminatingSystemsNative());
    }

    public final List<Identifier> m() {
        return IdentifierImpl.a(getTerminatingLinesNative());
    }

    public final List<Identifier> n() {
        return IdentifierImpl.a(getTransfersNative());
    }

    private static final ParkingSize a(int i) {
        switch (i) {
            case 1:
                return ParkingSize.FIVE_OR_LESS;
            case 2:
                return ParkingSize.TEN_OR_LESS;
            case 3:
                return ParkingSize.FIFTY_OR_LESS;
            case 4:
                return ParkingSize.TWO_HUNDRED_OR_LESS;
            case 5:
                return ParkingSize.MORE_THAN_200;
            default:
                return ParkingSize.UNKNOWN;
        }
    }

    private static final Attribute b(int i) {
        switch (i) {
            case 0:
                return Attribute.CONNECTED_STOP;
            case 1:
                return Attribute.INTER_STOPS_TRANSFER;
            case 2:
                return Attribute.TERMINATING_STOP;
            case 3:
                return Attribute.DEPARTING_STOP;
            case 4:
                return Attribute.PAY_CAR_PARKING;
            case 5:
                return Attribute.FREE_CAR_PARKING;
            case 6:
                return Attribute.BICYCLE_PARKING;
            case 7:
                return Attribute.SMOKING_ALLOWED;
            case 8:
                return Attribute.TOILETS;
            case 9:
                return Attribute.WIRELESS_INTERNET;
            case 10:
                return Attribute.CELLULAR_SERVICE;
            case 11:
                return Attribute.TICKET_MACHINES;
            case 12:
                return Attribute.LUGGAGE_LOCKERS;
            case 13:
                return Attribute.LUGGAGE_CHECKS;
            case 14:
                return Attribute.ATTENDANT_BOOTH;
            case 15:
                return Attribute.SHOPS;
            case 16:
                return Attribute.OUTDOOR;
            case 17:
                return Attribute.COVERED;
            case 18:
                return Attribute.PEDESTRIAN_RAMPS;
            case 19:
                return Attribute.ELEVATORS;
            case 20:
                return Attribute.ESCALATORS;
            case 21:
                return Attribute.STAIRS;
            default:
                throw new IllegalArgumentException("Enum value not supported.");
        }
    }

    public static void a(am<TransitStopInfo, TransitStopInfoImpl> amVar) {
        b = amVar;
    }

    static TransitStopInfo a(TransitStopInfoImpl transitStopInfoImpl) {
        if (transitStopInfoImpl != null) {
            return (TransitStopInfo) b.a(transitStopInfoImpl);
        }
        return null;
    }

    static {
        ce.a(TransitStopInfo.class);
    }
}
