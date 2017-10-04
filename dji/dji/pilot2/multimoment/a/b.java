package dji.pilot2.multimoment.a;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.google.android.gms.common.api.CommonStatusCodes;
import dji.g.b.g;
import dji.log.DJILogHelper;
import dji.midware.media.e;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.utils.o;
import dji.pilot2.utils.p;

public class b extends Fragment implements Callback {
    public static String a = "files_path";
    public static String b = "files_start_time";
    public static String c = "files_end_time";
    public static String d = "file_music";
    public static int e = 2000;
    public static int f = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    public static int g = 1;
    public static int h = 2;
    public static int i = 3;
    public static int j = 4;
    public static int k = 5;
    public static int l = 6;
    public static int m = 7;
    int[] A;
    double[] B;
    a C;
    SurfaceHolder D;
    boolean E;
    boolean F;
    int G;
    int H;
    int I = 0;
    Boolean J = Boolean.valueOf(false);
    Boolean K = Boolean.valueOf(false);
    Handler L = new Handler(Looper.getMainLooper());
    double M = 0.0d;
    double[] N = null;
    private dji.pilot2.multimoment.videolib.a O;
    private TextView P = null;
    private boolean Q = false;
    dji.g.b.a n;
    public dji.pilot2.videolib.a o;
    SurfaceView p;
    ImageView q;
    String[] r;
    double[] s;
    double[] t;
    String u;
    double[] v;
    double[] w;
    double[] x;
    double[] y;
    double[] z;

    public interface a {
        void a();

        void a(long j);

        void b();
    }

    public dji.g.b.a a() {
        return this.n;
    }

    public static b a(String[] strArr, double[] dArr, double[] dArr2, String str) {
        b bVar = new b();
        String[] strArr2 = new String[strArr.length];
        double[] dArr3 = new double[dArr.length];
        dArr3 = new double[dArr2.length];
        String str2 = new String();
        strArr2 = (String[]) strArr.clone();
        double[] dArr4 = (double[]) dArr.clone();
        double[] dArr5 = (double[]) dArr2.clone();
        Bundle bundle = new Bundle();
        bundle.putStringArray(a, strArr2);
        bundle.putDoubleArray(b, dArr4);
        bundle.putDoubleArray(c, dArr5);
        bundle.putString(d, str);
        bVar.setArguments(bundle);
        return bVar;
    }

