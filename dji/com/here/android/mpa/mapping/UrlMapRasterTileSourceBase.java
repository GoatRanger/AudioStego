package com.here.android.mpa.mapping;

import com.here.android.mpa.mapping.MapRasterTileSource.TileResult.Error;
import com.nokia.maps.annotation.Online;

@Online
public abstract class UrlMapRasterTileSourceBase extends MapRasterTileSource {
    public abstract String getUrl(int i, int i2, int i3);

    protected UrlMapRasterTileSourceBase() {
        this(MapRasterTileSource$a.CUSTOM);
    }

    UrlMapRasterTileSourceBase(MapRasterTileSource$a mapRasterTileSource$a) {
        super(mapRasterTileSource$a);
        this.m_impl.c();
    }

    public final boolean hasTile(int i, int i2, int i3) {
        return false;
    }

    public final MapRasterTileSource$TileResult getTileWithError(int i, int i2, int i3) {
        return new MapRasterTileSource$TileResult(Error.NONE, null);
    }
}
