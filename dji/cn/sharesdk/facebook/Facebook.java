package cn.sharesdk.facebook;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.d;
import com.facebook.internal.ab;
import com.facebook.internal.af;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class Facebook extends Platform {
    public static final String NAME = Facebook.class.getSimpleName();
    private String a;
    private String b;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
    }

    public Facebook(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo("ConsumerKey");
        this.b = getDevinfo("RedirectUrl");
    }

    public int getPlatformId() {
        return 10;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("api_key", "ConsumerKey");
        this.b = getNetworkDevinfo("redirect_uri", "RedirectUrl");
        if (TextUtils.isEmpty(this.b)) {
            this.b = af.t;
        }
    }

    protected void doAuthorize(String[] strArr) {
        final d a = d.a((Platform) this);
        a.a(this.a);
        a.d(this.b);
        a.a(strArr);
        a.a(new AuthorizeListener(this) {
            final /* synthetic */ Facebook b;

            public void onError(Throwable th) {
                if (this.b.listener != null) {
                    this.b.listener.onError(this.b, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString(MobileRegisterActivity.RESPONSE_OAUTH_TOKEN);
                int i = bundle.getInt("oauth_token_expires");
                if (i == 0) {
                    try {
                        i = R.parseInt(String.valueOf(bundle.get("expires_in")));
                    } catch (Throwable th) {
                        d.a().d(th);
                        i = 0;
                    }
                }
                if (TextUtils.isEmpty(string)) {
                    string = bundle.getString("access_token");
                }
                this.b.db.putToken(string);
                this.b.db.putExpiresIn((long) i);
                a.a(string, String.valueOf(i));
                this.b.afterRegister(1, null);
            }

            public void onCancel() {
                if (this.b.listener != null) {
                    this.b.listener.onCancel(this.b, 1);
                }
            }
        }, isSSODisable());
    }

    public boolean isClientValid() {
        d a = d.a((Platform) this);
        a.a(this.a);
        return a.b();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid()) {
            d a = d.a((Platform) this);
            a.a(this.a);
            String token = this.db.getToken();
            String valueOf = String.valueOf(this.db.getExpiresIn());
            if (!(token == null || valueOf == null)) {
                a.a(token, valueOf);
                if (a.a()) {
                    return true;
                }
            }
        } else if ((obj instanceof cn.sharesdk.framework.Platform.ShareParams) && ((cn.sharesdk.framework.Platform.ShareParams) obj).getShareType() == 4) {
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        d a = d.a((Platform) this);
        a.a(this.a);
        try {
            String shortLintk = getShortLintk(shareParams.getText(), false);
            String imagePath = shareParams.getImagePath();
            Object imageUrl = shareParams.getImageUrl();
            CharSequence titleUrl = shareParams.getTitleUrl();
            CharSequence url = shareParams.getUrl();
            if (shareParams.getShareType() != 4 || (TextUtils.isEmpty(titleUrl) && TextUtils.isEmpty(url))) {
                HashMap b;
                if (!TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
                    b = a.b(shortLintk, imagePath);
                } else if (TextUtils.isEmpty(imageUrl)) {
                    b = a.b(shortLintk);
                } else {
                    File file = new File(BitmapHelper.downloadBitmap(this.context, imageUrl));
                    if (file.exists()) {
                        b = a.b(shortLintk, file.getAbsolutePath());
                    } else {
                        b = a.b(shortLintk);
                    }
                }
                if (b == null || b.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable("response is null"));
                        return;
                    }
                    return;
                } else if (b.containsKey(ab.an) || b.containsKey("error")) {
                    if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable(new Hashon().fromHashMap(b)));
                        return;
                    }
                    return;
                } else if (this.listener != null) {
                    b.put("ShareParams", shareParams);
                    this.listener.onComplete(this, 9, b);
                    return;
                } else {
                    return;
                }
            }
            if (TextUtils.isEmpty(imageUrl) && !TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
                shareParams.setImageUrl(uploadImageToFileServer(imagePath));
            }
            a.a(shareParams, new PlatformActionListener(this) {
                final /* synthetic */ Facebook b;

                public void onError(Platform platform, int i, Throwable th) {
                    if (this.b.listener != null) {
                        this.b.listener.onError(this.b, 9, th);
                    }
                }

                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    if (this.b.listener != null) {
                        hashMap.put("ShareParams", shareParams);
                        this.b.listener.onComplete(this.b, 9, hashMap);
                    }
                }

                public void onCancel(Platform platform, int i) {
                    if (this.b.listener != null) {
                        this.b.listener.onCancel(this.b, 9);
                    }
                }
            });
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    protected void userInfor(String str) {
        HashMap c = d.a((Platform) this).c(str);
        if (c == null || c.size() <= 0) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable("response is null"));
            }
        } else if (!c.containsKey(ab.an) && !c.containsKey("error")) {
            if (str == null) {
                HashMap hashMap;
                String[] split;
                ArrayList arrayList;
                HashMap hashMap2;
                String fromHashMap;
                this.db.putUserId(String.valueOf(c.get("id")));
                this.db.put(dji.pilot.usercenter.protocol.d.D, String.valueOf(c.get("name")));
                this.db.put(n.aG, n.aY.equals(String.valueOf(c.get(n.aG))) ? "0" : "1");
                this.db.put("token_for_business", (String) c.get("token_for_business"));
                if (c.containsKey("picture")) {
                    hashMap = (HashMap) c.get("picture");
                } else {
                    hashMap = null;
                }
                if (hashMap != null) {
                    hashMap = (HashMap) hashMap.get("data");
                    if (hashMap != null) {
                        this.db.put(MessageKey.MSG_ICON, String.valueOf(hashMap.get("url")));
                    }
                }
                try {
                    if (c.containsKey("birthday")) {
                        split = String.valueOf(c.get("birthday")).split(dji.pilot.usercenter.protocol.d.t);
                        Calendar instance = Calendar.getInstance();
                        instance.set(1, R.parseInt(split[2]));
                        instance.set(2, R.parseInt(split[0]) - 1);
                        instance.set(5, R.parseInt(split[1]));
                        this.db.put("birthday", String.valueOf(instance.getTimeInMillis()));
                    }
                } catch (Throwable th) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, th);
                        return;
                    }
                    return;
                }
                this.db.put("secretType", "true".equals(String.valueOf(c.get("verified"))) ? "1" : "0");
                this.db.put("snsUserUrl", String.valueOf(c.get("link")));
                this.db.put("resume", String.valueOf(c.get("link")));
                if (c.containsKey("education")) {
                    arrayList = (ArrayList) c.get("education");
                } else {
                    arrayList = null;
                }
                if (arrayList != null) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        hashMap = (HashMap) it.next();
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("school_type", Integer.valueOf(0));
                        if (hashMap.containsKey("school")) {
                            hashMap2 = (HashMap) hashMap.get("school");
                        } else {
                            hashMap2 = null;
                        }
                        if (hashMap2 != null) {
                            hashMap3.put("school", String.valueOf(hashMap2.get("name")));
                        }
                        try {
                            if (hashMap.containsKey("year")) {
                                hashMap = (HashMap) hashMap.get("year");
                            } else {
                                hashMap = null;
                            }
                            hashMap3.put("year", Integer.valueOf(R.parseInt(String.valueOf(hashMap.get("name")))));
                        } catch (Throwable th2) {
                            d.a().d(th2);
                        }
                        hashMap3.put("background", Integer.valueOf(0));
                        arrayList2.add(hashMap3);
                    }
                    hashMap = new HashMap();
                    hashMap.put("list", arrayList2);
                    fromHashMap = new Hashon().fromHashMap(hashMap);
                    this.db.put("educationJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                }
                arrayList = c.containsKey("work") ? (ArrayList) c.get("work") : null;
                if (arrayList != null) {
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        hashMap = (HashMap) it2.next();
                        HashMap hashMap4 = new HashMap();
                        hashMap2 = (HashMap) hashMap.get("employer");
                        if (hashMap2 != null) {
                            hashMap4.put(n.aP, String.valueOf(hashMap2.get("name")));
                        }
                        hashMap2 = (HashMap) hashMap.get("position");
                        if (hashMap2 != null) {
                            hashMap4.put("position", String.valueOf(hashMap2.get("name")));
                        }
                        try {
                            String[] split2 = String.valueOf(hashMap.get("start_date")).split("-");
                            hashMap4.put("start_date", Integer.valueOf(R.parseInt(split2[1]) + (R.parseInt(split2[0]) * 100)));
                        } catch (Throwable th3) {
                            d.a().d(th3);
                        }
                        try {
                            split = String.valueOf(hashMap.get("end_date")).split("-");
                            hashMap4.put("end_date", Integer.valueOf(R.parseInt(split[1]) + (R.parseInt(split[0]) * 100)));
                        } catch (Throwable th22) {
                            d.a().d(th22);
                            hashMap4.put("end_date", Integer.valueOf(0));
                        }
                        arrayList3.add(hashMap4);
                    }
                    hashMap = new HashMap();
                    hashMap.put("list", arrayList3);
                    fromHashMap = new Hashon().fromHashMap(hashMap);
                    this.db.put("workJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                }
            }
            if (this.listener != null) {
                this.listener.onComplete(this, 8, c);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(c)));
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        if (hashMap != null) {
            Object titleUrl;
            if (hashMap != null && hashMap.containsKey("source")) {
                aVar.d.add(String.valueOf(hashMap.get("source")));
            } else if (4 == shareParams.getShareType()) {
                aVar.d.add(shareParams.getImageUrl());
                titleUrl = shareParams.getTitleUrl();
                if (TextUtils.isEmpty(titleUrl)) {
                    titleUrl = shareParams.getUrl();
                }
                aVar.c.add(titleUrl);
            }
            titleUrl = hashMap.get(com.facebook.share.internal.n.q);
            aVar.a = titleUrl == null ? null : String.valueOf(titleUrl);
            aVar.g = hashMap;
        }
        return aVar;
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

    protected void getFriendList(int i, int i2, String str) {
        try {
            HashMap a = d.a((Platform) this).a(i, i2 * i, str);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable("response is null"));
                }
            } else if (a.containsKey(ab.an) || a.containsKey("error")) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(a)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 2, a);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 2, th);
            }
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = d.a((Platform) this).a(str, str2, hashMap, hashMap2);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable("response is null"));
                }
            } else if (a.containsKey(ab.an) || a.containsKey("error")) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, i, a);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, i, th);
            }
        }
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        try {
            HashMap a = d.a((Platform) this).a(i, i2, str);
            if (!(a == null || a.size() <= 0 || a.containsKey(ab.an) || a.containsKey("error"))) {
                a.put("current_limit", Integer.valueOf(i));
                a.put("current_cursor", Integer.valueOf(i2));
                hashMap = filterFriendshipInfo(2, a);
            }
        } catch (Throwable th) {
            d.a().d(th);
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("data");
        if (obj == null) {
            return null;
        }
        HashMap<String, Object> hashMap2 = new HashMap();
        hashMap2.put("type", "FOLLOWING");
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int intValue = ((Integer) hashMap.get("current_cursor")).intValue();
        int intValue2 = ((Integer) hashMap.get("current_limit")).intValue();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                String[] split;
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put(dji.pilot.usercenter.protocol.d.D, String.valueOf(hashMap3.get("name")));
                hashMap4.put(n.aG, n.aY.equals(String.valueOf(hashMap3.get(n.aG))) ? "0" : "1");
                hashMap4.put("secretType", "true".equals(String.valueOf(hashMap3.get("verified"))) ? "1" : "0");
                hashMap4.put("snsUserUrl", String.valueOf(hashMap3.get("link")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("link")));
                HashMap hashMap5 = hashMap3.containsKey("picture") ? (HashMap) hashMap3.get("picture") : null;
                if (hashMap5 != null) {
                    hashMap5 = hashMap5.containsKey("data") ? (HashMap) hashMap5.get("data") : null;
                    if (hashMap5 != null) {
                        hashMap4.put(MessageKey.MSG_ICON, String.valueOf(hashMap5.get("url")));
                    }
                }
                try {
                    if (hashMap3.containsKey("birthday")) {
                        split = String.valueOf(hashMap3.get("birthday")).split(dji.pilot.usercenter.protocol.d.t);
                        Calendar instance = Calendar.getInstance();
                        instance.set(1, R.parseInt(split[2]));
                        instance.set(2, R.parseInt(split[0]) - 1);
                        instance.set(5, R.parseInt(split[1]));
                        hashMap4.put("birthday", String.valueOf(instance.getTimeInMillis()));
                    }
                } catch (Throwable th) {
                    d.a().d(th);
                }
                ArrayList arrayList3 = hashMap3.containsKey("education") ? (ArrayList) hashMap3.get("education") : null;
                if (arrayList3 != null) {
                    ArrayList arrayList4 = new ArrayList();
                    Iterator it2 = arrayList3.iterator();
                    while (it2.hasNext()) {
                        hashMap5 = (HashMap) it2.next();
                        HashMap hashMap6 = new HashMap();
                        hashMap6.put("school_type", Integer.valueOf(0));
                        HashMap hashMap7 = (HashMap) hashMap5.get("school");
                        if (hashMap7 != null) {
                            hashMap6.put("school", String.valueOf(hashMap7.get("name")));
                        }
                        try {
                            hashMap6.put("year", Integer.valueOf(R.parseInt(String.valueOf(((HashMap) hashMap5.get("year")).get("name")))));
                        } catch (Throwable th2) {
                            d.a().d(th2);
                        }
                        hashMap6.put("background", Integer.valueOf(0));
                        arrayList4.add(hashMap6);
                    }
                    hashMap5 = new HashMap();
                    hashMap5.put("list", arrayList4);
                    String fromHashMap = new Hashon().fromHashMap(hashMap5);
                    hashMap4.put("educationJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                }
                arrayList2 = hashMap3.containsKey("work") ? (ArrayList) hashMap3.get("work") : null;
                if (arrayList2 != null) {
                    ArrayList arrayList5 = new ArrayList();
                    Iterator it3 = arrayList2.iterator();
                    while (it3.hasNext()) {
                        hashMap3 = (HashMap) it3.next();
                        HashMap hashMap8 = new HashMap();
                        hashMap5 = (HashMap) hashMap3.get("employer");
                        if (hashMap5 != null) {
                            hashMap8.put(n.aP, String.valueOf(hashMap5.get("name")));
                        }
                        hashMap5 = (HashMap) hashMap3.get("position");
                        if (hashMap5 != null) {
                            hashMap8.put("position", String.valueOf(hashMap5.get("name")));
                        }
                        try {
                            split = String.valueOf(hashMap3.get("start_date")).split("-");
                            hashMap8.put("start_date", Integer.valueOf(R.parseInt(split[1]) + (R.parseInt(split[0]) * 100)));
                        } catch (Throwable th22) {
                            d.a().d(th22);
                        }
                        try {
                            String[] split2 = String.valueOf(hashMap3.get("end_date")).split("-");
                            hashMap8.put("end_date", Integer.valueOf(R.parseInt(split2[1]) + (R.parseInt(split2[0]) * 100)));
                        } catch (Throwable th3) {
                            d.a().d(th3);
                            hashMap8.put("end_date", Integer.valueOf(0));
                        }
                        arrayList5.add(hashMap8);
                    }
                    hashMap3 = new HashMap();
                    hashMap3.put("list", arrayList5);
                    String fromHashMap2 = new Hashon().fromHashMap(hashMap3);
                    hashMap4.put("workJSONArrayStr", fromHashMap2.substring(8, fromHashMap2.length() - 1));
                }
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        fromHashMap2 = "_false";
        if (intValue2 >= arrayList.size()) {
            fromHashMap2 = "_true";
        }
        hashMap2.put("nextCursor", (arrayList.size() + intValue) + fromHashMap2);
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
