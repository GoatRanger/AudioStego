package dji.device.camera.view.common;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.device.camera.a.a;
import dji.device.camera.a.b;
import dji.device.camera.a.d;
import dji.device.camera.a.e;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.g;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJICameraAnimViewCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganShutterButton extends RelativeLayout implements OnClickListener {
    DJIStateImageViewCompat a;
    DJIStateImageViewCompat b;
    DJIStateImageViewCompat c;
    DJIStateImageViewCompat d;
    Animation e;
    DJICameraAnimViewCompat f;
    AudioManager g;
    boolean h = false;
    boolean i = true;
    boolean j = false;
    SDCardState k = SDCardState.OTHER;
    private final String l = "LonganShutterButton";
    private boolean m = false;
    private boolean n = false;
    private DiskStatus o = DiskStatus.OTHER;

    public LonganShutterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void addSoundPlayer(DJICameraAnimViewCompat dJICameraAnimViewCompat) {
        this.f = dJICameraAnimViewCompat;
    }

    public boolean canStop() {
        return this.h;
    }

    private void a() {
        this.a = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_photo);
        this.b = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_record);
        this.c = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_photo_outer);
        this.d = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_controll_saving);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.g = (AudioManager) getContext().getSystemService("audio");
        b();
        setEnabled(false);
        c.a().a(this);
        onEventMainThread(a.getInstance());
        onEventMainThread(d.getInstance().c());
        onEventMainThread(b.getInstance().d());
    }

    private void b() {
        this.e = AnimationUtils.loadAnimation(getContext(), R.anim.longan_saving_rotate);
        this.e.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LonganShutterButton a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.j = true;
                this.a.d.show();
                this.a.c.hide();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.j = false;
                this.a.d.hide();
                this.a.c.show();
            }
        });
    }

    public void onEventMainThread(a aVar) {
        switch (aVar.d()) {
            case TAKEPHOTO:
                this.a.show();
                this.b.hide();
                return;
            case RECORD:
                this.a.hide();
                this.b.show();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(b.b bVar) {
        if (bVar == b.b.SAVING) {
            Log.d("LonganShutterButton", "saving");
            e();
        } else if (bVar == b.b.SAVING_NOT) {
            Log.d("LonganShutterButton", "SAVING_NOT");
            f();
        }
    }

    public void onEventMainThread(d.a aVar) {
        if (a.getInstance().d() != a.a.TAKEPHOTO) {
            if (aVar == d.a.RECORDING) {
                this.i = false;
                this.b.setSelected(true);
                f();
            } else if (aVar == d.a.NO) {
                this.b.setSelected(false);
                this.i = true;
                f();
            } else if (aVar == d.a.START) {
                if (this.g.getRingerMode() == 2) {
                    this.f.startVideo();
                }
                e();
            } else if (aVar == d.a.STOP) {
                if (this.g.getRingerMode() == 2) {
                    this.f.stopVideo();
                }
                e();
            }
        }
    }

    public void onEventMainThread(b.a aVar) {
        if (aVar == b.a.PHOTO_ING) {
            if (dji.device.camera.a.c.getInstance().d() != dji.device.camera.a.c.a.PANO_MANU) {
                if (!this.h) {
                    this.a.setEnabled(false);
                }
                if (dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.PANO) {
                    c();
                }
            }
        } else if (aVar == b.a.PHOTO_NOT) {
            this.a.setEnabled(true);
        }
    }

    private void c() {
        TYPE b = dji.device.camera.a.c.getInstance().b(dji.device.camera.a.c.getInstance().c());
        int e = dji.device.camera.a.c.getInstance().e();
        if (this.g.getRingerMode() == 2) {
            this.f.startTakePic(b, e);
        }
    }

    public void onEventMainThread(b.c cVar) {
        if (cVar == b.c.TIMING) {
            this.a.setBackground(getResources().getDrawable(R.drawable.longan_selector_shutter_interval));
            this.a.setSelected(true);
            this.h = true;
        } else if (cVar == b.c.NOT_TIMING) {
            this.a.setBackground(getResources().getDrawable(R.drawable.longan_shutter_photo));
            this.a.setSelected(false);
            this.h = false;
            if (this.j) {
                f();
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_camera_controll_photo) {
            g();
        } else if (id == R.id.longan_camera_controll_record) {
            DataCameraGetAudio.getInstance().start(new dji.midware.e.d(this) {
                final /* synthetic */ LonganShutterButton a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.d();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.d();
                }
            });
        }
        c.a().e(m.HIDE_MENU);
    }

    private void d() {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ LonganShutterButton a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h();
            }
        });
    }

    private void e() {
        if (!this.j) {
            this.d.startAnimation(this.e);
        }
    }

    private void f() {
        this.d.clearAnimation();
        this.e.cancel();
    }

    private void g() {
        dji.device.camera.a.c instance = dji.device.camera.a.c.getInstance();
        if (instance.c() == dji.device.camera.a.c.b.INTERVAL || instance.c() == dji.device.camera.a.c.b.TIMELAPSE) {
            if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.TIMELAPSE && dji.device.timelapse.b.getInstance().c() == dji.device.timelapse.b.b.MOTION && b.getInstance().d() == b.c.NOT_TIMING && dji.device.timelapse.b.getInstance().g().size() <= 1) {
                Toast.makeText(getContext(), getResources().getString(R.string.longan_timelapse_moving_warning), 0).show();
                return;
            }
            if (instance.c() == dji.device.camera.a.c.b.TIMELAPSE && !this.h && dji.device.timelapse.b.getInstance().c == 10 && dji.logic.f.b.b(null)) {
                Toast.makeText(getContext(), R.string.longan_timelapse_1s_interval_tip, 1).show();
            }
            i();
        } else if (instance.c() != dji.device.camera.a.c.b.SINGLE || instance.f() == 0) {
            TYPE type;
            if (instance.d() == dji.device.camera.a.c.a.SINGLE_HDR) {
                type = TYPE.HDR;
            } else {
                type = instance.b(instance.c());
            }
            dji.device.camera.settings.a.getInstance().a(type, instance.e(), instance.f());
        } else {
            j();
        }
    }

    private void h() {
        if (e.getInstance().b() == e.a.AUTO) {
            dji.device.camera.settings.a.getInstance().b(this.i, 0, 0);
        } else if (e.getInstance().b() != e.a.TIMELAPSE) {
            dji.device.camera.settings.a.getInstance().b(this.i, 2, 0);
        }
    }

    private void i() {
        dji.device.camera.a.c instance = dji.device.camera.a.c.getInstance();
        if (canStop()) {
            DataSpecialControl.getInstance().setPhotoType(TYPE.STOP).start(20);
            return;
        }
        dji.device.camera.settings.a.getInstance().a(instance.b(instance.c()), instance.e(), instance.f());
    }

    private void j() {
        if (canStop()) {
            DataSpecialControl.getInstance().setPhotoType(TYPE.STOP).start(20);
            f();
            return;
        }
        dji.device.camera.a.c instance = dji.device.camera.a.c.getInstance();
        TYPE b = instance.b(instance.c());
        dji.device.camera.settings.a.getInstance().a(b, instance.e(), instance.f());
        if (b == TYPE.SINGLE) {
            DataSpecialControl.getInstance().setPhotoType(TYPE.STOP).start(20);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            setAlpha(1.0f);
            this.a.setEnabled(true);
            this.b.setEnabled(true);
            return;
        }
        setAlpha(0.3f);
        this.a.setEnabled(false);
        this.b.setEnabled(false);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            k();
        } else if (oVar == o.b) {
            this.m = false;
            this.k = SDCardState.OTHER;
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        }
    }

    private void k() {
        setEnabled(false);
        if (this.j) {
            f();
        }
        this.m = true;
    }

    public void onEventMainThread(DJIUIEventManagerLongan.e eVar) {
        if (eVar == DJIUIEventManagerLongan.e.ENTER_SLEEP_MODE) {
            k();
        }
    }

    public void onEventMainThread(g gVar) {
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        SDCardState sDCardState = dataCameraGetPushStateInfo.getSDCardState();
        if (!this.k._equals(sDCardState.value()) && !this.m) {
            DJILogHelper.getInstance().LOGE("LonganShutterButton", "curSdcardState=" + sDCardState, false, true);
            this.k = sDCardState;
            switch (this.k) {
                case Full:
                    setEnabled(false);
                    this.n = false;
                    return;
                case Normal:
                case ToFormat:
                case BecomeSlow:
                case Slow:
                    setEnabled(true);
                    this.n = true;
                    return;
                default:
                    setEnabled(false);
                    this.n = false;
                    return;
            }
        }
    }

    public void onEventMainThread(k kVar) {
        if (kVar == k.START) {
            g();
        }
    }

    public void onEventMainThread(DJIUIEventManagerLongan.c cVar) {
        if (cVar == DJIUIEventManagerLongan.c.DISABLE_ALL) {
            setEnabled(false);
        } else if (cVar == DJIUIEventManagerLongan.c.ENABLE_ALL) {
            setEnabled(true);
        }
    }

    public void onEventMainThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        if (dataCameraGetPushRawParams.isGetted()) {
            DiskStatus diskStatus = dataCameraGetPushRawParams.getDiskStatus();
            if (this.o == diskStatus) {
                return;
            }
            if (diskStatus == DiskStatus.INITIALIZING) {
                setEnabled(false);
            } else if (this.n) {
                setEnabled(true);
            }
        }
    }
}
