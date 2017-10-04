package com.nokia.maps;

import com.nokia.maps.annotation.Internal;

@Internal
public class ep extends BaseNativeObject {
    private native void a();

    protected void finalize() {
        if (this.nativeptr != 0) {
            a();
        }
    }
}
