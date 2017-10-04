package com.nokia.maps;

import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;
import java.util.List;

public class PlacesTextSuggestionRequest extends PlacesBaseRequest<List<String>> {
    @OnlineNative
    private PlacesTextSuggestionRequest(int i) {
        super(i);
        this.i = c.TEXT_SUGGESTIONS;
    }
}
