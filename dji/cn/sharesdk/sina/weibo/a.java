package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.Msg;
import com.sina.weibo.sdk.constant.WBConstants.Response;
import com.sina.weibo.sdk.constant.WBConstants.SDK;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class a extends FakeActivity implements Callback {
    private long a = 2097152;
    private boolean b;
    private String c;
    private ShareParams d;
    private AuthorizeListener e;

    public void onCreate() {
        try {
            Intent intent = new Intent();
            intent.setAction("com.sina.weibo.action.browser.share");
            startActivity(intent);
        } catch (Throwable th) {
            d.a().d(th);
        }
        if (c()) {
            UIHandler.sendEmptyMessageDelayed(1, 700, new Callback(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean handleMessage(Message message) {
                    this.a.a();
                    return true;
                }
            });
        }
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(ShareParams shareParams) {
        this.d = shareParams;
    }

    public void a(AuthorizeListener authorizeListener) {
        this.e = authorizeListener;
    }

    private void a() {
        Bundle bundle = new Bundle();
        bundle.putInt(WBConstants.COMMAND_TYPE_KEY, 1);
        bundle.putString(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        if (!TextUtils.isEmpty(this.d.getText())) {
            bundle.putParcelable(Msg.TEXT, d());
            bundle.putString(Msg.TEXT_EXTRA, "");
        }
        if (!(this.d.getImageData() == null && TextUtils.isEmpty(this.d.getImagePath()))) {
            this.a = 2097152;
            Parcelable e = e();
            if (e.checkArgs()) {
                bundle.putParcelable(Msg.IMAGE, e);
                bundle.putString(Msg.IMAGE_EXTRA, "");
            }
        }
        try {
            a(this.activity, e.a(this.activity).a(), this.c, bundle);
        } catch (Throwable th) {
            if (this.e != null) {
                this.e.onError(new Throwable(th));
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        d.a().d("sina activity requestCode = %s, resultCode = %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
        b();
    }

    public void onNewIntent(Intent intent) {
        this.b = true;
        Bundle extras = intent.getExtras();
        d.a().i("onNewIntent ==>>", new Object[]{extras.toString()});
        String stringExtra = intent.getStringExtra(Base.APP_PKG);
        CharSequence stringExtra2 = intent.getStringExtra(WBConstants.TRAN);
        if (TextUtils.isEmpty(stringExtra)) {
            d.a().e("handleWeiboResponse faild appPackage is null", new Object[0]);
            return;
        }
        d.a().d("handleWeiboResponse getCallingPackage : " + this.activity.getCallingPackage(), new Object[0]);
        if (TextUtils.isEmpty(stringExtra2)) {
            d.a().e("handleWeiboResponse faild intent _weibo_transaction is null", new Object[0]);
        } else if (e.a(this.activity, stringExtra) || stringExtra.equals(this.activity.getPackageName())) {
            a(extras.getInt(Response.ERRCODE), extras.getString(Response.ERRMSG));
        } else {
            d.a().e("handleWeiboResponse faild appPackage validateSign faild", new Object[0]);
        }
    }

    private void a(int i, String str) {
        switch (i) {
            case 0:
                if (this.e != null) {
                    this.e.onComplete(null);
                    break;
                }
                break;
            case 1:
                if (this.e != null) {
                    this.e.onCancel();
                    break;
                }
                break;
            case 2:
                if (this.e != null) {
                    this.e.onError(new Throwable(str));
                    break;
                }
                break;
        }
        finish();
    }

    private void b() {
        UIHandler.sendEmptyMessageDelayed(1, 200, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            if (!(this.b || this.e == null)) {
                this.e.onCancel();
            }
            finish();
        }
        return false;
    }

    private boolean a(Activity activity, String str, String str2, Bundle bundle) {
        Object obj = WBConstants.ACTIVITY_WEIBO;
        if (activity == null || TextUtils.isEmpty(obj) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            d.a().e("launchWeiboActivity fail, invalid arguments", new Object[0]);
            return false;
        }
        String packageName = activity.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction(obj);
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, str2);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, a((Context) activity, packageName));
        intent.putExtra(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            d.a().d("launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
            activity.startActivityForResult(intent, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
            return true;
        } catch (ActivityNotFoundException e) {
            d.a().e(e.getMessage(), new Object[0]);
            return false;
        }
    }

    private boolean c() {
        Intent intent = new Intent();
        intent.setAction(WBConstants.ACTION_WEIBO_REGISTER);
        String packageName = this.activity.getPackageName();
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, this.c);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, a(this.activity, packageName));
        d.a().d("intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
        this.activity.sendBroadcast(intent, WBConstants.ACTION_WEIBO_SDK_PERMISSION);
        return true;
    }

    private String a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] toByteArray2 = toByteArray.toByteArray();
                if (toByteArray2 != null) {
                    return Data.MD5(toByteArray2);
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private TextObject d() {
        TextObject textObject = new TextObject();
        textObject.text = this.d.getText();
        return textObject;
    }

    private ImageObject e() {
        ImageObject imageObject = new ImageObject();
        try {
            if (this.d.getImageData() != null) {
                imageObject.imageData = a(this.activity, this.d.getImageData());
            } else if (!TextUtils.isEmpty(this.d.getImagePath())) {
                DeviceHelper instance = DeviceHelper.getInstance(this.activity);
                if (instance.getSdcardState() && this.d.getImagePath().startsWith(instance.getSdcardPath())) {
                    File file = new File(this.d.getImagePath());
                    if (file.exists() && file.length() != 0 && file.length() < 10485760) {
                        imageObject.imagePath = this.d.getImagePath();
                    }
                }
                imageObject.imageData = b(this.activity, this.d.getImagePath());
            }
        } catch (Throwable th) {
            d.a().d(th);
        }
        return imageObject;
    }

    private byte[] a(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (!bitmap.isRecycled()) {
            return b(context, bitmap);
        } else {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
    }

    private byte[] b(Context context, String str) throws Throwable {
        if (new File(str).exists()) {
            return b(context, BitmapHelper.getBitmap(str));
        }
        throw new FileNotFoundException();
    }

    private byte[] b(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            int length = toByteArray.length;
            while (((long) length) > this.a) {
                bitmap = a(bitmap, ((double) length) / ((double) this.a));
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                toByteArray = byteArrayOutputStream.toByteArray();
                length = toByteArray.length;
            }
            return toByteArray;
        }
    }

    private Bitmap a(Bitmap bitmap, double d) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) / sqrt), (int) (((double) height) / sqrt), true);
    }
}
