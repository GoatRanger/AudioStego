package com.here.android.mpa.mapping;

import com.here.android.mpa.common.TimeInterval;
import com.nokia.maps.OperatingHoursImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.List;

@Online
public final class OperatingHours {
    private OperatingHoursImpl a;

    private OperatingHours(OperatingHoursImpl operatingHoursImpl) {
        this.a = operatingHoursImpl;
    }

    public List<TimeInterval> getMondaySchedule() {
        return this.a.a();
    }

    public List<TimeInterval> getTuesdaySchedule() {
        return this.a.b();
    }

    public List<TimeInterval> getWednesdaySchedule() {
        return this.a.c();
    }

    public List<TimeInterval> getThursdaySchedule() {
        return this.a.d();
    }

    public List<TimeInterval> getFridaySchedule() {
        return this.a.e();
    }

    public List<TimeInterval> getSaturdaySchedule() {
        return this.a.f();
    }

    public List<TimeInterval> getSundaySchedule() {
        return this.a.g();
    }

    public List<TimeInterval> getHolidaySchedule() {
        return this.a.h();
    }

    static {
        OperatingHoursImpl.a(new am<OperatingHours, OperatingHoursImpl>() {
            public OperatingHours a(OperatingHoursImpl operatingHoursImpl) {
                if (operatingHoursImpl != null) {
                    return new OperatingHours(operatingHoursImpl);
                }
                return null;
            }
        });
    }
}
