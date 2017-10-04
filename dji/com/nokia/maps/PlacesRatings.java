package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.Ratings;

public class PlacesRatings {
    private static k<Ratings, PlacesRatings> a;
    private static am<Ratings, PlacesRatings> b;
    @SerializedName("average")
    private Double m_average;
    @SerializedName("count")
    private Integer m_count;

    public static void a(k<Ratings, PlacesRatings> kVar, am<Ratings, PlacesRatings> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesRatings a(Ratings ratings) {
        return (PlacesRatings) a.a(ratings);
    }

    static Ratings a(PlacesRatings placesRatings) {
        if (placesRatings != null) {
            return (Ratings) b.a(placesRatings);
        }
        return null;
    }

    static {
        ce.a(Ratings.class);
    }

    PlacesRatings(int i, double d) {
        this.m_count = Integer.valueOf(i);
        this.m_average = Double.valueOf(d);
    }

    public final double a() {
        return this.m_average.doubleValue();
    }

    public final int b() {
        return this.m_count.intValue();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_average == null ? 0 : this.m_average.hashCode()) + 31) * 31;
        if (this.m_count != null) {
            i = this.m_count.hashCode();
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
            obj = (PlacesRatings) obj;
        } else if (Ratings.class != obj.getClass()) {
            return false;
        } else {
            obj = a((Ratings) obj);
        }
        if (this.m_average == null) {
            if (obj.m_average != null) {
                return false;
            }
        } else if (!this.m_average.equals(obj.m_average)) {
            return false;
        }
        if (this.m_count == null) {
            if (obj.m_count != null) {
                return false;
            }
            return true;
        } else if (this.m_count.equals(obj.m_count)) {
            return true;
        } else {
            return false;
        }
    }
}
