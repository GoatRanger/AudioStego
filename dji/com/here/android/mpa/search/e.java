package com.here.android.mpa.search;

import com.nokia.maps.PlacesTilesLink;
import com.nokia.maps.am;
import com.nokia.maps.k;

public class e {
    private PlacesTilesLink a;

    private e(PlacesTilesLink placesTilesLink) {
        this.a = placesTilesLink;
    }

    static {
        PlacesTilesLink.a(new k<e, PlacesTilesLink>() {
            public PlacesTilesLink a(e eVar) {
                return eVar.a;
            }
        }, new am<e, PlacesTilesLink>() {
            public e a(PlacesTilesLink placesTilesLink) {
                return placesTilesLink != null ? new e(placesTilesLink) : null;
            }
        });
    }
}
