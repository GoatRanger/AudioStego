package com.nokia.maps;

import com.here.android.mpa.search.RichTextFormatting;
import com.nokia.maps.annotation.Internal;

public abstract class dd {
    static final b a = b.NONE;
    static final RichTextFormatting b = RichTextFormatting.HTML;

    @Internal
    public enum a {
        OFFLINE,
        ONLINE,
        HYBRID;

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    @Internal
    public enum b {
        NONE,
        WALK,
        DRIVE
    }

    @Internal
    public enum c {
        BROWSE_BY_CORRIDOR,
        BROWSE_BY_CORRIDOR_SHORT,
        BROWSE_BY_JUNCTIONS,
        CATEGORY_GRAPH,
        DISCOVER,
        DISCOVER_AROUND,
        DISCOVER_EXPLORE,
        DISCOVER_HERE,
        DISCOVER_SEARCH,
        GEOCODE,
        k,
        MEDIA_EDITORIAL_COLLECTION_PAGE,
        MEDIA_IMAGE_COLLECTION_PAGE,
        MEDIA_RATING_COLLECTION_PAGE,
        MEDIA_REVIEW_COLLECTION_PAGE,
        p,
        PLACES_LOOKUP,
        REVERSE_GEOCODE,
        TEXT_SUGGESTIONS,
        TEXT_AUTOSUGGESTIONS,
        TILES,
        TRANSIT_SCHEDULE_PAGE,
        UNKNOWN;

        public static c[] a() {
            return (c[]) x.clone();
        }
    }
}
