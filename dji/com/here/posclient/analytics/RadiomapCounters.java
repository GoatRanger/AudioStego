package com.here.posclient.analytics;

import dji.pilot.usercenter.protocol.d;

public class RadiomapCounters extends Counters {
    public final long downloadCount;
    public final long downloadFileSize;
    public final long errors;

    RadiomapCounters(int i, long[] jArr) {
        super(i);
        this.errors = jArr[0];
        this.downloadCount = jArr[1];
        this.downloadFileSize = jArr[2];
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("RadiomapCounters [");
        stringBuffer.append("event: ").append(this.event);
        stringBuffer.append(", errors: ").append(this.errors);
        stringBuffer.append(", downloadCount: ").append(this.downloadCount);
        stringBuffer.append(", downloadFileSize: ").append(this.downloadFileSize).append("kB");
        stringBuffer.append(d.H);
        return stringBuffer.toString();
    }
}
