package com.here.android.mpa.search;

import com.nokia.maps.PlacesMedia;
import com.nokia.maps.annotation.Online;

public class ReviewMedia extends Media {
    ReviewMedia(PlacesMedia<?> placesMedia) {
        super(placesMedia);
    }

    @Online
    public String getDate() {
        return this.a.i();
    }

    @Online
    public String getTitle() {
        return this.a.m();
    }

    @Online
    public double getRating() {
        return this.a.l();
    }

    @Online
    public String getDescription() {
        return this.a.j();
    }

    @Online
    public String getIsoLanguageCode() {
        return this.a.k();
    }

    @Online
    public String getId() {
        return this.a.g();
    }

    @Online
    public UserLink getUser() {
        return this.a.h();
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
