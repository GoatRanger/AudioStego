package com.nokia.maps.a;

import com.here.a.a.a.i.d;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.urbanmobility.TransportType;

public class ba {
    public static TransportType a(d dVar) {
        switch (dVar) {
            case TRAIN_HIGH_SPEED:
                return TransportType.TRAIN_HIGH_SPEED;
            case TRAIN_INTERCITY:
                return TransportType.TRAIN_INTERCITY;
            case TRAIN_INTERREGIONAL_AND_FAST:
                return TransportType.TRAIN_INTERREGIONAL_AND_FAST;
            case TRAIN_REGIONAL_AND_OTHER:
                return TransportType.TRAIN_REGIONAL_AND_OTHER;
            case TRAIN_CITY:
                return TransportType.TRAIN_CITY;
            case BUS:
                return TransportType.BUS;
            case WATER_BOAT_OR_FERRYS:
                return TransportType.WATER_BOAT_OR_FERRYS;
            case RAIL_UNDEGROUND_OR_SUBWAY:
                return TransportType.RAIL_UNDEGROUND_OR_SUBWAY;
            case RAIL_TRAM:
                return TransportType.RAIL_TRAM;
            case ORDERED_SERVICES_OR_TAXI:
                return TransportType.ORDERED_SERVICES_OR_TAXI;
            case RAIL_INCLINED:
                return TransportType.RAIL_INCLINED;
            case AERIAL:
                return TransportType.AERIAL;
            case BUS_RAPID:
                return TransportType.BUS_RAPID;
            case RAIL_MONORAIL:
                return TransportType.RAIL_MONORAIL;
            case FLIGHT:
                return TransportType.FLIGHT;
            default:
                return TransportType.UNKNOWN;
        }
    }

    public static d a(TransportType transportType) {
        switch (transportType) {
            case TRAIN_HIGH_SPEED:
                return d.TRAIN_HIGH_SPEED;
            case TRAIN_INTERCITY:
                return d.TRAIN_INTERCITY;
            case TRAIN_INTERREGIONAL_AND_FAST:
                return d.TRAIN_INTERREGIONAL_AND_FAST;
            case TRAIN_REGIONAL_AND_OTHER:
                return d.TRAIN_REGIONAL_AND_OTHER;
            case TRAIN_CITY:
                return d.TRAIN_CITY;
            case BUS:
                return d.BUS;
            case WATER_BOAT_OR_FERRYS:
                return d.WATER_BOAT_OR_FERRYS;
            case RAIL_UNDEGROUND_OR_SUBWAY:
                return d.RAIL_UNDEGROUND_OR_SUBWAY;
            case RAIL_TRAM:
                return d.RAIL_TRAM;
            case ORDERED_SERVICES_OR_TAXI:
                return d.ORDERED_SERVICES_OR_TAXI;
            case RAIL_INCLINED:
                return d.RAIL_INCLINED;
            case AERIAL:
                return d.AERIAL;
            case BUS_RAPID:
                return d.BUS_RAPID;
            case RAIL_MONORAIL:
                return d.RAIL_MONORAIL;
            case FLIGHT:
                return d.FLIGHT;
            default:
                return d.UNKNOWN;
        }
    }

    public static TransportType a(TransitType transitType) {
        if (TransitType.AERIAL == transitType) {
            return TransportType.AERIAL;
        }
        if (TransitType.BUS_PUBLIC == transitType) {
            return TransportType.BUS;
        }
        if (TransitType.BUS_EXPRESS == transitType || TransitType.BUS_INTERCITY == transitType) {
            return TransportType.BUS_RAPID;
        }
        if (TransitType.RAIL_LIGHT == transitType) {
            return TransportType.RAIL_TRAM;
        }
        if (TransitType.RAIL_METRO == transitType) {
            return TransportType.RAIL_UNDEGROUND_OR_SUBWAY;
        }
        if (TransitType.RAIL_REGIONAL == transitType) {
            return TransportType.TRAIN_CITY;
        }
        if (TransitType.TRAIN_REGIONAL == transitType) {
            return TransportType.TRAIN_REGIONAL_AND_OTHER;
        }
        if (TransitType.TRAIN_INTERCITY == transitType) {
            return TransportType.TRAIN_INTERCITY;
        }
        if (TransitType.TRAIN_HIGH_SPEED == transitType) {
            return TransportType.TRAIN_HIGH_SPEED;
        }
        if (TransitType.MONORAIL == transitType) {
            return TransportType.RAIL_MONORAIL;
        }
        if (TransitType.INCLINED == transitType) {
            return TransportType.RAIL_INCLINED;
        }
        if (TransitType.WATER == transitType) {
            return TransportType.WATER_BOAT_OR_FERRYS;
        }
        if (TransitType.AIRLINE == transitType) {
            return TransportType.FLIGHT;
        }
        if (TransitType.TRAIN_INTERREGIONAL_AND_FAST == transitType) {
            return TransportType.TRAIN_INTERREGIONAL_AND_FAST;
        }
        if (TransitType.ORDERED_SERVICES_OR_TAXI == transitType) {
            return TransportType.ORDERED_SERVICES_OR_TAXI;
        }
        return TransportType.UNKNOWN;
    }

    public static TransitType b(TransportType transportType) {
        if (TransportType.AERIAL == transportType) {
            return TransitType.AERIAL;
        }
        if (TransportType.BUS == transportType) {
            return TransitType.BUS_PUBLIC;
        }
        if (TransportType.BUS_RAPID == transportType) {
            return TransitType.BUS_EXPRESS;
        }
        if (TransportType.RAIL_INCLINED == transportType) {
            return TransitType.INCLINED;
        }
        if (TransportType.RAIL_MONORAIL == transportType) {
            return TransitType.MONORAIL;
        }
        if (TransportType.RAIL_TRAM == transportType) {
            return TransitType.RAIL_LIGHT;
        }
        if (TransportType.RAIL_UNDEGROUND_OR_SUBWAY == transportType) {
            return TransitType.RAIL_METRO;
        }
        if (TransportType.TRAIN_CITY == transportType) {
            return TransitType.RAIL_REGIONAL;
        }
        if (TransportType.TRAIN_HIGH_SPEED == transportType) {
            return TransitType.TRAIN_HIGH_SPEED;
        }
        if (TransportType.TRAIN_INTERCITY == transportType) {
            return TransitType.TRAIN_INTERCITY;
        }
        if (TransportType.TRAIN_REGIONAL_AND_OTHER == transportType) {
            return TransitType.TRAIN_REGIONAL;
        }
        if (TransportType.WATER_BOAT_OR_FERRYS == transportType) {
            return TransitType.WATER;
        }
        if (TransportType.FLIGHT == transportType) {
            return TransitType.AIRLINE;
        }
        if (TransportType.TRAIN_INTERREGIONAL_AND_FAST == transportType) {
            return TransitType.TRAIN_INTERREGIONAL_AND_FAST;
        }
        if (TransportType.ORDERED_SERVICES_OR_TAXI == transportType) {
            return TransitType.ORDERED_SERVICES_OR_TAXI;
        }
        return TransitType.UNKNOWN;
    }
}
