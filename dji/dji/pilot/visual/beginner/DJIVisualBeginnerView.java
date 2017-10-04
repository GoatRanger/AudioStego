package dji.pilot.visual.beginner;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelType;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.pilot.publics.widget.b;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.pilot.visual.stage.DJIVisualPointSpeedView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;
import java.util.Locale;

public class DJIVisualBeginnerView extends DJIRelativeLayout implements OnClickListener, a, b {
    private static final float D = 0.3f;
    private static final float E = 0.26f;
    private static final float F = 0.4583f;
    private static final int af = 4096;
    private static final long ag = 80;
    private static final int ah = 8192;
    private static final long ai = 2000;
    private static final int aj = 12288;
    private static final long ak = 2000;
    private static final int al = 16384;
    private static final long am = 1500;
    private static final int an = 36864;
    private static final long ao = 100;
    private static final int ap = 36880;
    private DJIRelativeLayout G = null;
    private DJIBeginnerTrackDrawView H = null;
    private DJIBeginnerPointGuideView I = null;
    private DJIImageView J = null;
    private DJIImageView K = null;
    private DJIImageView L = null;
    private DJIBeginnerStickView M = null;
    private DJILinearLayout N = null;
    private DJIImageView O = null;
    private DJITextView P = null;
    private DJITextView Q = null;
    private DJILinearLayout R = null;
    private DJITextView S = null;
    private DJIImageView T = null;
    private int U = Integer.MIN_VALUE;
    private int V = Integer.MIN_VALUE;
    private c W = null;
    private a aa = null;
    private k ab = null;
    private final Rect ac = new Rect();
    private b ad = null;
    private int ae = 0;
    private Callback aq = new Callback(this) {
        final /* synthetic */ DJIVisualBeginnerView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            if (4096 <= message.what && message.what <= 4110) {
                this.a.a(message.what - 4096, false);
            } else if (8192 == message.what) {
                if (this.a.U == 6 && !this.a.ar) {
                    this.a.a(6, true);
                }
            } else if (12288 == message.what) {
                if (this.a.U == 10) {
                    this.a.M.go();
                    this.a.R.setEnabled(true);
                }
            } else if (DJIVisualBeginnerView.an == message.what) {
                if (message.arg1 == 0) {
                    this.a.getRcControlMode();
                } else {
                    this.a.getStickAileronPosition();
                }
            } else if (16384 == message.what) {
                if (this.a.U == 10) {
                    this.a.g();
                }
            } else if (DJIVisualBeginnerView.ap == message.what) {
                this.a.c();
            }
            return true;
        }
    };
    private boolean ar = false;

    public DJIVisualBeginnerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.W = c.getInstance();
            this.aa = a.getInstance();
            this.ab = new k(this, this.aq);
        }
    }

    public View getHandleEventView() {
        return this.N;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.G = (DJIRelativeLayout) findViewById(R.id.d8q);
            this.H = (DJIBeginnerTrackDrawView) findViewById(R.id.d8r);
            this.I = (DJIBeginnerPointGuideView) findViewById(R.id.d8s);
            this.J = (DJIImageView) findViewById(R.id.d8t);
            this.K = (DJIImageView) findViewById(R.id.d8u);
            this.L = (DJIImageView) findViewById(R.id.d8v);
            this.M = (DJIBeginnerStickView) findViewById(R.id.d94);
            this.N = (DJILinearLayout) findViewById(R.id.d8w);
            this.O = (DJIImageView) findViewById(R.id.d8x);
            this.P = (DJITextView) findViewById(R.id.d8z);
            this.Q = (DJITextView) findViewById(R.id.d90);
            this.R = (DJILinearLayout) findViewById(R.id.d91);
            this.S = (DJITextView) findViewById(R.id.d93);
            this.T = (DJIImageView) findViewById(R.id.d92);
            this.O.setOnClickListener(this);
            this.R.setOnClickListener(this);
            this.L.setOnClickListener(this);
            b(0);
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            a.getInstance().a(true);
            b(0, true);
            setVisibility(0);
        }
    }

    public void go() {
        this.M.go();
        this.I.go();
        this.O.show();
        this.H.go();
        this.L.go();
        this.G.setBackgroundColor(0);
        this.ab.removeMessages(8192);
        this.ab.removeMessages(12288);
        this.ar = false;
        a.getInstance().a(false);
        a.getInstance().a(Integer.MIN_VALUE);
        super.go();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.d91) {
            if (this.U == 1) {
                d();
            } else if (this.U == 12 || this.U == 7) {
                this.aa.j();
                go();
            } else {
                h();
            }
        } else if (id == R.id.d8x) {
            a();
        } else if (id == R.id.d8v || id == R.id.d8q) {
            this.O.show();
            this.H.go();
            this.L.go();
            this.G.setBackgroundColor(0);
        }
    }

    private void a() {
        Context context = getContext();
        if (this.ad == null) {
            this.ad = b.a(context, (int) R.string.app_tip, (int) R.string.visual_beginner_exit_tip, (int) R.string.visual_beginner_temporary, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisualBeginnerView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a(true);
                }
            }, (int) R.string.visual_beginner_noshow, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisualBeginnerView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a(false);
                }
            });
        }
        if (!this.ad.isShowing()) {
            this.ad.show();
        }
    }

    private void b() {
        if (this.ad != null && this.ad.isShowing()) {
            this.ad.dismiss();
        }
    }

    private void a(boolean z) {
        b();
        if (!z) {
            this.aa.j();
        }
        go();
    }

    private void getRcControlMode() {
        DataRcGetControlMode.getInstance().start(new d(this) {
            final /* synthetic */ DJIVisualBeginnerView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.ab.sendMessageDelayed(this.a.ab.obtainMessage(DJIVisualBeginnerView.an, 1, 0), DJIVisualBeginnerView.ao);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.ab.sendMessageDelayed(this.a.ab.obtainMessage(DJIVisualBeginnerView.an, 0, 0), DJIVisualBeginnerView.ao);
            }
        });
    }

    private void c() {
        this.W.a().a(1.0f, new d(this) {
            final /* synthetic */ DJIVisualBeginnerView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.ab.sendEmptyMessageDelayed(DJIVisualBeginnerView.ap, DJIVisualBeginnerView.ao);
            }
        });
    }

    private void d() {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(new String[]{"g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0"});
        dataFlycSetParams.a(new Integer[]{Integer.valueOf(1), Integer.valueOf(1)});
        dataFlycSetParams.start(new d(this) {
            final /* synthetic */ DJIVisualBeginnerView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.e();
                    }
                });
            }
        });
    }

    private void e() {
        b a = b.a(getContext(), (int) R.string.visual_beginner_ass_title, R.string.visual_beginner_ass_title, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIVisualBeginnerView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, (int) R.string.app_retry, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIVisualBeginnerView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.d();
            }
        });
        a.a(false);
        a.show();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.U == 2) {
            float height = ((float) dataOsdGetPushCommon.getHeight()) * 0.1f;
            int groundOrSky = dataOsdGetPushCommon.groundOrSky();
            a(groundOrSky, height);
            this.R.setEnabled(b(groundOrSky, height));
        }
    }

    private void a(int i, float f) {
        boolean b = b(i, f);
        int color = getResources().getColor(R.color.of);
        if (b) {
            color = getResources().getColor(R.color.oe);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.rk);
        String format = String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(f)});
        CharSequence string = getResources().getString(v[2], new Object[]{format});
        int indexOf = string.indexOf("3");
        int indexOf2 = string.indexOf(format);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), indexOf, indexOf + 1, 17);
        spannableStringBuilder.setSpan(new dji.pilot.publics.d.a.b(null, 0, dimensionPixelSize, color), indexOf2, format.length() + indexOf2, 17);
        this.Q.setText(spannableStringBuilder);
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        if (!this.W.l()) {
            b();
            go();
        }
    }

    private boolean b(int i, float f) {
        float[] fArr = A;
        if (i != 2 || fArr[0] > f) {
            return false;
        }
        return true;
    }

    public void onEventMainThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        if (this.U == 1 && dji.pilot.visual.util.c.e()) {
            h();
        }
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        if (this.U == 10 && !this.R.isEnabled() && !this.ab.hasMessages(12288)) {
            int aileron = this.ae == 0 ? dataRcGetPushParams.getAileron() : this.ae == 1 ? dataRcGetPushParams.getElevator() : this.ae == 2 ? dataRcGetPushParams.getThrottle() : this.ae == 3 ? dataRcGetPushParams.getRudder() : 1024;
            if (aileron != 1024) {
                this.ab.sendEmptyMessageDelayed(12288, 2000);
            }
        }
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (dji_pilot_visual_a_g_d == g$d.CTRLMODE_CHANGED) {
            if (this.W.c()) {
                if (this.U == 3) {
                    b(4);
                } else if (this.U == 13) {
                    b(this.V);
                }
            } else if (this.U == 6) {
                if (this.ar) {
                    this.ar = false;
                    b(7);
                } else {
                    a(6);
                    b(3);
                }
            } else if (this.U == 4 || this.U == 5) {
                if (this.U == 5) {
                    a(5);
                }
                b(13);
            }
            if (this.W.d()) {
                if (this.U == 8) {
                    b(9);
                } else if (this.U == 14) {
                    b(this.V);
                }
            } else if (this.U == 9 || this.U == 10) {
                f();
                b(14);
            }
        }
    }

    private void getStickAileronPosition() {
        ControlMode controlType = DataRcGetControlMode.getInstance().getControlType();
        if (controlType == ControlMode.b || controlType == ControlMode.a) {
            this.ae = 0;
        } else if (controlType == ControlMode.c) {
            this.ae = 3;
        } else if (controlType == ControlMode.d) {
            List channels = DataRcGetControlMode.getInstance().getChannels();
            int size = channels.size();
            for (int i = 0; i < size; i++) {
                if (((ChannelCustomModel) channels.get(i)).b == ChannelType.b.a()) {
                    this.ae = i;
                    return;
                }
            }
        }
    }

    private void f() {
        this.M.go();
    }

    private void g() {
        int i = 0;
        int i2 = 1;
        if (this.ae != 0) {
            if (this.ae == 1) {
                i = 1;
            } else if (this.ae == 2) {
                i = 1;
                i2 = 0;
            } else if (this.ae == 3) {
                i2 = 0;
            }
        }
        this.M.show();
        this.M.animByDirection(i2, i);
    }

    private void a(int i, boolean z) {
        int i2 = z[i];
        this.ac.setEmpty();
        if (i2 != 0) {
            View findViewById = ((Activity) getContext()).findViewById(i2);
            if (findViewById == null) {
                return;
            }
            if (!findViewById.isShown() || z) {
                this.ab.sendEmptyMessageDelayed(i + 4096, ag);
                return;
            }
            findViewById.getGlobalVisibleRect(this.ac);
            int intrinsicHeight;
            MarginLayoutParams marginLayoutParams;
            if (i == 3 || i == 6 || i == 12) {
                i2 = this.J.getBackground().getIntrinsicWidth();
                intrinsicHeight = this.J.getBackground().getIntrinsicHeight();
                marginLayoutParams = (MarginLayoutParams) this.J.getLayoutParams();
                marginLayoutParams.leftMargin = (int) ((((float) i2) * -0.15829998f) + ((float) this.ac.centerX()));
                marginLayoutParams.topMargin = (int) (((float) this.ac.centerY()) + (((float) intrinsicHeight) * E));
                this.J.setLayoutParams(marginLayoutParams);
                this.J.show();
                this.J.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.am));
            } else if (i == 5) {
                i2 = this.K.getBackground().getIntrinsicWidth();
                intrinsicHeight = this.K.getBackground().getIntrinsicHeight();
                marginLayoutParams = (MarginLayoutParams) this.K.getLayoutParams();
                marginLayoutParams.leftMargin = (int) ((((float) i2) * -0.8417f) + ((float) this.ac.centerX()));
                marginLayoutParams.topMargin = (int) ((((float) this.ac.centerY()) + (((float) intrinsicHeight) * E)) + (((float) this.ac.height()) * 0.35f));
                this.K.setLayoutParams(marginLayoutParams);
                this.K.show();
                this.K.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.an));
            }
        }
    }

    private void a(int i) {
        this.ab.removeMessages(i + 4096);
        if (i == 3 || i == 6 || i == 12) {
            this.J.clearAnimation();
            this.J.go();
        } else if (i == 5) {
            this.K.clearAnimation();
            this.K.go();
        }
    }

    public void onEventMainThread(b.a aVar) {
        if (aVar == b.a.POINT_TAP) {
            if (this.U == 3) {
                this.I.go();
                a(3, true);
            }
        } else if (aVar == b.a.POINT_TAP_HIDEAUTO || aVar == b.a.POINT_PRE_CLICK) {
            if (this.U == 3) {
                a(3);
            }
        } else if (aVar == b.a.VISUAL_STOP) {
            if (this.U == 6) {
                a(6);
                this.ar = true;
            } else if (this.U == 11) {
                this.ar = true;
            } else if (this.U == 12) {
                this.ar = true;
                a(12);
            }
        } else if (aVar == b.a.POINT_SPEED) {
            if (this.U == 5) {
                a(5);
            }
        } else if (aVar == b.a.TRACK_PERSON) {
            if (this.U == 11) {
                this.I.go();
            }
        } else if (aVar == b.a.TRACK_SELECT && this.U == 8) {
            this.O.show();
            this.H.go();
            this.L.go();
            this.G.setBackgroundColor(0);
        }
    }

    private void h() {
        if (this.U == 0) {
            if (dji.pilot.visual.util.c.e()) {
                this.U = 1;
                h();
                return;
            }
            b(1);
        } else if (this.U == 1) {
            DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
            if (b(instance.groundOrSky(), ((float) instance.getHeight()) * 0.1f)) {
                this.U = 2;
                h();
                return;
            }
            b(2);
        } else if (this.U == 2) {
            if (c.getInstance().f() == g$e.TRACK_MODE) {
                b(8);
                this.O.hide();
                this.G.setBackgroundColor(getResources().getColor(R.color.d_));
                this.H.show();
                this.L.show();
                return;
            }
            b(3);
            this.I.show();
        } else if (this.U == 4) {
            b(5);
        } else if (this.U == 5) {
            a(5);
            b(6);
            this.ab.removeMessages(8192);
            this.ab.sendEmptyMessageDelayed(8192, 2000);
        } else if (this.U == 6) {
        } else {
            if (this.U == 9) {
                b(10);
                this.ab.sendEmptyMessageDelayed(16384, am);
            } else if (this.U == 10) {
                b(11);
                this.I.show();
            } else if (this.U == 11) {
                b(12);
                this.I.go();
                if (!this.ar) {
                    a(12, true);
                }
            } else {
                b(this.U + 1);
            }
        }
    }

    private void b(int i) {
        b(i, false);
    }

    private void b(int i, boolean z) {
        if (i != this.U || z) {
            this.aa.a(i);
            if (i == 13 || i == 14) {
                this.V = this.U;
            } else if (i == 5) {
                dji.thirdparty.a.c.a().e(DJIVisualPointSpeedView.a.BLINK);
            } else if (i == 0) {
                if (c.getInstance().f() == g$e.TRACK_MODE) {
                    getRcControlMode();
                } else {
                    c();
                }
            } else if (this.U == 12 || this.U == 7) {
                this.aa.j();
                this.O.hide();
            }
            this.U = i;
            this.P.setText(u[i]);
            if (i == 2) {
                a(DataOsdGetPushCommon.getInstance().groundOrSky(), ((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f);
            } else {
                this.Q.setText(v[i]);
            }
            if (w[i] == 0) {
                this.R.hide();
            } else {
                this.S.setText(w[i]);
                this.R.setEnabled(x[i]);
                this.R.show();
            }
            if (this.U == 12 || this.U == 7) {
                this.T.show();
                this.R.setBackgroundColor(getResources().getColor(R.color.o_));
                return;
            }
            this.T.go();
            this.R.setBackgroundColor(getResources().getColor(R.color.oa));
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            a.getInstance().a(true);
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            if (dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().d(this);
            }
            a.getInstance().a(false);
        }
        super.onDetachedFromWindow();
    }

    public boolean isFinished() {
        return getVisibility() != 0;
    }
}
