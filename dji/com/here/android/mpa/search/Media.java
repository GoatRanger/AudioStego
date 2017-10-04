package com.here.android.mpa.search;

import com.nokia.maps.PlacesMedia;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;

public abstract class Media {
    protected PlacesMedia<?> a;

    @OnlineNative
    public enum Type {
        UNKNOWN,
        EDITORIAL,
        IMAGE,
        RATING,
        REVIEW
    }

    protected Media(PlacesMedia<?> placesMedia) {
        this.a = placesMedia;
    }

    @Online
    public String getAttributionText() {
        return this.a.a();
    }

    @Online
    public SupplierLink getSupplier() {
        return this.a.d();
    }

    @Online
    public ViaLink getVia() {
        return this.a.e();
    }

    @Online
    public Type getType() {
        return this.a.n();
    }

    protected String getUrl() {
        return this.a.f();
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
        PlacesMedia.a(new k<Media, PlacesMedia<?>>() {
            public PlacesMedia<?> a(Media media) {
                return media != null ? media.a : null;
            }
        }, new am<Media, PlacesMedia<?>>() {
            public Media a(PlacesMedia<?> placesMedia) {
                if (placesMedia == null) {
                    return null;
                }
                switch (placesMedia.n()) {
                    case EDITORIAL:
                        return new EditorialMedia(placesMedia);
                    case IMAGE:
                        return new ImageMedia(placesMedia);
                    case RATING:
                        return new RatingMedia(placesMedia);
                    case REVIEW:
                        return new ReviewMedia(placesMedia);
                    default:
                        return null;
                }
            }
        });
    }
}
