package dji.setting.a;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dji.pilot.phonecamera.h;
import dji.pilot.setting.ui.R;
import java.util.ArrayList;

public class a {
    private static long a;

    public static int a(String str, int i) {
        if (!a(str)) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public static float a(String str, float f) {
        if (!a(str)) {
            try {
                f = Float.parseFloat(str);
            } catch (NumberFormatException e) {
            }
        }
        return f;
    }

    public static void a(View view, int i) {
        a(view, i, view.getContext());
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static void a(View view, int i, Context context) {
        View inflate = LayoutInflater.from(context).inflate(i, null);
        if ((view instanceof LinearLayout) && (inflate instanceof LinearLayout)) {
            a((LinearLayout) view, (LinearLayout) inflate);
        } else {
            ((ViewGroup) view).addView(inflate);
        }
    }

    public static void a(LinearLayout linearLayout, LinearLayout linearLayout2) {
        int i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int childCount = linearLayout2.getChildCount();
        for (i = 0; i < childCount; i++) {
            arrayList.add(linearLayout2.getChildAt(i));
            arrayList2.add((LayoutParams) linearLayout2.getChildAt(i).getLayoutParams());
        }
        linearLayout2.removeAllViews();
        linearLayout.setOrientation(linearLayout2.getOrientation());
        for (i = 0; i < childCount; i++) {
            linearLayout.addView((View) arrayList.get(i), (ViewGroup.LayoutParams) arrayList2.get(i));
        }
    }

    private static void b(LinearLayout linearLayout, LinearLayout linearLayout2) {
        try {
            linearLayout.setGravity(((Integer) LinearLayout.class.getField("mGravity").get(linearLayout2)).intValue());
        } catch (Exception e) {
        }
    }

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - a;
        if (0 >= j || j >= 1200) {
            a = currentTimeMillis;
            Log.d("isFastDoubleClick", h.e);
            return false;
        }
        Log.d("isFastDoubleClick", "true");
        return true;
    }

    public static int a(dji.midware.data.config.P3.a aVar) {
        int i = R.string.setting_ui_code_unknown;
        if (dji.midware.data.config.P3.a.a == aVar || dji.midware.data.config.P3.a.f == aVar) {
            return R.string.setting_ui_code_timeout;
        }
        if (dji.midware.data.config.P3.a.c == aVar) {
            return R.string.setting_ui_code_successed;
        }
        if (dji.midware.data.config.P3.a.e == aVar) {
            return R.string.setting_ui_code_invalid_cmd;
        }
        if (dji.midware.data.config.P3.a.i == aVar) {
            return R.string.setting_ui_code_notsupport_now;
        }
        if (dji.midware.data.config.P3.a.m == aVar) {
            return R.string.setting_ui_code_sd_removal;
        }
        if (dji.midware.data.config.P3.a.n == aVar) {
            return R.string.setting_ui_code_sd_full;
        }
        if (dji.midware.data.config.P3.a.o == aVar) {
            return R.string.setting_ui_code_sd_error;
        }
        if (dji.midware.data.config.P3.a.q == aVar) {
            return R.string.setting_ui_code_camera_critical_error;
        }
        if (dji.midware.data.config.P3.a.E == aVar) {
            return R.string.setting_ui_code_disconnect;
        }
        return i;
    }
}
