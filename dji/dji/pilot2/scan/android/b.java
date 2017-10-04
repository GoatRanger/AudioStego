package dji.pilot2.scan.android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.a.e;
import com.google.a.r;
import dji.pilot.R;
import dji.pilot2.scan.a.d;
import dji.pilot2.scan.b.c;
import java.util.Collection;
import java.util.Map;

public final class b extends Handler {
    private static final String a = b.class.getSimpleName();
    private final CaptureActivity b;
    private final c c;
    private a d = a.SUCCESS;
    private final d e;

    private enum a {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public b(CaptureActivity captureActivity, Collection<com.google.a.a> collection, Map<e, ?> map, String str, d dVar) {
        this.b = captureActivity;
        this.c = new c(captureActivity, collection, map, str, new dji.pilot2.scan.view.b(captureActivity.a()));
        this.c.start();
        this.e = dVar;
        dVar.c();
        b();
    }

    public void handleMessage(Message message) {
        String str = null;
        switch (message.what) {
            case R.id.f:
                this.d = a.PREVIEW;
                this.e.a(this.c.a(), (int) R.id.e);
                return;
            case R.id.g:
                float f;
                Bitmap bitmap;
                this.d = a.SUCCESS;
                Bundle data = message.getData();
                if (data != null) {
                    Bitmap copy;
                    byte[] byteArray = data.getByteArray(c.a);
                    if (byteArray != null) {
                        copy = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Config.ARGB_8888, true);
                    } else {
                        copy = null;
                    }
                    f = data.getFloat(c.b);
                    bitmap = copy;
                } else {
                    bitmap = null;
                    f = 1.0f;
                }
                this.b.a((r) message.obj, bitmap, f);
                return;
            case R.id.i:
                String str2 = (String) message.obj;
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(524288);
                intent.setData(Uri.parse(str2));
                ResolveInfo resolveActivity = this.b.getPackageManager().resolveActivity(intent, 65536);
                if (!(resolveActivity == null || resolveActivity.activityInfo == null)) {
                    str = resolveActivity.activityInfo.packageName;
                    Log.d(a, "Using browser in package " + str);
                }
                if ("com.android.browser".equals(str) || "com.android.chrome".equals(str)) {
                    intent.setPackage(str);
                    intent.addFlags(268435456);
                    intent.putExtra("com.android.browser.application_id", str);
                }
                try {
                    this.b.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException e) {
                    Log.w(a, "Can't find anything to handle VIEW of URI " + str2);
                    return;
                }
            case R.id.q:
                b();
                return;
            case R.id.r:
                this.b.setResult(-1, (Intent) message.obj);
                this.b.finish();
                return;
            default:
                return;
        }
    }

    public void a() {
        this.d = a.DONE;
        this.e.d();
        Message.obtain(this.c.a(), R.id.p).sendToTarget();
        try {
            this.c.join(500);
        } catch (InterruptedException e) {
        }
        removeMessages(R.id.g);
        removeMessages(R.id.f);
    }

    public void b() {
        if (this.d == a.SUCCESS) {
            this.d = a.PREVIEW;
            this.e.a(this.c.a(), (int) R.id.e);
            this.b.d();
        }
    }
}
