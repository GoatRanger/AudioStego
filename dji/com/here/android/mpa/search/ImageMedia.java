package com.here.android.mpa.search;

import com.nokia.maps.PlacesMedia;
import com.nokia.maps.annotation.Online;

public final class ImageMedia extends Media {
    ImageMedia(PlacesMedia<?> placesMedia) {
        super(placesMedia);
    }

    @Online
    public String getUrl() {
        return this.a.f();
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
    public final String getDimensionHref(int i, int i2) {
        return this.a.a(i, i2);
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
