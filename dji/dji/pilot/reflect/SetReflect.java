package dji.pilot.reflect;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.a.a;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.upgrade.e;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SetReflect {
    public static void showSetActivity(Context context) {
        try {
            Class cls = Class.forName("dji.pilot.set.SetProxy");
            cls.getMethod("showSetActivity", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void notifyConfigChange(String str) {
        try {
            Class cls = Class.forName("dji.pilot.set.AppConfigManager");
            cls.getMethod("notifyConfigChange", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void notifyMainProjectChange(String str) {
        DJIGenSettingDataManager.getInstance().handleConfigChanged(str);
    }

    public static String getFirmwareVersion() {
        return e.getInstance().b();
    }

    public static String getAppVersion() {
        Context a = DJIApplication.a();
        String buildNumber = getBuildNumber(a);
        if (buildNumber == null) {
            buildNumber = a.getResources().getString(R.string.buildname);
        }
        return a.j + "(" + buildNumber + ")";
    }

    private static String getBuildNumber(Context context) {
        try {
            String readLine = new BufferedReader(new InputStreamReader(context.getAssets().open("build"))).readLine();
            if (readLine == null) {
                return readLine;
            }
            int length = readLine.length();
            if (length <= 0 || !readLine.substring(length - 1, length).equals(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                return readLine;
            }
            return readLine.substring(0, length - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
