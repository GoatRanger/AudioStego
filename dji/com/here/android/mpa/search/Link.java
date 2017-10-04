package com.here.android.mpa.search;

import com.nokia.maps.PlacesLink;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class Link {
    protected PlacesLink a;

    protected Link(PlacesLink placesLink) {
        this.a = placesLink;
    }

    public String getId() {
        return this.a.d();
    }

    public String getTitle() {
        return this.a.c();
    }

    public String getIconUrl() {
        return this.a.e();
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
        PlacesLink.a(new k<Link, PlacesLink>() {
            public PlacesLink a(Link link) {
                return link.a;
            }
        }, new am<c, PlacesLink>() {
            public c a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new c(placesLink);
            }
        }, new am<DiscoveryLink, PlacesLink>() {
            public DiscoveryLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new DiscoveryLink(placesLink);
            }
        }, new am<PlaceLink, PlacesLink>() {
            public PlaceLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new PlaceLink(placesLink);
            }
        }, new am<ReportingLink, PlacesLink>() {
            public ReportingLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new ReportingLink(placesLink);
            }
        }, new am<SupplierLink, PlacesLink>() {
            public SupplierLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new SupplierLink(placesLink);
            }
        }, new am<UserLink, PlacesLink>() {
            public UserLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new UserLink(placesLink);
            }
        }, new am<ViaLink, PlacesLink>() {
            public ViaLink a(PlacesLink placesLink) {
                if (placesLink == null) {
                    return null;
                }
                return new ViaLink(placesLink);
            }
        });
    }
}
