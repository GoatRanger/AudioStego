package com.nokia.maps;

import com.here.android.mpa.common.TransitType;
import com.nokia.maps.annotation.OnlineNative;

public class TransitTypeImpl {
    @OnlineNative
    public static TransitType valueOf(int i) {
        switch (i) {
            case 0:
                return TransitType.BUS_PUBLIC;
            case 1:
                return TransitType.BUS_TOURISTIC;
            case 2:
                return TransitType.BUS_INTERCITY;
            case 3:
                return TransitType.BUS_EXPRESS;
            case 4:
                return TransitType.RAIL_METRO;
            case 5:
                return TransitType.RAIL_LIGHT;
            case 6:
                return TransitType.RAIL_REGIONAL;
            case 7:
                return TransitType.TRAIN_REGIONAL;
            case 8:
                return TransitType.TRAIN_INTERCITY;
            case 9:
                return TransitType.TRAIN_HIGH_SPEED;
            case 10:
                return TransitType.MONORAIL;
            case 11:
                return TransitType.AERIAL;
            case 12:
                return TransitType.INCLINED;
            case 13:
                return TransitType.WATER;
            case 14:
                return TransitType.AIRLINE;
            default:
                return TransitType.UNKNOWN;
        }
    }
}
