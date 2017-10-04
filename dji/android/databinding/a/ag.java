package android.databinding.a;

import android.databinding.c;
import android.databinding.m;
import android.databinding.n;
import android.os.Build.VERSION;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class ag {
    @c(a = {"android:hour"})
    public static void a(TimePicker timePicker, int i) {
        if (VERSION.SDK_INT >= 23) {
            if (timePicker.getHour() != i) {
                timePicker.setHour(i);
            }
        } else if (timePicker.getCurrentHour().intValue() != i) {
            timePicker.setCurrentHour(Integer.valueOf(i));
        }
    }

    @c(a = {"android:minute"})
    public static void b(TimePicker timePicker, int i) {
        if (VERSION.SDK_INT >= 23) {
            if (timePicker.getMinute() != i) {
                timePicker.setMinute(i);
            }
        } else if (timePicker.getCurrentMinute().intValue() != i) {
            timePicker.setCurrentHour(Integer.valueOf(i));
        }
    }

    @m(a = "android:hour")
    public static int a(TimePicker timePicker) {
        if (VERSION.SDK_INT >= 23) {
            return timePicker.getHour();
        }
        Integer currentHour = timePicker.getCurrentHour();
        if (currentHour == null) {
            return 0;
        }
        return currentHour.intValue();
    }

    @m(a = "android:minute")
    public static int b(TimePicker timePicker) {
        if (VERSION.SDK_INT >= 23) {
            return timePicker.getMinute();
        }
        Integer currentMinute = timePicker.getCurrentMinute();
        if (currentMinute == null) {
            return 0;
        }
        return currentMinute.intValue();
    }

    @c(a = {"android:onTimeChanged", "android:hourAttrChanged", "android:minuteAttrChanged"}, b = false)
    public static void a(TimePicker timePicker, final OnTimeChangedListener onTimeChangedListener, final n nVar, final n nVar2) {
        if (nVar == null && nVar2 == null) {
            timePicker.setOnTimeChangedListener(onTimeChangedListener);
        } else {
            timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
                public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                    if (onTimeChangedListener != null) {
                        onTimeChangedListener.onTimeChanged(timePicker, i, i2);
                    }
                    if (nVar != null) {
                        nVar.a();
                    }
                    if (nVar2 != null) {
                        nVar2.a();
                    }
                }
            });
        }
    }
}
