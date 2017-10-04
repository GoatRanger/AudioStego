package com.nokia.maps;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Pair;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.streetlevel.StreetLevelCoverage;
import com.here.android.mpa.streetlevel.StreetLevelCoverage.Listener;
import com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class cs extends BaseNativeObject {
    private static final String a = cs.class.getSimpleName();
    private static k<StreetLevelCoverage, cs> b = null;
    private static am<StreetLevelCoverage, cs> c = null;
    private static List<String> d = new CopyOnWriteArrayList();
    private bm e = new bm();
    private HashMap<GeoCoordinateImpl, Pair<TimerTask, AsyncTask<b, Void, ResultCode>>> f = new HashMap();
    private long g = -1;
    private final ApplicationContext$c h = new ApplicationContext$c(this) {
        final /* synthetic */ cs a;

        {
            this.a = r1;
        }

        public void a() {
            throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
        }

        public void b() {
        }
    };

    private class a extends AsyncTask<b, Void, ResultCode> {
        public b a;
        final /* synthetic */ cs b;
        private GeoCoordinate c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((b[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ResultCode) obj);
        }

        public a(cs csVar, b bVar) {
            this.b = csVar;
            this.a = bVar;
        }

        protected ResultCode a(b... bVarArr) {
            PanoramaModelImpl panoramaModelImpl = new PanoramaModelImpl(MapsEngine.e());
            PanoramaImpl a = panoramaModelImpl.a(GeoCoordinateImpl.create(this.a.a), this.a.c.intValue());
            if (a == null) {
                return ResultCode.HAS_NO_COVERAGE;
            }
            a.c();
            while (panoramaModelImpl.needPanoramaData()) {
                try {
                    Thread.sleep(50);
                } catch (Throwable e) {
                    bj.c(cs.a, "InterruptedException \n%s", new Object[]{bj.a(e)});
                }
            }
            if (!a.isPositionAvailable()) {
                return ResultCode.HAS_NO_COVERAGE;
            }
            this.c = GeoCoordinateImpl.create(a.getPosition());
            return ResultCode.HAS_COVERAGE;
        }

        protected void a(ResultCode resultCode) {
            int i = -1;
            if (!isCancelled() || this.a == null) {
                if (resultCode == ResultCode.HAS_COVERAGE) {
                    i = (int) Math.abs(this.a.a.a(this.c));
                }
                this.b.a(this.a, i, resultCode);
                return;
            }
            this.b.a(this.a, -1, ResultCode.CANCELLED);
            this.a = null;
        }

        protected void onCancelled() {
            this.b.a(this.a, -1, ResultCode.CANCELLED);
            this.a = null;
        }
    }

    private static class b {
        public GeoCoordinateImpl a;
        public Integer b;
        public Integer c;
        public String d;
        public String e;
        public Boolean f;
        public Listener g;

        private b() {
        }
    }

    private class c extends AsyncTask<b, Void, ResultCode> {
        public b a;
        final /* synthetic */ cs b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((b[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ResultCode) obj);
        }

        public c(cs csVar, b bVar) {
            this.b = csVar;
            this.a = bVar;
        }

        protected ResultCode a(b... bVarArr) {
            return cs.b(this.a.d, this.a.e);
        }

        protected void a(ResultCode resultCode) {
            if (isCancelled() && this.a != null) {
                this.b.a(this.a, -1, ResultCode.CANCELLED);
            } else if (resultCode == ResultCode.UNKNOWN_COVERAGE) {
                TimerTask timerTask;
                Pair pair = (Pair) this.b.f.get(this.a.a);
                if (pair != null) {
                    timerTask = (TimerTask) pair.first;
                } else {
                    timerTask = null;
                }
                this.b.a(this.a, true, timerTask);
            } else {
                this.b.a(this.a, -1, resultCode);
            }
            this.a = null;
        }

        protected void onCancelled() {
            this.b.a(this.a, -1, ResultCode.CANCELLED);
            this.a = null;
        }
    }

    static {
        ce.a(StreetLevelCoverage.class);
    }

    public static void a(k<StreetLevelCoverage, cs> kVar, am<StreetLevelCoverage, cs> amVar) {
        b = kVar;
        c = amVar;
    }

    public cs() {
        super(true);
        ApplicationContext.b().check(22, this.h);
    }

    public void a(long j) {
        boolean z = j > 0 || j == -1;
        dy.a(z, "Timeout limit arguement is invalid");
        this.g = j;
    }

    public long a() {
        return this.g;
    }

    public boolean a(final GeoCoordinate geoCoordinate, int i, boolean z, Listener listener) {
        boolean z2 = false;
        dy.a((Object) geoCoordinate, "geoCoordinate arguement is null");
        dy.a(geoCoordinate.isValid(), "geoCoordinate arguement is invalid");
        dy.a(i > 0, "radius arguement is not a positive value");
        dy.a((Object) listener, "listener arguement is null");
        if (this.f.containsKey(GeoCoordinateImpl.get(geoCoordinate))) {
            return false;
        }
        TimerTask anonymousClass2;
        GeoCoordinateImpl geoCoordinateImpl = GeoCoordinateImpl.get(geoCoordinate);
        c();
        b bVar = new b();
        bVar.a = geoCoordinateImpl;
        bVar.c = Integer.valueOf(i);
        bVar.b = Integer.valueOf(13);
        bVar.f = Boolean.valueOf(z);
        bVar.g = listener;
        if (a() > 0) {
            anonymousClass2 = new TimerTask(this) {
                final /* synthetic */ cs b;

                public void run() {
                    this.b.a(geoCoordinate);
                }
            };
        } else {
            anonymousClass2 = null;
        }
        if (bVar.f.booleanValue()) {
            a(bVar, anonymousClass2);
        } else {
            z2 = a(bVar, false, anonymousClass2);
        }
        if (!(z2 || anonymousClass2 == null)) {
            new Timer().schedule(anonymousClass2, a());
        }
        return true;
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "geoCoordinate arguement is null");
        dy.a(geoCoordinate.isValid(), "geoCoordinate arguement is invalid");
        Pair pair = (Pair) this.f.remove(GeoCoordinateImpl.get(geoCoordinate));
        if (pair != null) {
            if (pair.first != null) {
                ((TimerTask) pair.first).cancel();
            }
            if (pair.second != null) {
                ((AsyncTask) pair.second).cancel(true);
            }
        }
    }

    private void c() {
        String i = MapSettings.i();
        if (!this.e.b(i).booleanValue()) {
            this.e.a(i);
        }
    }

    private void a(b bVar, int i, ResultCode resultCode) {
        bj.e(a, "notificatedCoverageFound", new Object[0]);
        Pair pair = (Pair) this.f.remove(bVar.a);
        if (!(pair == null || pair.first == null)) {
            ((TimerTask) pair.first).cancel();
        }
        if (bVar.g != null) {
            bVar.g.onCoverageCheckCompleted(GeoCoordinateImpl.create(bVar.a), i, resultCode);
            bVar.g = null;
        }
    }

    private boolean a(b bVar, boolean z, TimerTask timerTask) {
        ResultCode resultCode = ResultCode.UNKNOWN_COVERAGE;
        int intValue = 1 << bVar.b.intValue();
        double b = ((((bVar.a.b() + 180.0d) * 3.141592653589793d) / 180.0d) * ((double) intValue)) / 6.283185307179586d;
        double abs = Math.abs(((((Math.log(Math.tan(((bVar.a.a() + 90.0d) * 3.141592653589793d) / 360.0d)) + 3.141592653589793d) * ((double) intValue)) / 6.283185307179586d) + 1.0d) - ((double) intValue)) + 1.0d;
        int i = (int) b;
        int i2 = (int) abs;
        String a = a(i, i2, bVar.b.intValue(), z);
        if (this.e.b(a).booleanValue()) {
            Bitmap decodeFile = BitmapFactory.decodeFile(a);
            intValue = -1;
            if (decodeFile != null) {
                int round = Math.round(((float) (-1 - decodeFile.getPixel((int) Math.floor((b - ((double) i)) * 256.0d), (int) Math.floor((abs - ((double) i2)) * 256.0d)))) / 10.0f);
                int i3 = round;
                resultCode = round < bVar.c.intValue() ? ResultCode.HAS_COVERAGE : ResultCode.HAS_NO_COVERAGE;
                intValue = i3;
            }
            if (z) {
                String a2 = a(i, i2, bVar.b.intValue(), false);
                String a3 = a(i, i2, bVar.b.intValue(), true);
                if (this.e.b(a2).booleanValue()) {
                    this.e.c(a3);
                } else {
                    this.e.a(a3, a2);
                }
            }
            if (resultCode != ResultCode.HAS_COVERAGE) {
                intValue = -1;
            }
            a(bVar, intValue, resultCode);
            return true;
        }
        a(i, i2, bVar, timerTask);
        return false;
    }

    @SuppressLint({"NewApi"})
    private void a(b bVar, TimerTask timerTask) {
        AsyncTask aVar = new a(this, bVar);
        if (VERSION.SDK_INT >= 11) {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new b[]{bVar});
        } else {
            aVar.execute(new b[]{bVar});
        }
        this.f.put(bVar.a, Pair.create(timerTask, aVar));
    }

    @SuppressLint({"NewApi"})
    private void a(int i, int i2, b bVar, TimerTask timerTask) {
        bVar.d = a(i, i2, bVar.b.intValue());
        bVar.e = a(i, i2, bVar.b.intValue(), true);
        bj.e("TAG", "downloadTile in progress:" + bVar.e + ":" + bVar.d, new Object[0]);
        AsyncTask cVar = new c(this, bVar);
        if (VERSION.SDK_INT >= 11) {
            cVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new b[]{bVar});
        } else {
            cVar.execute(new b[]{bVar});
        }
        this.f.put(bVar.a, Pair.create(timerTask, cVar));
    }

    private String a(int i, int i2, int i3, boolean z) {
        return String.format(z ? "%s/tmp_%s_%d_%d_%d.png" : "%s/%s_%d_%d_%d.png", new Object[]{MapSettings.i(), getClass().getSimpleName(), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
    }

    private String a(int i, int i2, int i3) {
        String format;
        Exception e;
        try {
            format = String.format("%s/%02d/%03d/%03d/%02d/%02d/cov_z%d_c%d_r%d.png", new Object[]{MapsEngine.n(), Integer.valueOf(i3), Integer.valueOf((i / 1000) % 1000), Integer.valueOf((i2 / 1000) % 1000), Integer.valueOf((i / 10) % 100), Integer.valueOf((i2 / 10) % 100), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
            try {
                ApplicationContext b = ApplicationContext.b();
                if (b != null) {
                    String e2 = b.e();
                    if (e2 != null) {
                        format = format + e2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bj.c(a, "%s", new Object[]{e.toString()});
                bj.e(a, "url=" + format, new Object[0]);
                return format;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            format = null;
            e = exception;
            bj.c(a, "%s", new Object[]{e.toString()});
            bj.e(a, "url=" + format, new Object[0]);
            return format;
        }
        bj.e(a, "url=" + format, new Object[0]);
        return format;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode b(java.lang.String r8, java.lang.String r9) {
        /*
        r4 = 0;
        r2 = d;
        monitor-enter(r2);
        r1 = d;	 Catch:{ all -> 0x007e }
        r3 = r1.iterator();	 Catch:{ all -> 0x007e }
    L_0x000a:
        r1 = r3.hasNext();	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x0020;
    L_0x0010:
        r1 = r3.next();	 Catch:{ all -> 0x007e }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x007e }
        r1 = r1.equals(r8);	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x000a;
    L_0x001c:
        r1 = com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode.HAS_NO_COVERAGE;	 Catch:{ all -> 0x007e }
        monitor-exit(r2);	 Catch:{ all -> 0x007e }
    L_0x001f:
        return r1;
    L_0x0020:
        monitor-exit(r2);	 Catch:{ all -> 0x007e }
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x0125, all -> 0x011f }
        r1.<init>(r8);	 Catch:{ Exception -> 0x0125, all -> 0x011f }
        r2 = r1.openConnection();	 Catch:{ Exception -> 0x0125, all -> 0x011f }
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r1 = r0;
        r1 = r1.getResponseCode();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r3) goto L_0x00b6;
    L_0x0036:
        r3 = a;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r4.<init>();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r5 = "Error ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r4 = " while retrieving bitmap from ";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        com.nokia.maps.bj.c(r3, r1, r4);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r3 = d;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        monitor-enter(r3);	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        r1 = d;	 Catch:{ all -> 0x0081 }
        r1.add(r8);	 Catch:{ all -> 0x0081 }
        r1 = d;	 Catch:{ all -> 0x0081 }
        r1 = r1.size();	 Catch:{ all -> 0x0081 }
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r1 <= r4) goto L_0x0073;
    L_0x006d:
        r1 = d;	 Catch:{ all -> 0x0081 }
        r4 = 0;
        r1.remove(r4);	 Catch:{ all -> 0x0081 }
    L_0x0073:
        monitor-exit(r3);	 Catch:{ all -> 0x0081 }
        r1 = com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode.HAS_NO_COVERAGE;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
        if (r2 == 0) goto L_0x001f;
    L_0x0078:
        r2 = (java.net.HttpURLConnection) r2;
        r2.disconnect();
        goto L_0x001f;
    L_0x007e:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x007e }
        throw r1;
    L_0x0081:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0081 }
        throw r1;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x0084:
        r1 = move-exception;
        r7 = r1;
        r1 = r2;
        r2 = r7;
    L_0x0088:
        r3 = a;	 Catch:{ all -> 0x0123 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0123 }
        r4.<init>();	 Catch:{ all -> 0x0123 }
        r5 = "Error while retrieving bitmap from ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0123 }
        r4 = r4.append(r8);	 Catch:{ all -> 0x0123 }
        r4 = r4.toString();	 Catch:{ all -> 0x0123 }
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0123 }
        r6 = 0;
        r2 = r2.toString();	 Catch:{ all -> 0x0123 }
        r5[r6] = r2;	 Catch:{ all -> 0x0123 }
        com.nokia.maps.bj.c(r3, r4, r5);	 Catch:{ all -> 0x0123 }
        r2 = com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode.NETWORK_ERROR;	 Catch:{ all -> 0x0123 }
        if (r1 == 0) goto L_0x00b3;
    L_0x00ae:
        r1 = (java.net.HttpURLConnection) r1;
        r1.disconnect();
    L_0x00b3:
        r1 = r2;
        goto L_0x001f;
    L_0x00b6:
        r5 = r2.getInputStream();	 Catch:{ all -> 0x012a }
        r1 = new java.io.File;	 Catch:{ all -> 0x012d }
        r1.<init>(r9);	 Catch:{ all -> 0x012d }
        r3 = new java.io.FileOutputStream;	 Catch:{ all -> 0x012d }
        r3.<init>(r1);	 Catch:{ all -> 0x012d }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ all -> 0x00d3 }
    L_0x00c8:
        r4 = r5.read(r1);	 Catch:{ all -> 0x00d3 }
        if (r4 <= 0) goto L_0x00ec;
    L_0x00ce:
        r6 = 0;
        r3.write(r1, r6, r4);	 Catch:{ all -> 0x00d3 }
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        r4 = r5;
    L_0x00d5:
        if (r3 == 0) goto L_0x00da;
    L_0x00d7:
        r3.close();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x00da:
        if (r4 == 0) goto L_0x00df;
    L_0x00dc:
        r4.close();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x00df:
        throw r1;	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x00e0:
        r1 = move-exception;
        r7 = r1;
        r1 = r2;
        r2 = r7;
    L_0x00e4:
        if (r1 == 0) goto L_0x00eb;
    L_0x00e6:
        r1 = (java.net.HttpURLConnection) r1;
        r1.disconnect();
    L_0x00eb:
        throw r2;
    L_0x00ec:
        r3.flush();	 Catch:{ all -> 0x00d3 }
        r1 = a;	 Catch:{ all -> 0x00d3 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d3 }
        r4.<init>();	 Catch:{ all -> 0x00d3 }
        r6 = "Success while retrieving bitmap from :";
        r4 = r4.append(r6);	 Catch:{ all -> 0x00d3 }
        r4 = r4.append(r8);	 Catch:{ all -> 0x00d3 }
        r4 = r4.toString();	 Catch:{ all -> 0x00d3 }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x00d3 }
        com.nokia.maps.bj.e(r1, r4, r6);	 Catch:{ all -> 0x00d3 }
        r1 = com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode.UNKNOWN_COVERAGE;	 Catch:{ all -> 0x00d3 }
        if (r3 == 0) goto L_0x0111;
    L_0x010e:
        r3.close();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x0111:
        if (r5 == 0) goto L_0x0116;
    L_0x0113:
        r5.close();	 Catch:{ Exception -> 0x0084, all -> 0x00e0 }
    L_0x0116:
        if (r2 == 0) goto L_0x001f;
    L_0x0118:
        r2 = (java.net.HttpURLConnection) r2;
        r2.disconnect();
        goto L_0x001f;
    L_0x011f:
        r1 = move-exception;
        r2 = r1;
        r1 = r4;
        goto L_0x00e4;
    L_0x0123:
        r2 = move-exception;
        goto L_0x00e4;
    L_0x0125:
        r1 = move-exception;
        r2 = r1;
        r1 = r4;
        goto L_0x0088;
    L_0x012a:
        r1 = move-exception;
        r3 = r4;
        goto L_0x00d5;
    L_0x012d:
        r1 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x00d5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.cs.b(java.lang.String, java.lang.String):com.here.android.mpa.streetlevel.StreetLevelCoverage$ResultCode");
    }
}
