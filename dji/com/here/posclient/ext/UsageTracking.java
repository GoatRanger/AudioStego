package com.here.posclient.ext;

import com.here.posclient.Status;
import com.here.posclient.analytics.Tracker;
import com.here.posclient.analytics.UsageTrackingListener;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

public class UsageTracking {
    private static final String TAG = "posclient.ext.UsageTracking";

    private static native int subscribe(UsageTrackingListener usageTrackingListener, long j);

    private static native long supportedTrackers();

    public static native int unsubscribe();

    static {
        System.loadLibrary("posclient");
    }

    private UsageTracking() {
    }

    public static EnumSet<Tracker> getSupportedTrackers() {
        return trackersFromBitmask(supportedTrackers());
    }

    public static Status subscribe(UsageTrackingListener usageTrackingListener, Tracker... trackerArr) {
        long trackersToBitmask = trackersToBitmask(trackerArr);
        if (trackersToBitmask == 0) {
            return Status.UsageError;
        }
        return Status.fromInt(subscribe(usageTrackingListener, trackersToBitmask));
    }

    private static long trackersToBitmask(Tracker[] trackerArr) {
        int length = trackerArr.length;
        int i = 0;
        long j = 0;
        while (i < length) {
            Tracker tracker = trackerArr[i];
            if (tracker == null) {
                return 0;
            }
            i++;
            j = tracker.mValue | j;
        }
        return j;
    }

    private static EnumSet<Tracker> trackersFromBitmask(long j) {
        if (j == 0) {
            return EnumSet.noneOf(Tracker.class);
        }
        Collection hashSet = new HashSet();
        for (Tracker tracker : Tracker.values()) {
            if ((tracker.mValue & j) == tracker.mValue) {
                hashSet.add(tracker);
            }
        }
        return EnumSet.copyOf(hashSet);
    }
}
