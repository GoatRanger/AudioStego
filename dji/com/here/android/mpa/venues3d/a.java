package com.here.android.mpa.venues3d;

import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.Internal;

@Internal
public final class a extends BaseNativeObject {
    private native void a();

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            a();
        }
        super.finalize();
    }
}
