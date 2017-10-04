package com.here.android.mpa.routing;

import com.nokia.maps.RouteOptionsImpl;
import com.nokia.maps.a.an;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public class UMRouteOptions extends RouteOptions {
    private an b;

    public UMRouteOptions() {
        this.b = new an();
        this.a = this.b;
    }

    public UMRouteOptions(RouteOptions routeOptions) {
        this.b = new an(routeOptions);
        this.a = this.b;
    }

    public UMRouteOptions(UMRouteOptions uMRouteOptions) {
        this.b = new an(uMRouteOptions);
        this.a = this.b;
    }

    private UMRouteOptions(an anVar) {
        super((RouteOptionsImpl) anVar);
        this.b = anVar;
    }

    public UMRouteOptions setTransitWalkMaxDistance(int i) {
        this.b.a(i);
        return this;
    }

    public int getTransitWalkMaxDistance() {
        return this.b.l();
    }

    public boolean isStrictRouteCountEnabled() {
        return this.b.m();
    }

    public UMRouteOptions setStrictRouteCountEnabled(boolean z) {
        this.b.b(z);
        return this;
    }

    static {
        an.b(new k<UMRouteOptions, an>() {
            public an a(UMRouteOptions uMRouteOptions) {
                return uMRouteOptions.b;
            }
        }, new am<UMRouteOptions, an>() {
            public UMRouteOptions a(an anVar) {
                return anVar != null ? new UMRouteOptions(anVar) : null;
            }
        });
    }

    public int hashCode() {
        return (this.b == null ? 0 : this.b.hashCode()) + 31;
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
        UMRouteOptions uMRouteOptions = (UMRouteOptions) obj;
        if (this.b == null) {
            if (uMRouteOptions.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(uMRouteOptions.b)) {
            return true;
        } else {
            return false;
        }
    }
}
