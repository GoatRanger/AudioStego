package com.e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

public class db extends Thread implements com.e.al.a {
    private static String h = "sodownload";
    private static String i = "sofail";
    private al a = new al(this.b);
    private a b;
    private RandomAccessFile c;
    private String d;
    private String e;
    private String f;
    private Context g;

    private static class a extends ao {
        private String a;

        a(String str) {
            this.a = str;
        }

        public Map<String, String> a() {
            return null;
        }

        public Map<String, String> b() {
            return null;
        }

        public String c() {
            return this.a;
        }
    }

    public db(Context context, String str, String str2, String str3) {
        this.g = context;
        this.f = str3;
        this.d = a(context, str + "temp.so");
        this.e = a(context, "libwgs2gcj.so");
        this.b = new a(str2);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private static String b(Context context, String str) {
        return a(context, str);
    }

    private void d() {
        File file = new File(this.d);
        if (file.exists()) {
            file.delete();
        }
    }

    public void a() {
        if (this.b != null && !TextUtils.isEmpty(this.b.c()) && this.b.c().contains("libJni_wgs2gcj.so") && this.b.c().contains(Build.CPU_ABI) && !new File(this.e).exists()) {
            start();
        }
    }

    public void a(Throwable th) {
        try {
            if (this.c != null) {
                this.c.close();
            }
            d();
            File file = new File(b(this.g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th2) {
            dg.a(th2, "SDKCoordinatorDownload", "onException");
        }
    }

    public void a(byte[] bArr, long j) {
        try {
            if (this.c == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.c = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            dg.a(e, "SDKCoordinatorDownload", "onDownload");
            d();
        } catch (Throwable e2) {
            d();
            dg.a(e2, "SDKCoordinatorDownload", "onDownload");
            return;
        }
        try {
            this.c.seek(j);
            this.c.write(bArr);
        } catch (Throwable e22) {
            d();
            dg.a(e22, "SDKCoordinatorDownload", "onDownload");
        }
    }

    public void b() {
        try {
            if (this.c != null) {
                this.c.close();
            }
            String a = cz.a(this.d);
            if (a == null || !a.equalsIgnoreCase(this.f)) {
                d();
            } else if (new File(this.e).exists()) {
                d();
            } else {
                new File(this.d).renameTo(new File(this.e));
            }
        } catch (Throwable th) {
            d();
            File file = new File(this.e);
            if (file.exists()) {
                file.delete();
            }
            dg.a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    public void c() {
        d();
    }

    public void run() {
        try {
            File file = new File(b(this.g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.a.a(this);
        } catch (Throwable th) {
            dg.a(th, "SDKCoordinatorDownload", "run");
            d();
        }
    }
}
