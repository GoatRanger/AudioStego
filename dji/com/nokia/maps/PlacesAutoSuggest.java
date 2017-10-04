package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.AutoSuggest;
import com.here.android.mpa.search.AutoSuggest.a;
import com.here.android.mpa.search.AutoSuggestPlace;
import com.here.android.mpa.search.AutoSuggestSearch;
import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.PlaceRequest;
import com.here.android.mpa.search.b;
import java.util.ArrayList;
import java.util.List;

public class PlacesAutoSuggest {
    private static k<AutoSuggest, PlacesAutoSuggest> a;
    private static am<b, PlacesAutoSuggest> b;
    private static am<AutoSuggestSearch, PlacesAutoSuggest> c;
    private static am<AutoSuggestPlace, PlacesAutoSuggest> d;
    @SerializedName("bbox")
    private List<Double> m_bbox = new ArrayList();
    @SerializedName("category")
    protected String m_category;
    @SerializedName("highlightedTitle")
    protected String m_highlightedTitle;
    @SerializedName("href")
    protected String m_href;
    @SerializedName("position")
    protected List<Double> m_position = new ArrayList();
    @SerializedName("title")
    protected String m_title;
    @SerializedName("type")
    protected String m_type;
    @SerializedName("vicinity")
    protected String m_vicinity;

    public static void a(k<AutoSuggest, PlacesAutoSuggest> kVar, am<b, PlacesAutoSuggest> amVar, am<AutoSuggestSearch, PlacesAutoSuggest> amVar2, am<AutoSuggestPlace, PlacesAutoSuggest> amVar3) {
        a = kVar;
        b = amVar;
        c = amVar2;
        d = amVar3;
    }

    static PlacesAutoSuggest a(AutoSuggest autoSuggest) {
        return (PlacesAutoSuggest) a.a(autoSuggest);
    }

    static AutoSuggestSearch a(PlacesAutoSuggest placesAutoSuggest) {
        if (placesAutoSuggest != null) {
            return (AutoSuggestSearch) c.a(placesAutoSuggest);
        }
        return null;
    }

    static AutoSuggestPlace b(PlacesAutoSuggest placesAutoSuggest) {
        if (placesAutoSuggest != null) {
            return (AutoSuggestPlace) d.a(placesAutoSuggest);
        }
        return null;
    }

    static {
        ce.a(AutoSuggest.class);
    }

    public final String a() {
        return em.a(this.m_title);
    }

    public final String b() {
        return em.a(this.m_highlightedTitle);
    }

    public final String c() {
        return em.a(this.m_vicinity);
    }

    public final GeoCoordinate d() {
        if (this.m_position == null || this.m_position.size() != 2) {
            return null;
        }
        return new GeoCoordinate(((Double) this.m_position.get(0)).doubleValue(), ((Double) this.m_position.get(1)).doubleValue());
    }

    public final String e() {
        return em.a(this.m_category);
    }

    public final GeoBoundingBox f() {
        if (this.m_bbox == null || this.m_bbox.size() != 4) {
            return null;
        }
        return new GeoBoundingBox(new GeoCoordinate(((Double) this.m_bbox.get(3)).doubleValue(), ((Double) this.m_bbox.get(0)).doubleValue()), new GeoCoordinate(((Double) this.m_bbox.get(1)).doubleValue(), ((Double) this.m_bbox.get(2)).doubleValue()));
    }

    public final PlaceRequest g() {
        return PlacesPlaceRequest.a(PlacesApi.a().a(this.m_href));
    }

    public final DiscoveryRequest h() {
        return PlacesApi.a().a(this.m_href, null);
    }

    public a i() {
        if (this.m_type.contentEquals("urn:nlp-types:place")) {
            return a.b;
        }
        if (this.m_type.contentEquals("urn:nlp-types:search")) {
            return a.c;
        }
        return a.a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_type == null ? 0 : this.m_type.hashCode()) + (((this.m_highlightedTitle == null ? 0 : this.m_highlightedTitle.hashCode()) + (((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_position == null ? 0 : this.m_position.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + (((this.m_category == null ? 0 : this.m_category.hashCode()) + (((this.m_bbox == null ? 0 : this.m_bbox.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
            obj = (PlacesAutoSuggest) obj;
        } else if (AutoSuggest.class == obj.getClass()) {
            obj = a((AutoSuggest) obj);
        } else if (AutoSuggestPlace.class == obj.getClass()) {
            obj = a((AutoSuggestPlace) obj);
        } else if (AutoSuggestSearch.class != obj.getClass()) {
            return false;
        } else {
            obj = a((AutoSuggestSearch) obj);
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
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(obj.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(obj.m_href)) {
            return false;
        }
        if (this.m_position == null) {
            if (obj.m_position != null) {
                return false;
            }
        } else if (!this.m_position.equals(obj.m_position)) {
            return false;
        }
        if (this.m_title == null) {
            if (!TextUtils.isEmpty(obj.m_title)) {
                return false;
            }
        } else if (!this.m_title.equals(obj.m_title)) {
            return false;
        }
        if (this.m_highlightedTitle == null) {
            if (!TextUtils.isEmpty(obj.m_highlightedTitle)) {
                return false;
            }
        } else if (!this.m_highlightedTitle.equals(obj.m_highlightedTitle)) {
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
