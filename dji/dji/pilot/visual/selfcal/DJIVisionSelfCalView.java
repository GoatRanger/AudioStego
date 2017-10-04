package dji.pilot.visual.selfcal;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataEyeEasySelfCalibration;
import dji.midware.data.model.P3.DataEyeEasySelfCalibration.SelfRequest;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration.CaliStatusCode;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration.VisionSensorType;
import dji.pilot.R;
import dji.pilot.fpv.d.c$v;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.widget.DJISquareLayout;
import dji.pilot.visual.a.d;
import dji.pilot.visual.util.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;
import java.util.Map;

public class DJIVisionSelfCalView extends DJILinearLayout implements OnClickListener {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJILinearLayout c = null;
    private DJIImageView d = null;
    private DJITextView e = null;
    private DJISquareLayout f = null;
    private DJIImageView g = null;
    private DJILinearLayout h = null;
    private DJIImageView i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJIRelativeLayout l = null;
    private DJITextView m = null;
    private DJILinearLayout n = null;
    private ProgressBar o = null;
    private DJITextView p = null;
    private final dji.pilot.visual.b.a q = new dji.pilot.visual.b.a();
    private final dji.pilot.visual.b.a r = new dji.pilot.visual.b.a();
    private a s = null;
    private long t = 0;
    private Context u = null;
    private boolean v = false;
    private final Rect w = new Rect();
    private Animator x = null;
    private boolean y = true;

    public interface a {
        void a(int i);
    }

