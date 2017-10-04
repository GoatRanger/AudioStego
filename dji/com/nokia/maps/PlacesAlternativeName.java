package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public final class PlacesAlternativeName {
    @SerializedName("language")
    private String m_language;
    @SerializedName("name")
    private String m_name;

    public final String a() {
        return em.a(this.m_language);
    }

    public final String b() {
        return em.a(this.m_name);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_language == null ? 0 : this.m_language.hashCode()) + 31) * 31;
        if (this.m_name != null) {
            i = this.m_name.hashCode();
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
        PlacesAlternativeName placesAlternativeName = (PlacesAlternativeName) obj;
        if (this.m_language == null) {
            if (!TextUtils.isEmpty(placesAlternativeName.m_language)) {
                return false;
            }
        } else if (!this.m_language.equals(placesAlternativeName.m_language)) {
            return false;
        }
        if (this.m_name == null) {
            if (TextUtils.isEmpty(placesAlternativeName.m_name)) {
                return true;
            }
            return false;
        } else if (this.m_name.equals(placesAlternativeName.m_name)) {
            return true;
        } else {
            return false;
        }
    }
}
