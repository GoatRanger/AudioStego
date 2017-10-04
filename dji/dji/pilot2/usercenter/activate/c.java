package dji.pilot2.usercenter.activate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import dji.log.DJILogHelper;
import dji.pilot.publics.widget.k;
import dji.pilot.visual.a.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class c {
    public static void a(String str) {
        DJILogHelper.getInstance().LOGE("ACTIVATE_NEW", str + " @" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()), "active");
    }

    public static Dialog a(Context context, String str) {
        Dialog kVar = new k(context);
        kVar.setCanceledOnTouchOutside(false);
        kVar.show();
        return kVar;
    }

    public static int a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + d.c);
    }

    public static int a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }

    public static int a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            View view = adapter.getView(i2, null, listView);
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            i += view.getMeasuredHeight();
        }
        return i + (listView.getDividerHeight() * (adapter.getCount() - 1));
    }

    public static boolean a() {
        Object a = dji.pilot.countrycode.a.c.getInstance().a();
        a("get j get code=" + a);
        if (TextUtils.isEmpty(a)) {
            return Locale.JAPAN.getLanguage().equals(Locale.getDefault().getLanguage());
        }
        return a.equals("JP");
    }

    public static boolean b() {
        String a = dji.pilot.countrycode.a.c.getInstance().a();
        a("get c get code=" + a);
        if (TextUtils.isEmpty(a)) {
            a = Locale.getDefault().getLanguage();
            if (Locale.CHINA.getLanguage().equals(a) || Locale.TAIWAN.getLanguage().equals(a)) {
                return true;
            }
            return false;
        } else if (a.equals("CN") || a.equals("HK") || a.equals("TW") || a.equals("MO")) {
            return true;
        } else {
            return false;
        }
    }
}
