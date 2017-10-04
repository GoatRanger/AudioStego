package cn.sharesdk.system.text;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.b.b.f.a;
import com.mob.tools.utils.BitmapHelper;
import java.io.File;
import java.util.HashMap;

public class ShortMessage extends Platform {
    public static final int ACTION_SEND = Integer.MAX_VALUE;
    public static final String NAME = ShortMessage.class.getSimpleName();

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String address;
        @Deprecated
        public String title;
    }

    public ShortMessage(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    public int getPlatformId() {
        return 19;
    }

    protected void setNetworkDevinfo() {
    }

    protected void doAuthorize(String[] strArr) {
        afterRegister(1, null);
    }

    protected boolean checkAuthorize(int i, Object obj) {
        return true;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        try {
            a a = a.a(this.context);
            ActionListener anonymousClass1 = new ActionListener(this) {
                final /* synthetic */ ShortMessage b;

                public void onStart(HashMap<String, Object> hashMap) {
                    hashMap.put("ShareParams", shareParams);
                    if (this.b.listener != null) {
                        this.b.listener.onComplete(this.b, 9, hashMap);
                    }
                }

                public void onError(Throwable th) {
                    if (this.b.listener != null) {
                        this.b.listener.onError(this.b, 9, th);
                    }
                }

                public void onComplete(HashMap<String, Object> hashMap) {
                    hashMap.put("ShareParams", shareParams);
                    if (this.b.listener != null) {
                        this.b.listener.onComplete(this.b, 9, hashMap);
                    }
                }
            };
            String shortLintk = getShortLintk(shareParams.getText(), false);
            String title = shareParams.getTitle();
            String address = shareParams.getAddress();
            String imagePath = shareParams.getImagePath();
            Object imageUrl = shareParams.getImageUrl();
            if (TextUtils.isEmpty(imagePath) && !TextUtils.isEmpty(imageUrl)) {
                File file = new File(BitmapHelper.downloadBitmap(getContext(), imageUrl));
                if (file.exists()) {
                    imagePath = file.getAbsolutePath();
                }
            }
            if (address == null) {
                address = "";
            }
            a.a(address, title, shortLintk, imagePath, anonymousClass1);
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
        if (this.listener != null) {
            this.listener.onCancel(this, 8);
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
        aVar.b = shareParams.getText();
        String imagePath = shareParams.getImagePath();
        if (imagePath != null) {
            aVar.e.add(imagePath);
        }
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
