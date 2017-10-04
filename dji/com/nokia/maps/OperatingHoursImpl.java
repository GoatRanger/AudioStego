package com.nokia.maps;

import com.here.android.mpa.common.TimeInterval;
import com.here.android.mpa.mapping.OperatingHours;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class OperatingHoursImpl extends BaseNativeObject {
    private static am<OperatingHours, OperatingHoursImpl> b;
    private cq a = new cq(OperatingHoursImpl.class.getName());

    private native void destroyOperatingHoursNative();

    private native TimeIntervalImpl[] friday();

    private native TimeIntervalImpl[] holiday();

    private native TimeIntervalImpl[] monday();

    private native TimeIntervalImpl[] saturday();

    private native TimeIntervalImpl[] sunday();

    private native TimeIntervalImpl[] thursday();

    private native TimeIntervalImpl[] tuesday();

    private native TimeIntervalImpl[] wednesday();

    public static void a(am<OperatingHours, OperatingHoursImpl> amVar) {
        b = amVar;
    }

    @OnlineNative
    private OperatingHoursImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyOperatingHoursNative();
    }

    public List<TimeInterval> a() {
        return a(monday());
    }

    public List<TimeInterval> b() {
        return a(tuesday());
    }

    public List<TimeInterval> c() {
        return a(wednesday());
    }

    public List<TimeInterval> d() {
        return a(thursday());
    }

    public List<TimeInterval> e() {
        return a(friday());
    }

    public List<TimeInterval> f() {
        return a(saturday());
    }

    public List<TimeInterval> g() {
        return a(sunday());
    }

    public List<TimeInterval> h() {
        return a(holiday());
    }

    private List<TimeInterval> a(TimeIntervalImpl[] timeIntervalImplArr) {
        if (timeIntervalImplArr == null) {
            return null;
        }
        List<TimeInterval> arrayList = new ArrayList();
        for (TimeIntervalImpl a : timeIntervalImplArr) {
            TimeInterval a2 = TimeIntervalImpl.a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    static OperatingHours a(OperatingHoursImpl operatingHoursImpl) {
        if (operatingHoursImpl != null) {
            return (OperatingHours) b.a(operatingHoursImpl);
        }
        return null;
    }

    static {
        ce.a(OperatingHours.class);
    }
}
