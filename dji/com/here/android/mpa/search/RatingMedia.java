package com.here.android.mpa.search;

import com.nokia.maps.PlacesMedia;
import com.nokia.maps.annotation.Online;

public class RatingMedia extends Media {
    RatingMedia(PlacesMedia<?> placesMedia) {
        super(placesMedia);
    }

    @Online
    public double getAverage() {
        return this.a.b();
    }

    @Online
    public int getCount() {
        return this.a.c();
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
}
