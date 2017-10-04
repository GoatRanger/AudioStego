package com.here.odnp.util;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;

public class WakeLock {
    private static final String TAG = "odnp.util.WakeLock";
    private static final String WAKELOCK_TAG = "odnp.WakeLock";
    private long mAcquiredTime;
    private final PowerManager mPowerManager;
    private final android.os.PowerManager.WakeLock mWakeLock;

    public WakeLock(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mWakeLock = this.mPowerManager.newWakeLock(1, WAKELOCK_TAG);
        this.mWakeLock.setReferenceCounted(false);
    }

    public void acquire() {
        if (!this.mWakeLock.isHeld()) {
            this.mWakeLock.acquire();
            this.mAcquiredTime = SystemClock.elapsedRealtime();
        }
    }

    public void release() {
        if (this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
        }
    }
}
