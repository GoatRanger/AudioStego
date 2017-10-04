package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;

public class SearchRequest extends DiscoveryRequest {
    private String m = null;

    @Online
    public SearchRequest(String str) {
        dy.a((Object) str, "Query text is null");
        dy.a(Boolean.valueOf(!str.isEmpty()), "Query text is empty");
        this.m = str;
    }

    @Online
    public SearchRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        return (SearchRequest) super.setSearchCenter(geoCoordinate);
    }

    @Online
    public SearchRequest setQueryText(String str) {
        dy.a(this.m, "Query text is null");
        dy.a(Boolean.valueOf(!this.m.isEmpty()), "Query text is empty");
        this.m = str;
        return this;
    }

    @Online
    public ErrorCode execute(ResultListener<DiscoveryResultPage> resultListener) {
        a();
        this.f = PlacesApi.a().a(this.l, this.m);
        if (this.a != null) {
            this.f.a("at", this.a.getLatitude() + "," + this.a.getLongitude());
        }
        return super.execute(resultListener);
    }
}
