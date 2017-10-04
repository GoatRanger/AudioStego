package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.PlacesBaseRequest;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.du;
import com.nokia.maps.dy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Request<T> {
    @Online
    public static final String BUILDING_ID_REFERENCE_NAME = "building";
    @Online
    public static final String PVID_ID_REFERENCE_NAME = "pvid";
    @Online
    public static final String VENUES_CONTENT_ID_REFERENCE_NAME = "venues.content";
    @Online
    public static final String VENUES_DESTINATION_ID_REFERENCE_NAME = "venues.destination";
    @Online
    public static final String VENUES_ID_REFERENCE_NAME = "venues";
    @Online
    public static final String VENUES_VENUE_ID_REFERENCE_NAME = "venues.venue";
    private static final RichTextFormatting a = RichTextFormatting.HTML;
    private AtomicBoolean b = new AtomicBoolean(false);
    private Map<String, String> c = new HashMap();
    protected PlacesBaseRequest<T> f;
    protected RichTextFormatting g = a;
    protected int h = 20;
    protected GeoBoundingBox i = null;
    protected List<String> j = new ArrayList();
    protected String k = null;
    protected a l = a.b;

    static {
        PlacesBaseRequest.a(new 1());
    }

    protected Request() {
    }

    protected Request(PlacesBaseRequest<T> placesBaseRequest) {
        this.f = placesBaseRequest;
    }

    protected void a() {
        this.l = du.a();
    }

    @Online
    public ErrorCode execute(ResultListener<T> resultListener) {
        if (this.b.get()) {
            return ErrorCode.CANCELLED;
        }
        if (this.g != a) {
            this.f.a(this.g);
        }
        if (this.h != 20) {
            this.f.a(this.h);
        }
        if (this.i != null) {
            this.f.a(this.i);
        }
        this.f.a(this.j);
        this.f.a(this.l);
        if (this.k != null) {
            this.f.a(this.k);
        }
        for (Entry entry : this.c.entrySet()) {
            this.f.b((String) entry.getKey(), (String) entry.getValue());
        }
        return this.f.a(resultListener);
    }

    @Online
    public boolean cancel() {
        this.b.set(true);
        if (this.f == null) {
            return true;
        }
        this.f.c();
        return this.b.get();
    }

    @Online
    protected int getCollectionSize() {
        return this.h;
    }

    @Online
    protected Request<T> setCollectionSize(int i) {
        boolean z = true;
        dy.a(i >= 1, "Collection size value must be greater than zero");
        if (i > 100) {
            z = false;
        }
        dy.a(z, "Collection size value cannot be greater than 100");
        this.h = i;
        return this;
    }

    @Online
    protected Request<T> addReference(String str) {
        dy.a(str, "Name is null");
        dy.a(Boolean.valueOf(!str.isEmpty()), "Name is empty");
        this.j.add(str);
        return this;
    }

    @Online
    protected List<String> getReferences() {
        return this.j;
    }

    @Online
    protected Request<T> setMapViewport(GeoBoundingBox geoBoundingBox) {
        dy.a(geoBoundingBox, "Map Viewport is null");
        this.i = geoBoundingBox;
        return this;
    }

    @HybridPlus
    @Deprecated
    public Request<T> setForceOffline(boolean z) {
        return this;
    }

    @HybridPlus
    public Request<T> setUserAuthentication(String str) {
        dy.a(str, "User authentication token is null.");
        dy.a(str, "User authentication token is invalid (empty).");
        this.k = str;
        return this;
    }
}
