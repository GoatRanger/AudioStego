package dji.phone.a;

import android.util.Log;
import dji.log.DJILogHelper;
import dji.phone.widget.DJILPMeterView;

public class g {
    private static final String a = "LLoger";
    private static final boolean b = true;
    private static String c = DJILPMeterView.a;

    public static void a(String str, String str2, boolean z) {
        if (z) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2) {
        Log.d(str, str2);
    }

    public static void b(String str, String str2) {
        DJILogHelper.getInstance().LOGE(str, str2, true, false);
    }
}
