package com.here.services.positioning.analytics.internal;

import android.os.Bundle;
import com.here.posclient.analytics.Counters;
import com.here.posclient.analytics.Counters.Handler;
import com.here.posclient.analytics.Tracker;
import java.util.Arrays;
import java.util.EnumSet;

public class UsageTrackingUtils {
    private static final String KEY_COUNTERS = "tracker.counters";
    private static final String KEY_EVENT = "tracker.event";
    private static final String KEY_TRACKERS = "trackers.list";

    public static EnumSet<Tracker> bundleToEnumSet(Bundle bundle) {
        if (bundle != null && bundle.containsKey(KEY_TRACKERS)) {
            EnumSet<Tracker> enumSet = (EnumSet) bundle.getSerializable(KEY_TRACKERS);
            if (enumSet != null) {
                return enumSet;
            }
        }
        return EnumSet.noneOf(Tracker.class);
    }

    public static Tracker[] bundleToArray(Bundle bundle) {
        if (bundle == null) {
            return new Tracker[0];
        }
        return (Tracker[]) bundleToEnumSet(bundle).toArray(new Tracker[0]);
    }

    public static Bundle trackersToBundle(Tracker... trackerArr) {
        Bundle bundle = new Bundle();
        if (!(trackerArr == null || trackerArr.length == 0)) {
            bundle.putSerializable(KEY_TRACKERS, EnumSet.copyOf(Arrays.asList(trackerArr)));
        }
        return bundle;
    }

    public static Bundle trackersToBundle(EnumSet<Tracker> enumSet) {
        if (enumSet == null) {
            return new Bundle();
        }
        return trackersToBundle((Tracker[]) enumSet.toArray(new Tracker[0]));
    }

    public static Bundle trackerUpdateToBundle(int i, long[] jArr) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_EVENT, i);
        bundle.putLongArray(KEY_COUNTERS, jArr);
        return bundle;
    }

    public static void trackerUpdateFromBundle(Bundle bundle, Handler handler) {
        if (bundle != null && bundle.containsKey(KEY_COUNTERS) && bundle.containsKey(KEY_EVENT)) {
            Counters.parse(bundle.getInt(KEY_EVENT), bundle.getLongArray(KEY_COUNTERS), handler);
        }
    }
}
