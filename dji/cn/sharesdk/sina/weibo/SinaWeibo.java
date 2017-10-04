package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import com.alipay.sdk.b.b;
import com.facebook.internal.ab;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SinaWeibo extends Platform {
    public static final String NAME = SinaWeibo.class.getSimpleName();
    private String a;
    private String b;
    private String c;
    private boolean d;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public float latitude;
        @Deprecated
        public float longitude;
    }

    public SinaWeibo(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo("AppKey");
        this.b = getDevinfo("AppSecret");
        this.c = getDevinfo("RedirectUrl");
        this.d = "true".equals(getDevinfo("ShareByAppClient"));
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected int getPlatformId() {
        return 1;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo(b.h, "AppKey");
        this.b = getNetworkDevinfo("app_secret", "AppSecret");
        this.c = getNetworkDevinfo("redirect_uri", "RedirectUrl");
    }

    public boolean isClientValid() {
        return d.a((Platform) this).a();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        d a = d.a((Platform) this);
        if (i == 9 && this.d && a.a()) {
            return true;
        }
        if (isAuthValid()) {
            a.a(this.a, this.b);
            a.b(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doAuthorize(String[] strArr) {
        final d a = d.a((Platform) this);
        a.a(this.a, this.b);
        a.a(this.c);
        a.a(strArr);
        a.a(new AuthorizeListener(this) {
            final /* synthetic */ SinaWeibo b;

            public void onComplete(Bundle bundle) {
                long parseLong;
                String string = bundle.getString("uid");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                this.b.db.put(d.D, bundle.getString("userName"));
                this.b.db.put("remind_in", bundle.getString("remind_in"));
                this.b.db.putToken(string2);
                try {
                    parseLong = R.parseLong(string3);
                } catch (Throwable th) {
                    parseLong = 0;
                }
                this.b.db.putExpiresIn(parseLong);
                this.b.db.putUserId(string);
                a.b(string2);
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
        }, isSSODisable());
    }

    protected void follow(String str) {
        try {
            HashMap d = d.a((Platform) this).d(str);
            if (d == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable());
                }
            } else if (!d.containsKey(ab.an) || ((Integer) d.get(ab.an)).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, 6, d);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 6, new Throwable(new Hashon().fromHashMap(d)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, 6, th);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap a = d.a((Platform) this).a(i, i2, str);
                if (a == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 7, new Throwable());
                    }
                } else if (!a.containsKey(ab.an) || ((Integer) a.get(ab.an)).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 7, a);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable(new Hashon().fromHashMap(a)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 7, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void userInfor(String str) {
        Object obj = 1;
        Object obj2 = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
            obj2 = 1;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        } else {
            obj = obj2;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = d.a((Platform) this).c(str);
                if (c == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!c.containsKey(ab.an) || ((Integer) c.get(ab.an)).intValue() == 0) {
                    if (obj != null) {
                        this.db.putUserId(String.valueOf(c.get("id")));
                        this.db.put(d.D, String.valueOf(c.get("screen_name")));
                        this.db.put(MessageKey.MSG_ICON, String.valueOf(c.get("avatar_hd")));
                        if (String.valueOf(c.get("verified")).equals("true")) {
                            this.db.put("secretType", "1");
                        } else {
                            this.db.put("secretType", "0");
                        }
                        this.db.put("secret", String.valueOf(c.get("verified_reason")));
                        String valueOf = String.valueOf(c.get(n.aG));
                        if (valueOf.equals("m")) {
                            this.db.put(n.aG, "0");
                        } else if (valueOf.equals("f")) {
                            this.db.put(n.aG, "1");
                        } else {
                            this.db.put(n.aG, "2");
                        }
                        this.db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(c.get("profile_url")));
                        this.db.put("resume", String.valueOf(c.get("description")));
                        this.db.put("followerCount", String.valueOf(c.get("followers_count")));
                        this.db.put("favouriteCount", String.valueOf(c.get("friends_count")));
                        this.db.put("shareCount", String.valueOf(c.get("statuses_count")));
                        this.db.put("snsregat", String.valueOf(R.dateToLong(String.valueOf(c.get(n.L)))));
                    }
                    if (this.listener != null) {
                        this.listener.onComplete(this, 8, c);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(c)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 8, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap b = d.a((Platform) this).b(i, i2, str);
                if (b == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 2, new Throwable());
                    }
                } else if (!b.containsKey(ab.an) || ((Integer) b.get(ab.an)).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 2, b);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(b)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 2, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = d.a((Platform) this).a(str, str2, (HashMap) hashMap, (HashMap) hashMap2);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable());
                }
            } else if (!a.containsKey(ab.an) || ((Integer) a.get(ab.an)).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, i, a);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, i, th);
        }
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        String string;
        d a;
        String imagePath;
        String imageUrl;
        HashMap a2;
        CharSequence text = shareParams.getText();
        if (TextUtils.isEmpty(text)) {
            int stringRes = R.getStringRes(getContext(), "ssdk_weibo_upload_content");
            if (stringRes > 0) {
                string = getContext().getString(stringRes);
                a = d.a((Platform) this);
                a.a(this.a, this.b);
                imagePath = shareParams.getImagePath();
                imageUrl = shareParams.getImageUrl();
                if (this.d || !a.a()) {
                    float latitude = shareParams.getLatitude();
                    a2 = a.a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude);
                    if (a2 != null) {
                        if (this.listener != null) {
                            this.listener.onError(this, 9, new Throwable());
                        }
                    } else if (a2.containsKey(ab.an) || ((Integer) a2.get(ab.an)).intValue() == 0) {
                        a2.put("ShareParams", shareParams);
                        if (this.listener != null) {
                            this.listener.onComplete(this, 9, a2);
                        }
                    } else if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable(new Hashon().fromHashMap(a2)));
                        return;
                    } else {
                        return;
                    }
                }
                try {
                    a.a(shareParams, this.listener);
                    return;
                } catch (Throwable th) {
                    this.listener.onError(this, 9, th);
                    return;
                }
            }
        }
        CharSequence charSequence = text;
        a = d.a((Platform) this);
        a.a(this.a, this.b);
        imagePath = shareParams.getImagePath();
        imageUrl = shareParams.getImageUrl();
        if (this.d) {
        }
        try {
            float latitude2 = shareParams.getLatitude();
            a2 = a.a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude2);
            if (a2 != null) {
                if (a2.containsKey(ab.an)) {
                }
                a2.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, a2);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable());
            }
        } catch (Throwable th2) {
            this.listener.onError(this, 9, th2);
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        if (hashMap != null) {
            aVar.a = String.valueOf(hashMap.get("id"));
            aVar.d.add(String.valueOf(hashMap.get("original_pic")));
            aVar.g = hashMap;
        }
        return aVar;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap b = d.a((Platform) this).b(i, i2, str);
                if (!(b == null || b.containsKey(ab.an))) {
                    b.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(2, b);
                }
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = d.a((Platform) this).c(i, i2, str);
                if (!(c == null || c.containsKey(ab.an))) {
                    c.put("page_count", Integer.valueOf(i));
                    c.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(10, c);
                }
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(d.D);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap d = d.a((Platform) this).d(i, i2, str);
                if (!(d == null || d.containsKey(ab.an))) {
                    d.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(11, d);
                }
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap();
        switch (i) {
            case 2:
                hashMap2.put("type", "FOLLOWING");
                break;
            case 10:
                hashMap2.put("type", com.facebook.share.internal.n.D);
                break;
            case 11:
                hashMap2.put("type", "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_number")));
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
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put(d.D, String.valueOf(hashMap3.get("screen_name")));
                hashMap4.put(MessageKey.MSG_ICON, String.valueOf(hashMap3.get("avatar_hd")));
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", "1");
                } else {
                    hashMap4.put("secretType", "0");
                }
                hashMap4.put("secret", String.valueOf(hashMap3.get("verified_reason")));
                String valueOf = String.valueOf(hashMap3.get(n.aG));
                if (valueOf.equals("m")) {
                    hashMap4.put(n.aG, "0");
                } else if (valueOf.equals("f")) {
                    hashMap4.put(n.aG, "1");
                } else {
                    hashMap4.put(n.aG, "2");
                }
                hashMap4.put("snsUserUrl", "http://weibo.com/" + String.valueOf(hashMap3.get("profile_url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("description")));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(R.dateToLong(String.valueOf(hashMap3.get(n.L)))));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        if (10 == i) {
            hashMap2.put("nextCursor", ((Integer) hashMap.get("page_count")).intValue() * (parseInt + 1) >= parseInt2 ? parseInt + "_true" : (parseInt + 1) + "_false");
        } else {
            int size = arrayList.size() + parseInt;
            if (size >= parseInt2) {
                obj = parseInt2 + "_true";
            } else {
                obj = size + "_false";
            }
            hashMap2.put("nextCursor", obj);
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
