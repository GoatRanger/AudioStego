package com.here.services.playback.internal.util;

import com.here.odnp.util.TimeManager;
import java.util.concurrent.TimeUnit;

class TimeCalculator {
    private static final String TAG = "services.playback.internal.util.TimeCalculator";
    private final long FAST_FORWARD_INTERVAL_LIMIT = TimeUnit.SECONDS.toMillis(4);
    private final long mCurrentTimeMillis = TimeManager.currentTimeMillis();
    private long mFastForwardAdjustment;
    private long mFfCumulativeCount;
    private long mFfCumulativeMovingAverage;
    private long mFfLastMeasurementTime;
    private final long mReferenceTime;
    private final long mTimeSinceBoot = TimeManager.timeSinceBoot();

    TimeCalculator(long j) {
        this.mReferenceTime = j;
    }

    public static long timeSinceBootDiff(long j) {
        return Math.max(0, j - TimeManager.timeSinceBoot());
    }

    public long getAdjustedTimeSinceBoot(long j) {
        return (this.mTimeSinceBoot + (j - this.mReferenceTime)) - this.mFastForwardAdjustment;
    }

    public long getAdjustedCurrentTimeMillis(long j) {
        return (this.mCurrentTimeMillis + (j - this.mReferenceTime)) - this.mFastForwardAdjustment;
    }

    public void doFastForwardAdjustments(long j) {
        if (this.mFfLastMeasurementTime == 0) {
            this.mFfLastMeasurementTime = j;
            return;
        }
        long j2 = j - this.mFfLastMeasurementTime;
        if (j2 > this.FAST_FORWARD_INTERVAL_LIMIT) {
            this.mFastForwardAdjustment = (j2 - this.mFfCumulativeMovingAverage) + this.mFastForwardAdjustment;
        } else {
            long j3 = this.mFfCumulativeMovingAverage;
            j2 -= this.mFfCumulativeMovingAverage;
            long j4 = this.mFfCumulativeCount + 1;
            this.mFfCumulativeCount = j4;
            this.mFfCumulativeMovingAverage = (j2 / j4) + j3;
        }
        this.mFfLastMeasurementTime = j;
    }
}
