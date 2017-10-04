package cn.sharesdk.system.text;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.d;
import com.here.posclient.UpdateOptions;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.R;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLConnection;
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

    public void a(String str, String str2, String str3, String str4, ActionListener actionListener) {
        boolean z = true;
        Intent intent;
        if (str4 == null || !new File(str4).exists()) {
            try {
                intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
                intent.setPackage(a());
                intent.putExtra("sms_body", str3);
                intent.setFlags(268435456);
                this.b.startActivity(intent);
            } catch (Throwable th) {
                if (actionListener != null) {
                    actionListener.onError(th);
                }
                z = false;
            }
        } else {
            File file = new File(str4);
            if (UpdateOptions.SOURCE_ANY <= file.length()) {
                try {
                    file = a(file);
                } catch (Throwable th2) {
                    if (actionListener != null) {
                        actionListener.onError(th2);
                        return;
                    }
                    return;
                }
            }
            try {
                this.b.startActivity(a(str, str2, str3, file));
            } catch (Throwable th3) {
                try {
                    this.b.startActivity(b(str, str2, str3, file));
                } catch (Throwable th22) {
                    if (actionListener != null) {
                        actionListener.onError(th22);
                    }
                    z = false;
                }
            }
        }
        final DeviceHelper instance = DeviceHelper.getInstance(this.b);
        final String packageName = this.b.getPackageName();
        final HashMap hashMap = new HashMap();
        hashMap.put("address", str);
        hashMap.put("subject", str2);
        hashMap.put("body", str3);
        hashMap.put("image", str4);
        if (!TextUtils.isEmpty(instance.getTopTaskPackageName())) {
            final ActionListener actionListener2 = actionListener;
            UIHandler.sendEmptyMessageDelayed(0, 2000, new Callback(this) {
                int a = 0;
                final /* synthetic */ a g;

                public boolean handleMessage(Message message) {
                    if (packageName.equals(instance.getTopTaskPackageName())) {
                        if (this.a < 5) {
                            this.a++;
                            UIHandler.sendEmptyMessageDelayed(0, 500, this);
                        }
                    } else if (z && actionListener2 != null) {
                        actionListener2.onStart(new HashMap(hashMap));
                    }
                    return true;
                }
            });
        } else if (z && actionListener != null) {
            actionListener.onStart(hashMap);
        }
    }

    private File a(File file) throws Throwable {
        File file2;
        double length = (((double) file.length()) / 2.147483647E9d) - 1.0d;
        do {
            file2 = new File(file.getParentFile(), "mms_tmp_file.jpg");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            length += 1.0d;
            Bitmap bitmap = BitmapHelper.getBitmap(file, (int) Math.ceil(length));
            if (bitmap == null || bitmap.isRecycled()) {
                throw new RuntimeException("Failed to compress image file");
            }
            OutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
        } while (file2.length() > UpdateOptions.SOURCE_ANY);
        return file2;
    }

    private Intent a(String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND_MSG");
        intent.putExtra("address", str);
        intent.setFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("subject", str2);
        intent.putExtra("sms_body", str3);
        intent.setType("text/plain");
        String absolutePath = file.getAbsolutePath();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(absolutePath);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            contentTypeFor = absolutePath.trim().toLowerCase();
            if (contentTypeFor.endsWith("png")) {
                contentTypeFor = "image/png";
            } else if (contentTypeFor.endsWith("jpg") || contentTypeFor.endsWith("jpeg")) {
                contentTypeFor = "image/jpeg";
            } else if (contentTypeFor.endsWith("gif")) {
                contentTypeFor = "image/gif";
            } else {
                contentTypeFor = "*/*";
            }
        }
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        intent.setType(contentTypeFor);
        return intent;
    }

    private Intent b(String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mms://"));
        intent.putExtra("address", str);
        intent.setFlags(268435456);
        if (str2 != null) {
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("subject", str2);
        }
        if (str3 != null) {
            intent.putExtra("sms_body", str3);
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.setType("text/plain");
        }
        String absolutePath = file.getAbsolutePath();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(absolutePath);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            contentTypeFor = absolutePath.trim().toLowerCase();
            if (contentTypeFor.endsWith("png")) {
                contentTypeFor = "image/png";
            } else if (contentTypeFor.endsWith("jpg") || contentTypeFor.endsWith("jpeg")) {
                contentTypeFor = "image/jpeg";
            } else if (contentTypeFor.endsWith("gif")) {
                contentTypeFor = "image/gif";
            } else {
                contentTypeFor = "*/*";
            }
        }
        if (VERSION.SDK_INT >= 24) {
            intent.putExtra("android.intent.extra.STREAM", R.pathToContentUri(this.b, file.getAbsolutePath()));
        } else {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        }
        intent.setType(contentTypeFor);
        return intent;
    }

    private String a() {
        if (VERSION.SDK_INT < 19) {
            return "com.android.mms";
        }
        try {
            ReflectHelper.importClass("Telephony.Sms", "android.provider.Telephony$Sms");
            return (String) ReflectHelper.invokeStaticMethod("Telephony.Sms", "getDefaultSmsPackage", new Object[]{this.b});
        } catch (Throwable th) {
            Throwable th2 = th;
            String str = "com.android.mms";
            d.a().d(th2);
            return str;
        }
    }
}
