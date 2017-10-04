package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

class PlacesReference {
    @SerializedName("alternatives")
    private List<PlacesReference> m_alternativeIds = new ArrayList();
    @SerializedName("id")
    private String m_id;

    PlacesReference() {
    }

    public String a() {
        return em.a(this.m_id);
    }

    public List<String> b() {
        List<String> arrayList = new ArrayList();
        if (this.m_alternativeIds != null) {
            for (PlacesReference placesReference : this.m_alternativeIds) {
                arrayList.add(placesReference.m_id);
            }
        }
        return arrayList;
    }

    public int hashCode() {
        int hashCode = (this.m_id == null ? 0 : this.m_id.hashCode()) + 31;
        if (this.m_alternativeIds == null) {
            return hashCode;
        }
        int i = hashCode;
        for (PlacesReference placesReference : this.m_alternativeIds) {
            i = (placesReference.m_id == null ? 0 : placesReference.m_id.hashCode()) + (i * 31);
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlacesReference placesReference = (PlacesReference) obj;
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(placesReference.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(placesReference.m_id)) {
            return false;
        }
        if (this.m_alternativeIds == null) {
            if (placesReference.b() != null) {
                return false;
            }
        } else if (this.m_alternativeIds.size() != placesReference.b().size()) {
            return false;
        } else {
            for (int i = 0; i < this.m_alternativeIds.size(); i++) {
                if (((PlacesReference) this.m_alternativeIds.get(i)).m_id.compareTo((String) placesReference.b().get(i)) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
