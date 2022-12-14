package cn.sharesdk.facebook;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
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
import com.facebook.GraphRequest;
import com.facebook.internal.ab;
import com.facebook.share.internal.n;
import com.google.api.client.http.HttpMethods;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class d extends b {
    private static final String[] b = new String[]{"user_about_me", "user_birthday", "user_photos", "publish_actions", "user_friends"};
    private static d c;
    private String d;
    private String e;
    private long f;
    private String g;
    private a h = a.a();
    private String[] i;
    private String j;

    public static d a(Platform platform) {
        if (c == null) {
            c = new d(platform);
        }
        return c;
    }

    private d(Platform platform) {
        super(platform);
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(String str, String str2) {
        this.e = str;
        if (str2 != null && !str2.equals("0")) {
            try {
                this.f = System.currentTimeMillis() + ((long) (R.parseInt(str2) * 1000));
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
            }
        }
    }

    public boolean a() {
        return this.e != null && (this.f == 0 || System.currentTimeMillis() < this.f);
    }

    public String getAuthorizeUrl() {
        int i = 0;
        String str = "https://www.facebook.com/dialog/oauth";
        Bundle bundle = new Bundle();
        bundle.putString("display", "touch");
        bundle.putString("redirect_uri", this.j);
        bundle.putString("type", com.alipay.sdk.b.b.b);
        String[] strArr = this.i == null ? b : this.i;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            String str2 = strArr[i];
            if (i2 > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(str2);
            i2++;
            i++;
        }
        bundle.putString("scope", stringBuilder.toString());
        bundle.putString("client_id", this.g);
        bundle.putString("response_type", "code");
        this.d = str + "?" + R.encodeUrl(bundle);
        ShareSDK.logApiEvent("/dialog/oauth", c());
        return this.d;
    }

    public void a(String[] strArr) {
        this.i = strArr;
    }

    public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(e eVar) {
        return new c(eVar);
    }

    public String getRedirectUri() {
        return this.j;
    }

    public boolean b() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", this.g);
        if (this.i != null && this.i.length > 0) {
            intent.putExtra("scope", TextUtils.join(",", this.i));
        }
        ResolveInfo resolveActivity = getPlatform().getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature toCharsString : getPlatform().getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2".equals(toCharsString.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public cn.sharesdk.framework.authorize.d getSSOProcessor(c cVar) {
        cn.sharesdk.framework.authorize.d bVar = new b(cVar);
        bVar.a(32525);
        bVar.a(this.g, this.i == null ? b : this.i);
        return bVar;
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
                    authorizeListener.onComplete(bundle);
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public void a(ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        Object imageUrl = shareParams.getImageUrl();
        Object title = shareParams.getTitle();
        Object text = shareParams.getText();
        Object musicUrl = shareParams.getMusicUrl();
        Object url = shareParams.getUrl();
        String titleUrl = shareParams.getTitleUrl();
        if (!TextUtils.isEmpty(titleUrl)) {
            titleUrl = this.a.getShortLintk(titleUrl, false);
        } else if (!TextUtils.isEmpty(url)) {
            titleUrl = this.a.getShortLintk(url, false);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://www.facebook.com/dialog/feed?");
        stringBuilder.append("app_id=").append(this.g);
        stringBuilder.append("&redirect_uri=fbconnect://success");
        stringBuilder.append("&link=").append(Data.urlEncode(titleUrl, "utf-8"));
        if (!TextUtils.isEmpty(imageUrl)) {
            stringBuilder.append("&picture=").append(Data.urlEncode(imageUrl, "utf-8"));
        }
        if (!TextUtils.isEmpty(title)) {
            stringBuilder.append("&caption=").append(Data.urlEncode(title, "utf-8"));
        }
        if (!TextUtils.isEmpty(text)) {
            stringBuilder.append("&description=").append(Data.urlEncode(text, "utf-8"));
        }
        if (!TextUtils.isEmpty(musicUrl)) {
            stringBuilder.append("&source=").append(Data.urlEncode(musicUrl, "utf-8"));
            if (!TextUtils.isEmpty(text)) {
                stringBuilder.append("&name=").append(Data.urlEncode(text, "utf-8"));
            }
        }
        e eVar = new e();
        eVar.a(stringBuilder.toString());
        eVar.a(platformActionListener);
        eVar.show(this.a.getContext(), null);
    }

    public HashMap<String, Object> b(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("message", str));
        String b = this.h.b("https://graph.facebook.com/v2.5/me/feed", arrayList, "/v2.5/me/feed", c());
        if (b == null || b.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(b);
    }

    public HashMap<String, Object> b(String str, String str2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair(n.am, str));
        String a = this.h.a("https://graph.facebook.com/v2.5/me/photos", arrayList, new KVPair("source", str2), "/v2.5/me/photos", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        String str2 = "/me";
        if (str != null) {
            str2 = dji.pilot.usercenter.protocol.d.t + str;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair(GraphRequest.c, "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,cover,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture,quotes,relationship_status,religion,security_settings,significant_other,video_upload_limits,website,work"));
        str2 = this.h.a("https://graph.facebook.com/v2.5" + str2, arrayList, "get_user_info", c());
        cn.sharesdk.framework.utils.d.a().i("facebook helper getUser", new Object[0]);
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str2);
    }

    public HashMap<String, Object> a(int i, int i2, String str) throws Throwable {
        String str2 = "me";
        if (str != null) {
            str2 = str;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("limit", String.valueOf(i)));
        arrayList.add(new KVPair(ParamKey.OFFSET, String.valueOf(i2)));
        arrayList.add(new KVPair(GraphRequest.c, "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,cover,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture,quotes,relationship_status,religion,security_settings,significant_other,video_upload_limits,website,work"));
        String str3 = "/friends";
        if (!TextUtils.isEmpty(str)) {
            str3 = "/taggable_friends";
        }
        str2 = this.h.a("https://graph.facebook.com/v2.5/" + str2 + str3, arrayList, ab.aC, c());
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str2);
    }

    public HashMap<String, Object> a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        if (str2 == null) {
            return null;
        }
        KVPair kVPair;
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        arrayList.add(new KVPair("access_token", this.e));
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
        String httpGet = HttpMethods.GET.equals(str2.toUpperCase()) ? this.h.httpGet(str, arrayList, null, null) : HttpMethods.POST.equals(str2.toUpperCase()) ? this.h.httpPost(str, arrayList, kVPair, null, null) : null;
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    public void d(String str) {
        this.j = str;
    }
}
