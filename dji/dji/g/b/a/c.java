package dji.g.b.a;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import dji.log.DJILogHelper;
import dji.midware.media.b;
import dji.midware.media.e;
import java.nio.ByteBuffer;

public class c {
    public static String a = "AudioPreview_Decoder";
    private static final long b = -100000000;
    private MediaCodec c = null;
    private MediaFormat d = null;
    private boolean e = false;
    private boolean f = false;
    private long g;
    private int h;
    private d i;
    private a j;
    private ByteBuffer k = ByteBuffer.allocateDirect(b.a);
    private String l;

    public void a(d dVar) {
        this.i = dVar;
    }

    protected d a() {
        return this.i;
    }

    public String b() {
        return this.l;
    }

    public c(String str) {
        this.l = str;
        this.g = b;
        this.h = -1;
    }

    public boolean c() {
        return this.e;
    }

    public void d() throws b {
        this.g = b;
        this.h = -1;
        this.e = false;
        m();
        j();
    }

    public String e() {
        return a + "_" + this.l;
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    protected a f() {
        return this.j;
    }

    public long g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public void i() {
        try {
            if (this.c != null) {
                this.c.stop();
                this.c.release();
                this.c = null;
            }
        } catch (Exception e) {
            e.a(e(), e);
        }
    }

    protected void j() {
        MediaFormat c = a().c();
        DJILogHelper.getInstance().LOGI(dji.publics.b.a.b.m, "setDecoder wb " + c);
        if ("audio/ffmpeg".equals(c.getString("mime"))) {
            c.setString("mime", "audio/mp4a-latm");
        }
        if (this.c == null) {
            try {
                this.c = MediaCodec.createDecoderByType(c.getString("mime"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.c.configure(c, null, null, 0);
            this.c.start();
            this.d = c;
        } else if (this.d == null || !this.d.equals(c)) {
            e.c(false, e(), "reconfigure decoder. {{{currentMediaFormat=" + this.d + " }}} {{{ newMediaFormat=" + c + "}}}");
            try {
                this.c.stop();
                this.c.release();
            } catch (Exception e2) {
                e.a(e(), e2);
            }
            try {
                this.c = MediaCodec.createDecoderByType(c.getString("mime"));
            } catch (Exception e22) {
                e22.printStackTrace();
            }
            this.c.configure(c, null, null, 0);
            this.c.start();
            this.d = c;
        } else if (this.f) {
            e.c(false, e(), "restart decoder due to decoderReceivedEOS");
        }
        this.f = false;
    }

    private void m() throws b {
        e.c(false, e(), "flushDecoderBuffer()");
        if (this.c != null) {
            try {
                this.c.flush();
            } catch (Exception e) {
                e.a(e(), e);
                throw new b();
            }
        }
    }

    public int k() throws b {
        e.c(false, e(), "feedData(): isInputFileEOS=" + a().i());
        int i = 0;
        while (!a().i() && i < 10) {
            j();
            try {
                int dequeueInputBuffer = this.c.dequeueInputBuffer(0);
                if (dequeueInputBuffer == -1) {
                    e.c(false, e(), "decoder input TRY_AGAIN_LATER");
                    break;
                } else if (dequeueInputBuffer >= 0) {
                    long j;
                    int i2;
                    int i3;
                    this.k.clear();
                    int a = a().a(this.k);
                    if (a < 0) {
                        j = 0;
                        e.b(e(), "readSize<0");
                        i2 = i;
                        i = 0;
                    } else {
                        i3 = i + 1;
                        ByteBuffer byteBuffer = this.c.getInputBuffers()[dequeueInputBuffer];
                        byteBuffer.clear();
                        this.k.position(0);
                        this.k.limit(a);
                        if (byteBuffer.capacity() < a) {
                            e.b(e(), "decoderInputBuffer.capacity<readSize. decoder's capacity=" + byteBuffer.capacity() + " readSize" + a);
                            i = 0;
                            this.k.limit(0);
                        } else {
                            i = a;
                        }
                        byteBuffer.put(this.k);
                        byteBuffer.position(0);
                        byteBuffer.limit(i);
                        j = a().f();
                        i2 = i3;
                    }
                    i3 = a().h();
                    Object obj = !a().l() ? 1 : null;
                    int i4 = i3 | (obj != null ? 4 : 0);
                    e.c(false, a().a(), a().e() + "->" + this.l + ". com_pts=" + j + " file=" + a().a(j) + " org_pts=" + a().b(j) + " size=" + i + " deFlag=" + i4);
                    if ((i4 & 4) > 0) {
                        i3 = 0;
                        i4 = 4;
                    } else {
                        i3 = i;
                    }
                    this.c.queueInputBuffer(dequeueInputBuffer, 0, i3, j, i4);
                    if (obj != null) {
                        this.f = true;
                        e.c(false, e(), a().e() + " send EOS to " + this.l);
                    }
                    i = i2;
                }
            } catch (Exception e) {
                e.a(e(), e);
                throw new b();
            }
        }
        return i;
    }

    public int l() throws b {
        e.c(false, e(), "tryReleaseDecoderOutputBuffer is called");
        if (this.e) {
            e.c(false, e(), "tryReleaseDecoderOutputBuffer decoderOutputEOS=true");
            return -1;
        }
        BufferInfo bufferInfo = new BufferInfo();
        if (this.c == null) {
            e.c(false, e(), "decoder==null");
            throw new b();
        }
        try {
            int dequeueOutputBuffer = this.c.dequeueOutputBuffer(bufferInfo, 0);
            if (dequeueOutputBuffer == -1) {
                e.c(false, e(), "decoder output TRY_AGAIN_LATER");
                return 0;
            }
            if (dequeueOutputBuffer == -2) {
                e.c(false, e(), "decoder outputs input_format_changed");
                e.c(false, e(), "decoder's output format: " + this.c.getOutputFormat());
            } else if (dequeueOutputBuffer >= 0) {
                e.c(false, e(), String.format("decoder output index=%d flags=%d size=%d pts=%d", new Object[]{Integer.valueOf(dequeueOutputBuffer), Integer.valueOf(bufferInfo.flags), Integer.valueOf(bufferInfo.size), Long.valueOf(bufferInfo.presentationTimeUs)}));
                if ((bufferInfo.flags & 4) > 0) {
                    this.e = true;
                    e.c(false, e(), "decoder sees EOS");
                }
                if (bufferInfo.size == 0) {
                    this.c.releaseOutputBuffer(dequeueOutputBuffer, false);
                } else {
                    this.g = a().b(bufferInfo.presentationTimeUs);
                    this.h = a().a(bufferInfo.presentationTimeUs);
                    if (this.g >= 0) {
                        ByteBuffer byteBuffer = this.c.getOutputBuffers()[dequeueOutputBuffer];
                        long currentTimeMillis = System.currentTimeMillis();
                        f().a(this.c.getOutputFormat(), byteBuffer, bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs);
                        e.c(false, e(), "doPresent output_frame_in=" + (System.currentTimeMillis() - currentTimeMillis));
                        this.c.releaseOutputBuffer(dequeueOutputBuffer, false);
                        e.c(false, e(), "Decoder: " + this.l + "->sink. got_pts=" + this.g + " size=" + bufferInfo.size + " flags=" + bufferInfo.flags);
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            e.a(e(), e);
            throw new b();
        }
    }
}
