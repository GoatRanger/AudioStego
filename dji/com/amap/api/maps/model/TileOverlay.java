package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;

public final class TileOverlay {
    private ITileOverlayDelegate a;

    public TileOverlay(ITileOverlayDelegate iTileOverlayDelegate) {
        this.a = iTileOverlayDelegate;
    }

    public void remove() {
        this.a.remove();
    }

    public void clearTileCache() {
        this.a.clearTileCache();
    }

    public String getId() {
        return this.a.getId();
    }

    public void setZIndex(float f) {
        this.a.setZIndex(f);
    }

    public float getZIndex() {
        return this.a.getZIndex();
    }

    public void setVisible(boolean z) {
        this.a.setVisible(z);
    }

    public boolean isVisible() {
        return this.a.isVisible();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TileOverlay) {
            return this.a.equalsRemote(((TileOverlay) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCodeRemote();
    }
}
