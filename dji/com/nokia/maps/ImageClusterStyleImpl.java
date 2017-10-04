package com.nokia.maps;

import com.here.android.mpa.cluster.ImageClusterStyle;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.Online;

@Online
public class ImageClusterStyleImpl extends ad {
    private native void createNative(ImageImpl imageImpl);

    private native void deleteNative();

    public ImageClusterStyleImpl(Image image) {
        createNative(ImageImpl.a(image));
    }

    public ImageClusterStyleImpl(int i) {
        Image image = new Image();
        try {
            image.setImageResource(i);
            createNative(ImageImpl.a(image));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + '#' + hashCode();
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }

    static {
        ce.a(ImageClusterStyle.class);
    }
}
