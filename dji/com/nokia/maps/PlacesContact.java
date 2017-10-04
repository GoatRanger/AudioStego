package com.nokia.maps;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public final class PlacesContact {
    @SerializedName("email")
    private List<PlacesContactDetail> m_email = new ArrayList();
    @SerializedName("fax")
    private List<PlacesContactDetail> m_fax = new ArrayList();
    @SerializedName("phone")
    private List<PlacesContactDetail> m_phone = new ArrayList();
    @SerializedName("website")
    private List<PlacesContactDetail> m_website = new ArrayList();

    public final List<PlacesContactDetail> a() {
        return this.m_email;
    }

    public final List<PlacesContactDetail> b() {
        return this.m_fax;
    }

    public final List<PlacesContactDetail> c() {
        return this.m_phone;
    }

    public final List<PlacesContactDetail> d() {
        return this.m_website;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_phone == null ? 0 : this.m_phone.hashCode()) + (((this.m_fax == null ? 0 : this.m_fax.hashCode()) + (((this.m_email == null ? 0 : this.m_email.hashCode()) + 31) * 31)) * 31)) * 31;
        if (this.m_website != null) {
            i = this.m_website.hashCode();
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
        PlacesContact placesContact = (PlacesContact) obj;
        if (this.m_email == null) {
            if (placesContact.m_email != null) {
                return false;
            }
        } else if (!this.m_email.equals(placesContact.m_email)) {
            return false;
        }
        if (this.m_fax == null) {
            if (placesContact.m_fax != null) {
                return false;
            }
        } else if (!this.m_fax.equals(placesContact.m_fax)) {
            return false;
        }
        if (this.m_phone == null) {
            if (placesContact.m_phone != null) {
                return false;
            }
        } else if (!this.m_phone.equals(placesContact.m_phone)) {
            return false;
        }
        if (this.m_website == null) {
            if (placesContact.m_website != null) {
                return false;
            }
            return true;
        } else if (this.m_website.equals(placesContact.m_website)) {
            return true;
        } else {
            return false;
        }
    }
}
