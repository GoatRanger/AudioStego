package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.al;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Set;

@HybridPlus
public final class RequestManager {
    private al a = new al();

    @Deprecated
    public DepartureBoardRequest createDepartureBoardRequest(GeoCoordinate geoCoordinate, String str, ResponseListener<DepartureBoard> responseListener) {
        return this.a.a(geoCoordinate, str, responseListener);
    }

    public DepartureBoardRequest createDepartureBoardRequest(GeoCoordinate geoCoordinate, String str, ResponseListener<DepartureBoard> responseListener) {
        return this.a.a(geoCoordinate, str, responseListener);
    }

    public MultiBoardRequest createMultiBoardRequest(GeoCoordinate geoCoordinate, ResponseListener<MultiBoardResult> responseListener) {
        return this.a.a(geoCoordinate, responseListener);
    }

    @Deprecated
    public StationSearchRequest createStationSearchRequest(GeoCoordinate geoCoordinate, String str, ResponseListener<StationSearchResult> responseListener) {
        return this.a.b(geoCoordinate, str, responseListener);
    }

    public StationSearchRequest createStationSearchRequest(GeoCoordinate geoCoordinate, String str, ResponseListener<StationSearchResult> responseListener) {
        return this.a.b(geoCoordinate, str, responseListener);
    }

    @Deprecated
    public StationSearchRequest createStationSearchRequest(String[] strArr, ResponseListener<StationSearchResult> responseListener) {
        return this.a.a(strArr, responseListener);
    }

    public StationSearchRequest createStationSearchRequest(Set<String> set, ResponseListener<StationSearchResult> responseListener) {
        return this.a.a(set, responseListener);
    }

    public CitySearchRequest createCitySearchRequest(String str, ResponseListener<CitySearchResult> responseListener) {
        return this.a.a(str, responseListener);
    }

    public CityCoverageRequest createCityCoverageRequest(ResponseListener<CityCoverageResult> responseListener) {
        return this.a.a(responseListener);
    }

    public NearbyCoverageRequest createNearbyCoverageRequest(GeoCoordinate geoCoordinate, ResponseListener<NearbyCoverageResult> responseListener) {
        return this.a.b(geoCoordinate, responseListener);
    }
}
