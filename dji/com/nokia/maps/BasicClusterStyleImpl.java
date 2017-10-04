package com.nokia.maps;

import com.here.android.mpa.cluster.BasicClusterStyle;
import com.nokia.maps.annotation.Online;

@Online
public class BasicClusterStyleImpl extends ad {
    private static final String a = BasicClusterStyleImpl.class.getSimpleName();

    private native void createNative();

    private native void deleteNative();

    public native int getFillColorNative();

    public native int getFontColorNative();

    public native int getStrokeColorNative();

    public native void setFillColorNative(int i);

    public native void setFontColorNative(int i);

    public native void setStrokeColorNative(int i);

    static {
        ce.a(BasicClusterStyle.class);
    }

    public BasicClusterStyleImpl() {
        createNative();
    }

    public String toString() {
        return getClass().getSimpleName() + '#' + hashCode();
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }
}
