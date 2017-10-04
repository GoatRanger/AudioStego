package dji.log;

import com.here.odnp.debug.DebugFile;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class DJILog {
    public static void d(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length >= 3) {
            String[] split = stackTrace[3].toString().split("\\.");
            if (split != null && split.length >= 3) {
                d(split[split.length - 3], str);
                return;
            }
        }
        d(DJIUsbAccessoryReceiver.c, str);
    }

    public static void d(String str, String str2) {
        d(str, str2, false, false);
    }

    public static void d(String str, String str2, boolean z, boolean z2) {
        DJILogHelper.getInstance().LOGD(str, str2, z, z2);
    }

    public static void e(String str, String str2) {
        e(str, str2, false, false);
    }

    public static void e(String str, String str2, boolean z, boolean z2) {
        DJILogHelper.getInstance().LOGE(str, str2, z, z2);
    }

    public static void i(String str, String str2) {
        i(str, str2, false, false);
    }

    public static void i(String str, String str2, boolean z, boolean z2) {
        DJILogHelper.getInstance().LOGI(str, str2, z, z2);
    }

    public static String exceptionToString(Exception exception) {
        if (exception == null) {
            return null;
        }
        try {
            Writer stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            return DebugFile.EOL + stringWriter.toString() + DebugFile.EOL;
        } catch (Exception e) {
            return "bad getErrorInfoFromException";
        }
    }

    public static String getCurrentStack() {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("    " + stackTraceElement.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
