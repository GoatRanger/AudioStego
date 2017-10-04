package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.NavigationPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlacesLocation {
    private static k<Location, PlacesLocation> a;
    private static am<Location, PlacesLocation> b;
    @SerializedName("access")
    private List<PlacesNavigationPosition> m_accessPoints = new ArrayList();
    @SerializedName("address")
    private PlacesAddress m_address;
    @SerializedName("bbox")
    private List<Double> m_boundingBox = new ArrayList();
    @SerializedName("id")
    private String m_id = "";
    @SerializedName("position")
    private List<Double> m_position = new ArrayList();
    @SerializedName("references")
    protected Map<String, PlacesReference> m_references = new LinkedTreeMap();

    public static void a(k<Location, PlacesLocation> kVar, am<Location, PlacesLocation> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesLocation a(Location location) {
        return (PlacesLocation) a.a(location);
    }

    static Location a(PlacesLocation placesLocation) {
        if (placesLocation != null) {
            return (Location) b.a(placesLocation);
        }
        return null;
    }

    static {
        ce.a(Location.class);
    }

    public PlacesLocation(GeoCoordinate geoCoordinate) {
        a(geoCoordinate);
    }

    public Address a() {
        return PlacesAddress.create(this.m_address);
    }

    public void a(Address address) {
        dy.a(address, "Address argument is null");
        this.m_address = PlacesAddress.a(address);
    }

    public GeoCoordinate b() {
        if (this.m_position == null || this.m_position.size() != 2) {
            return null;
        }
        return new GeoCoordinate(((Double) this.m_position.get(0)).doubleValue(), ((Double) this.m_position.get(1)).doubleValue());
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a(geoCoordinate, "GeoCoordinate argument is null");
        this.m_position.clear();
        this.m_position.add(Double.valueOf(geoCoordinate.getLatitude()));
        this.m_position.add(Double.valueOf(geoCoordinate.getLongitude()));
    }

    public GeoBoundingBox c() {
        if (this.m_boundingBox == null || this.m_boundingBox.size() != 4) {
            return null;
        }
        return new GeoBoundingBox(new GeoCoordinate(((Double) this.m_boundingBox.get(3)).doubleValue(), ((Double) this.m_boundingBox.get(0)).doubleValue()), new GeoCoordinate(((Double) this.m_boundingBox.get(1)).doubleValue(), ((Double) this.m_boundingBox.get(2)).doubleValue()));
    }

    public void a(GeoBoundingBox geoBoundingBox) {
        dy.a(geoBoundingBox, "GeoBoundingBox argument is null");
        GeoCoordinate topLeft = geoBoundingBox.getTopLeft();
        GeoCoordinate bottomRight = geoBoundingBox.getBottomRight();
        this.m_boundingBox.add(0, Double.valueOf(topLeft.getLongitude()));
        this.m_boundingBox.add(1, Double.valueOf(bottomRight.getLatitude()));
        this.m_boundingBox.add(2, Double.valueOf(bottomRight.getLongitude()));
        this.m_boundingBox.add(3, Double.valueOf(topLeft.getLatitude()));
    }

    public String d() {
        return em.a(this.m_id);
    }

    public void a(String str) {
        if (str == null) {
            str = "";
        }
        this.m_id = str;
    }

    public String b(String str) {
        return (this.m_references == null || !this.m_references.containsKey(str)) ? "" : ((PlacesReference) this.m_references.get(str)).a();
    }

    public List<NavigationPosition> e() {
        List<NavigationPosition> arrayList = new ArrayList();
        if (this.m_accessPoints != null && this.m_accessPoints.size() > 0) {
            for (PlacesNavigationPosition a : this.m_accessPoints) {
                arrayList.add(PlacesNavigationPosition.a(a));
            }
        }
        return arrayList;
    }

    public NavigationPosition f() {
        PlacesNavigationPosition placesNavigationPosition = (this.m_accessPoints == null || this.m_accessPoints.size() <= 0) ? null : (PlacesNavigationPosition) this.m_accessPoints.get(0);
        return PlacesNavigationPosition.a(placesNavigationPosition);
    }

    public void a(List<NavigationPosition> list) {
        this.m_accessPoints = new ArrayList();
        for (NavigationPosition a : list) {
            this.m_accessPoints.add(PlacesNavigationPosition.a(a));
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_position == null ? 0 : this.m_position.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + (((this.m_boundingBox == null ? 0 : this.m_boundingBox.hashCode()) + (((this.m_address == null ? 0 : this.m_address.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_references != null) {
            i = this.m_references.hashCode();
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
            obj = (PlacesLocation) obj;
        } else if (Location.class != obj.getClass()) {
            return false;
        } else {
            obj = a((Location) obj);
        }
        if (this.m_address == null) {
            if (obj.m_address != null) {
                return false;
            }
        } else if (!this.m_address.equals(obj.m_address)) {
            return false;
        }
        if (this.m_boundingBox == null) {
            if (obj.m_boundingBox != null) {
                return false;
            }
        } else if (!this.m_boundingBox.equals(obj.m_boundingBox)) {
            return false;
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_position == null) {
            if (obj.m_position != null) {
                return false;
            }
        } else if (!this.m_position.equals(obj.m_position)) {
            return false;
        }
        if (this.m_references == null) {
            if (obj.m_references != null) {
                return false;
            }
            return true;
        } else if (this.m_references.equals(obj.m_references)) {
            return true;
        } else {
            return false;
        }
    }
}
