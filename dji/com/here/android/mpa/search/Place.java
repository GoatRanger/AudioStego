package com.here.android.mpa.search;

import com.nokia.maps.PlacesPlace;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import com.nokia.maps.k;
import java.util.List;
import java.util.Map;

public class Place {
    @Online
    public static final String PUBLIC_TRANSPORT_RELATED_LINK_NAME = "public-transport";
    @Online
    public static final String RECOMMENDED_RELATED_LINK_NAME = "recommended";
    private PlacesPlace a;

    Place() {
        this.a = new PlacesPlace();
    }

    private Place(PlacesPlace placesPlace) {
        this.a = placesPlace;
    }

    @Online
    public String getId() {
        return this.a.a();
    }

    @Online
    public String getViewUri() {
        return this.a.b();
    }

    @Online
    public String getName() {
        return this.a.c();
    }

    @Online
    public Map<String, List<String>> getAlternativeNames() {
        return this.a.d();
    }

    @Online
    public Location getLocation() {
        return this.a.e();
    }

    @Online
    public List<Category> getCategories() {
        return this.a.f();
    }

    @Online
    public String getIconUrl() {
        return this.a.g();
    }

    @Online
    public List<ContactDetail> getContacts() {
        return this.a.h();
    }

    @Online
    public String getAttributionText() {
        return this.a.i();
    }

    @Online
    public SupplierLink getSupplier() {
        return this.a.j();
    }

    @Online
    public Ratings getUserRatings() {
        return this.a.k();
    }

    @Online
    public final String getReference(String str) {
        dy.a((Object) str, "Name argument is null");
        dy.a(!str.isEmpty(), "Name argument is empty");
        return this.a.a(str);
    }

    @Online
    public final List<String> getAlternativeReferenceIds(String str) {
        dy.a((Object) str, "Name argument is null");
        dy.a(!str.isEmpty(), "Name argument is empty");
        return this.a.b(str);
    }

    @Online
    public List<ExtendedAttribute> getExtendedAttributes() {
        return this.a.l();
    }

    @Online
    public MediaCollectionPage<EditorialMedia> getEditorials() {
        return this.a.m();
    }

    @Online
    public MediaCollectionPage<ImageMedia> getImages() {
        return this.a.n();
    }

    @Online
    public MediaCollectionPage<RatingMedia> getRatings() {
        return this.a.o();
    }

    @Online
    public MediaCollectionPage<ReviewMedia> getReviews() {
        return this.a.p();
    }

    @Online
    public Map<String, DiscoveryLink> getRelated() {
        return this.a.q();
    }

    @Online
    public PlaceLink getResidingVenue() {
        return this.a.r();
    }

    @Online
    public final ReportingLink getReportingLink() {
        return this.a.s();
    }

    @Online
    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        PlacesPlace.a(new k<Place, PlacesPlace>() {
            public PlacesPlace a(Place place) {
                return place.a;
            }
        }, new am<Place, PlacesPlace>() {
            public Place a(PlacesPlace placesPlace) {
                return placesPlace != null ? new Place(placesPlace) : null;
            }
        });
    }
}
