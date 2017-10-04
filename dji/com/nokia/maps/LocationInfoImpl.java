package com.nokia.maps;

import com.here.android.mpa.mapping.LocationInfo;
import com.here.android.mpa.mapping.LocationInfo.Field;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class LocationInfoImpl extends BaseNativeObject {
    private static am<LocationInfo, LocationInfoImpl> a = null;
    private static k<LocationInfo, LocationInfoImpl> b = null;

    private final native void destroyLocationInfoNative();

    private final native String getFieldNative(int i);

    private final native boolean hasFieldNative(int i);

    private native boolean isEqualNative(LocationInfoImpl locationInfoImpl);

    private final native void setFieldNative(int i, String str);

    static {
        ce.a(LocationInfo.class);
    }

    static LocationInfo a(LocationInfoImpl locationInfoImpl) {
        if (locationInfoImpl != null) {
            return (LocationInfo) a.a(locationInfoImpl);
        }
        return null;
    }

    public static void a(k<LocationInfo, LocationInfoImpl> kVar, am<LocationInfo, LocationInfoImpl> amVar) {
        b = kVar;
        a = amVar;
    }

    static LocationInfoImpl a(LocationInfo locationInfo) {
        if (b != null) {
            return (LocationInfoImpl) b.a(locationInfo);
        }
        return null;
    }

    @OnlineNative
    private LocationInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyLocationInfoNative();
    }

    public boolean a(Field field) {
        return hasFieldNative(c(field));
    }

    public String b(Field field) {
        return getFieldNative(c(field));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (LocationInfoImpl.class.isInstance(obj)) {
            obj = (LocationInfoImpl) obj;
        } else if (!LocationInfo.class.isInstance(obj)) {
            return false;
        } else {
            obj = a((LocationInfo) obj);
        }
        return isEqualNative(obj);
    }

    public int hashCode() {
        int i = 1;
        for (Field b : Field.values()) {
            i = (i * 31) + b(b).hashCode();
        }
        return i;
    }

    private static int c(Field field) {
        switch (field) {
            case ADDR_COUNTRY_CODE:
                return 0;
            case ADDR_COUNTRY_NAME:
                return 1;
            case ADDR_STATE_NAME:
                return 2;
            case ADDR_STATE_CODE:
                return 3;
            case ADDR_PROVINCE_NAME:
                return 4;
            case ADDR_COUNTY_NAME:
                return 5;
            case ADDR_CITY_NAME:
                return 6;
            case ADDR_DISTRICT_NAME:
                return 7;
            case ADDR_POSTAL_CODE:
                return 8;
            case ADDR_TOWNSHIP_NAME:
                return 9;
            case ADDR_NEIGHBORHOOD_NAME:
                return 10;
            case ADDR_STREET_NAME:
                return 11;
            case ADDR_HOUSE_NUMBER:
                return 12;
            case ADDR_CONTINENT_NAME:
                return 13;
            case ADDR_TYPE_NAME:
                return 14;
            case ADDR_POPULATION:
                return 15;
            case ADDR_BUILDING_NAME:
                return 16;
            case ADDR_BUILDING_FLOOR:
                return 17;
            case ADDR_BUILDING_ROOM:
                return 18;
            case ADDR_BUILDING_ZONE:
                return 19;
            case PLACE_NAME:
                return 20;
            case PLACE_PRE_NAME:
                return 21;
            case PLACE_POST_NAME:
                return 22;
            case PLACE_TYPE:
                return 23;
            case PLACE_CATEGORY:
                return 24;
            case PLACE_CATEGORY_ID:
                return 25;
            case PLACE_DESCRIPTION:
                return 26;
            case PLACE_PHONE_NUMBER:
                return 27;
            case PLACE_URL:
                return 28;
            case PLACE_PREMIUM_URL_ID:
                return 29;
            case PLACE_PREMIUM_NODE_ID:
                return 30;
            case PLACE_ADVERTISEMENT_STRING:
                return 31;
            case TZ_OFFSET_MINUTES:
                return 32;
            case OTHER_DATA:
                return 33;
            case PLACE_ICON_ID:
                return 34;
            case LOCATION_TYPE:
                return 35;
            case LOCATION_META:
                return 36;
            case LOCATION_TEXT:
                return 37;
            case ADDR_AREA_ID:
                return 38;
            case FOREIGN_ID:
                return 39;
            case FOREIGN_ID_SOURCE:
                return 40;
            default:
                throw new IllegalArgumentException("Field enum value not recogized.");
        }
    }
}
