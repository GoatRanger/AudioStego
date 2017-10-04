package com.here.posclient.analytics;

import dji.pilot.usercenter.protocol.d;

public class PositioningCounters extends Counters {
    public final long byBle;
    public final long byCell;
    public final long byWlan;
    public final long estimates;
    public final long externals;
    public final long fallbacks;
    public final long onlines;
    public final long updateErrors;
    public final long updates;
    public final long withBuilding;
    public final long withFloor;

    PositioningCounters(int i, long[] jArr) {
        super(i);
        this.updates = jArr[0];
        this.updateErrors = jArr[1];
        this.fallbacks = jArr[2];
        this.estimates = jArr[3];
        this.externals = jArr[4];
        this.withBuilding = jArr[5];
        this.withFloor = jArr[6];
        this.byCell = jArr[7];
        this.byWlan = jArr[8];
        this.byBle = jArr[9];
        this.onlines = jArr[10];
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PositioningCounters [");
        stringBuffer.append("event: ").append(this.event);
        stringBuffer.append(", updates: ").append(this.updates);
        stringBuffer.append(", updateErrors: ").append(this.updateErrors);
        stringBuffer.append(", fallbacks: ").append(this.fallbacks);
        stringBuffer.append(", estimates: ").append(this.estimates);
        stringBuffer.append(", externals: ").append(this.externals);
        stringBuffer.append(", withBuilding: ").append(this.withBuilding);
        stringBuffer.append(", withFloor: ").append(this.withFloor);
        stringBuffer.append(", byCell: ").append(this.byCell);
        stringBuffer.append(", byWlan: ").append(this.byWlan);
        stringBuffer.append(", byBle: ").append(this.byBle);
        stringBuffer.append(", onlines: ").append(this.onlines);
        stringBuffer.append(d.H);
        return stringBuffer.toString();
    }
}
