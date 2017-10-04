package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Category;
import com.here.android.mpa.search.DiscoveryLink;
import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.DiscoveryResult;
import com.here.android.mpa.search.DiscoveryResult.ResultType;
import com.here.android.mpa.search.Link;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.PlaceRequest;
import com.here.android.mpa.search.ReportingLink;
import com.here.android.mpa.search.SupplierLink;
import com.here.android.mpa.search.UserLink;
import com.here.android.mpa.search.ViaLink;
import com.here.android.mpa.search.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlacesLink {
    private static k<Link, PlacesLink> a;
    private static am<c, PlacesLink> b;
    private static am<DiscoveryLink, PlacesLink> c;
    private static am<PlaceLink, PlacesLink> d;
    private static am<ReportingLink, PlacesLink> e;
    private static am<SupplierLink, PlacesLink> f;
    private static am<UserLink, PlacesLink> g;
    private static am<ViaLink, PlacesLink> h;
    @SerializedName("averageRating")
    private double m_averageRating = 0.0d;
    @SerializedName("bbox")
    private List<Double> m_bbox = new ArrayList();
    @SerializedName("category")
    protected PlacesCategory m_category;
    @SerializedName("contacts")
    private PlacesContact m_contacts;
    @SerializedName("distance")
    protected int m_distance = 0;
    @SerializedName("href")
    protected String m_href;
    @SerializedName("icon")
    protected String m_icon;
    @SerializedName("id")
    protected String m_id;
    @SerializedName("name")
    protected String m_name;
    @SerializedName("position")
    protected List<Double> m_position = new ArrayList();
    @SerializedName("references")
    protected Map<String, PlacesReference> m_references = new LinkedTreeMap();
    @SerializedName("sponsored")
    protected boolean m_sponsored = false;
    @SerializedName("title")
    protected String m_title;
    @SerializedName("type")
    protected String m_type;
    @SerializedName("vicinity")
    protected String m_vicinity;

    public static void a(k<Link, PlacesLink> kVar, am<c, PlacesLink> amVar, am<DiscoveryLink, PlacesLink> amVar2, am<PlaceLink, PlacesLink> amVar3, am<ReportingLink, PlacesLink> amVar4, am<SupplierLink, PlacesLink> amVar5, am<UserLink, PlacesLink> amVar6, am<ViaLink, PlacesLink> amVar7) {
        a = kVar;
        b = amVar;
        c = amVar2;
        d = amVar3;
        e = amVar4;
        f = amVar5;
        g = amVar6;
        h = amVar7;
    }

    static PlacesLink a(Link link) {
        return (PlacesLink) a.a(link);
    }

    static DiscoveryLink a(PlacesLink placesLink) {
        if (placesLink != null) {
            return (DiscoveryLink) c.a(placesLink);
        }
        return null;
    }

    static PlaceLink b(PlacesLink placesLink) {
        if (placesLink != null) {
            return (PlaceLink) d.a(placesLink);
        }
        return null;
    }

    static ReportingLink c(PlacesLink placesLink) {
        if (placesLink != null) {
            return (ReportingLink) e.a(placesLink);
        }
        return null;
    }

    static SupplierLink d(PlacesLink placesLink) {
        if (placesLink != null) {
            return (SupplierLink) f.a(placesLink);
        }
        return null;
    }

    static UserLink e(PlacesLink placesLink) {
        if (placesLink != null) {
            return (UserLink) g.a(placesLink);
        }
        return null;
    }

    static ViaLink f(PlacesLink placesLink) {
        if (placesLink != null) {
            return (ViaLink) h.a(placesLink);
        }
        return null;
    }

    static {
        ce.a(Link.class);
    }

    public final String a() {
        return em.a(this.m_href);
    }

    final String b() {
        return em.a(this.m_type);
    }

    public final String c() {
        return em.a(this.m_title);
    }

    public final String d() {
        return em.a(this.m_id);
    }

    public final String e() {
        return em.a(this.m_icon);
    }

    public final String f() {
        return em.a(this.m_name);
    }

    public ResultType g() {
        if (this.m_type.contentEquals("urn:nlp-types:place")) {
            return ResultType.PLACE;
        }
        if (this.m_type.contentEquals("urn:nlp-types:search")) {
            return ResultType.DISCOVERY;
        }
        return ResultType.UNKNOWN;
    }

    public final PlaceRequest h() {
        return PlacesPlaceRequest.a(PlacesApi.a().a(this.m_href));
    }

    public final GeoCoordinate i() {
        if (this.m_position == null || this.m_position.size() != 2) {
            return null;
        }
        return new GeoCoordinate(((Double) this.m_position.get(0)).doubleValue(), ((Double) this.m_position.get(1)).doubleValue());
    }

    public final int j() {
        return this.m_distance;
    }

    public final double k() {
        return this.m_averageRating;
    }

    public final Category l() {
        return PlacesCategory.a(this.m_category);
    }

    public final String m() {
        return em.a(this.m_vicinity);
    }

    public final GeoBoundingBox n() {
        if (this.m_bbox == null || this.m_bbox.size() != 4) {
            return null;
        }
        return new GeoBoundingBox(new GeoCoordinate(((Double) this.m_bbox.get(3)).doubleValue(), ((Double) this.m_bbox.get(0)).doubleValue()), new GeoCoordinate(((Double) this.m_bbox.get(1)).doubleValue(), ((Double) this.m_bbox.get(2)).doubleValue()));
    }

    public final boolean o() {
        return this.m_sponsored;
    }

    public final String a(String str) {
        return (this.m_references == null || !this.m_references.containsKey(str)) ? "" : ((PlacesReference) this.m_references.get(str)).a();
    }

    public final List<String> b(String str) {
        return (this.m_references == null || !this.m_references.containsKey(str)) ? new ArrayList() : ((PlacesReference) this.m_references.get(str)).b();
    }

    public final DiscoveryRequest p() {
        return PlacesApi.a().a(this.m_href, null);
    }

    public int hashCode() {
        int i = 0;
        long doubleToLongBits = Double.doubleToLongBits(this.m_averageRating);
        int hashCode = ((this.m_type == null ? 0 : this.m_type.hashCode()) + (((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_sponsored ? 1231 : 1237) + (((this.m_references == null ? 0 : this.m_references.hashCode()) + (((this.m_position == null ? 0 : this.m_position.hashCode()) + (((this.m_name == null ? 0 : this.m_name.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + (((this.m_icon == null ? 0 : this.m_icon.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + (((((this.m_contacts == null ? 0 : this.m_contacts.hashCode()) + (((this.m_category == null ? 0 : this.m_category.hashCode()) + (((this.m_bbox == null ? 0 : this.m_bbox.hashCode()) + ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31)) * 31)) * 31)) * 31) + this.m_distance) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.m_vicinity != null) {
            i = this.m_vicinity.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesLink) obj;
        } else if (Link.class == obj.getClass()) {
            obj = a((Link) obj);
        } else if (PlaceLink.class == obj.getClass()) {
            obj = a((PlaceLink) obj);
        } else if (DiscoveryLink.class == obj.getClass()) {
            obj = a((DiscoveryLink) obj);
        } else if (DiscoveryResult.class == obj.getClass()) {
            obj = a((DiscoveryResult) obj);
        } else if (c.class == obj.getClass()) {
            obj = a((c) obj);
        } else if (ReportingLink.class == obj.getClass()) {
            obj = a((ReportingLink) obj);
        } else if (SupplierLink.class == obj.getClass()) {
            obj = a((SupplierLink) obj);
        } else if (UserLink.class == obj.getClass()) {
            obj = a((UserLink) obj);
        } else if (ViaLink.class != obj.getClass()) {
            return false;
        } else {
            obj = a((ViaLink) obj);
        }
        if (Double.doubleToLongBits(this.m_averageRating) != Double.doubleToLongBits(obj.m_averageRating)) {
            return false;
        }
        if (this.m_bbox == null) {
            if (obj.m_bbox != null) {
                return false;
            }
        } else if (!this.m_bbox.equals(obj.m_bbox)) {
            return false;
        }
        if (this.m_category == null) {
            if (obj.m_category != null) {
                return false;
            }
        } else if (!this.m_category.equals(obj.m_category)) {
            return false;
        }
        if (this.m_contacts == null) {
            if (obj.m_contacts != null) {
                return false;
            }
        } else if (!this.m_contacts.equals(obj.m_contacts)) {
            return false;
        }
        if (this.m_distance != obj.m_distance) {
            return false;
        }
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(obj.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(obj.m_href)) {
            return false;
        }
        if (this.m_icon == null) {
            if (!TextUtils.isEmpty(obj.m_icon)) {
                return false;
            }
        } else if (!this.m_icon.equals(obj.m_icon)) {
            return false;
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_name == null) {
            if (!TextUtils.isEmpty(obj.m_name)) {
                return false;
            }
        } else if (!this.m_name.equals(obj.m_name)) {
            return false;
        }
        if (this.m_position == null) {
            if (obj.m_position != null) {
                return false;
            }
        } else if (!this.m_position.equals(obj.m_position)) {
            return false;
        }
        if (this.m_references == null) {
            if (obj.m_references != null) {
                return false;
            }
        } else if (!this.m_references.equals(obj.m_references)) {
            return false;
        }
        if (this.m_sponsored != obj.m_sponsored) {
            return false;
        }
        if (this.m_title == null) {
            if (!TextUtils.isEmpty(obj.m_title)) {
                return false;
            }
        } else if (!this.m_title.equals(obj.m_title)) {
            return false;
        }
        if (this.m_type == null) {
            if (!TextUtils.isEmpty(obj.m_type)) {
                return false;
            }
        } else if (!this.m_type.equals(obj.m_type)) {
            return false;
        }
        if (this.m_vicinity == null) {
            if (TextUtils.isEmpty(obj.m_vicinity)) {
                return true;
            }
            return false;
        } else if (this.m_vicinity.equals(obj.m_vicinity)) {
            return true;
        } else {
            return false;
        }
    }
}
