package dji.pilot.set;

import android.content.Context;
import android.content.Intent;
import dji.pilot.set.a.a;
import dji.pilot.set.longan.SetActivity;
import dji.thirdparty.a.c;

public class SetProxy {
    public static void showSetActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SetActivity.class);
        context.startActivity(intent);
    }

    public static void notifyConfigChange(String str) {
        c.a().e(new a(str));
    }
}
