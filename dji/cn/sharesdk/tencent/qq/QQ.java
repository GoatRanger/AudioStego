package cn.sharesdk.tencent.qq;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import com.alipay.sdk.f.d;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import java.util.HashMap;

public class QQ extends Platform {
    public static final String NAME = QQ.class.getSimpleName();
    private String a;
    private boolean b;
    private boolean c = true;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String musicUrl;
        @Deprecated
        public String title;
        @Deprecated
        public String titleUrl;
    }

    public QQ(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo(d.f);
        this.b = "true".equals(getDevinfo("ShareByAppClient"));
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("QZone", d.f);
            if (this.a != null && this.a.length() > 0) {
                copyDevinfo("QZone", NAME);
                this.a = getDevinfo(d.f);
                this.b = "true".equals(getDevinfo("ShareByAppClient"));
                cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public int getPlatformId() {
        return 24;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_id", d.f);
        if (this.a == null || this.a.length() <= 0) {
            this.a = getNetworkDevinfo(6, "app_id", d.f);
            if (this.a != null && this.a.length() > 0) {
                copyNetworkDevinfo(6, 24);
                this.a = getNetworkDevinfo("app_id", d.f);
                cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public boolean isClientValid() {
        b a = b.a((Platform) this);
        a.a(this.a);
        return a.a();
    }

    protected void doAuthorize(String[] strArr) {
        final b a = b.a((Platform) this);
        a.a(this.a);
        a.a(strArr);
        a.a(new AuthorizeListener(this) {
            final /* synthetic */ QQ b;

            public void onError(Throwable th) {
                if (this.b.listener != null) {
                    this.b.listener.onError(this.b, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                this.b.db.putToken(string2);
                this.b.db.putTokenSecret("");
                try {
                    this.b.db.putExpiresIn(R.parseLong(string3));
                } catch (Throwable th) {
                    cn.sharesdk.framework.utils.d.a().d(th);
                }
                this.b.db.putUserId(string);
                string3 = bundle.getString("pf");
                String string4 = bundle.getString("pfkey");
                String string5 = bundle.getString("pay_token");
                this.b.db.put("pf", string3);
                this.b.db.put("pfkey", string4);
                this.b.db.put("pay_token", string5);
                a.b(string);
                a.d(string2);
                this.b.afterRegister(1, null);
            }

            public void onCancel() {
                if (this.b.listener != null) {
                    this.b.listener.onCancel(this.b, 1);
                }
            }
        }, isSSODisable());
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid() || (i == 9 && obj != null && (obj instanceof cn.sharesdk.framework.Platform.ShareParams) && !((cn.sharesdk.framework.Platform.ShareParams) obj).isShareTencentWeibo())) {
            b a = b.a((Platform) this);
            a.a(this.a);
            a.b(this.db.getUserId());
            a.d(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        Object title = shareParams.getTitle();
        String text = shareParams.getText();
        Object imagePath = shareParams.getImagePath();
        Object imageUrl = shareParams.getImageUrl();
        Object musicUrl = shareParams.getMusicUrl();
        String titleUrl = shareParams.getTitleUrl();
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        int hidden = shareParams.getHidden();
        if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(text) || !TextUtils.isEmpty(imagePath) || !TextUtils.isEmpty(imageUrl) || !TextUtils.isEmpty(musicUrl)) {
            b a = b.a((Platform) this);
            PlatformActionListener anonymousClass2 = new PlatformActionListener(this) {
                final /* synthetic */ QQ b;

                public void onError(Platform platform, int i, Throwable th) {
                    if (this.b.listener != null) {
                        this.b.listener.onError(this.b, 9, th);
                    }
                }

                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    HashMap hashMap2 = new HashMap();
                    if (hashMap != null) {
                        hashMap2.putAll(hashMap);
                    }
                    hashMap2.put("ShareParams", shareParams);
                    if (this.b.listener != null) {
                        this.b.listener.onComplete(this.b, 9, hashMap2);
                    }
                }

                public void onCancel(Platform platform, int i) {
                    if (this.b.listener != null) {
                        this.b.listener.onCancel(this.b, 9);
                    }
                }
            };
            if (!TextUtils.isEmpty(titleUrl)) {
                titleUrl = getShortLintk(titleUrl, false);
            }
            if (!TextUtils.isEmpty(text)) {
                text = getShortLintk(text, false);
            }
            a.a(title, titleUrl, text, imagePath, imageUrl, musicUrl, this.b, anonymousClass2, isShareTencentWeibo, hidden);
        } else if (this.listener != null) {
            this.listener.onError(this, 9, new Throwable("qq share must have one param at least"));
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
        if (str == null || str.length() < 0) {
            str = this.db.getUserId();
        }
        if (str != null && str.length() >= 0) {
            try {
                HashMap e = b.a((Platform) this).e(str);
                if (e == null || e.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (e.containsKey("ret")) {
                    if (((Integer) e.get("ret")).intValue() == 0) {
                        if (str == this.db.getUserId()) {
                            this.db.put(dji.pilot.usercenter.protocol.d.D, String.valueOf(e.get(dji.pilot.usercenter.protocol.d.D)));
                            if (e.containsKey("figureurl_qq_1")) {
                                this.db.put(MessageKey.MSG_ICON, String.valueOf(e.get("figureurl_qq_1")));
                            } else if (e.containsKey("figureurl_qq_2")) {
                                this.db.put(MessageKey.MSG_ICON, String.valueOf(e.get("figureurl_qq_2")));
                            }
                            if (e.containsKey("figureurl_2")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl_2")));
                            } else if (e.containsKey("figureurl_1")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl_1")));
                            } else if (e.containsKey("figureurl")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl")));
                            }
                            this.db.put("secretType", String.valueOf(e.get("is_yellow_vip")));
                            if (String.valueOf(e.get("is_yellow_vip")).equals("1")) {
                                this.db.put("snsUserLevel", String.valueOf(e.get("level")));
                            }
                            String valueOf = String.valueOf(e.get(n.aG));
                            if (valueOf.equals("男")) {
                                this.db.put(n.aG, "0");
                            } else if (valueOf.equals("女")) {
                                this.db.put(n.aG, "1");
                            } else {
                                this.db.put(n.aG, "2");
                            }
                        }
                        if (this.listener != null) {
                            this.listener.onComplete(this, 8, e);
                        }
                    } else if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(e)));
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
            this.listener.onError(this, 8, new RuntimeException("qq account is null"));
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
        String titleUrl = shareParams.getTitleUrl();
        aVar.c.add(titleUrl);
        aVar.a = this.a;
        Object text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            aVar.b = text;
        }
        CharSequence imageUrl = shareParams.getImageUrl();
        CharSequence imagePath = shareParams.getImagePath();
        if (!TextUtils.isEmpty(imagePath)) {
            aVar.e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", titleUrl);
        hashMap2.put("imageLocalUrl", imagePath);
        hashMap2.put("summary", text);
        hashMap2.put("appName", DeviceHelper.getInstance(this.context).getAppName());
        aVar.g = hashMap2;
        return aVar;
    }

    protected String uploadImageToFileServer(String str) {
        return super.uploadImageToFileServer(str);
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return this.c;
    }
}
