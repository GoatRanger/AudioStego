package dji.phone.preview;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import dji.common.ui.dialog.DJIAlertDialogUtil;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.phone.DJILPTouchLogicView;
import dji.phone.a.g;
import dji.phone.a.h;
import dji.phone.bluetooth.DJILPBleStatusView;
import dji.phone.e.a.e;
import dji.phone.f.c;
import dji.phone.h.b;
import dji.phone.pano.d;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.fpv.R;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class a implements OnClickListener {
    public static final String a = "OSMO_PHONE_TAKE_SCREENSHOT";
    public static final int b = 1;
    public static DJILPPreviewActivity c = null;
    public static b d = b.ROTATION_UNKNOWN;
    public static int e = 0;
    public static int f = 0;
    private static final String g = "DJILPPreviewPresenter";
    private static final String h = "DJILPPreviewPresenter";
    private static a n;
    private OrientationEventListener i = null;
    private float j = b.ROTATION_UNKNOWN.a();
    private int k = b.ROTATION_UNKNOWN.b();
    private a l;
    private c m;

    private class a extends Handler {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            super.handleMessage(message);
        }
    }

    private a() {
    }

    public static a getInstance() {
        if (n == null) {
            n = new a();
        }
        return n;
    }

    public void a(DJILPPreviewActivity dJILPPreviewActivity) {
        c = dJILPPreviewActivity;
        this.l = new a();
        l();
        k();
        dji.f.a.a(this);
        j();
    }

    public void a(int i) {
        this.l.sendEmptyMessageDelayed(i, 2000);
    }

    private void j() {
        this.i = new OrientationEventListener(this, c) {
            final /* synthetic */ a a;

            public void onOrientationChanged(int i) {
                if (i != -1 && this.a.b(i)) {
                    dji.thirdparty.a.c.a().e(b.findByRotation(this.a.k));
                }
            }
        };
    }

    private void k() {
        this.m = new c();
    }

    private boolean b(int i) {
        int i2 = 0;
        float abs = Math.abs(((float) i) - this.j);
        if (abs > 180.0f) {
            abs = 360.0f - abs;
        }
        if (abs <= 60.0f && this.j != -1.0f) {
            return false;
        }
        int i3 = (((i + 45) / 90) * 90) % 360;
        if (((float) i3) == this.j) {
            return false;
        }
        this.j = (float) i3;
        if (c == null) {
            return false;
        }
        switch (c.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
        }
        this.k = (int) ((360.0f - ((((float) i2) + this.j) % 360.0f)) % 360.0f);
        g.a("DJILPPreviewPresenter", "mUIRotation:" + this.k);
        return true;
    }

    private void l() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Point point = new Point();
        if (VERSION.SDK_INT < 17) {
            c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            e = displayMetrics.widthPixels;
            f = displayMetrics.heightPixels;
        } else {
            c.getWindowManager().getDefaultDisplay().getRealSize(point);
            e = point.x;
            f = point.y;
        }
        g.a("DJILPPreviewPresenter", "screensize: width:" + e + "screen height:" + f);
    }

    public void a() {
        this.i.enable();
    }

    public void b() {
        this.i.disable();
    }

    public void c() {
        dji.f.a.b(this);
        c = null;
        this.m.a();
        this.j = b.ROTATION_UNKNOWN.a();
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        e eVar = bVar.a;
        dji.phone.e.a.a aVar = bVar.b;
        dji.phone.e.a.c cVar = bVar.c;
        if (eVar == e.VIEW_BLE_DIALOG) {
            if (cVar != dji.phone.e.a.c.f) {
                return;
            }
            if (DJILPBleStatusView.c == dji.phone.bluetooth.b.NOTCONNECTED) {
                if (h.getInstance().isVisible()) {
                    h.getInstance().dismiss();
                } else {
                    h.getInstance().show(c.getFragmentManager(), null);
                }
            } else if (DJILPBleStatusView.c == dji.phone.bluetooth.b.CONNECTED) {
                m();
            }
        } else if (eVar == e.BTN_CAMERA_MODE) {
            if (bVar.c == dji.phone.e.a.c.d) {
                c.changeMenuBgVisible(0);
            } else if (bVar.c == dji.phone.e.a.c.e) {
                c.changeMenuBgVisible(4);
            }
        } else if (eVar == e.VIEW_GIMBAL_ROLL_TUNE) {
            c.showGimbalRollTune();
        } else if (aVar == dji.phone.e.a.a.h) {
            c.hidePopView();
        } else if (aVar == dji.phone.e.a.a.f) {
            MotionEvent motionEvent = DJILPTouchLogicView.b;
            c.changeMeterPos((int) motionEvent.getX(), (int) motionEvent.getY());
        } else if (aVar == dji.phone.e.a.a.i) {
            c.setBrightness(0.1f);
        } else if (aVar == dji.phone.e.a.a.j) {
            c.setBrightness(1.0f);
        }
    }

    public void d() {
        c.handleActionSwitchCamera();
    }

    private void m() {
        String k = dji.midware.b.c.getInstance().f().k();
        if (k == null) {
            k = "";
        }
        DJIAlertDialogUtil.show(c, k, c.getString(R.string.lp_ble_device_disconnect_hint_txt), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dji.phone.bluetooth.c.getInstance().d();
                dialogInterface.dismiss();
            }
        });
        dji.publics.b.b.a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "7", false).a("pageid", "2", false).a("device_ver", dji.device.common.b.getInstance().b(), false).a("device_type", i.getInstance().c()._name(), true);
    }

    public void onEventMainThread(dji.pilot.phonecamera.a.c.a aVar) {
        c.handleShowGrid(Integer.valueOf(aVar.a()));
    }

    public void onEventMainThread(dji.phone.g.b bVar) {
        c.switchUIModeImg(bVar);
    }

    public void onEventMainThread(b bVar) {
        d = bVar;
    }

    public void onEventMainThread(dji.pilot.phonecamera.a.a aVar) {
        if (aVar == dji.pilot.phonecamera.a.a.d) {
            c.switchCameraID();
        }
    }

    public void onClick(View view) {
    }

    public boolean e() {
        return c.isPopViewShown();
    }

    public boolean f() {
        return !c.isPopViewShown() && (DJILPUISwitcher.a == dji.phone.g.b.AUTO || DJILPUISwitcher.a == dji.phone.g.b.METERING);
    }

    public boolean g() {
        return DJILPUISwitcher.a == dji.phone.g.b.DARKENED;
    }

    public Bitmap a(int i, int i2) {
        Bitmap peekPreview = c.peekPreview(i, i2);
        if (peekPreview == null) {
            return Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        }
        return peekPreview;
    }

    public void h() {
        Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
        DJILPPreviewActivity dJILPPreviewActivity = c;
        DJILPPreviewActivity dJILPPreviewActivity2 = c;
        dJILPPreviewActivity.startActivityForResult(intent, 1021);
    }

    public void onEventBackgroundThread(DataGimbalActiveStatus dataGimbalActiveStatus) {
        c.finish();
    }

    public void a(d dVar, boolean z) {
        c.startPano(dVar, z);
    }

    public void a(d dVar, String str) {
        int i = e / 2;
        int i2 = f / 2;
        if (dVar == d.P_WIDE) {
            i = e / 3;
            i2 = f / 3;
        }
        Bitmap peekPreview = c.peekPreview(i, i2);
        try {
            OutputStream fileOutputStream = new FileOutputStream(str);
            peekPreview.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Intent intent = new Intent(a);
            intent.putExtra("path", str);
            c.sendBroadcast(intent);
        } catch (FileNotFoundException e) {
        } catch (IOException e2) {
        }
    }

    public boolean i() {
        return this.k == b.ROTATION_0.b() || this.k == b.ROTATION_180.b();
    }
}
