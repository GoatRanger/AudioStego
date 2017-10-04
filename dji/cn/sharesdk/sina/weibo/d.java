package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.b;
import com.google.api.client.http.HttpMethods;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.usercenter.mode.n;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class d extends b {
    private static d b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String[] g = new String[]{"follow_app_official_microblog"};
    private a h = a.a();
    private Context i;

    public static synchronized d a(Platform platform) {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d(platform);
            }
            dVar = b;
        }
        return dVar;
    }

    private d(Platform platform) {
        super(platform);
        this.i = platform.getContext();
    }

    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.g = strArr;
        }
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return new b(eVar);
    }

    public cn.sharesdk.framework.authorize.d getSSOProcessor(c cVar) {
        cn.sharesdk.framework.authorize.d cVar2 = new c(cVar);
        cVar2.a(32973);
        cVar2.a(this.c, this.e, this.g);
        return cVar2;
    }

    public void a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            b(authorizeListener);
        } else {
            a(new SSOListener(this) {
                final /* synthetic */ d b;

                public void onFailed(Throwable th) {
                    cn.sharesdk.framework.utils.d.a().d(th);
                    this.b.b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    try {
                        R.parseLong(bundle.getString("expires_in"));
                        authorizeListener.onComplete(bundle);
                    } catch (Throwable th) {
                        onFailed(th);
                    }
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public String getAuthorizeUrl() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.c));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("redirect_uri", this.e));
        if (this.g != null && this.g.length > 0) {
            arrayList.add(new KVPair("scope", TextUtils.join(",", this.g)));
        }
        arrayList.add(new KVPair("display", dji.pilot2.publics.b.a.r));
        String str = "https://open.weibo.cn/oauth2/authorize?" + R.encodeUrl(arrayList);
        ShareSDK.logApiEvent("/oauth2/authorize", c());
        return str;
    }

    public String getRedirectUri() {
        return TextUtils.isEmpty(this.e) ? dji.pilot.liveshare.Weibo.b.d.REDIRECT_URL : this.e;
    }

    public String a(Context context, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.c));
        arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_CLIENT_SECRET, this.d));
        arrayList.add(new KVPair("redirect_uri", this.e));
        arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_GRANT_TYPE, "authorization_code"));
        arrayList.add(new KVPair("code", str));
        String b = this.h.b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", c());
        ShareSDK.logApiEvent("/oauth2/access_token", c());
        return b;
    }

    public void b(String str) {
        this.f = str;
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.h.a("https://api.weibo.com/2/users/show.json", arrayList, "/2/users/show.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public boolean a() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.sina.weibo");
        intent.setType("image/*");
        ResolveInfo resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            intent = new Intent("android.intent.action.SEND");
            intent.setPackage("com.sina.weibog3");
            intent.setType("image/*");
            resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        }
        if (resolveActivity != null) {
            return true;
        }
        return false;
    }

    public void a(final ShareParams shareParams, final PlatformActionListener platformActionListener) {
        if (shareParams.getImageData() == null && TextUtils.isEmpty(shareParams.getImagePath()) && !TextUtils.isEmpty(shareParams.getImageUrl())) {
            try {
                File file = new File(BitmapHelper.downloadBitmap(this.i, shareParams.getImageUrl()));
                if (file.exists()) {
                    shareParams.setImagePath(file.getAbsolutePath());
                }
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
            }
        }
        Object text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(getPlatform().getShortLintk(text, false));
        }
        AuthorizeListener anonymousClass2 = new AuthorizeListener(this) {
            final /* synthetic */ d c;

            public void onError(Throwable th) {
                if (platformActionListener != null) {
                    platformActionListener.onError(this.c.a, 9, th);
                }
            }

            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(this.c.a, 9, hashMap);
                }
            }

            public void onCancel() {
                if (platformActionListener != null) {
                    platformActionListener.onCancel(this.c.a, 9);
                }
            }
        };
        a aVar = new a();
        aVar.a(this.c);
        aVar.a(shareParams);
        aVar.a(anonymousClass2);
        aVar.show(this.i, null, true);
    }

    public HashMap<String, Object> a(String str, String str2, String str3, float f, float f2) throws Throwable {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new Throwable("weibo content can not be null!");
        } else if (!TextUtils.isEmpty(str3)) {
            return b(str3, str, f, f2);
        } else {
            if (TextUtils.isEmpty(str2)) {
                return a(str, f, f2);
            }
            return a(str, str2, f, f2);
        }
    }

    private HashMap<String, Object> a(String str, String str2, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("status", str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(n.x, String.valueOf(f2)));
        arrayList.add(new KVPair("url", str2));
        String b = this.h.b("https://api.weibo.com/2/statuses/upload_url_text.json", arrayList, "/2/statuses/upload_url_text.json", c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    private HashMap<String, Object> b(String str, String str2, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("status", str2));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(n.x, String.valueOf(f2)));
        KVPair kVPair = new KVPair("pic", str);
        String a = this.h.a("https://api.weibo.com/2/statuses/upload.json", arrayList, kVPair, "/2/statuses/upload.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    private HashMap<String, Object> a(String str, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        arrayList.add(new KVPair("access_token", this.f));
        arrayList.add(new KVPair("status", str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(n.x, String.valueOf(f2)));
        String b = this.h.b("https://api.weibo.com/2/statuses/update.json", arrayList, "/2/statuses/update.json", c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        arrayList.add(new KVPair("access_token", this.f));
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String b = this.h.b("https://api.weibo.com/2/friendships/create.json", arrayList, "/2/friendships/create.json", c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    public HashMap<String, Object> a(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(ParamKey.COUNT, String.valueOf(i)));
        arrayList.add(new KVPair(ParamKey.PAGE, String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/statuses/user_timeline.json", arrayList, "/2/statuses/user_timeline.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> b(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(ParamKey.COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/friends.json", arrayList, "/2/friendships/friends.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> c(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(ParamKey.COUNT, String.valueOf(i)));
        arrayList.add(new KVPair(ParamKey.PAGE, String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/friends/bilateral.json", arrayList, "/2/friendships/friends/bilateral.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> d(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        Object obj = 1;
        try {
            R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(ParamKey.COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/followers.json", arrayList, "/2/friendships/followers.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
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
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            HashMap<String, Object> hashMap3 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = hashMap3;
        }
        try {
            if (HttpMethods.GET.equals(str2.toUpperCase())) {
                httpGet = new NetworkHelper().httpGet(str, arrayList, null, null);
            } else {
                if (HttpMethods.POST.equals(str2.toUpperCase())) {
                    httpGet = new NetworkHelper().httpPost(str, arrayList, kVPair, null, null);
                }
                httpGet = null;
            }
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
        }
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }
}
