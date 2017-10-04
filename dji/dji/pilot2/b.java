package dji.pilot2;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class b {
    public static b a;
    private Handler b = null;
    private Context c = null;

    public static void a(Context context) {
        if (a == null) {
            a = new b(context);
        }
    }

    public Context a() {
        return this.c;
    }

    public b(Context context) {
        this.c = context;
        this.b = new Handler();
    }

    public Handler b() {
        return this.b;
    }

    public int c() {
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int d() {
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int e() {
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (d() > displayMetrics.widthPixels) {
            return d();
        }
        return displayMetrics.widthPixels;
    }

    public int f() {
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (d() < displayMetrics.widthPixels) {
            return displayMetrics.widthPixels;
        }
        return d();
    }
}
