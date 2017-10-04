package dji.midware.g.a;

import com.google.a.b.a.k;

public class c {
    private final int a = 12;
    private final byte[] b = new byte[]{(byte) 0, (byte) 0, (byte) 1, (byte) -1};
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private final boolean h = false;
    private String i = getClass().getSimpleName();
    private d j;

    public enum a {
        LiveView(17),
        FileDownload(18),
        SecondaryLiveView(19);
        
        private int d;

        private a(int i) {
            this.d = 0;
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    public interface b {
        void onRecv(int i, byte[] bArr, int i2, int i3);
    }

    public c(final b bVar) {
        e eVar = new e();
        eVar.a = this.b;
        eVar.b = 8;
        this.j = new d(1048576, eVar, new dji.midware.g.a.d.a(this) {
            final /* synthetic */ c b;

            public int parseSecondHeader(byte[] bArr, int i, int i2) {
                this.b.c = dji.midware.util.c.f(bArr, i);
                int i3 = i + 4;
                this.b.d = bArr[i3];
                i3++;
                this.b.e = bArr[i3];
                i3++;
                this.b.f = bArr[i3];
                i3++;
                this.b.g = bArr[i3];
                i3++;
                return this.b.b(bArr, i, i2) != 0 ? -1 : this.b.c;
            }

            public void onGetBody(byte[] bArr, int i, int i2) {
                bVar.onRecv(this.b.e, bArr, i, i2);
            }
        });
        this.j.a(k.b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.j.a(bArr, i, i2);
    }

    private int b(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = this.b[0];
        for (i3 = 1; i3 < this.b.length; i3++) {
            i4 ^= this.b[i3];
        }
        for (i3 = 0; i3 < i2; i3++) {
            i4 ^= bArr[i + i3];
        }
        return i4;
    }
}
