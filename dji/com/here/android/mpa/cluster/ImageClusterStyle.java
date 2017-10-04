package com.here.android.mpa.cluster;

import com.here.android.mpa.common.Image;
import com.nokia.maps.ImageClusterStyleImpl;
import com.nokia.maps.ImageImpl;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class ImageClusterStyle extends ClusterStyle {
    private native void createNative(ImageImpl imageImpl);

    private native void deleteNative();

    public ImageClusterStyle(Image image) {
        this.m_pimpl = new ImageClusterStyleImpl(image);
    }

    public ImageClusterStyle(int i) {
        this.m_pimpl = new ImageClusterStyleImpl(i);
    }

    public String toString() {
        return "ICS#" + (hashCode() % 1000);
    }
}
