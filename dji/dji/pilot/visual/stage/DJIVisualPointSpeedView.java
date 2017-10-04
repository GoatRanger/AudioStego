package dji.pilot.visual.stage;

import android.content.Context;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.DJIVSeekBar;

public class DJIVisualPointSpeedView extends DJILinearLayout implements g {
    private c a;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJIVSeekBar d = null;
    private dji.publics.widget.DJIVSeekBar.a e = null;
    private boolean f = false;
    private float g = AutoScrollHelper.NO_MAX;
    private float h = AutoScrollHelper.NO_MAX;
    private float i = AutoScrollHelper.NO_MAX;

    public enum a {
        BLINK
    }

    public DJIVisualPointSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.a = c.getInstance();
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.a.a().a(new d(this) {
                final /* synthetic */ DJIVisualPointSpeedView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            }, false);
            onEventMainThread(DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED);
            if (DataOsdGetPushCommon.getInstance().isGetted()) {
                onEventMainThread(DataOsdGetPushCommon.getInstance());
            }
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        float xSpeed = ((float) dataOsdGetPushCommon.getXSpeed()) * 0.1f;
        float ySpeed = ((float) dataOsdGetPushCommon.getYSpeed()) * 0.1f;
        float zSpeed = ((float) dataOsdGetPushCommon.getZSpeed()) * 0.1f;
        if (Math.abs(this.g - xSpeed) >= 0.1f || Math.abs(this.h - ySpeed) >= 0.1f || Math.abs(this.i - zSpeed) >= 0.1f) {
            this.g = xSpeed;
            this.h = ySpeed;
            this.i = zSpeed;
            this.d.setSecondaryProgress(a((float) Math.sqrt((double) (((xSpeed * xSpeed) + (ySpeed * ySpeed)) + (zSpeed * zSpeed)))));
        }
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED) {
            int v = DJIGenSettingDataManager.getInstance().v();
            if (v == 0) {
                this.c.setText("MPH");
            } else if (2 == v) {
                this.c.setText("KM/H");
            } else {
                this.c.setText("M/S");
            }
            a(this.d.getProgress());
        }
    }

    private void a(int i) {
        DJIGenSettingDataManager instance = DJIGenSettingDataManager.getInstance();
        float b = b(i);
        this.b.setText(String.format("%.1f", new Object[]{Float.valueOf(instance.a(b))}));
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (dji_pilot_visual_a_g_d == g$d.USERSPEED_CHANGED) {
            a();
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.BLINK) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(7);
            alphaAnimation.setRepeatMode(2);
            startAnimation(alphaAnimation);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            if (dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().d(this);
            }
            setVisibility(8);
        }
    }

    private void a() {
        this.d.setProgress(a(this.a.a().d()));
    }

    private int getMaxSpeed() {
        if (ProductType.Pomato == i.getInstance().c()) {
            return 120;
        }
        return 90;
    }

    private float b(int i) {
        return (((float) i) / 10.0f) + 1.0f;
    }

    private int a(float f) {
        int max = this.d.getMax();
        int i = ((int) (10.0f * f)) - 10;
        if (i > max) {
            return max;
        }
        return i < 0 ? 0 : i;
    }

    private void b() {
        this.e = new dji.publics.widget.DJIVSeekBar.a(this) {
            final /* synthetic */ DJIVisualPointSpeedView a;

            {
                this.a = r1;
            }

            public void a(DJIVSeekBar dJIVSeekBar) {
                this.a.f = false;
                dji.pilot.visual.beginner.a.getInstance().e();
                this.a.a.a().a(this.a.b(dJIVSeekBar.getProgress()), new d(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.a();
                            }
                        });
                    }
                });
            }

            public void b(DJIVSeekBar dJIVSeekBar) {
                this.a.f = true;
            }

            public void a(DJIVSeekBar dJIVSeekBar, int i, boolean z) {
                this.a.a(i);
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
            this.b = (DJITextView) findViewById(R.id.d9y);
            this.d = (DJIVSeekBar) findViewById(R.id.d9z);
            this.c = (DJITextView) findViewById(R.id.d_0);
            this.d.setMax(getMaxSpeed());
            this.d.setOnChangeListener(this.e);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
