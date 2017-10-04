package cn.sharesdk.instagram;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Instagram extends Platform {
    public static final String NAME = Instagram.class.getSimpleName();
    private String a;
    private String b;
    private String c;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
    }

    public Instagram(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo("ClientId");
        this.b = getDevinfo("ClientSecret");
        this.c = getDevinfo("RedirectUri");
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected int getPlatformId() {
        return 15;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("client_id", "ClientId");
        this.b = getNetworkDevinfo(WBConstants.AUTH_PARAMS_CLIENT_SECRET, "ClientSecret");
        this.c = getNetworkDevinfo("redirect_uri", "RedirectUri");
    }

    protected void doAuthorize(String[] strArr) {
        final b a = b.a((Platform) this);
        a.a(this.a, this.b, this.c);
        a.a(strArr);
        a.a(new AuthorizeListener(this) {
            final /* synthetic */ Instagram b;

            public void onError(Throwable th) {
                if (this.b.listener != null) {
                    this.b.listener.onError(this.b, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("access_token");
                String string2 = bundle.getString("username");
                String string3 = bundle.getString(n.W);
                String string4 = bundle.getString("website");
                String string5 = bundle.getString("profile_picture");
                String string6 = bundle.getString("full_name");
                String string7 = bundle.getString("id");
                this.b.db.putToken(string);
                this.b.db.putUserId(string7);
                this.b.db.put(d.D, string2);
                this.b.db.put("resume", string3);
                this.b.db.put("website", string4);
                this.b.db.put(MessageKey.MSG_ICON, string5);
                this.b.db.put("full_name", string6);
                a.b(string);
                this.b.afterRegister(1, null);
            }

            public void onCancel() {
                if (this.b.listener != null) {
                    this.b.listener.onCancel(this.b, 1);
                }
            }
        });
    }

    public boolean isClientValid() {
        return b.a((Platform) this).a();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        b a = b.a((Platform) this);
        if (i == 9) {
            if (isClientValid()) {
                return true;
            }
            if (this.listener == null) {
                return false;
            }
            this.listener.onError(this, 9, new InstagramClientNotExistException());
            return false;
        } else if (isAuthValid()) {
            a.a(this.a, this.b, this.c);
            a.b(this.db.getToken());
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        PlatformActionListener anonymousClass2 = new PlatformActionListener(this) {
            final /* synthetic */ Instagram b;

            public void onError(Platform platform, int i, Throwable th) {
                if (this.b.listener != null) {
                    this.b.listener.onError(platform, i, th);
                }
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                HashMap hashMap2 = new HashMap();
                if (hashMap != null) {
                    hashMap2.putAll(hashMap);
                }
                hashMap2.put("ShareParams", shareParams);
                if (this.b.listener != null) {
                    this.b.listener.onComplete(platform, i, hashMap2);
                }
            }

            public void onCancel(Platform platform, int i) {
                if (this.b.listener != null) {
                    this.b.listener.onCancel(platform, i);
                }
            }
        };
        b a = b.a((Platform) this);
        String imagePath = shareParams.getImagePath();
        String imageUrl = shareParams.getImageUrl();
        String filePath = shareParams.getFilePath();
        String text = shareParams.getText();
        if ((TextUtils.isEmpty(imagePath) || !new File(imagePath).exists()) && !TextUtils.isEmpty(filePath) && new File(filePath).exists()) {
            imagePath = filePath;
        }
        a.a(imagePath, imageUrl, text, anonymousClass2);
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
        Object obj = null;
        if (str == null || str.length() < 0) {
            str = this.db.getUserId();
            obj = 1;
        }
        if (str != null && str.length() >= 0) {
            try {
                HashMap c = b.a((Platform) this).c(str);
                if (c != null && c.size() > 0) {
                    if (obj != null) {
                        HashMap hashMap = (HashMap) c.get("data");
                        if (hashMap != null && hashMap.size() > 0) {
                            this.db.put("resume", String.valueOf(hashMap.get(n.W)));
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(hashMap.get("profile_picture")));
                            String valueOf = String.valueOf(hashMap.get("username"));
                            this.db.put(d.D, valueOf);
                            this.db.put("snsUserUrl", "http://instagram.com/" + valueOf + "/#");
                            hashMap = (HashMap) hashMap.get("counts");
                            if (hashMap != null && hashMap.size() > 0) {
                                this.db.put("followerCount", String.valueOf(hashMap.get("followed_by")));
                                this.db.put("favouriteCount", String.valueOf(hashMap.get("follows")));
                                this.db.put("shareCount", String.valueOf(hashMap.get("media")));
                            }
                        }
                    }
                    if (this.listener != null) {
                        this.listener.onComplete(this, 8, c);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable());
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("Instagram account is null"));
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        CharSequence imagePath = shareParams.getImagePath();
        CharSequence imageUrl = shareParams.getImageUrl();
        if (!TextUtils.isEmpty(imagePath)) {
            aVar.e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        }
        return aVar;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        try {
            Object userId;
            if (TextUtils.isEmpty(str)) {
                userId = this.db.getUserId();
            } else {
                String str2 = str;
            }
            if (TextUtils.isEmpty(userId)) {
                userId = this.db.get(d.D);
            }
            if (!TextUtils.isEmpty(userId)) {
                HashMap d = b.a((Platform) this).d(userId);
                if (d != null && d.size() > 0) {
                    hashMap = filterFriendshipInfo(2, d);
                }
            }
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        try {
            Object userId;
            if (TextUtils.isEmpty(str)) {
                userId = this.db.getUserId();
            } else {
                String str2 = str;
            }
            if (TextUtils.isEmpty(userId)) {
                userId = this.db.get(d.D);
            }
            if (!TextUtils.isEmpty(userId)) {
                HashMap e = b.a((Platform) this).e(userId);
                if (e != null && e.size() > 0) {
                    hashMap = filterFriendshipInfo(11, e);
                }
            }
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
        }
        return hashMap;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap();
        if (2 == i) {
            hashMap2.put("type", "FOLLOWING");
        } else {
            hashMap2.put("type", "FOLLOWERS");
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        Object obj = hashMap.get("data");
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
                String valueOf = String.valueOf(hashMap3.get("username"));
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put(d.D, valueOf);
                hashMap4.put("snsUserUrl", "http://instagram.com/" + valueOf + "/#");
                hashMap4.put(MessageKey.MSG_ICON, String.valueOf(hashMap3.get("profile_picture")));
                hashMap4.put(n.aG, "2");
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        hashMap2.put("nextCursor", "0_true");
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return false;
    }
}
