package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.Media;
import com.here.android.mpa.search.Media.Type;
import com.here.android.mpa.search.MediaCollectionPage;
import com.here.android.mpa.search.MediaCollectionPageRequest;
import java.util.ArrayList;
import java.util.List;

public class PlacesMediaCollectionPage<T> {
    private static k<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>> b;
    private static am<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>> c;
    protected Type a = Type.UNKNOWN;
    @SerializedName("available")
    protected int m_available = 0;
    @SerializedName("create")
    protected PlacesCreateLink m_create;
    @SerializedName("items")
    protected List<PlacesMedia<T>> m_items = new ArrayList();
    @SerializedName("next")
    protected String m_next;
    @SerializedName("offset")
    protected int m_offset = 0;
    @SerializedName("previous")
    protected String m_previous;

    public static void a(k<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>> kVar, am<MediaCollectionPage<?>, PlacesMediaCollectionPage<?>> amVar) {
        b = kVar;
        c = amVar;
    }

    static PlacesMediaCollectionPage<?> a(MediaCollectionPage<?> mediaCollectionPage) {
        return (PlacesMediaCollectionPage) b.a(mediaCollectionPage);
    }

    static MediaCollectionPage<?> a(PlacesMediaCollectionPage<?> placesMediaCollectionPage) {
        if (placesMediaCollectionPage != null) {
            return (MediaCollectionPage) c.a(placesMediaCollectionPage);
        }
        return null;
    }

    static {
        ce.a(MediaCollectionPage.class);
    }

    protected PlacesMediaCollectionPage(Type type) {
        this.a = type;
    }

    public final Type a() {
        return this.a;
    }

    public final int b() {
        return this.m_available;
    }

    public final int c() {
        return this.m_offset;
    }

    public final MediaCollectionPageRequest<T> d() {
        return PlacesApi.a().a(this.m_next, this.a);
    }

    public final List<Media> e() {
        List<Media> arrayList = new ArrayList();
        synchronized (this.m_items) {
            for (PlacesMedia placesMedia : this.m_items) {
                placesMedia.a(this.a);
                arrayList.add(PlacesMedia.a(placesMedia));
            }
        }
        return arrayList;
    }

    public final boolean a(Media media) {
        boolean remove;
        synchronized (this.m_items) {
            remove = this.m_items.remove(PlacesMedia.a(media));
        }
        return remove;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.m_next == null ? 0 : this.m_next.hashCode()) + (((this.a == null ? 0 : this.a.hashCode()) + (((this.m_items == null ? 0 : this.m_items.hashCode()) + (((this.m_create == null ? 0 : this.m_create.hashCode()) + ((this.m_available + 31) * 31)) * 31)) * 31)) * 31)) * 31) + this.m_offset) * 31;
        if (this.m_previous != null) {
            i = this.m_previous.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesMediaCollectionPage) obj;
        } else if (MediaCollectionPage.class != obj.getClass()) {
            return false;
        } else {
            obj = a((MediaCollectionPage) obj);
        }
        if (this.m_available != obj.m_available) {
            return false;
        }
        if (this.m_create == null) {
            if (obj.m_create != null) {
                return false;
            }
        } else if (!this.m_create.equals(obj.m_create)) {
            return false;
        }
        if (this.m_items == null) {
            if (!(obj.m_items == null || obj.m_items.isEmpty())) {
                return false;
            }
        } else if (obj.m_items == null) {
            if (!this.m_items.isEmpty()) {
                return false;
            }
        } else if (!this.m_items.equals(obj.m_items)) {
            return false;
        }
        if (this.a != obj.a) {
            return false;
        }
        if (this.m_next == null) {
            if (obj.m_next != null) {
                return false;
            }
        } else if (!this.m_next.equals(obj.m_next)) {
            return false;
        }
        if (this.m_offset != obj.m_offset) {
            return false;
        }
        if (this.m_previous == null) {
            if (obj.m_previous != null) {
                return false;
            }
            return true;
        } else if (this.m_previous.equals(obj.m_previous)) {
            return true;
        } else {
            return false;
        }
    }
}
