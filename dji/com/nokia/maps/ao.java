package com.nokia.maps;

public final class ao {
    private PlacesDiscoveryContext a;

    private ao(PlacesDiscoveryContext placesDiscoveryContext) {
        this.a = placesDiscoveryContext;
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
        PlacesDiscoveryContext.a(new am<ao, PlacesDiscoveryContext>() {
            public ao a(PlacesDiscoveryContext placesDiscoveryContext) {
                return new ao(placesDiscoveryContext);
            }
        });
    }
}
