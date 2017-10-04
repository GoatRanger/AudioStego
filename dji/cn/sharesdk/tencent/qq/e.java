package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.R;
import dji.publics.b.a.c.h;
import java.io.File;

public class e extends FakeActivity {
    private Platform a;
    private String b;
    private PlatformActionListener c;

    public void a(Platform platform, PlatformActionListener platformActionListener) {
        this.a = platform;
        this.c = platformActionListener;
    }

    public void a(String str) {
        this.b = "tencent" + str;
    }

    public void onCreate() {
        Bundle extras = this.activity.getIntent().getExtras();
        final String string = extras.getString("title");
        final String string2 = extras.getString("titleUrl");
        final String string3 = extras.getString("summary");
        final String string4 = extras.getString("imageUrl");
        final String string5 = extras.getString("musicUrl");
        final String appName = DeviceHelper.getInstance(this.activity).getAppName();
        final String string6 = extras.getString("appId");
        final int i = extras.getInt("hidden");
        String string7 = extras.getString("imagePath");
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string3) && TextUtils.isEmpty(string2) && ((TextUtils.isEmpty(string7) || !new File(string7).exists()) && !TextUtils.isEmpty(string4))) {
            new Thread(this) {
                final /* synthetic */ e i;

                public void run() {
                    String downloadBitmap;
                    try {
                        downloadBitmap = BitmapHelper.downloadBitmap(this.i.activity, string4);
                    } catch (Throwable th) {
                        d.a().d(th);
                        return;
                    }
                    this.i.a(string, string2, string3, string4, downloadBitmap, string5, appName, string6, i);
                }
            }.start();
        } else {
            a(string, string2, string3, string4, string7, string5, appName, string6, i);
        }
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        String b = b(str, str2, str3, str4, str5, str6, str7, str8, i);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(b));
        try {
            int[] a = a();
            d dVar = new d();
            dVar.a(this.b);
            dVar.a(this.a, this.c);
            FakeActivity.registerExecutor(this.b, dVar);
            if (a.length <= 1 || (a[0] < 4 && a[1] < 6)) {
                intent.putExtra("key_request_code", 0);
            }
            intent.putExtra("pkg_name", this.activity.getPackageName());
            this.activity.startActivityForResult(intent, 0);
        } catch (Throwable th) {
            this.activity.finish();
            if (this.c != null) {
                this.c.onError(this.a, 9, th);
            }
        }
    }

    private String b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        String str9;
        String str10 = "mqqapi://share/to_fri?src_type=app&version=1&file_type=news";
        if (!TextUtils.isEmpty(str4)) {
            str10 = str10 + "&image_url=" + Base64.encodeToString(str4.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str5)) {
            str10 = str10 + "&file_data=" + Base64.encodeToString(str5.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str)) {
            str10 = str10 + "&title=" + Base64.encodeToString(str.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str3)) {
            str10 = str10 + "&description=" + Base64.encodeToString(str3.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str7)) {
            if (str7.length() > 20) {
                str7 = str7.substring(0, 20) + "...";
            }
            str10 = str10 + "&app_name=" + Base64.encodeToString(str7.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str8)) {
            str10 = str10 + "&share_id=" + str8;
        }
        if (!TextUtils.isEmpty(str2)) {
            str10 = str10 + "&url=" + Base64.encodeToString(str2.getBytes(), 2);
        }
        if (!TextUtils.isEmpty(str3)) {
            str10 = str10 + "&share_qq_ext_str=" + Base64.encodeToString(str3.getBytes(), 2);
        }
        String str11 = "00";
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str5)) {
            str9 = str10 + "&req_type=" + Base64.encodeToString("5".getBytes(), 2);
        } else if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2)) {
            str9 = str10 + "&req_type=" + Base64.encodeToString("6".getBytes(), 2);
        } else if (TextUtils.isEmpty(str6)) {
            str9 = str10 + "&req_type=" + Base64.encodeToString("1".getBytes(), 2);
        } else {
            str9 = (str10 + "&req_type=" + Base64.encodeToString("2".getBytes(), 2)) + "&audioUrl=" + Base64.encodeToString(str6.getBytes(), 2);
        }
        if (i == 1) {
            str10 = h.j;
        } else {
            str10 = str11;
        }
        return str9 + "&cflag=" + Base64.encodeToString(str10.getBytes(), 2);
    }

    private int[] a() {
        String str;
        try {
            str = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            d.a().d(th);
            str = "0";
        }
        String[] split = str.split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = R.parseInt(split[i]);
            } catch (Throwable th2) {
                d.a().d(th2);
                iArr[i] = 0;
            }
        }
        return iArr;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        finish();
    }
}
