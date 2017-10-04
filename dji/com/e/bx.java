package com.e;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class bx {
    private Context a;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;

    protected bx(Context context) {
        this.a = context;
        a(768);
        b(768);
    }

    private ArrayList<File> a(File[] fileArr) {
        ArrayList<File> arrayList = new ArrayList();
        int i = 0;
        while (i < fileArr.length) {
            try {
                if (fileArr[i].isFile() && fileArr[i].getName().length() == 10 && TextUtils.isDigitsOnly(fileArr[i].getName())) {
                    arrayList.add(fileArr[i]);
                }
                i++;
            } catch (Throwable th) {
                bc.a(th, "CollectorFileProvider", "getKnownFile");
            }
        }
        return arrayList;
    }

    private File c() {
        try {
            if (Process.myUid() == 1000) {
                return null;
            }
            File file;
            boolean equals = "mounted".equals(Environment.getExternalStorageState());
            if (!cb.a() || equals) {
                File file2 = new File(cb.a(this.a).getPath() + File.separator + "carrierdata");
                if (file2.exists() && file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        ArrayList a = a(listFiles);
                        if (a.size() == 1) {
                            if (((File) a.get(0)).length() < ((long) this.f)) {
                                file = (File) a.get(0);
                                return file;
                            }
                        } else if (a.size() >= 2) {
                            file = (File) a.get(0);
                            file2 = (File) a.get(1);
                            if (file.getName().compareTo(file2.getName()) <= 0) {
                                file = file2;
                            }
                            return file;
                        }
                    }
                }
            }
            file = null;
            return file;
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "findWriteInSDCard");
        }
    }

    private File c(long j) {
        boolean z = false;
        try {
            if (Process.myUid() == 1000) {
                return null;
            }
            File file;
            boolean equals = "mounted".equals(Environment.getExternalStorageState());
            if (cb.a() && !equals) {
                equals = z;
                file = null;
            } else if (cb.b() <= ((long) (this.d / 2))) {
                return null;
            } else {
                File file2 = new File(cb.a(this.a).getPath() + File.separator + "carrierdata");
                if (!(file2.exists() && file2.isDirectory())) {
                    file2.mkdirs();
                }
                File file3 = new File(file2.getPath() + File.separator + j);
                try {
                    file = file3;
                    equals = file3.createNewFile();
                } catch (IOException e) {
                    boolean z2 = z;
                    file = file3;
                    equals = z2;
                }
            }
            return equals ? file : null;
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "createWriteFileInSDCard");
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int d() {
        /*
        r6 = this;
        r0 = 2;
        r2 = 1;
        r1 = 0;
        r3 = android.os.Process.myUid();	 Catch:{ Throwable -> 0x0087 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r3 != r4) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r3 = "mounted";
        r4 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x007a }
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x007a }
    L_0x0016:
        r4 = com.e.cb.a();	 Catch:{ Throwable -> 0x0087 }
        if (r4 == 0) goto L_0x001e;
    L_0x001c:
        if (r3 == 0) goto L_0x0085;
    L_0x001e:
        r3 = r6.a;	 Catch:{ Throwable -> 0x0087 }
        r3 = com.e.cb.a(r3);	 Catch:{ Throwable -> 0x0087 }
        r3 = r3.getPath();	 Catch:{ Throwable -> 0x0087 }
        r4 = new java.io.File;	 Catch:{ Throwable -> 0x0087 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0087 }
        r5.<init>();	 Catch:{ Throwable -> 0x0087 }
        r3 = r5.append(r3);	 Catch:{ Throwable -> 0x0087 }
        r5 = java.io.File.separator;	 Catch:{ Throwable -> 0x0087 }
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x0087 }
        r5 = "carrierdata";
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x0087 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0087 }
        r4.<init>(r3);	 Catch:{ Throwable -> 0x0087 }
        r3 = r4.exists();	 Catch:{ Throwable -> 0x0087 }
        if (r3 == 0) goto L_0x0085;
    L_0x004c:
        r3 = r4.isDirectory();	 Catch:{ Throwable -> 0x0087 }
        if (r3 == 0) goto L_0x0085;
    L_0x0052:
        r3 = r4.listFiles();	 Catch:{ Throwable -> 0x0087 }
        if (r3 == 0) goto L_0x0085;
    L_0x0058:
        r4 = r3.length;	 Catch:{ Throwable -> 0x0087 }
        if (r4 <= 0) goto L_0x0085;
    L_0x005b:
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0087 }
        r4 = r3.size();	 Catch:{ Throwable -> 0x0087 }
        if (r4 != r2) goto L_0x007f;
    L_0x0065:
        r0 = 0;
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0087 }
        r0 = (java.io.File) r0;	 Catch:{ Throwable -> 0x0087 }
        r0 = r0.length();	 Catch:{ Throwable -> 0x0087 }
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x007d;
    L_0x0076:
        r0 = 10;
    L_0x0078:
        r1 = r0;
        goto L_0x000b;
    L_0x007a:
        r3 = move-exception;
        r3 = r1;
        goto L_0x0016;
    L_0x007d:
        r0 = r2;
        goto L_0x0078;
    L_0x007f:
        r2 = r3.size();	 Catch:{ Throwable -> 0x0087 }
        if (r2 >= r0) goto L_0x0078;
    L_0x0085:
        r0 = r1;
        goto L_0x0078;
    L_0x0087:
        r0 = move-exception;
        r2 = "CollectorFileProvider";
        r3 = "findWriteInSDCard_OneFile";
        com.e.bc.a(r0, r2, r3);
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bx.d():int");
    }

    private File e() {
        boolean z = false;
        try {
            if (Process.myUid() == 1000) {
                return null;
            }
            File file;
            try {
                z = "mounted".equals(Environment.getExternalStorageState());
            } catch (Exception e) {
            }
            if (!cb.a() || r0) {
                File a = cb.a(this.a);
                if (a != null) {
                    file = new File(a.getPath() + File.separator + "carrierdata");
                    if (file.exists() && file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null && listFiles.length > 0) {
                            ArrayList a2 = a(listFiles);
                            if (a2.size() >= 2) {
                                a = (File) a2.get(0);
                                file = (File) a2.get(1);
                                if (a.getName().compareTo(file.getName()) <= 0) {
                                    file = a;
                                }
                                return file;
                            }
                        }
                    }
                }
            }
            file = null;
            return file;
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "findReadInSDCard");
        }
    }

    protected int a() {
        return this.b;
    }

    protected synchronized File a(long j) {
        File c;
        Throwable th;
        try {
            c = c();
            if (c == null) {
                try {
                    c = c(j);
                } catch (Throwable th2) {
                    th = th2;
                    bc.a(th, "CollectorFileProvider", "getWriteFile");
                    return c;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            c = null;
            th = th4;
            bc.a(th, "CollectorFileProvider", "getWriteFile");
            return c;
        }
        return c;
    }

    protected void a(int i) {
        try {
            this.b = i;
            this.d = (((this.b * 8) * 1500) + this.b) + 4;
            if (this.b == 256 || this.b == 768) {
                this.f = this.d / 100;
            } else if (this.b == 8736) {
                this.f = this.d - 5000;
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "setSnapshotInfo");
        }
    }

    protected File b() {
        try {
            e();
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "getReadFile");
        }
        return null;
    }

    protected void b(int i) {
        try {
            this.c = i;
            this.e = (((this.c * 8) * 1000) + this.c) + 4;
            this.g = this.e;
        } catch (Throwable th) {
            bc.a(th, "CollectorFileProvider", "setExternalSnapshotInfo");
        }
    }

    protected synchronized boolean b(long j) {
        boolean z = false;
        synchronized (this) {
            try {
                int d = d();
                if (d != 0) {
                    if (d == 1) {
                        if (c(j) != null) {
                            z = true;
                        }
                    } else if (d == 2) {
                        z = true;
                    }
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorFileProvider", "setUploadEnabled");
            }
        }
        return z;
    }
}
