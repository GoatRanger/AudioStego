package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;

@Online
public interface Map$OnTransformListener {
    void onMapTransformEnd(MapState mapState);

    void onMapTransformStart();
}
