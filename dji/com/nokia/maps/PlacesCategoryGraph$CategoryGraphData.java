package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PlacesCategoryGraph$CategoryGraphData {
    final /* synthetic */ PlacesCategoryGraph a;
    @SerializedName("items")
    private List<PlacesCategory> m_items;
    @SerializedName("locale")
    private String m_locale;

    public PlacesCategoryGraph$CategoryGraphData(PlacesCategoryGraph placesCategoryGraph) {
        this.a = placesCategoryGraph;
        this.m_locale = "";
        this.m_items = new ArrayList();
        this.m_locale = "";
    }

    public List<PlacesCategory> a() {
        return this.m_items;
    }

    public String b() {
        return this.m_locale;
    }
}
