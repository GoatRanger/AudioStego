package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.TransitLineStyle;

public class PlacesTransitLineStyle {
    private static k<TransitLineStyle, PlacesTransitLineStyle> a;
    private static am<TransitLineStyle, PlacesTransitLineStyle> b;
    @SerializedName("color")
    private String m_color;
    @SerializedName("iconShape")
    private String m_iconShape;
    @SerializedName("outlineColor")
    private String m_outlineColor;
    @SerializedName("textColor")
    private String m_textColor;

    public static void a(k<TransitLineStyle, PlacesTransitLineStyle> kVar, am<TransitLineStyle, PlacesTransitLineStyle> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitLineStyle a(TransitLineStyle transitLineStyle) {
        return (PlacesTransitLineStyle) a.a(transitLineStyle);
    }

    static TransitLineStyle a(PlacesTransitLineStyle placesTransitLineStyle) {
        if (placesTransitLineStyle != null) {
            return (TransitLineStyle) b.a(placesTransitLineStyle);
        }
        return null;
    }

    static {
        ce.a(TransitLineStyle.class);
    }

    public String a() {
        return em.a(this.m_color);
    }

    public String b() {
        return em.a(this.m_textColor);
    }

    public String c() {
        return em.a(this.m_outlineColor);
    }

    public String d() {
        return em.a(this.m_iconShape);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_outlineColor == null ? 0 : this.m_outlineColor.hashCode()) + (((this.m_iconShape == null ? 0 : this.m_iconShape.hashCode()) + (((this.m_color == null ? 0 : this.m_color.hashCode()) + 31) * 31)) * 31)) * 31;
        if (this.m_textColor != null) {
            i = this.m_textColor.hashCode();
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
            obj = (PlacesTransitLineStyle) obj;
        } else if (TransitLineStyle.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitLineStyle) obj);
        }
        if (this.m_color == null) {
            if (!TextUtils.isEmpty(obj.m_color)) {
                return false;
            }
        } else if (!this.m_color.equals(obj.m_color)) {
            return false;
        }
        if (this.m_iconShape == null) {
            if (!TextUtils.isEmpty(obj.m_iconShape)) {
                return false;
            }
        } else if (!this.m_iconShape.equals(obj.m_iconShape)) {
            return false;
        }
        if (this.m_outlineColor == null) {
            if (!TextUtils.isEmpty(obj.m_outlineColor)) {
                return false;
            }
        } else if (!this.m_outlineColor.equals(obj.m_outlineColor)) {
            return false;
        }
        if (this.m_textColor == null) {
            if (TextUtils.isEmpty(obj.m_textColor)) {
                return true;
            }
            return false;
        } else if (this.m_textColor.equals(obj.m_textColor)) {
            return true;
        } else {
            return false;
        }
    }
}
