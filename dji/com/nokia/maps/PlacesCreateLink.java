package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PlacesCreateLink {
    @SerializedName("accept")
    private List<String> m_accept = new ArrayList();
    @SerializedName("href")
    private String m_href;
    @SerializedName("method")
    private String m_method;
    @SerializedName("title")
    private String m_title;
    @SerializedName("type")
    private String m_type;

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_method == null ? 0 : this.m_method.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + (((this.m_accept == null ? 0 : this.m_accept.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_type != null) {
            i = this.m_type.hashCode();
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
        PlacesCreateLink placesCreateLink = (PlacesCreateLink) obj;
        if (this.m_accept == null) {
            if (placesCreateLink.m_accept != null) {
                return false;
            }
        } else if (!this.m_accept.equals(placesCreateLink.m_accept)) {
            return false;
        }
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(placesCreateLink.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(placesCreateLink.m_href)) {
            return false;
        }
        if (this.m_method == null) {
            if (!TextUtils.isEmpty(placesCreateLink.m_method)) {
                return false;
            }
        } else if (!this.m_method.equals(placesCreateLink.m_method)) {
            return false;
        }
        if (this.m_title == null) {
            if (!TextUtils.isEmpty(placesCreateLink.m_title)) {
                return false;
            }
        } else if (!this.m_title.equals(placesCreateLink.m_title)) {
            return false;
        }
        if (this.m_type == null) {
            if (TextUtils.isEmpty(placesCreateLink.m_type)) {
                return true;
            }
            return false;
        } else if (this.m_type.equals(placesCreateLink.m_type)) {
            return true;
        } else {
            return false;
        }
    }
}
