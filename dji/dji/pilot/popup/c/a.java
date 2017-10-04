package dji.pilot.popup.c;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import java.util.Date;

public class a {
    public static boolean a = true;
    public static boolean b = false;
    public static boolean c = false;
    public static int d = 0;
    private static final String e = "popup";

    public static class a {
        public static ProductType a = ProductType.Pomato;
    }

    public static class b {
    }

    public static void a(String str, String str2, Throwable th, int i, String str3) {
        a(str + "." + str2, th, i, str3);
    }

    public static void a(String str, Throwable th, int i, String str2) {
        if (th != null) {
            th.printStackTrace();
        }
        a("method=" + str + ",t=" + th + ",errorNo = " + i + ",strMsg=" + str2);
    }

    public static void a(final Context context, String str) {
        if (!b || !c) {
            return;
        }
        if (context == null) {
            a("null == context,will not show the toast, " + str);
            return;
        }
        final String str2 = "[ toastId=" + d + " ]:" + str;
        a(str2);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                a.d++;
                Toast.makeText(context.getApplicationContext(), str2, 1).show();
            }
        }, 0);
    }

    public static void a(String str, String str2) {
        a(str + "->" + str2);
    }

    public static void a(String str) {
        if (!b) {
            return;
        }
        if (a) {
            DJILogHelper.getInstance().LOGE(e, str, true, true);
        } else {
            Log.e(e, str);
        }
    }

    public static void b(String str) {
        DJILogHelper.getInstance().LOGE(e, str + " @" + new Date(), e);
    }
}
