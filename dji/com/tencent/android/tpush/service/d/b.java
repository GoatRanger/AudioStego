package com.tencent.android.tpush.service.d;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class b {
    public static String a = "yyyyMMdd_HHmm";

    public static String a() {
        return new SimpleDateFormat(a).format(new Date());
    }

    public static Date a(String str) {
        try {
            return new SimpleDateFormat(a).parse(str);
        } catch (Throwable e) {
            Log.e("XGLogger", "parse filename datetime error - " + str, e);
            return null;
        }
    }

    public static boolean a(Date date, int i) {
        boolean z = false;
        if (date != null) {
            try {
                Calendar instance = Calendar.getInstance();
                Calendar instance2 = Calendar.getInstance();
                instance2.setTimeInMillis(date.getTime());
                instance.add(5, -i);
                z = instance.after(instance2);
            } catch (Throwable e) {
                Log.e("XGLogger", "DateUtils -> isDaysAgo ", e);
            }
        }
        return z;
    }
}
