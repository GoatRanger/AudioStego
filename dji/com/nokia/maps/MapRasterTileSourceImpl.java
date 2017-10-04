package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.mapping.MapOverlayType;
import com.here.android.mpa.mapping.MapRasterTileSource;
import com.here.android.mpa.mapping.MapRasterTileSource$TileResult;
import com.here.android.mpa.mapping.MapRasterTileSource$Transparency;
import com.here.android.mpa.mapping.MapRasterTileSource.TileResult.Error;
import com.here.android.mpa.mapping.UrlMapRasterTileSourceBase;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapRasterTileSourceImpl extends BaseNativeObject {
    private static final String a = MapRasterTileSourceImpl.class.getName();
    private static k<MapRasterTileSource, MapRasterTileSourceImpl> d;
    private MapRasterTileSource b;
    private boolean c = false;

    public enum a {
        PIXEL_FORMAT_UNKNOWN,
        PIXEL_FORMAT_RGBA,
        PIXEL_FORMAT_BGRA,
        PIXEL_FORMAT_RGB,
        PIXEL_FORMAT_R5G6B5,
        PIXEL_FORMAT_DISTANCE_FIELD_24
    }

    private native void createBaseNative(String str, int i);

    private native void destroyNative();

    private final native GeoBoundingBoxImpl getBoundingAreaNative();

    private final native MapOverlayType getOverlayTypeNative();

    private final native int getPixelFormatNative();

    private native void hideAtZoomLevelNative(int i);

    private native void hideAtZoomRangeNative(int i, int i2);

    private native boolean isShownAtZoomLevelNative(int i);

    private native void setBoundingAreaNative(GeoBoundingBoxImpl geoBoundingBoxImpl);

    private native void setCacheExpirationNative(int i);

    private native void setOverlayTypeNative(String str);

    private native void setPixelFormatNative(int i);

    private native void setTileMode();

    private native void setTileSizeNative(int i);

    private native void setTransparencyNative(int i);

    private native void setZIndexNative(int i);

    private native void showAtZoomLevelNative(int i);

    private native void showAtZoomRangeNative(int i, int i2);

    public native int getCacheExpiration();

    public native boolean getCached();

    public final native int getTileSize();

    public native int getZIndex();

    public final native boolean hasTransparency();

    public native void setCachePrefix(String str);

    public native void setCached(boolean z);

    public MapRasterTileSourceImpl(String str, MapRasterTileSource mapRasterTileSource, int i) {
        super(true);
        createBaseNative(str, i);
        this.b = mapRasterTileSource;
    }

    protected void finalize() {
        destroyNative();
    }

    public void a(MapOverlayType mapOverlayType) {
        setOverlayTypeNative(mapOverlayType.name());
    }

    public final MapOverlayType a() {
        return getOverlayTypeNative();
    }

    public void a(MapRasterTileSource$Transparency mapRasterTileSource$Transparency) {
        setTransparencyNative(mapRasterTileSource$Transparency.ordinal());
    }

    public void a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Tile size cannot be <= 0");
        }
        setTileSizeNative(i);
    }

    void a(a aVar) {
        setPixelFormatNative(aVar.ordinal());
    }

    public void a(GeoBoundingBox geoBoundingBox) {
        if (geoBoundingBox.isEmpty()) {
            throw new IllegalArgumentException("GeoBoundingBox provided is empty.");
        }
        setBoundingAreaNative(GeoBoundingBoxImpl.get(geoBoundingBox));
    }

    public final GeoBoundingBox b() {
        return GeoBoundingBoxImpl.create(getBoundingAreaNative());
    }

    public void b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Zoom level cannot be < 0");
        }
        hideAtZoomLevelNative(i);
    }

    public void c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Zoom level cannot be < 0");
        }
        showAtZoomLevelNative(i);
    }

    public void a(int i, int i2) {
        c(i, i2);
        hideAtZoomRangeNative(i, i2);
    }

    public void b(int i, int i2) {
        c(i, i2);
        showAtZoomRangeNative(i, i2);
    }

    public boolean d(int i) {
        if (i >= 0) {
            return isShownAtZoomLevelNative(i);
        }
        throw new IllegalArgumentException("Zoom level cannot be < 0");
    }

    private void c(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Zoom level cannot be < 0");
        } else if (i > i2) {
            throw new IllegalArgumentException("Begin zoom level larger then end zoom level");
        }
    }

    public void e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cache expiration time cannot be <= 0");
        }
        setCacheExpirationNative(i);
    }

    public void f(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Z-Index cannot be < 0");
        }
        setZIndexNative(i);
    }

    @OnlineNative
    public String getUrl(int i, int i2, int i3) {
        String str = "";
        if (this.b == null || !this.c) {
            return str;
        }
        return ((UrlMapRasterTileSourceBase) this.b).getUrl(i, i2, i3);
    }

    @OnlineNative
    public boolean hasTile(int i, int i2, int i3) {
        if (this.b != null) {
            return this.b.hasTile(i, i2, i3);
        }
        return false;
    }

    @OnlineNative
    private int[] getTileInternal(int i, int i2, int i3) {
        if (this.b == null) {
            return null;
        }
        MapRasterTileSource$TileResult tileWithError = this.b.getTileWithError(i, i2, i3);
        if (tileWithError.getError() != Error.NONE) {
            return null;
        }
        byte[] data = tileWithError.getData();
        if (data == null) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        if (decodeByteArray == null) {
            return null;
        }
        int width = decodeByteArray.getWidth();
        int height = decodeByteArray.getHeight();
        int[] iArr = new int[((width * height) + 3)];
        decodeByteArray.getPixels(iArr, 0, width, 0, 0, width, height);
        decodeByteArray.recycle();
        iArr[width * height] = width;
        iArr[(width * height) + 1] = height;
        iArr[(width * height) + 2] = tileWithError.getError().ordinal();
        return iArr;
    }

    public void c() {
        setTileMode();
        this.c = true;
    }

    public static void a(k<MapRasterTileSource, MapRasterTileSourceImpl> kVar) {
        d = kVar;
    }

    static MapRasterTileSourceImpl a(MapRasterTileSource mapRasterTileSource) {
        return (MapRasterTileSourceImpl) d.a(mapRasterTileSource);
    }
}
