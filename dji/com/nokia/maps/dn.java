package com.nokia.maps;

import com.here.android.mpa.search.Media.Type;
import com.here.android.mpa.search.RatingMedia;

class dn extends PlacesMediaCollectionPage<RatingMedia> {
    public dn() {
        super(Type.RATING);
    }
}
