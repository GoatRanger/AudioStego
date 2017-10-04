package com.here.android.mpa.common;

import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Online;

@Online
public final class Size {
    @HybridPlusNative
    public int height;
    @HybridPlusNative
    public int width;

    public Size() {
        this.width = 0;
        this.height = 0;
    }

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        if (this.width == size.width && this.height == size.height) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.width + 31) * 31) + this.height;
    }

    public boolean isNull() {
        return this.width == 0 && this.height == 0;
    }
}
