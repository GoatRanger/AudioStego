package cn.sharesdk.tumblr;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Tumblr extends Platform {
    public static final String NAME = Tumblr.class.getSimpleName();
    private String a;
    private String b;
    private String c;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String title;
        @Deprecated
        public String url;
    }

    public Tumblr(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo("OAuthConsumerKey");
        this.b = getDevinfo("SecretKey");
        this.c = getDevinfo("CallbackUrl");
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected int getPlatformId() {
        return 17;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("consumer_key", "OAuthConsumerKey");
        this.b = getNetworkDevinfo("consumer_secret", "SecretKey");
        this.c = getNetworkDevinfo("callback_url", "CallbackUrl");
    }

    protected void doAuthorize(String[] strArr) {
        final b a = b.a((Platform) this);
        a.a(this.a, this.b, this.c);
        a.a(new AuthorizeListener(this) {
            final /* synthetic */ Tumblr b;

            public void onComplete(Bundle bundle) {
                String string = bundle.getString(MobileRegisterActivity.RESPONSE_OAUTH_TOKEN);
                String string2 = bundle.getString("oauth_token_secret");
                this.b.db.putToken(string);
                this.b.db.putTokenSecret(string2);
                a.a(string, string2);
                this.b.afterRegister(1, null);
            }

            public void onError(Throwable th) {
                if (this.b.listener != null) {
                    this.b.listener.onError(this.b, 1, th);
                }
            }

            public void onCancel() {
                if (this.b.listener != null) {
                    this.b.listener.onCancel(this.b, 1);
                }
            }
        });
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid()) {
            b a = b.a((Platform) this);
            a.a(this.a, this.b, this.c);
            String token = this.db.getToken();
            String tokenSecret = this.db.getTokenSecret();
            if (!(token == null || tokenSecret == null)) {
                a.a(token, tokenSecret);
                return true;
            }
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        b a = b.a((Platform) this);
        String shortLintk = getShortLintk(shareParams.getText(), false);
        try {
            HashMap a2;
            Object imageUrl = shareParams.getImageUrl();
            Object imagePath = shareParams.getImagePath();
            if (TextUtils.isEmpty(imagePath) && TextUtils.isEmpty(imageUrl)) {
                a2 = a.a(null, null, null, null, null, null, shareParams.getTitle(), shortLintk);
            } else {
                a2 = a.a(null, null, null, null, null, null, shortLintk, shareParams.getUrl(), imageUrl, imagePath);
            }
            if (a2 != null) {
                a2.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, a2);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("response is null"));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    protected void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    protected void userInfor(String str) {
        try {
            HashMap b = b.a((Platform) this).b(str);
            HashMap hashMap = (HashMap) b.get("response");
            if (hashMap != null) {
                hashMap = (HashMap) hashMap.get("user");
                if (hashMap != null) {
                    this.db.putUserId(String.valueOf(hashMap.get("name")));
                    this.db.put(d.D, String.valueOf(hashMap.get("name")));
                    this.db.put(n.aG, "2");
                    this.db.put("secretType", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
                    this.db.put("favouriteCount", String.valueOf(hashMap.get("following")));
                    ArrayList arrayList = (ArrayList) hashMap.get("blogs");
                    if (arrayList != null && arrayList.size() > 0) {
                        hashMap = (HashMap) arrayList.get(0);
                        this.db.put("url", String.valueOf(hashMap.get("url")));
                        this.db.put("snsUserUrl", String.valueOf(hashMap.get("url")));
                        this.db.put("shareCount", String.valueOf(hashMap.get("posts")));
                    }
                }
            }
            if (this.listener != null) {
                this.listener.onComplete(this, 8, b);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 8, th);
            }
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = b.a((Platform) this).a(str, str2, hashMap, hashMap2);
            if (this.listener != null) {
                this.listener.onComplete(this, i, a);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, i, th);
            }
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        if (hashMap != null) {
            CharSequence imageUrl = shareParams.getImageUrl();
            CharSequence imagePath = shareParams.getImagePath();
            if (!TextUtils.isEmpty(imageUrl)) {
                aVar.d.add(imageUrl);
            } else if (!TextUtils.isEmpty(imagePath)) {
                aVar.e.add(imagePath);
            }
        }
        HashMap hashMap2 = (HashMap) hashMap.get("response");
        if (hashMap2 != null) {
            aVar.a = String.valueOf(hashMap2.get("id"));
        }
        aVar.g = hashMap;
        return aVar;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserName();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap a = b.a((Platform) this).a(str, i, i2);
            if (a == null) {
                return null;
            }
            a = (HashMap) a.get("response");
            if (a != null) {
                a.put("current_cursor", Integer.valueOf(i2));
                return filterFriendshipInfo(11, a);
            }
            return null;
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
        }
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap();
        hashMap2.put("type", "FOLLOWERS");
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_users")));
        if (parseInt2 == 0) {
            return null;
        }
        Object obj = hashMap.get("users");
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put(n.aG, "2");
                hashMap4.put(d.D, String.valueOf(hashMap3.get("name")));
                hashMap4.put("snsUserUrl", String.valueOf(hashMap3.get("url")));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        int size = arrayList.size() + parseInt;
        hashMap2.put("nextCursor", size >= parseInt2 ? parseInt2 + "_true" : size + "_false");
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
