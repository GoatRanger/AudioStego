package dji.thirdparty.b.a.b;

import dji.thirdparty.b.a.j;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class f {
    public static final long a = 253402300799999L;
    private static final ThreadLocal<DateFormat> b = new ThreadLocal<DateFormat>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected DateFormat a() {
            DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(j.d);
            return simpleDateFormat;
        }
    };
    private static final String[] c = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    private static final DateFormat[] d = new DateFormat[c.length];

    public static Date a(String str) {
        int i = 0;
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = ((DateFormat) b.get()).parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        synchronized (c) {
            int length = c.length;
            while (i < length) {
                DateFormat dateFormat = d[i];
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(c[i], Locale.US);
                    dateFormat.setTimeZone(j.d);
                    d[i] = dateFormat;
                }
                parsePosition.setIndex(0);
                parse = dateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return parse;
                }
                i++;
            }
            return null;
        }
    }

    public static String a(Date date) {
        return ((DateFormat) b.get()).format(date);
    }

    private f() {
    }
}
