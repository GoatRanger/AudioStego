package dji.pilot2.multimoment.a;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import dji.g.b.g;
import dji.log.DJILogHelper;
import dji.midware.media.e;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.utils.p;

public class a extends Fragment implements Callback {
    public static String a = "files_path";
    public static String b = "files_start_time";
    public static String c = "files_end_time";
    public static String d = "file_music";
    public static int e = 1;
    public static int f = 2;
    public static int g = 3;
    public static int h = 4;
    public static int i = 6;
    public static int j = 7;
    boolean A;
    boolean B;
    int C;
    int D;
    int E = 0;
    Boolean F = Boolean.valueOf(false);
    Boolean G = Boolean.valueOf(false);
    double H = 0.0d;
    private TextView I = null;
    private boolean J = false;
    dji.pilot2.videolib.a k;
    dji.g.b.a l;
    SurfaceView m;
    ImageView n;
    String[] o;
    double[] p;
    double[] q;
    String r;
    double[] s;
    double[] t;
    double[] u;
    double[] v;
    int[] w;
    double[] x;
    a y;
    SurfaceHolder z;

    public interface a {
        void a();

        void a(long j);

        void b();

        void c();
    }

    public static a a(String[] strArr, double[] dArr, double[] dArr2) {
        a aVar = new a();
        String[] strArr2 = new String[strArr.length];
        double[] dArr3 = new double[dArr.length];
        dArr3 = new double[dArr2.length];
        strArr2 = (String[]) strArr.clone();
        double[] dArr4 = (double[]) dArr.clone();
        double[] dArr5 = (double[]) dArr2.clone();
        Bundle bundle = new Bundle();
        bundle.putStringArray(a, strArr2);
        bundle.putDoubleArray(b, dArr4);
        bundle.putDoubleArray(c, dArr5);
        aVar.setArguments(bundle);
        return aVar;
    }

    private void a(String[] strArr, long[] jArr, long[] jArr2) {
        j();
        this.k = new dji.pilot2.videolib.a();
        this.k.a();
        this.l = new dji.g.b.a();
        this.l.a(new g(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(long j) {
                DJILogHelper.getInstance().LOGI("bob", "DJIMultiMomentFineActivity timeMs=" + j + "mFast = " + this.a.v[0]);
                this.a.a(j, (long) this.a.E);
                if (this.a.v != null) {
                    j = (long) (((double) j) * this.a.v[0]);
                }
                if (this.a.y != null && this.a.D != 1) {
                    this.a.y.a(j);
                }
            }

            public void a() {
                if (this.a.y != null) {
                    this.a.y.b();
                }
            }

            public void a(int i) {
                this.a.D = 0;
                double d = (!this.a.J || this.a.v == null || this.a.v[0] == 0.0d) ? 1.0d : this.a.v[0];
                this.a.a((long) (((double) (this.a.E + 500)) / d), (long) this.a.E);
                this.a.n.setVisibility(0);
                if (this.a.y != null) {
                    this.a.y.a();
                }
            }

            public void b(int i) {
                DJILogHelper.getInstance().LOGI("error", "video player throw exception");
                Toast.makeText(this.a.getActivity(), this.a.getString(R.string.v2_videoplayer_exception), 1).show();
            }
        });
        this.l.a(this.k);
        this.l.a(strArr, jArr, jArr2, false, null, null, false);
        this.l.a(this.H);
        this.D = 0;
    }

    private void j() {
        if (this.l != null) {
            this.l.a();
            this.l = null;
        }
        if (this.k != null) {
            this.k.b();
            this.k = null;
        }
    }

    public void a(int i, int[] iArr, double[] dArr) {
        if (i == i || i == j) {
            this.w = new int[iArr.length];
            this.w = (int[]) iArr.clone();
            this.x = new double[dArr.length];
            this.x = (double[]) dArr.clone();
            if (!(this.l == null || e())) {
                this.k.a(iArr, dArr);
            }
            if (this.l != null && d()) {
                this.l.q();
            }
        }
    }

    public void a(int i, double[] dArr) {
        if (i == e) {
            this.s = new double[dArr.length];
            this.s = (double[]) dArr.clone();
            if (!(this.l == null || e() || this.s == null)) {
                this.k.a(this.s);
            }
        }
        if (i == g) {
            this.u = new double[dArr.length];
            this.u = (double[]) dArr.clone();
            if (!(this.l == null || e() || this.u == null)) {
                this.k.c(this.u);
            }
        }
        if (i == f) {
            this.t = new double[dArr.length];
            this.t = (double[]) dArr.clone();
            if (!(this.l == null || e() || this.t == null)) {
                this.k.b(this.t);
            }
        }
        if (i == h) {
            this.v = new double[dArr.length];
            this.v = (double[]) dArr.clone();
            if (!(this.l == null || e() || this.v == null)) {
                for (int i2 = 0; i2 < this.v.length; i2++) {
                    this.l.a(i2, this.v[i2]);
                }
            }
        }
        if (this.l != null && d()) {
            this.l.q();
        }
    }

    public void a(int i) {
        if (this.l != null) {
            this.k.a(i);
        }
    }

    protected void a() {
        if (this.l != null) {
            if (this.s != null) {
                this.k.a(this.s);
            }
            if (this.u != null) {
                this.k.c(this.u);
            }
            if (this.t != null) {
                this.k.b(this.t);
            }
            if (!(this.w == null || this.x == null)) {
                this.k.a(this.w, this.x);
            }
            if (this.v != null) {
                for (int i = 0; i < this.v.length; i++) {
                    double d = this.v[i];
                    if (d != 1.0d) {
                        this.l.a(i, d);
                    }
                }
            }
        }
    }

