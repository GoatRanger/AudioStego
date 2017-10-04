package dji.pilot.fpv.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import dji.logic.c.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.fpv.control.q;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.sport.MergeSportAttitudeView;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f.j;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;
import dji.pilot.publics.e.f;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class GroupAttitudeView extends DJIRelativeLayout implements s, h<j> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Animation g = null;
    private Animation h = null;
    private boolean i = false;
    private DJIRelativeLayout j;
    private DJILinearLayout k;
    private RelativeLayout l;
    private MergeSportAttitudeView m;
    private View n;
    private ImageView o;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private int s = 0;
    private boolean t = false;
    private RcModeChannel u = RcModeChannel.CHANNEL_UNKNOWN;
    private g v = null;
    private int w = 0;
    private d x = null;

    public GroupAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            bindInfo(a.ViewId_GroupAttiView, j.SHOW, j.HIDE);
            a();
        }
    }

    public void show() {
        if (!isShown() && !b.getInstance().a(null)) {
            super.show();
            clearAnimation();
            startAnimation(this.g);
        }
    }

    public void hide() {
        if (isShown()) {
            super.hide();
            clearAnimation();
            startAnimation(this.h);
        }
    }

    public void go() {
        if (isShown()) {
            super.go();
            clearAnimation();
            startAnimation(this.h);
        }
    }

    private void a() {
        this.f = dji.pilot.fpv.model.b.a(getContext(), R.dimen.fi);
        this.a = dji.pilot.fpv.model.b.a(getContext(), R.dimen.oc) - dji.pilot.fpv.model.b.a(getContext(), R.dimen.fj);
        getResources().getDrawable(R.drawable.sport_attitude_tip_bg);
        this.b = this.f;
        this.c = this.f;
        getResources().getDrawable(R.drawable.sport_attitude_new_bg);
        this.d = 0;
        this.e = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.l = (RelativeLayout) findViewById(R.id.u3);
        this.j = (DJIRelativeLayout) findViewById(R.id.ts);
        this.k = (DJILinearLayout) findViewById(R.id.a2r);
        this.m = (MergeSportAttitudeView) findViewById(R.id.u4);
        this.n = findViewById(R.id.a2p);
        this.o = (ImageView) findViewById(R.id.a2q);
        Resources resources = getContext().getResources();
        this.p = resources.getDimensionPixelSize(R.dimen.iq) / 2;
        this.q = resources.getDimensionPixelSize(R.dimen.is) / 2;
        this.r = resources.getDimensionPixelSize(R.dimen.gw);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GroupAttitudeView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.t && !this.a.i) {
                    this.a.c();
                }
            }
        });
        this.g = AnimationUtils.loadAnimation(getContext(), R.anim.be);
        this.h = AnimationUtils.loadAnimation(getContext(), R.anim.bf);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.j.getLayoutParams();
        marginLayoutParams.leftMargin = this.d;
        marginLayoutParams.bottomMargin = this.e;
        this.j.setLayoutParams(marginLayoutParams);
        marginLayoutParams = (MarginLayoutParams) this.k.getLayoutParams();
        marginLayoutParams.bottomMargin = this.e;
        this.k.setLayoutParams(marginLayoutParams);
    }

    public void setGsOnRight(boolean z) {
        this.i = z;
        if (z) {
            this.k.setTranslationX(0.0f);
            return;
        }
        f.a(this.l, 4);
        this.n.setVisibility(4);
        this.k.setTranslationX((float) (this.a - this.f));
    }

    private void b() {
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        DataOsdGetPushCommon instance2 = DataOsdGetPushCommon.getInstance();
        RcModeChannel modeChannel = instance2.getModeChannel();
        f.a(this.l, 0);
        this.n.setVisibility(0);
        if (instance.isGetted() && instance2.isGetted() && this.l.getVisibility() == 0) {
            if (a && modeChannel == RcModeChannel.CHANNEL_S) {
                this.m.show();
            } else {
                this.m.go();
            }
        }
        this.l.animate().alpha(1.0f).setDuration(300).setListener(new AnimatorListener(this) {
            final /* synthetic */ GroupAttitudeView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.t = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                this.a.t = false;
            }

            public void onAnimationCancel(Animator animator) {
            }
        }).start();
        float f = 0.0f;
        if (this.m.getVisibility() == 0) {
            f = (float) (0 - this.d);
        }
        this.k.animate().translationX(f).setDuration(300).start();
    }

    private void c() {
        c.a().e(q.a.SWITCH_SHOW);
        float f = (float) (this.a - this.f);
        if (this.m.getVisibility() == 0) {
            f = (float) (this.a - this.b);
        }
        this.k.animate().translationX(f).setDuration(300).start();
        this.l.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListener(this) {
            final /* synthetic */ GroupAttitudeView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.t = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                f.a(this.a.l, 4);
                this.a.n.setVisibility(4);
                this.a.t = false;
            }

            public void onAnimationCancel(Animator animator) {
            }
        }).start();
    }

    public void onEventMainThread(q.a aVar) {
        if (aVar == q.a.SWITCH_GO && !this.i) {
            b();
            e.c(s.dr);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar != o.b) {
            return;
        }
        if (b.getInstance().a(null)) {
            go();
        } else if (this.v.a(this.x, 0)) {
            this.k.show();
            show();
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        if (this.u != modeChannel) {
            this.u = modeChannel;
            DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
            boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
            if (this.l.getVisibility() != 0) {
                return;
            }
            if (a && modeChannel == RcModeChannel.CHANNEL_S) {
                this.m.show();
            } else {
                this.m.go();
            }
        }
    }

    public void onEventBackgroundThread(float[] fArr) {
        int i = 0;
        int toDegrees = (int) Math.toDegrees((double) fArr[0]);
        if (toDegrees - this.s > 2 || this.s - toDegrees > 2) {
            i = 1;
        }
        if (i != 0) {
            this.s = toDegrees;
            if (dji.pilot.fpv.model.b.c(getContext()) == 3) {
                toDegrees += 180;
            }
            double toRadians = Math.toRadians((double) ((360 - (toDegrees + 90)) % 360));
            final float sin = (float) (((double) (this.p + this.r)) + (((double) this.p) * Math.sin(toRadians)));
            final float cos = (float) (((double) (this.p + this.r)) - (Math.cos(toRadians) * ((double) this.p)));
            post(new Runnable(this) {
                final /* synthetic */ GroupAttitudeView c;

                public void run() {
                    float f = sin;
                    float f2 = cos - ((float) this.c.q);
                    this.c.o.setX(f - ((float) this.c.q));
                    this.c.o.setY(f2);
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        d();
    }

    private void d() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventMainThread(o.b);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void bind(g gVar, int i) {
        this.v = gVar;
        this.w = i;
    }

    public void bindInfo(a aVar, j jVar, j jVar2) {
        this.x = new d(this, aVar, jVar, jVar2);
    }

    public a getViewId() {
        return this.x.b;
    }

    public d getViewInfo() {
        return this.x;
    }

    public boolean needShow() {
        if (b.getInstance().a(null)) {
            return false;
        }
        return true;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(j jVar) {
        if (jVar != j.SHOW) {
            go();
        } else if (needShow() && this.v.a(this.x, 0)) {
            show();
        }
    }
}
