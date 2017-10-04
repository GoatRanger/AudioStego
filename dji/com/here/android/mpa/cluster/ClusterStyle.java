package com.here.android.mpa.cluster;

import com.nokia.maps.ad;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public abstract class ClusterStyle {
    protected ad m_pimpl;

    ClusterStyle() {
    }

    static {
        ad.a(new k<ClusterStyle, ad>() {
            public ad a(ClusterStyle clusterStyle) {
                return clusterStyle.m_pimpl;
            }
        });
    }
}
