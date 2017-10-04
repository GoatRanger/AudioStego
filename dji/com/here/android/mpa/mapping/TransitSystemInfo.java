package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.nokia.maps.TransitSystemInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.EnumSet;

@Online
public final class TransitSystemInfo {
    private TransitSystemInfoImpl a;

    @Online
    public enum Attribute {
        COMPANY_LOGO,
        SYSTEM_LOGO,
        SYSTEM_ACCESS_LOGO
    }

    private TransitSystemInfo(TransitSystemInfoImpl transitSystemInfoImpl) {
        this.a = transitSystemInfoImpl;
    }

    public Identifier getId() {
        return this.a.a();
    }

    public String getSystemOfficialName() {
        return this.a.getSystemOfficialName();
    }

    public String getSystemInformalName() {
        return this.a.getSystemInformalName();
    }

    public String getSystemShortName() {
        return this.a.getSystemShortName();
    }

    public String getSystemWebsiteUrl() {
        return this.a.getSystemWebsiteUrl();
    }

    public String getCompanyOfficialName() {
        return this.a.getCompanyOfficialName();
    }

    public String getCompanyInformalName() {
        return this.a.getCompanyInformalName();
    }

    public String getCompanyShortName() {
        return this.a.getCompanyShortName();
    }

    public String getCompanyWebsiteUrl() {
        return this.a.getCompanyWebsiteUrl();
    }

    public String getCompanyRoutePlannerUrl() {
        return this.a.getCompanyRoutePlannerUrl();
    }

    public String getCompanyScheduleUrl() {
        return this.a.getCompanyScheduleUrl();
    }

    public String getCompanyPhone() {
        return this.a.getCompanyPhone();
    }

    public EnumSet<Attribute> getAttributes() {
        return this.a.b();
    }

    public OperatingHours getBicycleHours() {
        return this.a.c();
    }

    public Image getSystemLogo() {
        return this.a.d();
    }

    public Image getSystemAccessLogo() {
        return this.a.e();
    }

    public Image getCompanyLogo() {
        return this.a.f();
    }

    static {
        TransitSystemInfoImpl.a(new am<TransitSystemInfo, TransitSystemInfoImpl>() {
            public TransitSystemInfo a(TransitSystemInfoImpl transitSystemInfoImpl) {
                if (transitSystemInfoImpl != null) {
                    return new TransitSystemInfo(transitSystemInfoImpl);
                }
                return null;
            }
        });
    }
}
