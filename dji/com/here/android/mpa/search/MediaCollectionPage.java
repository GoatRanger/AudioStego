package com.here.android.mpa.search;

import com.here.android.mpa.search.Media.Type;
import com.nokia.maps.PlacesMediaCollectionPage;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public final class MediaCollectionPage<T> {
    private PlacesMediaCollectionPage<T> a;

    private MediaCollectionPage(PlacesMediaCollectionPage<T> placesMediaCollectionPage) {
        this.a = placesMediaCollectionPage;
    }

    public Type getType() {
        return this.a.a();
    }

    public int getAvailable() {
        return this.a.b();
    }

    public int getOffsetCount() {
        return this.a.c();
    }

    public MediaCollectionPageRequest<T> getNextPageRequest() {
        return this.a.d();
    }

    public List<Media> getItems() {
        return this.a.e();
    }

    public boolean removeItem(Media media) {
        return this.a.a(media);
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

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
        PlacesMediaCollectionPage.a(new k<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>>() {
            public PlacesMediaCollectionPage<?> a(MediaCollectionPage<?> mediaCollectionPage) {
                return mediaCollectionPage != null ? mediaCollectionPage.a : null;
            }
        }, new am<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>>() {
            public MediaCollectionPage<?> a(PlacesMediaCollectionPage<?> placesMediaCollectionPage) {
                return placesMediaCollectionPage != null ? new MediaCollectionPage(placesMediaCollectionPage) : null;
            }
        });
    }
}
