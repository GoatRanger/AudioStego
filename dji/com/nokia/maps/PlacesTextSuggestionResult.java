package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

class PlacesTextSuggestionResult {
    @SerializedName("suggestions")
    private List<String> m_suggestions = new ArrayList();

    public final List<String> a() {
        return this.m_suggestions != null ? this.m_suggestions : new ArrayList();
    }

    public int hashCode() {
        return (this.m_suggestions == null ? 0 : this.m_suggestions.hashCode()) + 31;
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
        PlacesTextSuggestionResult placesTextSuggestionResult = (PlacesTextSuggestionResult) obj;
        if (this.m_suggestions == null) {
            if (placesTextSuggestionResult.m_suggestions != null) {
                return false;
            }
            return true;
        } else if (this.m_suggestions.equals(placesTextSuggestionResult.m_suggestions)) {
            return true;
        } else {
            return false;
        }
    }
}
