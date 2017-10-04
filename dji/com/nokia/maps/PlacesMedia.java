package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.EditorialMedia;
import com.here.android.mpa.search.ImageMedia;
import com.here.android.mpa.search.Media;
import com.here.android.mpa.search.Media.Type;
import com.here.android.mpa.search.RatingMedia;
import com.here.android.mpa.search.ReviewMedia;
import com.here.android.mpa.search.SupplierLink;
import com.here.android.mpa.search.UserLink;
import com.here.android.mpa.search.ViaLink;
import java.util.Map;

public class PlacesMedia<T> {
    private static k<Media, PlacesMedia<?>> b;
    private static am<Media, PlacesMedia<?>> c;
    private Type a = Type.UNKNOWN;
    @SerializedName("attribution")
    protected String m_attribution;
    @SerializedName("date")
    protected String m_date;
    @SerializedName("description")
    protected String m_description;
    @SerializedName("dimensions")
    protected Map<String, String> m_dimensions = new LinkedTreeMap();
    @SerializedName("href")
    protected String m_href;
    @SerializedName("id")
    protected String m_id;
    @SerializedName("language")
    protected String m_language;
    @SerializedName("average")
    protected double m_ratingAverage = 0.0d;
    @SerializedName("count")
    protected int m_ratingCount = 0;
    @SerializedName("report")
    protected PlacesLink m_report;
    @SerializedName("rating")
    protected double m_reviewRating = 0.0d;
    @SerializedName("src")
    protected String m_src;
    @SerializedName("supplier")
    protected PlacesLink m_supplier;
    @SerializedName("title")
    protected String m_title;
    @SerializedName("type")
    protected String m_type;
    @SerializedName("url")
    protected String m_url;
    @SerializedName("user")
    protected PlacesLink m_user;
    @SerializedName("via")
    protected PlacesLink m_via;

    public static void a(k<Media, PlacesMedia<?>> kVar, am<Media, PlacesMedia<?>> amVar) {
        b = kVar;
        c = amVar;
    }

    static PlacesMedia<?> a(Media media) {
        return (PlacesMedia) b.a(media);
    }

    static Media a(PlacesMedia<?> placesMedia) {
        if (placesMedia != null) {
            return (Media) c.a(placesMedia);
        }
        return null;
    }

    static {
        ce.a(EditorialMedia.class);
        ce.a(ImageMedia.class);
        ce.a(RatingMedia.class);
        ce.a(ReviewMedia.class);
    }

    PlacesMedia(Type type) {
        this.a = type;
    }

    public final String a() {
        return em.a(this.m_attribution);
    }

    public final double b() {
        return this.m_ratingAverage;
    }

    public final int c() {
        return this.m_ratingCount;
    }

    public final SupplierLink d() {
        return PlacesLink.d(this.m_supplier);
    }

    public final ViaLink e() {
        return PlacesLink.f(this.m_via);
    }

    public final String f() {
        return em.a(this.m_src);
    }

    public final String g() {
        return em.a(this.m_id);
    }

    public final UserLink h() {
        return PlacesLink.e(this.m_user);
    }

