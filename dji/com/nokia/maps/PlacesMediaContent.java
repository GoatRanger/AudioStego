package com.nokia.maps;

import com.google.gson.annotations.SerializedName;

public final class PlacesMediaContent {
    @SerializedName("editorials")
    private dh m_editorials;
    @SerializedName("images")
    private dl m_images;
    @SerializedName("links")
    private PlacesMediaCollectionPage<PlacesLink> m_links;
    @SerializedName("ratings")
    private dn m_ratings;
    @SerializedName("reviews")
    private dr m_reviews;

    public final dh a() {
        return this.m_editorials;
    }

    public final dl b() {
        return this.m_images;
    }

    public final dn c() {
        return this.m_ratings;
    }

    public final dr d() {
        return this.m_reviews;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_ratings == null ? 0 : this.m_ratings.hashCode()) + (((this.m_links == null ? 0 : this.m_links.hashCode()) + (((this.m_images == null ? 0 : this.m_images.hashCode()) + (((this.m_editorials == null ? 0 : this.m_editorials.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_reviews != null) {
            i = this.m_reviews.hashCode();
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlacesMediaContent placesMediaContent = (PlacesMediaContent) obj;
        if (this.m_editorials == null) {
            if (placesMediaContent.m_editorials != null) {
                return false;
            }
        } else if (!this.m_editorials.equals(placesMediaContent.m_editorials)) {
            return false;
        }
        if (this.m_images == null) {
            if (placesMediaContent.m_images != null) {
                return false;
            }
        } else if (!this.m_images.equals(placesMediaContent.m_images)) {
            return false;
        }
        if (this.m_links == null) {
            if (placesMediaContent.m_links != null) {
                return false;
            }
        } else if (!this.m_links.equals(placesMediaContent.m_links)) {
            return false;
        }
        if (this.m_ratings == null) {
            if (placesMediaContent.m_ratings != null) {
                return false;
            }
        } else if (!this.m_ratings.equals(placesMediaContent.m_ratings)) {
            return false;
        }
        if (this.m_reviews == null) {
            if (placesMediaContent.m_reviews != null) {
                return false;
            }
            return true;
        } else if (this.m_reviews.equals(placesMediaContent.m_reviews)) {
            return true;
        } else {
            return false;
        }
    }
}
