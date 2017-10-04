package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Link;
import com.here.android.mpa.search.Media.Type;
import com.here.android.mpa.search.MediaCollectionPageRequest;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.RichTextFormatting;
import com.here.android.mpa.search.TransitSchedulePageRequest;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.dd.c;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

@Online
public class PlacesApi extends BaseNativeObject {
    private static final String a = PlacesApi.class.getSimpleName();
    private static LocationContext b;
    private String c;
    @Deprecated
    private a d;

    private native void createNative();

    private native void destroyNative();

    private native PlacesDiscoveryRequest newAroundRequestNative(int i, LocationContext locationContext);

    private native PlacesCategoryGraphRequest newCategoryGraphRequestNative(String str);

    private native PlacesDiscoveryRequest newDiscoveryRequestNative(String str);

    private native PlacesDiscoveryRequest newExploreRequestNative(int i, LocationContext locationContext);

    private native PlacesDiscoveryRequest newHereRequestNative(int i, LocationContext locationContext);

    private native dm newJsonRequestNative(String str);

    private native PlacesMediaPageRequest<?> newMediaPageRequestNative(String str);

    private native PlacesPlaceRequest newPlaceRequestNative(String str);

    private native PlacesDiscoveryRequest newSearchRequestNative(String str, int i, LocationContext locationContext);

    private native PlacesTextAutoSuggestionRequest newTextAutoSuggestionRequestNative(String str);

    private native PlacesTextSuggestionRequest newTextSuggestionRequestNative(String str, int i, LocationContext locationContext);

    private native PlacesTilesRequest newTilesRequestNative(String str);

    private native PlacesTransitSchedulePageRequest newTransitSchedulePageRequestNative(String str);

    private native int setServerUrlNative(String str);

    public static PlacesApi a() {
        try {
            return a.a;
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException(th.getCause().getLocalizedMessage());
        }
    }

    private PlacesApi() {
        super(true);
        this.d = a.b;
        createNative();
        d(MapsEngine.i());
        b = new LocationContext();
        c();
    }

    public PlacesDiscoveryRequest a(a aVar, String str) {
        PlacesDiscoveryRequest newSearchRequestNative = newSearchRequestNative(str, aVar.ordinal(), c());
        if (newSearchRequestNative != null) {
            newSearchRequestNative.a(c.i);
        }
        return newSearchRequestNative;
    }

    public PlacesDiscoveryRequest a(a aVar) {
        PlacesDiscoveryRequest newExploreRequestNative = newExploreRequestNative(aVar.ordinal(), c());
        if (newExploreRequestNative != null) {
            newExploreRequestNative.a(c.g);
        }
        return newExploreRequestNative;
    }

    public PlacesDiscoveryRequest b(a aVar) {
        PlacesDiscoveryRequest newHereRequestNative = newHereRequestNative(aVar.ordinal(), c());
        if (newHereRequestNative != null) {
            newHereRequestNative.a(c.h);
        }
        return newHereRequestNative;
    }

    public PlacesDiscoveryRequest c(a aVar) {
        PlacesDiscoveryRequest newAroundRequestNative = newAroundRequestNative(aVar.ordinal(), c());
        if (newAroundRequestNative != null) {
            newAroundRequestNative.a(c.f);
        }
        return newAroundRequestNative;
    }

    public PlacesTextSuggestionRequest b(a aVar, String str) {
        return newTextSuggestionRequestNative(str, aVar.ordinal(), c());
    }

    public PlacesTextAutoSuggestionRequest c(a aVar, String str) {
        String format = String.format("%s/autosuggest/?", new Object[]{this.c});
        PlacesBaseRequest newTextAutoSuggestionRequestNative = newTextAutoSuggestionRequestNative(format);
        newTextAutoSuggestionRequestNative.a(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str);
        if (!f(format)) {
            a(newTextAutoSuggestionRequestNative);
        }
        return newTextAutoSuggestionRequestNative;
    }

    public di a(GeoCoordinate geoCoordinate, String str) throws IllegalArgumentException {
        dy.a(str, "Search query text is null");
        dy.a(!str.isEmpty(), "Search query text is empty");
        return new di(str, geoCoordinate);
    }

    @Deprecated
    public do a(GeoCoordinate geoCoordinate) throws IllegalArgumentException {
        dy.a(geoCoordinate, "Location coordinate is null");
        dy.a(geoCoordinate.isValid(), "Location coordinate is invalid");
        return new do(geoCoordinate);
    }

    public dp a(GeoCoordinate geoCoordinate, Locale locale) throws IllegalArgumentException {
        dy.a(geoCoordinate, "Location coordinate is null");
        dy.a(geoCoordinate.isValid(), "Location coordinate is invalid");
        return new dp(geoCoordinate, locale);
    }

