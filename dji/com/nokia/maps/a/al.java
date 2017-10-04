package com.nokia.maps.a;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.UMRouter.SubsequentRouteType;
import com.here.android.mpa.urbanmobility.CityCoverageRequest;
import com.here.android.mpa.urbanmobility.CityCoverageResult;
import com.here.android.mpa.urbanmobility.CitySearchRequest;
import com.here.android.mpa.urbanmobility.CitySearchResult;
import com.here.android.mpa.urbanmobility.DepartureBoard;
import com.here.android.mpa.urbanmobility.DepartureBoardRequest;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.MultiBoardRequest;
import com.here.android.mpa.urbanmobility.MultiBoardResult;
import com.here.android.mpa.urbanmobility.NearbyCoverageRequest;
import com.here.android.mpa.urbanmobility.NearbyCoverageResult;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.ResponseListener;
import com.here.android.mpa.urbanmobility.StationSearchRequest;
import com.here.android.mpa.urbanmobility.StationSearchResult;
import com.nokia.maps.ApplicationContext;
import java.util.Set;

public class al {
    final String a;
    final String b;
    final String c;

    public enum a {
        LOCALHOST,
        DEV,
        DATA_INTEGRATION,
        DATA_TESTING,
        FUNCTIONAL_TESTING,
        LOAD_TESTING,
        DEMO,
        STAGING,
        CUSTOMER_INTEGRATION,
        PROD
    }

    private static class b<T> implements RequestManager$ResponseListener<T> {
        private ResponseListener a;

        public b(ResponseListener responseListener) {
            this.a = responseListener;
        }

        public void onSuccess(T t) {
            this.a.onSuccess(t);
        }

        public void onError(ErrorCode errorCode, String str) {
            com.here.android.mpa.search.ErrorCode errorCode2;
            switch (errorCode) {
                case INVALID_CREDENTIALS:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.INVALID_CREDENTIALS;
                    break;
                case INVALID_PARAMETERS:
                case INVALID_PERIOD:
                case START_DESTINATION_TOO_CLOSE:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.INVALID_PARAMETER;
                    break;
                case CANCELLED:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.CANCELLED;
                    break;
                case OPERATION_NOT_ALLOWED:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.OPERATION_NOT_ALLOWED;
                    break;
                case NETWORK_COMMUNICATION:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.NETWORK_COMMUNICATION;
                    break;
                case ROUTING_NOT_POSSIBLE:
                case NO_COVERAGE:
                case UNAVAILABLE_API:
                case NO_STATION_NEARBY:
                case NOT_FOUND:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.NOT_FOUND;
                    break;
                case INVALID_RESPONSE:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.NO_CONTENT;
                    break;
                case SERVICE_UNAVAILABLE:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.SERVICE_UNAVAILABLE;
                    break;
                case OUT_OF_MEMORY:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.OUT_OF_MEMORY;
                    break;
                case UNKNOWN:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.UNKNOWN;
                    break;
                default:
                    errorCode2 = com.here.android.mpa.search.ErrorCode.GENERAL;
                    break;
            }
            this.a.onError(errorCode2);
        }
    }

