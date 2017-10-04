package android.databinding.a;

import android.databinding.c;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

@p(a = {@o(a = CalendarView.class, b = "android:date")})
public class g {
    @c(a = {"android:date"})
    public static void a(CalendarView calendarView, long j) {
        if (calendarView.getDate() != j) {
            calendarView.setDate(j);
        }
    }

    @c(a = {"android:onSelectedDayChange", "android:dateAttrChanged"}, b = false)
    public static void a(CalendarView calendarView, final OnDateChangeListener onDateChangeListener, final n nVar) {
        if (nVar == null) {
            calendarView.setOnDateChangeListener(onDateChangeListener);
        } else {
            calendarView.setOnDateChangeListener(new OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                    if (onDateChangeListener != null) {
                        onDateChangeListener.onSelectedDayChange(calendarView, i, i2, i3);
                    }
                    nVar.a();
                }
            });
        }
    }
}
