package com.nokia.maps;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.OperatingHours;
import com.here.android.mpa.mapping.TransitSystemInfo;
import com.here.android.mpa.mapping.TransitSystemInfo.Attribute;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;

@Online
public class TransitSystemInfoImpl extends BaseNativeObject {
    private static am<TransitSystemInfo, TransitSystemInfoImpl> b;
    private cq a = new cq(TransitSystemInfoImpl.class.getName());

    private native void destroyTransitSystemInfoNative();

    private native int[] getAttributesNative();

    private final native OperatingHoursImpl getBicycleHoursNative();

    private final native ImageImpl getCompanyLogoNative();

    private final native IdentifierImpl getIdNative();

    private final native ImageImpl getSystemAccessLogoNative();

    private final native ImageImpl getSystemLogoNative();

    public final native String getCompanyInformalName();

    public final native String getCompanyOfficialName();

    public final native String getCompanyPhone();

    public final native String getCompanyRoutePlannerUrl();

    public final native String getCompanyScheduleUrl();

    public final native String getCompanyShortName();

    public final native String getCompanyWebsiteUrl();

    public final native String getSystemInformalName();

    public final native String getSystemOfficialName();

    public final native String getSystemShortName();

    public final native String getSystemWebsiteUrl();

    @OnlineNative
    private TransitSystemInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitSystemInfoNative();
    }

    public Identifier a() {
        return IdentifierImpl.a(getIdNative());
    }

    public final EnumSet<Attribute> b() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int a : getAttributesNative()) {
            noneOf.add(a(a));
        }
        return noneOf;
    }

    public final OperatingHours c() {
        return OperatingHoursImpl.a(getBicycleHoursNative());
    }

    public final Image d() {
        return ImageImpl.a(getSystemLogoNative());
    }

    public final Image e() {
        return ImageImpl.a(getSystemAccessLogoNative());
    }

    public final Image f() {
        return ImageImpl.a(getCompanyLogoNative());
    }

    private static final Attribute a(int i) {
        switch (i) {
            case 0:
                return Attribute.COMPANY_LOGO;
            case 1:
                return Attribute.SYSTEM_LOGO;
            case 2:
                return Attribute.SYSTEM_ACCESS_LOGO;
            default:
                throw new IllegalArgumentException("Enum value not supported");
        }
    }

    public static void a(am<TransitSystemInfo, TransitSystemInfoImpl> amVar) {
        b = amVar;
    }

    static TransitSystemInfo a(TransitSystemInfoImpl transitSystemInfoImpl) {
        if (transitSystemInfoImpl != null) {
            return (TransitSystemInfo) b.a(transitSystemInfoImpl);
        }
        return null;
    }

    static {
        ce.a(TransitSystemInfo.class);
    }
}
