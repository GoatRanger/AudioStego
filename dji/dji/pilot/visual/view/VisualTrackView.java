package dji.pilot.visual.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.d;
import dji.pilot.visual.a.f;
import dji.pilot.visual.a.f.a;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.pilot.visual.util.VisualDrawOnScreen;
import dji.pilot.visual.view.VisualScreenTouchView.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;

public class VisualTrackView extends DJIRelativeLayout implements g {
    private DJITrackTargetView a = null;
    private VisualDrawOnScreen b = null;
    private DJIImageView c = null;
    private DJILinearLayout d = null;
    private DJICircleSeekBar e = null;
    private c f;
    private AnimatorListener g = null;

    public VisualTrackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.f = c.getInstance();
        }
    }

    private void a() {
        ViewParent parent = getParent();
        if (parent instanceof VisualScreenTouchView) {
            ((VisualScreenTouchView) parent).a(b.TRACK_EVENT);
        }
    }

    void a(g$f dji_pilot_visual_a_g_f) {
        if (dji_pilot_visual_a_g_f == g$f.NONE) {
            this.a.go();
            this.b.go();
            this.d.go();
        }
    }

    void a(g$e dji_pilot_visual_a_g_e) {
        if (dji_pilot_visual_a_g_e == g$e.POINT_MODE) {
            this.b.resetPath();
            this.b.go();
            this.a.go();
            this.d.go();
        } else if (dji_pilot_visual_a_g_e == g$e.TRACK_MODE) {
            onEventMainThread(this.f.b().d());
        }
    }

    void a(b bVar) {
        if (bVar == b.POINT_EVENT) {
            this.b.resetPath();
            this.b.go();
        }
    }

    public void onEventMainThread(a aVar) {
        this.a.updateSurface((float) DJIBaseActivity.screenWidth, (float) DJIBaseActivity.screenHeight);
        this.a.handleTrackRecvChanged(aVar);
        if (!dji.pilot.visual.util.c.e(null) || aVar.k != TrackingMode.a || !this.f.b().b()) {
            this.d.go();
        } else if (this.d.getVisibility() != 0) {
            this.e.setValue(0);
            this.d.show();
        }
    }

    boolean a(MotionEvent motionEvent) {
        return this.a.getVisibility() == 0 && this.a.pointInView2(motionEvent.getX(), motionEvent.getY());
    }

    PointF getTargetCenter() {
        return this.a.getCenter();
    }

    boolean b(MotionEvent motionEvent) {
        float f = 0.0f;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.b.resetPath();
            this.b.startMotionXY(motionEvent.getX(), motionEvent.getY());
            dji.pilot.visual.beginner.a.getInstance().f();
            return false;
        } else if (action != 1) {
            if (action == 2) {
                int i = this.f.c[1] - this.f.c[0];
                int i2 = this.f.b[1] - this.f.b[0];
                float x = motionEvent.getX();
                if (x < 0.0f) {
                    x = 0.0f;
                } else if (x > ((float) i2)) {
                    x = (float) i2;
                }
                float y = motionEvent.getY();
                if (y >= 0.0f) {
                    f = y > ((float) i) ? (float) i : y;
                }
                if (this.b.updateMotionXY(x, f)) {
                    this.b.show();
                    a();
                    return true;
                }
            }
            return false;
        } else if (this.b.needDraw()) {
            this.b.stopMotionXY();
            this.b.resetPath();
            this.b.go();
            a(false, 0.0f, 0.0f);
            return true;
        } else if (this.a.isTrackBeConfirmShowing()) {
            getController().g();
            return true;
        } else {
            a(true, motionEvent.getX(), motionEvent.getY());
            return false;
        }
    }

    private f getController() {
        return this.f.b();
    }

    private void a(boolean z, float f, float f2) {
        float f3 = 0.0f;
        if (z) {
            this.c.setAlpha(1.0f);
            this.c.setX(f - ((float) (this.c.getWidth() / 2)));
            this.c.setY(f2 - ((float) (this.c.getHeight() / 2)));
            this.c.show();
            this.c.animate().alpha(0.2f).setDuration(300).setListener(this.g).start();
        }
        if (dji.pilot.visual.util.c.a(getContext(), null)) {
            float f4;
            if (z) {
                dji.pilot.visual.beginner.a.getInstance().g();
                f4 = 0.0f;
            } else {
                f3 = this.b.getMinX();
                f4 = this.b.getMaxX();
                float minY = this.b.getMinY();
                float maxY = this.b.getMaxY();
                f = (f3 + f4) * d.c;
                f2 = (minY + maxY) * d.c;
                f4 -= f3;
                f3 = maxY - minY;
            }
            float[] a = this.f.a(f, f2, f4, f3);
            getController().a(a[0], a[1], a[2], a[3]);
        }
    }

    private float a(int i) {
        return (((((float) (i + 100)) * 1.0f) / 200.0f) * (m[1] - m[0])) + m[0];
    }

    private int a(float f) {
        return (int) ((((f - m[0]) / (m[1] - m[0])) * 200.0f) - DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJITrackTargetView) findViewById(R.id.da2);
            this.b = (VisualDrawOnScreen) findViewById(R.id.da0);
            this.c = (DJIImageView) findViewById(R.id.da1);
            this.d = (DJILinearLayout) findViewById(R.id.da3);
            this.e = (DJICircleSeekBar) findViewById(R.id.da5);
            this.e.setRange(-100, 100);
            this.e.setValue(0);
            this.e.setOnValueChanged(new DJICircleSeekBar.a(this) {
                final /* synthetic */ VisualTrackView a;

                {
                    this.a = r1;
                }

                public void a(View view, int i, boolean z) {
                    this.a.f.b().a(this.a.a(i), new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }
            });
            this.a.setClickListener(new OnClickListener(this) {
                final /* synthetic */ VisualTrackView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.getController().f();
                }
            });
            this.g = new AnimatorListener(this) {
                final /* synthetic */ VisualTrackView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    this.a.c.go();
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            };
        }
    }
}
