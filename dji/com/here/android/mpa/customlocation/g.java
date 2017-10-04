package com.here.android.mpa.customlocation;

import com.nokia.maps.MapsEngine;

enum g {
    PROXIMITY {
        String b() {
            return g.i + "proximity?";
        }
    },
    BOUNDING_BOX {
        String b() {
            return g.i + "bbox?";
        }
    },
    CORRIDOR {
        String b() {
            return g.i + "corridor?";
        }
    },
    ROUTE {
        String b() {
            return g.i + "route/corridor?";
        }
    },
    CUSTOM_ATTRIBUTE {
        String b() {
            return g.i + "attribute?";
        }
    },
    GEOMETRY_BOUNDING_BOX {
        String b() {
            return g.i + "feature/bbox?";
        }
    },
    GEOMETRY_POINT {
        String b() {
            return g.i + "feature/point?";
        }
    },
    GEOMETRY_ID {
        String b() {
            return g.i + "feature/id?";
        }
    };
    
    private static final String i = null;

    abstract String b();

    public static g[] a() {
        return (g[]) j.clone();
    }

    static {
        i = MapsEngine.l();
    }
}