    public void a(String[] strArr, double[] dArr, double[] dArr2, String str) {
        String[] strArr2 = new String[strArr.length];
        double[] dArr3 = new double[dArr.length];
        dArr3 = new double[dArr2.length];
        strArr2 = (String[]) strArr.clone();
        double[] dArr4 = (double[]) dArr2.clone();
        this.p = (double[]) dArr.clone();
        this.q = dArr4;
        this.o = strArr2;
        this.r = str;
        this.s = null;
        this.u = null;
        this.t = null;
        this.v = null;
    }

    public void b(String[] strArr, double[] dArr, double[] dArr2, String str) {
        int i = 0;
        if (this.l != null) {
            g();
            j();
        }
        a(strArr, dArr, dArr2, str);
        long[] jArr = new long[this.p.length];
        long[] jArr2 = new long[this.p.length];
        this.E = 0;
        while (i < this.p.length) {
            jArr[i] = (long) this.p[i];
            jArr2[i] = (long) this.q[i];
            this.E = (int) (((double) this.E) + (this.q[i] - this.p[i]));
            i++;
        }
        a(0, (long) this.E);
        a(this.o, jArr, jArr2);
        this.l.a(this.H);
        this.l.a(this.m.getHolder().getSurface());
        this.l.b();
        b();
    }

    private void k() {
        a(0, (long) this.E);
        b(this.o, this.p, this.q, null);
    }

    public void b() {
        if (this.D == 0) {
            if (this.l != null) {
                this.l.c();
                a();
            }
            this.D = 2;
            this.n.setVisibility(4);
        }
    }

    public boolean c() {
        return this.D == 2;
    }

    public boolean d() {
        return this.D == 1;
    }

    public boolean e() {
        return this.D == 0;
    }

    public void a(long j) {
        if (this.l != null && this.D > 0) {
            this.l.a(j);
        }
    }

    public void f() {
        if (this.l == null) {
            return;
        }
        if (this.D == 2) {
            this.n.setVisibility(0);
            this.D = 1;
            this.l.e();
            return;
        }
        this.n.setVisibility(4);
        this.D = 2;
        this.l.c();
    }

    public void b(int i) {
        this.D = 2;
        a((long) i);
        this.n.setVisibility(4);
        this.l.c();
    }

    public void a(boolean z) {
        this.G = Boolean.valueOf(z);
        if (this.l != null && this.n.getVisibility() == 4) {
            this.n.setVisibility(0);
            this.D = 1;
            this.l.e();
        }
    }

    public void g() {
        if (this.l != null) {
            this.l.d();
        }
        this.D = 0;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.o = arguments.getStringArray(a);
        this.p = arguments.getDoubleArray(b);
        this.q = arguments.getDoubleArray(c);
        this.r = arguments.getString(d);
        this.A = false;
        this.C = 0;
        this.B = false;
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_player, null);
        this.m = (SurfaceView) inflate.findViewById(R.id.coe);
        this.m.getHolder().addCallback(this);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.l == null) {
                    return;
                }
                if (this.a.D > 0) {
                    this.a.f();
                    return;
                }
                this.a.g();
                this.a.b();
            }
        });
        this.n = (ImageView) inflate.findViewById(R.id.cof);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.l == null) {
                    return;
                }
                if (this.a.D > 0) {
                    this.a.f();
                    return;
                }
                this.a.g();
                this.a.b();
            }
        });
        this.I = (TextView) inflate.findViewById(R.id.cog);
        this.I.setVisibility(0);
        return inflate;
    }

    private void a(long j, long j2) {
        e.d("ShowTime", "pos=" + j + " duration=" + j2);
        double d = (!this.J || this.v == null || this.v[0] == 0.0d) ? 1.0d : this.v[0];
        if (this.I != null) {
            this.I.setText(p.d((int) (j / 1000)) + d.t + p.d((int) (((double) (500 + j2)) / (d * 1000.0d))));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        g();
    }

    public void onStop() {
        super.onStop();
        if (!getActivity().isChangingConfigurations()) {
            j();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.l == null && !this.G.booleanValue()) {
            long[] jArr = new long[this.p.length];
            long[] jArr2 = new long[this.p.length];
            this.E = 0;
            for (int i = 0; i < this.p.length; i++) {
                jArr[i] = (long) this.p[i];
                jArr2[i] = (long) this.q[i];
                this.E = (int) (((double) this.E) + (this.q[i] - this.p[i]));
            }
            a(0, (long) this.E);
            a(this.o, jArr, jArr2);
            if (this.A && this.z != null) {
                this.l.a(this.H);
                this.l.a(this.z.getSurface());
                this.l.b();
                b();
                f();
                if (this.D == 0 || this.D == 1) {
                    this.n.setVisibility(0);
                } else {
                    this.n.setVisibility(4);
                }
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null && surfaceHolder.getSurface() != null && this.l != null) {
            this.l.a(this.H);
            this.l.a(surfaceHolder.getSurface());
            this.l.b();
            b();
            this.z = surfaceHolder;
            this.A = true;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.z = surfaceHolder;
        if (!this.B) {
            this.z.setFixedSize(i2, i3);
            this.B = true;
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        j();
        this.A = false;
    }

    public void a(a aVar) {
        this.y = aVar;
    }

    public void h() {
        this.J = true;
    }

    public void a(double d) {
        this.H = d;
        this.l.a(this.H);
    }

    public boolean i() {
        if (this.l != null) {
            return this.l.n();
        }
        return false;
    }
}
