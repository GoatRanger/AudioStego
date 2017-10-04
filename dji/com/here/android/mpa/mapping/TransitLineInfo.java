package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.nokia.maps.TransitLineInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.EnumSet;

@Online
public final class TransitLineInfo {
    private TransitLineInfoImpl a;

    @Online
    public enum Attribute {
        EXPRESS,
        ACCESSIBLE_TO_DISABLED,
        LUGGAGE_RACKS,
        ONBOARD_TOILETS,
        ONBOARD_FOOD,
        SMOKING_ALLOWED,
        SLEEPING_CARS
    }

    private TransitLineInfo(TransitLineInfoImpl transitLineInfoImpl) {
        this.a = transitLineInfoImpl;
    }

    public Identifier getId() {
        return this.a.a();
    }

    public Identifier getSystemId() {
        return this.a.b();
    }

    public TransitType getTransitType() {
        return this.a.getTransitType();
    }

    public String getOfficialName() {
        return this.a.getOfficialName();
    }

    public String getInformalName() {
        return this.a.getInformalName();
    }

    public String getShortName() {
        return this.a.getShortName();
    }

    public int getColor() {
        return this.a.d();
    }

    public final EnumSet<Attribute> getAttributes() {
        return this.a.c();
    }

    static {
        TransitLineInfoImpl.a(new am<TransitLineInfo, TransitLineInfoImpl>() {
            public TransitLineInfo a(TransitLineInfoImpl transitLineInfoImpl) {
                if (transitLineInfoImpl != null) {
                    return new TransitLineInfo(transitLineInfoImpl);
                }
                return null;
            }
        });
    }
}
