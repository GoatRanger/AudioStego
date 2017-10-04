package dji.g.b;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.g.c.a.e;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.i.g;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class a implements g {
    public static String a = "DJIEditorMediaPlayer";
    private static final boolean f = false;
    private static boolean g = true;
    private static boolean h = false;
    private static int i = 2000;
    private boolean A = false;
    private c B;
    private String[] C;
    private long[] D;
    private long[] E;
    private double[] F;
    private String G;
    private String H = null;
    private boolean I = false;
    private e J = null;
    private Object K;
    private Object L = new Object();
    private boolean M = false;
    private g N = new g(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.v = true;
            this.a.x = false;
            dji.midware.media.e.c(a.g, this.a.w(), "video progress onStarted");
            this.a.x();
        }

        public void a(long j) {
            this.a.x = false;
            if (this.a.Q != null && this.a.c != null) {
                this.a.u = j;
                dji.midware.media.e.c(a.g, this.a.w(), "video progress = " + this.a.u);
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.Q.a(this.a.a.u);
                    }
                });
            }
        }

        public void a(int i) {
            this.a.x = true;
            dji.midware.media.e.c(a.g, this.a.w(), "video progress onFinished");
            this.a.y();
        }

        public void b(final int i) {
            this.a.x = false;
            dji.midware.media.e.c(a.g, this.a.w(), "VideoPlayer onError");
            Log.e(this.a.w(), "AudioPlayer onError");
            if (this.a.Q != null && this.a.c != null) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void run() {
                        this.b.a.Q.b(i);
                    }
                });
            }
        }
    };
    private g O = new g(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.w = true;
            this.a.y = false;
            dji.midware.media.e.c(a.g, this.a.w(), "audio progress onStarted");
            this.a.x();
        }

        public void a(long j) {
            this.a.y = false;
            dji.midware.media.e.c(a.g, this.a.w(), "audio progress = " + j + " (a-v) difference (ms)= " + (j - this.a.u));
            if (this.a.j == null) {
                this.a.Q.a(j);
            }
        }

        public void a(int i) {
            this.a.y = true;
            dji.midware.media.e.c(a.g, this.a.w(), "audio progress onFinished");
            this.a.y();
        }

        public void b(final int i) {
            this.a.y = false;
            dji.midware.media.e.c(a.g, this.a.w(), "AudioPlayer onError");
            Log.e(this.a.w(), "AudioPlayer onError");
            if (this.a.Q != null && this.a.c != null) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 b;

                    public void run() {
                        this.b.a.Q.b(i);
                    }
                });
            }
        }
    };
    private g P = null;
    private g Q = new g(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.c != null) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.P != null) {
                            dji.midware.media.e.d(this.a.a.w(), "callbackPreviewAll: onStarted");
                            this.a.a.P.a();
                        }
                    }
                });
            }
        }

        public void a(final long j) {
            if (this.a.c != null) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 b;

                    public void run() {
                        this.b.a.m = (long) ((int) j);
                        dji.midware.media.e.d(this.b.a.w(), "callbackPreviewAll: progress_ms=" + this.b.a.m);
                        if (this.b.a.P != null) {
                            this.b.a.P.a(this.b.a.m);
                        }
                    }
                });
            }
        }

        public void a(final int i) {
            if (this.a.c != null) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 b;

                    public void run() {
                        dji.midware.media.e.d(this.b.a.w(), "callbackPreviewAll: progress = onFinished");
                        if (this.b.a.P != null) {
                            this.b.a.P.a(i);
                        }
                        if (this.b.a.o != null) {
                            this.b.a.o.a(this.b.a);
                        }
                    }
                });
            }
        }

        public void b(int i) {
            Log.e(a.a, "video player throw exception");
            this.a.l = d.Error;
            if (this.a.P != null) {
                this.a.P.b(i);
            }
        }
    };
    private i R = new i(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.t != null) {
                this.a.t.a();
            }
        }

        public void a(int i) {
            if (this.a.t != null) {
                this.a.t.a((int) (((double) i) * 0.2d));
            }
        }

        public void b(int i) {
        }

        public void c(int i) {
            if (this.a.t != null) {
                this.a.t.c(i);
            }
        }
    };
    private i S = new i(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.k == null && this.a.t != null) {
                this.a.t.a();
            }
        }

        public void a(int i) {
            if (this.a.t != null) {
                if (this.a.k != null) {
                    i = (int) (20.0d + (((double) i) * 0.8d));
                }
                this.a.t.a(i);
            }
        }

        public void b(int i) {
            if (this.a.t != null) {
                this.a.t.b(i);
            }
        }

        public void c(int i) {
            if (this.a.t != null) {
                this.a.t.c(i);
            }
        }
    };
    int[] b;
    protected Handler c = new Handler(Looper.getMainLooper());
    HandlerThread d = new HandlerThread("DJIEditorMediaPlayer_Thread");
    int e = 0;
    private d j;
    private b k;
    private d l = d.Uninitialized;
    private long m;
    private int n;
    private dji.midware.media.i.g.a o;
    private dji.midware.media.i.g.b p;
    private f q;
    private f r;
    private boolean s = false;
    private i t = null;
    private long u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private double[] z;

    public static class a {
        public final int a;
        public final long b;
        public final Object c;

        public a(int i, long j) {
            this.a = i;
            this.b = j;
            this.c = null;
        }

        public a(int i, long j, Object obj) {
            this.a = i;
            this.b = j;
            this.c = obj;
        }

        public int a() {
            return this.a;
        }
    }

    public static class b {
        static final int a = 0;
        static final int b = 1;
        static final int c = 2;
        static final int d = 3;
        static final int e = 4;
        static final int f = 5;
        static final int g = 9;
        static final int h = 10;
        static final int i = 11;
        static final int j = 12;
        static final int k = 13;

        public static String a(int i) {
            switch (i) {
                case 0:
                    return "SeekTo";
                case 1:
                    return "Start";
                case 2:
                    return "Pause";
                case 3:
                    return "SetDisplay";
                case 4:
                    return "Stop";
                case 5:
                    return "SetAudioLevel";
                case 9:
                    return "SetSpeed";
                case 10:
                    return "Prepare";
                case 11:
                    return "Save";
                case 12:
                    return "Refresh";
                default:
                    return "Unrecognised";
            }
        }
    }

    private class c extends Handler {
        final /* synthetic */ a a;
        private a b = null;
        private boolean c = true;

        public c(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        private void b() {
            DJILogHelper.getInstance().LOGI("bob", "doPrepare stateOverall= " + this.a.l);
            if (this.a.l == d.Initialized || this.a.l == d.Stopped) {
                Object n;
                this.a.m = 0;
                this.a.x = false;
                this.a.y = false;
                synchronized (this.a.L) {
                    n = this.a.K;
                }
                if (!(this.a.q == null || n == null)) {
                    this.a.q.c = n;
                    this.a.q.b = this.a.N;
                    this.a.q.d = this.a.J;
                    this.a.j = new d();
                    try {
                        this.a.j.a(this.a.q);
                    } catch (Exception e) {
                        dji.midware.media.e.a(this.a.w(), e);
                    }
                }
                if (this.a.r != null) {
                    this.a.k = new b();
                    try {
                        this.a.k.a(this.a.r);
                        if (this.a.k != null) {
                            this.a.k.b();
                        }
                    } catch (Exception e2) {
                        dji.midware.media.e.a(this.a.w(), e2);
                        return;
                    }
                }
                this.a.l = d.Prepared;
                return;
            }
            this.c = false;
        }

        private void c() {
            int i = 0;
            double[] dArr = (double[]) this.b.c;
            int i2 = 0;
            while (i < dArr.length) {
                dji.midware.media.e.d(this.a.w(), "SetAudio No." + i + " old=" + this.a.z[i] + " new=" + dArr[i]);
                if (this.a.z[i] != dArr[i]) {
                    this.a.z[i] = dArr[i];
                    i2 = 1;
                }
                i++;
            }
            if (i2 != 0) {
                d();
            }
        }

        private void d() {
            if (this.a.k != null) {
                if (this.a.l == d.Playing) {
                    this.a.j.e();
                }
                this.a.k.d();
                this.a.k = null;
                f l = this.a.l();
                if (l != null) {
                    this.a.k = new b();
                    try {
                        this.a.k.a(l);
                        if (this.a.k != null) {
                            this.a.k.b();
                        }
                    } catch (Exception e) {
                        dji.midware.media.e.a(this.a.w(), e);
                        return;
                    }
                }
                dji.midware.media.e.d(this.a.w(), "audio seek to " + this.a.u);
                this.a.k.c();
                this.a.k.a((long) ((int) this.a.u));
                if (this.a.l == d.Playing) {
                    this.a.j.f();
                } else {
                    this.a.k.e();
                }
            }
        }

        private void e() {
            DJILogHelper.getInstance().LOGI("bob", "doStop stateOverall= " + this.a.l);
            this.a.l = d.Stopped;
            if (this.a.j != null) {
                this.a.j.g();
                this.a.j = null;
            }
            if (this.a.k != null) {
                this.a.k.d();
                this.a.k = null;
            }
        }

        public void a() {
            DJILogHelper.getInstance().LOGI("bob", "doPause stateOverall= " + this.a.l);
            if (this.a.l == d.Playing) {
                this.a.l = d.Paused;
                if (this.a.j != null) {
                    this.a.j.e();
                }
                if (this.a.k != null) {
                    this.a.k.e();
                    return;
                }
                return;
            }
            this.c = false;
        }

        private void f() {
            DJILogHelper.getInstance().LOGI("bob", "doStart stateOverall= " + this.a.l);
            if (this.a.l == d.Prepared || this.a.l == d.g || this.a.l == d.Paused) {
                if (this.a.l == d.Prepared || this.a.l == d.g) {
                    this.a.w = false;
                    this.a.v = false;
                }
                if (this.a.j != null) {
                    this.a.j.f();
                }
                if (this.a.k != null) {
                    if (this.a.l == d.Paused) {
                        this.a.k.a(this.a.m);
                    }
                    this.a.k.c();
                }
                this.a.l = d.Playing;
                return;
            }
            this.c = false;
        }

        private void g() {
            DJILogHelper.getInstance().LOGI("bob", "doSeekTo stateOverall= " + this.a.l);
            if (this.a.l == d.Prepared || this.a.l == d.Paused || this.a.l == d.Playing || this.a.l == d.g) {
                long j = this.b.b;
                if (Math.abs(j - this.a.m) < 200) {
                    dji.midware.media.e.c(a.g, this.a.w(), "seekTo() REJECT cur=" + this.a.m + " seek To=" + j);
                    return;
                }
                dji.midware.media.e.c(a.g, this.a.w(), "seekTo() Dealing cur=" + this.a.m + " seek To=" + j);
                this.a.x = false;
                this.a.y = false;
                if (this.a.j != null) {
                    this.a.j.a(1000 * j);
                }
                if (this.a.k != null) {
                    this.a.k.a(j);
                }
                if (this.a.h()) {
                    if (this.a.j != null) {
                        this.a.j.f();
                    }
                    if (this.a.k != null) {
                        this.a.k.c();
                    }
                }
                this.a.m = j;
                if (this.a.p != null) {
                    this.a.p.a(this.a);
                    return;
                }
                return;
            }
            this.c = false;
        }

        public void handleMessage(Message message) {
            this.b = (a) message.obj;
            try {
                dji.midware.media.e.c(a.h, this.a.w(), "Start to process CMD " + b.a(this.b.a) + " at state " + this.a.l);
                this.c = true;
                switch (this.b.a()) {
                    case 0:
                        g();
                        break;
                    case 1:
                        f();
                        break;
                    case 2:
                        a();
                        break;
                    case 3:
                        i();
                        break;
                    case 4:
                        e();
                        break;
                    case 5:
                        c();
                        break;
                    case 9:
                        k();
                        break;
                    case 10:
                        b();
                        break;
                    case 11:
                        j();
                        break;
                    case 12:
                        h();
                        break;
                    default:
                        this.c = false;
                        break;
                }
                if (!this.c) {
                    dji.midware.media.e.b(this.a.w(), "Ignore CMD " + b.a(this.b.a) + " at state " + this.a.l);
                }
                this.a.a(this.b);
            } catch (Exception e) {
                dji.midware.media.e.a(this.a.w(), e);
                removeCallbacksAndMessages(null);
                if (this.a.Q != null) {
                    this.a.Q.b(-1);
                }
                this.a.a(this.b);
            } catch (Throwable th) {
                this.a.a(this.b);
            }
        }

        private void h() {
            DJILogHelper.getInstance().LOGI("bob", "doRefresh stateOverall= " + this.a.l);
            if (this.a.l != d.Paused) {
                this.c = false;
            } else if (this.a.j != null) {
                this.a.j.a();
            }
        }

        private void i() {
        }

        private void j() {
            String str = null;
            h hVar = (h) this.b.c;
            if (this.a.j != null) {
                this.a.j.e();
            }
            if (this.a.k != null) {
                this.a.k.e();
            }
            if (this.a.s) {
                this.a.t = hVar.c;
                if (this.a.k != null) {
                    dji.midware.media.e.d(this.a.w(), "start to save audio...");
                    File file = new File(hVar.b);
                    ServiceManager.getInstance();
                    str = com.dji.frame.c.d.a(ServiceManager.getContext(), file.getName() + ".m4a");
                    dji.midware.media.e.d(this.a.w(), "audio tmpPath=" + str);
                    this.a.k.a(new h(str, this.a.R));
                    dji.midware.media.e.d(this.a.w(), "done with saving audio...");
                }
                if (this.a.j != null) {
                    dji.midware.media.e.d(this.a.w(), "start to save video...");
                    if (str != null && new File(str).exists()) {
                        hVar.f = str;
                    }
                    hVar.c = this.a.S;
                    this.a.j.a(hVar);
                    dji.midware.media.e.d(this.a.w(), "done with saving video...");
                }
                if (str != null) {
                    new File(str).delete();
                }
            } else if (this.a.j != null) {
                dji.midware.media.e.a("call mVideoPreview.save()");
                this.a.j.a(hVar);
            } else {
                if (this.a.k != null) {
                    this.a.k.d();
                    this.a.k = null;
                }
                this.a.r = new f();
                e eVar = new e(this.a.v(), dji.g.a.a.a.APP_RESOURCE, 0, hVar.d[0].f - hVar.d[0].e, false, 1.0d);
                f r = this.a.r;
                e[][] eVarArr = new e[2][];
                eVarArr[0] = hVar.d;
                eVarArr[1] = new e[]{eVar};
                r.a = eVarArr;
                this.a.k = new b();
                try {
                    this.a.k.a(this.a.r);
                    this.a.k.b();
                    this.a.k.e();
                    this.a.k.a(new h(hVar.b, hVar.c));
                } catch (Exception e) {
                    dji.midware.media.e.a(this.a.w(), e);
                    this.a.e = -1;
                    return;
                }
            }
            dji.midware.media.e.d(this.a.w(), "overall saving result=" + 0);
            this.a.e = 0;
        }

        private void k() {
            DJILogHelper.getInstance().LOGI("bob", "doSetSpeed stateOverall= " + this.a.l);
            if (this.a.l == d.Paused || this.a.l == d.Playing || this.a.l == d.g) {
                int i = (int) this.b.b;
                double doubleValue = ((Double) this.b.c).doubleValue();
                if (this.a.F[i] != doubleValue) {
                    long h = (long) (((((double) this.a.m) * this.a.F[i]) / doubleValue) * 1000.0d);
                    this.a.F[i] = doubleValue;
                    if (this.a.j != null) {
                        this.a.j.e();
                        this.a.j.a(i, doubleValue);
                        this.a.j.a(h);
                        if (this.a.l == d.Playing) {
                            this.a.j.f();
                        }
                    }
                    if (this.a.k != null) {
                        d();
                        return;
                    }
                    return;
                }
                return;
            }
            this.c = false;
        }
    }

    public enum d {
        Uninitialized,
        Initialized,
        Preparing,
        Prepared,
        Playing,
        Paused,
        g,
        Stopped,
        Seeking_at_Pause,
        End,
        Error
    }

    public a() {
        dji.midware.media.e.b(w(), "create a DJIEditorMediaPlayer object");
        this.d.start();
        dji.midware.media.e.d(w(), "thread started");
        this.B = new c(this, this.d.getLooper());
        dji.midware.media.e.d(w(), "controller created");
    }

    public synchronized void a(String str) {
        dji.midware.media.e.b(w(), "setDataSource() cut");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(str, null);
        }
    }

    public synchronized void a(e[][] eVarArr, e[][] eVarArr2) {
        dji.midware.media.e.b(w(), "setDataSource() bigfilm");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.M = true;
            this.q = new f();
            this.q.a = eVarArr;
            this.s = true;
            if (eVarArr2 != null) {
                this.r = new f();
                this.r.a = eVarArr2;
                this.z = new double[this.r.a[0].length];
                this.n = 0;
                int i = 0;
                while (i < this.r.a[0].length) {
                    double d = 1.0d;
                    if (!(this.F == null || this.F[i] == 0.0d)) {
                        d = this.F[i];
                    }
                    this.n = (int) (((long) (((double) (this.r.a[0][i].f - this.r.a[0][i].e)) / d)) + ((long) this.n));
                    i++;
                }
            }
            this.l = d.Initialized;
        }
    }

    public synchronized void a(String[] strArr, long[] jArr, long[] jArr2, boolean z, double[] dArr, String str, boolean z2) {
        dji.midware.media.e.b(w(), "setDataSource() single/multiple template");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            if (!(strArr == null || jArr == null || jArr2 == null)) {
                if (strArr.length == jArr.length && jArr.length == jArr2.length && (dArr == null || jArr2.length == dArr.length)) {
                    int i;
                    this.s = true;
                    this.M = z2;
                    this.C = (String[]) strArr.clone();
                    this.D = (long[]) jArr.clone();
                    this.E = (long[]) jArr2.clone();
                    if (dArr != null) {
                        this.F = (double[]) dArr.clone();
                    } else {
                        this.F = new double[this.C.length];
                        for (i = 0; i < this.F.length; i++) {
                            this.F[i] = 1.0d;
                        }
                    }
                    this.I = z;
                    this.G = str;
                    for (i = 0; i < jArr.length; i++) {
                        dji.midware.media.e.c(g, w(), "setDataSource: File " + i + " start=" + jArr[i] + " end=" + jArr2[i]);
                    }
                    this.z = new double[strArr.length];
                    this.n = z ? i : 0;
                    int i2 = 0;
                    while (i2 < jArr2.length) {
                        double d = 1.0d;
                        if (!(dArr == null || dArr[i2] == 0.0d)) {
                            d = dArr[i2];
                        }
                        this.n = (int) (((long) (((double) (jArr2[i2] - jArr[i2])) / d)) + ((long) this.n));
                        i2++;
                    }
                    this.q = u();
                    this.r = l();
                    this.l = d.Initialized;
                }
            }
            throw new RuntimeException("invalid parameters: " + String.format("paths=%s starts_ms=%s ends_ms=%s speeds=%s", new Object[]{Arrays.toString(strArr), Arrays.toString(jArr), Arrays.toString(jArr2), Arrays.toString(dArr)}));
        }
    }

    public synchronized void a(String str, String str2) {
        dji.midware.media.e.b(w(), "setDataSource()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            dji.midware.media.e.a aVar = new dji.midware.media.e.a();
            aVar.a(str);
            this.n = aVar.b();
            if (this.n == 0) {
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(str);
                    mediaPlayer.prepare();
                    this.n = mediaPlayer.getDuration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dji.midware.media.e.d(w(), "duration = " + this.n);
            this.G = str2;
            this.C = new String[]{str};
            this.D = new long[]{0};
            this.E = new long[]{(long) this.n};
            this.F = new double[]{1.0d};
            this.z = new double[]{1.0d};
            dji.midware.media.e.c(g, w(), "setDataSource: start=0 end=" + this.n);
            this.q = u();
            this.r = l();
            this.l = d.Initialized;
        }
    }

    protected void k() {
        this.b = new int[this.C.length];
        for (int i = 0; i < this.C.length; i++) {
            dji.midware.media.d.d a = dji.g.a.a.a(this.C[i], dji.g.a.a.a(this.C[i]));
            try {
                a.a(this.C[i]);
            } catch (IOException e) {
            }
            this.b[i] = a.g();
            dji.midware.media.e.d(w(), "segment index=" + i + ", audio track=" + this.b[i]);
        }
    }

    protected f l() {
        dji.g.a.a.a a;
        e eVar;
        long currentTimeMillis = System.currentTimeMillis();
        Vector vector = new Vector();
        DJILogHelper.getInstance().LOGI(w(), "previewInitAudio 1  " + (System.currentTimeMillis() - currentTimeMillis));
        k();
        this.A = false;
        int i = 0;
        while (i < this.C.length) {
            DJILogHelper.getInstance().LOGI(w(), "previewInitAudio 2-" + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (System.currentTimeMillis() - currentTimeMillis));
            if (this.b[i] >= 0) {
                this.A = true;
            }
            double d = 1.0d;
            if (this.F != null) {
                d = this.F[i];
            }
            dji.midware.media.e.d(w(), "initializing audio: seg=" + i + " level=" + this.z[i] + " track=" + this.b[i]);
            if (this.z[i] > 0.0d && this.b[i] >= 0) {
                a = dji.g.a.a.a(this.C[i]);
                dji.midware.media.e.b(w(), "fileType=" + a);
                if (a.equals(dji.g.a.a.a.DRONE_PRODUCED)) {
                    a = dji.g.a.a.a.FOOTAGE;
                }
                eVar = new e(this.C[i], a, this.D[i], this.E[i], false, d);
                eVar.a(this.z[i]);
                vector.add(eVar);
            } else if (vector.size() <= 0 || !((e) vector.lastElement()).b.equals(v())) {
                eVar = new e(v(), dji.g.a.a.a.APP_RESOURCE, 0, (long) (((double) (this.E[i] - this.D[i])) / d), false, 1.0d);
                eVar.a(0.0d);
                vector.add(eVar);
            } else {
                e eVar2 = (e) vector.lastElement();
                eVar2.f += (long) (((double) (this.E[i] - this.D[i])) / d);
            }
            i++;
        }
        DJILogHelper.getInstance().LOGI(w(), "previewInitAudio 3  " + (System.currentTimeMillis() - currentTimeMillis));
        if (this.I) {
            dji.midware.media.e.d(w(), "Add Logo. Logo time=" + this.I);
            if (vector.size() <= 0 || !((e) vector.lastElement()).b.equals(v())) {
                eVar = new e(v(), dji.g.a.a.a.APP_RESOURCE, 0, (long) i, false, 1.0d);
                eVar.a(0.0d);
                vector.add(eVar);
            } else {
                eVar2 = (e) vector.lastElement();
                eVar2.f += (long) i;
            }
        }
        DJILogHelper.getInstance().LOGI("bob", "previewInitAudio 4  " + (System.currentTimeMillis() - currentTimeMillis));
        String v = this.G != null ? this.G : v();
        if (v == null) {
            a = dji.g.a.a.a.NONE;
        } else {
            a = dji.g.a.a.a(v);
        }
        eVar = new e(v, a, 0, (long) this.n, false, 1.0d);
        if (this.M) {
            eVar.a(false);
        } else {
            eVar.a(true);
        }
        e[] eVarArr = new e[]{eVar};
        f fVar = new f();
        r5 = new e[2][];
        r5[0] = (e[]) vector.toArray(new e[0]);
        r5[1] = eVarArr;
        fVar.a = r5;
        fVar.b = this.O;
        dji.midware.media.e.b(w(), "original audio available = " + this.A);
        return fVar;
    }

    public synchronized void a() {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (this.l != d.End) {
            dji.midware.media.e.b(w(), "release() start");
            d();
            t();
            this.l = d.End;
            dji.midware.media.e.b(w(), "Has released a DJIEditorMediaPlayer object");
        } else {
            dji.midware.media.e.b(w(), "release() called. It is already at Unitialized or End state");
        }
    }

    @SuppressLint({"NewApi"})
    private void t() {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (this.d != null && this.d.isAlive()) {
            try {
                if (dji.midware.media.d.a() >= 18) {
                    this.d.quitSafely();
                } else {
                    this.d.quit();
                }
                this.d.join();
            } catch (Exception e) {
                dji.midware.media.e.a(w(), e);
            }
            this.d = null;
        }
    }

    public void a(Object obj) {
        dji.midware.media.e.b(w(), "setDisplay() = " + obj);
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return;
        }
        synchronized (this.L) {
            this.K = obj;
        }
    }

    private f u() {
        e[] eVarArr = new e[this.C.length];
        for (int i = 0; i < eVarArr.length; i++) {
            double d = 1.0d;
            if (this.F != null) {
                d = this.F[i];
            }
            eVarArr[i] = new e(this.C[i], dji.g.a.a.a(this.C[i]), this.D[i], this.E[i], false, d);
            DJILogHelper.getInstance().LOGI("wwwbbb", "time " + (this.E[i] - this.D[i]));
        }
        if (this.s) {
            eVarArr[eVarArr.length - 1].b(this.I ? (long) i : 0);
            f fVar = new f();
            fVar.a = new e[][]{eVarArr};
            fVar.b = this.N;
            return fVar;
        }
        fVar = new f();
        fVar.a = new e[][]{eVarArr};
        fVar.b = this.Q;
        return fVar;
    }

    public synchronized void b() {
        dji.midware.media.e.b(w(), "prepare()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (!(this.q == null && this.r == null)) {
            a(10, 0, null);
        }
    }

    private String v() {
        if (this.H == null) {
            try {
                ServiceManager.getInstance();
                this.H = com.dji.frame.c.d.a(ServiceManager.getContext(), "videoeditor/single_music/blank.m4a");
                if (!new File(this.H).exists()) {
                    this.H = Environment.getExternalStorageDirectory().getPath() + "/DJI/dji.pilot/videoeditor/single_music/blank.m4a";
                }
            } catch (Exception e) {
                dji.midware.media.e.a(w(), e);
            }
        }
        return this.H;
    }

    public synchronized void m() {
    }

    public synchronized void c() {
        dji.midware.media.e.b(w(), "start()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(1, 0, null);
        }
    }

    public void finalize() {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            t();
        }
    }

    private String w() {
        return a + "_" + hashCode();
    }

    public synchronized void a(int i, double d) {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            if (i >= 0) {
                if (i < this.F.length) {
                    if (d <= 0.0d || d >= 15.0d) {
                        throw new RuntimeException(String.format("setSpeed(). invalid speed=%d. valid range=(0,%d)", new Object[]{Double.valueOf(d), Integer.valueOf(15)}));
                    } else {
                        dji.midware.media.e.b(w(), "setSpeed() index=" + i + " speed=" + d);
                        a(9, (long) i, Double.valueOf(d));
                    }
                }
            }
            throw new RuntimeException(String.format("setSpeed(). invalid index=%d. valid range=[0,%d]", new Object[]{Integer.valueOf(i), Integer.valueOf(this.F.length - 1)}));
        }
    }

    public synchronized void a(e eVar) {
        this.J = eVar;
    }

    public synchronized void a(double d) {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (this.z == null) {
            dji.midware.media.e.d(a, "No audio has been set. Ignore setOriginalAudioLevel()");
        } else {
            dji.midware.media.e.d(w(), "set originalAudioLevel for all =" + d);
            double[] dArr = new double[this.z.length];
            for (int i = 0; i < this.z.length; i++) {
                dArr[i] = d;
            }
            a(dArr);
        }
    }

    public synchronized void a(double[] dArr) {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (this.z == null) {
            dji.midware.media.e.d(a, "No audio has been set. Ignore setOriginalAudioLevel()");
        } else if (dArr == null) {
            throw new RuntimeException("input level[] is null");
        } else if (dArr.length != this.z.length) {
            throw new RuntimeException("input level[]'s length is " + dArr.length + " the expected length is " + this.z.length);
        } else {
            for (int i = 0; i < this.z.length; i++) {
                dji.midware.media.e.d(w(), "segment=" + i + " originalAudioLevel=" + dArr[i]);
            }
            dji.midware.media.e.b(w(), "resetAudioMode() current state=" + this.l.toString());
            a(5, 0, dArr);
        }
    }

    public boolean n() {
        if (this.l != d.Error) {
            return this.A;
        }
        Log.e(a, "PreviewState.Error. Simply return");
        return false;
    }

    public boolean b(int i) {
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return false;
        } else if (i >= this.b.length || this.b[i] >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized void d() {
        dji.midware.media.e.b(w(), "stop a DJIEditorMediaPlayer object. stop() start");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(4, 0, null);
            dji.midware.media.e.b(w(), "stop a DJIEditorMediaPlayer object. stop() end");
        }
    }

    public synchronized void e() {
        dji.midware.media.e.b(w(), "pause()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(2, 0, null);
        }
    }

    public int f() {
        if (this.l != d.Error) {
            return (int) this.m;
        }
        Log.e(a, "PreviewState.Error. Simply return");
        return 0;
    }

    public int g() {
        if (this.l != d.Error) {
            return this.n;
        }
        Log.e(a, "PreviewState.Error. Simply return");
        return 1;
    }

    public synchronized void a(long j) {
        dji.midware.media.e.b(w(), "seekTo() " + j + " ms");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            if (j < 0) {
                j = 0;
            }
            this.B.removeMessages(0);
            b(0, j, null);
        }
    }

    public boolean h() {
        return this.l == d.Playing;
    }

    public boolean i() {
        return this.l == d.Paused;
    }

    public synchronized void j() {
        dji.midware.media.e.b(w(), "reset()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (this.l == d.End || this.l == d.Error) {
            this.l = d.Uninitialized;
        } else if (this.l != d.Uninitialized) {
            d();
            this.l = d.Uninitialized;
        }
    }

    public synchronized void a(int i) {
    }

    public synchronized void a(dji.midware.media.i.g.a aVar) {
        this.o = aVar;
    }

    public boolean o() {
        return this.k != null;
    }

    public boolean p() {
        return this.j != null;
    }

    public synchronized void a(dji.midware.media.i.g.b bVar) {
        this.p = bVar;
    }

    private void x() {
        if (this.q != null && !this.v) {
            return;
        }
        if ((this.r == null || this.w) && this.Q != null && this.c != null) {
            dji.midware.media.e.c(g, w(), "progress all = onStarted");
            this.c.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.Q.a();
                }
            });
        }
    }

    private void y() {
        if (this.q != null && !this.x) {
            return;
        }
        if (this.r == null || this.y) {
            this.l = d.g;
            if (this.Q != null && this.c != null) {
                dji.midware.media.e.c(g, w(), "onPartFinished: progress all = onFinished");
                this.c.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.Q.a(0);
                    }
                });
            }
        }
    }

    public synchronized void a(g gVar) {
        this.P = gVar;
    }

    public synchronized int a(h hVar) {
        int i;
        dji.midware.media.e.b(w(), "save() param=" + hVar);
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            i = -1;
        } else {
            a(11, 0, hVar);
            i = this.e;
        }
        return i;
    }

    public synchronized void q() {
        dji.midware.media.e.b(w(), "refresh()");
        if (this.l == d.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(12, 0, null);
        }
    }

    private void a(int i, long j, Object obj) {
        if (this.B != null && this.d != null && this.d.isAlive()) {
            a aVar = new a(i, j, obj);
            synchronized (aVar) {
                this.B.sendMessage(this.B.obtainMessage(0, aVar));
                dji.midware.media.e.d(g, w(), "UI Signal " + this.B.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
                try {
                    aVar.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dji.midware.media.e.c(g, w(), "Done " + b.a(i));
        }
    }

    private void b(int i, long j, Object obj) {
        if (this.B != null && this.d != null && this.d.isAlive()) {
            this.B.sendMessage(this.B.obtainMessage(0, new a(i, j, obj)));
            dji.midware.media.e.d(g, w(), "UI Signal " + this.B.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
        }
    }

    private void a(a aVar) {
        synchronized (aVar) {
            aVar.notifyAll();
        }
    }
}
