package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.DiscoveryResult;
import com.here.android.mpa.search.DiscoveryResultPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesDiscoveryResultPage {
    private static k<DiscoveryResultPage, PlacesDiscoveryResultPage> b;
    private static am<DiscoveryResultPage, PlacesDiscoveryResultPage> c;
    private Map<String, String> a = new HashMap();
    @SerializedName("items")
    private List<PlacesLink> m_items = new ArrayList();
    @SerializedName("next")
    private String m_next;
    @SerializedName("offset")
    private Integer m_offset;
    @SerializedName("previous")
    private String m_previous;
    @SerializedName("search")
    private PlacesDiscoveryContext m_searchContext;

    public static void a(k<DiscoveryResultPage, PlacesDiscoveryResultPage> kVar, am<DiscoveryResultPage, PlacesDiscoveryResultPage> amVar) {
        b = kVar;
        c = amVar;
    }

    static PlacesDiscoveryResultPage a(DiscoveryResultPage discoveryResultPage) {
        return (PlacesDiscoveryResultPage) b.a(discoveryResultPage);
    }

    static DiscoveryResultPage a(PlacesDiscoveryResultPage placesDiscoveryResultPage) {
        if (placesDiscoveryResultPage != null) {
            return (DiscoveryResultPage) c.a(placesDiscoveryResultPage);
        }
        return null;
    }

    static {
        ce.a(DiscoveryResultPage.class);
    }

    public final int a() {
        return this.m_offset != null ? this.m_offset.intValue() : 0;
    }

    public final DiscoveryRequest b() {
        return PlacesApi.a().a(this.m_next, this.a);
    }

    public final DiscoveryRequest c() {
        return PlacesApi.a().a(this.m_previous, this.a);
    }

    public final List<DiscoveryResult> d() {
        List<DiscoveryResult> arrayList = new ArrayList();
        if (this.m_items != null) {
            for (PlacesLink placesLink : this.m_items) {
                if (placesLink.b().contentEquals("urn:nlp-types:place")) {
                    arrayList.add(PlacesLink.b(placesLink));
                } else {
                    arrayList.add(PlacesLink.a(placesLink));
                }
            }
        }
        return arrayList;
    }

    void a(Map<String, String> map) {
        this.a = map;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_previous == null ? 0 : this.m_previous.hashCode()) + (((this.m_offset == null ? 0 : this.m_offset.hashCode()) + (((this.m_next == null ? 0 : this.m_next.hashCode()) + (((this.m_items == null ? 0 : this.m_items.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_searchContext != null) {
            i = this.m_searchContext.hashCode();
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
            obj = (PlacesDiscoveryResultPage) obj;
        } else if (DiscoveryResultPage.class != obj.getClass()) {
            return false;
        } else {
            obj = a((DiscoveryResultPage) obj);
        }
        if (this.m_items == null) {
            if (obj.m_items != null) {
                return false;
            }
        } else if (!this.m_items.equals(obj.m_items)) {
            return false;
        }
        if (this.m_next == null) {
            if (!TextUtils.isEmpty(obj.m_next)) {
                return false;
            }
        } else if (!this.m_next.equals(obj.m_next)) {
            return false;
        }
        if (this.m_offset == null) {
            if (obj.m_offset != null) {
                return false;
            }
        } else if (!this.m_offset.equals(obj.m_offset)) {
            return false;
        }
        if (this.m_previous == null) {
            if (!TextUtils.isEmpty(obj.m_previous)) {
                return false;
            }
        } else if (!this.m_previous.equals(obj.m_previous)) {
            return false;
        }
        if (this.m_searchContext == null) {
            if (obj.m_searchContext != null) {
                return false;
            }
            return true;
        } else if (this.m_searchContext.equals(obj.m_searchContext)) {
            return true;
        } else {
            return false;
        }
    }
}
