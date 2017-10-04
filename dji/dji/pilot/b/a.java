package dji.pilot.b;

import android.content.Context;
import com.tencent.bugly.crashreport.CrashReport;

public class a {
    public static void a(Context context, String str, String str2, String str3) {
        if (str2 != null) {
            CrashReport.setUserId(str2);
        }
        if (str != null) {
            CrashReport.putUserData(context, "userId", str);
        }
        if (str2 != null) {
            CrashReport.putUserData(context, "email", str2);
        }
        if (str3 != null) {
            CrashReport.putUserData(context, "phone", str3);
        }
    }
}
