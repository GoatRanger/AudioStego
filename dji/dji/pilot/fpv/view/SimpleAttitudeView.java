package dji.pilot.fpv.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dji.logic.c.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.newfpv.f.f;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;
import dji.pilot.publics.d.a.c;
import dji.publics.DJIUI.DJILinearLayout;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import java.util.Locale;

public class SimpleAttitudeView extends DJILinearLayout implements h<f>, d {
    private static final String v = " FT";
    private static final String w = " M";
    private static final String x = " MPH";
    private static final String y = " M/S";
    private static final String z = " KM/H";
    private String A = w;
    private String B = y;
    private Handler C = new Handler(new Callback(this) {
        final /* synthetic */ SimpleAttitudeView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 256:
                    this.a.g();
                    break;
                case 257:
                    this.a.b();
                    break;
            }
            return false;
        }
    });
    private g D = null;
    private int E = 0;
    private dji.pilot.newfpv.d F = null;
    private TextView a;
    private TextView b;
    private ImageView c;
    private TextView d;
    private DJIGenSettingDataManager e = null;
    private int f = 3;
    private int g = 3;
    private int h;
    private int i;
    private float j = Float.NaN;
    private float k = Float.NaN;
    private float l = Float.NaN;
    private float m = Float.NaN;
    private double n = Double.NaN;
    private double o = Double.NaN;
    private double p = Double.NaN;
    private double q = Double.NaN;
    private final int r = 256;
    private final int s = 257;
    private final long t = 200;
    private c u;

    public SimpleAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(a.ViewId_SimAtti, f.SIM_ATTI_SHOW, f.SIM_ATTI_HIDE);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.e = DJIGenSettingDataManager.getInstance();
            this.a = (TextView) findViewById(R.id.a8p);
            this.b = (TextView) findViewById(R.id.a8q);
            this.c = (ImageView) findViewById(R.id.a8r);
            this.d = (TextView) findViewById(R.id.a8s);
            this.h = getResources().getColor(R.color.gj);
            this.i = getResources().getColor(R.color.om);
            this.u = new c(getResources().getDimensionPixelSize(R.dimen.q4), -1711276033);
            a();
        }
    }

    private void a() {
        this.a.setText(R.string.fpv_default_str);
        this.b.setText(R.string.fpv_default_str);
        this.d.setText(R.string.fpv_default_str);
        this.c.setVisibility(4);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = Float.NaN;
        this.n = Double.NaN;
        this.o = Double.NaN;
        this.p = Double.NaN;
        this.q = Double.NaN;
    }

    private void b() {
        this.C.removeMessages(257);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
            if (isShown()) {
                d();
            }
            e();
            c();
        }
    }

    private void c() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventMainThread(o.b);
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!isInEditMode()) {
            if (i == 0) {
                d();
            } else {
                dji.sdksharedlib.a.a.a(this);
            }
        }
    }

    private void d() {
        dji.sdksharedlib.a.a.g(this, new String[]{e.W, e.T, e.U, e.V, e.al, e.am, e.R, e.S, e.ah});
        this.C.sendEmptyMessageDelayed(256, 200);
    }

    private void e() {
        int v = this.e.v();
        if (v != this.f) {
            this.g = this.f;
            this.f = v;
            if (this.f == 0) {
                this.A = v;
                this.B = x;
            } else if (this.f == 1) {
                this.A = w;
                this.B = y;
            } else {
                this.A = w;
                this.B = z;
            }
            f();
        }
    }

    private void f() {
        int length;
        int length2;
        if (this.g == 1) {
            length = w.length();
            length2 = y.length();
        } else if (this.g == 0) {
            length = v.length();
            length2 = x.length();
        } else {
            length = w.length();
            length2 = z.length();
        }
        String string = getResources().getString(R.string.fpv_default_str);
        CharSequence text = this.b.getText();
        if (!text.equals(string)) {
            this.b.setText(a(text.subSequence(0, text.length() - length), this.A));
        }
        text = this.a.getText();
        if (!text.equals(string)) {
            this.a.setText(a(text.subSequence(0, text.length() - length), this.A));
        }
        CharSequence text2 = this.d.getText();
        if (!text2.equals(string)) {
            this.d.setText(a(text2.subSequence(0, text2.length() - length2), this.B));
        }
    }

    private SpannableString a(CharSequence charSequence, String str) {
        SpannableString spannableString = new SpannableString(charSequence + str);
        spannableString.setSpan(this.u, charSequence.length(), charSequence.length() + str.length(), 17);
        return spannableString;
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        dji.thirdparty.a.c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED) {
            e();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            this.C.removeMessages(257);
            if (b.getInstance().a(null)) {
                setVisibility(0);
            } else {
                setVisibility(8);
            }
        } else if (oVar == o.a) {
            this.C.removeMessages(256);
            this.C.sendEmptyMessageDelayed(257, 200);
        }
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (!this.C.hasMessages(256)) {
            g();
            this.C.sendEmptyMessageDelayed(256, 200);
        }
    }

    private void g() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            h();
            i();
            j();
        }
    }

    private void h() {
        float c;
        if (dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.ah), false)) {
            c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.ai));
        } else {
            c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.W));
        }
        if (this.j != c) {
            this.j = c;
            if (((double) c) <= 1.2d && this.a.getCurrentTextColor() != this.h) {
                this.a.setTextColor(this.h);
            } else if (((double) c) > 1.2d && this.a.getCurrentTextColor() != this.i) {
                this.a.setTextColor(this.i);
            }
            this.a.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.e.b(c))}), this.A));
        }
    }

    private void i() {
        float c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.T));
        float c2 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.U));
        float c3 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.V));
        if (this.k != c || this.l != c2 || c3 != this.m) {
            this.k = c;
            this.l = c2;
            this.m = c3;
            c = (float) Math.sqrt((double) (((c * c) + (c2 * c2)) + (c3 * c3)));
            this.d.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.e.c(c))}), this.B));
            if (c3 == 0.0f) {
                this.c.setVisibility(4);
                return;
            }
            this.c.setVisibility(0);
            Object tag = this.c.getTag();
            if (c3 < 0.0f && (tag == null || !tag.equals(Integer.valueOf(R.drawable.fpv_up)))) {
                this.c.setImageResource(R.drawable.fpv_up);
                this.c.setTag(Integer.valueOf(R.drawable.fpv_up));
            } else if (c3 <= 0.0f) {
            } else {
                if (tag == null || !tag.equals(Integer.valueOf(R.drawable.fpv_down))) {
                    this.c.setImageResource(R.drawable.fpv_down);
                    this.c.setTag(Integer.valueOf(R.drawable.fpv_down));
                }
            }
        }
    }

    private void j() {
        if (!dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.e(e.aC))) {
            return;
        }
        if (dji.pilot.fpv.d.b.b(dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.Q)))) {
            double d = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.al));
            double d2 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.am));
            double d3 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.R));
            double d4 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.S));
            if (this.n != d || this.o != d2 || this.p != d3 || this.q != d4) {
                this.n = d;
                this.o = d2;
                this.p = d3;
                this.q = d4;
                if (dji.pilot.fpv.d.b.a(d) && dji.pilot.fpv.d.b.b(d2) && dji.pilot.fpv.d.b.a(d3) && dji.pilot.fpv.d.b.b(d4)) {
                    float f = dji.pilot.fpv.d.b.c(d, d2, d3, d4)[1];
                    this.b.setText(a(String.format(Locale.US, "%.0f", new Object[]{Float.valueOf(this.e.b(f))}), this.A));
                    return;
                }
                this.b.setText(R.string.fpv_default_str);
                return;
            }
            return;
        }
        this.b.setText(R.string.fpv_default_str);
    }

    public void bind(g gVar, int i) {
        this.D = gVar;
        this.E = i;
    }

    public void bindInfo(a aVar, f fVar, f fVar2) {
        this.F = new dji.pilot.newfpv.d(this, aVar, fVar, fVar2);
    }

    public a getViewId() {
        return this.F.b;
    }

    public dji.pilot.newfpv.d getViewInfo() {
        return this.F;
    }

    public boolean needShow() {
        if (b.getInstance().a(null)) {
            return true;
        }
        return false;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(f fVar) {
        dji.pilot.newfpv.view.a.a(f.SIM_ATTI_SHOW == fVar, this.D, this);
    }
}
