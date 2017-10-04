package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.TransitOperatorSupplier;

public class PlacesTransitOperatorSupplier {
    private static k<TransitOperatorSupplier, PlacesTransitOperatorSupplier> a;
    private static am<TransitOperatorSupplier, PlacesTransitOperatorSupplier> b;
    @SerializedName("title")
    protected String m_title;

    public static void a(k<TransitOperatorSupplier, PlacesTransitOperatorSupplier> kVar, am<TransitOperatorSupplier, PlacesTransitOperatorSupplier> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitOperatorSupplier a(TransitOperatorSupplier transitOperatorSupplier) {
        return (PlacesTransitOperatorSupplier) a.a(transitOperatorSupplier);
    }

    static TransitOperatorSupplier a(PlacesTransitOperatorSupplier placesTransitOperatorSupplier) {
        if (placesTransitOperatorSupplier != null) {
            return (TransitOperatorSupplier) b.a(placesTransitOperatorSupplier);
        }
        return null;
    }

    static {
        ce.a(TransitOperatorSupplier.class);
    }

    public String a() {
        return em.a(this.m_title);
    }

    public int hashCode() {
        return (this.m_title == null ? 0 : this.m_title.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesTransitOperatorSupplier) obj;
        } else if (TransitOperatorSupplier.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitOperatorSupplier) obj);
        }
        if (this.m_title == null) {
            if (TextUtils.isEmpty(obj.m_title)) {
                return true;
            }
            return false;
        } else if (this.m_title.equals(obj.m_title)) {
            return true;
        } else {
            return false;
        }
    }
}
