package com.here.android.mpa.search;

import com.nokia.maps.PlacesAttribute;
import com.nokia.maps.annotation.Online;
import java.util.List;
import java.util.Map;

@Online
public class TransitLinesAttribute extends ExtendedAttribute {
    TransitLinesAttribute(PlacesAttribute placesAttribute) {
        super(placesAttribute);
    }

    public Map<String, TransitLine> getLines() {
        return this.a.f();
    }

    public List<TransitDestination> getDestinations() {
        return this.a.g();
    }
}
