package dji.midware.g.a;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.FPVController;
import dji.midware.util.c;

public class b {
    byte[] a = new byte[4];
    private final byte[] b = new byte[]{(byte) 68, (byte) 74, (byte) 65, (byte) 86};
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private byte[] f = new byte[8];
    private final boolean g = false;
    private String h = getClass().getSimpleName();
    private d i;

    public enum a {
        Video(1),
        Audio(2);
        
        private int c;

        private a(int i) {
            this.c = 0;
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    public b() {
        e eVar = new e();
        eVar.a = this.b;
        eVar.b = 16;
        this.i = new d(1048576, eVar, new dji.midware.g.a.d.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public int parseSecondHeader(byte[] bArr, int i, int i2) {
                int i3 = i + 1;
                System.arraycopy(bArr, i3, this.a.a, 0, 3);
                int g = (int) c.g(this.a.a, 0);
                i3 += 3;
                this.a.e = (bArr[i3] & 240) >> 4;
                this.a.d = bArr[i3] & 15;
                i3++;
                System.arraycopy(bArr, i3, this.a.f, 0, 2);
                i3 += 3;
                System.arraycopy(bArr, i3, this.a.f, 2, 2);
                i3 += 3;
                System.arraycopy(bArr, i3, this.a.f, 4, 2);
                i3 += 3;
                System.arraycopy(bArr, i3, this.a.f, 6, 2);
                i3 += 2;
                return g;
            }

            public void onGetBody(byte[] bArr, int i, int i2) {
                if (this.a.d == a.Video.a()) {
                    FPVController.native_transferVideoDataDirect(bArr, i, i2, this.a.f, this.a.f.length);
                } else if (this.a.d == a.Audio.a()) {
                    ServiceManager.getInstance().d();
                    FPVController.native_transferAudioData(bArr, i, i2, this.a.f, this.a.f.length);
                } else {
                    DJILogHelper.getInstance().LOGD(this.a.h, "packType=" + this.a.d + " packVer=" + this.a.e);
                }
            }
        });
        this.i.a("Playback");
    }

    public void a(byte[] bArr, int i, int i2) {
        this.i.a(bArr, i, i2);
    }
}
