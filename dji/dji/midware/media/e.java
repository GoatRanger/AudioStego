package dji.midware.media;

import android.util.Log;
import dji.log.DJILogHelper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

public class e {
    private static e a = null;
    private static String b = "MediaLogger";
    private static boolean e = false;
    private FileWriter c = null;
    private BufferedWriter d = null;

    private static e b() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private e() {
        try {
            this.c = new FileWriter(dji.midware.media.e.e.a() + "MediaLogger.log");
            this.d = new BufferedWriter(this.c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, Object obj) {
        String str2 = "null";
        if (obj != null) {
            str2 = obj.toString();
        }
        DJILogHelper.getInstance().LOGD(str, str2, false, true);
        if (e) {
            Log.i(b, str2);
        }
    }

    public static void a(String str) {
        DJILogHelper.getInstance().LOGD(b, str, false, true);
        if (e) {
            Log.i(b, str);
        }
    }

    public static void a(String str, String str2) {
        DJILogHelper.getInstance().LOGD(b, str2, false, true);
        if (e) {
            Log.i(str, str2);
        }
    }

    public static void a(boolean z, String str, String str2) {
        if (e && d.a(z)) {
            a(str, str2);
        }
    }

    public static void a(Exception exception) {
        String b = b(exception);
        DJILogHelper.getInstance().LOGD(b, b, true, true);
        if (e) {
            Log.i(b, b);
        }
    }

    public static void a(String str, Exception exception) {
        if (e) {
            Log.e(str, b(exception));
        }
    }

    public static void b(String str, String str2) {
        if (e) {
            Log.e(str, str2);
        }
    }

    public static void b(boolean z, String str, String str2) {
        if (e && d.a(z)) {
            b(str, str2);
        }
    }

    public static void b(String str, Exception exception) {
        DJILogHelper.getInstance().LOGD(str, b(exception), false, true);
    }

    public static void c(String str, String str2) {
        DJILogHelper.getInstance().LOGD(str, str2, true, true);
    }

    public static void c(String str, Exception exception) {
        DJILogHelper.getInstance().LOGD(str, b(exception), true, true);
    }

    public static void d(String str, String str2) {
        if (e && d.a(true)) {
            Log.i(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (e && d.a(true)) {
            Log.d(str, str2);
        }
    }

    public static void c(boolean z, String str, String str2) {
        if (e && d.a(z)) {
            d(str, str2);
        }
    }

    public static void d(boolean z, String str, String str2) {
        if (e && d.a(z)) {
            e(str, str2);
        }
    }

    public static void f(String str, String str2) {
        if (e) {
            try {
                b().d.write(String.format("%s [%s]:%s\n", new Object[]{new Date().toString(), str, str2}));
                b().d.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String str, Exception exception) {
        g(str, b(exception));
    }

    public static String b(Exception exception) {
        Writer stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void g(String str, String str2) {
        try {
            b().d.write(String.format("%s [%s]^^^EXCEPTION^^^:%s\n", new Object[]{new Date().toString(), str, str2}));
            b().d.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        try {
            if (this.d != null) {
                this.d.close();
            }
            if (this.c != null) {
                this.c.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
