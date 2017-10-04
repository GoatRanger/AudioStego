package dji.pilot.usercenter.e;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class a {
    private static final String a = "RegionCode/";
    private static final String b = "regioncode";
    private static final String c = ".txt";
    private static final char d = '_';
    private static final char e = '|';
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 4096;
    private static final int j = 4097;
    private static final long k = 500;
    private Context l = null;
    private a m = null;
    private b n = null;
    private d o = null;
    private volatile boolean p = false;
    private String q = null;
    private String r = null;
    private volatile int s = 0;
    private volatile int t = 0;
    private String u = null;
    private final ArrayList<b> v = new ArrayList();

    public interface c {
        void a(List<b> list, b bVar);
    }

    private static final class a extends Handler {
        private final WeakReference<a> a;

        public a(Looper looper, a aVar) {
            super(looper);
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null && aVar.d()) {
                switch (message.what) {
                    case 4096:
                        if (aVar.s == 0) {
                            aVar.a((f) message.obj);
                            return;
                        } else if (aVar.s == 1) {
                            sendMessageDelayed(obtainMessage(4096, message.obj), 500);
                            return;
                        } else {
                            return;
                        }
                    case 4097:
                        aVar.f();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b extends HandlerThread {
        public b(String str) {
            this(str, 1);
        }

        public b(String str, int i) {
            super(str, i);
        }
    }

    private static final class d extends Handler {
        private final WeakReference<a> a;

        public d(Looper looper, a aVar) {
            super(looper);
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null && aVar.d()) {
                switch (message.what) {
                    case 4096:
                        f fVar = (f) message.obj;
                        c cVar = (c) fVar.b.get();
                        if (cVar != null) {
                            cVar.a(fVar.c, fVar.a);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class e {
        public static a a = new a();

        private e() {
        }
    }

    private static final class f {
        public b a;
        public WeakReference<c> b;
        List<b> c;

        private f() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public static a getInstance() {
        return e.a;
    }

    public void a() {
        this.v.clear();
        this.q = null;
        this.r = null;
    }

    public String b() {
        return this.u;
    }

    public synchronized boolean a(Context context) {
        this.t++;
        if (!this.p) {
            this.l = context.getApplicationContext();
            this.n = new b("region_decoder");
            this.n.start();
            this.m = new a(this.n.getLooper(), this);
            this.o = new d(Looper.getMainLooper(), this);
            this.p = true;
            e();
        }
        return this.p;
    }

    public synchronized boolean c() {
        boolean z = false;
        synchronized (this) {
            this.t--;
            if (this.p && this.t <= 0) {
                this.m = null;
                this.n.quit();
                this.n = null;
                this.o = null;
                this.t = 0;
                this.p = false;
            }
            if (!this.p) {
                z = true;
            }
        }
        return z;
    }

    public List<b> a(b bVar, c cVar) {
        f fVar;
        Message obtainMessage;
        if (bVar == null) {
            if (this.v.isEmpty()) {
                fVar = new f();
                fVar.a = bVar;
                fVar.b = new WeakReference(cVar);
                obtainMessage = this.m.obtainMessage(4096, fVar);
                if (this.s == 1) {
                    this.m.sendMessageDelayed(obtainMessage, 500);
                    return null;
                }
                this.m.sendMessage(obtainMessage);
                return null;
            }
            if (cVar != null) {
                cVar.a(this.v, bVar);
            }
            return this.v;
        } else if (!bVar.c) {
            return null;
        } else {
            fVar = new f();
            fVar.a = bVar;
            fVar.b = new WeakReference(cVar);
            obtainMessage = this.m.obtainMessage(4096, fVar);
            if (this.s == 1) {
                this.m.sendMessageDelayed(obtainMessage, 500);
                return null;
            }
            this.m.sendMessage(obtainMessage);
            return null;
        }
    }

    private boolean d() {
        return this.p;
    }

    private void e() {
        if (this.q == null) {
            Locale locale = this.l.getResources().getConfiguration().locale;
            this.u = locale.getLanguage();
            String absolutePath = this.l.getCacheDir().getAbsolutePath();
            if (absolutePath.endsWith(File.separator)) {
                absolutePath = absolutePath.substring(0, absolutePath.length() - 1);
            }
            int lastIndexOf = absolutePath.lastIndexOf(File.separatorChar);
            if (lastIndexOf != -1) {
                absolutePath = absolutePath.substring(0, lastIndexOf);
            }
            this.q = absolutePath + File.separator + a;
            dji.pilot.usercenter.f.c.f(this.q);
            if (!Locale.SIMPLIFIED_CHINESE.getLanguage().equals(locale.getLanguage())) {
                absolutePath = "_en";
            } else if (Locale.SIMPLIFIED_CHINESE.getCountry().equals(locale.getCountry())) {
                absolutePath = "_zh_CN";
            } else {
                absolutePath = "_zh_TW";
            }
            this.r = b + absolutePath + c;
            if (!dji.pilot.usercenter.f.c.a(this.q + this.r)) {
                this.s = 1;
                this.m.sendEmptyMessage(4097);
            }
        }
    }

    private void f() {
        AssetManager assets = this.l.getAssets();
        try {
            assets.list(b);
            dji.pilot.usercenter.f.c.b(assets.open(b + File.separator + this.r, 2), this.q + this.r);
            this.s = 0;
        } catch (Exception e) {
        }
    }

    private void a(f fVar) {
        if (fVar.a == null) {
            c(fVar);
        } else {
            b(fVar);
        }
    }

    private void b(f fVar) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        File file = new File(this.q + this.r);
        List arrayList = new ArrayList();
        if (dji.pilot.usercenter.f.c.a(file)) {
            b bVar = fVar.a;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                try {
                    bufferedReader.skip(bVar.d);
                    long j = bVar.d;
                    String str = bVar.a + d;
                    int length = str.length();
                    bVar = null;
                    long j2 = j;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null && readLine.startsWith(str)) {
                            j2 += (long) (readLine.length() + 2);
                            int indexOf = readLine.indexOf(124);
                            if (-1 != indexOf) {
                                String substring = readLine.substring(0, indexOf);
                                String substring2 = readLine.substring(indexOf + 1, readLine.length());
                                if (bVar != null && substring.startsWith(bVar.a + d)) {
                                    bVar.c = true;
                                }
                                if (readLine.indexOf(95, length) == -1) {
                                    bVar = new b();
                                    bVar.d = j2;
                                    bVar.b = substring2;
                                    bVar.a = substring;
                                    arrayList.add(bVar);
                                }
                            }
                        } else if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                            }
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e4) {
                            }
                        }
                        fVar.c = arrayList;
                        this.o.obtainMessage(4096, fVar).sendToTarget();
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                bufferedReader = null;
                e.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                fVar.c = arrayList;
                this.o.obtainMessage(4096, fVar).sendToTarget();
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        fVar.c = arrayList;
        this.o.obtainMessage(4096, fVar).sendToTarget();
    }

    private void c(f fVar) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        File file = new File(this.q + this.r);
        if (dji.pilot.usercenter.f.c.a(file)) {
            Collection arrayList = new ArrayList();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                b bVar = null;
                long j = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        j += (long) (readLine.length() + 2);
                        int indexOf = readLine.indexOf(124);
                        if (-1 != indexOf) {
                            String substring = readLine.substring(0, indexOf);
                            String substring2 = readLine.substring(indexOf + 1, readLine.length());
                            if (bVar != null && substring.startsWith(bVar.a + d)) {
                                bVar.c = true;
                            }
                            if (readLine.charAt(2) != d) {
                                bVar = new b();
                                bVar.d = j;
                                bVar.b = substring2;
                                bVar.a = substring;
                                arrayList.add(bVar);
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e5) {
                        }
                    }
                    this.v.addAll(arrayList);
                    fVar.c = this.v;
                    this.o.obtainMessage(4096, fVar).sendToTarget();
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
            this.v.addAll(arrayList);
        }
        fVar.c = this.v;
        this.o.obtainMessage(4096, fVar).sendToTarget();
    }
}
