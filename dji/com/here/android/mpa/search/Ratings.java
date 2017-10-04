package com.here.android.mpa.search;

import com.nokia.maps.PlacesRatings;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

public class Ratings {
    private PlacesRatings a;

    private Ratings(PlacesRatings placesRatings) {
        this.a = placesRatings;
    }

    @Online
    public double getAverage() {
        return this.a.a();
    }

    @Online
    public int getCount() {
        return this.a.b();
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
        PlacesRatings.a(new k<Ratings, PlacesRatings>() {
            public PlacesRatings a(Ratings ratings) {
                return ratings != null ? ratings.a : null;
            }
        }, new am<Ratings, PlacesRatings>() {
            public Ratings a(PlacesRatings placesRatings) {
                return placesRatings != null ? new Ratings(placesRatings) : null;
            }
        });
    }
}
