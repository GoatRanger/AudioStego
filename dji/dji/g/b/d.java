package dji.g.b;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class d {
    public static final String a = "VideoPreview";
    public static final String b = "VideoPreview_Main";
    public static final boolean c = true;
    public static final boolean d = false;
    public static final int e = 15;
    HandlerThread f = new HandlerThread("VideoPreviewThread");
    long g = System.currentTimeMillis();
    private f h = null;
    private d i;
    private dji.g.b.a.f[] j;
    private dji.g.b.a.e[] k;
    private Surface[] l;
    private c m;
    private long n;
    private boolean o = false;

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
        static final int g = 6;
        static final int h = 7;
        static final int i = 8;
        static final int j = 9;
        static final int k = 10;
        static final int l = 11;
        static final int m = 12;
        static final int n = 13;

        public static String a(int i) {
            switch (i) {
                case 0:
                    return "SeekTo";
                case 1:
                    return "Start";
                case 2:
                    return "Pause";
                case 3:
                    return "SetWindow";
                case 4:
                    return "Stop";
                case 5:
                    return "FeedData";
                case 6:
                    return "Display";
                case 7:
                    return "RemoveInputFile";
                case 8:
                    return "AddInputFile";
                case 9:
                    return "SetSpeed";
                case 10:
                    return "Init";
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
        final /* synthetic */ d a;
        private String b = "VideoPreview_Controller";
        private f c = f.Uninitialized;
        private a d = null;
        private boolean e = true;

        public c(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        private void a(int i) {
            a aVar = new a(i, 0, null);
            this.a.m.sendMessage(this.a.m.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(true, this.b, "Signal to Controller. CMD: " + b.a(i));
        }

        private void a(int i, long j, Object obj) {
            a aVar = new a(i, j, obj);
            this.a.m.sendMessage(this.a.m.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(true, this.b, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
        }

        private void a(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.a.m.sendMessageDelayed(this.a.m.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(false, this.b, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        private void b(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.a.m.sendMessageAtTime(this.a.m.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(false, this.b, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        private void b() {
            if (this.c == f.Uninitialized) {
                this.a.j();
                this.c = f.Initialized;
                return;
            }
            this.e = false;
        }

        private void c() {
            if (this.c == f.Initialized) {
                this.a.i.a((Surface) this.d.c);
                return;
            }
            this.e = false;
        }

        private void d() {
            if (this.c == f.Uninitialized) {
                this.c = f.Stopped;
            } else if (this.c == f.Initialized) {
                this.c = f.Stopped;
                g();
            } else if (this.c == f.Paused || this.c == f.Playing || this.c == f.e) {
                this.c = f.Stopped;
                g();
            } else {
                this.e = false;
            }
        }

        public void a() {
            if (this.c == f.Playing) {
                this.c = f.Paused;
                removeMessages(5);
                removeMessages(6);
                dji.midware.media.e.c(true, this.b, "paused()");
                return;
            }
            this.e = false;
        }

        private void e() throws dji.g.b.a.b {
            int i = 0;
            if (this.c == f.Initialized || this.c == f.e) {
                this.c = f.Playing;
                this.a.n = 0;
                while (i < this.a.h.a.length) {
                    this.a.j[i].d(0);
                    this.a.k[i].a(0);
                    i++;
                }
                this.a.i.a(0);
                this.a.i.c();
                a(5, 0, null);
                a(6, 33, 0, null);
            } else if (this.c == f.Paused) {
                this.c = f.Playing;
                this.a.i.c();
                a(5, 0, null);
                a(6, 33, 0, null);
            } else {
                this.e = false;
            }
        }

        private void f() throws dji.g.b.a.b {
            if (this.c == f.Paused || this.c == f.Playing || this.c == f.e) {
                long j = this.d.b;
                if (Math.abs(j - this.a.n) < 200000 || j >= this.a.i()) {
                    dji.midware.media.e.c(true, this.b, "seekTo() REJECT cur=" + this.a.n + " seek To=" + j);
                    return;
                }
                int i;
                removeMessages(5);
                removeMessages(6);
                dji.midware.media.e.c(true, this.b, "Accept seekTo cur=" + this.a.n + " seek To=" + j);
                this.a.n = j;
                for (i = 0; i < this.a.h.a.length; i++) {
                    this.a.j[i].d(j);
                    this.a.k[i].a(j);
                }
                this.a.i.a(j);
                this.a.i.c();
                if (this.c == f.Paused || this.c == f.e) {
                    this.c = f.Seeking_at_Pause;
                    boolean z = false;
                    while (!this.a.i.a && !r0) {
                        for (i = 0; i < this.a.h.a.length; i++) {
                            this.a.k[i].j();
                        }
                        z = this.a.i.a(false);
                        if (!z) {
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (this.a.i.a) {
                        this.c = f.e;
                        return;
                    } else {
                        this.c = f.Paused;
                        return;
                    }
                } else if (this.c == f.Playing) {
                    a(5, 0, null);
                    a(6, 33, 0, null);
                    return;
                } else {
                    return;
                }
            }
            this.e = false;
        }

        public void handleMessage(Message message) {
            this.d = (a) message.obj;
            try {
                dji.midware.media.e.c(false, this.b, "Start to process CMD " + b.a(this.d.a) + " at state " + this.c);
                this.e = true;
                switch (this.d.a()) {
                    case 0:
                        f();
                        break;
                    case 1:
                        e();
                        break;
                    case 2:
                        a();
                        break;
                    case 3:
                        c();
                        break;
                    case 4:
                        d();
                        break;
                    case 5:
                        j();
                        break;
                    case 6:
                        i();
                        break;
                    case 7:
                        n();
                        break;
                    case 8:
                        m();
                        break;
                    case 9:
                        l();
                        break;
                    case 10:
                        b();
                        break;
                    case 11:
                        k();
                        break;
                    case 12:
                        h();
                        break;
                    default:
                        this.e = false;
                        break;
                }
                if (!this.e) {
                    dji.midware.media.e.b(d.b, "Ignore CMD " + b.a(this.d.a) + " at state " + this.c);
                }
                this.a.a(this.d);
            } catch (Exception e) {
                dji.midware.media.e.a(d.b, e);
                removeCallbacksAndMessages(null);
                this.c = f.Error;
                g();
                if (this.a.h.b != null) {
                    this.a.h.b.b(-1);
                }
                this.a.a(this.d);
            } catch (Throwable th) {
                this.a.a(this.d);
            }
        }

        private void g() {
            int i = 0;
            try {
                if (this.a.k != null) {
                    for (int i2 = 0; i2 < this.a.k.length; i2++) {
                        if (this.a.k != null) {
                            this.a.k[i2].c();
                            this.a.k[i2] = null;
                        }
                    }
                }
                if (this.a.i != null) {
                    this.a.i.b();
                    this.a.i = null;
                }
                if (this.a.j != null) {
                    while (i < this.a.j.length) {
                        if (this.a.j[i] != null) {
                            this.a.j[i].c();
                            this.a.j[i] = null;
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                dji.midware.media.e.a(this.b, e);
            }
        }

        private void h() {
            if (this.c == f.Paused) {
                this.a.i.a();
            } else {
                this.e = false;
            }
        }

        private void i() throws dji.g.b.a.b {
            if (this.c == f.Playing) {
                this.a.g = System.currentTimeMillis();
                this.a.i.a(false);
                long currentTimeMillis = System.currentTimeMillis() - this.a.g;
                if (this.a.i.a) {
                    this.c = f.e;
                    return;
                }
                Object obj = null;
                long j = -1;
                long j2 = 0;
                while (j < 0 && this.a.n < this.a.i()) {
                    j = (this.a.i.e + (this.a.n / 1000)) + 33;
                    j2 = j - SystemClock.uptimeMillis();
                    long j3;
                    if (j2 < 0) {
                        dji.midware.media.e.c(true, this.b, String.format("delay<0, %d(base_system_uptime)+%d(target_pts)+33-%d(now)=%d", new Object[]{Long.valueOf(this.a.i.e), Long.valueOf(this.a.n / 1000), Long.valueOf(SystemClock.uptimeMillis()), Long.valueOf(j2)}));
                        this.a.n = this.a.n + dji.midware.media.d.e();
                        for (dji.g.b.a.e b : this.a.k) {
                            b.b(this.a.n);
                        }
                        obj = 1;
                        j3 = j;
                        j = j2;
                        j2 = j3;
                    } else {
                        j3 = j;
                        j = j2;
                        j2 = j3;
                    }
                }
                if (obj != null) {
                    this.a.i.a(true);
                }
                dji.midware.media.e.c(true, this.b, "display current time=" + SystemClock.uptimeMillis() + " pts=" + (this.a.n / 1000) + " schedule=" + j2 + " difference=" + (j2 - SystemClock.uptimeMillis()) + " duration=" + currentTimeMillis + " delay=" + j);
                a(6, j, 0, null);
                return;
            }
            this.e = false;
        }

        private void j() throws dji.g.b.a.b {
            if (this.c == f.Playing) {
                int a;
                long currentTimeMillis = System.currentTimeMillis();
                int i = 0;
                while (i < this.a.j.length) {
                    a = this.a.j[i].a(this.a.n);
                    if (a > this.a.j[i].h() && a < this.a.h.a[i].length) {
                        this.a.j[i].d(this.a.n);
                        this.a.k[i].a(this.a.n);
                    }
                    i++;
                }
                for (a = 0; a < 4; a++) {
                    for (i = 0; i < this.a.k.length; i++) {
                        this.a.k[i].j();
                        this.a.k[i].i();
                    }
                }
                boolean z = true;
                for (dji.g.b.a.f b : this.a.j) {
                    if (!b.b()) {
                        z = false;
                    }
                }
                if (z) {
                    dji.midware.media.e.c(true, this.b, "reader.isInputFileEOS=true");
                } else {
                    a(5, 5, 0, null);
                }
                dji.midware.media.e.d(true, this.b, "doFeedData duration=" + (System.currentTimeMillis() - currentTimeMillis));
                return;
            }
            this.e = false;
        }

        private void k() {
            int i = 0;
            if (this.c == f.e || this.c == f.Paused || this.c == f.Playing || this.c == f.Initialized) {
                if (this.a.k != null) {
                    while (i < this.a.k.length) {
                        this.a.k[i].c();
                        this.a.k[i] = null;
                        i++;
                    }
                    this.a.k = null;
                }
                h hVar = (h) this.d.c;
                if (this.a.h.d != null) {
                    this.a.h.d.a(hVar.l, hVar.m);
                }
                try {
                    new c().a(hVar);
                } catch (Exception e) {
                    dji.midware.media.e.a(this.b, e);
                    if (hVar.c != null) {
                        hVar.c.c(1);
                    }
                }
                if (this.a.h.d != null) {
                    this.a.h.d.a(this.a.i.d.l, this.a.i.d.m);
                    return;
                }
                return;
            }
            this.e = false;
        }

        private void l() {
            if (this.c == f.Paused || this.c == f.Playing || this.c == f.e) {
                int i = (int) this.d.b;
                double doubleValue = ((Double) this.d.c).doubleValue();
                if (doubleValue <= 0.0d || doubleValue > 15.0d) {
                    dji.midware.media.e.b(d.b, "the speed=" + doubleValue + " is not in a valid range");
                    return;
                }
                dji.midware.media.e.c(true, d.b, "setSpeed() index=" + i + " s=" + doubleValue);
                this.a.j[0].a(i, doubleValue);
                this.a.j[0].j();
                this.a.i.c();
                return;
            }
            this.e = false;
        }

        private void m() {
            if (this.c == f.Paused || this.c == f.Playing || this.c == f.e) {
                this.a.j[0].a((int) this.d.b, (e) this.d.c);
                this.a.j[0].j();
                return;
            }
            this.e = false;
        }

        private void n() {
            if (this.c == f.Paused || this.c == f.Playing || this.c == f.e) {
                this.a.j[0].a((int) this.d.b);
                this.a.j[0].j();
                return;
            }
            this.e = false;
        }
    }

    private class d {
        boolean a = false;
        final /* synthetic */ d b;
        private String c = "VideoPreview_Displayer";
        private e d;
        private long e;

        public d(d dVar) {
            this.b = dVar;
            this.d = new e();
            this.d.a();
        }

        public void a() {
            if (this.d != null) {
                this.d.b();
            }
        }

        public void b() {
            if (this.d != null) {
                this.d.c();
            }
        }

        public void a(long j) {
            this.a = false;
        }

        public void c() {
            this.e = SystemClock.uptimeMillis() - (this.b.n / 1000);
        }

        public void a(Surface surface) {
            this.d.a((Object) surface);
        }

        public boolean a(boolean z) throws dji.g.b.a.b {
            int i = 0;
            if (this.a) {
                return false;
            }
            int i2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" got_pts=");
            for (i2 = 0; i2 < this.b.k.length; i2++) {
                stringBuilder.append("[d" + i2 + "]=" + this.b.k[i2].h() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuilder.append(" target_pts=" + this.b.n);
            stringBuilder.append(" maxPts=" + this.b.i() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (this.b.n <= this.b.i()) {
                boolean z2 = true;
                boolean z3 = true;
                for (i2 = 0; i2 < this.b.k.length; i2++) {
                    this.b.k[i2].i();
                    if (this.b.k[i2].h() < this.b.n) {
                        z3 = false;
                    }
                    if (this.b.k[i2].h() < 0) {
                        z2 = false;
                    }
                }
                if (z2 && (z || r4)) {
                    this.d.a(this.b.n, false);
                    if (this.b.h.b != null) {
                        dji.midware.media.e.c(true, this.c, "callback progress 1: " + stringBuilder.toString() + " base_time" + this.e + " now=" + SystemClock.uptimeMillis());
                        try {
                            this.b.h.b.a(this.b.n / 1000);
                        } catch (Exception e) {
                            dji.midware.media.e.a(d.b, e);
                        }
                    }
                    if (!z) {
                        this.b.n = this.b.n + dji.midware.media.d.e();
                        while (i < this.b.k.length) {
                            this.b.k[i].b(this.b.n);
                            i++;
                        }
                    }
                    return true;
                }
                dji.midware.media.e.c(true, this.c, "No updated frame for DISPLAY." + stringBuilder.toString());
                return false;
            }
            if (this.b.h.b != null) {
                dji.midware.media.e.c(true, this.c, "callback progress 3: " + stringBuilder.toString());
                try {
                    this.b.h.b.a(this.b.n / 1000);
                } catch (Exception e2) {
                    dji.midware.media.e.a(d.b, e2);
                }
                try {
                    this.b.h.b.a(0);
                } catch (Exception e22) {
                    dji.midware.media.e.a(d.b, e22);
                }
                dji.midware.media.e.c(true, d.b, "call back onFinish()");
            }
            this.a = true;
            return false;
        }

        private boolean d() {
            boolean z = true;
            for (dji.g.b.a.e g : this.b.k) {
                if (!g.g()) {
                    z = false;
                }
            }
            return z;
        }
    }

    private class e {
        final /* synthetic */ d a;
        private String b;
        private dji.midware.media.h.a c;
        private dji.midware.media.h.a.b d;
        private dji.midware.media.h.a.b e;
        private int[] f;
        private SurfaceTexture[] g;
        private float[][] h;
        private dji.midware.media.h.d.a[] i;
        private dji.midware.media.h.d.a j;
        private int k;
        private int l;
        private int m;
        private long n;

        private e(d dVar) {
            this.a = dVar;
            this.b = "VideoPreview_OpenGLMgr";
            this.c = new dji.midware.media.h.a();
            this.d = new dji.midware.media.h.a.b(true);
            this.e = new dji.midware.media.h.a.b(false);
            this.n = 0;
        }

        public void a(Object obj) {
            this.c.a(obj);
            this.c.f();
            this.l = this.c.l();
            this.m = this.c.m();
            dji.midware.media.e.d(this.b, "display_width=" + this.l + " display_height=" + this.m);
            if (this.a.h.d != null) {
                this.a.h.d.a(this.l, this.m);
            }
            this.k = dji.midware.media.h.d.b();
        }

        public void a() {
            this.c.b();
            a(this.a.h.c);
            this.f = new int[this.a.h.a.length];
            this.g = new SurfaceTexture[this.a.h.a.length];
            this.a.l = new Surface[this.a.h.a.length];
            this.h = new float[this.a.h.a.length][];
            this.i = new dji.midware.media.h.d.a[this.a.h.a.length];
            for (int i = 0; i < this.a.h.a.length; i++) {
                this.f[i] = dji.midware.media.h.d.a(36197);
                this.g[i] = new SurfaceTexture(this.f[i]);
                this.a.l[i] = new Surface(this.g[i]);
                this.h[i] = new float[16];
                this.i[i] = dji.midware.media.h.d.a(this.l, this.m);
                dji.midware.media.e.d(this.b, "filter Src. No." + i + " FB=" + this.i[i].a + " TX=" + this.i[i].b);
            }
            this.j = dji.midware.media.h.d.a(this.l, this.m);
            dji.midware.media.e.d(this.b, "filter target. FB=" + this.j.a + " TX=" + this.j.b);
            this.d.e();
            this.e.e();
        }

        public void b() {
            a(this.n);
            this.c.j();
        }

        public void c() {
            int i;
            int i2 = 0;
            dji.midware.media.e.d(d.b, "1");
            if (this.j != null) {
                dji.midware.media.h.d.c(this.j.a);
                dji.midware.media.h.d.b(this.j.b);
            }
            if (this.i != null) {
                for (i = 0; i < this.i.length; i++) {
                    dji.midware.media.h.d.c(this.i[i].a);
                    dji.midware.media.h.d.b(this.i[i].b);
                }
            }
            if (this.d != null) {
                this.d.f();
            }
            if (this.e != null) {
                this.e.f();
            }
            dji.midware.media.e.d(d.b, "2");
            if (this.a.h.d != null) {
                this.a.h.d.c();
            }
            dji.midware.media.e.d(d.b, "2a");
            if (this.a.l != null) {
                for (Surface release : this.a.l) {
                    release.release();
                }
            }
            dji.midware.media.e.d(d.b, "2b");
            if (this.g != null) {
                dji.midware.media.e.d(d.b, "2c");
                for (i = 0; i < this.g.length; i++) {
                    if (this.g[i] != null) {
                        this.g[i].release();
                    }
                }
                this.g = null;
            }
            if (this.f != null) {
                while (i2 < this.f.length) {
                    if (this.f[i2] != -1) {
                        dji.midware.media.h.d.b(this.f[i2]);
                        this.f[i2] = -1;
                    }
                    i2++;
                }
                this.f = null;
            }
            dji.midware.media.e.d(d.b, "3");
            if (this.c != null) {
                this.c.i();
            }
            dji.midware.media.e.d(d.b, "4");
        }

        public void a(long j, boolean z) {
            if (z) {
                dji.midware.media.e.c(true, this.b, "addLogo");
            }
            for (int i = 0; i < this.a.k.length; i++) {
                if (this.a.k[i].b()) {
                    this.a.k[i].a();
                    this.g[i].updateTexImage();
                    this.g[i].getTransformMatrix(this.h[i]);
                    dji.midware.media.h.d.d(this.i[i].a);
                    float f = 0.0f;
                    try {
                        f = (float) (-this.a.j[i].c(0).getInteger("rotation"));
                    } catch (Exception e) {
                    }
                    dji.midware.media.e.c(true, this.b, "rotation=" + f);
                    this.d.a(this.f[i], 36197, this.h[i], false, f, this.l, this.m);
                }
            }
            dji.midware.media.e.c(true, this.b, "decoder onFrameAvailable after updateTexImage()");
            a(j);
            this.c.j();
        }

        private void a(long j) {
            this.n = j;
            dji.midware.media.h.d.d(this.k);
            dji.midware.media.e.c(true, this.b, "bind frame buffer " + this.k);
            int i = 0;
            long uptimeMillis = SystemClock.uptimeMillis();
            if (this.a.h.d != null) {
                int[] iArr = new int[this.a.k.length];
                int[] iArr2 = new int[this.a.k.length];
                int[] iArr3 = new int[this.a.k.length];
                long[] jArr = new long[this.a.k.length];
                long[] jArr2 = new long[this.a.k.length];
                for (i = 0; i < iArr.length; i++) {
                    iArr[i] = this.a.j[i].a(j);
                    iArr2[i] = this.i[i].b;
                    jArr[i] = this.a.k[i].h();
                    jArr2[i] = this.a.k[i].e();
                    iArr3[i] = this.a.j[i].a(iArr[i], this.a.n);
                }
                boolean z = iArr[0] == this.a.h.a[0].length + -1 && iArr3[0] == 3;
                dji.midware.media.e.c(true, this.b, String.format("apply filter: src_texture=%s seg_index=%s stage=%s decoderGotPts=%s decoderTargetPts=%s allTargetPts=%d isLogo=%b", new Object[]{Arrays.toString(iArr2), Arrays.toString(iArr), Arrays.toString(iArr3), Arrays.toString(jArr), Arrays.toString(jArr2), Long.valueOf(j), Boolean.valueOf(z)}));
                i = this.a.h.d.a(iArr2, iArr, iArr3, j, this.a.i(), z);
            }
            dji.midware.media.e.c(true, this.b, "Num. of filters applied: " + i + " duration(ms)=" + (SystemClock.uptimeMillis() - uptimeMillis));
            dji.midware.media.e.d(this.b, "filter=" + this.a.h.d + " num=" + i);
            if (i == 0) {
                this.e.a(this.i[0].b, 3553, null, false, 0.0f, this.l, this.m);
            }
        }
    }

    public enum f {
        Uninitialized,
        Initialized,
        Paused,
        Playing,
        e,
        Stopped,
        Seeking_at_Pause,
        Error
    }

    public d() {
        dji.midware.media.e.b(b, "create a VideoPreview object");
        this.f.start();
        dji.midware.media.e.d(b, "thread started");
        this.m = new c(this, this.f.getLooper());
        dji.midware.media.e.d(b, "controller created");
    }

    public void a(f fVar) {
        dji.midware.media.e.a(a, "VideoPreview: mediaFileType=" + fVar.a[0][0].q);
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.h = fVar;
            if (fVar.d == null) {
                dji.midware.media.e.b(b, "applyFilterWrapp==null. continue.");
            }
            if (fVar.c == null) {
                throw new RuntimeException("window == null. can't preview on a null window");
            }
            b(this.m, 10, 0, null);
        }
    }

    public void a(g gVar) {
        if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        }
    }

    private void a(Handler handler, int i, long j, Object obj, long j2) {
        a aVar = new a(i, j, obj);
        synchronized (aVar) {
            handler.sendMessageDelayed(this.m.obtainMessage(0, aVar), j2);
            dji.midware.media.e.d(true, b, "UI Signal " + handler.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
            try {
                aVar.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dji.midware.media.e.c(true, b, "Done " + b.a(i));
    }

    private void a(Handler handler, int i, long j, Object obj) {
        handler.sendMessage(this.m.obtainMessage(0, new a(i, j, obj)));
        dji.midware.media.e.d(true, b, "UI Signal " + handler.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
        dji.midware.media.e.c(true, b, "Done " + b.a(i));
    }

    private void b(Handler handler, int i, long j, Object obj) {
        a aVar = new a(i, j, obj);
        synchronized (aVar) {
            handler.sendMessage(this.m.obtainMessage(0, aVar));
            dji.midware.media.e.d(true, b, "UI Signal " + handler.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
            try {
                aVar.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dji.midware.media.e.c(true, b, "Done " + b.a(i));
    }

    private void a(a aVar) {
        synchronized (aVar) {
            aVar.notifyAll();
        }
    }

    private long i() {
        long j = 0;
        for (int i = 0; i < this.j.length; i++) {
            dji.midware.media.e.c(true, a, "track " + i + " getMaxPts()=" + this.j[i].k());
            if (this.j[i].k() > j) {
                j = this.j[i].k();
            }
        }
        return j;
    }

    private void j() {
        int i = 0;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.j = new dji.g.b.a.f[this.h.a.length];
            for (int i2 = 0; i2 < this.h.a.length; i2++) {
                this.j[i2] = new dji.g.b.a.f(i2 + "");
                for (int i3 = 0; i3 < this.h.a[i2].length; i3++) {
                    dji.midware.media.e.d(true, b, String.format(Locale.US, "VideoPlayer input: %s [%d ,(%d->%d), %d] speed=%1.2f", new Object[]{r3.b, Long.valueOf(r3.g), Long.valueOf(r3.e), Long.valueOf(r3.f), Long.valueOf(r3.h), Double.valueOf(this.h.a[i2][i3].c)}));
                    this.j[i2].a(i3, r3);
                }
                this.j[i2].j();
            }
            dji.midware.media.e.d(a, "video init delay=" + (System.currentTimeMillis() - currentTimeMillis));
            this.i = new d(this);
            this.k = new dji.g.b.a.e[this.h.a.length];
            while (i < this.h.a.length) {
                this.k[i] = new dji.g.b.a.e(i + "");
                this.k[i].a(this.j[i]);
                this.k[i].a(this.l[i]);
                i++;
            }
            if (this.h.b != null) {
                try {
                    this.h.b.a();
                } catch (Exception e) {
                    dji.midware.media.e.a(b, e);
                }
            }
        } catch (Exception e2) {
            dji.midware.media.e.a(b, e2);
        }
    }

    public void a(int i, e eVar) throws IOException {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 8, (long) i, eVar);
        }
    }

    public void a(int i) {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 7, (long) i, null);
        }
    }

    public void a(long j) {
        dji.midware.media.e.c(true, b, "call to SeekToAsync. ptsUs=" + j);
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.m.removeMessages(0);
            a aVar = new a(0, j, null);
            this.m.sendMessage(this.m.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(true, b, "Signal to Controller. CMD: " + b.a(0) + " param 1=" + j + " param2=" + null);
        }
    }

    public void b(long j) {
        dji.midware.media.e.c(true, b, "call to SeekToSync. ptsUs=" + j);
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (Math.abs(j - this.n) < 200000) {
            dji.midware.media.e.c(true, b, "Reject seek. target_pts=" + this.n + " request pts=" + j);
        } else {
            b(this.m, 0, j, null);
        }
    }

    public void a() {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 12, 0, null);
        }
    }

    public boolean b() {
        if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return false;
        } else if (this.m == null || this.m.c != f.Playing) {
            return false;
        } else {
            return true;
        }
    }

    public boolean c() {
        if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return false;
        } else if (this.m == null || this.m.c != f.Paused) {
            return false;
        } else {
            return true;
        }
    }

    public boolean d() {
        if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return true;
        } else if (this.m == null || this.m.c != f.e) {
            return false;
        } else {
            return true;
        }
    }

    public void e() {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 2, 0, null);
        }
    }

    public void a(Surface surface) {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 3, 0, surface);
        }
    }

    public void f() {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 1, 0, null);
        }
    }

    public void a(h hVar) {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 11, 0, hVar);
        }
    }

    public void a(int i, double d) {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 9, (long) i, Double.valueOf(d));
        }
    }

    @SuppressLint({"NewApi"})
    public synchronized void g() {
        dji.midware.media.e.e(b, "call to stop a VideoPreview object");
        if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (!this.o) {
            this.o = true;
            b(this.m, 4, 0, null);
            try {
                if (dji.midware.media.d.a() >= 18) {
                    this.f.quitSafely();
                } else {
                    this.f.quit();
                }
                this.f.join();
            } catch (Exception e) {
                dji.midware.media.e.a(b, e);
            }
            dji.midware.media.e.b(b, "Stop a VideoPreview object");
        }
    }

    public void h() {
        if (this.o) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.m.c == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(this.m, 1, 0, null);
        }
    }
}
