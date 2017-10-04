package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.b.b.b;
import cn.sharesdk.framework.b.b.c;
import cn.sharesdk.framework.b.b.f;
import cn.sharesdk.framework.b.d;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import java.util.HashMap;

public class a implements PlatformActionListener {
    private PlatformActionListener a;
    private HashMap<Platform, ShareParams> b = new HashMap();

    a() {
    }

    void a(PlatformActionListener platformActionListener) {
        this.a = platformActionListener;
    }

    PlatformActionListener a() {
        return this.a;
    }

    public void a(Platform platform, ShareParams shareParams) {
        this.b.put(platform, shareParams);
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.a != null) {
            this.a.onError(platform, i, th);
            this.a = null;
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (!(platform instanceof CustomPlatform)) {
            switch (i) {
                case 1:
                    a(platform, i, (HashMap) hashMap);
                    return;
                case 9:
                    b(platform, i, hashMap);
                    return;
                default:
                    if (this.a != null) {
                        this.a.onComplete(platform, i, hashMap);
                        if (!"Wechat".equals(platform.getName())) {
                            this.a = null;
                            return;
                        }
                        return;
                    }
                    return;
            }
        } else if (this.a != null) {
            this.a.onComplete(platform, i, hashMap);
            this.a = null;
        }
    }

    public void onCancel(Platform platform, int i) {
        if (this.a != null) {
            this.a.onCancel(platform, i);
            this.a = null;
        }
    }

    private void a(Platform platform, final int i, final HashMap<String, Object> hashMap) {
        final PlatformActionListener platformActionListener = this.a;
        this.a = new PlatformActionListener(this) {
            final /* synthetic */ a d;

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                this.d.a = platformActionListener;
                if (this.d.a != null) {
                    this.d.a.onComplete(platform, i, hashMap);
                    if (!"Wechat".equals(platform.getName())) {
                        this.d.a = null;
                    }
                }
                c bVar = new b();
                bVar.a = platform.getPlatformId();
                bVar.b = "TencentWeibo".equals(platform.getName()) ? platform.getDb().get("name") : platform.getDb().getUserId();
                bVar.c = new Hashon().fromHashMap(hashMap);
                bVar.d = this.d.a(platform);
                d a = d.a(platform.getContext(), bVar.g);
                if (a != null) {
                    a.a(bVar);
                }
                this.d.a(1, platform);
            }

            public void onError(Platform platform, int i, Throwable th) {
                cn.sharesdk.framework.utils.d.a().w(th);
                this.d.a = platformActionListener;
                if (this.d.a != null) {
                    this.d.a.onComplete(platform, i, hashMap);
                    this.d.a = null;
                }
            }

            public void onCancel(Platform platform, int i) {
                this.d.a = platformActionListener;
                if (this.d.a != null) {
                    this.d.a.onComplete(platform, i, hashMap);
                    this.d.a = null;
                }
            }
        };
        platform.showUser(null);
    }

    private void b(Platform platform, int i, HashMap<String, Object> hashMap) {
        ShareParams shareParams;
        ShareParams shareParams2 = (ShareParams) this.b.remove(platform);
        if (hashMap != null) {
            shareParams = (ShareParams) hashMap.remove("ShareParams");
        } else {
            shareParams = shareParams2;
        }
        try {
            HashMap hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
            HashMap<String, Object> hashMap3 = hashMap;
        }
        if (shareParams != null) {
            c fVar = new f();
            fVar.o = shareParams.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                Platform platform2 = ShareSDK.getPlatform("Wechat");
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get("name");
            }
            fVar.b = userId;
            fVar.a = platform.getPlatformId();
            cn.sharesdk.framework.b.b.f.a filterShareContent = platform.filterShareContent(shareParams, hashMap2);
            if (filterShareContent != null) {
                fVar.c = filterShareContent.a;
                fVar.d = filterShareContent;
            }
            fVar.n = b(platform);
            d a = d.a(platform.getContext(), fVar.g);
            if (a != null) {
                a.a(fVar);
            }
        }
        if (this.a != null) {
            try {
                this.a.onComplete(platform, i, hashMap);
                this.a = null;
            } catch (Throwable th2) {
                cn.sharesdk.framework.utils.d.a().d(th2);
            }
        }
        a(9, platform);
    }

    private void a(int i, Platform platform) {
    }

    void a(Platform platform, final int i, final Object obj) {
        final PlatformActionListener platformActionListener = this.a;
        this.a = new PlatformActionListener(this) {
            final /* synthetic */ a d;

            public void onError(Platform platform, int i, Throwable th) {
                this.d.a = platformActionListener;
                if (this.d.a != null) {
                    this.d.a.onError(platform, i, th);
                    this.d.a = null;
                }
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                this.d.a = platformActionListener;
                platform.afterRegister(i, obj);
            }

            public void onCancel(Platform platform, int i) {
                this.d.a = platformActionListener;
                if (this.d.a != null) {
                    this.d.a.onCancel(platform, i);
                    this.d.a = null;
                }
            }
        };
        platform.doAuthorize(null);
    }

    private String a(Platform platform) {
        try {
            return a(platform.getDb(), new String[]{dji.pilot.usercenter.protocol.d.D, MessageKey.MSG_ICON, n.aG, "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().w(th);
            return null;
        }
    }

    private String b(Platform platform) {
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            Platform platform2 = ShareSDK.getPlatform("Wechat");
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return a(db, new String[]{n.aG, "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().w(th);
            return null;
        }
    }

    private String a(PlatformDb platformDb, String[] strArr) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                stringBuilder2.append('|');
                stringBuilder.append('|');
            }
            i++;
            String str2 = platformDb.get(str2);
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append(str2);
                stringBuilder2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        cn.sharesdk.framework.utils.d.a().i("======UserData: " + stringBuilder.toString(), new Object[0]);
        return stringBuilder2.toString();
    }
}
