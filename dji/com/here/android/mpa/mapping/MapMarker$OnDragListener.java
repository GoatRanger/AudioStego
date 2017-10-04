package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;

@Online
public interface MapMarker$OnDragListener {
    void onMarkerDrag(MapMarker mapMarker);

    void onMarkerDragEnd(MapMarker mapMarker);

    void onMarkerDragStart(MapMarker mapMarker);
}
