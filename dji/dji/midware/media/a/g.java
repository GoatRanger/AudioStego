package dji.midware.media.a;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraControlTransCode;
import dji.midware.data.model.P3.DataCameraControlTransCode.ControlType;
import dji.midware.data.model.P3.DataCameraControlTransCode.DJIFragmentModel;
import dji.midware.data.model.P3.DataCameraControlTransCode.ToFps;
import dji.midware.data.model.P3.DataCameraControlTransCode.ToResolution;
import dji.midware.data.model.P3.DataCameraGetFileParams;
import dji.midware.data.model.P3.DataCameraGetFileParams.ParamsType;
import dji.midware.data.model.P3.DataCameraGetMode;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraSetMode;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class g {
    private static g A = null;
    private static final String r = "HDConversion";
    private static final boolean s = true;
    private static final long t = 5000;
    String a;
    b b;
    Vector<dji.midware.media.e.f> c;
    DataCameraGetFileParams d = DataCameraGetFileParams.getInstance();
    DataCameraControlTransCode e = DataCameraControlTransCode.getInstance();
    d f;
    e g = new e();
    a h;
    b i = new b();
    MODE j;
    int k;
    int l;
    int m;
    int n;
    int o;
    long p;
    int q;
    private HandlerThread u = new HandlerThread("HDConversionThread");
    private c v;
    private h w = h.Uninitialized;
    private g[] x;
    private long[] y;
    private long[] z;

    public interface b {
        void a();

        void a(int i);

        void a(Exception exception);

        void b();
    }

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

    private class c extends Handler {
        final /* synthetic */ g a;
        private a b = null;
        private boolean c = true;

        public c(g gVar, Looper looper) {
            this.a = gVar;
            super(looper);
        }

        private void a(int i) {
            a aVar = new a(i, 0, null);
            this.a.v.sendMessage(this.a.v.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(true, g.r, "Signal to Controller. CMD: " + e.a(i));
        }

        private void a(int i, long j, Object obj) {
            a aVar = new a(i, j, obj);
            this.a.v.sendMessage(this.a.v.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(true, g.r, "Signal to Controller. CMD: " + e.a(i) + " param 1=" + j + " param2=" + obj);
        }

        private void a(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.a.v.sendMessageDelayed(this.a.v.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(true, g.r, "Signal to Controller. CMD: " + e.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        private void b(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.a.v.sendMessageAtTime(this.a.v.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(true, g.r, "Signal to Controller. CMD: " + e.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r5) {
            /*
            r4 = this;
            r0 = r5.obj;
            r0 = (dji.midware.media.a.g.a) r0;
            r0 = (dji.midware.media.a.g.a) r0;
            r4.b = r0;
            r0 = 1;
            r1 = "HDConversion";
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0092 }
            r2.<init>();	 Catch:{ Exception -> 0x0092 }
            r3 = "Start to process CMD ";
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0092 }
            r3 = r4.b;	 Catch:{ Exception -> 0x0092 }
            r3 = r3.a;	 Catch:{ Exception -> 0x0092 }
            r3 = dji.midware.media.a.g.e.a(r3);	 Catch:{ Exception -> 0x0092 }
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0092 }
            r3 = " at state ";
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0092 }
            r3 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r3 = r3.w;	 Catch:{ Exception -> 0x0092 }
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0092 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0092 }
            dji.midware.media.e.c(r0, r1, r2);	 Catch:{ Exception -> 0x0092 }
            r0 = 1;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            r0 = r4.b;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.a();	 Catch:{ Exception -> 0x0092 }
            switch(r0) {
                case 0: goto L_0x0082;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00ce;
                case 3: goto L_0x00e4;
                case 4: goto L_0x0104;
                case 5: goto L_0x011a;
                case 6: goto L_0x0130;
                case 7: goto L_0x015c;
                case 8: goto L_0x0188;
                case 9: goto L_0x0146;
                case 10: goto L_0x0172;
                default: goto L_0x0045;
            };	 Catch:{ Exception -> 0x0092 }
        L_0x0045:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
        L_0x0048:
            r0 = r4.c;	 Catch:{ Exception -> 0x0092 }
            if (r0 != 0) goto L_0x007c;
        L_0x004c:
            r0 = "HDConversion";
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0092 }
            r1.<init>();	 Catch:{ Exception -> 0x0092 }
            r2 = "Ignore CMD ";
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0092 }
            r2 = r4.b;	 Catch:{ Exception -> 0x0092 }
            r2 = r2.a;	 Catch:{ Exception -> 0x0092 }
            r2 = dji.midware.media.a.g.e.a(r2);	 Catch:{ Exception -> 0x0092 }
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0092 }
            r2 = " at state ";
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0092 }
            r2 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r2 = r2.w;	 Catch:{ Exception -> 0x0092 }
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0092 }
            r1 = r1.toString();	 Catch:{ Exception -> 0x0092 }
            dji.midware.media.e.b(r0, r1);	 Catch:{ Exception -> 0x0092 }
        L_0x007c:
            r0 = r4.b;
            r4.a(r0);
        L_0x0081:
            return;
        L_0x0082:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.Uninitialized;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x00ae;
        L_0x008c:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.j();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0092:
            r0 = move-exception;
            r1 = "HDConversion";
            dji.midware.media.e.a(r1, r0);	 Catch:{ all -> 0x00b2 }
            r1 = r4.a;	 Catch:{ all -> 0x00b2 }
            r1 = r1.b;	 Catch:{ all -> 0x00b2 }
            if (r1 == 0) goto L_0x00a5;
        L_0x009e:
            r1 = r4.a;	 Catch:{ all -> 0x00b2 }
            r1 = r1.b;	 Catch:{ all -> 0x00b2 }
            r1.a(r0);	 Catch:{ all -> 0x00b2 }
        L_0x00a5:
            r4.a();	 Catch:{ all -> 0x00b2 }
            r0 = r4.b;
            r4.a(r0);
            goto L_0x0081;
        L_0x00ae:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00b2:
            r0 = move-exception;
            r1 = r4.b;
            r4.a(r1);
            throw r0;
        L_0x00b9:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.Started;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x00c9;
        L_0x00c3:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.h();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00c9:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00ce:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentGetOriginalMode;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x00df;
        L_0x00d8:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.g();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00df:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00e4:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentGetOriginalMode;	 Catch:{ Exception -> 0x0092 }
            if (r0 == r1) goto L_0x00f8;
        L_0x00ee:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentSwithToTranscode;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x00ff;
        L_0x00f8:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.f();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x00ff:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0104:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentGetFileParams;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x0115;
        L_0x010e:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.e();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0115:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x011a:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentRequestTranscoding;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x012b;
        L_0x0124:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.b();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x012b:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0130:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentQueryTranscodeStatus;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x0141;
        L_0x013a:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.c();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0141:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0146:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.WaitForDownloading;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x0157;
        L_0x0150:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.k();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0157:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x015c:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.AllFileDownloaded;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x016d;
        L_0x0166:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.a();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x016d:
            r0 = 0;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0172:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0 = r0.w;	 Catch:{ Exception -> 0x0092 }
            r1 = dji.midware.media.a.g.h.SentSwitchBack;	 Catch:{ Exception -> 0x0092 }
            if (r0 != r1) goto L_0x0183;
        L_0x017c:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r0.i();	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0183:
            r0 = 1;
            r4.c = r0;	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
        L_0x0188:
            r0 = r4.a;	 Catch:{ Exception -> 0x0092 }
            r1 = r4.b;	 Catch:{ Exception -> 0x0092 }
            r1 = r1.c;	 Catch:{ Exception -> 0x0092 }
            r0.a(r1);	 Catch:{ Exception -> 0x0092 }
            goto L_0x0048;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.a.g.c.handleMessage(android.os.Message):void");
        }

        @SuppressLint({"NewApi"})
        public void a() {
            removeCallbacksAndMessages(null);
            if (dji.midware.media.d.a() >= 18) {
                this.a.u.quitSafely();
            } else {
                this.a.u.quit();
            }
        }

        private void a(a aVar) {
            synchronized (aVar) {
                aVar.notifyAll();
            }
        }
    }

    private enum d {
        TimeOut,
        Disconnected,
        INVALID_PARAM_OR_ERR_UNSPECIFIED,
        DownloadTimeOut
    }

    public static class e {
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

        public static String a(int i) {
            switch (i) {
                case 0:
                    return "Start";
                case 1:
                    return "GetOriginalMode";
                case 2:
                    return "SwitchToTranscoding";
                case 3:
                    return "GetFileParams";
                case 4:
                    return "RequestTranscoding";
                case 5:
                    return "QueryTranscodingStatus";
                case 6:
                    return "AnalyzeTranscodeStatus";
                case 7:
                    return "RestoreToOriginalMode";
                case 8:
                    return "OnError";
                case 9:
                    return "WaitForDownloading";
                case 10:
                    return "OnSuccess";
                default:
                    return "Unrecognised";
            }
        }
    }

    public static class f {
        private final int a;
        private final int b;

        public f(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    private enum g {
        PendingTranscodeResult,
        ToDownload,
        HasDownloaded,
        Failure
    }

    public enum h {
        Uninitialized,
        Started,
        SentGetOriginalMode,
        SentSwithToTranscode,
        SentGetFileParams,
        SentRequestTranscoding,
        SentQueryTranscodeStatus,
        WaitForDownloading,
        AllFileDownloaded,
        SentSwitchBack,
        Success,
        Error
    }

    public g() {
        this.u.start();
        dji.midware.media.e.d(r, "thread started");
        this.v = new c(this, this.u.getLooper());
    }

    public static g getInstance() {
        if (A == null) {
            A = new g();
        }
        return A;
    }

    public void a(Vector<dji.midware.media.e.f> vector, String str, b bVar) throws Exception {
        if (vector != null && vector.size() != 0) {
            this.a = str;
            this.b = bVar;
            this.c = vector;
            this.v.a(0);
            try {
                this.u.join();
            } catch (Exception e) {
                dji.midware.media.e.a(r, e);
            }
            dji.midware.media.e.e(r, "getHDSegments() done");
        }
    }

    private void a() {
        dji.midware.media.e.d(r, "try to switch to mode " + this.j);
        DataCameraSetMode.getInstance().a(this.j).start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.midware.media.e.d(g.r, "have successfully switched to MODE: " + this.a.j);
                this.a.v.a(10);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.v.a(8, 0, aVar);
            }
        });
        this.w = h.SentSwitchBack;
    }

    private void b() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.g.a(this.k);
            this.g.a(new dji.logic.album.a.d.a<d>(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a() {
                    dji.midware.media.e.d(g.r, "clipListLoader onStart");
                }

                public void a(long j, long j2, long j3) {
                    dji.midware.media.e.d(g.r, "clipListLoader onRateUpdate: total=" + j + " current=" + j2 + " persize=" + j3);
                }

                public void a(long j, long j2) {
                    dji.midware.media.e.d(g.r, "clipListLoader onProgress total=" + j + " current=" + j2);
                }

                public void a(d dVar) {
                    dji.midware.media.e.d(g.r, "clipListLoader onSuccess");
                    this.a.f = dVar;
                    this.a.v.a(6);
                }

                public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                    dji.midware.media.e.d(g.r, "clipListLoader: onFailure");
                    this.a.v.a(8, 0, dJIAlbumPullErrorType);
                }
            });
            this.g.b();
            this.w = h.SentQueryTranscodeStatus;
            return;
        }
        this.v.a(8, 0, d.Disconnected);
    }

    private void c() {
        int i = 0;
        if (this.f != null) {
            dji.midware.media.e.a(this.f.toString());
        }
        for (int i2 = 0; i2 < this.f.d.size(); i2++) {
            if (this.x[i2] == g.PendingTranscodeResult) {
                c cVar = (c) this.f.d.get(i2);
                switch (cVar.c) {
                    case SUECCESS:
                    case ERR_INCOMPLETE:
                        this.x[i2] = g.ToDownload;
                        this.o--;
                        break;
                    case UNDO:
                        break;
                    case INVALID_PARAM:
                        this.x[i2] = g.Failure;
                        this.o--;
                        this.m++;
                        break;
                    default:
                        this.v.a(8, 0, d.INVALID_PARAM_OR_ERR_UNSPECIFIED);
                        dji.midware.media.e.f fVar = (dji.midware.media.e.f) this.c.get(i2);
                        dji.midware.media.e.a(r, "Clip conversion error: Source File ID=" + fVar.A + " Clip No. " + i2 + " Time:" + fVar.B() + "-" + fVar.A() + " Error type:" + cVar.c);
                        return;
                }
            }
        }
        l();
        if (this.o > 0) {
            this.w = h.SentRequestTranscoding;
            this.v.a(5, 200, 0, null);
        } else if (d()) {
            this.p = SystemClock.uptimeMillis();
            this.w = h.WaitForDownloading;
            this.v.a(9);
        } else {
            while (i < this.c.size()) {
                dji.midware.media.e.d(r, "segmentStatus " + i + " =" + this.x[i]);
                i++;
            }
            dji.midware.media.e.d(r, "numFinishedDownload=" + this.n);
            dji.midware.media.e.d(r, "numFinishedError=" + this.m);
            this.w = h.AllFileDownloaded;
            this.v.a(7);
        }
    }

    private boolean d() {
        int i = 0;
        while (i < this.c.size()) {
            if (this.x[i] == g.ToDownload) {
                break;
            }
            i++;
        }
        i = -1;
        if (i == -1) {
            return false;
        }
        a(i);
        return true;
    }

    private void e() {
        int i = 0;
        int y = ((dji.midware.media.e.f) this.c.get(0)).y();
        int x = ((dji.midware.media.e.f) this.c.get(0)).x();
        int w = (int) ((dji.midware.media.e.f) this.c.get(0)).w();
        dji.midware.media.e.d(r, "foldId=" + y + " fileId=" + x + " uuid=" + w);
        this.e.a(ControlType.START);
        this.e.a(y);
        this.e.b(x);
        this.e.c(w);
        this.e.d(this.k);
        this.e.a(ToResolution.R1280_720_16_9);
        this.e.a(ToFps.fps30);
        this.e.f(60);
        this.e.e(0);
        ArrayList arrayList = new ArrayList();
        while (i < this.c.size()) {
            DataCameraControlTransCode dataCameraControlTransCode = this.e;
            dataCameraControlTransCode.getClass();
            DJIFragmentModel dJIFragmentModel = new DJIFragmentModel(dataCameraControlTransCode);
            dJIFragmentModel.a = ((dji.midware.media.e.f) this.c.get(i)).B();
            dJIFragmentModel.b = ((dji.midware.media.e.f) this.c.get(i)).A();
            dji.midware.media.e.d(r, "making request to convert segment from " + dJIFragmentModel.a + " to " + dJIFragmentModel.b);
            arrayList.add(dJIFragmentModel);
            i++;
        }
        this.e.a(arrayList);
        this.e.start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.v.a(5);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.v.a(8, 0, aVar);
            }
        });
        this.w = h.SentRequestTranscoding;
        dji.midware.media.e.d(r, "have sent RequestTranscoding. start to query state");
    }

    private void f() {
        this.d.setType(ParamsType.CLIP);
        this.d.start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.midware.media.e.a("Returned folderID=" + this.a.d.getFolderId());
                this.a.k = this.a.d.getFolderId();
                this.a.v.a(4);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.v.a(8, 0, aVar);
            }
        });
        this.w = h.SentGetFileParams;
    }

    private void g() {
        DataCameraSetMode.getInstance().a(MODE.TRANSCODE).start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.midware.media.e.a("have switched to MODE: " + MODE.TRANSCODE);
                this.a.v.a(3);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.v.a(8, 0, aVar);
            }
        });
        this.w = h.SentSwithToTranscode;
    }

    private void h() {
        this.j = null;
        DataCameraGetMode.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.j = DataCameraGetMode.getInstance().getMode();
                if (this.a.j == null) {
                    dji.midware.media.e.a("Can't get camera's original mode");
                } else {
                    dji.midware.media.e.a("Original mode is " + this.a.j.toString());
                }
                if (this.a.j == MODE.TRANSCODE) {
                    this.a.j = MODE.TAKEPHOTO;
                    this.a.v.a(3);
                    return;
                }
                this.a.v.a(2);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.v.a(8, 0, aVar);
            }
        });
        dji.midware.media.e.e(r, "switch to SentGetOriginalMode");
        this.w = h.SentGetOriginalMode;
    }

    private void a(Object obj) {
        if (obj instanceof Exception) {
            this.b.a((Exception) obj);
        } else {
            this.b.a(new Exception("State=" + this.w.toString() + " Error=" + String.valueOf(obj.toString())));
        }
        if (this.j != null) {
            dji.midware.media.e.d(r, "try to switch to mode " + this.j);
            DataCameraSetMode.getInstance().a(this.j).start(new dji.midware.e.d(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    dji.midware.media.e.d(g.r, "have successfully switched to MODE: " + this.a.j);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    dji.midware.media.e.d(g.r, "failed to switch to MODE: " + this.a.j + " ccode=" + aVar);
                }
            });
        }
        this.v.a();
    }

    private void i() {
        if (this.b != null) {
            this.b.a(100);
            this.b.a();
        }
        DJILogHelper.getInstance().LOGD("", "done", false, true);
        this.v.a();
    }

    private void j() {
        File file = new File(this.a);
        if (file.exists() || file.mkdirs()) {
            if (this.b != null) {
                this.b.b();
            }
            this.x = new g[this.c.size()];
            this.y = new long[this.c.size()];
            this.z = new long[this.c.size()];
            for (int i = 0; i < this.c.size(); i++) {
                this.x[i] = g.PendingTranscodeResult;
                this.y[i] = 0;
                this.z[i] = 0;
            }
            this.o = this.c.size();
            this.m = 0;
            this.n = 0;
            this.q = -1;
            this.w = h.Started;
            this.v.a(1);
            return;
        }
        Exception exception = new Exception("can't create the directory for saving target HD segments");
        dji.midware.media.e.a(exception);
        if (this.b != null) {
            this.b.a(exception);
        }
        this.v.a(8);
    }

    private void k() {
        if (this.w == h.WaitForDownloading) {
            l();
            if (this.q == -1) {
                d();
            }
            if (this.n + this.m == this.c.size()) {
                for (int i = 0; i < this.c.size(); i++) {
                    dji.midware.media.e.d(r, "segmentStatus " + i + " =" + this.x[i]);
                }
                dji.midware.media.e.d(r, "numFinishedDownload=" + this.n);
                dji.midware.media.e.d(r, "numFinishedError=" + this.m);
                this.w = h.AllFileDownloaded;
                this.v.a(7);
            } else if (SystemClock.uptimeMillis() - this.p > 5000) {
                this.v.a(8, 0, d.DownloadTimeOut);
            } else {
                this.v.a(9, 200, 0, null);
            }
        }
    }

    private void l() {
        double d = 0.0d;
        for (int i = 0; i < this.c.size(); i++) {
            d += this.y[i] == 0 ? 0.0d : ((double) this.z[i]) / ((double) this.y[i]);
        }
        double size = d / ((double) this.c.size());
        if (this.b != null) {
            this.b.a((int) (size * 100.0d));
        }
        dji.midware.media.e.d(r, "total progress=" + (size * 100.0d) + "%");
    }

    private void a(final int i) {
        this.q = i;
        c cVar = (c) this.f.d.get(i);
        final dji.midware.media.e.f fVar = (dji.midware.media.e.f) this.c.get(i);
        cVar.d = this.k;
        cVar.f = fVar.w();
        cVar.g = fVar.B();
        cVar.h = fVar.A();
        cVar.a = dji.logic.album.a.b.f.MOV;
        this.i.a(cVar, new dji.logic.album.a.d.a<a>(this) {
            final /* synthetic */ g c;

            public void a(a aVar) {
                this.c.q = -1;
                dji.midware.media.e.d(g.r, "clipFileLoader OnSuccess: path=" + aVar.c);
                this.c.h = aVar;
                try {
                    File file = new File(this.c.h.c);
                    File file2 = new File(this.c.a + fVar.u() + ".mov");
                    if (!file2.exists()) {
                        file.renameTo(file2);
                    } else if (file2.delete()) {
                        file.renameTo(file2);
                    }
                    fVar.a(Boolean.valueOf(true));
                    this.c.x[i] = g.HasDownloaded;
                    g gVar = this.c;
                    gVar.n++;
                } catch (Exception e) {
                    dji.midware.media.e.a(e);
                }
            }

            public void a() {
                dji.midware.media.e.d(g.r, "clipFileLoader OnStart");
            }

            public void a(long j, long j2, long j3) {
                dji.midware.media.e.d(g.r, "clipFileLoader onRateUpdate: total=" + j + " ; current=" + j2 + " ; persize" + j3);
            }

            public void a(long j, long j2) {
                dji.midware.media.e.d(g.r, "file index=" + i + " progress: total=" + j + " ; current=" + j2);
                this.c.p = SystemClock.uptimeMillis();
                this.c.z[i] = j2;
                this.c.y[i] = j;
            }

            public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                this.c.x[i] = g.Failure;
                g gVar = this.c;
                gVar.m++;
                this.c.q = -1;
                dji.midware.media.e.b(g.r, "clipFileLoader onFailure segmentIndex=" + i);
            }
        });
        this.i.b();
        dji.midware.media.e.d(r, "clipFileLoader start(): clipNo=" + cVar.e);
    }
}