    public PlacesPlaceRequest a(PlaceLink placeLink) {
        dy.a(placeLink, "PlaceLink is null");
        PlacesLink a = PlacesLink.a((Link) placeLink);
        dy.a(a, "PlacesLink is null");
        dy.a(!a.a().isEmpty(), "PlacesLink is invalid");
        return a(a.a());
    }

    PlacesPlaceRequest a(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        PlacesBaseRequest newPlaceRequestNative = newPlaceRequestNative(e(g(str)));
        if (f(str)) {
            return newPlaceRequestNative;
        }
        a(newPlaceRequestNative);
        return newPlaceRequestNative;
    }

    public PlacesPlaceRequest a(String str, String str2) {
        boolean z;
        dy.a(str, "Source is null");
        dy.a(!str.isEmpty(), "Source is empty");
        dy.a(str2, "ID is null");
        if (str2.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        dy.a(z, "ID is empty");
        String format = String.format("%s/places/lookup?", new Object[]{this.c});
        bj.e(a, "createPlaceByForeignIdRequest href=%s", format);
        PlacesBaseRequest newPlaceRequestNative = newPlaceRequestNative(e(g(format)));
        newPlaceRequestNative.a("source", str);
        newPlaceRequestNative.a("id", str2);
        if (!f(format)) {
            a(newPlaceRequestNative);
        }
        return newPlaceRequestNative;
    }

    DiscoveryRequest a(String str, Map<String, String> map) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        PlacesBaseRequest newDiscoveryRequestNative = newDiscoveryRequestNative(g(str));
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                newDiscoveryRequestNative.b((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (!f(str)) {
            a(newDiscoveryRequestNative);
        }
        newDiscoveryRequestNative.a(RichTextFormatting.HTML);
        return PlacesDiscoveryRequest.a(newDiscoveryRequestNative);
    }

    MediaCollectionPageRequest<?> a(String str, Type type) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        PlacesBaseRequest newMediaPageRequestNative = newMediaPageRequestNative(g(str));
        newMediaPageRequestNative.a(type);
        if (!f(str)) {
            a(newMediaPageRequestNative);
        }
        return PlacesMediaPageRequest.a(newMediaPageRequestNative);
    }

    TransitSchedulePageRequest b(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        PlacesBaseRequest newTransitSchedulePageRequestNative = newTransitSchedulePageRequestNative(g(str));
        if (!f(str)) {
            a(newTransitSchedulePageRequestNative);
        }
        return PlacesTransitSchedulePageRequest.a(newTransitSchedulePageRequestNative);
    }

    public PlacesTilesRequest b() throws IllegalArgumentException {
        String format = String.format("%s/tiles?", new Object[]{this.c});
        bj.e(a, "createTilesRequest href=%s", format);
        PlacesBaseRequest newTilesRequestNative = newTilesRequestNative(e(g(format)));
        if (!f(format)) {
            a(newTilesRequestNative);
        }
        return newTilesRequestNative;
    }

    public PlacesCategoryGraphRequest c(String str) {
        dy.a(str, "Locale value is null");
        dy.a(!str.isEmpty(), "Locale value is empty");
        PlacesCategoryGraphRequest newCategoryGraphRequestNative = newCategoryGraphRequestNative(g(String.format("%s/categories/places?", new Object[]{this.c})));
        newCategoryGraphRequestNative.a("app_id", ConnectionInfoImpl.getApplicationId());
        newCategoryGraphRequestNative.a("app_code", ConnectionInfoImpl.getApplicationCode());
        newCategoryGraphRequestNative.b("Accept-Language", str);
        return newCategoryGraphRequestNative;
    }

    ErrorCode d(String str) {
        this.c = str;
        return ErrorCode.values()[setServerUrlNative(this.c)];
    }

    protected void finalize() {
        bj.e(a, "nativeptr=0x%X", Integer.valueOf(this.nativeptr));
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }

    private static LocationContext c() {
        try {
            PositioningManagerImpl a = PositioningManagerImpl.a();
            if (a.d()) {
                b.a(a.e());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    private String e(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!str.contains("?")) {
            stringBuilder.append("?");
        }
        return stringBuilder.toString();
    }

    private boolean f(String str) {
        return str.contains("app_id") && str.contains("app_code");
    }

    private void a(PlacesBaseRequest<?> placesBaseRequest) {
        if (MapServiceClient.b) {
            placesBaseRequest.b("Authorization", PlacesBaseRequest.a());
            return;
        }
        placesBaseRequest.a("app_id", ConnectionInfoImpl.getApplicationId());
        placesBaseRequest.a("app_code", ConnectionInfoImpl.getApplicationCode());
    }

    private String g(String str) {
        boolean z = false;
        try {
            z = MapsEngine.c().isOnline();
        } catch (Exception e) {
        }
        if (z) {
            return str;
        }
        return str.replace(this.c, "offline://");
    }
}
