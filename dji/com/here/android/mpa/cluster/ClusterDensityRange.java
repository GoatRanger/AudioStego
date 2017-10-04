package com.here.android.mpa.cluster;

import com.nokia.maps.annotation.HybridPlus;
import dji.pilot.usercenter.protocol.d;

@HybridPlus
public class ClusterDensityRange {
    public static final int MINIMUM_CLUSTER_DENSITY = 2;
    public final int from;
    public final int to;

    public ClusterDensityRange(int i, int i2) throws IllegalArgumentException {
        if (isValidDensityRange(i, i2)) {
            this.from = i;
            this.to = i2;
            return;
        }
        throw new IllegalArgumentException("lower limit must be >= 2 and <= upper limit");
    }

    public static boolean isValidDensityRange(int i, int i2) {
        return 2 <= i && i <= i2;
    }

    public String toString() {
        return d.G + this.from + ", " + this.to + d.H;
    }
}
