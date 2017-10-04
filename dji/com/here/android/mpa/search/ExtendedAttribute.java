package com.here.android.mpa.search;

import com.nokia.maps.PlacesAttribute;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class ExtendedAttribute {
    public static final String TRANSIT_DEPARTURES_EXTENDED_ATTRIBUTE_ID = "departures";
    public static final String TRANSIT_LINES_EXTENDED_ATTRIBUTE_ID = "transitLines";
    protected PlacesAttribute a;

    protected ExtendedAttribute(PlacesAttribute placesAttribute) {
        this.a = placesAttribute;
    }

    public String getId() {
        return this.a.a();
    }

    public String getLabel() {
        return this.a.b();
    }

    public String getText() {
        return this.a.c();
    }

    public String getAttribution() {
        return this.a.e();
    }

    public Link getVia() {
        return this.a.d();
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
        PlacesAttribute.a(new k<ExtendedAttribute, PlacesAttribute>() {
            public PlacesAttribute a(ExtendedAttribute extendedAttribute) {
                return extendedAttribute != null ? extendedAttribute.a : null;
            }
        }, new am<ExtendedAttribute, PlacesAttribute>() {
            public ExtendedAttribute a(PlacesAttribute placesAttribute) {
                return placesAttribute != null ? new ExtendedAttribute(placesAttribute) : null;
            }
        }, new am<TransitLinesAttribute, PlacesAttribute>() {
            public TransitLinesAttribute a(PlacesAttribute placesAttribute) {
                return placesAttribute != null ? new TransitLinesAttribute(placesAttribute) : null;
            }
        }, new am<TransitDeparturesAttribute, PlacesAttribute>() {
            public TransitDeparturesAttribute a(PlacesAttribute placesAttribute) {
                return placesAttribute != null ? new TransitDeparturesAttribute(placesAttribute) : null;
            }
        });
    }
}
