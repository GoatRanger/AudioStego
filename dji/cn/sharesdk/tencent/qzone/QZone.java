package cn.sharesdk.tencent.qzone;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import com.alipay.sdk.f.d;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import java.io.File;
import java.util.HashMap;

public class QZone extends Platform {
    public static final String NAME = QZone.class.getSimpleName();
    private String a;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String comment;
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String site;
        @Deprecated
        public String siteUrl;
        @Deprecated
        public String title;
        @Deprecated
        public String titleUrl;
        @Deprecated
        boolean webShare;
    }

    public QZone(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo(d.f);
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("QQ", d.f);
            if (this.a != null && this.a.length() > 0) {
                copyDevinfo("QQ", NAME);
                this.a = getDevinfo(d.f);
                cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public int getPlatformId() {
        return 6;
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
            this.a = getNetworkDevinfo(24, "app_id", d.f);
            if (this.a != null && this.a.length() > 0) {
                copyNetworkDevinfo(24, 6);
                this.a = getNetworkDevinfo("app_id", d.f);
                cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
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
            final /* synthetic */ QZone b;

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
                a.c(string2);
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
        if (isAuthValid() || i == 9) {
            b a = b.a((Platform) this);
            a.a(this.a);
            a.b(this.db.getUserId());
            a.c(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        if (shareParams.isShareTencentWeibo()) {
            a(shareParams);
        } else {
            b(shareParams);
        }
    }

    private void a(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        Object imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        try {
            if (TextUtils.isEmpty(imagePath) && !TextUtils.isEmpty(imageUrl)) {
                shareParams.setImagePath(BitmapHelper.downloadBitmap(this.context, imageUrl));
                doShare(shareParams);
            } else if (isAuthValid()) {
                imageUrl = shareParams.getText();
                if (!TextUtils.isEmpty(imageUrl)) {
                    HashMap b;
                    String shortLintk = getShortLintk(imageUrl, false);
                    b a = b.a((Platform) this);
                    if (isShareTencentWeibo) {
                        b = a.b(imagePath, shortLintk);
                    } else {
                        b = a.a(imagePath, shortLintk);
                    }
                    if (b == null && this.listener != null) {
                        this.listener.onError(this, 9, new Throwable("response is empty"));
                    }
                    b.put("ShareParams", shareParams);
                    if (this.listener != null) {
                        this.listener.onComplete(this, 9, b);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 9, new Throwable("share params' value of text is empty!"));
                }
            } else {
                final PlatformActionListener platformActionListener = getPlatformActionListener();
                setPlatformActionListener(new PlatformActionListener(this) {
                    final /* synthetic */ QZone c;

                    public void onError(Platform platform, int i, Throwable th) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(platform, 9, th);
                        }
                    }

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        this.c.setPlatformActionListener(platformActionListener);
                        this.c.doShare(shareParams);
                    }

                    public void onCancel(Platform platform, int i) {
                        if (platformActionListener != null) {
                            platformActionListener.onCancel(platform, 9);
                        }
                    }
                });
                authorize();
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    private void b(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        try {
            String imageUrl = shareParams.getImageUrl();
            String imagePath = shareParams.getImagePath();
            if (isClientValid()) {
                if (TextUtils.isEmpty(imagePath) || !new File(imagePath).exists()) {
                    imagePath = imageUrl;
                }
                String title = shareParams.getTitle();
                String titleUrl = shareParams.getTitleUrl();
                String site = shareParams.getSite();
                String text = shareParams.getText();
                if (!TextUtils.isEmpty(text)) {
                    text = getShortLintk(text, false);
                }
                if (!TextUtils.isEmpty(titleUrl)) {
                    titleUrl = getShortLintk(titleUrl, false);
                }
                b.a((Platform) this).a(title, titleUrl, text, imagePath, site, new PlatformActionListener(this) {
                    final /* synthetic */ QZone b;

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
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new QQClientNotExistException());
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
        if (str == null || str.length() < 0) {
            str = this.db.getUserId();
        }
        if (str != null && str.length() >= 0) {
            try {
                HashMap d = b.a((Platform) this).d(str);
                if (d == null || d.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (d.containsKey("ret")) {
                    if (((Integer) d.get("ret")).intValue() == 0) {
                        if (str == this.db.getUserId()) {
                            this.db.put(dji.pilot.usercenter.protocol.d.D, String.valueOf(d.get(dji.pilot.usercenter.protocol.d.D)));
                            if (d.containsKey("figureurl_qq_1")) {
                                this.db.put("iconQQ", String.valueOf(d.get("figureurl_qq_1")));
                            } else if (d.containsKey("figureurl_qq_2")) {
                                this.db.put("iconQQ", String.valueOf(d.get("figureurl_qq_2")));
                            }
                            if (d.containsKey("figureurl_2")) {
                                this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl_2")));
                            } else if (d.containsKey("figureurl_1")) {
                                this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl_1")));
                            } else if (d.containsKey("figureurl")) {
                                this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl")));
                            }
                            this.db.put("secretType", String.valueOf(d.get("is_yellow_vip")));
                            if (String.valueOf(d.get("is_yellow_vip")).equals("1")) {
                                this.db.put("snsUserLevel", String.valueOf(d.get("level")));
                            }
                            String valueOf = String.valueOf(d.get(n.aG));
                            if (valueOf.equals("男")) {
                                this.db.put(n.aG, "0");
                            } else if (valueOf.equals("女")) {
                                this.db.put(n.aG, "1");
                            } else {
                                this.db.put(n.aG, "2");
                            }
                        }
                        if (this.listener != null) {
                            this.listener.onComplete(this, 8, d);
                        }
                    } else if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(d)));
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
        HashMap a = b.a((Platform) this).a(str, str2, hashMap, hashMap2);
        if (a == null || a.size() <= 0) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } else if (a.containsKey("ret")) {
            if (((Integer) a.get("ret")).intValue() != 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, i, a);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, i, new Throwable());
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        if (imagePath != null) {
            aVar.e.add(imagePath);
        } else if (hashMap.get("large_url") != null) {
            aVar.d.add(String.valueOf(hashMap.get("large_url")));
        } else if (hashMap.get("small_url") != null) {
            aVar.d.add(String.valueOf(hashMap.get("small_url")));
        } else if (imageUrl != null) {
            aVar.d.add(imageUrl);
        }
        imageUrl = shareParams.getTitleUrl();
        if (imageUrl != null) {
            aVar.c.add(imageUrl);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("titleUrl", shareParams.getTitleUrl());
        hashMap2.put("site", shareParams.getSite());
        aVar.g = hashMap2;
        return aVar;
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
        return true;
    }
}
