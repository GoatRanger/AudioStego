package cn.sharesdk.whatsapp;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.b.b.f.a;
import com.mob.tools.utils.BitmapHelper;
import java.io.File;
import java.util.HashMap;

public class WhatsApp extends Platform {
    public static final String NAME = WhatsApp.class.getSimpleName();
    private b a = new b(this);

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
    }

    public WhatsApp(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
    }

    public int getPlatformId() {
        return 43;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    @Deprecated
    public boolean isValid() {
        return this.a.a();
    }

    public boolean isClientValid() {
        return this.a.a();
    }

    protected void setNetworkDevinfo() {
    }

    protected void doAuthorize(String[] strArr) {
        if (isClientValid()) {
            afterRegister(1, null);
        } else if (this.listener != null) {
            this.listener.onError(this, 1, new WhatsAppClientNotExistException());
        }
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isClientValid()) {
            return true;
        }
        if (this.listener != null) {
            this.listener.onError(this, i, new WhatsAppClientNotExistException());
        }
        return false;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        Object text = shareParams.getText();
        String title = shareParams.getTitle();
        String filePath = shareParams.getFilePath();
        Object address = shareParams.getAddress();
        try {
            String imagePath = shareParams.getImagePath();
            Object imageUrl = shareParams.getImageUrl();
            PlatformActionListener anonymousClass1 = new PlatformActionListener(this) {
                final /* synthetic */ WhatsApp b;

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
            if (!TextUtils.isEmpty(text)) {
                this.a.a(getShortLintk(text, false), title, anonymousClass1);
            } else if (!TextUtils.isEmpty(imagePath)) {
                this.a.a(2, imagePath, anonymousClass1);
            } else if (!TextUtils.isEmpty(imageUrl)) {
                File file = new File(BitmapHelper.downloadBitmap(getContext(), imageUrl));
                if (file.exists()) {
                    imagePath = file.getAbsolutePath();
                }
                this.a.a(2, imagePath, anonymousClass1);
            } else if (!TextUtils.isEmpty(filePath)) {
                this.a.a(6, filePath, anonymousClass1);
            } else if (!TextUtils.isEmpty(address)) {
                this.a.a(address, anonymousClass1);
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable("Missing parameters"));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    protected void userInfor(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 8);
        }
    }

    protected a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        Object text = shareParams.getText();
        CharSequence imageUrl = shareParams.getImageUrl();
        CharSequence imagePath = shareParams.getImagePath();
        if (!TextUtils.isEmpty(text)) {
            aVar.b = text;
        } else if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        } else if (!TextUtils.isEmpty(imagePath)) {
            aVar.e.add(imagePath);
        }
        if (hashMap != null) {
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
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
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
