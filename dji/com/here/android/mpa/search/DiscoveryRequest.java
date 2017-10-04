package com.here.android.mpa.search;

import android.util.Pair;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesDiscoveryRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryRequest extends Request<DiscoveryResultPage> {
    protected GeoCoordinate a = null;
    protected GeoBoundingBox b = null;
    protected int c = 0;
    protected CategoryFilter d = null;
    protected List<Pair<Integer, Integer>> e;

    protected DiscoveryRequest() {
    }

    protected DiscoveryRequest(PlacesDiscoveryRequest placesDiscoveryRequest) {
        super(placesDiscoveryRequest);
    }

    @Online
    public RichTextFormatting getRichTextFormatting() {
        return this.g;
    }

    @Online
    public DiscoveryRequest setRichTextFormatting(RichTextFormatting richTextFormatting) {
        this.g = richTextFormatting;
        return this;
    }

    @Online
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Online
    public DiscoveryRequest setCollectionSize(int i) {
        return (DiscoveryRequest) super.setCollectionSize(i);
    }

    @Online
    public DiscoveryRequest addReference(String str) {
        return (DiscoveryRequest) super.addReference(str);
    }

    @Online
    public List<String> getReferences() {
        return super.getReferences();
    }

    @Online
    public DiscoveryRequest setMapViewport(GeoBoundingBox geoBoundingBox) {
        return (DiscoveryRequest) super.setMapViewport(geoBoundingBox);
    }

    protected DiscoveryRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Search center coordinate is null");
        dy.a(geoCoordinate.isValid(), "Search center coordinate is invalid");
        this.a = geoCoordinate;
        return this;
    }

    protected DiscoveryRequest setSearchArea(GeoCoordinate geoCoordinate, int i) {
        dy.a((Object) geoCoordinate, "Search center coordinate is null");
        dy.a(geoCoordinate.isValid(), "Search center coordinate is invalid");
        dy.a(i > 0, "Search radius must be greater than 0");
        this.a = geoCoordinate;
        this.c = i;
        return this;
    }

    protected DiscoveryRequest setSearchArea(GeoBoundingBox geoBoundingBox) {
        dy.a((Object) geoBoundingBox, "Search area bounding box is null");
        this.b = geoBoundingBox;
        return this;
    }

    protected DiscoveryRequest setCategoryFilter(CategoryFilter categoryFilter) {
        this.d = categoryFilter;
        return this;
    }

    @Online
    public void addImageDimensions(int i, int i2) {
        boolean z = true;
        dy.a(i >= 0, "Width must be a positive value");
        if (i2 < 0) {
            z = false;
        }
        dy.a(z, "Height must be a positive value");
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(new Pair(Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @Online
    public ErrorCode execute(ResultListener<DiscoveryResultPage> resultListener) {
        if (this.e != null) {
            for (Pair pair : this.e) {
                this.f.a(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue());
            }
        }
        return super.execute(resultListener);
    }

    static {
        PlacesDiscoveryRequest.a(new k<DiscoveryRequest, PlacesDiscoveryRequest>() {
            public PlacesDiscoveryRequest a(DiscoveryRequest discoveryRequest) {
                return (PlacesDiscoveryRequest) discoveryRequest.f;
            }
        }, new am<DiscoveryRequest, PlacesDiscoveryRequest>() {
            public DiscoveryRequest a(PlacesDiscoveryRequest placesDiscoveryRequest) {
                return placesDiscoveryRequest != null ? new DiscoveryRequest(placesDiscoveryRequest) : null;
            }
        });
    }
}
