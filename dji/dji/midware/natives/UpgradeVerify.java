package dji.midware.natives;

import android.util.Log;

public class UpgradeVerify {
    public static native String native_getMD5Input(String str, int i);

    public static native boolean native_verifyCfg(String str, String str2);

    public static native boolean native_verifyFile(String str, String str2);

    static {
        try {
            System.loadLibrary("UpgradeVerify");
            Log.d("UpgradeVerify", "load lib suc");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.d("UpgradeVerify", "Couldn't load lib");
        }
    }

    public static void loadLibrary() {
    }

    public static String native_getMD5Input(String str) {
        return native_getMD5Input(str, 0);
    }
}
