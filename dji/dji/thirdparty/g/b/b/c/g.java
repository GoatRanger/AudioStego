package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.a.e;
import dji.thirdparty.g.f;
import java.io.IOException;

abstract class g implements dji.thirdparty.g.b.b.a.a {
    public static final int k = -1;
    private int j = -1;

    public static class a extends g {
        private final byte[] j;
        private final String l;

        public a(String str, byte[] bArr) {
            this.l = str;
            this.j = bArr;
        }

        public int e() {
            return this.j.length;
        }

        public String f() {
            return this.l;
        }

        public void a(byte[] bArr) throws f {
            if (this.j.length != bArr.length) {
                throw new f("Updated data size mismatch: " + this.j.length + " vs. " + bArr.length);
            }
            System.arraycopy(bArr, 0, this.j, 0, bArr.length);
        }

        public void a(e eVar) throws IOException, f {
            eVar.write(this.j);
        }
    }

    public abstract void a(e eVar) throws IOException, f;

    public abstract int e();

    public abstract String f();

    g() {
    }

    protected int g() {
        return this.j;
    }

    protected void c(int i) {
        this.j = i;
    }
}
