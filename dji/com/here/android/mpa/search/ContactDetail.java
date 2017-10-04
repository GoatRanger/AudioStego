package com.here.android.mpa.search;

import com.nokia.maps.PlacesContactDetail;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class ContactDetail {
    private PlacesContactDetail a;

    private ContactDetail(PlacesContactDetail placesContactDetail) {
        this.a = placesContactDetail;
    }

    public String getType() {
        return this.a.a();
    }

    public String getLabel() {
        return this.a.b();
    }

    public String getValue() {
        return this.a.c();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        PlacesContactDetail.a(new k<ContactDetail, PlacesContactDetail>() {
            public PlacesContactDetail a(ContactDetail contactDetail) {
                return contactDetail.a;
            }
        }, new am<ContactDetail, PlacesContactDetail>() {
            public ContactDetail a(PlacesContactDetail placesContactDetail) {
                return placesContactDetail != null ? new ContactDetail(placesContactDetail) : null;
            }
        });
    }
}
