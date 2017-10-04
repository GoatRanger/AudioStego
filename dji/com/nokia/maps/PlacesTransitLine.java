package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.TransitLine;
import com.here.android.mpa.search.TransitLineCategory;
import com.here.android.mpa.search.TransitLineStyle;

public class PlacesTransitLine {
    private static k<TransitLine, PlacesTransitLine> a;
    private static am<TransitLine, PlacesTransitLine> b;
    @SerializedName("category")
    private PlacesTransitLineCategory m_category;
    @SerializedName("destination")
    private String m_destination;
    @SerializedName("name")
    private String m_name;
    @SerializedName("operator")
    private String m_operator;
    @SerializedName("style")
    private PlacesTransitLineStyle m_style;

    public static void a(k<TransitLine, PlacesTransitLine> kVar, am<TransitLine, PlacesTransitLine> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitLine a(TransitLine transitLine) {
        return (PlacesTransitLine) a.a(transitLine);
    }

    static TransitLine a(PlacesTransitLine placesTransitLine) {
        if (placesTransitLine != null) {
            return (TransitLine) b.a(placesTransitLine);
        }
        return null;
    }

    static {
        ce.a(TransitLine.class);
    }

    public String a() {
        return em.a(this.m_name);
    }

    public String b() {
        return em.a(this.m_destination);
    }

    public TransitLineCategory c() {
        return PlacesTransitLineCategory.a(this.m_category);
    }

    public TransitLineStyle d() {
        return PlacesTransitLineStyle.a(this.m_style);
    }

    public String e() {
        return em.a(this.m_operator);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_operator == null ? 0 : this.m_operator.hashCode()) + (((this.m_name == null ? 0 : this.m_name.hashCode()) + (((this.m_destination == null ? 0 : this.m_destination.hashCode()) + (((this.m_category == null ? 0 : this.m_category.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_style != null) {
            i = this.m_style.hashCode();
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
            obj = (PlacesTransitLine) obj;
        } else if (TransitLine.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitLine) obj);
        }
        if (this.m_category == null) {
            if (obj.m_category != null) {
                return false;
            }
        } else if (!this.m_category.equals(obj.m_category)) {
            return false;
        }
        if (this.m_destination == null) {
            if (!TextUtils.isEmpty(obj.m_destination)) {
                return false;
            }
        } else if (!this.m_destination.equals(obj.m_destination)) {
            return false;
        }
        if (this.m_name == null) {
            if (!TextUtils.isEmpty(obj.m_name)) {
                return false;
            }
        } else if (!this.m_name.equals(obj.m_name)) {
            return false;
        }
        if (this.m_operator == null) {
            if (!TextUtils.isEmpty(obj.m_operator)) {
                return false;
            }
        } else if (!this.m_operator.equals(obj.m_operator)) {
            return false;
        }
        if (this.m_style == null) {
            if (obj.m_style != null) {
                return false;
            }
            return true;
        } else if (this.m_style.equals(obj.m_style)) {
            return true;
        } else {
            return false;
        }
    }
}
