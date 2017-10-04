package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.Link;
import com.here.android.mpa.search.TransitOperatorLink;

public class PlacesTransitOperatorLink {
    private static k<TransitOperatorLink, PlacesTransitOperatorLink> a;
    private static am<TransitOperatorLink, PlacesTransitOperatorLink> b;
    @SerializedName("text")
    protected String m_text;
    @SerializedName("url")
    protected PlacesLink m_url;

    public static void a(k<TransitOperatorLink, PlacesTransitOperatorLink> kVar, am<TransitOperatorLink, PlacesTransitOperatorLink> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitOperatorLink a(TransitOperatorLink transitOperatorLink) {
        return (PlacesTransitOperatorLink) a.a(transitOperatorLink);
    }

    static TransitOperatorLink a(PlacesTransitOperatorLink placesTransitOperatorLink) {
        if (placesTransitOperatorLink != null) {
            return (TransitOperatorLink) b.a(placesTransitOperatorLink);
        }
        return null;
    }

    static {
        ce.a(TransitOperatorLink.class);
    }

    public String a() {
        return em.a(this.m_text);
    }

    public Link b() {
        return PlacesLink.b(this.m_url);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_text == null ? 0 : this.m_text.hashCode()) + 31) * 31;
        if (this.m_url != null) {
            i = this.m_url.hashCode();
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
            obj = (PlacesTransitOperatorLink) obj;
        } else if (TransitOperatorLink.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitOperatorLink) obj);
        }
        if (this.m_text == null) {
            if (!TextUtils.isEmpty(obj.m_text)) {
                return false;
            }
        } else if (!this.m_text.equals(obj.m_text)) {
            return false;
        }
        if (this.m_url == null) {
            if (obj.m_url != null) {
                return false;
            }
            return true;
        } else if (this.m_url.equals(obj.m_url)) {
            return true;
        } else {
            return false;
        }
    }
}
