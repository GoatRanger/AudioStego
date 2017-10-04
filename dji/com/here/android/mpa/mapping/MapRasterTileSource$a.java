package com.here.android.mpa.mapping;

enum MapRasterTileSource$a {
    CUSTOM(0),
    CONGESTION(1),
    FLEET_MAP(2),
    HISTORICAL_TRAFFIC(3),
    TRUCK_RESTRICTIONS(4);
    
    private int f;

    private MapRasterTileSource$a(int i) {
        this.f = i;
    }

    private int a() {
        return this.f;
    }
}
