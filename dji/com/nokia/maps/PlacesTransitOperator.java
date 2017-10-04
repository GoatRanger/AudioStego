package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.TransitOperator;
import com.here.android.mpa.search.TransitOperatorLink;
import com.here.android.mpa.search.TransitOperatorSupplier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PlacesTransitOperator {
    private static k<TransitOperator, PlacesTransitOperator> a;
    private static am<TransitOperator, PlacesTransitOperator> b;
    @SerializedName("id")
    private String m_id;
    @SerializedName("links")
    private Map<String, PlacesTransitOperatorLink> m_links = new LinkedTreeMap();
    @SerializedName("supplier")
    private PlacesTransitOperatorSupplier m_supplier;
    @SerializedName("title")
    private String m_title;

    public static void a(k<TransitOperator, PlacesTransitOperator> kVar, am<TransitOperator, PlacesTransitOperator> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitOperator a(TransitOperator transitOperator) {
        return (PlacesTransitOperator) a.a(transitOperator);
    }

    static TransitOperator a(PlacesTransitOperator placesTransitOperator) {
        if (placesTransitOperator != null) {
            return (TransitOperator) b.a(placesTransitOperator);
        }
        return null;
    }

    static {
        ce.a(TransitOperator.class);
    }

    public String a() {
        return em.a(this.m_id);
    }

    public String b() {
        return em.a(this.m_title);
    }

    public TransitOperatorSupplier c() {
        return PlacesTransitOperatorSupplier.a(this.m_supplier);
    }

    public Map<String, TransitOperatorLink> d() {
        Map<String, TransitOperatorLink> hashMap = new HashMap();
        if (this.m_links != null) {
            for (Entry entry : this.m_links.entrySet()) {
                hashMap.put(entry.getKey(), PlacesTransitOperatorLink.a((PlacesTransitOperatorLink) entry.getValue()));
            }
        }
        return hashMap;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_supplier == null ? 0 : this.m_supplier.hashCode()) + (((this.m_links == null ? 0 : this.m_links.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + 31) * 31)) * 31)) * 31;
        if (this.m_title != null) {
            i = this.m_title.hashCode();
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
            obj = (PlacesTransitOperator) obj;
        } else if (TransitOperator.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitOperator) obj);
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_links == null) {
            if (obj.m_links != null) {
                return false;
            }
        } else if (!this.m_links.equals(obj.m_links)) {
            return false;
        }
        if (this.m_supplier == null) {
            if (obj.m_supplier != null) {
                return false;
            }
        } else if (!this.m_supplier.equals(obj.m_supplier)) {
            return false;
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
