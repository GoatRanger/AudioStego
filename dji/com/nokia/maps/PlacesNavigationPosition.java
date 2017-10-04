package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.NavigationPosition;
import java.util.ArrayList;
import java.util.List;

public class PlacesNavigationPosition {
    private static k<NavigationPosition, PlacesNavigationPosition> a;
    private static am<NavigationPosition, PlacesNavigationPosition> b;
    @SerializedName("accessType")
    private String m_accessType;
    @SerializedName("position")
    private List<Double> m_position = new ArrayList();

    public static void a(k<NavigationPosition, PlacesNavigationPosition> kVar, am<NavigationPosition, PlacesNavigationPosition> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesNavigationPosition a(NavigationPosition navigationPosition) {
        return (PlacesNavigationPosition) a.a(navigationPosition);
    }

    static NavigationPosition a(PlacesNavigationPosition placesNavigationPosition) {
        if (placesNavigationPosition != null) {
            return (NavigationPosition) b.a(placesNavigationPosition);
        }
        return null;
    }

    static PlacesNavigationPosition a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate == null) {
            return null;
        }
        PlacesNavigationPosition placesNavigationPosition = new PlacesNavigationPosition();
        placesNavigationPosition.m_position.add(Double.valueOf(geoCoordinate.getLatitude()));
        placesNavigationPosition.m_position.add(Double.valueOf(geoCoordinate.getLongitude()));
        placesNavigationPosition.m_accessType = "road";
        return placesNavigationPosition;
    }

    static {
        ce.a(NavigationPosition.class);
    }

    public GeoCoordinate a() {
        if (this.m_position.size() == 2) {
            return new GeoCoordinate(((Double) this.m_position.get(0)).doubleValue(), ((Double) this.m_position.get(1)).doubleValue());
        }
        return null;
    }

    public String b() {
        return em.a(this.m_accessType);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_position == null ? 0 : this.m_position.hashCode()) + 31) * 31;
        if (this.m_accessType != null) {
            i = this.m_accessType.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesNavigationPosition) obj;
        } else if (NavigationPosition.class != obj.getClass()) {
            return false;
        } else {
            obj = a((NavigationPosition) obj);
        }
        if (this.m_position == null) {
            if (obj.m_position != null) {
                return false;
            }
        } else if (!this.m_position.equals(obj.m_position)) {
            return false;
        }
        if (this.m_accessType == null) {
            if (obj.m_accessType != null) {
                return false;
            }
            return true;
        } else if (this.m_accessType.equals(obj.m_accessType)) {
            return true;
        } else {
            return false;
        }
    }
}
