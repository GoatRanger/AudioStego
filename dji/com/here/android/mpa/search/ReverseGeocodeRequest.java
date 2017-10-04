package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.do;

@Deprecated
public class ReverseGeocodeRequest extends Request<Address> {
    @Online
    public ReverseGeocodeRequest(GeoCoordinate geoCoordinate) {
        super(PlacesApi.a().a(geoCoordinate));
    }

    private ReverseGeocodeRequest(do doVar) {
        super(doVar);
    }

    @Online
    public ErrorCode execute(ResultListener<Address> resultListener) {
        a();
        return super.execute(resultListener);
    }

    static {
        do.a(new 1(), new 2());
    }
}
