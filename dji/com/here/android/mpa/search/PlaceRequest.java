package com.here.android.mpa.search;

import android.util.Pair;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.PlacesPlaceRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.List;

public class PlaceRequest extends Request<Place> {
    private List<Pair<Integer, Integer>> a;
    private PlaceLink b;
    private String c;
    private String d;

    @Online
    public PlaceRequest(PlaceLink placeLink) {
        dy.a((Object) placeLink, "PlaceLink is null");
        this.b = placeLink;
    }

    @Online
    public PlaceRequest(String str, String str2) {
        boolean z = true;
        dy.a((Object) str, "Lookup source is null");
        dy.a((Object) str2, "Lookup ID is null");
        dy.a(!str.isEmpty(), "Lookup source is empty");
        if (str2.isEmpty()) {
            z = false;
        }
        dy.a(z, "Lookup ID is empty");
        this.c = str;
        this.d = str2;
    }

    PlaceRequest(PlacesPlaceRequest placesPlaceRequest) {
        super(placesPlaceRequest);
    }

    @Online
    public RichTextFormatting getRichTextFormatting() {
        return this.g;
    }

    @Online
    public PlaceRequest setRichTextFormatting(RichTextFormatting richTextFormatting) {
        this.g = richTextFormatting;
        return this;
    }

    @Online
    public PlaceRequest addReference(String str) {
        return (PlaceRequest) super.addReference(str);
    }

    @Online
    public List<String> getReferences() {
        return super.getReferences();
    }

    @Online
    public void addImageDimensions(int i, int i2) {
        boolean z = true;
        dy.a(i >= 0, "Width must be a positive value");
        if (i2 < 0) {
            z = false;
        }
        dy.a(z, "Height must be a positive value");
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(new Pair(Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @Online
    public ErrorCode execute(ResultListener<Place> resultListener) {
        if (this.f == null) {
            if (this.b != null) {
                this.f = PlacesApi.a().a(this.b);
            } else {
                this.f = PlacesApi.a().a(this.c, this.d);
            }
        }
        if (this.a != null) {
            for (Pair pair : this.a) {
                this.f.a(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue());
            }
        }
        return super.execute(resultListener);
    }

    static {
        PlacesPlaceRequest.a(new k<PlaceRequest, PlacesPlaceRequest>() {
            public PlacesPlaceRequest a(PlaceRequest placeRequest) {
                return (PlacesPlaceRequest) placeRequest.f;
            }
        }, new am<PlaceRequest, PlacesPlaceRequest>() {
            public PlaceRequest a(PlacesPlaceRequest placesPlaceRequest) {
                return placesPlaceRequest != null ? new PlaceRequest(placesPlaceRequest) : null;
            }
        });
    }
}
