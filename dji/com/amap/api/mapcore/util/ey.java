package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import com.amap.api.mapcore.util.fr.a;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

public class ey extends Thread implements a {
    private ez a;
    private fr b;
    private dv c;
    private String d;
    private RandomAccessFile e;
    private String f;
    private Context g;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l;
    private int m;

    public ey(Context context, ez ezVar, dv dvVar) {
        try {
            this.g = context.getApplicationContext();
            this.c = dvVar;
            if (ezVar != null) {
                this.a = ezVar;
                this.b = new fr(new fh(this.a));
                String[] split = this.a.a().split(d.t);
                this.f = split[split.length - 1];
                split = this.f.split("_");
                this.h = split[0];
                this.i = split[2];
                this.j = split[1];
                this.l = Integer.parseInt(split[3]);
                this.m = Integer.parseInt(split[4].split("\\.")[0]);
                this.k = ezVar.b();
                this.d = fa.a(context, this.f);
            }
        } catch (Throwable th) {
            eb.a(th, "DexDownLoad", "DexDownLoad");
        }
    }

    public void a() {
        try {
            start();
        } catch (Throwable th) {
            eb.a(th, "DexDownLoad", "startDownload");
        }
    }

    public void run() {
        try {
            if (g()) {
                this.b.a(this);
            }
        } catch (Throwable th) {
            eb.a(th, "DexDownLoad", "run");
        }
    }

    private boolean a(ek ekVar, fd fdVar, String str, String str2, String str3, String str4) {
        if ("errorstatus".equals(fdVar.e())) {
            if (!new File(fa.b(this.g, this.c.a(), this.c.b())).exists()) {
                fa.a(this.g, ekVar, this.c);
                try {
                    ex.a().b(this.g, this.c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else if (!new File(this.d).exists()) {
            return false;
        } else {
            List b = ekVar.b(fd.a(fa.a(this.g, str, str2), str, str2, str3), fd.class);
            if (b != null && b.size() > 0) {
                return true;
            }
            try {
                fa.a(this.g, ekVar, this.c, new fd.a(fa.a(this.g, str, this.c.b()), str4, str, str2, str3).a("usedex").a(), this.d);
                ex.a().b(this.g, this.c);
            } catch (Throwable e2) {
                eb.a(e2, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e22) {
                eb.a(e22, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e222) {
                eb.a(e222, "DexDownLoad", "processDownloadedFile()");
            }
            return true;
        }
    }

    private boolean b() {
        ek ekVar = new ek(this.g, fc.a());
        try {
            List a = fa.a.a(ekVar, this.h, "usedex");
            if (a != null && a.size() > 0 && ff.a(((fd) a.get(0)).d(), this.j) > 0) {
                return true;
            }
        } catch (Throwable th) {
            eb.a(th, "DexDownLoad", "isDownloaded()");
        }
        fd a2 = fa.a.a(ekVar, this.f);
        return a2 != null ? a(ekVar, a2, this.h, this.i, this.j, this.k) : false;
    }

    private boolean c() {
        if (this.c != null && this.c.a().equals(this.h) && this.c.b().equals(this.i)) {
            return true;
        }
        return false;
    }

    private boolean f() {
        return VERSION.SDK_INT >= this.m && VERSION.SDK_INT <= this.l;
    }

    private boolean a(Context context) {
        return dq.m(context) == 1;
    }

    private boolean g() {
        try {
            if (!c() || b() || !f() || !a(this.g)) {
                return false;
            }
            a(this.c.a());
            return true;
        } catch (Throwable th) {
            eb.a(th, "DexDownLoad", "isNeedDownload()");
            return false;
        }
    }

    private void a(String str) {
        ek ekVar = new ek(this.g, fc.a());
        List a = fa.a.a(ekVar, str, "copy");
        fa.a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                fa.b(this.g, ekVar, ((fd) a.get(i)).a());
            }
        }
    }

    public void a(byte[] bArr, long j) {
        try {
            if (this.e == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.e = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            eb.a(e, "DexDownLoad", "onDownload()");
        } catch (Throwable e2) {
            eb.a(e2, "DexDownLoad", "onDownload()");
            return;
        }
        try {
            this.e.seek(j);
            this.e.write(bArr);
        } catch (Throwable e22) {
            eb.a(e22, "DexDownLoad", "onDownload()");
        }
    }

    public void a(Throwable th) {
        try {
            if (this.e != null) {
                this.e.close();
            }
        } catch (Throwable e) {
            eb.a(e, "DexDownLoad", "onException()");
        } catch (Throwable e2) {
            eb.a(e2, "DexDownLoad", "onException()");
        }
    }

    public void e() {
        try {
            if (this.e != null) {
                try {
                    this.e.close();
                } catch (Throwable e) {
                    eb.a(e, "DexDownLoad", "onFinish()");
                }
                String b = this.a.b();
                if (fa.a(this.d, b)) {
                    String c = this.a.c();
                    ek ekVar = new ek(this.g, fc.a());
                    fa.a.a(ekVar, new fd.a(this.f, b, this.h, c, this.j).a("copy").a(), fd.a(this.f, this.h, c, this.j));
                    fa.a(this.g, ekVar, this.c, new fd.a(fa.a(this.g, this.h, this.c.b()), b, this.h, c, this.j).a("usedex").a(), this.d);
                    ex.a().b(this.g, this.c);
                    return;
                }
                try {
                    new File(this.d).delete();
                } catch (Throwable e2) {
                    eb.a(e2, "DexDownLoad", "onFinish()");
                }
            }
        } catch (Throwable e22) {
            eb.a(e22, "DexDownLoad", "onFinish()");
        } catch (Throwable e222) {
            eb.a(e222, "DexDownLoad", "onFinish()");
        } catch (Throwable e2222) {
            eb.a(e2222, "DexDownLoad", "onFinish()");
        }
    }

    public void d() {
    }
}
