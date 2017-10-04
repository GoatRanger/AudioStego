package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.e;
import java.util.ArrayList;
import java.util.List;

public class PlacesTilesLink {
    private static k<e, PlacesTilesLink> a;
    private static am<e, PlacesTilesLink> b;
    @SerializedName("tiles")
    private List<String> m_tiles = new ArrayList();

    public static void a(k<e, PlacesTilesLink> kVar, am<e, PlacesTilesLink> amVar) {
        a = kVar;
        b = amVar;
    }

    static e a(PlacesTilesLink placesTilesLink) {
        if (placesTilesLink != null) {
            return (e) b.a(placesTilesLink);
        }
        return null;
    }

    static {
        ce.a(e.class);
    }
}