    private void a(String[] strArr, long[] jArr, long[] jArr2, double[] dArr, String str, boolean z) {
        this.o = new dji.pilot2.videolib.a();
        this.o.a();
        if (this.Q) {
            this.O = new dji.pilot2.multimoment.videolib.a(getActivity());
            this.n = this.O.a();
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) (jArr2[i] - jArr[i]);
            }
            this.O.a(strArr, iArr);
            this.n.a(this.o);
            this.O.a(this.o);
            this.O.a(0, 0);
        } else {
            this.n = new dji.g.b.a();
            this.n.a(this.o);
            this.n.a(strArr, jArr, jArr2, true, dArr, str, z);
            if (dArr != null) {
                this.y = (double[]) dArr.clone();
            }
        }
        this.n.a(new g(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.a(j, (long) this.a.I);
                if (this.a.C != null && this.a.H != 1) {
                    this.a.C.a(j);
                }
            }

            public void a() {
                if (this.a.C != null) {
                    this.a.C.a();
                }
            }

            public void a(int i) {
                this.a.H = 0;
                this.a.a((long) (this.a.I + 500), (long) this.a.I);
                if (this.a.C != null) {
                    this.a.C.b();
                    this.a.H = 1;
                }
                this.a.d(0);
            }

            public void b(int i) {
                if (i == -1) {
                    DJILogHelper.getInstance().LOGI("error", "video player throw exception");
                    Toast.makeText(this.a.getActivity(), this.a.getString(R.string.v2_videoplayer_exception), 1).show();
                }
            }
        });
        DJILogHelper.getInstance().LOGE("bob", "mFast==" + this.y);
        this.H = 0;
    }

    private void k() {
        if (!this.K.booleanValue()) {
            if (this.n != null) {
                this.n.a();
                if (!this.K.booleanValue()) {
                    this.n = null;
                }
            }
            if (this.o != null) {
                this.o.b();
            }
        }
    }

    public void a(int i, int[] iArr, double[] dArr) {
        if (i == l || i == m) {
            this.A = new int[iArr.length];
            this.A = (int[]) iArr.clone();
            this.B = new double[dArr.length];
            this.B = (double[]) dArr.clone();
            if (this.n != null && !f()) {
                this.o.a(iArr, dArr);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r8, double[] r9) {
        /*
        r7 = this;
        r0 = g;
        if (r8 != r0) goto L_0x0026;
    L_0x0004:
        r0 = r9.length;
        r0 = new double[r0];
        r7.v = r0;
        r0 = r9.clone();
        r0 = (double[]) r0;
        r7.v = r0;
        r0 = r7.n;
        if (r0 == 0) goto L_0x0026;
    L_0x0015:
        r0 = r7.f();
        if (r0 != 0) goto L_0x0026;
    L_0x001b:
        r0 = r7.v;
        if (r0 == 0) goto L_0x0026;
    L_0x001f:
        r0 = r7.o;
        r1 = r7.v;
        r0.a(r1);
    L_0x0026:
        r0 = h;
        if (r8 != r0) goto L_0x004c;
    L_0x002a:
        r0 = r9.length;
        r0 = new double[r0];
        r7.w = r0;
        r0 = r9.clone();
        r0 = (double[]) r0;
        r7.w = r0;
        r0 = r7.n;
        if (r0 == 0) goto L_0x004c;
    L_0x003b:
        r0 = r7.f();
        if (r0 != 0) goto L_0x004c;
    L_0x0041:
        r0 = r7.w;
        if (r0 == 0) goto L_0x004c;
    L_0x0045:
        r0 = r7.o;
        r1 = r7.w;
        r0.b(r1);
    L_0x004c:
        r0 = i;
        if (r8 != r0) goto L_0x0072;
    L_0x0050:
        r0 = r9.length;
        r0 = new double[r0];
        r7.x = r0;
        r0 = r9.clone();
        r0 = (double[]) r0;
        r7.x = r0;
        r0 = r7.n;
        if (r0 == 0) goto L_0x0072;
    L_0x0061:
        r0 = r7.f();
        if (r0 != 0) goto L_0x0072;
    L_0x0067:
        r0 = r7.x;
        if (r0 == 0) goto L_0x0072;
    L_0x006b:
        r0 = r7.o;
        r1 = r7.x;
        r0.c(r1);
    L_0x0072:
        r0 = j;
        if (r8 != r0) goto L_0x00cf;
    L_0x0076:
        r0 = r9.length;
        r0 = new double[r0];
        r7.y = r0;
        r0 = r9.clone();
        r0 = (double[]) r0;
        r7.y = r0;
        r0 = r7.n;
        if (r0 == 0) goto L_0x00cf;
    L_0x0087:
        r0 = r7.f();
        if (r0 != 0) goto L_0x00cf;
    L_0x008d:
        r0 = r7.y;
        if (r0 == 0) goto L_0x00cf;
    L_0x0091:
        r0 = 0;
    L_0x0092:
        r1 = r7.y;
        r1 = r1.length;
        if (r0 >= r1) goto L_0x00cf;
    L_0x0097:
        r1 = r7.y;
        r2 = r1[r0];
        r1 = dji.log.DJILogHelper.getInstance();
        r4 = "bob";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "speed[";
        r5 = r5.append(r6);
        r5 = r5.append(r0);
        r6 = "]";
        r5 = r5.append(r6);
        r6 = "= ";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r5 = r5.toString();
        r1.LOGE(r4, r5);
        r1 = r7.n;
        r1.a(r0, r2);
        r0 = r0 + 1;
        goto L_0x0092;
    L_0x00cf:
        r0 = k;
        if (r8 != r0) goto L_0x00ee;
    L_0x00d3:
        r0 = r9.length;
        r0 = new double[r0];
        r7.z = r0;
        r0 = r9.clone();
        r0 = (double[]) r0;
        r7.z = r0;
        r0 = r7.n;
        if (r0 == 0) goto L_0x00ee;
    L_0x00e4:
        r0 = r7.f();
        if (r0 != 0) goto L_0x00ee;
    L_0x00ea:
        r0 = r7.z;
        if (r0 == 0) goto L_0x00ee;
    L_0x00ee:
        r0 = r7.n;
        if (r0 == 0) goto L_0x00fd;
    L_0x00f2:
        r0 = r7.e();
        if (r0 == 0) goto L_0x00fd;
    L_0x00f8:
        r0 = r7.n;
        r0.q();
    L_0x00fd:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.multimoment.a.b.a(int, double[]):void");
    }

    public void a(int i) {
        if (this.n != null) {
            this.o.a(i);
            if (e()) {
                this.n.q();
            }
        }
    }

    protected void b() {
        if (this.n != null) {
            if (this.o != null) {
                if (this.v != null) {
                    this.o.a(this.v);
                }
                if (this.x != null) {
                    this.o.c(this.x);
                }
                if (this.w != null) {
                    this.o.b(this.w);
                }
                if (!(this.A == null || this.B == null)) {
                    this.o.a(this.A, this.B);
                }
            }
            if (this.y != null) {
                for (int i = 0; i < this.y.length; i++) {
                    double d = this.y[i];
                    if (d != 1.0d) {
                        this.n.a(i, d);
                    }
                }
            }
            if (this.z != null) {
                DJILogHelper.getInstance().LOGD("rxq", "volume count: " + this.z.length);
                this.n.a(this.z);
            }
        }
    }

    public void b(String[] strArr, double[] dArr, double[] dArr2, String str) {
        String[] strArr2 = new String[strArr.length];
        double[] dArr3 = new double[dArr.length];
        dArr3 = new double[dArr2.length];
        String str2 = new String();
        strArr2 = (String[]) strArr.clone();
        double[] dArr4 = (double[]) dArr2.clone();
        this.s = (double[]) dArr.clone();
        this.t = dArr4;
        this.r = strArr2;
        this.u = str;
        this.v = null;
        this.x = null;
        this.w = null;
    }

    public void b(int i) {
        o.a("enter setTemplateID");
        if (this.n != null) {
            this.o.b(i);
        }
        o.a("leave setTemplateID");
    }

    public void c(int i) {
        if (this.n != null) {
            this.o.c(i);
            this.n.q();
        }
    }

    public void a(String[] strArr, double[] dArr, double[] dArr2, double[] dArr3, String str, boolean z) {
        b(strArr, dArr, dArr2, dArr3, str, z);
        Log.e("zhang", "musicFileName:" + str);
        c();
    }

    public void b(String[] strArr, double[] dArr, double[] dArr2, double[] dArr3, String str, boolean z) {
        int i = 0;
        if (this.n != null) {
            h();
            k();
        }
        b(strArr, dArr, dArr2, str);
        long[] jArr = new long[this.s.length];
        long[] jArr2 = new long[this.s.length];
        this.I = 0;
        DJIMultiMomentEditActivity dJIMultiMomentEditActivity = (DJIMultiMomentEditActivity) getActivity();
        while (i < this.s.length) {
            jArr[i] = (long) this.s[i];
            jArr2[i] = (long) this.t[i];
            i++;
        }
        this.I = dJIMultiMomentEditActivity.g().s();
        this.I += e;
        a(this.r, jArr, jArr2, dArr3, this.u, z);
        this.n.a(this.p.getHolder().getSurface());
        this.n.b();
        this.v = null;
        this.x = null;
        this.w = null;
    }

    private void a(long j, long j2) {
        if (this.P != null) {
            this.P.setText(p.d((int) (j / 1000)) + d.t + p.d((int) ((500 + j2) / 1000)));
        }
    }

    public void c() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.H == 0 && this.n != null) {
            this.n.c();
            DJILogHelper.getInstance().LOGI("bob", "play after start " + (System.currentTimeMillis() - currentTimeMillis));
            b();
            DJILogHelper.getInstance().LOGI("bob", "play after setSegAttrToC " + (System.currentTimeMillis() - currentTimeMillis));
            this.H = 2;
        }
        this.L.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d(4);
            }
        });
        DJILogHelper.getInstance().LOGI("bob", "play");
    }

    public boolean d() {
        return this.H == 2;
    }

    public boolean e() {
        return this.H == 1;
    }

    public boolean f() {
        return this.H == 0;
    }

    public void a(long j) {
        if (this.n != null && this.H > 0) {
            this.n.a(j);
        }
        a(j, (long) this.I);
    }

    public void d(int i) {
        e.d(dji.g.b.a.a, "button = " + (i == 0));
        this.q.setVisibility(i);
    }

    public void g() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.n != null) {
            if (this.H == 2) {
                d(0);
                this.H = 1;
                this.n.e();
            } else {
                d(4);
                this.H = 2;
                this.n.c();
            }
        }
        DJILogHelper.getInstance().LOGI("bob", "pause " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void a(boolean z) {
        this.K = Boolean.valueOf(z);
    }

    public void b(boolean z) {
        this.K = Boolean.valueOf(z);
        if (this.n != null && this.q.getVisibility() == 4) {
            d(0);
            this.H = 1;
            this.n.e();
        }
    }

    public void h() {
        if (this.n != null) {
            this.n.d();
        }
        this.H = 0;
    }

    public void i() {
        e.d("FFMpegPlayerFragment", "mMediaPlayer=" + this.n);
        if (this.n != null) {
            this.n.d();
            this.n.a();
        }
        if (this.o != null) {
            this.L.post(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.o.b();
                }
            });
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        long currentTimeMillis = System.currentTimeMillis();
        Bundle arguments = getArguments();
        this.r = arguments.getStringArray(a);
        this.s = arguments.getDoubleArray(b);
        this.t = arguments.getDoubleArray(c);
        this.u = arguments.getString(d);
        this.E = false;
        this.G = 0;
        this.F = false;
        setRetainInstance(true);
        DJILogHelper.getInstance().LOGI("bob", "bbbbcreate " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_player, null);
        this.p = (SurfaceView) inflate.findViewById(R.id.coe);
        this.p.getHolder().addCallback(this);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.n == null) {
                    return;
                }
                if (this.a.H > 0) {
                    this.a.g();
                } else {
                    this.a.c();
                }
            }
        });
        this.q = (ImageView) inflate.findViewById(R.id.cof);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.n == null) {
                    return;
                }
                if (this.a.H > 0) {
                    this.a.g();
                    return;
                }
                this.a.h();
                this.a.c();
            }
        });
        this.P = (TextView) inflate.findViewById(R.id.cog);
        this.P.setVisibility(0);
        DJILogHelper.getInstance().LOGI("bob", "TemplatePlayerFragment onCreateView " + (System.currentTimeMillis() - currentTimeMillis));
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        if (!this.K.booleanValue()) {
            h();
        }
    }

    public void onStop() {
        super.onStop();
        if (!getActivity().isChangingConfigurations()) {
            k();
        }
    }

    public void onResume() {
        super.onResume();
        long currentTimeMillis = System.currentTimeMillis();
        DJIMultiMomentEditActivity dJIMultiMomentEditActivity = (DJIMultiMomentEditActivity) getActivity();
        if (this.n == null && !this.K.booleanValue()) {
            boolean z;
            long[] jArr = new long[this.s.length];
            long[] jArr2 = new long[this.s.length];
            this.I = 0;
            dJIMultiMomentEditActivity.m();
            for (int i = 0; i < this.s.length; i++) {
                jArr[i] = (long) this.s[i];
                jArr2[i] = (long) this.t[i];
                if (this.y != null) {
                    DJILogHelper.getInstance().LOGI("bob", "fast = " + this.y[i]);
                }
            }
            this.I = dJIMultiMomentEditActivity.g().s();
            DJILogHelper.getInstance().LOGI("bob", "mDuration = " + this.I);
            this.I += e;
            a(0, (long) this.I);
            DJILogHelper.getInstance().LOGI("bob", "TemplatePlayerFragment onResume befor createMediaPlayer " + (System.currentTimeMillis() - currentTimeMillis));
            if (dJIMultiMomentEditActivity.g().c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || dJIMultiMomentEditActivity.g().c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
                z = true;
            } else {
                z = false;
            }
            a(this.r, jArr, jArr2, this.y, this.u, z);
            DJILogHelper.getInstance().LOGI("bob", "TemplatePlayerFragment onResume after createMediaPlayer " + (System.currentTimeMillis() - currentTimeMillis));
        }
        if (!(!this.E || this.D == null || this.K.booleanValue())) {
            this.n.a(this.D.getSurface());
            this.n.b();
            c();
            if (this.H == 0 || this.H == 1) {
                d(0);
            } else {
                d(4);
            }
        }
        Log.i("zhang", "set simple!");
        if (dJIMultiMomentEditActivity.g().c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
            b(dJIMultiMomentEditActivity.o().getPosToID(dJIMultiMomentEditActivity.g().e()));
        }
        DJILogHelper.getInstance().LOGI("bob", "bbbb onResume " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
        DJILogHelper.getInstance().LOGI("bob", "surfaceCreated");
        final long currentTimeMillis = System.currentTimeMillis();
        this.D = surfaceHolder;
        this.E = true;
        DJILogHelper.getInstance().LOGI("bob", "surfaceCreated " + (System.currentTimeMillis() - currentTimeMillis));
        if (surfaceHolder != null && surfaceHolder.getSurface() != null && this.n != null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    this.c.n.a(surfaceHolder.getSurface());
                    this.c.n.b();
                    Activity activity = this.c.getActivity();
                    if (activity instanceof DJIMultiMomentEditActivity) {
                        ((DJIMultiMomentEditActivity) activity).n();
                    }
                    DJILogHelper.getInstance().LOGI("bob", "surfaceCreated before play " + (System.currentTimeMillis() - currentTimeMillis));
                    if (((DJIMultiMomentEditActivity) activity).g().c() == dji.pilot2.multimoment.videolib.b.SingleEdit) {
                        this.c.c();
                    } else if (!dji.pilot2.widget.a.a(this.c.getActivity(), 6)) {
                        this.c.c();
                    }
                    DJILogHelper.getInstance().LOGI("bob", "surfaceCreated after play " + (System.currentTimeMillis() - currentTimeMillis));
                }
            }).start();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        DJILogHelper.getInstance().LOGI("bob", "surfaceChanged");
        this.D = surfaceHolder;
        if (!this.F) {
            this.D.setFixedSize(i2, i3);
            this.F = true;
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.n != null) {
            DJILogHelper.getInstance().LOGI("wwwbbb", "surfaceDestroyed");
            this.n.a(null);
            k();
        }
        this.E = false;
    }

    public void a(a aVar) {
        this.C = aVar;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.n != null) {
            DJILogHelper.getInstance().LOGI("wwwbbb", "surfaceDestroyed");
            this.n.a(null);
            k();
        }
    }

    public void a(double d) {
        this.M = d;
        this.n.a(this.M);
    }

    public void a(double[] dArr) {
        this.N = dArr;
        this.n.a(this.N);
    }

    public boolean j() {
        if (this.n != null) {
            return this.n.n();
        }
        return false;
    }

    public boolean e(int i) {
        if (this.n != null) {
            return this.n.b(i);
        }
        return false;
    }
}
