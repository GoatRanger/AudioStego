package com.nokia.maps;

import com.here.android.mpa.common.TimeInterval;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.Date;

@Online
public class TimeIntervalImpl extends BaseNativeObject {
    private static am<TimeInterval, TimeIntervalImpl> b;
    private cq a;

    private native void destroyTimeIntervalNative();

    private final native long getEndTimeNative();

    private final native long getStartTimeNative();

    public static void a(am<TimeInterval, TimeIntervalImpl> amVar) {
        b = amVar;
    }

    private TimeIntervalImpl() {
        this.a = new cq(TimeIntervalImpl.class.getName());
        this.nativeptr = 0;
    }

    @OnlineNative
    private TimeIntervalImpl(int i) {
        this.a = new cq(TimeIntervalImpl.class.getName());
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTimeIntervalNative();
    }

    public final Date a() {
        return new Date(getStartTimeNative());
    }

    public final Date b() {
        return new Date(getEndTimeNative());
    }

    static TimeInterval a(TimeIntervalImpl timeIntervalImpl) {
        if (timeIntervalImpl != null) {
            return (TimeInterval) b.a(timeIntervalImpl);
        }
        return null;
    }

    static {
        ce.a(TimeInterval.class);
    }
}
