package dji.phone.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.here.odnp.config.OdnpConfigStatic;
import dji.device.widget.VerticalSeekBar;
import dji.midware.util.a.b;
import dji.phone.c.a;
import dji.pilot.fpv.R;
import dji.pilot.fpv.model.f;
import dji.pilot.visual.a.d;
import dji.thirdparty.a.c;

public class DJILPMeterView extends RelativeLayout {
    public static final String a = "DJILPMeterView";
    public static VerticalSeekBar b;
    VerticalSeekBar c;
    ImageView d;
    int e = 0;
    int f = 0;
    ObjectAnimator g;
    ObjectAnimator h;
    float i;
    AnimatorSet j;
    OnSeekBarChangeListener k = new OnSeekBarChangeListener(this) {
        final /* synthetic */ DJILPMeterView a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            a.c().d(this.a.e + i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    private int l;
    private int m;
    private Runnable n = new Runnable(this) {
        final /* synthetic */ DJILPMeterView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c();
            this.a.setAlpha(d.c);
        }
    };

    public DJILPMeterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setLimit(int i, int i2) {
        if (this.m == 0) {
            this.m = i2;
        }
        if (this.l == 0) {
            this.l = i;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d = (ImageView) findViewById(R.id.lp_meter_pos_iv);
        b = (VerticalSeekBar) findViewById(R.id.lp_meter_light_left);
        this.c = (VerticalSeekBar) findViewById(R.id.lp_meter_light_right);
        a();
        c();
        dji.f.a.a(this);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        this.g = ObjectAnimator.ofFloat(this.d, "scaleX", new float[]{0.9f, b.c, 0.9f, b.c, 0.9f}).setDuration(600);
        objectAnimator = new ObjectAnimator();
        this.h = ObjectAnimator.ofFloat(this.d, "scaleY", new float[]{0.9f, b.c, 0.9f, b.c, 0.9f}).setDuration(600);
        this.j = new AnimatorSet();
        this.j.playTogether(new Animator[]{this.g, this.h});
        this.j.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJILPMeterView a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                c.a().e(dji.phone.b.a.CMD_LOCK_AEAF);
            }
        });
        b.setOnSeekBarChangeListener(this.k);
    }

    private void a() {
        if (a.b() && this.f == 0) {
            this.e = a.c().t();
            this.f = a.c().u();
            b.setMax(this.f - this.e);
            this.c.setMax(this.f - this.e);
            b.setProgress(b.getMax() / 2);
            this.c.setProgress(this.c.getMax() / 2);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getHandler().removeCallbacks(this.n);
        dji.f.a.b(this);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onPositionChange() {
        a();
        c();
        if (b()) {
            this.c.setVisibility(4);
            b.setVisibility(0);
            return;
        }
        this.c.setVisibility(0);
        b.setVisibility(4);
    }

    private boolean b() {
        int rotation = (int) getRotation();
        if (rotation == 0) {
            if (getTranslationX() + ((float) getWidth()) > ((float) this.m)) {
                return true;
            }
            return false;
        } else if (rotation == 180) {
            if (getTranslationX() >= ((float) this.l)) {
                return false;
            }
            return true;
        } else if (rotation == 90) {
            if (getTranslationY() + ((float) getWidth()) <= ((float) dji.phone.preview.a.f)) {
                return false;
            }
            return true;
        } else if (rotation != 270) {
            return false;
        } else {
            if (getTranslationY() >= 0.0f) {
                return false;
            }
            return true;
        }
    }

    private void c() {
        b.setSecondaryProgress(0);
        this.c.setSecondaryProgress(0);
    }

    private void a(float f) {
        if (this.i + f > ((float) b.getMax())) {
            this.i = (float) b.getMax();
        } else if (this.i + f < 0.0f) {
            this.i = 0.0f;
        } else {
            this.i += f;
        }
        b.setSecondaryProgress(100);
        this.c.setSecondaryProgress(100);
        b.setProgress((int) this.i);
        this.c.setProgress((int) this.i);
    }

    private void d() {
        getHandler().removeCallbacks(this.n);
        getHandler().postDelayed(this.n, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
    }

    private void e() {
        setAlpha(1.0f);
    }

    public void changeMeterPos(int i, int i2) {
        e();
        setTranslationX((float) (i - (getWidth() / 2)));
        setTranslationY((float) (i2 - (getHeight() / 2)));
        onPositionChange();
        d();
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        if (bVar.b == dji.phone.e.a.a.f) {
            if (a.b()) {
                a.c().a(false);
            }
            this.i = (float) b.getProgress();
        }
    }

    public void onEventMainThread(dji.phone.g.b bVar) {
        if (bVar == dji.phone.g.b.TRACKING) {
            setVisibility(4);
        } else if (bVar == dji.phone.g.b.METERING) {
            setVisibility(0);
        }
    }

    public void onEventMainThread(dji.phone.e.a.d dVar) {
        if (DJILPUISwitcher.a != dji.phone.g.b.TRACKING) {
            e();
            if (dVar.a == dji.phone.e.a.a.b) {
                if (!isShown()) {
                    setVisibility(0);
                }
                MotionEvent motionEvent = (MotionEvent) dVar.b;
                changeMeterPos((int) motionEvent.getX(), (int) motionEvent.getY());
                c.a().e(dji.phone.b.a.CMD_UNLOCK_AEAF);
                this.j.start();
            } else if (dVar.a == dji.phone.e.a.a.a) {
                a(((Float) dVar.b).floatValue() / f.a);
            }
            d();
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        dji.phone.h.a.a((View) this, getRotation(), (float) bVar.b(), null);
    }
}
