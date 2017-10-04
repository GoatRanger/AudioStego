package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import cn.sharesdk.framework.b.b.f.a;
import java.util.HashMap;

public abstract class Platform {
    public static final int ACTION_AUTHORIZING = 1;
    protected static final int ACTION_CUSTOMER = 655360;
    public static final int ACTION_FOLLOWING_USER = 6;
    protected static final int ACTION_GETTING_BILATERAL_LIST = 10;
    protected static final int ACTION_GETTING_FOLLOWER_LIST = 11;
    public static final int ACTION_GETTING_FRIEND_LIST = 2;
    public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
    public static final int ACTION_SHARE = 9;
    public static final int ACTION_TIMELINE = 7;
    public static final int ACTION_USER_INFOR = 8;
    public static final int CUSTOMER_ACTION_MASK = 65535;
    public static final int SHARE_APPS = 7;
    public static final int SHARE_EMOJI = 9;
    public static final int SHARE_FILE = 8;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_MUSIC = 5;
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_VIDEO = 6;
    public static final int SHARE_WEBPAGE = 4;
    private c a;
    protected final Context context;
    protected final PlatformDb db = this.a.g();
    protected PlatformActionListener listener = this.a.i();

    public static class ShareParams extends InnerShareParams {
        @Deprecated
        public String imagePath;
        @Deprecated
        public String text;

        public ShareParams(HashMap<String, Object> hashMap) {
            super((HashMap) hashMap);
        }

        public ShareParams(String str) {
            super(str);
        }
    }

    protected abstract boolean checkAuthorize(int i, Object obj);

    protected abstract void doAuthorize(String[] strArr);

    protected abstract void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2);

    protected abstract void doShare(ShareParams shareParams);

    protected abstract HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap);

    protected abstract a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap);

    protected abstract void follow(String str);

    protected abstract HashMap<String, Object> getBilaterals(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowers(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowings(int i, int i2, String str);

    protected abstract void getFriendList(int i, int i2, String str);

    public abstract String getName();

    protected abstract int getPlatformId();

    public abstract int getVersion();

    public abstract boolean hasShareCallback();

    protected abstract void initDevInfo(String str);

    protected abstract void setNetworkDevinfo();

    protected abstract void timeline(int i, int i2, String str);

    protected abstract void userInfor(String str);

    public Platform(Context context) {
        this.context = context;
        this.a = new c(this, context);
    }

    void a() {
        this.a.a(false);
        this.a.a(getName());
    }

    protected void copyDevinfo(String str, String str2) {
        ShareSDK.a(str, str2);
    }

    protected void copyNetworkDevinfo(int i, int i2) {
        ShareSDK.a(i, i2);
    }

    public String getDevinfo(String str) {
        return getDevinfo(getName(), str);
    }

    public String getDevinfo(String str, String str2) {
        return ShareSDK.b(str, str2);
    }

    protected String getNetworkDevinfo(String str, String str2) {
        return getNetworkDevinfo(getPlatformId(), str, str2);
    }

    protected String getNetworkDevinfo(int i, String str, String str2) {
        return this.a.a(i, str, str2);
    }

    public Context getContext() {
        return this.context;
    }

    public int getId() {
        return this.a.a();
    }

    public int getSortId() {
        return this.a.b();
    }

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.a.a(platformActionListener);
    }

    public PlatformActionListener getPlatformActionListener() {
        return this.a.c();
    }

    public boolean isAuthValid() {
        return this.a.d();
    }

    public boolean isClientValid() {
        return false;
    }

    @Deprecated
    public boolean isValid() {
        return this.a.d();
    }

    public void SSOSetting(boolean z) {
        this.a.a(z);
    }

    public boolean isSSODisable() {
        return this.a.e();
    }

    boolean b() {
        return this.a.f();
    }

    public void authorize() {
        authorize(null);
    }

    public void authorize(String[] strArr) {
        this.a.a(strArr);
    }

    protected void innerAuthorize(int i, Object obj) {
        this.a.a(i, obj);
    }

    public void share(ShareParams shareParams) {
        this.a.a(shareParams);
    }

    public void followFriend(String str) {
        this.a.b(str);
    }

    public void getTimeLine(String str, int i, int i2) {
        this.a.a(str, i, i2);
    }

    public void showUser(String str) {
        this.a.c(str);
    }

    public void listFriend(int i, int i2, String str) {
        this.a.a(i, i2, str);
    }

    public void customerProtocol(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        this.a.a(str, str2, s, hashMap, hashMap2);
    }

    protected void afterRegister(int i, Object obj) {
        this.a.b(i, obj);
    }

    public PlatformDb getDb() {
        return this.db;
    }

    @Deprecated
    public void removeAccount() {
        this.a.h();
    }

    public void removeAccount(boolean z) {
        this.a.h();
        ShareSDK.removeCookieOnAuthorize(z);
    }

    public String getShortLintk(String str, boolean z) {
        return this.a.a(str, z);
    }

    protected String uploadImageToFileServer(String str) {
        return this.a.d(str);
    }

    protected String uploadImageToFileServer(Bitmap bitmap) {
        return this.a.a(bitmap);
    }
}
