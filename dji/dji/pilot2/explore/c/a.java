package dji.pilot2.explore.c;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.pilot.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class a {
    public static final String a = "RelativeDateFormat";
    private static final long b = 60000;
    private static final long c = 3600000;
    private static final long d = 86400000;
    private static final long e = 604800000;
    private static Date f;

    public static String a(Context context, String str) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:m:s").parse(a(str));
        long time = f.getTime() - parse.getTime();
        if (time < 60000) {
            return context.getString(R.string.v2_explore_comment_time_rightnow);
        }
        if (time < 3600000) {
            long b = b(time);
            StringBuilder stringBuilder = new StringBuilder();
            if (b <= 0) {
                b = 1;
            }
            return stringBuilder.append(b).append(context.getString(R.string.v2_explore_comment_time_minute)).toString();
        }
        SimpleDateFormat simpleDateFormat;
        if (time < 86400000) {
            simpleDateFormat = new SimpleDateFormat("dd");
            if (simpleDateFormat.format(parse).equals(simpleDateFormat.format(f))) {
                return new SimpleDateFormat("h:mm a").format(parse);
            }
        }
        if (time < 31536000000L) {
            simpleDateFormat = new SimpleDateFormat("yy");
            if (simpleDateFormat.format(parse).equals(simpleDateFormat.format(f))) {
                return new SimpleDateFormat("M/dd").format(parse);
            }
        }
        return new SimpleDateFormat("yy/M/dd").format(parse);
    }

    private static long a(long j) {
        return j / 1000;
    }

    private static long b(long j) {
        return a(j) / 60;
    }

    private static long c(long j) {
        return b(j) / 60;
    }

    private static long d(long j) {
        return c(j) / 24;
    }

    private static long e(long j) {
        return d(j) / 30;
    }

    private static long f(long j) {
        return e(j) / 365;
    }

    public static void a() {
        f = null;
        f = new Date();
    }

    public static void b() {
        f = null;
    }

    private static String a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        if (str == null) {
            return str;
        }
        try {
            TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
            TimeZone timeZone2 = TimeZone.getDefault();
            if (timeZone2.equals(timeZone)) {
                DJILogHelper.getInstance().LOGD(a, "the same time zone!");
                return str;
            }
            simpleDateFormat.setTimeZone(timeZone);
            long time = simpleDateFormat.parse(str).getTime();
            simpleDateFormat.setTimeZone(timeZone2);
            DJILogHelper.getInstance().LOGD(a, "diffrent time zone,convert from:" + str + " to:" + simpleDateFormat.format(Long.valueOf(time)));
            return simpleDateFormat.format(Long.valueOf(time));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
