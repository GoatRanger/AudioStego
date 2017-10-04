package dji.pilot2.scan.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.a.c;
import com.google.a.c.j;
import com.google.a.e;
import com.google.a.k;
import com.google.a.n;
import com.google.a.q;
import dji.pilot.R;
import dji.pilot2.scan.android.CaptureActivity;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

public final class b extends Handler {
    private static final String a = b.class.getSimpleName();
    private final CaptureActivity b;
    private final k c = new k();
    private boolean d = true;

    b(CaptureActivity captureActivity, Map<e, Object> map) {
        this.c.a(map);
        this.b = captureActivity;
    }

    public void handleMessage(Message message) {
        if (this.d) {
            switch (message.what) {
                case R.id.e:
                    a((byte[]) message.obj, message.arg1, message.arg2);
                    return;
                case R.id.p:
                    this.d = false;
                    Looper.myLooper().quit();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(byte[] bArr, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        Object obj = null;
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        n a = this.b.c().a(bArr2, i2, i);
        if (a != null) {
            try {
                obj = this.c.b(new c(new j(a)));
            } catch (q e) {
            } finally {
                a = this.c;
                a.a();
            }
        }
        Handler b = this.b.b();
        if (obj != null) {
            Log.d(a, "Found barcode in " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            if (b != null) {
                Message obtain = Message.obtain(b, R.id.g, obj);
                Bundle bundle = new Bundle();
                a(a, bundle);
                obtain.setData(bundle);
                obtain.sendToTarget();
            }
        } else if (b != null) {
            Message.obtain(b, R.id.f).sendToTarget();
        }
    }

    private static void a(n nVar, Bundle bundle) {
        int[] i = nVar.i();
        int j = nVar.j();
        Bitmap createBitmap = Bitmap.createBitmap(i, 0, j, j, nVar.k(), Config.ARGB_8888);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(c.a, byteArrayOutputStream.toByteArray());
        bundle.putFloat(c.b, ((float) j) / ((float) nVar.g()));
    }
}
