package com.here.android.mpa.common;

import com.nokia.maps.TimeIntervalImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.Date;

@Online
public final class TimeInterval {
    private TimeIntervalImpl a;

    private TimeInterval(TimeIntervalImpl timeIntervalImpl) {
        dy.a((Object) timeIntervalImpl, "Insufficient data to create an instance of " + TimeInterval.class.getName());
        this.a = timeIntervalImpl;
    }

    public final Date getStartTime() {
        return this.a.a();
    }

    public final Date getEndTime() {
        return this.a.b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (TimeInterval.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    static {
        TimeIntervalImpl.a(new am<TimeInterval, TimeIntervalImpl>() {
            public TimeInterval a(TimeIntervalImpl timeIntervalImpl) {
                if (timeIntervalImpl != null) {
                    return new TimeInterval(timeIntervalImpl);
                }
                return null;
            }
        });
    }
}
