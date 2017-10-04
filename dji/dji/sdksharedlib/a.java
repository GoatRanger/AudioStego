package dji.sdksharedlib;

import android.content.Context;
import dji.midware.b;

public class a {
    public final String a = "DJISDKSharedLib";

    public static a getInstance() {
        return a.a();
    }

    protected a() {
    }

    public void a(Context context) {
        b.a(context);
        DJISDKCache.getInstance().init();
    }

    public void a() {
        DJISDKCache.getInstance().destroy();
    }
}
