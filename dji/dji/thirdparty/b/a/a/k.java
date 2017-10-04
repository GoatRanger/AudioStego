package dji.thirdparty.b.a.a;

import dji.thirdparty.c.c;
import dji.thirdparty.c.e;
import dji.thirdparty.c.f;
import dji.thirdparty.c.i;
import dji.thirdparty.c.o;
import dji.thirdparty.c.p;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

class k {
    private final o a;
    private int b;
    private final e c = p.a(this.a);

    public k(e eVar) {
        this.a = new o(new i(this, eVar) {
            final /* synthetic */ k a;

            public long a(c cVar, long j) throws IOException {
                if (this.a.b == 0) {
                    return -1;
                }
                long a = super.a(cVar, Math.min(j, (long) this.a.b));
                if (a == -1) {
                    return -1;
                }
                this.a.b = (int) (((long) this.a.b) - a);
                return a;
            }
        }, new Inflater(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public int inflate(byte[] bArr, int i, int i2) throws DataFormatException {
                int inflate = super.inflate(bArr, i, i2);
                if (inflate != 0 || !needsDictionary()) {
                    return inflate;
                }
                setDictionary(o.m);
                return super.inflate(bArr, i, i2);
            }
        });
    }

    public List<f> a(int i) throws IOException {
        this.b += i;
        int l = this.c.l();
        if (l < 0) {
            throw new IOException("numberOfPairs < 0: " + l);
        } else if (l > 1024) {
            throw new IOException("numberOfPairs > 1024: " + l);
        } else {
            List<f> arrayList = new ArrayList(l);
            for (int i2 = 0; i2 < l; i2++) {
                f h = b().h();
                f b = b();
                if (h.j() == 0) {
                    throw new IOException("name.size == 0");
                }
                arrayList.add(new f(h, b));
            }
            c();
            return arrayList;
        }
    }

    private f b() throws IOException {
        return this.c.d((long) this.c.l());
    }

    private void c() throws IOException {
        if (this.b > 0) {
            this.a.b();
            if (this.b != 0) {
                throw new IOException("compressedLimit > 0: " + this.b);
            }
        }
    }

    public void a() throws IOException {
        this.c.close();
    }
}
