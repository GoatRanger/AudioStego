package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.AutoSuggest;
import com.here.android.mpa.search.AutoSuggest.a;
import java.util.ArrayList;
import java.util.List;

class PlacesTextAutoSuggestionResult {
    @SerializedName("results")
    private List<PlacesAutoSuggest> m_autoSuggestions = new ArrayList();

    public final List<AutoSuggest> a() {
        List<AutoSuggest> arrayList = new ArrayList();
        bj.a("PlacesTextAutoSuggestionResult", "m_autoSuggestions size %d", Integer.valueOf(this.m_autoSuggestions.size()));
        if (!this.m_autoSuggestions.isEmpty()) {
            for (PlacesAutoSuggest placesAutoSuggest : this.m_autoSuggestions) {
                if (placesAutoSuggest.i() == a.b) {
                    arrayList.add(PlacesAutoSuggest.b(placesAutoSuggest));
                } else if (placesAutoSuggest.i() == a.c) {
                    arrayList.add(PlacesAutoSuggest.a(placesAutoSuggest));
                }
            }
        }
        return arrayList;
    }

    public int hashCode() {
        return (this.m_autoSuggestions == null ? 0 : this.m_autoSuggestions.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlacesTextAutoSuggestionResult placesTextAutoSuggestionResult = (PlacesTextAutoSuggestionResult) obj;
        if (this.m_autoSuggestions == null) {
            if (placesTextAutoSuggestionResult.m_autoSuggestions != null) {
                return false;
            }
            return true;
        } else if (this.m_autoSuggestions.equals(placesTextAutoSuggestionResult.m_autoSuggestions)) {
            return true;
        } else {
            return false;
        }
    }
}
