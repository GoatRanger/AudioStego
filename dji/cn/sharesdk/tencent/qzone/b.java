package cn.sharesdk.tencent.qzone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
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
import com.google.api.client.http.HttpMethods;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class b extends cn.sharesdk.framework.b {
    private static final String[] b = new String[]{"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};
    private static b c;
    private String d;
    private String e;
    private String f;
    private a g = a.a();
    private String[] h;

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
        this.h = strArr;
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
        String[] strArr = this.h == null ? b : this.h;
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

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("openid", this.e));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.g.a("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, null, "/user/get_simple_userinfo", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public void a(String str, String str2, String str3, String str4, String str5, PlatformActionListener platformActionListener) throws Throwable {
        String str6;
        String str7;
        if (TextUtils.isEmpty(str5)) {
            str5 = DeviceHelper.getInstance(this.a.getContext()).getAppName();
        }
        if (str5.length() > 20) {
            str6 = str5.substring(0, 20) + "...";
        } else {
            str6 = str5;
        }
        if (TextUtils.isEmpty(str) || str.length() <= 200) {
            str7 = str;
        } else {
            str7 = str.substring(0, 200);
        }
        b(str7, str2, str3, str4, str6, platformActionListener);
    }

    public void b(String str, String str2, String str3, String str4, String str5, PlatformActionListener platformActionListener) throws Throwable {
        String str6 = "1";
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            str6 = "3";
        } else if (TextUtils.isEmpty(str2)) {
            if (platformActionListener != null) {
                platformActionListener.onError(null, 9, new Throwable("The param of title or titleUrl is null !"));
                return;
            }
            return;
        }
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
        if (!TextUtils.isEmpty(str3) && str3.length() > 600) {
            str3 = str3.substring(0, 600);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (str6 == "3") {
            stringBuilder.append("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        } else {
            stringBuilder.append("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        }
        if (!TextUtils.isEmpty(str4)) {
            stringBuilder.append("&image_url=").append(Base64.encodeToString(str4.getBytes("utf-8"), 2));
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append("&title=").append(Base64.encodeToString(str.getBytes("utf-8"), 2));
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append("&description=").append(Base64.encodeToString(str3.getBytes("utf-8"), 2));
        }
        stringBuilder.append("&share_id=").append(this.d);
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("&url=").append(Base64.encodeToString(str2.getBytes("utf-8"), 2));
        }
        stringBuilder.append("&app_name=").append(Base64.encodeToString(str5.getBytes("utf-8"), 2));
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append("&share_qq_ext_str=").append(Base64.encodeToString(str3.getBytes(), 2));
        }
        stringBuilder.append("&req_type=").append(Base64.encodeToString(str6.getBytes("utf-8"), 2));
        stringBuilder.append("&cflag=").append(Base64.encodeToString((a() ? "1" : "0").getBytes("utf-8"), 2));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuilder.toString()));
        if (this.a.getContext().getPackageManager().resolveActivity(intent, 1) != null) {
            d dVar = new d();
            dVar.a(stringBuilder.toString(), true);
            dVar.a(platformActionListener);
            dVar.a(this.d);
            dVar.show(this.a.getContext(), null);
        }
    }

    public boolean a() {
        CharSequence charSequence;
        try {
            charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            d.a().d(th);
            charSequence = null;
        }
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return true;
    }

    public HashMap<String, Object> a(String str, String str2) throws Throwable {
        String str3 = "https://graph.qq.com/photo/upload_pic";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("openid", this.e));
        arrayList.add(new KVPair("format", "json"));
        if (!TextUtils.isEmpty(str2)) {
            if (str2.length() > 200) {
                str2 = str2.substring(0, 199) + "…";
            }
            arrayList.add(new KVPair("photodesc", str2));
        }
        arrayList.add(new KVPair(dji.pilot2.publics.b.a.r, "1"));
        KVPair kVPair = new KVPair("picture", str);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.g.a(str3, arrayList, kVPair, arrayList2, "/photo/upload_pic", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.g.a("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, null, "/oauth2.0/me", c());
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

    public HashMap<String, Object> a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (str2 == null) {
            return null;
        }
        KVPair kVPair;
        String httpGet;
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("openid", this.e));
        arrayList.add(new KVPair("format", "json"));
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            HashMap<String, Object> hashMap3 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = hashMap3;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        try {
            if (HttpMethods.GET.equals(str2.toUpperCase())) {
                httpGet = new NetworkHelper().httpGet(str, arrayList, arrayList2, null);
            } else {
                if (HttpMethods.POST.equals(str2.toUpperCase())) {
                    httpGet = new NetworkHelper().httpPost(str, arrayList, kVPair, arrayList2, null);
                }
                httpGet = null;
            }
        } catch (Throwable th) {
            d.a().d(th);
        }
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    public HashMap<String, Object> b(String str, String str2) throws Throwable {
        String a;
        Object obj = !TextUtils.isEmpty(str) ? 1 : null;
        String str3 = obj != null ? "/t/add_pic_t" : "/t/add_t";
        String str4 = "https://graph.qq.com" + str3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("openid", this.e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("content", str2));
        if (obj != null) {
            a = this.g.a(str4, arrayList, new KVPair("pic", str), str3, c());
        } else {
            a = this.g.b(str4, arrayList, str3, c());
        }
        if (a == null || a.length() <= 0) {
            return null;
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (((Integer) fromJson.get("ret")).intValue() == 0) {
            return fromJson;
        }
        throw new Throwable(a);
    }
}