    public final String a(int i, int i2) {
        if (this.m_dimensions == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (i > 0) {
            stringBuffer.append("w");
            stringBuffer.append(i);
        }
        if (i > 0 && i2 > 0) {
            stringBuffer.append("-");
        }
        if (i2 > 0) {
            stringBuffer.append("h");
            stringBuffer.append(i2);
        }
        return (String) this.m_dimensions.get(stringBuffer.toString());
    }

    public final String i() {
        return em.a(this.m_date);
    }

    public final String j() {
        return em.a(this.m_description);
    }

    public final String k() {
        return em.a(this.m_language);
    }

    public final double l() {
        return this.m_reviewRating;
    }

    public final String m() {
        return em.a(this.m_title);
    }

    public Type n() {
        return this.a;
    }

    void a(Type type) {
        this.a = type;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.m_attribution == null ? 0 : this.m_attribution.hashCode()) + 31;
        long doubleToLongBits = Double.doubleToLongBits(this.m_ratingAverage);
        hashCode = (this.a == null ? 0 : this.a.hashCode()) + (((this.m_language == null ? 0 : this.m_language.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + (((this.m_dimensions == null ? 0 : this.m_dimensions.hashCode()) + (((this.m_description == null ? 0 : this.m_description.hashCode()) + (((this.m_date == null ? 0 : this.m_date.hashCode()) + (((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.m_ratingCount) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
        doubleToLongBits = Double.doubleToLongBits(this.m_reviewRating);
        hashCode = ((this.m_user == null ? 0 : this.m_user.hashCode()) + (((this.m_url == null ? 0 : this.m_url.hashCode()) + (((this.m_type == null ? 0 : this.m_type.hashCode()) + (((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_supplier == null ? 0 : this.m_supplier.hashCode()) + (((this.m_src == null ? 0 : this.m_src.hashCode()) + (((this.m_report == null ? 0 : this.m_report.hashCode()) + (((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.m_via != null) {
            i = this.m_via.hashCode();
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
            obj = (PlacesMedia) obj;
        } else if (EditorialMedia.class == obj.getClass()) {
            obj = a((EditorialMedia) obj);
        } else if (ImageMedia.class == obj.getClass()) {
            obj = a((ImageMedia) obj);
        } else if (ReviewMedia.class != obj.getClass()) {
            return false;
        } else {
            obj = a((ReviewMedia) obj);
        }
        if (this.m_attribution == null) {
            if (!TextUtils.isEmpty(obj.m_attribution)) {
                return false;
            }
        } else if (!this.m_attribution.equals(obj.m_attribution)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m_ratingAverage) != Double.doubleToLongBits(obj.m_ratingAverage)) {
            return false;
        }
        if (this.m_ratingCount != obj.m_ratingCount) {
            return false;
        }
        if (this.m_date == null) {
            if (!TextUtils.isEmpty(obj.m_date)) {
                return false;
            }
        } else if (!this.m_date.equals(obj.m_date)) {
            return false;
        }
        if (this.m_description == null) {
            if (!TextUtils.isEmpty(obj.m_description)) {
                return false;
            }
        } else if (!this.m_description.equals(obj.m_description)) {
            return false;
        }
        if (this.m_dimensions == null) {
            if (!(obj.m_dimensions == null || obj.m_dimensions.isEmpty())) {
                return false;
            }
        } else if (obj.m_dimensions == null) {
            if (!this.m_dimensions.isEmpty()) {
                return false;
            }
        } else if (!this.m_dimensions.equals(obj.m_dimensions)) {
            return false;
        }
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(obj.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(obj.m_href)) {
            return false;
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_language == null) {
            if (!TextUtils.isEmpty(obj.m_language)) {
                return false;
            }
        } else if (!this.m_language.equals(obj.m_language)) {
            return false;
        }
        if (this.a != obj.a) {
            return false;
        }
        if (Double.doubleToLongBits(this.m_reviewRating) != Double.doubleToLongBits(obj.m_reviewRating)) {
            return false;
        }
        if (this.m_report == null) {
            if (obj.m_report != null) {
                return false;
            }
        } else if (!this.m_report.equals(obj.m_report)) {
            return false;
        }
        if (this.m_src == null) {
            if (!TextUtils.isEmpty(obj.m_src)) {
                return false;
            }
        } else if (!this.m_src.equals(obj.m_src)) {
            return false;
        }
        if (this.m_supplier == null) {
            if (obj.m_supplier != null) {
                return false;
            }
        } else if (!this.m_supplier.equals(obj.m_supplier)) {
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
            if (obj.m_type != null) {
                return false;
            }
        } else if (!this.m_type.equals(obj.m_type)) {
            return false;
        }
        if (this.m_url == null) {
            if (obj.m_url != null) {
                return false;
            }
        } else if (!this.m_url.equals(obj.m_url)) {
            return false;
        }
        if (this.m_user == null) {
            if (obj.m_user != null) {
                return false;
            }
        } else if (!this.m_user.equals(obj.m_user)) {
            return false;
        }
        if (this.m_via == null) {
            if (obj.m_via != null) {
                return false;
            }
            return true;
        } else if (this.m_via.equals(obj.m_via)) {
            return true;
        } else {
            return false;
        }
    }
}
