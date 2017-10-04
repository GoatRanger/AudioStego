package com.here.android.mpa.search;

import com.nokia.maps.PlacesAutoSuggest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public abstract class AutoSuggest {
    protected PlacesAutoSuggest m_pimpl;

    public enum a {
        UNKNOWN,
        b,
        SEARCH
    }

    protected AutoSuggest(PlacesAutoSuggest placesAutoSuggest) {
        this.m_pimpl = placesAutoSuggest;
    }

    public String getTitle() {
        return this.m_pimpl.a();
    }

    public String getHighlightedTitle() {
        return this.m_pimpl.b();
    }

    public a getType() {
        return this.m_pimpl.i();
    }

    public int hashCode() {
        return (this.m_pimpl == null ? 0 : this.m_pimpl.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.m_pimpl.equals(obj);
    }

    static {
        PlacesAutoSuggest.a(new k<AutoSuggest, PlacesAutoSuggest>() {
            public PlacesAutoSuggest a(AutoSuggest autoSuggest) {
                return autoSuggest.m_pimpl;
            }
        }, new am<b, PlacesAutoSuggest>() {
            public b a(PlacesAutoSuggest placesAutoSuggest) {
                if (placesAutoSuggest == null) {
                    return null;
                }
                return new b(placesAutoSuggest);
            }
        }, new am<AutoSuggestSearch, PlacesAutoSuggest>() {
            public AutoSuggestSearch a(PlacesAutoSuggest placesAutoSuggest) {
                if (placesAutoSuggest == null) {
                    return null;
                }
                return new AutoSuggestSearch(placesAutoSuggest);
            }
        }, new am<AutoSuggestPlace, PlacesAutoSuggest>() {
            public AutoSuggestPlace a(PlacesAutoSuggest placesAutoSuggest) {
                if (placesAutoSuggest == null) {
                    return null;
                }
                return new AutoSuggestPlace(placesAutoSuggest);
            }
        });
    }

    public static void set(k<AutoSuggest, PlacesAutoSuggest> kVar, am<AutoSuggestPlace, PlacesAutoSuggest> amVar, am<AutoSuggestSearch, PlacesAutoSuggest> amVar2) {
    }
}
