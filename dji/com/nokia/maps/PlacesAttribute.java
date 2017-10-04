package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.ExtendedAttribute;
import com.here.android.mpa.search.Link;
import com.here.android.mpa.search.TransitDeparturesAttribute;
import com.here.android.mpa.search.TransitDestination;
import com.here.android.mpa.search.TransitLine;
import com.here.android.mpa.search.TransitLinesAttribute;
import com.here.android.mpa.search.TransitSchedulePage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class PlacesAttribute {
    private static k<ExtendedAttribute, PlacesAttribute> b;
    private static am<ExtendedAttribute, PlacesAttribute> c;
    private static am<TransitLinesAttribute, PlacesAttribute> d;
    private static am<TransitDeparturesAttribute, PlacesAttribute> e;
    private String a;
    @SerializedName("attribution")
    private String m_attribution;
    @SerializedName("destinations")
    private List<PlacesTransitDestination> m_destinations = new ArrayList();
    @SerializedName("label")
    private String m_label;
    @SerializedName("lines")
    private Map<String, PlacesTransitLine> m_lines = new LinkedTreeMap();
    @SerializedName("schedule")
    private PlacesTransitSchedulePage m_schedule;
    @SerializedName("text")
    private String m_text;
    @SerializedName("via")
    private PlacesLink m_via;

    public static void a(k<ExtendedAttribute, PlacesAttribute> kVar, am<ExtendedAttribute, PlacesAttribute> amVar, am<TransitLinesAttribute, PlacesAttribute> amVar2, am<TransitDeparturesAttribute, PlacesAttribute> amVar3) {
        b = kVar;
        c = amVar;
        d = amVar2;
        e = amVar3;
    }

    static PlacesAttribute a(ExtendedAttribute extendedAttribute) {
        return (PlacesAttribute) b.a(extendedAttribute);
    }

    static ExtendedAttribute a(PlacesAttribute placesAttribute) {
        if (placesAttribute != null) {
            return (ExtendedAttribute) c.a(placesAttribute);
        }
        return null;
    }

    static TransitLinesAttribute b(PlacesAttribute placesAttribute) {
        if (placesAttribute != null) {
            return (TransitLinesAttribute) d.a(placesAttribute);
        }
        return null;
    }

    static TransitDeparturesAttribute c(PlacesAttribute placesAttribute) {
        if (placesAttribute != null) {
            return (TransitDeparturesAttribute) e.a(placesAttribute);
        }
        return null;
    }

    static {
        ce.a(ExtendedAttribute.class);
    }

    public final String a() {
        return em.a(this.a);
    }

    void a(String str) {
        this.a = str;
    }

    public final String b() {
        return em.a(this.m_label);
    }

    public final String c() {
        return em.a(this.m_text);
    }

    public Link d() {
        return PlacesLink.b(this.m_via);
    }

    public String e() {
        return em.a(this.m_attribution);
    }

    public final Map<String, TransitLine> f() {
        Map<String, TransitLine> hashMap = new HashMap();
        if (this.m_lines != null) {
            for (Entry entry : this.m_lines.entrySet()) {
                hashMap.put(entry.getKey(), PlacesTransitLine.a((PlacesTransitLine) entry.getValue()));
            }
        }
        return hashMap;
    }

    public final List<TransitDestination> g() {
        List<TransitDestination> arrayList = new ArrayList();
        if (this.m_destinations != null) {
            for (PlacesTransitDestination a : this.m_destinations) {
                arrayList.add(PlacesTransitDestination.a(a));
            }
        }
        return arrayList;
    }

    public final TransitSchedulePage h() {
        return this.m_schedule != null ? PlacesTransitSchedulePage.a(this.m_schedule) : null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_text == null ? 0 : this.m_text.hashCode()) + (((this.m_schedule == null ? 0 : this.m_schedule.hashCode()) + (((this.m_lines == null ? 0 : this.m_lines.hashCode()) + (((this.m_label == null ? 0 : this.m_label.hashCode()) + (((this.a == null ? 0 : this.a.hashCode()) + (((this.m_destinations == null ? 0 : this.m_destinations.hashCode()) + (((this.m_attribution == null ? 0 : this.m_attribution.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.m_via != null) {
            i = this.m_via.hashCode();
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
            obj = (PlacesAttribute) obj;
        } else if (ExtendedAttribute.class != obj.getClass()) {
            return false;
        } else {
            obj = a((ExtendedAttribute) obj);
        }
        if (this.m_attribution == null) {
            if (!TextUtils.isEmpty(obj.m_attribution)) {
                return false;
            }
        } else if (!this.m_attribution.equals(obj.m_attribution)) {
            return false;
        }
        if (this.m_destinations == null) {
            if (obj.m_destinations != null) {
                return false;
            }
        } else if (!this.m_destinations.equals(obj.m_destinations)) {
            return false;
        }
        if (this.m_label == null) {
            if (!TextUtils.isEmpty(obj.m_label)) {
                return false;
            }
        } else if (!this.m_label.equals(obj.m_label)) {
            return false;
        }
        if (this.m_lines == null) {
            if (obj.m_lines != null) {
                return false;
            }
        } else if (!this.m_lines.equals(obj.m_lines)) {
            return false;
        }
        if (this.m_schedule == null) {
            if (obj.m_schedule != null) {
                return false;
            }
        } else if (!this.m_schedule.equals(obj.m_schedule)) {
            return false;
        }
        if (this.m_text == null) {
            if (!TextUtils.isEmpty(obj.m_text)) {
                return false;
            }
        } else if (!this.m_text.equals(obj.m_text)) {
            return false;
        }
        if (this.m_via == null) {
            if (obj.m_via != null) {
                return false;
            }
            return true;
        } else if (this.m_via.equals(obj.m_via)) {
            return true;
        } else {
            return false;
        }
    }
}
