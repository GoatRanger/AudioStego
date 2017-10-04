package com.nokia.maps;

import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;

@Online
@Internal
public class GLExtension {
    private static Boolean a = null;

    public static native boolean glFramebufferTexture2DMultisampleIMG(int i);

    public static native boolean glRenderbufferStorageMultisampleIMG(int i, int i2);
}
