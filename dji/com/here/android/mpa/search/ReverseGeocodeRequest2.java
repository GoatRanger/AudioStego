package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dp;
import com.nokia.maps.k;
import java.util.Locale;

public class ReverseGeocodeRequest2 extends Request<Location> {
    @Online
    public ReverseGeocodeRequest2(GeoCoordinate geoCoordinate) {
        this(geoCoordinate, null);
    }

    @Online
    public ReverseGeocodeRequest2(GeoCoordinate geoCoordinate, Locale locale) {
        super(PlacesApi.a().a(geoCoordinate, locale));
    }

    private ReverseGeocodeRequest2(dp dpVar) {
        super(dpVar);
    }

    @Online
    public ErrorCode execute(ResultListener<Location> resultListener) {
        a();
        return super.execute(resultListener);
    }

    static {
        dp.a(new k<ReverseGeocodeRequest2, dp>() {
            public dp a(ReverseGeocodeRequest2 reverseGeocodeRequest2) {
                return (dp) reverseGeocodeRequest2.f;
            }
        }, new am<ReverseGeocodeRequest2, dp>() {
            public ReverseGeocodeRequest2 a(dp dpVar) {
                return dpVar != null ? new ReverseGeocodeRequest2(dpVar) : null;
            }
        });
    }
}
