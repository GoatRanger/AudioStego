package dji.midware.media.g;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import com.tencent.android.tpush.common.MessageKey;
import dji.midware.data.model.P3.DataCameraGetCameraRotationMode;
import dji.midware.data.model.P3.DataCameraSetCameraRotationMode.RotationAngleType;
import dji.midware.media.e;
import dji.midware.natives.FPVController;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class d implements b {
    private static String e = "FFMpegMuxer";
    private static boolean h = true;
    a a = null;
    c b = null;
    private String c;
    private dji.midware.media.c.a d = new dji.midware.media.c.a(dji.midware.media.d.r, true);
    private b f = b.StandBy;
    private int g = 0;

    static class a {
        int a;
        int b;
        int c;
        int d;
        long e;
        byte[] f;
        int g;

        a() {
        }
    }

    private enum b {
        StandBy,
        Initialized,
        TrackAdded,
        Muxing,
        Stopped
    }

    static class c {
        int a;
        int b;
        int c;
        byte[] d;
        int e;
        long f;

        c() {
        }
    }

    public synchronized void a(String str) throws IOException {
        if (this.f != b.StandBy) {
            e.b(e, "Current status=" + this.f + ", op=Init()");
        } else {
            e.c(h, e, "init");
            this.c = str;
            this.g = 0;
            this.f = b.Initialized;
        }
    }

    public int a() {
        return this.g;
    }

    public synchronized int a(MediaFormat mediaFormat) {
        int i;
        if (this.f == b.Initialized || this.f == b.TrackAdded) {
            e.c(h, e, "addTrack");
            i = 1;
            ByteBuffer byteBuffer;
            if (mediaFormat.getString("mime").equals(dji.midware.media.d.c[0])) {
                byteBuffer = mediaFormat.getByteBuffer("csd-0");
                ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("csd-1");
                Log.i(e, "sps = " + dji.midware.util.c.i(byteBuffer.array()));
                Log.i(e, "pps = " + dji.midware.util.c.i(byteBuffer2.array()));
                byte[] bArr = new byte[(byteBuffer.capacity() + byteBuffer2.capacity())];
                byteBuffer.clear();
                byteBuffer.get(bArr, 0, byteBuffer.capacity());
                byteBuffer2.clear();
                byteBuffer2.get(bArr, byteBuffer.capacity(), byteBuffer2.capacity());
                this.b = new c();
                this.b.a = this.g;
                this.b.b = mediaFormat.getInteger("width");
                this.b.c = mediaFormat.getInteger("height");
                this.b.d = bArr;
                this.b.e = bArr.length;
                if (mediaFormat.containsKey("durationUs")) {
                    this.b.f = mediaFormat.getLong("durationUs") * 1000;
                } else {
                    this.b.f = 1000;
                }
            } else if (mediaFormat.getString("mime").equals(dji.midware.media.d.d[0])) {
                byteBuffer = mediaFormat.getByteBuffer("csd-0");
                Log.i(e, "csd = " + dji.midware.util.c.i(byteBuffer.array()));
                byte[] bArr2 = new byte[byteBuffer.capacity()];
                byteBuffer.clear();
                byteBuffer.get(bArr2);
                e.c(h, e, "csdArray=" + Arrays.toString(bArr2));
                this.a = new a();
                this.a.a = this.g;
                this.a.b = 128000;
                this.a.c = mediaFormat.getInteger("sample-rate");
                this.a.d = mediaFormat.getInteger("channel-count");
                this.a.e = mediaFormat.getLong("durationUs") * 1000;
                this.a.f = bArr2;
                this.a.g = bArr2.length;
            }
            this.g++;
            this.f = b.TrackAdded;
        } else {
            e.b(e, "Current status=" + this.f + ", op=addTrack()");
            i = -1;
        }
        return i;
    }

    public synchronized void c() {
        int i = 1;
        synchronized (this) {
            if (this.f != b.TrackAdded) {
                e.b(e, "Current status=" + this.f + ", op=start()");
            } else {
                e.c(h, e, MessageKey.MSG_ACCEPT_TIME_START);
                FPVController.native_mp4MuxerInit(this.g);
                e.c(h, e, "isRotated: " + DataCameraGetCameraRotationMode.getInstance().getRotationAngleType().toString());
                if ((DataCameraGetCameraRotationMode.getInstance().getRotationAngleType() == RotationAngleType.Rotate90 ? 1 : 0) == 0) {
                    i = 0;
                }
                FPVController.native_mp4MuxerSetIsRotated(i);
                if (this.b != null) {
                    FPVController.native_mp4MuxerAddVideoTrack(this.b.a, this.b.b, this.b.c, this.b.d, this.b.e, this.b.f);
                }
                if (this.a != null) {
                    FPVController.native_mp4MuxerAddAudioTrack(this.a.a, this.a.b, this.a.c, this.a.d, this.a.e, this.a.f, this.a.g);
                }
                FPVController.native_mp4MuxerStart(this.c);
                this.f = b.Muxing;
            }
        }
    }

    public synchronized void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo, long j) {
        if (this.f != b.Muxing) {
            e.b(e, "Current status=" + this.f + ", op=writeSampleData()");
        } else {
            dji.midware.util.a.a.getInstance("FFMpegMuxer.writeSampleData").a(dji.midware.util.a.a.a, Integer.valueOf(bufferInfo.size));
            e.c(h, e, "writeSampleData");
            int i2 = 0;
            if ((bufferInfo.flags & 1) != 0) {
                i2 = 1;
            }
            byteBuffer.position(bufferInfo.offset);
            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            this.d.a(byteBuffer);
            try {
                if (FPVController.native_mp4MuxerWrite(i, this.d.c(), bufferInfo.size, i2, bufferInfo.presentationTimeUs, j) != 0) {
                    Log.e(e, "write error: re");
                }
            } catch (Exception e) {
            } finally {
                this.d.e();
            }
        }
    }

    public synchronized void d() {
        if (this.f != b.Muxing) {
            e.b(e, "Current status=" + this.f + ", op=stop()");
        } else {
            e.c(h, e, "stop");
            if (FPVController.native_mp4MuxerStop() != 0) {
                Log.e(e, "write error: re");
            }
            this.f = b.Stopped;
        }
    }

    public synchronized void b() {
        if (this.f != b.Stopped) {
            e.b(e, "Current status=" + this.f + ", op=release()");
        } else {
            e.c(h, e, "FFMpegMuxer release");
            this.f = b.StandBy;
        }
    }
}
