package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.Category;
import com.here.android.mpa.search.ContactDetail;
import com.here.android.mpa.search.DiscoveryLink;
import com.here.android.mpa.search.EditorialMedia;
import com.here.android.mpa.search.ExtendedAttribute;
import com.here.android.mpa.search.ImageMedia;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.Media;
import com.here.android.mpa.search.MediaCollectionPage;
import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.RatingMedia;
import com.here.android.mpa.search.Ratings;
import com.here.android.mpa.search.ReportingLink;
import com.here.android.mpa.search.ReviewMedia;
import com.here.android.mpa.search.SupplierLink;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class PlacesPlace {
    private static k<Place, PlacesPlace> a;
    private static am<Place, PlacesPlace> b;
    @SerializedName("alternativeNames")
    private List<PlacesAlternativeName> m_alternativeNames = new ArrayList();
    @SerializedName("attribution")
    private String m_attribution;
    @SerializedName("categories")
    private List<PlacesCategory> m_categories;
    @SerializedName("contacts")
    private PlacesContact m_contacts;
    @SerializedName("extended")
    private Map<String, PlacesAttribute> m_extendedAttributes = new LinkedTreeMap();
    @SerializedName("icon")
    private String m_icon;
    @SerializedName("location")
    private PlacesLocation m_location;
    @SerializedName("media")
    private PlacesMediaContent m_media;
    @SerializedName("name")
    private String m_name;
    @SerializedName("placeId")
    private String m_placeId;
    @SerializedName("references")
    private Map<String, PlacesReference> m_references = new LinkedTreeMap();
    @SerializedName("related")
    private Map<String, PlacesLink> m_related = new LinkedTreeMap();
    @SerializedName("report")
    private PlacesLink m_report;
    @SerializedName("supplier")
    private PlacesLink m_supplier;
    @SerializedName("via")
    private PlacesLink m_via;
    @SerializedName("view")
    private String m_view;

    public static void a(k<Place, PlacesPlace> kVar, am<Place, PlacesPlace> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesPlace a(Place place) {
        return (PlacesPlace) a.a(place);
    }

    static Place a(PlacesPlace placesPlace) {
        if (placesPlace != null) {
            return (Place) b.a(placesPlace);
        }
        return null;
    }

    static {
        ce.a(Place.class);
    }

    public final String a() {
        return em.a(this.m_placeId);
    }

    public final String b() {
        return em.a(this.m_view);
    }

    public final String c() {
        return em.a(this.m_name);
    }

    public final Map<String, List<String>> d() {
        Map<String, List<String>> linkedTreeMap = new LinkedTreeMap();
        if (this.m_alternativeNames != null) {
            for (PlacesAlternativeName placesAlternativeName : this.m_alternativeNames) {
                List list = (List) linkedTreeMap.get(placesAlternativeName.a());
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(placesAlternativeName.b());
                linkedTreeMap.put(placesAlternativeName.a(), list);
            }
        }
        return linkedTreeMap;
    }

    public final Location e() {
        return PlacesLocation.a(this.m_location);
    }

    public final List<Category> f() {
        List<Category> arrayList = new ArrayList();
        if (this.m_categories != null) {
            for (PlacesCategory a : this.m_categories) {
                arrayList.add(PlacesCategory.a(a));
            }
        }
        return arrayList;
    }

    public final String g() {
        return em.a(this.m_icon);
    }

    public final List<ContactDetail> h() {
        List<ContactDetail> arrayList = new ArrayList();
        if (this.m_contacts != null) {
            for (PlacesContactDetail placesContactDetail : this.m_contacts.a()) {
                placesContactDetail.a("email");
                arrayList.add(PlacesContactDetail.a(placesContactDetail));
            }
            for (PlacesContactDetail placesContactDetail2 : this.m_contacts.b()) {
                placesContactDetail2.a("fax");
                arrayList.add(PlacesContactDetail.a(placesContactDetail2));
            }
            for (PlacesContactDetail placesContactDetail22 : this.m_contacts.c()) {
                placesContactDetail22.a("phone");
                arrayList.add(PlacesContactDetail.a(placesContactDetail22));
            }
            for (PlacesContactDetail placesContactDetail222 : this.m_contacts.d()) {
                placesContactDetail222.a("website");
                arrayList.add(PlacesContactDetail.a(placesContactDetail222));
            }
        }
        return arrayList;
    }

    public final String i() {
        return em.a(this.m_attribution);
    }

    public final SupplierLink j() {
        return PlacesLink.d(this.m_supplier);
    }

    public Ratings k() {
        Ratings a = PlacesRatings.a(new PlacesRatings(0, 0.0d));
        if (this.m_media != null) {
            dn c = this.m_media.c();
            if (c != null) {
                for (Media media : c.e()) {
                    if (media instanceof RatingMedia) {
                        RatingMedia ratingMedia = (RatingMedia) media;
                        SupplierLink supplier = media.getSupplier();
                        if (supplier != null && supplier.getId().matches("here")) {
                            return PlacesRatings.a(new PlacesRatings(ratingMedia.getCount(), ratingMedia.getAverage()));
                        }
                    }
                }
            }
        }
        return a;
    }

    public final String a(String str) {
        return (this.m_references == null || !this.m_references.containsKey(str)) ? "" : ((PlacesReference) this.m_references.get(str)).a();
    }

    public final List<String> b(String str) {
        return (this.m_references == null || !this.m_references.containsKey(str)) ? new ArrayList() : ((PlacesReference) this.m_references.get(str)).b();
    }

    public final List<ExtendedAttribute> l() {
        List<ExtendedAttribute> arrayList = new ArrayList();
        if (this.m_extendedAttributes != null) {
            for (Entry entry : this.m_extendedAttributes.entrySet()) {
                PlacesAttribute placesAttribute = (PlacesAttribute) entry.getValue();
                placesAttribute.a((String) entry.getKey());
                if (((String) entry.getKey()).contentEquals(ExtendedAttribute.TRANSIT_LINES_EXTENDED_ATTRIBUTE_ID)) {
                    arrayList.add(PlacesAttribute.b(placesAttribute));
                } else if (((String) entry.getKey()).contains(ExtendedAttribute.TRANSIT_DEPARTURES_EXTENDED_ATTRIBUTE_ID)) {
                    arrayList.add(PlacesAttribute.c(placesAttribute));
                } else {
                    arrayList.add(PlacesAttribute.a(placesAttribute));
                }
            }
        }
        return arrayList;
    }

    public final MediaCollectionPage<EditorialMedia> m() {
        return this.m_media != null ? PlacesMediaCollectionPage.a(this.m_media.a()) : null;
    }

    public final MediaCollectionPage<ImageMedia> n() {
        return this.m_media != null ? PlacesMediaCollectionPage.a(this.m_media.b()) : null;
    }

    public final MediaCollectionPage<RatingMedia> o() {
        return this.m_media != null ? PlacesMediaCollectionPage.a(this.m_media.c()) : null;
    }

    public final MediaCollectionPage<ReviewMedia> p() {
        return this.m_media != null ? PlacesMediaCollectionPage.a(this.m_media.d()) : null;
    }

    public final Map<String, DiscoveryLink> q() {
        Map<String, DiscoveryLink> linkedTreeMap = new LinkedTreeMap();
        if (this.m_related != null) {
            for (Entry entry : this.m_related.entrySet()) {
                linkedTreeMap.put(entry.getKey(), PlacesLink.a((PlacesLink) entry.getValue()));
            }
        }
        return linkedTreeMap;
    }

    public final PlaceLink r() {
        PlaceLink placeLink = null;
        if (this.m_related != null) {
            for (Entry entry : this.m_related.entrySet()) {
                PlaceLink b;
                if (((String) entry.getKey()).contains("venue")) {
                    b = PlacesLink.b((PlacesLink) entry.getValue());
                } else {
                    b = placeLink;
                }
                placeLink = b;
            }
        }
        return placeLink;
    }

    public final ReportingLink s() {
        return PlacesLink.c(this.m_report);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.m_alternativeNames == null) {
            i = 0;
        } else {
            i = this.m_alternativeNames.hashCode();
        }
        int hashCode = ((this.m_contacts == null ? 0 : this.m_contacts.hashCode()) + (((this.m_categories == null ? 0 : this.m_categories.hashCode()) + (((this.m_attribution == null ? 0 : this.m_attribution.hashCode()) + ((i + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_extendedAttributes == null) {
            i = 0;
        } else {
            i = this.m_extendedAttributes.hashCode();
        }
        i = ((this.m_via == null ? 0 : this.m_via.hashCode()) + (((this.m_supplier == null ? 0 : this.m_supplier.hashCode()) + (((this.m_report == null ? 0 : this.m_report.hashCode()) + (((this.m_related == null ? 0 : this.m_related.hashCode()) + (((this.m_placeId == null ? 0 : this.m_placeId.hashCode()) + (((this.m_name == null ? 0 : this.m_name.hashCode()) + (((this.m_media == null ? 0 : this.m_media.hashCode()) + (((this.m_location == null ? 0 : this.m_location.hashCode()) + (((this.m_icon == null ? 0 : this.m_icon.hashCode()) + ((i + hashCode) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.m_view != null) {
            i2 = this.m_view.hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesPlace) obj;
        } else if (Place.class != obj.getClass()) {
            return false;
        } else {
            obj = a((Place) obj);
        }
        if (this.m_alternativeNames == null) {
            if (obj.m_alternativeNames != null) {
                return false;
            }
        } else if (!this.m_alternativeNames.equals(obj.m_alternativeNames)) {
            return false;
        }
        if (this.m_attribution == null) {
            if (!TextUtils.isEmpty(obj.m_attribution)) {
                return false;
            }
        } else if (!this.m_attribution.equals(obj.m_attribution)) {
            return false;
        }
        if (this.m_categories == null) {
            if (obj.m_categories != null) {
                return false;
            }
        } else if (!this.m_categories.equals(obj.m_categories)) {
            return false;
        }
        if (this.m_contacts == null) {
            if (obj.m_contacts != null) {
                return false;
            }
        } else if (!this.m_contacts.equals(obj.m_contacts)) {
            return false;
        }
        if (this.m_extendedAttributes == null) {
            if (obj.m_extendedAttributes != null) {
                return false;
            }
        } else if (!this.m_extendedAttributes.equals(obj.m_extendedAttributes)) {
            return false;
        }
        if (this.m_icon == null) {
            if (!TextUtils.isEmpty(obj.m_icon)) {
                return false;
            }
        } else if (!this.m_icon.equals(obj.m_icon)) {
            return false;
        }
        if (this.m_location == null) {
            if (obj.m_location != null) {
                return false;
            }
        } else if (!this.m_location.equals(obj.m_location)) {
            return false;
        }
        if (this.m_media == null) {
            if (obj.m_media != null) {
                return false;
            }
        } else if (!this.m_media.equals(obj.m_media)) {
            return false;
        }
        if (this.m_name == null) {
            if (!TextUtils.isEmpty(obj.m_name)) {
                return false;
            }
        } else if (!this.m_name.equals(obj.m_name)) {
            return false;
        }
        if (this.m_placeId == null) {
            if (!TextUtils.isEmpty(obj.m_placeId)) {
                return false;
            }
        } else if (!this.m_placeId.equals(obj.m_placeId)) {
            return false;
        }
        if (this.m_related == null) {
            if (obj.m_related != null) {
                return false;
            }
        } else if (!this.m_related.equals(obj.m_related)) {
            return false;
        }
        if (this.m_report == null) {
            if (obj.m_report != null) {
                return false;
            }
        } else if (!this.m_report.equals(obj.m_report)) {
            return false;
        }
        if (this.m_supplier == null) {
            if (obj.m_supplier != null) {
                return false;
            }
        } else if (!this.m_supplier.equals(obj.m_supplier)) {
            return false;
        }
        if (this.m_via == null) {
            if (obj.m_via != null) {
                return false;
            }
        } else if (!this.m_via.equals(obj.m_via)) {
            return false;
        }
        if (this.m_view == null) {
            if (TextUtils.isEmpty(obj.m_view)) {
                return true;
            }
            return false;
        } else if (this.m_view.equals(obj.m_view)) {
            return true;
        } else {
            return false;
        }
    }
}
