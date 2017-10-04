package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;
import com.alipay.sdk.j.i;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class b extends cn.sharesdk.framework.b {
    private static final String[] b = new String[]{"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};
    private static b c;
    private String d;
    private String[] e;
    private String f;
    private String g;

    public static b a(Platform platform) {
        if (c == null) {
            c = new b(platform);
        }
        return c;
    }

    private b(Platform platform) {
        super(platform);
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(String[] strArr) {
        this.e = strArr;
    }

    public void a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            b(authorizeListener);
        } else {
            a(new SSOListener(this) {
                final /* synthetic */ b b;

                public void onFailed(Throwable th) {
                    this.b.b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    authorizeListener.onComplete(bundle);
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public void b(String str) {
        this.f = str;
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = a.a().a("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, null, "/oauth2.0/me", c());
        if (a.startsWith(com.alipay.sdk.a.a.c)) {
            while (!a.startsWith("{") && a.length() > 0) {
                a = a.substring(1);
            }
            while (!a.endsWith(i.d) && a.length() > 0) {
                a = a.substring(0, a.length() - 1);
            }
        }
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public void d(String str) {
        this.g = str;
    }

    public String getAuthorizeUrl() {
        String urlEncode;
        ShareSDK.logApiEvent("/oauth2.0/authorize", c());
        String b = b();
        try {
            urlEncode = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            d.a().d(th);
            urlEncode = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.d + com.alipay.sdk.h.a.b + "redirect_uri=" + urlEncode + com.alipay.sdk.h.a.b + "display=mobile&" + "scope=" + b;
    }

    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return new a(eVar);
    }

    private String b() {
        int i = 0;
        String[] strArr = this.e == null ? b : this.e;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            String str = strArr[i];
            if (i2 > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(str);
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    public cn.sharesdk.framework.authorize.d getSSOProcessor(c cVar) {
        cn.sharesdk.framework.authorize.d cVar2 = new c(cVar);
        cVar2.a(5656);
        cVar2.a(this.d, b());
        return cVar2;
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("openid", this.f));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = a.a().a("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, null, "/user/get_simple_userinfo", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, boolean z, PlatformActionListener platformActionListener, boolean z2, int i) {
        if (z2) {
            b(str, str2, str3, str4, str5, str6, platformActionListener);
        } else if (z && a()) {
            if (!TextUtils.isEmpty(str4)) {
                File file = new File(str4);
                if (file.exists() && str4.startsWith("/data/")) {
                    String absolutePath = new File(R.getCachePath(this.a.getContext(), "images"), System.currentTimeMillis() + file.getName()).getAbsolutePath();
                    if (R.copyFile(str4, absolutePath)) {
                        str4 = absolutePath;
                    } else {
                        str4 = null;
                    }
                }
            }
            Intent intent = new Intent();
            intent.putExtra("title", str);
            intent.putExtra("titleUrl", str2);
            intent.putExtra("summary", str3);
            intent.putExtra("imagePath", str4);
            intent.putExtra("imageUrl", str5);
            intent.putExtra("musicUrl", str6);
            intent.putExtra("appId", this.d);
            intent.putExtra("hidden", i);
            e eVar = new e();
            eVar.a(this.a, platformActionListener);
            eVar.a(this.d);
            eVar.show(this.a.getContext(), intent);
        } else {
            a(str, str2, str3, str4, str5, str6, platformActionListener);
        }
    }

    public boolean a() {
        CharSequence charSequence = null;
        try {
            charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            try {
                charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.qqlite", 0).versionName;
            } catch (Throwable th2) {
                try {
                    charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.minihd.qq", 0).versionName;
                } catch (Throwable th3) {
                }
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return true;
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) {
        if (str5 == null && str4 != null && new File(str4).exists()) {
            str5 = ((QQ) this.a).uploadImageToFileServer(str4);
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://openmobile.qq.com/api/check?");
            stringBuilder.append("page=shareindex.html&");
            stringBuilder.append("style=9&");
            stringBuilder.append("action=shareToQQ&");
            stringBuilder.append("sdkv=2.2.1&");
            stringBuilder.append("sdkp=a&");
            stringBuilder.append("appId=").append(this.d).append(com.alipay.sdk.h.a.b);
            DeviceHelper instance = DeviceHelper.getInstance(this.a.getContext());
            stringBuilder.append("status_os=").append(Data.urlEncode(instance.getOSVersionName(), "utf-8")).append(com.alipay.sdk.h.a.b);
            stringBuilder.append("status_machine=").append(Data.urlEncode(instance.getModel(), "utf-8")).append(com.alipay.sdk.h.a.b);
            stringBuilder.append("status_version=").append(Data.urlEncode(String.valueOf(instance.getOSVersionInt()), "utf-8")).append(com.alipay.sdk.h.a.b);
            Object appName = instance.getAppName();
            if (!TextUtils.isEmpty(appName)) {
                stringBuilder.append("site=").append(Data.urlEncode(appName, "utf-8")).append(com.alipay.sdk.h.a.b);
            }
            if (!TextUtils.isEmpty(str)) {
                String str7;
                if (str.length() > 40) {
                    str7 = str.substring(40) + "...";
                } else {
                    str7 = str;
                }
                if (str7.length() > 80) {
                    str7 = str7.substring(80) + "...";
                }
                stringBuilder.append("title=").append(Data.urlEncode(str7, "utf-8")).append(com.alipay.sdk.h.a.b);
            }
            if (!TextUtils.isEmpty(str3)) {
                stringBuilder.append("summary=").append(Data.urlEncode(str3, "utf-8")).append(com.alipay.sdk.h.a.b);
            }
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append("targeturl=").append(Data.urlEncode(str2, "utf-8")).append(com.alipay.sdk.h.a.b);
            }
            if (!TextUtils.isEmpty(str5)) {
                stringBuilder.append("imageUrl=").append(Data.urlEncode(str5, "utf-8")).append(com.alipay.sdk.h.a.b);
            }
            stringBuilder.append("type=1");
            f fVar = new f();
            fVar.a(stringBuilder.toString());
            fVar.a(platformActionListener);
            fVar.b(this.d);
            fVar.show(this.a.getContext(), null);
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }

    private void b(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) {
        try {
            Object obj = (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str5)) ? null : 1;
            String str7 = obj == null ? "/t/add_t" : "/t/add_pic_t";
            String str8 = "https://graph.qq.com" + str7;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_consumer_key", this.d));
            arrayList.add(new KVPair("access_token", this.g));
            arrayList.add(new KVPair("openid", this.f));
            arrayList.add(new KVPair("format", "json"));
            arrayList.add(new KVPair("content", str3));
            if (obj != null) {
                if (TextUtils.isEmpty(str4)) {
                    str4 = BitmapHelper.downloadBitmap(this.a.getContext(), str5);
                }
                str8 = a.a().a(str8, arrayList, new KVPair("pic", str4), str7, c());
            } else {
                str8 = a.a().b(str8, arrayList, str7, c());
            }
            if (str8 != null && str8.length() > 0 && platformActionListener != null) {
                HashMap fromJson = new Hashon().fromJson(str8);
                if (((Integer) fromJson.get("ret")).intValue() != 0) {
                    platformActionListener.onError(this.a, 9, new Exception(str8));
                } else {
                    platformActionListener.onComplete(this.a, 9, fromJson);
                }
            }
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }
}
