package dji.midware.media;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.here.odnp.config.OdnpConfigStatic;
import java.nio.ByteBuffer;

public class c {
    private static final String a = "DJIVideoHardwareEncoder";
    private static final String b = "video/avc";
    private static final int c = 0;
    private static final boolean d = true;
    private boolean e = false;
    private int f = 16;
    private int g = 8;
    private BufferInfo h = new BufferInfo();
    private ByteBuffer[] i;
    private ByteBuffer[] j;
    private Surface k;
    private b l;
    private MediaCodec m;
    private HandlerThread n;
    private Handler o;
    private byte[] p;
    private byte[] q;

    private class a {
        final /* synthetic */ c a;
        private byte[] b;
        private long c = System.currentTimeMillis();

        public a(c cVar, byte[] bArr) {
            this.a = cVar;
            this.b = bArr;
        }

        public byte[] a() {
            return this.b;
        }

        public long b() {
            return this.c;
        }
    }

    private void a(String str) {
        Log.d(a, str);
    }

    private void e() {
        if (this.o == null) {
            this.n = new HandlerThread("video hardware encoder thread");
            this.n.start();
            this.o = new Handler(this, this.n.getLooper()) {
                final /* synthetic */ c a;

                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 0:
                            this.a.g();
                            try {
                                sendEmptyMessageDelayed(0, 1);
                                return;
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                return;
                            }
                        default:
                            return;
                    }
                }
            };
        }
    }

    private void f() {
        if (this.o != null) {
            this.o.removeCallbacksAndMessages(null);
        }
        if (this.n != null && this.n.isAlive()) {
            if (VERSION.SDK_INT >= 18) {
                this.n.quitSafely();
            } else {
                this.n.quit();
            }
            try {
                this.n.join(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
            } catch (Throwable e) {
                Log.e(a, "stopEncodingHandler: encoder thread join error: ", e);
            }
            j();
            this.n = null;
            this.o = null;
        }
    }

    public Surface a() {
        return this.k;
    }

    @TargetApi(21)
    public void a(int i, int i2, b bVar) {
        MediaCodecInfo mediaCodecInfo = null;
        if (this.m != null) {
            b();
        }
        e();
        a("encoder start");
        this.f = i;
        this.g = i2;
        this.l = bVar;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(b, i, i2);
        try {
            this.m = MediaCodec.createEncoderByType(b);
            mediaCodecInfo = this.m.getCodecInfo();
        } catch (Throwable e) {
            Log.e(a, "start: create MediaCodec error: ", e);
        }
        if (mediaCodecInfo.getCapabilitiesForType(b).getEncoderCapabilities().isBitrateModeSupported(1)) {
            createVideoFormat.setInteger("bitrate-mode", 1);
        }
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", 10000000);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 1);
        try {
            this.m.configure(createVideoFormat, null, null, 1);
        } catch (CodecException e2) {
            Log.e(a, "start: encoder configure codec exception: \n" + e2.getDiagnosticInfo());
        } catch (Throwable e3) {
            if (VERSION.SDK_INT < 21 || !(e3 instanceof CodecException)) {
                Log.e(a, "start: encoder configure error", e3);
            } else {
                Log.e(a, "start: encoder configure codec exception: \n" + ((CodecException) e3).getDiagnosticInfo());
            }
        }
        try {
            this.k = this.m.createInputSurface();
        } catch (CodecException e22) {
            Log.e(a, "start: create input surface error: \n" + e22.getDiagnosticInfo());
        } catch (Throwable e4) {
            if (VERSION.SDK_INT >= 21 && (e4 instanceof CodecException)) {
                Log.e(a, "start: create input surface error: \n" + ((CodecException) e4).getDiagnosticInfo());
            }
            Log.e(a, "start: create input surface error", e4);
        }
        this.m.start();
        this.e = true;
        this.j = this.m.getOutputBuffers();
        if (!this.o.hasMessages(0)) {
            this.o.sendEmptyMessage(0);
        }
    }

    private void g() {
        if (this.m != null && this.e) {
            int i = Integer.MIN_VALUE;
            try {
                i = this.m.dequeueOutputBuffer(this.h, 40000);
                Log.d(a, "dequeueDataFromEncoder: outputIndex = " + i);
            } catch (Throwable e) {
                Log.e(a, "dequeueDataFromEncoder: dequeue output buffer error: ", e);
            }
            if (i >= 0) {
                ByteBuffer byteBuffer = this.j[i];
                byteBuffer.position(this.h.offset);
                byteBuffer.limit(this.h.size - this.h.offset);
                byte[] bArr = new byte[(this.h.size - this.h.offset)];
                byteBuffer.get(bArr);
                a(bArr, this.h);
                try {
                    if (this.m != null) {
                        this.m.releaseOutputBuffer(i, false);
                    }
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                }
            } else if (i == -3) {
                h();
            } else if (i == -2) {
                i();
            }
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    private void a(byte[] bArr, BufferInfo bufferInfo) {
        boolean z = true;
        if ((bufferInfo.flags & 1) != 1) {
            z = false;
        }
        if (bArr != null && bArr.length > 5) {
            if ((bArr[4] & 31) == 7) {
                this.p = bArr;
                return;
            } else if ((bArr[4] & 31) == 8) {
                this.q = bArr;
                return;
            }
        }
        if (z) {
            bArr = a(a(this.p, this.q), bArr);
        }
        Log.d(a, "onOutputFrame: length: " + bArr.length);
        if (this.l != null) {
            this.l.onEncodeData(bArr, this.f, this.g, z);
        }
    }

    private void h() {
        this.j = this.m.getOutputBuffers();
    }

    private void i() {
        Log.e(a, "dequeueOutputBuffer INFO_OUTPUT_FORMAT_CHANGED");
    }

    private void j() {
        if (this.m != null) {
            try {
                this.m.flush();
                this.m.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } finally {
                this.m = null;
                this.e = false;
            }
        }
    }

    public void b() {
        if (this.o != null) {
            this.o.removeCallbacksAndMessages(null);
        }
        if (this.m != null) {
            try {
                this.m.flush();
                this.m = null;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        if (this.k != null) {
            this.k.release();
            this.k = null;
        }
        f();
    }

    public void c() {
        f();
    }

    public void d() {
        e();
    }
}
