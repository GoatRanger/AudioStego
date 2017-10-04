package dji.g.c.a;

import android.media.MediaCodec.BufferInfo;
import dji.midware.media.e;
import java.nio.ByteBuffer;
import java.util.Locale;

public class b extends c {
    private double h;
    private double i;
    private int j;
    private int k;
    private long l;

    public b() {
        this("");
    }

    public b(String str) {
        this(str, 1.0d, 1.0d);
    }

    public b(String str, double d, double d2) {
        super(str);
        this.h = 1.0d;
        this.i = 1.0d;
        this.l = -1;
        this.h = d;
        this.i = d2;
        this.j = 1;
        this.k = 0;
    }

    protected String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.US, "[in_0] volume=volume=%1.2f", new Object[]{Double.valueOf(this.h)}));
        if (this.i != 1.0d) {
            double d = this.i;
            if (d > 1.0d) {
                while (d > 2.0d) {
                    stringBuilder.append(",atempo=2.0");
                    d /= 2.0d;
                }
                stringBuilder.append(String.format(Locale.US, ",atempo=%1.2f", new Object[]{Double.valueOf(d)}));
            } else {
                while (d < 0.5d) {
                    stringBuilder.append(",atempo=0.5");
                    d /= 0.5d;
                }
                stringBuilder.append(String.format(Locale.US, ",atempo=%1.2f", new Object[]{Double.valueOf(d)}));
            }
        }
        stringBuilder.append(" [result]");
        String stringBuilder2 = stringBuilder.toString();
        e.d(d(), "FilterDescr: " + stringBuilder2);
        return stringBuilder2;
    }

    public int a(int i, ByteBuffer byteBuffer, int i2, int i3, long j) {
        if (this.i != 1.0d && this.l < 0) {
            this.l = j;
            e.d(d(), "set pts_start as " + j);
        }
        int i4 = 0;
        if (this.k % this.j == 0) {
            i4 = super.a(i, byteBuffer, i2, i3, j);
        }
        this.k++;
        return i4;
    }

    public synchronized int a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        int a;
        a = super.a(byteBuffer, bufferInfo);
        if (this.l >= 0) {
            bufferInfo.presentationTimeUs += this.l;
        }
        return a;
    }

    public void b() {
        this.l = -1;
        super.b();
    }
}