    public DJIVisionSelfCalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.u = context;
    }

    public DJIVisionSelfCalView setOnSelfCalListener(a aVar) {
        this.s = aVar;
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJITextView) findViewById(R.id.d7r);
        this.b = (DJIImageView) findViewById(R.id.d7s);
        this.c = (DJILinearLayout) findViewById(R.id.d7t);
        this.d = (DJIImageView) findViewById(R.id.d7u);
        this.e = (DJITextView) findViewById(R.id.d7x);
        this.f = (DJISquareLayout) findViewById(R.id.d7v);
        this.g = (DJIImageView) findViewById(R.id.d7w);
        this.h = (DJILinearLayout) findViewById(R.id.d7y);
        this.i = (DJIImageView) findViewById(R.id.d7z);
        this.j = (DJITextView) findViewById(R.id.d80);
        this.k = (DJITextView) findViewById(R.id.d81);
        this.l = (DJIRelativeLayout) findViewById(R.id.d82);
        this.m = (DJITextView) findViewById(R.id.d83);
        this.n = (DJILinearLayout) findViewById(R.id.d84);
        this.o = (ProgressBar) findViewById(R.id.d85);
        this.p = (DJITextView) findViewById(R.id.d86);
        this.b.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    public void onEventMainThread(DataEyeGetPushEasySelfCalibration dataEyeGetPushEasySelfCalibration) {
        Object obj = 1;
        this.r.a(dataEyeGetPushEasySelfCalibration.getTinkCount(), dataEyeGetPushEasySelfCalibration.getSensorType(), dataEyeGetPushEasySelfCalibration.getCaliStatusCode(), dataEyeGetPushEasySelfCalibration.getProgress());
        c.a(this.r.toString());
        if (!this.q.equals(this.r)) {
            Object obj2;
            Object obj3 = this.q.c != this.r.c ? 1 : null;
            if (obj3 != null) {
                if (CaliStatusCode.WaitingMove == this.r.c) {
                    this.t = System.currentTimeMillis();
                } else if (CaliStatusCode.Success == this.r.c || CaliStatusCode.WaitingNext == this.r.c) {
                    if (0 != this.t) {
                        float currentTimeMillis = (((float) (System.currentTimeMillis() - this.t)) * 1.0f) / 1000.0f;
                        Map hashMap = new HashMap();
                        hashMap.put(c$v.a, String.valueOf(currentTimeMillis));
                        e.a(c$v.b, hashMap);
                    }
                    e.a(c$v.c);
                    this.t = 0;
                } else if (CaliStatusCode.CalulateTimeOut == this.r.c) {
                    e.a(c$v.d);
                    this.t = 0;
                } else if (CaliStatusCode.AlreadyCali == this.r.c || CaliStatusCode.ParamDiffSerious == this.q.c) {
                    e.a(c$v.e);
                    this.t = 0;
                }
            }
            if (this.q.b != this.r.b) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (this.q.d == this.r.d) {
                obj = null;
            }
            this.q.a(this.r);
            if (obj3 != null) {
                a();
            }
            if (obj2 != null) {
                b();
            }
            if (obj != null) {
                c();
            }
        }
    }

    private void a() {
        CaliStatusCode caliStatusCode = this.q.c;
        if (CaliStatusCode.NotCalibrating == caliStatusCode) {
            d();
        } else if (CaliStatusCode.WaitingMove == caliStatusCode) {
            a((int) R.string.vision_selfcal_waiting_move);
        } else if (CaliStatusCode.CollectImage == caliStatusCode) {
            a((int) R.string.vision_selfcal_collect_image);
        } else if (CaliStatusCode.MoveWrong == caliStatusCode) {
            a((int) R.string.vision_selfcal_move_wrong);
        } else if (CaliStatusCode.MoveTooFast == caliStatusCode) {
            a((int) R.string.vision_selfcal_move_toofast);
        } else if (CaliStatusCode.GroundDetailTooLess == caliStatusCode) {
            a((int) R.string.vision_selfcal_ground_custom);
        } else if (CaliStatusCode.LightEnviromentInvalid == caliStatusCode) {
            a((int) R.string.vision_selfcal_environment);
        } else if (CaliStatusCode.FeatureLess == caliStatusCode) {
            a((int) R.string.vision_selfcal_feature_less);
        } else if (CaliStatusCode.DiffLarge == caliStatusCode) {
            a((int) R.string.vision_selfcal_diff_large);
        } else if (CaliStatusCode.Caculating == caliStatusCode) {
            e();
        } else if (CaliStatusCode.WaitingNext == caliStatusCode) {
            f();
        } else if (CaliStatusCode.Success == caliStatusCode) {
            g();
        } else if (CaliStatusCode.CalulateTimeOut == caliStatusCode) {
            h();
        } else if (CaliStatusCode.AlreadyCali == caliStatusCode || CaliStatusCode.ParamDiffSerious == caliStatusCode) {
            a(caliStatusCode);
        } else {
            a((int) R.string.vision_selfcal_waiting_move);
        }
    }

    private void b() {
        this.a.setText(this.u.getString(R.string.vision_selfcal_title, new Object[]{i()}));
        this.e.setText(j());
        int[] k = k();
        this.g.setBackgroundResource(k[0]);
        this.g.setRotation((float) k[1]);
    }

    private void c() {
        int i = this.q.d;
        if (i < 0) {
            i = 0;
        } else if (i > this.o.getMax()) {
            i = this.o.getMax();
        }
        this.o.setProgress(i);
    }

    private void d() {
        this.b.show();
        this.c.show();
        this.l.go();
        this.l.show();
        this.n.go();
        p();
        m();
    }

    private void a(int i) {
        this.b.show();
        this.c.show();
        this.h.go();
        this.l.go();
        this.n.show();
        this.p.setText(i);
        o();
        m();
    }

    private void e() {
        this.b.go();
        this.c.go();
        this.h.show();
        this.l.go();
        this.n.show();
        this.i.setBackgroundResource(R.drawable.vision_selfcal_processing);
        this.j.setText(R.string.vision_selfcal_collect_completed);
        this.k.go();
        this.p.setText(R.string.vision_selfcal_calculating);
        p();
        l();
    }

    private void f() {
        this.b.show();
        this.c.go();
        this.h.show();
        this.l.go();
        this.n.go();
        this.i.setBackgroundResource(R.drawable.vision_selfcal_success);
        this.j.setText(this.u.getString(R.string.vision_selfcal_success, new Object[]{i()}));
        this.k.show();
        this.k.setText(R.string.vision_selfcal_next);
        p();
        m();
    }

    private void g() {
        this.b.show();
        this.c.go();
        this.h.show();
        this.l.go();
        this.n.go();
        this.i.setBackgroundResource(R.drawable.vision_selfcal_success);
        this.j.setText(this.u.getString(R.string.vision_selfcal_success, new Object[]{i()}));
        this.k.show();
        this.k.setText(R.string.vision_selfcal_return_fpv);
        p();
        m();
    }

    private void h() {
        this.b.show();
        this.c.go();
        this.h.show();
        this.l.go();
        this.n.go();
        this.i.setBackgroundResource(R.drawable.vision_selfcal_failure);
        this.j.setText(this.u.getString(R.string.vision_selfcal_fail, new Object[]{i()}));
        this.k.show();
        this.k.setText(R.string.vision_selfcal_retry);
        p();
        m();
    }

    private void a(CaliStatusCode caliStatusCode) {
        this.b.show();
        this.c.go();
        this.h.show();
        this.l.go();
        this.n.go();
        this.i.setBackgroundResource(R.drawable.vision_selfcal_failure);
        if (CaliStatusCode.ParamDiffSerious == caliStatusCode) {
            this.j.setText(this.u.getString(R.string.vision_selfcal_param_diff_serious));
        } else {
            this.j.setText(this.u.getString(R.string.vision_selfcal_assistant));
        }
        this.k.show();
        this.k.setText(R.string.vision_selfcal_return_fpv);
        p();
        m();
    }

    private String i() {
        if (VisionSensorType.Bottom == this.q.b) {
            return this.u.getString(R.string.vision_type_down);
        }
        if (VisionSensorType.Backward == this.q.b) {
            return this.u.getString(R.string.vision_type_back);
        }
        if (VisionSensorType.Left == this.q.b) {
            return this.u.getString(R.string.vision_type_left);
        }
        if (VisionSensorType.Right == this.q.b) {
            return this.u.getString(R.string.vision_type_right);
        }
        if (VisionSensorType.Top == this.q.b) {
            return this.u.getString(R.string.vision_type_up);
        }
        if (VisionSensorType.Forward == this.q.b) {
            return this.u.getString(R.string.vision_type_front);
        }
        return this.u.getString(R.string.vision_type_front);
    }

    private int j() {
        if (VisionSensorType.Bottom == this.q.b) {
            return R.string.vision_selfcal_desc_bottom;
        }
        if (VisionSensorType.Backward == this.q.b) {
            return R.string.vision_selfcal_desc_back;
        }
        if (VisionSensorType.Forward == this.q.b) {
            return R.string.vision_selfcal_desc_front;
        }
        if (VisionSensorType.Left == this.q.b) {
            return R.string.vision_selfcal_desc_left;
        }
        if (VisionSensorType.Right == this.q.b) {
            return R.string.vision_selfcal_desc_right;
        }
        if (VisionSensorType.Top == this.q.b) {
            return R.string.vision_selfcal_desc_top;
        }
        return R.string.vision_selfcal_desc_front;
    }

    private int[] getCurrentDefaultImg() {
        if (VisionSensorType.Bottom == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_down, 0};
        }
        if (VisionSensorType.Backward == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_front, 180};
        }
        if (VisionSensorType.Forward == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_front, 0};
        }
        if (VisionSensorType.Left == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_front, 90};
        }
        if (VisionSensorType.Right == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_front, -90};
        }
        if (VisionSensorType.Top == this.q.b) {
            return new int[]{R.drawable.vision_selfcal_down, 180};
        }
        return new int[]{R.drawable.vision_selfcal_front, 0};
    }

    private int[] getCurrentWm220SensorImg() {
        if (VisionSensorType.Bottom == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_down, 0};
        }
        if (VisionSensorType.Backward == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_front, 180};
        }
        if (VisionSensorType.Forward == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_front, 0};
        }
        if (VisionSensorType.Left == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_front, 90};
        }
        if (VisionSensorType.Right == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_front, -90};
        }
        if (VisionSensorType.Top == this.q.b) {
            return new int[]{R.drawable.kumquat_vision_selfcal_down, 180};
        }
        return new int[]{R.drawable.kumquat_vision_selfcal_front, 0};
    }

    private int[] k() {
        if (dji.pilot.publics.e.a.c(i.getInstance().c())) {
            return getCurrentWm220SensorImg();
        }
        return getCurrentDefaultImg();
    }

    private void l() {
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, d.c, 1, d.c);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(1);
        this.i.startAnimation(rotateAnimation);
    }

    private void m() {
        this.i.clearAnimation();
    }

    private Animator n() {
        this.f.getHitRect(this.w);
        int a = b.a(getContext(), R.dimen.fo) / 2;
        this.w.inset(a, a);
        a = this.g.getWidth() / 2;
        c.a(this.w.toString() + com.alipay.sdk.j.i.b + a);
        this.g.setTranslationX((float) (this.w.left - a));
        this.g.setTranslationY((float) (this.w.bottom - a));
        ObjectAnimator.ofFloat(this.g, "translationY", new float[]{(float) (this.w.bottom - a), (float) (this.w.top - a)}).setDuration(2000);
        ObjectAnimator.ofFloat(this.g, "translationX", new float[]{(float) (this.w.left - a), (float) (this.w.right - a)}).setDuration(2000);
        ObjectAnimator.ofFloat(this.g, "translationY", new float[]{(float) (this.w.top - a), (float) (this.w.bottom - a)}).setDuration(2000);
        ObjectAnimator.ofFloat(this.g, "translationX", new float[]{(float) (this.w.right - a), (float) (this.w.left - a)}).setDuration(2000);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r1, r2, r3, r0});
        animatorSet.addListener(new AnimatorListener(this) {
            final /* synthetic */ DJIVisionSelfCalView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.y = false;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.a.y) {
                    animator.start();
                }
            }

            public void onAnimationCancel(Animator animator) {
                this.a.y = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        return animatorSet;
    }

    private void o() {
        if (!this.v) {
            this.d.postDelayed(new Runnable(this) {
                final /* synthetic */ DJIVisionSelfCalView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.o();
                }
            }, 200);
        } else if (this.x == null) {
            this.y = false;
            this.x = n();
            this.x.start();
        }
    }

    private void p() {
        if (this.x != null) {
            this.y = true;
            this.x.cancel();
            this.x = null;
        }
    }

    private void q() {
        if (CaliStatusCode.CalulateTimeOut == this.q.c || CaliStatusCode.Success == this.q.c || CaliStatusCode.NotCalibrating == this.q.c || CaliStatusCode.AlreadyCali == this.q.c || CaliStatusCode.ParamDiffSerious == this.q.c) {
            r();
        } else {
            dji.pilot.publics.widget.b.a(getContext(), (int) R.string.vision_selfcal_exit_title, (int) R.string.vision_selfcal_exit_content, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisionSelfCalView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.app_enter, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisionSelfCalView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.r();
                }
            }).show();
        }
    }

    private void r() {
        this.o.setProgress(0);
        DataEyeEasySelfCalibration.getInstance().a(SelfRequest.c).start(null);
        if (this.s != null) {
            this.s.a(0);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.d7s == id) {
            q();
        } else if (R.id.d83 == id) {
            DataEyeEasySelfCalibration.getInstance().a(SelfRequest.b).start(null);
        } else if (R.id.d81 != id) {
        } else {
            if (CaliStatusCode.Success == this.q.c || CaliStatusCode.AlreadyCali == this.q.c || CaliStatusCode.ParamDiffSerious == this.q.c) {
                r();
            } else {
                DataEyeEasySelfCalibration.getInstance().a(SelfRequest.b).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DJIVisionSelfCalView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        c.a("Self Cal start success");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        c.a("Self Cal start fail-" + aVar);
                    }
                });
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.v = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        if (!isInEditMode()) {
            onEventMainThread(DataEyeGetPushEasySelfCalibration.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
