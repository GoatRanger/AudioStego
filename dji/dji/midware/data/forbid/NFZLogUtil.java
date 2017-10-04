package dji.midware.data.forbid;

import dji.log.DJILogHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NFZLogUtil {
    private static boolean DEBUG = false;
    private static final String NFZ_SAVE_DIR = "NFZ";
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void LOGD(String str) {
        if (DEBUG) {
            DJILogHelper.getInstance().LOGD("nfz", str, true, true);
        }
    }

    public static void savedLOG(String str) {
        if (DEBUG) {
            DJILogHelper.getInstance().NFZSavedLOGE("nfz", df.format(new Date()) + ":  " + str, true, true);
        }
    }

    public static void savedLOGD(String str) {
        DJILogHelper.getInstance().LOGD("nfz", df.format(new Date()) + ":  " + str, NFZ_SAVE_DIR);
    }
}
