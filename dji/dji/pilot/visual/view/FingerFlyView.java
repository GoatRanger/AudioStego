package dji.pilot.visual.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushPointState.PointMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.c$a;
import dji.pilot.visual.a.d;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.pilot.visual.beginner.a;
import dji.pilot.visual.view.VisualScreenTouchView.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class FingerFlyView extends DJIRelativeLayout implements g {
    protected static final String a = FingerFlyView.class.getSimpleName();
    private static final boolean b = true;
    private static final long c = 80;
    private DJILinearLayout d = null;
    private DJIImageView e = null;
    private DJIImageView f = null;
    private DJITextView g = null;
    private DJIFingerFlyPreView h = null;
    private DJIVisualHorizontalView i = null;
    private DJIRedTipView j = null;
    private c k;

    public FingerFlyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.k = c.getInstance();
        }
    }

    void a(g$f dji_pilot_visual_a_g_f) {
        if (dji_pilot_visual_a_g_f == g$f.NONE) {
            this.d.go();
            this.h.go();
            this.i.go();
            this.j.go();
            return;
        }
        a(this.k.f());
    }

    void a(g$e dji_pilot_visual_a_g_e) {
        if (dji_pilot_visual_a_g_e == g$e.TRACK_MODE) {
            this.h.go();
            this.i.go();
            this.d.go();
            this.j.go();
            return;
        }
        this.i.show();
        this.j.show();
        onEventMainThread(this.k.i());
    }

    void a(b bVar) {
        if (bVar == b.TRACK_EVENT) {
            this.h.go();
        }
    }

    void a(g$d dji_pilot_visual_a_g_d) {
        if (dji_pilot_visual_a_g_d == g$d.CTRLMODE_CHANGED && !this.k.c()) {
            this.d.go();
        }
    }

    void a() {
        this.i.handleTouchEvent(null, true);
    }

    boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        this.i.handleTouchEvent(motionEvent, false);
        if (action == 0) {
            return false;
        }
        if (action != 1) {
            return action == 2 ? false : false;
        } else {
            if (this.h.isShown()) {
                this.h.dimSelf();
                a.getInstance().b();
            } else {
                a(motionEvent.getX(), motionEvent.getY());
            }
            return true;
        }
    }

    public void onEventMainThread(d.c cVar) {
        float f;
        if (cVar.h) {
            if (this.h.getVisibility() != 0) {
                this.e.clearAnimation();
                this.d.go();
                float[] d = this.k.d(cVar.b, cVar.c, 0.0f, 0.0f);
                f = d[0];
                float f2 = d[1];
                this.h.handleFirst(true);
                this.h.handleMotion(f, f2, true);
            }
        } else if (!this.k.a().a()) {
            this.e.clearAnimation();
            this.d.go();
        } else if (cVar.a == PointMode.CANT_FLY || cVar.a == PointMode.FLYING) {
            boolean z = this.d.getVisibility() == 0;
            this.h.go();
            this.d.show();
            if (cVar.a == PointMode.CANT_FLY) {
                this.f.setBackgroundResource(R.drawable.visual_point_fail);
            } else if (cVar.a == PointMode.FLYING) {
                this.f.setBackgroundResource(R.drawable.visual_point_now);
            }
            if (cVar.g) {
                this.e.go();
                this.e.clearAnimation();
                this.g.show();
                this.g.setText(R.string.visual_point_terrian_follow);
            } else if (cVar.d.b()) {
                this.e.show();
                this.e.startAnimation(getDetourAnim());
                this.e.setRotation(cVar.d.a());
                this.g.setText(R.string.visual_parallel_now);
                this.g.show();
            } else if (cVar.e.b()) {
                this.e.go();
                this.e.clearAnimation();
                this.g.show();
                if (cVar.e == dji.pilot.visual.util.b.a.UP) {
                    this.g.setText(R.string.visual_point_ascending);
                } else if (cVar.e == dji.pilot.visual.util.b.a.DOWN) {
                    this.g.setText(R.string.visual_point_descending);
                }
            } else {
                this.e.clearAnimation();
                this.e.go();
                this.g.go();
            }
            String charSequence = this.g.getText().toString();
            int width = this.f.getWidth();
            if (this.e.getVisibility() == 0) {
                width = this.e.getBackground().getIntrinsicWidth();
            }
            if (this.g.getVisibility() == 0 && !dji.pilot.publics.e.d.a(charSequence)) {
                int measureText = (int) this.g.getPaint().measureText(charSequence);
                if (measureText > width) {
                    width = measureText;
                }
            }
            float[] d2 = this.k.d(cVar.b, cVar.c, 0.0f, 0.0f);
            f = d2[0] - (((float) width) * d.c);
            float height = d2[1] - (((float) this.f.getHeight()) * d.c);
            if (z) {
                this.d.animate().translationX(f).translationY(height).setInterpolator(new DecelerateInterpolator()).setDuration(c).start();
                return;
            }
            this.d.setX(f);
            this.d.setY(height);
        } else {
            this.h.go();
            this.d.go();
        }
    }

    private Animation getDetourAnim() {
        Animation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setDuration(400);
        return alphaAnimation;
    }

    public void onEventMainThread(c$a dji_pilot_visual_a_c_a) {
        if (dji_pilot_visual_a_c_a != null) {
            this.i.onEventMainThread(dji_pilot_visual_a_c_a);
            this.j.handleGimbalChanged(dji_pilot_visual_a_c_a.a);
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!this.k.d()) {
            this.i.onEventMainThread(dataOsdGetPushCommon);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (!this.k.d()) {
            this.i.onEventMainThread(dataOsdGetPushHome);
        }
    }

    private void b() {
        dji.pilot.publics.widget.b.a(getContext(), (int) R.string.visual_point_need_avoid_alert_title, (int) R.string.visual_point_need_avoid_alert_content, (int) R.string.app_cancel, new OnClickListener(this) {
            final /* synthetic */ FingerFlyView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, (int) R.string.app_enter, new OnClickListener(this) {
            final /* synthetic */ FingerFlyView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.k.b(true);
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void a(float f, float f2) {
        DJIErrorPopView.b bVar;
        if (!ServiceManager.getInstance().f()) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.visual_point_fail_title;
            bVar.d = R.string.visual_point_novideo;
            bVar.a = DJIErrorPopView.d.b;
            dji.thirdparty.a.c.a().e(bVar);
        } else if (!dji.pilot.visual.util.c.e()) {
            b();
        } else if (DataEyeGetPushException.getInstance().isInNonFlyZone()) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.visual_point_fail_title;
            bVar.d = R.string.visual_point_non_fly_zone;
            bVar.a = DJIErrorPopView.d.b;
            dji.thirdparty.a.c.a().e(bVar);
        } else if (DataEyeGetPushException.getInstance().isFusionDataAbnormal()) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.visual_point_fail_title;
            bVar.d = R.string.visual_track_exception_nofusion;
            bVar.a = DJIErrorPopView.d.b;
            dji.thirdparty.a.c.a().e(bVar);
        } else if (this.j.isInTipBounds(f, f2)) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.visual_point_fail_title;
            bVar.d = R.string.visual_point_taplimit_tip;
            bVar.a = DJIErrorPopView.d.b;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            boolean a = this.k.a().a();
            this.h.handleFirst(!a);
            RectF locationSelf = this.i.getLocationSelf();
            if (locationSelf.contains(f, f2)) {
                f2 = locationSelf.centerY();
            }
            this.h.handleMotion(f, f2, false);
            if (a) {
                float[] c = this.k.c(f, f2, 0.0f, 0.0f);
                this.k.a().a(c[0], c[1]);
                this.h.dimSelf();
                return;
            }
            a.getInstance().a();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.d = (DJILinearLayout) findViewById(R.id.d9b);
            this.e = (DJIImageView) findViewById(R.id.d9c);
            this.f = (DJIImageView) findViewById(R.id.d9d);
            this.g = (DJITextView) findViewById(R.id.d9e);
            this.h = (DJIFingerFlyPreView) findViewById(R.id.d9a);
            this.i = (DJIVisualHorizontalView) findViewById(R.id.d9_);
            this.j = (DJIRedTipView) findViewById(R.id.d99);
        }
    }
}
