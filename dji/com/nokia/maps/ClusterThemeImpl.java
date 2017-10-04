package com.nokia.maps;

import com.here.android.mpa.cluster.ClusterDensityRange;
import com.here.android.mpa.cluster.ClusterStyle;
import com.here.android.mpa.cluster.ClusterTheme;
import com.nokia.maps.annotation.Online;

@Online
public class ClusterThemeImpl extends BaseNativeObject {
    private static final String a = ClusterThemeImpl.class.getSimpleName();
    private static volatile k<ClusterTheme, ClusterThemeImpl> b;

    private native void createCopyNative(ClusterThemeImpl clusterThemeImpl);

    private native void createNative();

    private native void deleteNative();

    private native boolean setStyleForDensityRangeNative(int i, int i2, ad adVar);

    static {
        ce.a(ClusterTheme.class);
    }

    public static void a(k<ClusterTheme, ClusterThemeImpl> kVar) {
        bj.e(a, "accessor =" + kVar, new Object[0]);
        b = kVar;
    }

    public static ClusterThemeImpl a(ClusterTheme clusterTheme) {
        return (ClusterThemeImpl) b.a(clusterTheme);
    }

    public ClusterThemeImpl() {
        createNative();
    }

    public ClusterThemeImpl(ClusterTheme clusterTheme) {
        createCopyNative(a(clusterTheme));
    }

    public void a(int i, int i2, ClusterStyle clusterStyle) {
        a(new ClusterDensityRange(i, i2), clusterStyle);
    }

    public void a(ClusterDensityRange clusterDensityRange, ClusterStyle clusterStyle) {
        dy.a((Object) clusterDensityRange, "range cannot be null");
        dy.a((Object) clusterStyle, "style cannot be null");
        bj.e(a, "setting style [" + clusterStyle + "] for the range: " + clusterDensityRange, new Object[0]);
        if (!setStyleForDensityRangeNative(clusterDensityRange.from, clusterDensityRange.to, ad.a(clusterStyle))) {
            throw new IllegalArgumentException("range [" + clusterDensityRange + "] overlaps with at least one range already set in this theme");
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }
}
