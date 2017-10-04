package dji.pilot.countrycode.a;

import android.content.Context;
import android.widget.Toast;

public class a {
    public static final String a = (a() + "geocoder_service/geoip");
    public static int b = 0;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    private static final String f = "CountryCodeConstant";
    private static final boolean g = false;

    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        AnonymousClass1(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public void run() {
            a.b++;
            Toast.makeText(this.a.getApplicationContext(), "【toastId=" + a.b + "】" + this.b, 1).show();
        }
    }

    private static String a() {
        return "https://mydjiflight.dji.com/api/v2/";
    }

    public static void a(Context context, String str) {
        if (context != null && !c) {
        }
    }

    public static void a(String str) {
    }

    public static void a(String[] strArr) {
    }
}
