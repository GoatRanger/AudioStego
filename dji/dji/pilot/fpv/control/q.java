package dji.pilot.fpv.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.gs.views.EventView;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot2.simulator.DJISimulatorActivity;
import dji.pilot2.simulator.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class q implements OnClickListener, s {
    public static int a;
    public static int b;
    private DJIRelativeLayout A;
    private DJIImageView B;
    private DJIRelativeLayout C;
    private DJIRelativeLayout D;
    private DJIImageView E;
    private boolean F;
    private boolean G;
    private AnimatorListenerAdapter H;
    private ValueAnimator I;
    private AnimatorUpdateListener J;
    private Handler K;
    protected boolean c;
    private boolean d;
    private boolean e;
    private b f;
    private TimeInterpolator g;
    private TimeInterpolator h;
    private TimeInterpolator i;
    private int j;
    private int k;
    private k l;
    private int[] m;
    private int[] n;
    private int[] o;
    private boolean p;
    private LayoutParams q;
    private LayoutParams r;
    private float s;
    private float t;
    private float u;
    private float v;
    private EventView w;
    private DJIRelativeLayout x;
    private DJIRelativeLayout y;
    private DJIRelativeLayout z;

    public interface b {
        void a(boolean z);
    }

    public enum a {
        SMALL,
        SMALL_HIDE,
        LARGE,
        LARGE_HIDE,
        SWITCH,
        SWITCH_GO,
        SWITCH_SHOW
    }

    public q(Context context, DJIRelativeLayout dJIRelativeLayout, boolean z) {
        this.d = true;
        this.e = true;
        this.g = new DecelerateInterpolator();
        this.h = new AccelerateInterpolator();
        this.i = new LinearInterpolator();
        this.j = 300;
        this.k = 500;
        this.m = new int[2];
        this.n = new int[2];
        this.o = new int[2];
        this.p = false;
        this.F = true;
        this.G = false;
        this.H = new AnimatorListenerAdapter(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.a.i()) {
                }
            }
        };
        this.I = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.J = new AnimatorUpdateListener(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        };
        this.K = new Handler(new Callback(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        this.a.k();
                        break;
                    case 1:
                        a aVar = this.a.d ? a.SMALL : a.LARGE;
                        DJILogHelper.getInstance().LOGD("", "gs camera getmode=" + DataCameraGetPushStateInfo.getInstance().getMode(), false, true);
                        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.PLAYBACK) {
                            if (aVar == a.SMALL) {
                                c.a().e(aVar);
                                break;
                            }
                            c.a().e(aVar);
                        }
                        break;
                    case 2:
                        if (this.a.i()) {
                            if (this.a.d) {
                                this.a.y.setScaleX(1.0f);
                                this.a.y.setScaleY(1.0f);
                                this.a.q.width = q.a;
                                this.a.q.height = q.b;
                                this.a.y.setLayoutParams(this.a.q);
                            } else {
                                this.a.z.setScaleX(1.0f);
                                this.a.z.setScaleY(1.0f);
                                this.a.r.width = q.a;
                                this.a.r.height = q.b;
                                this.a.z.setLayoutParams(this.a.r);
                            }
                        } else if (this.a.D.equals(this.a.y)) {
                            this.a.D.setScaleX(this.a.u);
                            this.a.D.setScaleY(this.a.v);
                        } else {
                            this.a.D.setScaleX(this.a.s);
                            this.a.D.setScaleY(this.a.t);
                        }
                        this.a.D.bringToFront();
                        this.a.a(this.a.D, true);
                        break;
                }
                return false;
            }
        });
        this.F = z;
        a(context, dJIRelativeLayout);
    }

    public q(Context context, DJIRelativeLayout dJIRelativeLayout) {
        this.d = true;
        this.e = true;
        this.g = new DecelerateInterpolator();
        this.h = new AccelerateInterpolator();
        this.i = new LinearInterpolator();
        this.j = 300;
        this.k = 500;
        this.m = new int[2];
        this.n = new int[2];
        this.o = new int[2];
        this.p = false;
        this.F = true;
        this.G = false;
        this.H = /* anonymous class already generated */;
        this.I = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.J = /* anonymous class already generated */;
        this.K = new Handler(/* anonymous class already generated */);
        a(context, dJIRelativeLayout);
    }

    public void a() {
        c.a().d(this);
        this.w = null;
    }

    private void a(Context context, DJIRelativeLayout dJIRelativeLayout) {
        this.x = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.a2i);
        this.A = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.a2j);
        this.y = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.a23);
        this.z = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.a1v);
        this.w = (EventView) dJIRelativeLayout.findViewById(R.id.ak7);
        this.B = (DJIImageView) dJIRelativeLayout.findViewById(R.id.a2n);
        this.E = (DJIImageView) dJIRelativeLayout.findViewById(R.id.a2m);
        if (this.F) {
            this.y.setPivotX((float) DJIPreviewActivity.screenWidth);
            this.y.setPivotY((float) DJIPreviewActivity.screenHeight);
            this.z.setPivotX((float) DJIPreviewActivity.screenWidth);
            this.z.setPivotY((float) DJIPreviewActivity.screenHeight);
        } else {
            this.y.setPivotX(0.0f);
            this.y.setPivotY((float) DJIPreviewActivity.screenHeight);
            this.z.setPivotX(0.0f);
            this.z.setPivotY((float) DJIPreviewActivity.screenHeight);
        }
        a = (int) context.getResources().getDimension(R.dimen.oc);
        b = (int) context.getResources().getDimension(R.dimen.ob);
        this.s = (((float) a) * 1.0f) / ((float) DJIPreviewActivity.screenWidth);
        this.t = (((float) b) * 1.0f) / ((float) DJIPreviewActivity.screenHeight);
        this.u = this.s;
        this.v = this.t;
        this.B.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.w.isDisPatchTouchEvent(true);
        a(this.y, true);
        this.C = this.z;
        this.D = this.y;
        this.I.setInterpolator(this.i);
        this.I.setDuration((long) this.k);
        this.I.addUpdateListener(this.J);
        this.r = (LayoutParams) this.z.getLayoutParams();
        if (i()) {
            this.q = (LayoutParams) this.y.getLayoutParams();
            this.q.width = a;
            this.q.height = b;
            this.y.setLayoutParams(this.q);
        } else {
            this.y.setScaleX(this.u);
            this.y.setScaleY(this.v);
        }
        c.a().a(this);
        if ((context instanceof DJISimulatorActivity) && !i()) {
            this.G = true;
            a(false);
            c.a().e(a.SWITCH_GO);
            dji.pilot.dji_groundstation.controller.a.getInstance(context).a(false);
        }
    }

    private boolean i() {
        return true;
    }

    private void j() {
        if (!this.p) {
            if (this.x.getWidth() != 0) {
                if (this.F) {
                    this.x.setPivotX((float) this.x.getWidth());
                } else {
                    this.x.setPivotX(0.0f);
                }
                this.x.setPivotY((float) this.x.getHeight());
            }
            this.p = true;
        }
    }

    private void k() {
        if (this.m[0] == 0) {
            this.m[0] = (int) ((((float) DJIPreviewActivity.screenWidth) * 1.0f) / 2.0f);
            this.m[1] = (int) ((((float) DJIPreviewActivity.screenHeight) * 1.0f) / 2.0f);
            if (this.F) {
                this.n[0] = (int) (((float) DJIPreviewActivity.screenWidth) * (1.0f - (this.u / 2.0f)));
            } else {
                this.n[0] = (int) (((float) DJIPreviewActivity.screenWidth) * (this.u / 2.0f));
            }
            this.n[1] = (int) (((float) DJIPreviewActivity.screenHeight) * (1.0f - (this.v / 2.0f)));
            this.o[0] = a / 2;
            this.o[1] = b / 2;
        }
        if (this.d) {
            this.l.a(this.o[0], this.o[1]);
        } else {
            this.l.a(this.m[0], this.m[1]);
        }
    }

    private void a(RelativeLayout relativeLayout, boolean z) {
        if (!z) {
            return;
        }
        if ((relativeLayout.getId() == R.id.a23 && i()) || !this.F) {
        }
    }

    public void a(k kVar) {
        this.l = kVar;
        k();
    }

    public void b() {
        b(true);
    }

    private void b(boolean z) {
        c.a().e(a.SWITCH);
        this.d = !this.d;
        if (this.d) {
            this.C = this.z;
            this.D = this.y;
        } else {
            this.C = this.y;
            this.D = this.z;
        }
        if (!i()) {
            this.C.animate().scaleX(1.0f).scaleY(1.0f).setListener(this.H).setDuration((long) this.k).setInterpolator(this.i).start();
        } else if (this.d) {
            this.z.setScaleX(this.u);
            this.z.setScaleY(this.v);
            this.r.width = DJIPreviewActivity.screenWidth;
            this.r.height = DJIPreviewActivity.screenHeight;
            this.z.setLayoutParams(this.r);
            this.z.animate().scaleX(1.0f).scaleY(1.0f).setListener(null).setDuration((long) this.k).setInterpolator(this.i).start();
        } else {
            this.y.setScaleX(this.u);
            this.y.setScaleY(this.v);
            this.q.width = DJIPreviewActivity.screenWidth;
            this.q.height = DJIPreviewActivity.screenHeight;
            this.y.setLayoutParams(this.q);
            this.y.animate().scaleX(1.0f).scaleY(1.0f).setListener(this.H).setDuration((long) this.k).setInterpolator(this.i).start();
        }
        a(this.C, false);
        this.K.sendEmptyMessageDelayed(2, (long) (this.k + 100));
        if (this.d) {
            this.A.show();
        } else {
            this.A.hide();
            e.c(s.dl);
        }
        this.w.isDisPatchTouchEvent(this.d);
        this.K.sendEmptyMessageDelayed(1, (long) this.k);
        this.K.sendEmptyMessageDelayed(0, (long) (this.k + 150));
        if (z && this.f != null) {
            this.f.a(this.d);
        }
    }

    public boolean c() {
        return this.d;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a23:
                if (d.h()) {
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.a = DJIErrorPopView.d.b;
                    bVar.b = R.string.v2_smlt_not_support_open_map;
                    c.a().e(bVar);
                    return;
                } else if (c()) {
                    b();
                    l();
                    return;
                } else {
                    return;
                }
            case R.id.a2m:
                this.l.i();
                return;
            case R.id.a2n:
                this.e = !this.e;
                c(this.e);
                e.c(s.dm);
                return;
            default:
                return;
        }
    }

    public void d() {
    }

    public void e() {
    }

    private void a(View view, boolean z) {
        if (z) {
            view.animate().setDuration((long) this.j).setInterpolator(this.i).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
        } else {
            view.animate().setDuration((long) this.j).setInterpolator(this.i).alpha(0.0f).scaleX(0.0f).scaleY(0.0f).start();
        }
    }

    private void c(boolean z) {
        j();
        if (z) {
            this.B.show();
            this.B.animate().setDuration((long) this.j).setInterpolator(this.i).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                    if (this.a.F) {
                        this.a.B.setBackgroundResource(R.drawable.gs_map_widget_hide_in);
                    }
                    if (this.a.d) {
                        this.a.A.show();
                    }
                    super.onAnimationStart(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    this.a.D.show();
                    this.a.c = false;
                }
            }).translationY(0.0f).translationX(0.0f);
            if (!this.F) {
                this.B.setEnabled(true);
                this.B.animate().alpha(1.0f);
            }
            this.B.animate().start();
        } else {
            float width = (float) (this.x.getWidth() - this.B.getWidth());
            float height = (float) (this.x.getHeight() - this.B.getHeight());
            if (!this.F) {
                width = -width;
            }
            this.D.go();
            this.B.animate().setDuration((long) this.j).setInterpolator(this.i).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                    if (this.a.F) {
                        this.a.B.setBackgroundResource(R.drawable.gs_map_widget_show_out);
                    }
                    super.onAnimationStart(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    this.a.D.go();
                    if (this.a.d) {
                        this.a.A.go();
                    }
                    this.a.c = true;
                    super.onAnimationEnd(animator);
                }
            }).translationX(width).translationY(height);
            if (!this.F) {
                this.B.setEnabled(false);
                this.B.animate().alpha(0.0f);
                c.a().e(a.SWITCH_GO);
                dji.pilot.dji_groundstation.controller.a.getInstance(this.x.getContext()).a(false);
            }
            this.B.animate().start();
        }
        if (this.d) {
            this.w.isDisPatchTouchEvent(z);
        } else {
            this.z.setClickable(z);
        }
        a(this.x, z);
    }

    public void a(boolean z) {
        if (!(this.d || z)) {
            b(true);
        }
        this.B.hide();
        this.x.hide();
        this.D.hide();
        this.A.hide();
    }

    public void f() {
        if (!dji.logic.c.b.getInstance().a(null) && !this.G) {
            if (this.F) {
                this.B.show();
            } else if (this.B.getAlpha() == 1.0f) {
                this.B.show();
            }
            this.x.show();
            if (!this.c) {
                if (this.d) {
                    this.A.show();
                }
                this.D.show();
            }
        }
    }

    public void g() {
        if (this.d) {
            this.A.show();
        }
        this.D.show();
    }

    private void a(float f) {
        if (this.d) {
            this.q.width = (int) (((float) a) + ((((float) DJIPreviewActivity.screenWidth) * (1.0f - this.u)) * (1.0f - f)));
            this.q.height = (int) (((float) b) + ((((float) DJIPreviewActivity.screenHeight) * (1.0f - this.v)) * (1.0f - f)));
        } else {
            this.q.width = (int) (((float) a) + ((((float) DJIPreviewActivity.screenWidth) * (1.0f - this.u)) * f));
            this.q.height = (int) (((float) b) + ((((float) DJIPreviewActivity.screenHeight) * (1.0f - this.v)) * f));
        }
        this.y.setLayoutParams(this.q);
    }

    public boolean h() {
        return this.D.isShown();
    }

    public void onEventMainThread(a aVar) {
        switch (aVar) {
            case SWITCH_SHOW:
                if (!this.G) {
                    this.e = true;
                    c(this.e);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void l() {
        if (Secure.getInt(this.x.getContext().getContentResolver(), "location_mode", 0) == 0) {
            dji.pilot.publics.widget.b.a(this.x.getContext(), (int) R.string.app_tip, (int) R.string.fpv_gs_location_off, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.app_enter, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    if (intent.resolveActivity(this.a.x.getContext().getPackageManager()) != null) {
                        this.a.x.getContext().startActivity(intent);
                        return;
                    }
                    this.a.x.getContext().startActivity(new Intent("android.settings.SETTINGS"));
                }
            }).show();
        }
    }
}
