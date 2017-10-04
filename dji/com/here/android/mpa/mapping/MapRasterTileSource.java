package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.MapRasterTileSourceImpl;
import com.nokia.maps.annotation.Online;
import dji.pilot.usercenter.protocol.d;

public abstract class MapRasterTileSource {
    protected MapRasterTileSourceImpl m_impl;

    @Online
    public abstract boolean hasTile(int i, int i2, int i3);

    @Online
    protected MapRasterTileSource() {
        this(a.a);
    }

    MapRasterTileSource(a aVar) {
        this.m_impl = new MapRasterTileSourceImpl(MapRasterTileSource.class.getName().replace(".", d.t), this, a.a(aVar));
    }

    @Online
    public MapRasterTileSource setOverlayType(MapOverlayType mapOverlayType) {
        this.m_impl.a(mapOverlayType);
        return this;
    }

    @Online
    public MapOverlayType getOverlayType() {
        return this.m_impl.a();
    }

    @Online
    public MapRasterTileSource setTransparency(Transparency transparency) {
        this.m_impl.a(transparency);
        return this;
    }

    @Online
    public boolean hasTransparency() {
        return this.m_impl.hasTransparency();
    }

    @Online
    public MapRasterTileSource setTileSize(int i) {
        this.m_impl.a(i);
        return this;
    }

    @Online
    public int getTileSize() {
        return this.m_impl.getTileSize();
    }

    @Online
    public MapRasterTileSource setBoundingArea(GeoBoundingBox geoBoundingBox) {
        this.m_impl.a(geoBoundingBox);
        return this;
    }

    @Online
    public GeoBoundingBox getBoundingArea() {
        return this.m_impl.b();
    }

    @Online
    public MapRasterTileSource hideAtZoomLevel(int i) {
        this.m_impl.b(i);
        return this;
    }

    @Online
    public MapRasterTileSource showAtZoomLevel(int i) {
        this.m_impl.c(i);
        return this;
    }

    @Online
    public MapRasterTileSource hideAtZoomRange(int i, int i2) {
        this.m_impl.a(i, i2);
        return this;
    }

    @Online
    public MapRasterTileSource showAtZoomRange(int i, int i2) {
        this.m_impl.b(i, i2);
        return this;
    }

    @Online
    public boolean isShownAtZoomLevel(int i) {
        return this.m_impl.d(i);
    }

    @Online
    public MapRasterTileSource setCachingEnabled(boolean z) {
        this.m_impl.setCached(z);
        return this;
    }

    @Online
    public boolean isCachingEnabled() {
        return this.m_impl.getCached();
    }

    @Online
    public MapRasterTileSource setCachePrefix(String str) {
        this.m_impl.setCachePrefix(str);
        return this;
    }

    @Online
    public MapRasterTileSource setCacheExpiration(int i) {
        this.m_impl.e(i);
        return this;
    }

    @Online
    public int getCacheExpiration() {
        return this.m_impl.getCacheExpiration();
    }

    @Online
    public int getZIndex() {
        return this.m_impl.getZIndex();
    }

    @Online
    public MapRasterTileSource setZIndex(int i) {
        this.m_impl.f(i);
        return this;
    }

    @Online
    @Deprecated
    public byte[] getTile(int i, int i2, int i3) {
        return null;
    }

    @Online
    public TileResult getTileWithError(int i, int i2, int i3) {
        byte[] tile = getTile(i, i2, i3);
        return new TileResult(tile != null ? Error.NONE : Error.NOT_FOUND, tile);
    }

    static {
        MapRasterTileSourceImpl.a(new 1());
    }
}
