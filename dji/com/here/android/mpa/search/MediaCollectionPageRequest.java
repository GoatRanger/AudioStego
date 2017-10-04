package com.here.android.mpa.search;

import com.nokia.maps.PlacesMediaPageRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

public class MediaCollectionPageRequest<T> extends Request<MediaCollectionPage<T>> {
    private PlacesMediaPageRequest<T> a;

    protected MediaCollectionPageRequest(PlacesMediaPageRequest<T> placesMediaPageRequest) {
        super(placesMediaPageRequest);
        this.a = placesMediaPageRequest;
    }

    @Online
    public ErrorCode execute(ResultListener<MediaCollectionPage<T>> resultListener) {
        return super.execute(resultListener);
    }

    static {
        PlacesMediaPageRequest.a(new k<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>>() {
            public PlacesMediaPageRequest<?> a(MediaCollectionPageRequest<?> mediaCollectionPageRequest) {
                return mediaCollectionPageRequest.a;
            }
        }, new am<MediaCollectionPageRequest<?>, PlacesMediaPageRequest<?>>() {
            public MediaCollectionPageRequest<?> a(PlacesMediaPageRequest<?> placesMediaPageRequest) {
                return placesMediaPageRequest != null ? new MediaCollectionPageRequest(placesMediaPageRequest) : null;
            }
        });
    }
}
