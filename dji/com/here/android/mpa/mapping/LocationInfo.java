package com.here.android.mpa.mapping;

import com.nokia.maps.LocationInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public final class LocationInfo {
    private LocationInfoImpl a;

    @Online
    public enum Field {
        ADDR_COUNTRY_CODE,
        ADDR_COUNTRY_NAME,
        ADDR_STATE_NAME,
        ADDR_STATE_CODE,
        ADDR_PROVINCE_NAME,
        ADDR_COUNTY_NAME,
        ADDR_CITY_NAME,
        ADDR_DISTRICT_NAME,
        ADDR_POSTAL_CODE,
        ADDR_TOWNSHIP_NAME,
        ADDR_NEIGHBORHOOD_NAME,
        ADDR_STREET_NAME,
        ADDR_HOUSE_NUMBER,
        ADDR_CONTINENT_NAME,
        ADDR_TYPE_NAME,
        ADDR_POPULATION,
        ADDR_BUILDING_NAME,
        ADDR_BUILDING_FLOOR,
        ADDR_BUILDING_ROOM,
        ADDR_BUILDING_ZONE,
        PLACE_NAME,
        PLACE_PRE_NAME,
        PLACE_POST_NAME,
        PLACE_TYPE,
        PLACE_CATEGORY,
        PLACE_CATEGORY_ID,
        PLACE_DESCRIPTION,
        PLACE_PHONE_NUMBER,
        PLACE_URL,
        PLACE_PREMIUM_URL_ID,
        PLACE_PREMIUM_NODE_ID,
        PLACE_ADVERTISEMENT_STRING,
        TZ_OFFSET_MINUTES,
        OTHER_DATA,
        PLACE_ICON_ID,
        LOCATION_TYPE,
        LOCATION_META,
        LOCATION_TEXT,
        ADDR_AREA_ID,
        FOREIGN_ID,
        FOREIGN_ID_SOURCE
    }

    private LocationInfo(LocationInfoImpl locationInfoImpl) {
        this.a = locationInfoImpl;
    }

    public boolean hasField(Field field) {
        return this.a.a(field);
    }

    public String getField(Field field) {
        return this.a.b(field);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (LocationInfo.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    static {
        LocationInfoImpl.a(new k<LocationInfo, LocationInfoImpl>() {
            public LocationInfoImpl a(LocationInfo locationInfo) {
                return locationInfo.a;
            }
        }, new am<LocationInfo, LocationInfoImpl>() {
            public LocationInfo a(LocationInfoImpl locationInfoImpl) {
                if (locationInfoImpl != null) {
                    return new LocationInfo(locationInfoImpl);
                }
                return null;
            }
        });
    }
}
