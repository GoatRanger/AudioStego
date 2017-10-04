package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.DiscoveryResult;
import com.here.android.mpa.search.DiscoveryResultPage;

public class PlacesDiscoveryResult {
    private static k<DiscoveryResult, PlacesDiscoveryResult> a;
    @SerializedName("results")
    private PlacesDiscoveryResultPage m_results;
    @SerializedName("search")
    private PlacesDiscoveryContext m_searchContext;

    static PlacesDiscoveryResult a(DiscoveryResult discoveryResult) {
        return (PlacesDiscoveryResult) a.a(discoveryResult);
    }

    static {
        ce.a(DiscoveryResult.class);
    }

    public final DiscoveryResultPage a() {
        return PlacesDiscoveryResultPage.a(this.m_results);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_results == null ? 0 : this.m_results.hashCode()) + 31) * 31;
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
            obj = (PlacesDiscoveryResult) obj;
        } else if (DiscoveryResult.class != obj.getClass()) {
            return false;
        } else {
            obj = a((DiscoveryResult) obj);
        }
        if (this.m_results == null) {
            if (obj.m_results != null) {
                return false;
            }
        } else if (!this.m_results.equals(obj.m_results)) {
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
