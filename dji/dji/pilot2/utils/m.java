package dji.pilot2.utils;

import android.content.Context;
import android.graphics.Paint;
import dji.pilot.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class m {
    private static Random a = new Random();
    private static char[] b = "0123456789abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static char[] c = "0123456789".toCharArray();

    private m() {
    }

    public static String a(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = b[a.nextInt(b.length - 1)];
        }
        return new String(cArr);
    }

    public static String b(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = c[a.nextInt(c.length - 1)];
        }
        return new String(cArr);
    }

    public static String a(Context context, long j) {
        long j2 = j % 3600000;
        long j3 = j2 / 60000;
        j2 %= 60000;
        long j4 = j2 / 1000;
        j2 = (j2 % 1000) / 100;
        return String.format("%02d:%02d:%02d.%01d", new Object[]{Long.valueOf(j / 3600000), Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j2)});
    }

    public static String b(Context context, long j) {
        long j2 = j % 3600000;
        long j3 = j2 / 60000;
        j2 = (j2 % 60000) / 1000;
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j / 3600000), Long.valueOf(j3), Long.valueOf(j2)});
    }

    public static String c(Context context, long j) {
        long j2 = j / 60000;
        long round = (long) Math.round((((float) (j % 60000)) * 1.0f) / 1000.0f);
        return String.format("%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(round)});
    }

    public static String d(Context context, long j) {
        long j2 = j / 3600000;
        long j3 = j % 3600000;
        long j4 = j3 / 60000;
        j3 = (j3 % 60000) / 1000;
        if (j2 == 0) {
            if (j4 == 0) {
                return String.format(context.getString(R.string.seconds), new Object[]{Long.valueOf(j3)});
            } else if (j4 == 1) {
                return String.format(context.getString(R.string.minute_and_seconds), new Object[]{Long.valueOf(j3)});
            } else {
                return String.format(context.getString(R.string.minutes), new Object[]{Long.valueOf(j4)});
            }
        } else if (j2 == 1) {
            return String.format(context.getString(R.string.hour_and_minutes), new Object[]{Long.valueOf(j4)});
        } else {
            return String.format(context.getString(R.string.hours_and_minutes), new Object[]{Long.valueOf(j2), Long.valueOf(j4)});
        }
    }

    public static String a(String str, Paint paint, int i) {
        if (((int) paint.measureText(str)) <= i) {
            return str;
        }
        return str.substring(0, paint.breakText(str, true, (float) (i - 12), null)) + "...";
    }

    public static long a(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ROOT).parse(str).getTime();
    }

    public static String a(long j) {
        int i = (int) ((j / 1000) % 60);
        return String.format("%02d:%02d", new Object[]{Integer.valueOf((int) ((j / 1000) / 60)), Integer.valueOf(i)});
    }

    public static String a() {
        return a(6);
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(String str) {
        return str == null || str.equals("");
    }
}
