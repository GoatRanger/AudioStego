package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.college.b.b;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.HashMap;

public class g {
    private String a;
    private String b;
    private a c = a.a();
    private Platform d;
    private int e;

    public g(Platform platform, int i) {
        this.d = platform;
        this.e = i;
    }

    public void a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public void a(Bundle bundle, AuthorizeListener authorizeListener) {
        String string = bundle.getString("_wxapi_sendauth_resp_url");
        if (!TextUtils.isEmpty(string)) {
            int indexOf = string.indexOf("://oauth?");
            if (indexOf >= 0) {
                string = string.substring(indexOf + 1);
            }
            try {
                a(R.urlToBundle(string).getString("code"), authorizeListener);
            } catch (Throwable th) {
                d.a().d(th);
                if (authorizeListener != null) {
                    authorizeListener.onError(th);
                }
            }
        } else if (authorizeListener != null) {
            authorizeListener.onError(null);
        }
    }

    public void a(final String str, final AuthorizeListener authorizeListener) throws Throwable {
        d.a().d("getAuthorizeToken ==>> " + str, new Object[0]);
        new Thread(this) {
            final /* synthetic */ g c;

            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KVPair(b.o, this.c.a));
                    arrayList.add(new KVPair("secret", this.c.b));
                    arrayList.add(new KVPair("code", str));
                    arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_GRANT_TYPE, "authorization_code"));
                    String a = this.c.c.a("https://api.weixin.qq.com/sns/oauth2/access_token", arrayList, "/sns/oauth2/access_token", this.c.e);
                    if (TextUtils.isEmpty(a)) {
                        authorizeListener.onError(new Throwable("Authorize token is empty"));
                    } else if (!a.contains("errcode")) {
                        this.c.a(a);
                        authorizeListener.onComplete(null);
                    } else if (authorizeListener != null) {
                        authorizeListener.onError(new Throwable(a));
                    }
                } catch (Throwable th) {
                    d.a().d(th);
                }
            }
        }.start();
    }

    public boolean a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(b.o, this.a));
        arrayList.add(new KVPair(Oauth2AccessToken.KEY_REFRESH_TOKEN, this.d.getDb().get(Oauth2AccessToken.KEY_REFRESH_TOKEN)));
        arrayList.add(new KVPair(WBConstants.AUTH_PARAMS_GRANT_TYPE, Oauth2AccessToken.KEY_REFRESH_TOKEN));
        try {
            String a = this.c.a("https://api.weixin.qq.com/sns/oauth2/refresh_token", arrayList, "/sns/oauth2/refresh_token", this.e);
            if (TextUtils.isEmpty(a) || a.contains("errcode")) {
                return false;
            }
            a(a);
            return true;
        } catch (Throwable th) {
            d.a().d(th);
            return false;
        }
    }

    public void a(final PlatformActionListener platformActionListener) throws Throwable {
        new Thread(this) {
            final /* synthetic */ g b;

            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair("access_token", this.b.d.getDb().getToken()));
                arrayList.add(new KVPair("openid", this.b.d.getDb().get("openid")));
                String a = this.b.c.a("https://api.weixin.qq.com/sns/userinfo", arrayList, "/sns/userinfo", this.b.e);
                if (!TextUtils.isEmpty(a)) {
                    d.a().d("getUserInfo ==>>" + a, new Object[0]);
                    HashMap fromJson = new Hashon().fromJson(a);
                    if (!fromJson.containsKey("errcode") || ((Integer) fromJson.get("errcode")).intValue() == 0) {
                        String valueOf;
                        String valueOf2;
                        int parseInt;
                        try {
                            valueOf = String.valueOf(fromJson.get("openid"));
                            valueOf2 = String.valueOf(fromJson.get(dji.pilot.usercenter.protocol.d.D));
                            parseInt = R.parseInt(String.valueOf(fromJson.get("sex")));
                        } catch (Throwable th) {
                            d.a().d(th);
                            return;
                        }
                        String valueOf3 = String.valueOf(fromJson.get(n.A));
                        String valueOf4 = String.valueOf(fromJson.get(n.B));
                        String valueOf5 = String.valueOf(fromJson.get("country"));
                        String valueOf6 = String.valueOf(fromJson.get("headimgurl"));
                        String valueOf7 = String.valueOf(fromJson.get("unionid"));
                        this.b.d.getDb().put(dji.pilot.usercenter.protocol.d.D, valueOf2);
                        if (parseInt == 1) {
                            this.b.d.getDb().put(n.aG, "0");
                        } else if (parseInt == 2) {
                            this.b.d.getDb().put(n.aG, "1");
                        } else {
                            this.b.d.getDb().put(n.aG, "2");
                        }
                        this.b.d.getDb().putUserId(valueOf);
                        this.b.d.getDb().put(MessageKey.MSG_ICON, valueOf6);
                        this.b.d.getDb().put(n.A, valueOf3);
                        this.b.d.getDb().put(n.B, valueOf4);
                        this.b.d.getDb().put("country", valueOf5);
                        this.b.d.getDb().put("openid", valueOf);
                        this.b.d.getDb().put("unionid", valueOf7);
                        platformActionListener.onComplete(this.b.d, 8, fromJson);
                    } else if (platformActionListener != null) {
                        platformActionListener.onError(this.b.d, 8, new Throwable(new Hashon().fromHashMap(fromJson)));
                    }
                } else if (platformActionListener != null) {
                    platformActionListener.onError(this.b.d, 8, new Throwable());
                }
            }
        }.start();
    }

    private void a(String str) {
        d.a().d("wechat getAuthorizeToken ==>>" + str, new Object[0]);
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get("access_token"));
        String valueOf2 = String.valueOf(fromJson.get(Oauth2AccessToken.KEY_REFRESH_TOKEN));
        String valueOf3 = String.valueOf(fromJson.get("expires_in"));
        this.d.getDb().put("openid", String.valueOf(fromJson.get("openid")));
        this.d.getDb().putExpiresIn(Long.valueOf(valueOf3).longValue());
        this.d.getDb().putToken(valueOf);
        this.d.getDb().put(Oauth2AccessToken.KEY_REFRESH_TOKEN, valueOf2);
    }
}
