package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.du;

public class AroundRequest extends DiscoveryRequest {
    @Online
    public AroundRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        return (AroundRequest) super.setSearchCenter(geoCoordinate);
    }

    @Online
    public AroundRequest setSearchArea(GeoCoordinate geoCoordinate, int i) {
        return (AroundRequest) super.setSearchArea(geoCoordinate, i);
    }

    @Online
    public AroundRequest setSearchArea(GeoBoundingBox geoBoundingBox) {
        return (AroundRequest) super.setSearchArea(geoBoundingBox);
    }

    @Online
    public AroundRequest setCategoryFilter(CategoryFilter categoryFilter) {
        return (AroundRequest) super.setCategoryFilter(categoryFilter);
    }

    @Online
    public ErrorCode execute(ResultListener<DiscoveryResultPage> resultListener) {
        a();
        this.f = PlacesApi.a().c(this.l);
        if (this.a != null) {
            if (this.c > 0) {
                this.f.a("in", this.a.getLatitude() + "," + this.a.getLongitude() + ";r=" + this.c);
            } else {
                this.f.a("at", this.a.getLatitude() + "," + this.a.getLongitude());
            }
        }
        if (this.b != null) {
            if (this.l == a.ONLINE) {
                this.f.a("in", du.a(this.b));
            } else {
                GeoCoordinate center = this.b.getCenter();
                this.f.a("in", center.getLatitude() + "," + center.getLongitude() + ";r=" + Double.valueOf(this.b.getCenter().distanceTo(this.b.getTopLeft())).intValue());
            }
        }
        if (this.d != null) {
            String categoryFilter = this.d.toString();
            if (!(categoryFilter == null || categoryFilter.isEmpty())) {
                this.f.a("cat", categoryFilter);
            }
        }
        return super.execute(resultListener);
    }
}
