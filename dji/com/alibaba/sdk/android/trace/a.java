package com.alibaba.sdk.android.trace;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class a {
    private static int a = 1;
    private String b;
    private int c;
    private OutputStream d;
    private int e = 5242880;
    private byte[] f;
    private long g = 0;
    private long h;

    public a(String str, int i, byte[] bArr) {
        this.f = bArr;
        a(str, 1024, i);
    }

    public final synchronized void a() {
        try {
            if (this.d != null) {
                this.d.flush();
            }
            this.h = 0;
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public final synchronized void b() {
        try {
            a();
            if (this.d != null) {
                this.d.close();
            }
            this.d = null;
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized void a(String str, int i, int i2) {
        b();
        this.b = str;
        this.c = i;
        this.e = i2;
        try {
            OutputStream bufferedOutputStream;
            File file = new File(str);
            if (file.exists()) {
                this.h = file.length();
            } else {
                file = file.getParentFile();
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            OutputStream fileOutputStream = new FileOutputStream(str, true);
            if (i > 0) {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream, i);
            } else {
                bufferedOutputStream = fileOutputStream;
            }
            this.d = bufferedOutputStream;
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public final a a(String str) {
        return b((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.CHINA).format(new Date()) + str) + "\n\r");
    }

    public final synchronized a b(String str) {
        if (!(this.b == null || this.d == null || str == null || str.length() <= 0)) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                if (bytes != null && bytes.length > 0 && this.f != null && this.f.length > 0) {
                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = (byte) (bytes[i] ^ this.f[i % this.f.length]);
                    }
                }
                if (bytes != null && bytes.length > 0) {
                    try {
                        this.d.write(bytes);
                        this.h = ((long) bytes.length) + this.h;
                        if (this.h >= ((long) this.e) && this.h >= this.g) {
                            if (this.d != null) {
                                this.g = this.h + ((long) this.e);
                            }
                            boolean z = true;
                            if (a > 0) {
                                File file = new File(this.b + '.' + a);
                                if (file.exists()) {
                                    z = file.delete();
                                }
                                boolean z2 = z;
                                for (int i2 = a - 1; i2 > 0 && z2; i2--) {
                                    File file2 = new File(this.b + "." + i2);
                                    if (file2.exists()) {
                                        z2 = file2.renameTo(new File(this.b + '.' + (i2 + 1)));
                                    }
                                }
                                if (z2) {
                                    file = new File(this.b + ".1");
                                    b();
                                    new File(this.b).renameTo(file);
                                    a(this.b, this.c, this.e);
                                }
                            }
                        }
                    } catch (IOException e) {
                        if (e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } catch (UnsupportedEncodingException e2) {
            }
        }
        return this;
    }
}
