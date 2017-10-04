package cn.sharesdk.tumblr;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.a;
import cn.sharesdk.framework.utils.d;
import com.facebook.share.internal.n;
import com.google.api.client.http.HttpMethods;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import dji.pilot2.publics.b.a$i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class b extends cn.sharesdk.framework.b {
    private static b b;
    private a c = new a();
    private cn.sharesdk.framework.a.a d = cn.sharesdk.framework.a.a.a();

    public static b a(Platform platform) {
        if (b == null) {
            b = new b(platform);
        }
        return b;
    }

    private b(Platform platform) {
        super(platform);
    }

    public void a(String str, String str2, String str3) {
        this.c.a(str, str2, str3);
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return new a(eVar);
    }

    public String getAuthorizeUrl() {
        try {
            String str = "https://www.tumblr.com/oauth/request_token";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_callback", getRedirectUri()));
            a(null, null);
            String a = this.d.a(str, arrayList, null, this.c.a(this.c.a(str, arrayList)), "/oauth/request_token", c());
            if (a == null) {
                return null;
            }
            String[] split = a.split(com.alipay.sdk.h.a.b);
            HashMap hashMap = new HashMap();
            for (String str2 : split) {
                if (str2 != null) {
                    String[] split2 = str2.split("=");
                    if (split2.length >= 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
            if (hashMap.containsKey(MobileRegisterActivity.RESPONSE_OAUTH_TOKEN)) {
                a = (String) hashMap.get(MobileRegisterActivity.RESPONSE_OAUTH_TOKEN);
                a(a, (String) hashMap.get("oauth_token_secret"));
                ShareSDK.logApiEvent("/oauth/authorize", c());
                return "https://www.tumblr.com/oauth/authorize?oauth_token=" + a;
            }
            return null;
        } catch (Throwable th) {
            d.a().d(th);
        }
    }

    public void a(String str, String str2) {
        this.c.a(str, str2);
    }

    public String getRedirectUri() {
        return this.c.a().e;
    }

    public String a(String str) {
        try {
            String str2 = "https://www.tumblr.com/oauth/access_token";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_verifier", str));
            return this.d.a(str2, arrayList, null, this.c.a(this.c.a(str2, arrayList)), "/oauth/access_token", c());
        } catch (Throwable th) {
            d.a().d(th);
            return null;
        }
    }

    public void a(AuthorizeListener authorizeListener) {
        b(authorizeListener);
    }

    public HashMap<String, Object> b(String str) throws Throwable {
        String str2 = "https://api.tumblr.com/v2/user/info";
        ArrayList arrayList = new ArrayList();
        String a = this.d.a(str2, arrayList, this.c.a(this.c.b(str2, arrayList)), null, "/user/info", c());
        if (a == null) {
            throw new Throwable("Response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("Response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        }
        int parseInt;
        try {
            parseInt = R.parseInt(String.valueOf(hashMap.get("status")));
        } catch (Throwable th) {
            d.a().d(th);
            parseInt = 0;
        }
        if (200 == parseInt) {
            return fromJson;
        }
        throw new Throwable(a);
    }

    public HashMap<String, Object> a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) throws Throwable {
        ArrayList a = a("text", str, str2, str3, str4, str5, str6);
        if (!TextUtils.isEmpty(str7)) {
            a.add(new KVPair("title", str7));
        }
        a.add(new KVPair("body", str8));
        return a(a, null, this.c.a(this.c.a(a(), a)));
    }

    public HashMap<String, Object> a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) throws Throwable {
        if (TextUtils.isEmpty(str9) && TextUtils.isEmpty(str10)) {
            throw new Throwable("imagePath or imageUrl not found");
        }
        ArrayList a = a("photo", str, str2, str3, str4, str5, str6);
        if (!TextUtils.isEmpty(str7)) {
            a.add(new KVPair(n.am, str7));
        }
        if (!TextUtils.isEmpty(str8)) {
            a.add(new KVPair("link", str8));
        }
        String a2 = a();
        if (TextUtils.isEmpty(str9)) {
            KVPair kVPair = new KVPair("data", str10);
            ArrayList a3 = this.c.a(this.c.a(a2, a));
            a3.remove(1);
            return a(a, kVPair, a3);
        }
        a.add(new KVPair("source", str9));
        return a(a, null, this.c.a(this.c.a(a2, a)));
    }

    private ArrayList<KVPair<String>> a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        ArrayList<KVPair<String>> arrayList = new ArrayList();
        arrayList.add(new KVPair("type", str));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new KVPair("state", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new KVPair("tags", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new KVPair("tweet", str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            arrayList.add(new KVPair("date", str5));
        }
        if (!TextUtils.isEmpty(str6)) {
            arrayList.add(new KVPair("format", str6));
        }
        if (!TextUtils.isEmpty(str7)) {
            arrayList.add(new KVPair("slug", str7));
        }
        return arrayList;
    }

    private String a() {
        return "https://api.tumblr.com/v2/blog/" + getPlatform().getDb().getUserId() + ".tumblr.com/post";
    }

    private HashMap<String, Object> a(ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2) throws Throwable {
        String a = this.d.a(a(), (ArrayList) arrayList, (KVPair) kVPair, (ArrayList) arrayList2, "/post", c());
        if (a == null) {
            throw new Throwable("Response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("Response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        }
        int parseInt;
        try {
            parseInt = R.parseInt(String.valueOf(hashMap.get("status")));
        } catch (Throwable th) {
            d.a().d(th);
            parseInt = 0;
        }
        if (dji.pilot.usercenter.f.d.y == parseInt) {
            return fromJson;
        }
        throw new Throwable(a);
    }

    public HashMap<String, Object> a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        HashMap<String, Object> hashMap3 = null;
        if (str2 != null) {
            KVPair kVPair;
            String httpGet;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("api_key", this.c.a().a));
            if (hashMap != null && hashMap.size() > 0) {
                for (Entry entry : hashMap.entrySet()) {
                    arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
            if (hashMap2 == null || hashMap2.size() <= 0) {
                kVPair = null;
            } else {
                HashMap<String, Object> hashMap4 = null;
                for (Entry entry2 : hashMap2.entrySet()) {
                    Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
                }
                kVPair = hashMap4;
            }
            if (HttpMethods.GET.equals(str2.toUpperCase())) {
                httpGet = this.d.httpGet(str, arrayList, this.c.a(this.c.b(str, arrayList)), null);
            } else if (HttpMethods.POST.equals(str2.toUpperCase())) {
                ArrayList a;
                if (hashMap2 == null || hashMap2.size() <= 0) {
                    a = this.c.a(this.c.a(str, arrayList));
                } else {
                    a = this.c.a(this.c.a(str, new ArrayList()));
                    a.remove(1);
                }
                httpGet = this.d.httpPost(str, arrayList, kVPair, a, null);
            } else {
                httpGet = null;
            }
            if (httpGet == null || httpGet.length() <= 0) {
                throw new Throwable("response is null");
            }
            hashMap3 = new Hashon().fromJson(httpGet);
            if (hashMap3 == null || hashMap3.size() <= 0) {
                throw new Throwable(httpGet);
            } else if (hashMap3.containsKey("meta")) {
                HashMap hashMap5 = (HashMap) hashMap3.get("meta");
                if (!(200 == ((Integer) hashMap5.get("status")).intValue() && "OK".equals(hashMap5.get("msg")))) {
                    throw new Throwable(httpGet);
                }
            } else {
                throw new Throwable(httpGet);
            }
        }
        return hashMap3;
    }

    public HashMap<String, Object> a(String str, int i, int i2) throws Throwable {
        String str2 = "https://api.tumblr.com/v2/blog/" + str + ".tumblr.com/followers";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("limit", String.valueOf(i)));
        arrayList.add(new KVPair(ParamKey.OFFSET, String.valueOf(i2)));
        String a = this.d.a(str2, arrayList, this.c.a(this.c.b(str2, arrayList)), null, a$i.ac, c());
        if (a == null) {
            throw new Throwable("Response is empty");
        }
        HashMap<String, Object> fromJson = new Hashon().fromJson(a);
        if (fromJson == null || fromJson.size() <= 0) {
            throw new Throwable("Response is empty");
        }
        HashMap hashMap = (HashMap) fromJson.get("meta");
        if (hashMap == null || hashMap.size() <= 0) {
            throw new Throwable(a);
        }
        int parseInt;
        try {
            parseInt = R.parseInt(String.valueOf(hashMap.get("status")));
        } catch (Throwable th) {
            d.a().d(th);
            parseInt = 0;
        }
        if (200 == parseInt) {
            return fromJson;
        }
        throw new Throwable(a);
    }
}
