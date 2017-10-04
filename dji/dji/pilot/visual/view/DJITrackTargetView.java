package dji.pilot.visual.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TargetObjType;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackMode;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.publics.e.d;
import dji.pilot.visual.a.f.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJITrackTargetView extends DJIRelativeLayout {
    private DJILinearLayout a = null;
    private DJILinearLayout b = null;
    private DJIImageView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJIImageView f = null;
    private DJIImageView g = null;
    private int h = 0;
    private float i = 0.0f;
    private int j = 0;
    private int k = 0;
    private final Animation[] l = new Animation[2];
    private Animation m = null;
    private TrackMode n = TrackMode.LOST;
    private TrackException o = TrackException.NONE;
    private float p = 0.0f;
    private float q = 0.0f;
    private final PointF r = new PointF();
    private int s = 27;
    private int t = 30;
    private int u = 24;
    private OnClickListener v = null;

    public DJITrackTargetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJITrackTargetView updateSurface(float f, float f2) {
        if (!(this.p == f && this.q == f2)) {
            this.p = f;
            this.q = f2;
            this.i = (((float) this.h) * 1.0f) / f;
        }
        return this;
    }

    public void setClickListener(OnClickListener onClickListener) {
        this.v = onClickListener;
    }

    public boolean isTrackBeConfirmShowing() {
        return this.n == TrackMode.CONFIRM || this.n == TrackMode.PERSON;
    }

    public PointF getCenter() {
        float x = getX();
        float y = getY();
        this.r.set(x + ((float) (getWidth() / 2)), y + ((float) (getHeight() / 2)));
        return this.r;
    }

    public boolean pointInView2(float f, float f2) {
        float x = getX();
        float y = getY();
        return f >= x && f < x + ((float) getWidth()) && f2 >= y && f2 < ((float) getHeight()) + y && this.n != TrackMode.CONFIRM && this.n != TrackMode.LOST && this.n != TrackMode.PERSON;
    }

    private int a(TrackException trackException, boolean z) {
        if (TrackException.LOW_DETECT == trackException) {
            return R.string.visual_track_exception_lowdetect;
        }
        if (TrackException.SHAKE == trackException) {
            return R.string.visual_track_exception_shake;
        }
        if (TrackException.RC_HALT == trackException) {
            return R.string.visual_tap_to_track;
        }
        if (TrackException.TOO_HIGH == trackException) {
            return R.string.visual_track_exception_toohigh;
        }
        if (TrackException.OBSTACLE == trackException) {
            return R.string.visual_track_exception_obstacle;
        }
        if (TrackException.PITCH_LOW == trackException) {
            return R.string.visual_track_exception_gimbal_pitchlow;
        }
        if (TrackException.TOO_FAR == trackException) {
            return R.string.visual_track_exception_too_far;
        }
        if (TrackException.TOO_CLOSE == trackException) {
            return R.string.visual_track_exception_too_close;
        }
        if (TrackException.DRONE_TOO_HIGH == trackException) {
            return R.string.visual_track_exception_drone_toohigh;
        }
        if (TrackException.DRONE_TOO_LOW == trackException) {
            return R.string.visual_track_exception_drone_toolow;
        }
        if (TrackException.TOO_SMALL == trackException) {
            return R.string.visual_track_exception_toosmall;
        }
        if (TrackException.TOO_LARGE == trackException) {
            return R.string.visual_track_exception_toolarge;
        }
        if (TrackException.NO_FEATURE == trackException) {
            return R.string.visual_track_exception_nodetect;
        }
        if (TrackException.RADAR_FAILED == trackException) {
            return R.string.visual_track_exception_radar_fail;
        }
        return TrackException.NONE == trackException ? !z ? R.string.visual_tracking : R.string.visual_tap_to_track : R.string.visual_tracking;
    }

    public void go() {
        this.n = TrackMode.LOST;
        this.o = TrackException.NONE;
        super.go();
    }

    public void handleTrackRecvChanged(a aVar) {
        boolean z = true;
        TrackMode trackMode = this.n;
        this.n = aVar.a;
        TrackException trackException = this.o;
        this.o = aVar.f;
        if (this.n != TrackMode.LOST) {
            if (aVar.i == TargetObjType.PERSON) {
                this.f.show();
                this.f.setBackgroundResource(R.drawable.visual_track_person);
            } else if (aVar.i == TargetObjType.BOAT) {
                this.f.show();
                this.f.setBackgroundResource(R.drawable.visual_track_boat);
            } else if (aVar.i == TargetObjType.BIKE) {
                this.f.show();
                this.f.setBackgroundResource(R.drawable.visual_track_bike);
            } else if (aVar.i == TargetObjType.CAR) {
                this.f.show();
                this.f.setBackgroundResource(R.drawable.visual_track_car);
            } else if (aVar.i == TargetObjType.VAN) {
                this.f.show();
                this.f.setBackgroundResource(R.drawable.visual_track_trunk);
            } else {
                this.f.go();
            }
            this.b.clearAnimation();
            if (this.n != TrackMode.CONFIRM && this.n != TrackMode.PERSON) {
                this.c.show();
                this.d.hide();
                if (aVar.j.b()) {
                    this.e.setText(R.string.visual_parallel_now);
                    this.e.show();
                    this.g.show();
                    this.g.startAnimation(getDetourAnim());
                } else {
                    this.e.go();
                    this.g.go();
                }
                setEnabled(false);
                if (trackMode != this.n) {
                    if (TrackMode.NORMAL == this.n) {
                        this.b.setBackgroundResource(R.drawable.visual_track_now);
                    } else if (TrackMode.WEAK == this.n) {
                        this.b.setBackgroundResource(R.drawable.visual_track_weak);
                    } else if (TrackMode.DETECT_AFTER_LOST == this.n) {
                        this.b.setBackgroundResource(R.drawable.visual_track_lost_detected);
                    } else if (TrackMode.TRACKING == this.n) {
                        this.b.setBackgroundResource(R.drawable.visual_track_detected_tracking);
                    } else {
                        this.b.setBackgroundResource(R.drawable.visual_track_now);
                    }
                }
            } else if (this.o == TrackException.NONE || TrackException.LOW_DETECT == this.o || TrackException.SHAKE == this.o || TrackException.RC_HALT == this.o || TrackException.APP_HALT == this.o) {
                if (this.o != trackException && this.o == TrackException.RC_HALT) {
                    b bVar = new b();
                    bVar.b = R.string.visual_track_exception_rc_halt;
                    c.a().e(bVar);
                }
                this.b.setBackgroundResource(R.drawable.visual_track_camera_tracking);
                this.c.go();
                this.e.show();
                this.g.go();
                setEnabled(true);
                this.e.setText(a(this.o, true));
                this.d.show();
            } else {
                if (TrackException.TOO_FAR == this.o || TrackException.TOO_CLOSE == this.o) {
                    this.b.setBackgroundResource(R.drawable.visual_track_confirm_weak);
                } else {
                    this.b.setBackgroundResource(R.drawable.visual_track_exception);
                }
                this.c.go();
                this.e.show();
                this.g.go();
                setEnabled(true);
                this.e.setText(a(this.o, false));
                this.d.go();
            }
            a(aVar);
            show();
        } else if ((trackException == TrackException.EXIT_BYSELF || trackException == TrackException.APP_STOP) && ((double) aVar.d) <= 1.0E-6d && ((double) aVar.e) <= 1.0E-6d) {
            go();
        } else if (trackMode != this.n || aVar.h) {
            this.b.setBackgroundResource(R.drawable.visual_track_weak);
            this.c.go();
            this.d.go();
            this.e.show();
            this.f.go();
            this.g.go();
            setEnabled(false);
            this.e.setText(a(this.o, false));
            a(aVar);
            show();
            if (this.m == null) {
                this.m = getLostAnim();
            }
            this.b.startAnimation(this.m);
            b bVar2 = new b();
            bVar2.b = R.string.visual_track_exception_title;
            if (TrackException.TOO_SMALL == this.o) {
                bVar2.d = R.string.visual_track_exception_toosmall;
            } else if (TrackException.TOO_LARGE == this.o) {
                bVar2.d = R.string.visual_track_exception_toolarge;
            } else if (TrackException.NO_FEATURE == this.o) {
                bVar2.d = R.string.visual_track_exception_nodetect;
            } else if (trackMode == TrackMode.LOST || trackMode == TrackMode.CONFIRM || trackMode == TrackMode.PERSON || !DataEyeGetPushException.getInstance().isInTracking() || this.o == TrackException.APP_STOP || this.o == TrackException.EXIT_BYSELF) {
                z = false;
            } else {
                bVar2.d = R.string.visual_track_exception_searching;
            }
            if (z) {
                c.a().e(bVar2);
            }
        }
    }

    private void a(a aVar) {
        int i;
        float f;
        float f2;
        float f3;
        int i2;
        int i3;
        int i4;
        int i5;
        MarginLayoutParams marginLayoutParams;
        MarginLayoutParams marginLayoutParams2;
        MarginLayoutParams marginLayoutParams3;
        MarginLayoutParams marginLayoutParams4;
        MarginLayoutParams marginLayoutParams5;
        int i6;
        LayoutParams layoutParams;
        int width = this.d.getWidth();
        int height = this.d.getHeight();
        String charSequence = this.e.getText().toString();
        if (this.e.getVisibility() != 0 || d.a(charSequence)) {
            i = 0;
        } else {
            i = (int) this.e.getPaint().measureText(charSequence);
        }
        float f4 = aVar.d;
        float f5 = aVar.e;
        float[] b = dji.pilot.visual.a.c.getInstance().b(aVar.b - (dji.pilot.visual.a.d.c * f4), aVar.c - (dji.pilot.visual.a.d.c * f5), f4, f5);
        float f6 = b[0];
        float f7 = b[1];
        float f8 = b[2];
        float f9 = b[3];
        int i7 = (this.j * 2) / 3;
        Object obj = null;
        if (f8 < ((float) this.h)) {
            f4 = this.i;
            obj = 1;
        }
        if (f9 < ((float) this.h)) {
            f5 = this.i;
            obj = 1;
        }
        if (obj != null) {
            b = dji.pilot.visual.a.c.getInstance().b(aVar.b - (dji.pilot.visual.a.d.c * f4), aVar.c - (dji.pilot.visual.a.d.c * f5), f4, f5);
            f6 = b[0];
            f9 = b[1];
            f4 = b[2];
            f = b[3];
            f2 = f4;
            f3 = f9;
        } else {
            f = f9;
            f2 = f8;
            f3 = f7;
        }
        int i8 = (int) f2;
        if (((float) i) > f2) {
            i2 = (int) ((((float) i) - f2) / 2.0f);
            i3 = i;
        } else {
            i2 = 0;
            i3 = i8;
        }
        i8 = this.t;
        int i9 = this.u;
        if (this.g.getVisibility() == 0) {
            this.g.setRotation(aVar.j.a());
            if (aVar.j == dji.pilot.visual.util.b.a.LEFT || aVar.j == dji.pilot.visual.util.b.a.RIGHT) {
                i8 = this.u;
                i4 = this.t;
                i5 = i8;
                marginLayoutParams = (MarginLayoutParams) this.g.getLayoutParams();
                marginLayoutParams2 = (MarginLayoutParams) this.f.getLayoutParams();
                marginLayoutParams3 = (MarginLayoutParams) this.d.getLayoutParams();
                marginLayoutParams4 = (MarginLayoutParams) this.a.getLayoutParams();
                marginLayoutParams5 = (MarginLayoutParams) this.e.getLayoutParams();
                marginLayoutParams.leftMargin = 0;
                marginLayoutParams.topMargin = 0;
                marginLayoutParams2.leftMargin = 0;
                marginLayoutParams2.topMargin = 0;
                marginLayoutParams4.leftMargin = 0;
                marginLayoutParams4.topMargin = 0;
                marginLayoutParams5.topMargin = i7;
                i6 = 0;
                if (this.f.getVisibility() != 0) {
                    i6 = this.s + i7;
                } else {
                    if (aVar.j == dji.pilot.visual.util.b.a.LEFT) {
                        i6 = i5 + i7;
                    }
                }
                if (i6 > 0) {
                    if (((float) i) > f2 || i2 < i6) {
                        marginLayoutParams4.leftMargin = i6 - i2;
                        f6 -= (float) i6;
                    } else {
                        f6 -= (float) i2;
                        marginLayoutParams2.leftMargin = i2 - i6;
                    }
                } else if (((float) i) > f2) {
                    f6 -= (float) i2;
                }
                if (aVar.j == dji.pilot.visual.util.b.a.LEFT) {
                    if (((float) i) > f2 && i2 > i6) {
                        marginLayoutParams.leftMargin = i2 - i6;
                    }
                    marginLayoutParams.topMargin = (int) ((f - ((float) i4)) / 2.0f);
                } else if (aVar.j == dji.pilot.visual.util.b.a.UP) {
                    marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + (f2 / 2.0f)) - ((float) (i5 / 2)));
                    marginLayoutParams2.topMargin = i4 + i7;
                    marginLayoutParams4.topMargin = i4 + i7;
                } else if (aVar.j == dji.pilot.visual.util.b.a.DOWN) {
                    marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + (f2 / 2.0f)) - ((float) (i5 / 2)));
                    marginLayoutParams.topMargin = (int) (((float) i7) + f);
                    marginLayoutParams5.topMargin = (i7 * 2) + i4;
                } else if (aVar.j == dji.pilot.visual.util.b.a.RIGHT) {
                    marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + f2) + ((float) i7));
                    marginLayoutParams.topMargin = (int) ((f - ((float) i4)) / 2.0f);
                }
                if (f2 > ((float) (this.k + width)) || f <= ((float) (this.k + height))) {
                    marginLayoutParams3.leftMargin = ((int) ((((float) i3) - ((((float) i3) - f2) / 2.0f)) + ((float) this.j))) + marginLayoutParams4.leftMargin;
                    if (aVar.j == dji.pilot.visual.util.b.a.RIGHT) {
                        marginLayoutParams3.leftMargin += i5 + i7;
                    }
                    marginLayoutParams3.topMargin = ((int) ((f - ((float) height)) / 2.0f)) + marginLayoutParams4.topMargin;
                    this.d.setBackgroundResource(R.drawable.visual_go_bg);
                } else {
                    marginLayoutParams3.leftMargin = ((i3 - width) / 2) + marginLayoutParams4.leftMargin;
                    marginLayoutParams3.topMargin = ((int) ((f - ((float) height)) / 2.0f)) + marginLayoutParams4.topMargin;
                    this.d.setBackgroundResource(0);
                }
                this.e.setLayoutParams(marginLayoutParams5);
                this.g.setLayoutParams(marginLayoutParams);
                this.f.setLayoutParams(marginLayoutParams2);
                this.a.setLayoutParams(marginLayoutParams4);
                this.d.setLayoutParams(marginLayoutParams3);
                layoutParams = this.b.getLayoutParams();
                if (!(((float) layoutParams.width) == f2 && ((float) layoutParams.height) == f)) {
                    layoutParams.width = (int) f2;
                    layoutParams.height = (int) f;
                    this.b.setLayoutParams(layoutParams);
                }
                setX(f6);
                setY(f3);
            }
        }
        i4 = i9;
        i5 = i8;
        marginLayoutParams = (MarginLayoutParams) this.g.getLayoutParams();
        marginLayoutParams2 = (MarginLayoutParams) this.f.getLayoutParams();
        marginLayoutParams3 = (MarginLayoutParams) this.d.getLayoutParams();
        marginLayoutParams4 = (MarginLayoutParams) this.a.getLayoutParams();
        marginLayoutParams5 = (MarginLayoutParams) this.e.getLayoutParams();
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.topMargin = 0;
        marginLayoutParams2.leftMargin = 0;
        marginLayoutParams2.topMargin = 0;
        marginLayoutParams4.leftMargin = 0;
        marginLayoutParams4.topMargin = 0;
        marginLayoutParams5.topMargin = i7;
        i6 = 0;
        if (this.f.getVisibility() != 0) {
            if (aVar.j == dji.pilot.visual.util.b.a.LEFT) {
                i6 = i5 + i7;
            }
        } else {
            i6 = this.s + i7;
        }
        if (i6 > 0) {
            if (((float) i) > f2) {
            }
            marginLayoutParams4.leftMargin = i6 - i2;
            f6 -= (float) i6;
        } else if (((float) i) > f2) {
            f6 -= (float) i2;
        }
        if (aVar.j == dji.pilot.visual.util.b.a.LEFT) {
            marginLayoutParams.leftMargin = i2 - i6;
            marginLayoutParams.topMargin = (int) ((f - ((float) i4)) / 2.0f);
        } else if (aVar.j == dji.pilot.visual.util.b.a.UP) {
            marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + (f2 / 2.0f)) - ((float) (i5 / 2)));
            marginLayoutParams2.topMargin = i4 + i7;
            marginLayoutParams4.topMargin = i4 + i7;
        } else if (aVar.j == dji.pilot.visual.util.b.a.DOWN) {
            marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + (f2 / 2.0f)) - ((float) (i5 / 2)));
            marginLayoutParams.topMargin = (int) (((float) i7) + f);
            marginLayoutParams5.topMargin = (i7 * 2) + i4;
        } else if (aVar.j == dji.pilot.visual.util.b.a.RIGHT) {
            marginLayoutParams.leftMargin = (int) ((((float) (marginLayoutParams4.leftMargin + i2)) + f2) + ((float) i7));
            marginLayoutParams.topMargin = (int) ((f - ((float) i4)) / 2.0f);
        }
        if (f2 > ((float) (this.k + width))) {
        }
        marginLayoutParams3.leftMargin = ((int) ((((float) i3) - ((((float) i3) - f2) / 2.0f)) + ((float) this.j))) + marginLayoutParams4.leftMargin;
        if (aVar.j == dji.pilot.visual.util.b.a.RIGHT) {
            marginLayoutParams3.leftMargin += i5 + i7;
        }
        marginLayoutParams3.topMargin = ((int) ((f - ((float) height)) / 2.0f)) + marginLayoutParams4.topMargin;
        this.d.setBackgroundResource(R.drawable.visual_go_bg);
        this.e.setLayoutParams(marginLayoutParams5);
        this.g.setLayoutParams(marginLayoutParams);
        this.f.setLayoutParams(marginLayoutParams2);
        this.a.setLayoutParams(marginLayoutParams4);
        this.d.setLayoutParams(marginLayoutParams3);
        layoutParams = this.b.getLayoutParams();
        layoutParams.width = (int) f2;
        layoutParams.height = (int) f;
        this.b.setLayoutParams(layoutParams);
        setX(f6);
        setY(f3);
    }

    private Animation getDetourAnim() {
        Animation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setDuration(400);
        return alphaAnimation;
    }

    private Animation getLostAnim() {
        Animation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setRepeatCount(5);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setDuration(300);
        alphaAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJITrackTargetView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.go();
            }
        });
        return alphaAnimation;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJILinearLayout) findViewById(R.id.d_s);
            this.b = (DJILinearLayout) findViewById(R.id.d_t);
            this.c = (DJIImageView) findViewById(R.id.d_u);
            this.d = (DJITextView) findViewById(R.id.d_y);
            this.e = (DJITextView) findViewById(R.id.d_v);
            this.f = (DJIImageView) findViewById(R.id.d_w);
            this.g = (DJIImageView) findViewById(R.id.d_x);
            Resources resources = getContext().getResources();
            Drawable drawable = resources.getDrawable(R.drawable.visual_enter);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.nu);
            this.h = drawable.getIntrinsicWidth() + (dimensionPixelSize * 2);
            this.j = resources.getDimensionPixelSize(R.dimen.n4);
            this.k = dimensionPixelSize * 2;
            drawable = resources.getDrawable(R.drawable.visual_track_person);
            if (drawable != null) {
                this.s = drawable.getIntrinsicWidth();
            }
            Drawable drawable2 = resources.getDrawable(R.drawable.visual_track_detour);
            if (drawable2 != null) {
                this.t = drawable2.getIntrinsicWidth();
                this.u = drawable2.getIntrinsicHeight();
            }
            this.l[0] = AnimationUtils.loadAnimation(getContext(), R.anim.g);
            this.l[1] = AnimationUtils.loadAnimation(getContext(), R.anim.g);
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJITrackTargetView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.v.onClick(view);
                }
            });
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }
}
