package dji.midware.media.i;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import dji.g.b.b;
import dji.gs.c.e;
import dji.midware.media.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class c implements OnAudioFocusChangeListener {
    private static final String b = "DJIAudioStreamPlayer";
    private static final boolean c = false;
    private static final boolean d = false;
    private static final int e = 3;
    private static final int f = 5;
    private static final int g = 4096;
    private static final int h = 16384;
    private static final int[] k = new int[]{96000, 88200, 64000, 48000, b.i, 32000, 24000, 22050, 16000, 12000, 11025, e.b};
    AudioManager a;
    private File i;
    private FileOutputStream j;
    private MediaCodec l;
    private AudioTrack m;
    private BufferInfo n;
    private short[] o;
    private a p;
    private boolean q;
    private boolean r;

    public enum a {
        ADTS,
        AAC,
        PCM
    }

    public c(a aVar) {
        this.i = new File("/sdcard/mydjiaudio.aac");
        this.j = null;
        this.l = null;
        this.m = null;
        this.n = new BufferInfo();
        this.o = new short[4096];
        this.q = false;
        this.r = true;
        this.a = null;
        this.p = aVar;
    }

    public c(a aVar, Context context) {
        this.i = new File("/sdcard/mydjiaudio.aac");
        this.j = null;
        this.l = null;
        this.m = null;
        this.n = new BufferInfo();
        this.o = new short[4096];
        this.q = false;
        this.r = true;
        this.a = null;
        this.p = aVar;
        this.a = (AudioManager) context.getSystemService("audio");
        c();
        try {
            this.j = new FileOutputStream(this.i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        if (this.m != null) {
            this.m.pause();
            this.m.flush();
            this.m.release();
            this.m = null;
        }
        if (this.l != null) {
            this.l.release();
            this.l = null;
        }
        if (this.j != null) {
            try {
                this.j.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        d();
        this.o = null;
    }

    public void b() {
    }

    public void a(int i, int i2, int i3) {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(d.d[0], i, i2);
        int i4 = 0;
        while (i4 < k.length) {
            if (i == k[i4]) {
                break;
            }
            i4++;
        }
        i4 = -1;
        if (i4 == -1) {
            throw new RuntimeException("unsupported sample rate");
        }
        createAudioFormat.setByteBuffer("csd-0", ByteBuffer.wrap(new byte[]{(byte) (((i3 + 1) << 3) | (i4 >> 1)), (byte) (((i4 << 7) & 128) | ((i2 & 15) << 3))}));
        a(createAudioFormat);
    }

    public void a(MediaFormat mediaFormat) {
        if (!this.q) {
            this.q = true;
            Integer valueOf = Integer.valueOf(mediaFormat.getInteger("sample-rate"));
            if (valueOf == null) {
                throw new RuntimeException("should set sampleRate");
            }
            Integer valueOf2 = Integer.valueOf(mediaFormat.getInteger("channel-count"));
            if (valueOf2 == null) {
                throw new RuntimeException("should set channelCount");
            }
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
            if (byteBuffer == null) {
                throw new RuntimeException("should set csd-0");
            }
            dji.midware.media.e.c(false, b, "csd-0=" + dji.midware.util.c.i(dji.thirdparty.afinal.c.c.a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position())));
            try {
                this.l = MediaCodec.createDecoderByType(mediaFormat.getString("mime"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat(mediaFormat.getString("mime"), valueOf.intValue(), valueOf2.intValue());
            createAudioFormat.setByteBuffer("csd-0", byteBuffer);
            this.l.configure(createAudioFormat, null, null, 0);
            this.l.start();
            this.m = new AudioTrack(3, valueOf.intValue(), 12, 2, 16384, 1);
            this.m.play();
        }
    }

    public void a(ByteBuffer byteBuffer, int i, int i2, long j) {
        dji.midware.media.e.c(true, b, "test 1 ptsUs=" + ((1000 * j) / 90));
        if (byteBuffer.capacity() < i + i2) {
            throw new RuntimeException("invalid offset and size. offset=" + i + " size=" + i2 + " capacity=" + byteBuffer.capacity());
        }
        int i3;
        byteBuffer.clear();
        if (!this.q) {
            if (this.p != a.ADTS) {
                throw new RuntimeException("should call prepare() with non-empty parameters if the stream is raw ACC instead of in ADTS format");
            } else if (i2 < 7) {
                throw new RuntimeException("invalid ADTS head");
            } else {
                byte[] bArr = new byte[7];
                byteBuffer.position(i);
                byteBuffer.get(bArr);
                dji.midware.media.e.c(false, b, "Frame head=" + dji.midware.util.c.i(bArr));
                i3 = (bArr[2] & 192) >>> 6;
                int i4 = k[(bArr[2] & 60) >> 2];
                int i5 = ((bArr[3] & 192) >>> 6) | ((bArr[2] & 1) << 2);
                dji.midware.media.e.c(false, b, "profile=" + i3 + " sampleRate=" + i4 + " channel=" + i5);
                a(i4, i5, i3);
            }
        }
        boolean z = false;
        if (this.p == a.ADTS) {
            if (i2 < 7) {
                throw new RuntimeException("invalid ADTS head");
            }
            bArr = new byte[7];
            byteBuffer.position(i);
            byteBuffer.get(bArr);
            z = (bArr[1] & 1) == 0;
            dji.midware.media.e.c(false, b, "CRC=" + z);
        }
        i3 = this.l.dequeueInputBuffer(0);
        if (i3 >= 0) {
            int i6;
            ByteBuffer byteBuffer2 = this.l.getInputBuffers()[i3];
            byteBuffer2.clear();
            if (this.p == a.ADTS) {
                i6 = i2 - (z ? 9 : 7);
                byteBuffer.position((z ? 9 : 7) + i);
            } else {
                byteBuffer.position(i);
                i6 = i2;
            }
            byteBuffer.limit(i + i2);
            byteBuffer2.put(byteBuffer);
            this.l.queueInputBuffer(i3, 0, i6, (1000 * j) / 90, 1);
            dji.midware.media.e.c(true, b, "test 2");
            i5 = -1;
            i3 = 0;
            while (i3 < 3 && r0 < 0) {
                i3++;
                i5 = this.l.dequeueOutputBuffer(this.n, 5);
            }
            dji.midware.media.e.c(true, b, "test 3");
            while (i5 >= 0) {
                dji.midware.media.e.c(false, b, "decoder outputs " + this.n.size + " bytes");
                if (this.n.size == 0) {
                    this.l.releaseOutputBuffer(i5, false);
                } else {
                    ByteBuffer byteBuffer3 = this.l.getOutputBuffers()[i5];
                    byteBuffer3.position(this.n.offset);
                    byteBuffer3.limit(this.n.offset + this.n.size);
                    i4 = this.n.size / 2;
                    byteBuffer3.asShortBuffer().get(this.o, 0, i4);
                    this.l.releaseOutputBuffer(i5, false);
                    if (this.r) {
                        dji.midware.media.e.c(true, b, "write " + this.m.write(this.o, 0, i4) + " shorts to the audio sink. Taken Time=" + (System.currentTimeMillis() - System.currentTimeMillis()));
                    }
                    i5 = this.l.dequeueOutputBuffer(this.n, 0);
                }
            }
            dji.midware.media.e.c(true, b, "test 4");
        }
    }

    public void a(double d, double d2) {
    }

    public boolean c() {
        boolean z = true;
        if (this.a == null) {
            return false;
        }
        if (1 != this.a.requestAudioFocus(this, 3, 1)) {
            z = false;
        }
        a(z);
        return z;
    }

    public boolean d() {
        boolean z = true;
        if (this.a == null) {
            return false;
        }
        if (1 != this.a.abandonAudioFocus(this)) {
            z = false;
        }
        a(z);
        return z;
    }

    public void a(boolean z) {
        if (z) {
            this.r = true;
        } else {
            this.r = false;
        }
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case -3:
                dji.midware.media.e.b(b, "audio focus: loss transient can duck");
                a(0.10000000149011612d, 0.10000000149011612d);
                return;
            case -2:
                dji.midware.media.e.b(b, "audio focus: loss transient");
                return;
            case -1:
                dji.midware.media.e.b(b, "audio focus: loss");
                return;
            case 1:
                dji.midware.media.e.b(b, "audio focus: gain");
                this.r = true;
                a(1.0d, 1.0d);
                return;
            default:
                return;
        }
    }
}
