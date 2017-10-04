package dji.midware.media;

import dji.gs.c.e;
import java.nio.ByteBuffer;

public class b {
    public static final int a = 102400;
    public static final int b = 20480;
    private static final int[] c = new int[]{96000, 88200, 64000, 48000, dji.g.b.b.i, 32000, 24000, 22050, 16000, 12000, 11025, e.b};

    public enum a {
        AV_SAMPLE_FMT_U8,
        AV_SAMPLE_FMT_S16,
        AV_SAMPLE_FMT_S32,
        AV_SAMPLE_FMT_FLT,
        AV_SAMPLE_FMT_DBL,
        AV_SAMPLE_FMT_U8P,
        AV_SAMPLE_FMT_S16P,
        AV_SAMPLE_FMT_S32P,
        AV_SAMPLE_FMT_FLTP,
        AV_SAMPLE_FMT_DBLP,
        AV_SAMPLE_FMT_NB
    }

    public static ByteBuffer a(int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < c.length) {
            if (i2 == c[i4]) {
                break;
            }
            i4++;
        }
        i4 = -1;
        return ByteBuffer.wrap(new byte[]{(byte) (((i + 1) << 3) | (i4 >> 1)), (byte) (((i4 << 7) & 128) | ((i3 & 15) << 3))});
    }
}
