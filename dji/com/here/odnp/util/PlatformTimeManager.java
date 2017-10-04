package com.here.odnp.util;

import android.os.SystemClock;

public class PlatformTimeManager implements ITimeManager {
    private static final String TAG = "odnp.util.PlatformTimeManager";

    public long timeSinceBoot() {
        return SystemClock.elapsedRealtime();
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
