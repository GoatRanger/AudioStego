package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class PlacesDiscoveryContext {
    private static am<ao, PlacesDiscoveryContext> a;
    @SerializedName("href")
    private String m_href;
    @SerializedName("location")
    private PlacesLocation m_location;
    @SerializedName("moved")
    private boolean m_moved = false;
    @SerializedName("title")
    private String m_title;
    @SerializedName("type")
    private String m_type;

    public static void a(am<ao, PlacesDiscoveryContext> amVar) {
        a = amVar;
    }

    static {
        ce.a(ao.class);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_moved ? 1231 : 1237) + (((this.m_location == null ? 0 : this.m_location.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_type != null) {
            i = this.m_type.hashCode();
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlacesDiscoveryContext placesDiscoveryContext = (PlacesDiscoveryContext) obj;
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(placesDiscoveryContext.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(placesDiscoveryContext.m_href)) {
            return false;
        }
        if (this.m_location == null) {
            if (placesDiscoveryContext.m_location != null) {
                return false;
            }
        } else if (!this.m_location.equals(placesDiscoveryContext.m_location)) {
            return false;
        }
        if (this.m_moved != placesDiscoveryContext.m_moved) {
            return false;
        }
        if (this.m_title == null) {
            if (!TextUtils.isEmpty(placesDiscoveryContext.m_title)) {
                return false;
            }
        } else if (!this.m_title.equals(placesDiscoveryContext.m_title)) {
            return false;
        }
        if (this.m_type == null) {
            if (TextUtils.isEmpty(placesDiscoveryContext.m_type)) {
                return true;
            }
            return false;
        } else if (this.m_type.equals(placesDiscoveryContext.m_type)) {
            return true;
        } else {
            return false;
        }
    }
}
