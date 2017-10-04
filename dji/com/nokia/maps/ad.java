package com.nokia.maps;

import com.here.android.mpa.cluster.ClusterStyle;

public abstract class ad extends BaseNativeObject {
    private static final String a = ad.class.getSimpleName();
    private static volatile k<ClusterStyle, ad> b;

    static {
        ce.a(ClusterStyle.class);
    }

    public static void a(k<ClusterStyle, ad> kVar) {
        bj.e(a, "accessor =" + kVar, new Object[0]);
        b = kVar;
    }

    public static ad a(ClusterStyle clusterStyle) {
        return (ad) b.a(clusterStyle);
    }

    ad() {
    }
}
