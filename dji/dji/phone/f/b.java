package dji.phone.f;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushShutterCmd;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCommonRestartDevice;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.phone.controview.DJILPCameraShutterButton;
import dji.phone.d.c.a;
import dji.phone.d.d;
import dji.phone.pano.f;
import dji.phone.preview.DJILPPreviewActivity;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    Activity a;
    Toast b;
    Timer c;
    TimerTask d = new TimerTask(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            if ((SystemClock.uptimeMillis() - ((long) this.a.k)) / 1000 >= 5 && this.a.j) {
                this.a.e.sendEmptyMessage(1);
            }
        }
    };
    final Handler e = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ b a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (this.a.b != null) {
                        this.a.b.cancel();
                    }
                    if (this.a.a != null) {
                        this.a.b = dji.phone.k.b.showLong(R.string.lp_gimbla_disturbed);
                        return;
                    }
                    return;
                case 100:
                    this.a.b();
                    return;
                case 101:
                    this.a.c();
                    return;
                default:
                    return;
            }
        }
    };
    private final String f = b.class.getSimpleName();
    private final int g = 1;
    private final int h = 100;
    private final int i = 101;
    private boolean j = false;
    private int k = 0;
    private int l = 1;
    private int m = 100;
    private AtomicBoolean n = new AtomicBoolean(false);
    private AtomicBoolean o = new AtomicBoolean(false);
    private long p = 0;
    private long q = 0;

    public b(Activity activity) {
        this.a = activity;
        this.n.set(false);
        this.c = new Timer(true);
        this.c.schedule(this.d, 0, 1000);
        c.a().a(this);
        if (this.j) {
            this.k = (int) SystemClock.uptimeMillis();
        }
    }

    public void onEventMainThread(dji.phone.tutorial.c.b bVar) {
        Log.d(this.f, "onEventMainThread: event = " + bVar.name());
        switch (bVar) {
            case START:
                this.n.set(true);
                return;
            case STOP:
                this.n.set(false);
                return;
            case FINISH:
                this.n.set(false);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DataCameraSetOpticsZoomMode dataCameraSetOpticsZoomMode) {
        Log.d(this.f, "onEventMainThread: zoomSet getZoomType " + dataCameraSetOpticsZoomMode.a());
        Log.d(this.f, "onEventMainThread: zoomSet getSetZoomSpeed " + dataCameraSetOpticsZoomMode.b());
        Log.d(this.f, "onEventMainThread: zoomSet getZoomDirection " + dataCameraSetOpticsZoomMode.c());
        Log.d(this.f, "onEventMainThread: zoomSet getZoomFocusLenthHigh " + dataCameraSetOpticsZoomMode.d());
        if (d.getInstance().c() != a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW && DJILPUISwitcher.a != dji.phone.g.b.TRACKING) {
            if (dataCameraSetOpticsZoomMode.b() == 75) {
                this.l = 1;
                this.m = 30;
            }
            if (dataCameraSetOpticsZoomMode.a() == 1) {
                if (dataCameraSetOpticsZoomMode.c() == 1) {
                    if (this.e.hasMessages(101)) {
                        this.e.removeMessages(101);
                    }
                    this.e.sendEmptyMessage(100);
                } else if (dataCameraSetOpticsZoomMode.c() == 0) {
                    if (this.e.hasMessages(100)) {
                        this.e.removeMessages(100);
                    }
                    this.e.sendEmptyMessage(101);
                }
            } else if (dataCameraSetOpticsZoomMode.a() == 255) {
                if (this.e.hasMessages(100)) {
                    this.e.removeMessages(100);
                }
                if (this.e.hasMessages(101)) {
                    this.e.removeMessages(101);
                }
            }
        }
    }

    private void b() {
        int h = dji.phone.c.a.c().h() + this.l;
        if (h > dji.phone.c.a.c().i()) {
            h = dji.phone.c.a.c().i();
        }
        if (h < 0) {
            h = 0;
        }
        d.getInstance().c(h, true);
        this.e.sendEmptyMessageDelayed(100, (long) this.m);
    }

    private void c() {
        int h = dji.phone.c.a.c().h() - this.l;
        if (h > dji.phone.c.a.c().i()) {
            h = dji.phone.c.a.c().i();
        }
        if (h < 0) {
            h = 0;
        }
        d.getInstance().c(h, true);
        this.e.sendEmptyMessageDelayed(101, (long) this.m);
    }

    public void onEventMainThread(DataCameraGetPushShutterCmd dataCameraGetPushShutterCmd) {
        Log.d(this.f, "onEventMainThread: cameraShutterCmd = " + dataCameraGetPushShutterCmd.getShutterType());
        this.p = SystemClock.uptimeMillis();
        if (this.q <= 0) {
            this.q = this.p;
        } else if (this.p - this.q < 500) {
            this.q = this.p;
            Log.d(this.f, "onEventMainThread: cmd abandon");
            return;
        } else {
            this.q = this.p;
        }
        if (d.getInstance().c() != a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW && dji.phone.pano.a.d() != f.PANO_STITCHING && this.a != null) {
            if (dataCameraGetPushShutterCmd.getShutterType() == 1) {
                if (dji.phone.c.a.c().a() == 1) {
                    ((DJILPPreviewActivity) this.a).mCameraPresenter.a(0);
                }
                c.a().e(dji.phone.b.a.CMD_TAKEPICTURE);
            } else if (dataCameraGetPushShutterCmd.getShutterType() == 2) {
                if (dji.phone.c.a.c().a() == 0) {
                    ((DJILPPreviewActivity) this.a).mCameraPresenter.a(1);
                }
                if ((d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_STATIONARY || d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_MOTION) && !DJILPCameraShutterButton.e) {
                    c.a().e(dji.phone.e.a.a.n);
                } else {
                    c.a().e(dji.phone.b.a.CMD_RECORD);
                }
            }
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        this.k = (int) SystemClock.uptimeMillis();
        if (d.getInstance().c() != a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW && dataGimbalGetPushParams.isTripleClick() && d.getInstance().c() != a.CAMERASTATE_RECORDING) {
            ((DJILPPreviewActivity) this.a).mCameraPresenter.a();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b) {
            this.j = true;
            if (this.j) {
                this.k = (int) SystemClock.uptimeMillis();
            }
        } else if (pVar == p.a) {
            this.j = false;
        }
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (!this.n.get() && d.getInstance().c() != a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW) {
            final dji.phone.a.c cVar;
            if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal() && !this.o.get()) {
                Log.d(this.f, "onEventMainThread: PhoneOutGimbal()");
                this.o.set(true);
                cVar = new dji.phone.a.c(this.a, R.style.LpBaseDialog);
                cVar.a(this.a.getResources().getString(R.string.lp_gimbla_put_phone));
                cVar.a(true);
                cVar.a(R.drawable.lp_popup_hold_phone);
                cVar.a(0, false);
                cVar.a(1, new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        cVar.dismiss();
                        this.b.o.set(false);
                    }
                });
                cVar.show();
            }
            if (!this.o.get()) {
                Log.d(this.f, "onEventMainThread: GimbalGravity " + dataGimbalGetPushAbnormalStatus.getGimbalGravity());
                switch (dataGimbalGetPushAbnormalStatus.getGimbalGravity()) {
                    case 1:
                        this.o.set(true);
                        cVar = new dji.phone.a.c(this.a, R.style.LpBaseDialog);
                        cVar.a(this.a.getResources().getString(R.string.lp_gimbla_adjust_holder));
                        cVar.a(true);
                        cVar.a(R.drawable.lp_popup_adjust_moveright);
                        cVar.a(0, false);
                        cVar.a(1, new OnClickListener(this) {
                            final /* synthetic */ b b;

                            public void onClick(View view) {
                                cVar.dismiss();
                                this.b.o.set(false);
                            }
                        });
                        cVar.show();
                        break;
                    case 2:
                        this.o.set(true);
                        cVar = new dji.phone.a.c(this.a, R.style.LpBaseDialog);
                        cVar.a(this.a.getResources().getString(R.string.lp_gimbla_adjust_holder));
                        cVar.a(true);
                        cVar.a(R.drawable.lp_popup_adjust_moveleft);
                        cVar.a(0, false);
                        cVar.a(1, new OnClickListener(this) {
                            final /* synthetic */ b b;

                            public void onClick(View view) {
                                cVar.dismiss();
                                this.b.o.set(false);
                            }
                        });
                        cVar.show();
                        break;
                    case 3:
                        if (this.b != null) {
                            this.b.cancel();
                        }
                        this.b = dji.phone.k.b.showLong(R.string.lp_gimbla_disturbed);
                        break;
                }
            }
            if (dataGimbalGetPushAbnormalStatus.isMotorProtected() && !this.o.get()) {
                Log.d(this.f, "onEventMainThread: MotorProtected");
                this.o.set(true);
                cVar = new dji.phone.a.c(this.a, R.style.LpBaseDialog);
                cVar.a(this.a.getResources().getString(R.string.lp_motor_protect));
                cVar.a(false);
                cVar.a(0, false);
                cVar.a(1, true);
                cVar.a(1, new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        DataCommonRestartDevice.getInstance().setReceiveType(DeviceType.GIMBAL).start(null);
                        cVar.dismiss();
                        this.b.o.set(false);
                    }
                });
                cVar.show();
            }
            if ((dataGimbalGetPushAbnormalStatus.isGimbalLocked() || dataGimbalGetPushAbnormalStatus.isJointLockAfterStartup() || dataGimbalGetPushAbnormalStatus.isJointLockWhenStartup()) && !this.o.get()) {
                Log.d(this.f, "onEventMainThread: GimbalLocked");
                this.o.set(true);
                cVar = new dji.phone.a.c(this.a, R.style.LpBaseDialog);
                cVar.a(this.a.getResources().getString(R.string.lp_gimbal_locked));
                cVar.a(false);
                cVar.a(0, true);
                cVar.a(1, true);
                cVar.a(1, new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        DataCommonRestartDevice.getInstance().setReceiveType(DeviceType.GIMBAL).start(null);
                        cVar.dismiss();
                        this.b.o.set(false);
                    }
                });
                cVar.show();
            }
            if (dataGimbalGetPushAbnormalStatus.isUpgrading()) {
                if (this.b != null) {
                    this.b.cancel();
                }
                this.b = dji.phone.k.b.showLong(R.string.lp_device_upgrade);
            }
            if (dataGimbalGetPushAbnormalStatus.isYawLimit()) {
                if (this.b != null) {
                    this.b.cancel();
                }
                this.b = dji.phone.k.b.showLong(R.string.lp_yaw_mech_limit);
            }
        }
    }

    public void a() {
        this.a = null;
        if (this.b != null) {
            this.b.cancel();
        }
        this.n.set(false);
        c.a().d(this);
    }
}
