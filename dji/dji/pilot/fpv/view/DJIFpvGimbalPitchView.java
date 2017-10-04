package dji.pilot.fpv.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.dji.frame.c.e;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.widget.DJIVSeekBar;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DJIFpvGimbalPitchView extends DJIRelativeLayout {
    private static final float B = 0.3f;
    private static String a = "DJIFpvGimbalPitchView";
    private static final int b = 4096;
    private static final long c = 3000;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int h = 120;
    private static final int i = 30;
    private static final int j = 5;
    private static final int k = 5;
    private static final int w = 40;
    private static final int x = 270;
    private float A = 139.0f;
    private Timer C;
    private OnSeekBarChangeListener D = null;
    private int g = 0;
    private DJIVSeekBar l = null;
    private float m = Float.MIN_VALUE;
    private int n = 30;
    private int o = 0;
    private a p = null;
    private boolean q = false;
    private DJIStateImageView r;
    private DJIStateImageView s;
    private LinearLayout t;
    private List<ImageView> u = new ArrayList();
    private final int v = 14;
    private final int y = 140;
    private float z = 10.0f;

    private static final class a extends Handler {
        private final WeakReference<DJIFpvGimbalPitchView> a;

        private a(DJIFpvGimbalPitchView dJIFpvGimbalPitchView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvGimbalPitchView);
        }

        public void handleMessage(Message message) {
            DJIFpvGimbalPitchView dJIFpvGimbalPitchView = (DJIFpvGimbalPitchView) this.a.get();
            if (dJIFpvGimbalPitchView != null) {
                switch (message.what) {
                    case 4096:
                        dJIFpvGimbalPitchView.setMyAlpha(DJIFpvGimbalPitchView.B);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFpvGimbalPitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = e.b(context, 1.0f);
    }

    public void setPlayBackViewVisible(boolean z) {
        this.q = z;
    }

    public void dispatchOnCreate() {
        show();
        setMyAlpha(B);
        c.a().a(this);
    }

    public void dispatchOnDestroy() {
        c.a().d(this);
    }

    public void show() {
        super.show();
        setMyAlpha(B);
    }

    public void setMyAlpha(float f) {
        if (f != B || !b.getInstance().a(null)) {
            super.setAlpha(f);
        }
    }

    private void b() {
        this.p = new a();
        this.D = new OnSeekBarChangeListener(this) {
            final /* synthetic */ DJIFpvGimbalPitchView a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.b(seekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
            this.l = (DJIVSeekBar) findViewById(R.id.a1m);
            this.l.setEnabled(false);
            this.l.setMax(120);
            this.l.setProgress(30);
            this.r = (DJIStateImageView) findViewById(R.id.a1l);
            this.s = (DJIStateImageView) findViewById(R.id.a1n);
            this.r.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ DJIFpvGimbalPitchView a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        this.a.startSendGimbalSpeed(DJIFpvGimbalPitchView.x);
                    } else if (motionEvent.getAction() == 1) {
                        this.a.stopSendGimbalSpeed(DJIFpvGimbalPitchView.x);
                    }
                    return true;
                }
            });
            this.s.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ DJIFpvGimbalPitchView a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        this.a.startSendGimbalSpeed(-270);
                    } else if (motionEvent.getAction() == 1) {
                        this.a.stopSendGimbalSpeed(-270);
                    }
                    return true;
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(new Runnable(this) {
            final /* synthetic */ DJIFpvGimbalPitchView a;

            {
                this.a = r1;
            }

            public void run() {
                if (DataGimbalGetPushParams.getInstance().isGetted()) {
                    this.a.onEventMainThread(DataGimbalGetPushParams.getInstance());
                }
                this.a.onEventMainThread(ServiceManager.getInstance().isConnected() ? p.b : p.a);
            }
        }, 100);
    }

    private void a(int i, int i2) {
        DataGimbalSpeedControl.getInstance().setPermission(true).setPitch(i).setRoll(0).setYaw(i2).start();
    }

    private void c() {
        DJILogHelper.getInstance().LOGD(a, "DataGimbalSpeedControl resetGimbalSpeed start");
        DataGimbalSpeedControl.getInstance().setPermission(false).setPitch(0).setRoll(0).setYaw(0).start(new d(this) {
            final /* synthetic */ DJIFpvGimbalPitchView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(DJIFpvGimbalPitchView.a, "DataGimbalSpeedControl onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(DJIFpvGimbalPitchView.a, "DataGimbalSpeedControl fail " + aVar);
            }
        });
    }

    public void startSendGimbalSpeed(final int i) {
        if (this.C != null) {
            this.C.cancel();
        }
        this.C = new Timer();
        this.C.schedule(new TimerTask(this) {
            final /* synthetic */ DJIFpvGimbalPitchView b;

            public void run() {
                this.b.a(i, 0);
            }
        }, 100, 40);
    }

    public void stopSendGimbalSpeed(int i) {
        if (this.C != null) {
            this.C.cancel();
            this.C = null;
        }
        a(i, 0);
        c();
    }

    private void a(int i) {
        if (i <= 5 || i >= 115) {
            if (this.o != 2) {
                this.o = 2;
                this.l.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_end_thumb));
            }
        } else if (25 > i || i > 35) {
            if (this.o != 1) {
                this.o = 1;
                this.l.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_normal_thumb));
            }
        } else if (this.o != 0) {
            this.o = 0;
            this.l.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_middle_thumb));
        }
    }

    private void b(int i) {
        if (this.n != i) {
            this.n = i;
            if (getAlpha() != 1.0f) {
                setMyAlpha(1.0f);
            }
            this.p.removeMessages(4096);
            this.p.sendEmptyMessageDelayed(4096, 3000);
            a(i);
            this.l.setProgress(this.n);
        }
    }

    private void a(float f) {
        int i = 120;
        float f2 = 0.1f * f;
        if (f2 != this.m) {
            int i2;
            this.m = f2;
            if (f2 >= 0.0f) {
                i2 = (int) (30.0f - f2);
            } else {
                i2 = (int) (30.0f - f2);
            }
            if (i2 < 0) {
                i = 0;
            } else if (i2 <= 120) {
                i = i2;
            }
            b(i);
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (!this.q) {
            a((float) dataGimbalGetPushParams.getPitch());
        }
    }

    public void onEventMainThread(p pVar) {
        d();
    }

    private void d() {
        if (b.getInstance().a(null)) {
            this.r.setVisibility(0);
            this.s.setVisibility(0);
            this.l.setProgressDrawable(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_wifi_pgb));
            return;
        }
        this.r.setVisibility(4);
        this.s.setVisibility(4);
        this.l.setProgressDrawable(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_sdr_pgb));
    }
}
