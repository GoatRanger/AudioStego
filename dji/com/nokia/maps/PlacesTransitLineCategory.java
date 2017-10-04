package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.TransitLineCategory;

public class PlacesTransitLineCategory {
    private static k<TransitLineCategory, PlacesTransitLineCategory> a;
    private static am<TransitLineCategory, PlacesTransitLineCategory> b;
    @SerializedName("icon")
    private String m_icon;
    @SerializedName("id")
    private String m_id;
    @SerializedName("localName")
    private String m_localName;
    @SerializedName("title")
    private String m_title;

    public static void a(k<TransitLineCategory, PlacesTransitLineCategory> kVar, am<TransitLineCategory, PlacesTransitLineCategory> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitLineCategory a(TransitLineCategory transitLineCategory) {
        return (PlacesTransitLineCategory) a.a(transitLineCategory);
    }

    static TransitLineCategory a(PlacesTransitLineCategory placesTransitLineCategory) {
        if (placesTransitLineCategory != null) {
            return (TransitLineCategory) b.a(placesTransitLineCategory);
        }
        return null;
    }

    static {
        ce.a(TransitLineCategory.class);
    }

    public String a() {
        return em.a(this.m_id);
    }

    public String b() {
        return em.a(this.m_title);
    }

    public String c() {
        return em.a(this.m_localName);
    }

    public String d() {
        return em.a(this.m_icon);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_localName == null ? 0 : this.m_localName.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + (((this.m_icon == null ? 0 : this.m_icon.hashCode()) + 31) * 31)) * 31)) * 31;
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
            obj = (PlacesTransitLineCategory) obj;
        } else if (TransitLineCategory.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitLineCategory) obj);
        }
        if (this.m_icon == null) {
            if (!TextUtils.isEmpty(obj.m_icon)) {
                return false;
            }
        } else if (!this.m_icon.equals(obj.m_icon)) {
            return false;
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_localName == null) {
            if (!TextUtils.isEmpty(obj.m_localName)) {
                return false;
            }
        } else if (!this.m_localName.equals(obj.m_localName)) {
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
