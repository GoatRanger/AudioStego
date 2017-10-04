package dji.midware.g.a;

public class d {
    private String a = getClass().getSimpleName();
    private boolean b = false;
    private final boolean c = false;
    private byte[] d;
    private int e = 0;
    private a f;
    private e g;
    private String h = "default";
    private int i = 0;
    private int j = 0;

    public interface a {
        void onGetBody(byte[] bArr, int i, int i2);

        int parseSecondHeader(byte[] bArr, int i, int i2);
    }

    public d(int i, e eVar, a aVar) {
        this.g = eVar;
        this.d = new byte[i];
        this.f = aVar;
    }

    public void a(String str) {
        this.h = str;
    }

    public String a() {
        return this.h;
    }

    public void a(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.d, this.e, i2);
        this.e += i2;
        c();
    }

    private void b() {
        if (this.j > 0) {
            this.e -= this.j;
            if (this.e > 0) {
                System.arraycopy(this.d, this.j, this.d, 0, this.e);
            }
            this.j = 0;
        }
    }

    private void c() {
        while (true) {
            int i;
            b();
            if (!this.b) {
                for (i = 0; i < this.e; i++) {
                    if (this.d[i] == this.g.a[0]) {
                        this.i = i;
                        if ((this.i + this.g.a.length) - 1 >= this.e) {
                            this.j = this.i;
                            break;
                        }
                        this.b = true;
                        for (int i2 = 1; i2 < this.g.a.length; i2++) {
                            if (this.d[this.i + i2] != this.g.a[i2]) {
                                this.b = false;
                                break;
                            }
                        }
                        if (this.b) {
                            this.j = this.i + this.g.a.length;
                            break;
                        }
                        this.j++;
                    }
                }
            }
            if (!this.b || this.e < this.j + this.g.b) {
                break;
            }
            i = this.f.parseSecondHeader(this.d, this.j, this.g.b);
            if (i >= 0) {
                this.g.c = i;
                if (this.e < (this.j + this.g.b) + i) {
                    break;
                }
                this.j += this.g.b;
                this.f.onGetBody(this.d, this.j, i);
                this.j = i + this.j;
                this.b = false;
            } else {
                this.b = false;
            }
        }
        b();
    }
}
