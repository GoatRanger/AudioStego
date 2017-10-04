package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.ContactDetail;

public final class PlacesContactDetail {
    private static k<ContactDetail, PlacesContactDetail> b;
    private static am<ContactDetail, PlacesContactDetail> c;
    private String a;
    @SerializedName("label")
    private String m_label;
    @SerializedName("value")
    private String m_value;

    public static void a(k<ContactDetail, PlacesContactDetail> kVar, am<ContactDetail, PlacesContactDetail> amVar) {
        b = kVar;
        c = amVar;
    }

    static PlacesContactDetail a(ContactDetail contactDetail) {
        return (PlacesContactDetail) b.a(contactDetail);
    }

    static ContactDetail a(PlacesContactDetail placesContactDetail) {
        if (placesContactDetail != null) {
            return (ContactDetail) c.a(placesContactDetail);
        }
        return null;
    }

    static {
        ce.a(ContactDetail.class);
    }

    public final String a() {
        return em.a(this.a);
    }

    void a(String str) {
        this.a = str;
    }

    public final String b() {
        return em.a(this.m_label);
    }

    public final String c() {
        return em.a(this.m_value);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.a == null ? 0 : this.a.hashCode()) + (((this.m_label == null ? 0 : this.m_label.hashCode()) + 31) * 31)) * 31;
        if (this.m_value != null) {
            i = this.m_value.hashCode();
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
            obj = (PlacesContactDetail) obj;
        } else if (ContactDetail.class != obj.getClass()) {
            return false;
        } else {
            obj = a((ContactDetail) obj);
        }
        if (this.m_label == null) {
            if (!TextUtils.isEmpty(obj.m_label)) {
                return false;
            }
        } else if (!this.m_label.equals(obj.m_label)) {
            return false;
        }
        if (this.a == null) {
            if (obj.a != null) {
                return false;
            }
        } else if (!this.a.equals(obj.a)) {
            return false;
        }
        if (this.m_value == null) {
            if (TextUtils.isEmpty(obj.m_value)) {
                return true;
            }
            return false;
        } else if (this.m_value.equals(obj.m_value)) {
            return true;
        } else {
            return false;
        }
    }
}
