package com.here.android.mpa.search;

import com.nokia.maps.PlacesLink;
import com.nokia.maps.annotation.Internal;

public class c extends Link {
    c(PlacesLink placesLink) {
        super(placesLink);
    }

    @Internal
    public String getTitle() {
        return this.a.c();
    }

    @Internal
    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    @Internal
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
