package com.flurry.sdk;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ig<T> {
    private static final String a = ig.class.getSimpleName();
    private final File b;
    private final jh<T> c;

    public ig(File file, String str, int i, jk<T> jkVar) {
        this.b = file;
        this.c = new jf(new jj(str, i, jkVar));
    }

    public T a() {
        Throwable e;
        Throwable th;
        T t = null;
        if (this.b != null) {
            if (this.b.exists()) {
                Object obj = null;
                Closeable fileInputStream;
                try {
                    fileInputStream = new FileInputStream(this.b);
                    try {
                        t = this.c.b(fileInputStream);
                        jz.a(fileInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            in.a(3, a, "Error reading data file:" + this.b.getName(), e);
                            obj = 1;
                            jz.a(fileInputStream);
                            if (obj != null) {
                                in.a(3, a, "Deleting data file:" + this.b.getName());
                                this.b.delete();
                            }
                            return t;
                        } catch (Throwable th2) {
                            th = th2;
                            jz.a(fileInputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = t;
                    in.a(3, a, "Error reading data file:" + this.b.getName(), e);
                    obj = 1;
                    jz.a(fileInputStream);
                    if (obj != null) {
                        in.a(3, a, "Deleting data file:" + this.b.getName());
                        this.b.delete();
                    }
                    return t;
                } catch (Throwable e4) {
                    fileInputStream = t;
                    th = e4;
                    jz.a(fileInputStream);
                    throw th;
                }
                if (obj != null) {
                    in.a(3, a, "Deleting data file:" + this.b.getName());
                    this.b.delete();
                }
            } else {
                in.a(5, a, "No data to read for file:" + this.b.getName());
            }
        }
        return t;
    }

    public void a(T t) {
        Throwable e;
        int i;
        Object obj = null;
        Closeable closeable = null;
        if (t == null) {
            in.a(3, a, "No data to write for file:" + this.b.getName());
            obj = 1;
        } else {
            try {
                if (jy.a(this.b)) {
                    Closeable fileOutputStream = new FileOutputStream(this.b);
                    try {
                        this.c.a(fileOutputStream, t);
                        jz.a(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        closeable = fileOutputStream;
                        try {
                            in.a(3, a, "Error writing data file:" + this.b.getName(), e);
                            jz.a(closeable);
                            i = 1;
                            if (obj == null) {
                                in.a(3, a, "Deleting data file:" + this.b.getName());
                                this.b.delete();
                            }
                        } catch (Throwable th) {
                            e = th;
                            jz.a(closeable);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        closeable = fileOutputStream;
                        jz.a(closeable);
                        throw e;
                    }
                }
                throw new IOException("Cannot create parent directory!");
            } catch (Exception e3) {
                e = e3;
                in.a(3, a, "Error writing data file:" + this.b.getName(), e);
                jz.a(closeable);
                i = 1;
                if (obj == null) {
                    in.a(3, a, "Deleting data file:" + this.b.getName());
                    this.b.delete();
                }
            }
        }
        if (obj == null) {
            in.a(3, a, "Deleting data file:" + this.b.getName());
            this.b.delete();
        }
    }

    public boolean b() {
        if (this.b == null) {
            return false;
        }
        return this.b.delete();
    }
}