    public al(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Host can't be empty or null.");
        }
        this.a = str;
        this.b = ApplicationContext.b().getAppId();
        this.c = ApplicationContext.b().getAppToken();
    }

    public al(a aVar) {
        this(a(aVar));
    }

    public al() {
        this(a.PROD);
    }

    public DepartureBoardRequest a(GeoCoordinate geoCoordinate, String str, ResponseListener<DepartureBoard> responseListener) {
        return a(geoCoordinate, str, new b(responseListener));
    }

    public DepartureBoardRequest a(GeoCoordinate geoCoordinate, String str, RequestManager$ResponseListener<DepartureBoard> requestManager$ResponseListener) {
        if (geoCoordinate != null && str != null && !str.isEmpty()) {
            return p.a(new p(this.a, this.b, this.c, geoCoordinate, str, requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("Station coordinate and stationId can't be null or empty.");
    }

    public MultiBoardRequest a(GeoCoordinate geoCoordinate, RequestManager$ResponseListener<MultiBoardResult> requestManager$ResponseListener) {
        if (geoCoordinate != null) {
            return ae.a(new ae(this.a, this.b, this.c, geoCoordinate, requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("Station coordinate can't be null.");
    }

    public StationSearchRequest b(GeoCoordinate geoCoordinate, String str, ResponseListener<StationSearchResult> responseListener) {
        return b(geoCoordinate, str, new b(responseListener));
    }

    public StationSearchRequest b(GeoCoordinate geoCoordinate, String str, RequestManager$ResponseListener<StationSearchResult> requestManager$ResponseListener) {
        if (geoCoordinate != null) {
            return au.a(new au(this.a, this.b, this.c, geoCoordinate, str, requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("StationCoordinate can't be null or empty.");
    }

    public StationSearchRequest a(String[] strArr, ResponseListener<StationSearchResult> responseListener) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("Station IDs can't be null or empty.");
        }
        return au.a(new au(this.a, this.b, this.c, strArr, new b(responseListener)));
    }

    public StationSearchRequest a(Set<String> set, RequestManager$ResponseListener<StationSearchResult> requestManager$ResponseListener) {
        if (set != null && !set.isEmpty()) {
            return au.a(new au(this.a, this.b, this.c, (Set) set, (RequestManager$ResponseListener) requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("Station IDs can't be null or empty.");
    }

    public CitySearchRequest a(String str, RequestManager$ResponseListener<CitySearchResult> requestManager$ResponseListener) {
        if (str != null && !str.isEmpty()) {
            return l.a(new l(this.a, this.b, this.c, str, requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("Search text string can't be null or empty.");
    }

    public CityCoverageRequest a(RequestManager$ResponseListener<CityCoverageResult> requestManager$ResponseListener) {
        return i.a(new i(this.a, this.b, this.c, requestManager$ResponseListener));
    }

    public NearbyCoverageRequest b(GeoCoordinate geoCoordinate, RequestManager$ResponseListener<NearbyCoverageResult> requestManager$ResponseListener) {
        if (geoCoordinate != null) {
            return ag.a(new ag(this.a, this.b, this.c, geoCoordinate, requestManager$ResponseListener));
        }
        throw new IllegalArgumentException("Location can't be null.");
    }

    ap a(RoutePlan routePlan, RequestManager$ResponseListener<h> requestManager$ResponseListener) {
        return new ap(this.a, this.b, this.c, routePlan, requestManager$ResponseListener);
    }

    ax a(RoutePlan routePlan, h hVar, SubsequentRouteType subsequentRouteType, int i, RequestManager$ResponseListener<h> requestManager$ResponseListener) {
        return new ax(this.a, this.b, this.c, routePlan, hVar, subsequentRouteType, i, requestManager$ResponseListener);
    }

    public static String a(a aVar) {
        if (a.LOCALHOST == aVar) {
            return com.here.a.a.a.i.a.LOCALHOST.k;
        }
        if (a.DEV == aVar) {
            return com.here.a.a.a.i.a.DEV.k;
        }
        if (a.DATA_INTEGRATION == aVar) {
            return com.here.a.a.a.i.a.DATA_INTEGRATION.k;
        }
        if (a.DATA_TESTING == aVar) {
            return com.here.a.a.a.i.a.DATA_TESTING.k;
        }
        if (a.FUNCTIONAL_TESTING == aVar) {
            return com.here.a.a.a.i.a.FUNCTIONAL_TESTING.k;
        }
        if (a.LOAD_TESTING == aVar) {
            return com.here.a.a.a.i.a.LOAD_TESTING.k;
        }
        if (a.DEMO == aVar) {
            return com.here.a.a.a.i.a.DEMO.k;
        }
        if (a.STAGING == aVar) {
            return com.here.a.a.a.i.a.STAGING.k;
        }
        if (a.CUSTOMER_INTEGRATION == aVar) {
            return com.here.a.a.a.i.a.CUSTOMER_INTEGRATION.k;
        }
        return com.here.a.a.a.i.a.PROD.k;
    }
}
