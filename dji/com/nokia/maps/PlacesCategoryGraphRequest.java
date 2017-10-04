package com.nokia.maps;

import com.nokia.maps.PlacesCategoryGraph.CategoryGraphData;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesCategoryGraphRequest extends PlacesBaseRequest<CategoryGraphData> {
    @OnlineNative
    private PlacesCategoryGraphRequest(int i) {
        super(i);
        this.i = c.CATEGORY_GRAPH;
    }
}
