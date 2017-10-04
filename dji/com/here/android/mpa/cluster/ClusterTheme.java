package com.here.android.mpa.cluster;

import com.nokia.maps.ClusterThemeImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public class ClusterTheme {
    private ClusterThemeImpl a = new ClusterThemeImpl();

    public ClusterTheme(ClusterTheme clusterTheme) {
        this.a = new ClusterThemeImpl(clusterTheme);
    }

    public void setStyleForDensityRange(int i, int i2, ClusterStyle clusterStyle) {
        this.a.a(i, i2, clusterStyle);
    }

    public void setStyleForDensityRange(ClusterDensityRange clusterDensityRange, ClusterStyle clusterStyle) {
        this.a.a(clusterDensityRange, clusterStyle);
    }

    static {
        ClusterThemeImpl.a(new k<ClusterTheme, ClusterThemeImpl>() {
            public ClusterThemeImpl a(ClusterTheme clusterTheme) {
                return clusterTheme.a;
            }
        });
    }
}
