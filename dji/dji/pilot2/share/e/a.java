package dji.pilot2.share.e;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tumblr.Tumblr;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.whatsapp.WhatsApp;
import dji.log.DJILogHelper;
import dji.pilot.R;

public class a {
    private static final String a = "DJIntegraterShare";
    private Context b;
    private Handler c = new Handler();
    private String d;
    private String e;
    private String f;
    private String g;
    private a h;

    public enum a {
        CONTENT_IMG,
        CONTENT_VIDEO,
        CONTENT_LINK_ADDR
    }

    public enum b {
        PLATFORM_TYPE_QQ,
        PLATFORM_TYPE_WECHAT,
        PLATFORM_TYPE_WECHAT_MOMENTS,
        PLATFORM_TYPE_WEIBO,
        PLATFORM_TYPE_FACKBOOK,
        PLATFORM_TYPE_TWITTER,
        PLATFORM_TYPE_TUMBLR,
        PLATFORM_TYPE_WHATSAPP,
        COPY_CHAINED_ADDRESS,
        PLATFORM_TYPE_SMS,
        PLATFORM_TYPE_MAIL,
        PLATFORM_TYPE_INSTAGRAM
    }

    public a(Context context) {
        this.b = context;
    }

    public void a(String str, String str2, String str3, String str4) {
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
    }

    public void a(b bVar, a aVar) {
        if (bVar == null) {
            a(true, null, null);
            return;
        }
        switch (bVar) {
            case PLATFORM_TYPE_QQ:
                a(false, QQ.NAME, aVar);
                return;
            case PLATFORM_TYPE_WECHAT:
                a(false, Wechat.NAME, aVar);
                return;
            case PLATFORM_TYPE_WECHAT_MOMENTS:
                a(false, WechatMoments.NAME, aVar);
                return;
            case PLATFORM_TYPE_WEIBO:
                a(false, SinaWeibo.NAME, aVar);
                return;
            case PLATFORM_TYPE_FACKBOOK:
                a(false, Facebook.NAME, aVar);
                return;
            case PLATFORM_TYPE_TWITTER:
                a(false, Twitter.NAME, aVar);
                return;
            case PLATFORM_TYPE_TUMBLR:
                a(false, Tumblr.NAME, aVar);
                return;
            case PLATFORM_TYPE_WHATSAPP:
                a(false, WhatsApp.NAME, aVar);
                return;
            case COPY_CHAINED_ADDRESS:
                Context context = this.b;
                this.b.getApplicationContext();
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("data", this.g));
                Toast.makeText(this.b, this.b.getResources().getString(R.string.v2_copy_link_addr_succ), 0).show();
                return;
            case PLATFORM_TYPE_MAIL:
                a();
                return;
            case PLATFORM_TYPE_SMS:
                b();
                return;
            default:
                return;
        }
    }

    private void a() {
    }

    private void b() {
    }

    private void a(boolean z, String str, final a aVar) {
        DJILogHelper.getInstance().LOGI("bob", "recordUrl = " + this.g + " msgText=" + this.e);
        OnekeyShare onekeyShare = new OnekeyShare();
        onekeyShare.setTitle(this.d);
        onekeyShare.setTitleUrl(this.g);
        onekeyShare.setText(this.e);
        if (!str.equals(Facebook.NAME)) {
            onekeyShare.setImagePath(this.f);
        }
        if (!str.equals(SinaWeibo.NAME)) {
            onekeyShare.setUrl(this.g);
        }
        onekeyShare.setSilent(z);
        onekeyShare.setDialogMode();
        onekeyShare.disableSSOWhenAuthorize();
        if (str != null) {
            onekeyShare.setPlatform(str);
            onekeyShare.setShareContentCustomizeCallback(new ShareContentCustomizeCallback(this) {
                final /* synthetic */ a b;

                public void onShare(Platform platform, ShareParams shareParams) {
                    if (WechatMoments.NAME.equals(platform.getName()) || Wechat.NAME.equals(platform.getName())) {
                        this.b.a(aVar);
                        if (aVar == a.CONTENT_VIDEO) {
                            shareParams.setShareType(6);
                        }
                        DJILogHelper.getInstance().LOGI("bob", "ShareType = " + shareParams.getShareType());
                    }
                }
            });
        }
        onekeyShare.show(this.b);
    }

    private void a(a aVar) {
        switch (aVar) {
            case CONTENT_IMG:
                DJILogHelper.getInstance().LOGD(a, "content_img");
                return;
            case CONTENT_VIDEO:
                DJILogHelper.getInstance().LOGD(a, "content_video");
                return;
            case CONTENT_LINK_ADDR:
                DJILogHelper.getInstance().LOGD(a, "content_link");
                return;
            default:
                DJILogHelper.getInstance().LOGD(a, "switch default");
                return;
        }
    }
}
