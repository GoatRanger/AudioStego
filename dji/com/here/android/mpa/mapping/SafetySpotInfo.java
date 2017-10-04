package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.SafetySpotInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class SafetySpotInfo {
    private SafetySpotInfoImpl a;

    @HybridPlus
    public enum Type {
        UNDEFINED(0),
        SPEED_CAMERA(1),
        REDLIGHT_CAMERA(2),
        SPEED_REDLIGHT_CAMERA(3);
        
        private final int a;

        private Type(int i) {
            this.a = i;
        }
    }

    private SafetySpotInfo(SafetySpotInfoImpl safetySpotInfoImpl) {
        this.a = safetySpotInfoImpl;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public Type getType() {
        return this.a.b();
    }

    public int getHeading1Deg() {
        return this.a.getHeading1Deg();
    }

    public int getHeading2Deg() {
        return this.a.getHeading2Deg();
    }

    public int getSpeedLimit1() {
        return this.a.getSpeedLimit1();
    }

    public int getSpeedLimit2() {
        return this.a.getSpeedLimit2();
    }

    public String toString() {
        return this.a.toString();
    }

    static {
        SafetySpotInfoImpl.a(new am<SafetySpotInfo, SafetySpotInfoImpl>() {
            public SafetySpotInfo a(SafetySpotInfoImpl safetySpotInfoImpl) {
                if (safetySpotInfoImpl != null) {
                    return new SafetySpotInfo(safetySpotInfoImpl);
                }
                return null;
            }
        });
    }
}
