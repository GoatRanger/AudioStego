package com.tencent.android.tpush.common;

import android.net.wifi.ScanResult;
import java.util.Comparator;

final class f implements Comparator {
    f() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((ScanResult) obj, (ScanResult) obj2);
    }

    public int a(ScanResult scanResult, ScanResult scanResult2) {
        int abs = Math.abs(scanResult.level);
        int abs2 = Math.abs(scanResult2.level);
        if (abs > abs2) {
            return 1;
        }
        return abs == abs2 ? 0 : -1;
    }
}
