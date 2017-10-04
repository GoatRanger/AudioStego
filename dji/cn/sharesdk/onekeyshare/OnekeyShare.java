package cn.sharesdk.onekeyshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import com.alipay.sdk.a.a;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.HashMap;

public class OnekeyShare {
    private HashMap<String, Object> params = new HashMap();

    public OnekeyShare() {
        this.params.put("customers", new ArrayList());
        this.params.put("hiddenPlatforms", new HashMap());
    }

    public void setAddress(String str) {
        this.params.put("address", str);
    }

    public void setTitle(String str) {
        this.params.put("title", str);
    }

    public void setTitleUrl(String str) {
        this.params.put("titleUrl", str);
    }

    public void setText(String str) {
        this.params.put("text", str);
    }

    public String getText() {
        return this.params.containsKey("text") ? String.valueOf(this.params.get("text")) : null;
    }

    public void setImagePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.params.put("imagePath", str);
        }
    }

    public void setImageUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.params.put("imageUrl", str);
        }
    }

    public void setUrl(String str) {
        this.params.put("url", str);
    }

    public void setFilePath(String str) {
        this.params.put("filePath", str);
    }

    public void setComment(String str) {
        this.params.put("comment", str);
    }

    public void setSite(String str) {
        this.params.put("site", str);
    }

    public void setSiteUrl(String str) {
        this.params.put("siteUrl", str);
    }

    public void setVenueName(String str) {
        this.params.put("venueName", str);
    }

    public void setVenueDescription(String str) {
        this.params.put("venueDescription", str);
    }

    public void setLatitude(float f) {
        this.params.put("latitude", Float.valueOf(f));
    }

    public void setLongitude(float f) {
        this.params.put("longitude", Float.valueOf(f));
    }

    public void setSilent(boolean z) {
        this.params.put("silent", Boolean.valueOf(z));
    }

    public void setPlatform(String str) {
        this.params.put("platform", str);
    }

    public void setInstallUrl(String str) {
        this.params.put("installurl", str);
    }

    public void setExecuteUrl(String str) {
        this.params.put("executeurl", str);
    }

    public void setMusicUrl(String str) {
        this.params.put("musicUrl", str);
    }

    public void setCallback(PlatformActionListener platformActionListener) {
        this.params.put(a.c, platformActionListener);
    }

    public PlatformActionListener getCallback() {
        return (PlatformActionListener) R.forceCast(this.params.get(a.c));
    }

    public void setShareContentCustomizeCallback(ShareContentCustomizeCallback shareContentCustomizeCallback) {
        this.params.put("customizeCallback", shareContentCustomizeCallback);
    }

    public ShareContentCustomizeCallback getShareContentCustomizeCallback() {
        return (ShareContentCustomizeCallback) R.forceCast(this.params.get("customizeCallback"));
    }

    public void setCustomerLogo(Bitmap bitmap, String str, OnClickListener onClickListener) {
        CustomerLogo customerLogo = new CustomerLogo();
        customerLogo.logo = bitmap;
        customerLogo.label = str;
        customerLogo.listener = onClickListener;
        ((ArrayList) R.forceCast(this.params.get("customers"))).add(customerLogo);
    }

    public void disableSSOWhenAuthorize() {
        this.params.put("disableSSO", Boolean.valueOf(true));
    }

    public void setVideoUrl(String str) {
        this.params.put("url", str);
        this.params.put("shareType", Integer.valueOf(6));
    }

    @Deprecated
    public void setDialogMode() {
        this.params.put("dialogMode", Boolean.valueOf(true));
    }

    public void addHiddenPlatform(String str) {
        ((HashMap) R.forceCast(this.params.get("hiddenPlatforms"))).put(str, str);
    }

    public void setViewToShare(View view) {
        try {
            this.params.put("viewToShare", BitmapHelper.captureView(view, view.getWidth(), view.getHeight()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setImageArray(String[] strArr) {
        this.params.put("imageArray", strArr);
    }

    public void setShareToTencentWeiboWhenPerformingQQOrQZoneSharing() {
        this.params.put("isShareTencentWeibo", Boolean.valueOf(true));
    }

    public void setTheme(OnekeyShareTheme onekeyShareTheme) {
        this.params.put("theme", Integer.valueOf(onekeyShareTheme.getValue()));
    }

    public void show(Context context) {
        int parseInt;
        boolean booleanValue;
        boolean z = false;
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.params);
        ShareSDK.initSDK(context.getApplicationContext());
        ShareSDK.logDemoEvent(1, null);
        try {
            parseInt = R.parseInt(String.valueOf(hashMap.remove("theme")));
        } catch (Throwable th) {
            parseInt = 0;
        }
        OnekeyShareThemeImpl impl = OnekeyShareTheme.fromValue(parseInt).getImpl();
        impl.setShareParamsMap(hashMap);
        if (hashMap.containsKey("dialogMode")) {
            booleanValue = ((Boolean) hashMap.remove("dialogMode")).booleanValue();
        } else {
            booleanValue = false;
        }
        impl.setDialogMode(booleanValue);
        if (hashMap.containsKey("silent")) {
            z = ((Boolean) hashMap.remove("silent")).booleanValue();
        }
        impl.setSilent(z);
        impl.setCustomerLogos((ArrayList) hashMap.remove("customers"));
        impl.setHiddenPlatforms((HashMap) hashMap.remove("hiddenPlatforms"));
        impl.setPlatformActionListener((PlatformActionListener) hashMap.remove(a.c));
        impl.setShareContentCustomizeCallback((ShareContentCustomizeCallback) hashMap.remove("customizeCallback"));
        if (hashMap.containsKey("disableSSO") && ((Boolean) hashMap.remove("disableSSO")).booleanValue()) {
            impl.disableSSO();
        }
        impl.show(context.getApplicationContext());
    }
}
