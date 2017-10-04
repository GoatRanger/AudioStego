package com.here.android.mpa.search;

import com.here.android.mpa.search.DiscoveryResult.ResultType;
import com.nokia.maps.PlacesDiscoveryResultPage;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.List;

@Online
public class DiscoveryResultPage {
    private PlacesDiscoveryResultPage a;

    private DiscoveryResultPage(PlacesDiscoveryResultPage placesDiscoveryResultPage) {
        this.a = placesDiscoveryResultPage;
    }

    public int getOffsetCount() {
        return this.a.a();
    }

    public DiscoveryRequest getNextPageRequest() {
        return this.a.b();
    }

    public DiscoveryRequest getPreviousPageRequest() {
        return this.a.c();
    }

    public List<DiscoveryResult> getItems() {
        return this.a.d();
    }

    public List<PlaceLink> getPlaceLinks() {
        List<PlaceLink> arrayList = new ArrayList();
        for (DiscoveryResult discoveryResult : this.a.d()) {
            if (discoveryResult.getResultType() == ResultType.PLACE) {
                arrayList.add((PlaceLink) discoveryResult);
            }
        }
        return arrayList;
    }

    public List<DiscoveryLink> getDiscoveryLinks() {
        List<DiscoveryLink> arrayList = new ArrayList();
        for (DiscoveryResult discoveryResult : this.a.d()) {
            if (discoveryResult.getResultType() == ResultType.DISCOVERY) {
                arrayList.add((DiscoveryLink) discoveryResult);
            }
        }
        return arrayList;
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        PlacesDiscoveryResultPage.a(new k<DiscoveryResultPage, PlacesDiscoveryResultPage>() {
            public PlacesDiscoveryResultPage a(DiscoveryResultPage discoveryResultPage) {
                return discoveryResultPage.a;
            }
        }, new am<DiscoveryResultPage, PlacesDiscoveryResultPage>() {
            public DiscoveryResultPage a(PlacesDiscoveryResultPage placesDiscoveryResultPage) {
                return placesDiscoveryResultPage != null ? new DiscoveryResultPage(placesDiscoveryResultPage) : null;
            }
        });
    }
}
