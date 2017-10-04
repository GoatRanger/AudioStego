package cn.sharesdk.system.email;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Spanned;
import android.text.TextUtils;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.util.HashMap;

public class a {
    private static a a;
    private Context b;

    public static a a(Context context) {
        if (a == null) {
            a = new a();
        }
        a.b = context;
        return a;
    }

    public void a(String str, String str2, Spanned spanned, String str3, ActionListener actionListener) {
        boolean z = true;
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(268435456);
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("android.intent.extra.TEXT", spanned);
            if (str3 != null) {
                File file = new File(str3);
                if (file.exists()) {
                    if (VERSION.SDK_INT >= 24) {
                        intent.putExtra("android.intent.extra.STREAM", R.pathToContentUri(this.b, str3));
                    } else {
                        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
                    }
                }
            }
            intent.setType("message/rfc822");
            this.b.startActivity(intent);
        } catch (Throwable th) {
            if (actionListener != null) {
                actionListener.onError(th);
            }
            z = false;
        }
        final DeviceHelper instance = DeviceHelper.getInstance(this.b);
        final String packageName = this.b.getPackageName();
        if (!TextUtils.isEmpty(instance.getTopTaskPackageName())) {
            final ActionListener actionListener2 = actionListener;
            UIHandler.sendEmptyMessageDelayed(0, 2000, new Callback(this) {
                int a = 0;
                final /* synthetic */ a f;

                public boolean handleMessage(Message message) {
                    if (packageName.equals(instance.getTopTaskPackageName())) {
                        if (this.a < 5) {
                            this.a++;
                            UIHandler.sendEmptyMessageDelayed(0, 500, this);
                        }
                    } else if (z && actionListener2 != null) {
                        actionListener2.onComplete(new HashMap());
                    }
                    return true;
                }
            });
        } else if (z && actionListener != null) {
            actionListener.onComplete(new HashMap());
        }
    }
}
