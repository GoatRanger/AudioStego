package com.e;

import android.content.Context;
import android.os.Build.VERSION;
import com.e.al.a;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

public class t extends Thread implements a {
    private u a;
    private al b;
    private dc c;
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

    public t(Context context, u uVar, dc dcVar) {
        try {
            this.g = context.getApplicationContext();
            this.c = dcVar;
            if (uVar != null) {
                this.a = uVar;
                this.b = new al(new ad(this.a));
                String[] split = this.a.a().split(d.t);
                this.f = split[split.length - 1];
                split = this.f.split("_");
                this.h = split[0];
                this.i = split[2];
                this.j = split[1];
                this.l = Integer.parseInt(split[3]);
                this.m = Integer.parseInt(split[4].split("\\.")[0]);
                this.k = uVar.b();
                this.d = v.a(context, this.f);
            }
        } catch (Throwable th) {
            dg.a(th, "DexDownLoad", "DexDownLoad");
        }
    }

    private void a(String str) {
        f fVar = new f(this.g, x.c());
        List a = v.a.a(fVar, str, "copy");
        v.a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                v.b(this.g, fVar, ((y) a.get(i)).a());
            }
        }
    }

    private boolean a(Context context) {
        return cx.m(context) == 1;
    }

    private boolean a(f fVar, y yVar, String str, String str2, String str3, String str4) {
        if ("errorstatus".equals(yVar.e())) {
            if (!new File(v.b(this.g, this.c.a(), this.c.b())).exists()) {
                v.a(this.g, fVar, this.c);
                try {
                    s.a().b(this.g, this.c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else if (!new File(this.d).exists()) {
            return false;
        } else {
            List b = fVar.b(y.a(v.a(this.g, str, str2), str, str2, str3), y.class);
            if (b != null && b.size() > 0) {
                return true;
            }
            try {
                v.a(this.g, fVar, this.c, new y.a(v.a(this.g, str, this.c.b()), str4, str, str2, str3).a("usedex").a(), this.d);
                s.a().b(this.g, this.c);
            } catch (Throwable e2) {
                dg.a(e2, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e22) {
                dg.a(e22, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e222) {
                dg.a(e222, "DexDownLoad", "processDownloadedFile()");
            }
            return true;
        }
    }

    private boolean d() {
        f fVar = new f(this.g, x.c());
        try {
            List a = v.a.a(fVar, this.h, "usedex");
            if (a != null && a.size() > 0 && aa.a(((y) a.get(0)).d(), this.j) > 0) {
                return true;
            }
        } catch (Throwable th) {
            dg.a(th, "DexDownLoad", "isDownloaded()");
        }
        y a2 = v.a.a(fVar, this.f);
        return a2 != null ? a(fVar, a2, this.h, this.i, this.j, this.k) : false;
    }

    private boolean e() {
        return this.c != null && this.c.a().equals(this.h) && this.c.b().equals(this.i);
    }

    private boolean f() {
        return VERSION.SDK_INT >= this.m && VERSION.SDK_INT <= this.l;
    }

    private boolean g() {
        try {
            if (!e() || d() || !f() || !a(this.g)) {
                return false;
            }
            a(this.c.a());
            return true;
        } catch (Throwable th) {
            dg.a(th, "DexDownLoad", "isNeedDownload()");
            return false;
        }
    }

    public void a() {
        try {
            start();
        } catch (Throwable th) {
            dg.a(th, "DexDownLoad", "startDownload");
        }
    }

    public void a(Throwable th) {
        try {
            if (this.e != null) {
                this.e.close();
            }
        } catch (Throwable e) {
            dg.a(e, "DexDownLoad", "onException()");
        } catch (Throwable e2) {
            dg.a(e2, "DexDownLoad", "onException()");
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
            dg.a(e, "DexDownLoad", "onDownload()");
        } catch (Throwable e2) {
            dg.a(e2, "DexDownLoad", "onDownload()");
            return;
        }
        try {
            this.e.seek(j);
            this.e.write(bArr);
        } catch (Throwable e22) {
            dg.a(e22, "DexDownLoad", "onDownload()");
        }
    }

    public void b() {
        try {
            if (this.e != null) {
                try {
                    this.e.close();
                } catch (Throwable e) {
                    dg.a(e, "DexDownLoad", "onFinish()");
                }
                String b = this.a.b();
                if (v.a(this.d, b)) {
                    String c = this.a.c();
                    f fVar = new f(this.g, x.c());
                    v.a.a(fVar, new y.a(this.f, b, this.h, c, this.j).a("copy").a(), y.a(this.f, this.h, c, this.j));
                    v.a(this.g, fVar, this.c, new y.a(v.a(this.g, this.h, this.c.b()), b, this.h, c, this.j).a("usedex").a(), this.d);
                    s.a().b(this.g, this.c);
                    return;
                }
                try {
                    new File(this.d).delete();
                } catch (Throwable e2) {
                    dg.a(e2, "DexDownLoad", "onFinish()");
                }
            }
        } catch (Throwable e22) {
            dg.a(e22, "DexDownLoad", "onFinish()");
        } catch (Throwable e222) {
            dg.a(e222, "DexDownLoad", "onFinish()");
        } catch (Throwable e2222) {
            dg.a(e2222, "DexDownLoad", "onFinish()");
        }
    }

    public void c() {
    }

    public void run() {
        try {
            if (g()) {
                this.b.a(this);
            }
        } catch (Throwable th) {
            dg.a(th, "DexDownLoad", "run");
        }
    }
}
