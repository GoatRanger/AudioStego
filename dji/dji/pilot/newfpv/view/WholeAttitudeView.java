package dji.pilot.newfpv.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dji.gs.e.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.control.k;
import dji.pilot.newfpv.f;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;
import dji.pilot.publics.d.a.c;
import dji.publics.DJIUI.DJILinearLayout;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import java.util.Locale;

public class WholeAttitudeView extends DJILinearLayout implements h<f.h>, d {
    private static final String F = " FT";
    private static final String G = " M";
    private static final String H = " MPH";
    private static final String I = " M/S";
    private static final String J = " KM/H";
    private final int A = 257;
    private final long B = 200;
    private g C = null;
    private int D = 0;
    private dji.pilot.newfpv.d E = null;
    private String K = G;
    private String L = I;
    private Handler M = new Handler(new Callback(this) {
        final /* synthetic */ WholeAttitudeView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 256:
                    this.a.h();
                    break;
                case 257:
                    this.a.g();
                    break;
            }
            return false;
        }
    });
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private double j = Double.NaN;
    private double k = Double.NaN;
    private double l = Double.NaN;
    private double m = Double.NaN;
    private float n = Float.NaN;
    private float o = Float.NaN;
    private float p = Float.NaN;
    private int q;
    private int r;
    private boolean s = false;
    private double t = 0.0d;
    private double u = 0.0d;
    private c v;
    private DJIGenSettingDataManager w = null;
    private int x = 3;
    private int y = 3;
    private final int z = 256;

    public WholeAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(a.ViewId_WholeAtti, f.h.WHOLE_ATTI_SHOW, f.h.WHOLE_ATTI_HIDE);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.w = DJIGenSettingDataManager.getInstance();
            this.a = (TextView) findViewById(R.id.a8p);
            this.b = (TextView) findViewById(R.id.a8q);
            this.c = (TextView) findViewById(R.id.adg);
            this.d = (TextView) findViewById(R.id.adi);
            this.e = (TextView) findViewById(R.id.adm);
            this.f = (TextView) findViewById(R.id.adj);
            this.i = (ImageView) findViewById(R.id.a8r);
            this.g = (ImageView) findViewById(R.id.adk);
            this.h = (ImageView) findViewById(R.id.adl);
            this.q = getResources().getColor(R.color.gj);
            this.r = getResources().getColor(R.color.om);
            this.v = new c(getResources().getDimensionPixelSize(R.dimen.q4), -1711276033);
            d();
            a();
        }
    }

    private void a() {
        this.a.setText(R.string.fpv_default_str);
        this.b.setText(R.string.fpv_default_str);
        this.c.setText(R.string.fpv_default_str);
        this.d.setText(R.string.fpv_default_str);
        this.e.setText(R.string.fpv_default_str);
        this.i.setVisibility(4);
        this.n = Float.NaN;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.j = Double.NaN;
        this.k = Double.NaN;
        this.l = Double.NaN;
        this.m = Double.NaN;
        a(true, false, false, false);
        b();
    }

    private SpannableString a(CharSequence charSequence, String str) {
        SpannableString spannableString = new SpannableString(charSequence + str);
        spannableString.setSpan(this.v, charSequence.length(), charSequence.length() + str.length(), 17);
        return spannableString;
    }

    private void a(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z) {
            this.e.setVisibility(0);
            if (z2) {
                this.f.setVisibility(4);
                this.g.setVisibility(4);
                this.h.setVisibility(0);
                return;
            } else if (z3) {
                this.f.setVisibility(0);
                this.g.setVisibility(4);
                this.h.setVisibility(4);
                return;
            } else if (z4) {
                this.f.setVisibility(4);
                this.g.setVisibility(0);
                this.h.setVisibility(4);
                return;
            } else {
                this.f.setVisibility(0);
                this.g.setVisibility(4);
                this.h.setVisibility(4);
                return;
            }
        }
        this.e.setVisibility(4);
        this.f.setVisibility(4);
        this.g.setVisibility(4);
        this.h.setVisibility(4);
    }

    private void b() {
        this.s = false;
        this.t = 0.0d;
        this.u = 0.0d;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
            if (isShown()) {
                c();
            }
            f();
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!isInEditMode()) {
            if (i == 0) {
                c();
            } else {
                dji.sdksharedlib.a.a.a(this);
            }
        }
    }

    private void c() {
        dji.sdksharedlib.a.a.g(this, new String[]{e.W, e.T, e.U, e.V, e.al, e.am, e.R, e.S, e.ah, e.ai, e.E});
        this.M.sendEmptyMessageDelayed(256, 200);
    }

    private void d() {
        int v = this.w.v();
        if (v != this.x) {
            this.y = this.x;
            this.x = v;
            if (this.x == 0) {
                this.K = F;
                this.L = H;
            } else if (this.x == 1) {
                this.K = G;
                this.L = I;
            } else {
                this.K = G;
                this.L = J;
            }
            e();
        }
    }

    private void e() {
        int length;
        int length2;
        if (this.y == 1) {
            length = G.length();
            length2 = I.length();
        } else if (this.y == 0) {
            length = F.length();
            length2 = H.length();
        } else {
            length = G.length();
            length2 = J.length();
        }
        String string = getResources().getString(R.string.fpv_default_str);
        CharSequence text = this.b.getText();
        if (!text.equals(string)) {
            this.b.setText(a(text.subSequence(0, text.length() - length), this.K));
        }
        text = this.a.getText();
        if (!text.equals(string)) {
            this.a.setText(a(text.subSequence(0, text.length() - length), this.K));
        }
        CharSequence text2 = this.d.getText();
        if (!text2.equals(string)) {
            int length3;
            if (this.y == 2) {
                length3 = I.length();
            } else {
                length3 = length2;
            }
            if (this.L.equals(J)) {
                text = a(text2.subSequence(0, text2.length() - length3), I);
            } else {
                text = a(text2.subSequence(0, text2.length() - length3), this.L);
            }
            this.d.setText(text);
        }
        text = this.c.getText();
        if (!text.equals(string)) {
            this.c.setText(a(text.subSequence(0, text.length() - length2), this.L));
        }
        CharSequence text3 = this.e.getText();
        if (!text3.equals(string)) {
            this.e.setText(a(text3.subSequence(0, text3.length() - length), this.K));
        }
    }

    private void f() {
        b k = k.k();
        if (k != null && dji.pilot.fpv.d.b.a(k.b) && dji.pilot.fpv.d.b.b(k.c)) {
            this.s = true;
            this.t = k.b;
            this.u = k.c;
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        dji.thirdparty.a.c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void g() {
        this.M.removeMessages(257);
        a();
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (!this.M.hasMessages(256)) {
            h();
            this.M.sendEmptyMessageDelayed(256, 200);
        }
    }

    private void h() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            k();
            j();
            l();
            i();
        }
    }

    private void i() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.E), true);
        boolean a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.ah), false);
        if (!a) {
            a(true, true, false, false);
            this.e.setText(R.string.fpv_default_str);
        } else if (a2) {
            a(true, false, true, false);
            r0 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.ai));
            if (((double) r0) <= 1.2d && this.e.getCurrentTextColor() != this.q) {
                this.e.setTextColor(this.q);
            } else if (((double) r0) > 1.2d && this.e.getCurrentTextColor() != this.r) {
                this.e.setTextColor(this.r);
            }
            this.e.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.b(r0))}), this.K));
        } else {
            a(true, false, false, true);
            if (this.e.getCurrentTextColor() != this.r) {
                this.e.setTextColor(this.r);
            }
            this.e.setText(R.string.fpv_default_str);
            if (this.s) {
                r0 = dji.pilot.fpv.d.b.a(this.t, this.u, dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.R)), dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.S)));
                this.e.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.b(r0))}), this.K));
            }
        }
    }

    private void j() {
        float c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.T));
        float c2 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.U));
        float c3 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.V));
        if (!(this.n == c && this.o == c2)) {
            this.n = c;
            this.o = c2;
            c = (float) Math.sqrt((double) ((c * c) + (c2 * c2)));
            this.c.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.c(c))}), this.L));
        }
        if (this.p != c3) {
            CharSequence a;
            this.p = c3;
            if (this.L.equals(J)) {
                a = a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.b(Math.abs(c3)))}), I);
            } else {
                a = a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.c(Math.abs(c3)))}), this.L);
            }
            this.d.setText(a);
        }
        if (c3 == 0.0f) {
            this.i.setVisibility(4);
            return;
        }
        this.i.setVisibility(0);
        Object tag = this.i.getTag();
        if (c3 < 0.0f && (tag == null || !tag.equals(Integer.valueOf(R.drawable.fpv_up)))) {
            this.i.setImageResource(R.drawable.fpv_up);
            this.i.setTag(Integer.valueOf(R.drawable.fpv_up));
        } else if (c3 <= 0.0f) {
        } else {
            if (tag == null || !tag.equals(Integer.valueOf(R.drawable.fpv_down))) {
                this.i.setImageResource(R.drawable.fpv_down);
                this.i.setTag(Integer.valueOf(R.drawable.fpv_down));
            }
        }
    }

    private void k() {
        if (((Float) dji.sdksharedlib.a.a.e(e.W)) != null) {
            this.a.setText(a(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(this.w.b(((Float) dji.sdksharedlib.a.a.e(e.W)).floatValue()))}), this.K));
        }
    }

    private void l() {
        if (!dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.aC), false)) {
            return;
        }
        if (dji.pilot.fpv.d.b.b(dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.Q)))) {
            double d = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.al));
            double d2 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.am));
            double d3 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.R));
            double d4 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.S));
            if (this.j != d || this.k != d2 || this.l != d3 || this.m != d4) {
                this.j = d;
                this.k = d2;
                this.l = d3;
                this.m = d4;
                if (dji.pilot.fpv.d.b.a(d) && dji.pilot.fpv.d.b.b(d2) && dji.pilot.fpv.d.b.a(d3) && dji.pilot.fpv.d.b.b(d4)) {
                    float f = dji.pilot.fpv.d.b.c(d, d2, d3, d4)[1];
                    this.b.setText(a(String.format(Locale.US, "%.0f", new Object[]{Float.valueOf(this.w.b(f))}), this.K));
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
        this.C = gVar;
        this.D = i;
    }

    public void bindInfo(a aVar, f.h hVar, f.h hVar2) {
        this.E = new dji.pilot.newfpv.d(this, aVar, hVar, hVar2);
    }

    public a getViewId() {
        return this.E.b;
    }

    public dji.pilot.newfpv.d getViewInfo() {
        return this.E;
    }

    public boolean needShow() {
        if (dji.logic.c.b.getInstance().a(null)) {
            return false;
        }
        return true;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(f.h hVar) {
        a.a(f.h.WHOLE_ATTI_SHOW == hVar, this.C, this);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            if (dji.logic.c.b.getInstance().a(null)) {
                go();
            } else if (this.C.a(this.E, 0)) {
                show();
            }
            this.M.removeMessages(257);
        } else if (oVar == o.a) {
            this.M.removeMessages(256);
            this.M.sendEmptyMessageDelayed(257, 200);
        }
    }

    public void onEventMainThread(b bVar) {
        if (bVar != null && dji.pilot.fpv.d.b.a(bVar.b) && dji.pilot.fpv.d.b.b(bVar.c)) {
            this.s = true;
            this.t = bVar.b;
            this.u = bVar.c;
        }
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED) {
            d();
        }
    }
}
