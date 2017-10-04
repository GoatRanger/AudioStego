package dji.pilot2.nativeexplore.d;

import android.content.Context;
import android.text.format.DateUtils;
import dji.pilot.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class a {
    public static String a(Context context, long j) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTimeInMillis(j * 1000);
        instance2.setTimeInMillis(System.currentTimeMillis());
        if (instance.get(1) != instance2.get(1)) {
            return new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
        }
        Calendar instance3 = Calendar.getInstance();
        instance3.setTimeInMillis(j * 1000);
        if (DateUtils.isToday(instance3.getTimeInMillis())) {
            return context.getResources().getString(R.string.v2_library_date_today);
        }
        instance3.add(5, 1);
        if (DateUtils.isToday(instance3.getTimeInMillis())) {
            return 1 + context.getResources().getString(R.string.explore_one_day_ago);
        }
        for (int i = 1; i < 5; i++) {
            instance3.add(5, 1);
            if (DateUtils.isToday(instance3.getTimeInMillis())) {
                return (i + 1) + context.getResources().getString(R.string.explore_days_ago);
            }
        }
        return new SimpleDateFormat("MM-dd").format(instance.getTime());
    }
}
