package com.here.odnp.util;

public class TimeManager {
    private static final String TAG = "odnp.util.TimeManager";
    private static ITimeManager mTimeManager;

    private TimeManager() {
    }

    public static long timeSinceBoot() {
        return getTimeManager().timeSinceBoot();
    }

    public static long currentTimeMillis() {
        return getTimeManager().currentTimeMillis();
    }

    public static synchronized void setTimeManager(ITimeManager iTimeManager) {
        synchronized (TimeManager.class) {
            mTimeManager = iTimeManager;
        }
    }

    private static synchronized ITimeManager getTimeManager() {
        ITimeManager iTimeManager;
        synchronized (TimeManager.class) {
            if (mTimeManager == null) {
                setTimeManager(new PlatformTimeManager());
            }
            iTimeManager = mTimeManager;
        }
        return iTimeManager;
    }
}
