package cn.sharesdk.wechat.favorite;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatHelper;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import cn.sharesdk.wechat.utils.g;
import cn.sharesdk.wechat.utils.i;
import com.alipay.sdk.f.d;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import java.util.HashMap;

public class WechatFavorite extends Platform {
    public static final String NAME = WechatFavorite.class.getSimpleName();
    private String a;
    private String b;

    public static class ShareParams extends cn.sharesdk.wechat.utils.WechatHelper.ShareParams {
        public ShareParams() {
            this.scene = 2;
        }
    }

    public WechatFavorite(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.a = getDevinfo(d.f);
        this.b = getDevinfo("AppSecret");
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("Wechat", d.f);
            if (this.a == null || this.a.length() <= 0) {
                this.a = getDevinfo("WechatMoments", d.f);
                if (this.a != null && this.a.length() > 0) {
                    copyDevinfo("WechatMoments", NAME);
                    this.a = getDevinfo(d.f);
                    cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyDevinfo("Wechat", NAME);
            this.a = getDevinfo(d.f);
            cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    public int getPlatformId() {
        return 37;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_id", d.f);
        this.b = getNetworkDevinfo("app_secret", "AppSecret");
        if (this.a == null || this.a.length() <= 0) {
            this.a = getNetworkDevinfo(22, "app_id", d.f);
            if (this.a == null || this.a.length() <= 0) {
                this.a = getNetworkDevinfo(23, "app_id", d.f);
                if (this.a != null && this.a.length() > 0) {
                    copyNetworkDevinfo(23, 37);
                    this.a = getNetworkDevinfo("app_id", d.f);
                    cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            } else {
                copyNetworkDevinfo(22, 37);
                this.a = getNetworkDevinfo("app_id", d.f);
                cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
        if (this.b == null || this.b.length() <= 0) {
            this.b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            if (this.b == null || this.b.length() <= 0) {
                this.b = getNetworkDevinfo(23, "app_secret", "AppSecret");
                if (this.b != null && this.b.length() > 0) {
                    copyNetworkDevinfo(23, 37);
                    this.b = getNetworkDevinfo("app_secret", "AppSecret");
                    cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyNetworkDevinfo(22, 37);
            this.b = getNetworkDevinfo("app_secret", "AppSecret");
            cn.sharesdk.framework.utils.d.a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    protected void doAuthorize(String[] strArr) {
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            WechatHelper a = WechatHelper.a();
            a.a(this.context, this.a);
            if (a.c()) {
                if (a.d()) {
                    g gVar = new g(this, 37);
                    gVar.a(this.a, this.b);
                    i iVar = new i(this);
                    iVar.a(gVar);
                    iVar.a(new AuthorizeListener(this) {
                        final /* synthetic */ WechatFavorite a;

                        {
                            this.a = r1;
                        }

                        public void onError(Throwable th) {
                            if (this.a.listener != null) {
                                this.a.listener.onError(this.a, 1, th);
                            }
                        }

                        public void onComplete(Bundle bundle) {
                            this.a.afterRegister(1, null);
                        }

                        public void onCancel() {
                            if (this.a.listener != null) {
                                this.a.listener.onCancel(this.a, 1);
                            }
                        }
                    });
                    try {
                        a.a(iVar);
                    } catch (Throwable th) {
                        if (this.listener != null) {
                            this.listener.onError(this, 1, th);
                        }
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }

    private boolean c() {
        if (TextUtils.isEmpty(getDb().get(Oauth2AccessToken.KEY_REFRESH_TOKEN))) {
            return false;
        }
        g gVar = new g(this, 37);
        gVar.a(this.a, this.b);
        return gVar.a();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        WechatHelper a = WechatHelper.a();
        a.a(this.context, this.a);
        if (a.c()) {
            if (a.d()) {
                if (i == 9 || isAuthValid() || c()) {
                    return true;
                }
                innerAuthorize(i, obj);
                return false;
            } else if (this.listener == null) {
                return false;
            } else {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
                return false;
            }
        } else if (this.listener == null) {
            return false;
        } else {
            this.listener.onError(this, i, new WechatClientNotExistException());
            return false;
        }
    }

    @Deprecated
    public boolean isValid() {
        WechatHelper a = WechatHelper.a();
        a.a(this.context, this.a);
        if (a.c() && super.isValid()) {
            return true;
        }
        return false;
    }

    public boolean isClientValid() {
        WechatHelper a = WechatHelper.a();
        a.a(this.context, this.a);
        if (a.c()) {
            return true;
        }
        return false;
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        shareParams.set("scene", Integer.valueOf(2));
        WechatHelper a = WechatHelper.a();
        a.a(this.context, this.a);
        i iVar = new i(this);
        iVar.a(shareParams, this.listener);
        try {
            a.b(iVar);
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
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            g gVar = new g(this, 37);
            gVar.a(this.a, this.b);
            try {
                gVar.a(this.listener);
            } catch (Throwable th) {
                cn.sharesdk.framework.utils.d.a().d(th);
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
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
        String text = shareParams.getText();
        aVar.b = text;
        CharSequence imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        Bitmap imageData = shareParams.getImageData();
        if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        } else if (imagePath != null) {
            aVar.e.add(imagePath);
        } else if (imageData != null) {
            aVar.f.add(imageData);
        }
        String url = shareParams.getUrl();
        if (url != null) {
            aVar.c.add(url);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", url);
        hashMap2.put("extInfo", null);
        hashMap2.put("content", text);
        hashMap2.put("image", aVar.d);
        hashMap2.put("musicFileUrl", url);
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
        return false;
    }
}
