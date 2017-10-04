package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.List;

public class TextSuggestionRequest extends Request<List<String>> {
    private GeoCoordinate a = null;
    private String b = null;

    @Online
    public TextSuggestionRequest(String str) {
        dy.a((Object) str, "Partial term query is null");
        dy.a(!str.isEmpty(), "Partial term query is empty");
        this.b = str;
    }

    @Online
    public TextSuggestionRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Search center coordinate is null");
        dy.a(geoCoordinate.isValid(), "Search center coordinate is invalid");
        this.a = geoCoordinate;
        return this;
    }

    @Online
    public TextSuggestionRequest setQueryText(String str) {
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
    public TextSuggestionRequest setRichTextFormatting(RichTextFormatting richTextFormatting) {
        this.g = richTextFormatting;
        return this;
    }

    @Online
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Online
    public TextSuggestionRequest setCollectionSize(int i) {
        return (TextSuggestionRequest) super.setCollectionSize(i);
    }

    @Online
    public TextSuggestionRequest setMapViewport(GeoBoundingBox geoBoundingBox) {
        return (TextSuggestionRequest) super.setMapViewport(geoBoundingBox);
    }

    @Online
    public ErrorCode execute(ResultListener<List<String>> resultListener) {
        a();
        this.f = PlacesApi.a().b(this.l, this.b);
        if (this.a != null) {
            this.f.a("at", this.a.getLatitude() + "," + this.a.getLongitude());
        }
        return super.execute(resultListener);
    }
}
