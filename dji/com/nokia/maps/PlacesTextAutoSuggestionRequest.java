package com.nokia.maps;

import com.here.android.mpa.search.AutoSuggest;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;
import java.util.List;

public class PlacesTextAutoSuggestionRequest extends PlacesBaseRequest<List<AutoSuggest>> {
    @OnlineNative
    private PlacesTextAutoSuggestionRequest(int i) {
        super(i);
        this.i = c.TEXT_AUTOSUGGESTIONS;
    }
}
