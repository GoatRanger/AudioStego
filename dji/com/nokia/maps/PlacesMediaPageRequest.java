package com.nokia.maps;

import com.here.android.mpa.search.Media.Type;
import com.here.android.mpa.search.MediaCollectionPage;
import com.here.android.mpa.search.MediaCollectionPageRequest;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesMediaPageRequest<T> extends PlacesBaseRequest<MediaCollectionPage<T>> {
    private static k<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>> j;
    private static am<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>> k;

    public static void a(k<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>> kVar, am<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>> amVar) {
        j = kVar;
        k = amVar;
    }

    static MediaCollectionPageRequest<?> a(PlacesMediaPageRequest<?> placesMediaPageRequest) {
        if (placesMediaPageRequest != null) {
            return (MediaCollectionPageRequest) k.a(placesMediaPageRequest);
        }
        return null;
    }

    static {
        ce.a(MediaCollectionPageRequest.class);
    }

    void a(Type type) {
        switch (type) {
            case EDITORIAL:
                this.i = c.MEDIA_EDITORIAL_COLLECTION_PAGE;
                return;
            case IMAGE:
                this.i = c.MEDIA_IMAGE_COLLECTION_PAGE;
                return;
            case RATING:
                this.i = c.MEDIA_RATING_COLLECTION_PAGE;
                return;
            case REVIEW:
                this.i = c.MEDIA_REVIEW_COLLECTION_PAGE;
                return;
            default:
                this.i = c.UNKNOWN;
                return;
        }
    }

    @OnlineNative
    private PlacesMediaPageRequest(int i) {
        super(i);
    }
}
