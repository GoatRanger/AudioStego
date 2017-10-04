package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.TransitDeparture;
import com.here.android.mpa.search.TransitLine;
import com.here.android.mpa.search.TransitOperator;
import com.here.android.mpa.search.TransitSchedulePage;
import com.here.android.mpa.search.TransitSchedulePageRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PlacesTransitSchedulePage {
    private static k<TransitSchedulePage, PlacesTransitSchedulePage> a;
    private static am<TransitSchedulePage, PlacesTransitSchedulePage> b;
    @SerializedName("items")
    protected List<PlacesTransitDeparture> m_items = new ArrayList();
    @SerializedName("lines")
    private Map<String, PlacesTransitLine> m_lines = new LinkedTreeMap();
    @SerializedName("next")
    protected String m_next;
    @SerializedName("offset")
    protected int m_offset = 0;
    @SerializedName("operators")
    private Map<String, PlacesTransitOperator> m_operators = new LinkedTreeMap();
    @SerializedName("previous")
    protected String m_previous;

    public static void a(k<TransitSchedulePage, PlacesTransitSchedulePage> kVar, am<TransitSchedulePage, PlacesTransitSchedulePage> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitSchedulePage a(TransitSchedulePage transitSchedulePage) {
        return (PlacesTransitSchedulePage) a.a(transitSchedulePage);
    }

    static TransitSchedulePage a(PlacesTransitSchedulePage placesTransitSchedulePage) {
        if (placesTransitSchedulePage != null) {
            return (TransitSchedulePage) b.a(placesTransitSchedulePage);
        }
        return null;
    }

    static {
        ce.a(TransitSchedulePage.class);
    }

    protected PlacesTransitSchedulePage() {
    }

    public final int a() {
        return this.m_offset;
    }

    public final TransitSchedulePageRequest b() {
        return PlacesApi.a().b(this.m_next);
    }

    public final TransitSchedulePageRequest c() {
        return PlacesApi.a().b(this.m_previous);
    }

    public final List<TransitDeparture> d() {
        List<TransitDeparture> arrayList = new ArrayList();
        synchronized (this.m_items) {
            for (PlacesTransitDeparture a : this.m_items) {
                arrayList.add(PlacesTransitDeparture.a(a));
            }
        }
        return arrayList;
    }

    public Map<String, TransitLine> e() {
        Map<String, TransitLine> hashMap = new HashMap();
        if (this.m_lines != null) {
            for (Entry entry : this.m_lines.entrySet()) {
                hashMap.put(entry.getKey(), PlacesTransitLine.a((PlacesTransitLine) entry.getValue()));
            }
        }
        return hashMap;
    }

    public Map<String, TransitOperator> f() {
        Map<String, TransitOperator> hashMap = new HashMap();
        if (this.m_operators != null) {
            for (Entry entry : this.m_operators.entrySet()) {
                hashMap.put(entry.getKey(), PlacesTransitOperator.a((PlacesTransitOperator) entry.getValue()));
            }
        }
        return hashMap;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.m_next == null ? 0 : this.m_next.hashCode()) + (((this.m_operators == null ? 0 : this.m_operators.hashCode()) + (((this.m_lines == null ? 0 : this.m_lines.hashCode()) + (((this.m_items == null ? 0 : this.m_items.hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + this.m_offset) * 31;
        if (this.m_previous != null) {
            i = this.m_previous.hashCode();
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
            obj = (PlacesTransitSchedulePage) obj;
        } else if (TransitSchedulePage.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitSchedulePage) obj);
        }
        if (this.m_items == null) {
            if (obj.m_items != null) {
                return false;
            }
        } else if (!this.m_items.equals(obj.m_items)) {
            return false;
        }
        if (this.m_lines == null) {
            if (obj.m_lines != null) {
                return false;
            }
        } else if (!this.m_lines.equals(obj.m_lines)) {
            return false;
        }
        if (this.m_operators == null) {
            if (obj.m_operators != null) {
                return false;
            }
        } else if (!this.m_operators.equals(obj.m_operators)) {
            return false;
        }
        if (this.m_next == null) {
            if (obj.m_next != null) {
                return false;
            }
        } else if (!this.m_next.equals(obj.m_next)) {
            return false;
        }
        if (this.m_offset != obj.m_offset) {
            return false;
        }
        if (this.m_previous == null) {
            if (obj.m_previous != null) {
                return false;
            }
            return true;
        } else if (this.m_previous.equals(obj.m_previous)) {
            return true;
        } else {
            return false;
        }
    }
}
