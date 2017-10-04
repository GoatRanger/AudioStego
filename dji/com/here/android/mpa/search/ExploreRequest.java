package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.du;

public class ExploreRequest extends DiscoveryRequest {
    @Online
    public ExploreRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        return (ExploreRequest) super.setSearchCenter(geoCoordinate);
    }

    @Online
    public ExploreRequest setSearchArea(GeoCoordinate geoCoordinate, int i) {
        return (ExploreRequest) super.setSearchArea(geoCoordinate, i);
    }

    @Online
    public ExploreRequest setSearchArea(GeoBoundingBox geoBoundingBox) {
        return (ExploreRequest) super.setSearchArea(geoBoundingBox);
    }

    @Online
    public ExploreRequest setCategoryFilter(CategoryFilter categoryFilter) {
        return (ExploreRequest) super.setCategoryFilter(categoryFilter);
    }

    @Online
    public ErrorCode execute(ResultListener<DiscoveryResultPage> resultListener) {
        a();
        this.f = PlacesApi.a().a(this.l);
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
