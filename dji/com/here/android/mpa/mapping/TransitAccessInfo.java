package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.nokia.maps.TransitAccessInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.EnumSet;

@Online
public final class TransitAccessInfo {
    private TransitAccessInfoImpl a;

    @Online
    public enum Attribute {
        ALLOWS_ENTERING,
        ALLOWS_EXITING,
        ACCESSIBLE_TO_DISABLED,
        LEVEL_DIFFERENT_FROM_GROUND,
        HAS_NAMES
    }

    @Online
    public enum Method {
        STAIRS,
        ESCALATOR,
        ELEVATOR,
        PEDESTRIAN_RAMP
    }

    private TransitAccessInfo(TransitAccessInfoImpl transitAccessInfoImpl) {
        this.a = transitAccessInfoImpl;
    }

    public String getName() {
        return this.a.getName();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public Identifier getId() {
        return this.a.b();
    }

    public Identifier getStopId() {
        return this.a.c();
    }

    public EnumSet<TransitType> getTransitTypes() {
        return this.a.d();
    }

    public EnumSet<Attribute> getAttributes() {
        return this.a.e();
    }

    public EnumSet<Method> getEntranceMethods() {
        return this.a.f();
    }

    public EnumSet<Method> getExitMethods() {
        return this.a.g();
    }

    public int getLevel() {
        return this.a.getLevel();
    }

    public OperatingHours getOpeningHours() {
        return this.a.h();
    }

    static {
        TransitAccessInfoImpl.a(new am<TransitAccessInfo, TransitAccessInfoImpl>() {
            public TransitAccessInfo a(TransitAccessInfoImpl transitAccessInfoImpl) {
                if (transitAccessInfoImpl != null) {
                    return new TransitAccessInfo(transitAccessInfoImpl);
                }
                return null;
            }
        });
    }
}
