package dji.thirdparty.b;

import dji.thirdparty.b.a.j;
import dji.thirdparty.c.d;
import dji.thirdparty.c.f;
import dji.thirdparty.c.p;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class ac {
    public abstract void a(d dVar) throws IOException;

    public abstract w b();

    public long c() throws IOException {
        return -1;
    }

    public static ac a(w wVar, String str) {
        Charset charset = j.c;
        if (wVar != null) {
            charset = wVar.c();
            if (charset == null) {
                charset = j.c;
                wVar = w.a(wVar + "; charset=utf-8");
            }
        }
        return a(wVar, str.getBytes(charset));
    }

    public static ac a(final w wVar, final f fVar) {
        return new ac() {
            public w b() {
                return wVar;
            }

            public long c() throws IOException {
                return (long) fVar.j();
            }

            public void a(d dVar) throws IOException {
                dVar.d(fVar);
            }
        };
    }

    public static ac a(w wVar, byte[] bArr) {
        return a(wVar, bArr, 0, bArr.length);
    }

    public static ac a(final w wVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        j.a((long) bArr.length, (long) i, (long) i2);
        return new ac() {
            public w b() {
                return wVar;
            }

            public long c() {
                return (long) i2;
            }

            public void a(d dVar) throws IOException {
                dVar.c(bArr, i, i2);
            }
        };
    }

    public static ac a(final w wVar, final File file) {
        if (file != null) {
            return new ac() {
                public w b() {
                    return wVar;
                }

                public long c() {
                    return file.length();
                }

                public void a(d dVar) throws IOException {
                    Closeable closeable = null;
                    try {
                        closeable = p.a(file);
                        dVar.a(closeable);
                    } finally {
                        j.a(closeable);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
