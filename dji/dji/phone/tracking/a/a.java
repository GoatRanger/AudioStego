package dji.phone.tracking.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.phone.j.c;
import dji.phone.tracking.DJINativeTracking;
import dji.phone.tracking.b;
import dji.phone.tracking.d;
import dji.pilot.fpv.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class a implements dji.phone.j.a {
    private static final String b = "DJILPTrackingPresenter";
    private static final boolean c = true;
    final String a = Environment.getExternalStorageDirectory().getPath();
    private c d;
    private b e;
    private byte[] f;
    private int g;
    private int h;
    private Context i;

    public a(Context context) {
        this.i = context;
        dji.thirdparty.a.c.a().a(this);
    }

    private void a(d dVar) {
        if (this.e == null) {
            this.e = new b("TRACKING THREAD");
            this.e.a(true);
            this.e.start();
        }
        this.d.a(true);
        this.e.f();
        this.e.a(dVar.a, dVar.b);
    }

    public void a(int i, byte[] bArr) {
        this.f = bArr;
        if (this.f != null && this.e != null) {
            if (this.e.b()) {
                synchronized (this.e.f) {
                    this.e.a(bArr);
                    this.e.f.notify();
                }
            } else if (this.e.d()) {
                synchronized (this.e.f) {
                    this.e.a(bArr);
                }
            }
        }
    }

    public void a() {
        dji.f.a.b(this);
    }

    @TargetApi(18)
    private void d() {
        if (this.e != null && this.e.d()) {
            this.e.a(false);
            DJINativeTracking.release();
        }
    }

    public void b() {
        this.d = dji.phone.j.d.getInstance().a(this.i, b.c, b.d, (dji.phone.j.a) this);
    }

    public void c() {
        if (this.d != null) {
            d();
            if (this.e != null) {
                synchronized (this.e.f) {
                    this.e.a();
                }
            }
            this.d.interrupt();
            this.d = null;
        }
    }

    private void a(Bitmap bitmap) {
        File file = new File(this.a + "/DJI/" + System.currentTimeMillis() + dji.pilot2.main.a.a.n);
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Media.insertImage(this.i.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void onEventBackgroundThread(d dVar) {
        dji.phone.f.d b = dji.phone.f.a.getInstance().b();
        if (b != dji.phone.f.d.WORK_FINE_NORMAL && b != dji.phone.f.d.WORK_FINE_TRACK && (b != dji.phone.f.d.WORK_FINE_TIME_LAPSE || !dji.phone.timelapse.a.getInstance().l())) {
            dji.phone.k.b.showLong(R.string.lp_tk_disable_tips);
        } else if (dji.phone.preview.a.getInstance().i()) {
            a(dVar);
            DJILogHelper.getInstance().LOGI("", "receive TKInitMotion", "TKLOG");
        } else {
            dji.phone.k.b.showLong(R.string.lp_tk_disable_vertical);
        }
    }

    public void onEventBackgroundThread(dji.phone.g.b bVar) {
    }

    public void onEventBackgroundThread(dji.phone.e.b bVar) {
        switch (bVar.a) {
            case BTN_TK_QUIT:
                if (this.e != null) {
                    this.e.f();
                    this.d.a(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.isDoubleClick()) {
            this.e.f();
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        if (this.e != null && this.e.d()) {
            this.e.f();
        }
    }
}
