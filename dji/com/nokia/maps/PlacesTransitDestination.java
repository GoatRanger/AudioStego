package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.TransitDestination;

public class PlacesTransitDestination {
    private static k<TransitDestination, PlacesTransitDestination> a;
    private static am<TransitDestination, PlacesTransitDestination> b;
    @SerializedName("destination")
    private String m_destination;
    @SerializedName("line")
    private String m_line;

    public static void a(k<TransitDestination, PlacesTransitDestination> kVar, am<TransitDestination, PlacesTransitDestination> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitDestination a(TransitDestination transitDestination) {
        return (PlacesTransitDestination) a.a(transitDestination);
    }

    static TransitDestination a(PlacesTransitDestination placesTransitDestination) {
        if (placesTransitDestination != null) {
            return (TransitDestination) b.a(placesTransitDestination);
        }
        return null;
    }

    static {
        ce.a(TransitDestination.class);
    }

    public String a() {
        return em.a(this.m_destination);
    }

    public String b() {
        return em.a(this.m_line);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_destination == null ? 0 : this.m_destination.hashCode()) + 31) * 31;
        if (this.m_line != null) {
            i = this.m_line.hashCode();
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
            obj = (PlacesTransitDestination) obj;
        } else if (TransitDestination.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitDestination) obj);
        }
        if (this.m_destination == null) {
            if (!TextUtils.isEmpty(obj.m_destination)) {
                return false;
            }
        } else if (!this.m_destination.equals(obj.m_destination)) {
            return false;
        }
        if (this.m_line == null) {
            if (TextUtils.isEmpty(obj.m_line)) {
                return true;
            }
            return false;
        } else if (this.m_line.equals(obj.m_line)) {
            return true;
        } else {
            return false;
        }
    }
}
