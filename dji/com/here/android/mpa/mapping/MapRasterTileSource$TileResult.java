package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;
import java.util.Arrays;

@Online
public class MapRasterTileSource$TileResult {
    private byte[] a;
    private Error b;

    @Online
    public enum Error {
        NONE,
        NOT_READY,
        NOT_FOUND
    }

    public MapRasterTileSource$TileResult(Error error, byte[] bArr) {
        this.a = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        this.b = error;
    }

    public byte[] getData() {
        if (this.a == null) {
            return null;
        }
        return Arrays.copyOf(this.a, this.a.length);
    }

    public Error getError() {
        return this.b;
    }
}
