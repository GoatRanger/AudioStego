package com.here.android.mpa.search;

import com.here.android.mpa.search.AutoSuggest.a;
import com.nokia.maps.PlacesAutoSuggest;
import com.nokia.maps.annotation.Internal;

public class b extends AutoSuggest {
    b(PlacesAutoSuggest placesAutoSuggest) {
        super(placesAutoSuggest);
    }

    @Internal
    public String getTitle() {
        return this.m_pimpl.a();
    }

    @Internal
    public String getHighlightedTitle() {
        return this.m_pimpl.b();
    }

    @Internal
    public a getType() {
        return this.m_pimpl.i();
    }

    @Internal
    public int hashCode() {
        return (this.m_pimpl == null ? 0 : this.m_pimpl.hashCode()) + 31;
    }

    @Internal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.m_pimpl.equals(obj);
    }
}
