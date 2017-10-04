package dji.device.gimbal.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.dji.frame.c.e;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.pilot.fpv.R;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class DJIFpvGimbalPitchViewYawLongan extends DJIRelativeLayout {
    private static final int a = 4096;
    private static final long b = 3000;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int g = 320;
    private static final int h = 0;
    private static final int i = 5;
    private static final int j = 5;
    private int f = 0;
    private SeekBar k = null;
    private float l = Float.MIN_VALUE;
    private int m = 0;
    private int n = 0;
    private a o = null;
    private boolean p = false;
    private OnSeekBarChangeListener q = null;

    private static final class a extends Handler {
        private final WeakReference<DJIFpvGimbalPitchViewYawLongan> a;

        private a(DJIFpvGimbalPitchViewYawLongan dJIFpvGimbalPitchViewYawLongan) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvGimbalPitchViewYawLongan);
        }

        public void handleMessage(Message message) {
            DJIFpvGimbalPitchViewYawLongan dJIFpvGimbalPitchViewYawLongan = (DJIFpvGimbalPitchViewYawLongan) this.a.get();
            if (dJIFpvGimbalPitchViewYawLongan != null) {
                switch (message.what) {
                    case 4096:
                        dJIFpvGimbalPitchViewYawLongan.setAlpha(0.3f);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFpvGimbalPitchViewYawLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = e.b(context, 1.0f);
    }

    public void setPlayBackViewVisible(boolean z) {
        this.p = z;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dispatchOnCreate();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dispatchOnDestroy();
    }

    public void dispatchOnCreate() {
        show();
        setAlpha(0.3f);
        c.a().a(this);
    }

    public void dispatchOnDestroy() {
        c.a().d(this);
    }

    public void show() {
        super.show();
        setAlpha(0.3f);
    }

    private void a() {
        this.o = new a();
        this.q = new OnSeekBarChangeListener(this) {
            final /* synthetic */ DJIFpvGimbalPitchViewYawLongan a;

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
            a();
            this.k = (SeekBar) findViewById(R.id.fpv_gimbal_pitch_sb);
            this.k.setOnSeekBarChangeListener(this.q);
            this.k.setEnabled(false);
            this.k.setMax(g);
            this.k.setProgress(0);
        }
    }

    private void a(int i) {
        if (i <= 5 || i >= d.h) {
            if (this.n != 2) {
                this.n = 2;
                this.k.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_end_thumb));
                this.k.setThumbOffset(this.f);
            }
        } else if (-5 > i || i > 5) {
            if (this.n != 1) {
                this.n = 1;
                this.k.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_normal_thumb));
                this.k.setThumbOffset(this.f);
            }
        } else if (this.n != 0) {
            this.n = 0;
            this.k.setThumb(getResources().getDrawable(R.drawable.fpv_gimbal_pitch_middle_thumb));
            this.k.setThumbOffset(this.f);
        }
    }

    private void b(int i) {
        if (this.m != i) {
            this.m = i;
            if (getAlpha() != 1.0f) {
                setAlpha(1.0f);
            }
            this.o.removeMessages(4096);
            this.o.sendEmptyMessageDelayed(4096, 3000);
            a(i);
            this.k.setProgress(this.m);
        }
    }

    private void a(float f) {
        int i = g;
        float f2 = 0.1f * f;
        if (f2 != this.l) {
            int i2;
            this.l = f2;
            if (f2 >= 0.0f) {
                i2 = (int) (0.0f - f2);
            } else {
                i2 = (int) (0.0f - f2);
            }
            if (i2 < 0) {
                i = 0;
            } else if (i2 <= g) {
                i = i2;
            }
            b(i);
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (!this.p) {
            a((float) dataGimbalGetPushParams.getYaw());
        }
    }
}
