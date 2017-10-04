package com.here.android.mpa.search;

import com.nokia.maps.PlacesAttribute;
import com.nokia.maps.annotation.Online;

@Online
public class TransitDeparturesAttribute extends ExtendedAttribute {
    TransitDeparturesAttribute(PlacesAttribute placesAttribute) {
        super(placesAttribute);
    }

    public TransitSchedulePage getSchedule() {
        return this.a.h();
    }
}
