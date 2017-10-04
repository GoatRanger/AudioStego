package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.dy;
import java.util.List;

public class TextAutoSuggestionRequest extends Request<List<AutoSuggest>> {
    private GeoCoordinate a = null;
    private String b = null;

    @Online
    public TextAutoSuggestionRequest(String str) {
        dy.a((Object) str, "Partial term query is null");
        dy.a(!str.isEmpty(), "Partial term query is empty");
        this.b = str;
    }

    @Online
    public TextAutoSuggestionRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Search center coordinate is null");
        dy.a(geoCoordinate.isValid(), "Search center coordinate is invalid");
        this.a = geoCoordinate;
        return this;
    }

    @Online
    public TextAutoSuggestionRequest setQueryText(String str) {
        dy.a(this.b, "Partial term query is null");
        dy.a(!this.b.isEmpty(), "Partial term query is empty");
        this.b = str;
        return this;
    }

    @Online
    public RichTextFormatting getRichTextFormatting() {
        return this.g;
    }

    @Online
    public TextAutoSuggestionRequest setRichTextFormatting(RichTextFormatting richTextFormatting) {
        this.g = richTextFormatting;
        return this;
    }

    @Online
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Online
    public TextAutoSuggestionRequest setCollectionSize(int i) {
        return (TextAutoSuggestionRequest) super.setCollectionSize(i);
    }

    @Online
    public TextAutoSuggestionRequest setMapViewport(GeoBoundingBox geoBoundingBox) {
        return (TextAutoSuggestionRequest) super.setMapViewport(geoBoundingBox);
    }

    @Online
    public ErrorCode execute(ResultListener<List<AutoSuggest>> resultListener) {
        a();
        if (this.l == a.OFFLINE) {
            return ErrorCode.SERVICE_UNAVAILABLE;
        }
        this.f = PlacesApi.a().c(this.l, this.b);
        if (this.a != null) {
            this.f.a("at", this.a.getLatitude() + "," + this.a.getLongitude());
        }
        return super.execute(resultListener);
    }
}
