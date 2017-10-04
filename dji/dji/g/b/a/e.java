package dji.g.b.a;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.view.Surface;
import dji.midware.media.d;
import java.nio.ByteBuffer;

public class e {
    private static String a = "VideoPreview_Decoder";
    private static final long f = -100000000;
    private MediaCodec b = null;
    private boolean c = false;
    private Surface d;
    private int e;
    private MediaFormat g = null;
    private ByteBuffer h;
    private long i;
    private boolean j = false;
    private f k;
    private String l;
    private long m = -1;
    private int n = -1;
    private long o = -1;
    private long p;
    private int q = -1;
    private long r = -1;

    public e(String str) {
        this.l = str;
        this.i = f;
        b(f);
        this.h = ByteBuffer.allocateDirect(d.a(0, 0, 0));
    }

    public void a(long j) throws b {
        this.i = f;
        this.c = false;
        b(j);
        k();
        d();
        this.j = false;
    }

    public void a() {
        this.j = false;
    }

    public boolean b() {
        return this.j;
    }

    public void a(Surface surface) {
        this.d = surface;
    }

    public void c() {
        try {
            if (this.b != null) {
                this.b.stop();
                this.b.release();
                this.b = null;
            }
        } catch (Exception e) {
            dji.midware.media.e.a(f(), e);
        }
    }

    protected void d() {
        MediaFormat g = l().g();
        if (this.b == null || this.g == null || !this.g.equals(g)) {
            if (this.b != null) {
                dji.midware.media.e.c(true, f(), "reconfigure decoder");
                try {
                    this.b.stop();
                    this.b.release();
                    this.b = null;
                } catch (Exception e) {
                    dji.midware.media.e.a(a, e);
                }
            }
            if (this.b == null) {
                try {
                    this.b = MediaCodec.createDecoderByType(g.getString("mime"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.b.configure(g, this.d, null, 0);
            this.b.start();
            this.g = g;
        }
    }

    private void k() throws b {
        this.m = -1;
        this.n = -1;
        this.o = -1;
        dji.midware.media.e.c(true, f(), "flushDecoderBuffer()");
        if (this.b != null) {
            try {
                this.b.flush();
            } catch (Exception e) {
                dji.midware.media.e.a(f(), e);
                throw new b();
            }
        }
    }

    public void b(long j) {
        this.p = j;
        if (this.k != null) {
            this.q = this.k.a(j);
        }
        if (this.k != null) {
            this.r = this.k.b(this.q, j);
        }
    }

    public long e() {
        return this.p;
    }

    protected String f() {
        if (this.l == null) {
            return a;
        }
        return a + "_" + this.l;
    }

    public boolean g() {
        return this.c;
    }

    public long h() {
        return this.i;
    }

    public boolean i() throws b {
        dji.midware.media.e.c(false, f(), "tryReleaseDecoderOutputBuffer is called");
        if (this.c) {
            dji.midware.media.e.c(false, f(), "tryReleaseDecoderOutputBuffer decoderOutputEOS=true");
            return false;
        }
        while (this.i < e()) {
            if (this.b == null) {
                dji.midware.media.e.c(true, f(), "decoder==null");
                return false;
            }
            BufferInfo bufferInfo = new BufferInfo();
            try {
                int dequeueOutputBuffer = this.b.dequeueOutputBuffer(bufferInfo, 0);
                if (dequeueOutputBuffer == -1) {
                    dji.midware.media.e.c(false, f(), "decoder output TRY_AGAIN_LATER");
                    return false;
                } else if (dequeueOutputBuffer == -2) {
                    dji.midware.media.e.c(true, f(), "decoder outputs input_format_changed");
                    MediaFormat outputFormat = this.b.getOutputFormat();
                    dji.midware.media.e.c(true, f(), "decoder's output format: width=" + outputFormat.getInteger("width") + " height=" + outputFormat.getInteger("height"));
                } else if (dequeueOutputBuffer >= 0) {
                    dji.midware.media.e.c(false, f(), String.format("decoder output index=%d flags=%d size=%d pts=%d", new Object[]{Integer.valueOf(dequeueOutputBuffer), Integer.valueOf(bufferInfo.flags), Integer.valueOf(bufferInfo.size), Long.valueOf(bufferInfo.presentationTimeUs)}));
                    if ((bufferInfo.flags & 4) > 0) {
                        this.c = true;
                        dji.midware.media.e.c(true, f(), "decoder sees EOS");
                    }
                    int b = l().b(bufferInfo.presentationTimeUs);
                    long c = l().c(bufferInfo.presentationTimeUs);
                    long c2 = l().c(b, c);
                    dji.midware.media.e.c(true, f(), "decoderOutput: fileIndex=" + b + " pts_local=" + c + " pts_global=" + c2);
                    dji.midware.media.e.c(true, f(), "decoderOutput: ptsFromDecoder=" + bufferInfo.presentationTimeUs);
                    if (c2 < 0 || (d.a() >= 18 && bufferInfo.size == 0)) {
                        this.b.releaseOutputBuffer(dequeueOutputBuffer, false);
                        dji.midware.media.e.c(true, f(), "decoder drops a frame (left-CutPoint). file index=" + b + " globalPts=" + c2);
                    } else {
                        this.i = c2;
                        this.e = b;
                        if (this.i >= e() || this.c) {
                            this.b.releaseOutputBuffer(dequeueOutputBuffer, true);
                            this.j = true;
                            dji.midware.media.e.c(true, f(), "decoder RENDERs a frames. file index=" + this.e + " got_pts=" + this.i + " target_pts=" + e() + " flags=" + bufferInfo.flags);
                        } else {
                            this.b.releaseOutputBuffer(dequeueOutputBuffer, true);
                            this.j = true;
                            dji.midware.media.e.c(true, f(), "decoder drops a frame (ahead of displayer's target). file index=" + this.e + " got_pts=" + this.i + " target_pts=" + e());
                        }
                    }
                }
            } catch (Exception e) {
                dji.midware.media.e.a(f(), e);
                this.g = null;
                d();
                return false;
            }
        }
        return true;
    }

    private f l() {
        return this.k;
    }

    public void j() throws b {
        dji.midware.media.e.c(false, f(), "feedData(): isInputFileEOS=" + this.k.b() + " pre_decoder_input_pts_global=" + this.o + " pre_decoder_input_fileIndex=" + this.n + " pre_decoder_input_pts_local=" + this.m + " target_pts_global=" + e() + " target_fileIndex=" + this.q + " target_pts_local=" + this.r);
        while (!this.k.b()) {
            if (this.n < 0 || this.m < 0 || this.n >= this.q || (this.n < this.q && this.o < this.p + (3 * d.e()))) {
                dji.midware.media.e.c(true, f(), "DecoderInput 1: pre_fileIndex=" + this.n + " pre_ptsLocal=" + this.m + " target_fileIndex=" + this.q + " target_pts_local=" + this.r);
                d();
                try {
                    int dequeueInputBuffer = this.b.dequeueInputBuffer(0);
                    if (dequeueInputBuffer == -1) {
                        dji.midware.media.e.c(false, f(), "decoder input TRY_AGAIN_LATER");
                        return;
                    } else if (dequeueInputBuffer >= 0) {
                        long j;
                        int i;
                        this.h.clear();
                        int a = this.k.a(this.h);
                        if (a < 0) {
                            dji.midware.media.e.b(f(), "readSize<0");
                            j = 0;
                            i = 0;
                        } else {
                            ByteBuffer byteBuffer = this.b.getInputBuffers()[dequeueInputBuffer];
                            byteBuffer.clear();
                            this.h.position(0);
                            this.h.limit(a);
                            if (byteBuffer.capacity() < a) {
                                dji.midware.media.e.b(f(), "decoderInputBuffer.capacity<readSize. decoder's capacity=" + byteBuffer.capacity() + " readSize" + a);
                                this.h.limit(0);
                                a = 0;
                            }
                            byteBuffer.put(this.h);
                            byteBuffer.position(0);
                            byteBuffer.limit(a);
                            j = this.k.e();
                            i = this.k.h();
                            long c = this.k.c(i, j);
                            dji.midware.media.e.c(true, f(), "DecoderInput: fileIndex=" + i + " ptsLocal=" + j + " ptsGlobal=" + c);
                            this.o = c;
                            this.n = i;
                            this.m = j;
                            j = this.k.f();
                            i = a;
                        }
                        int d = this.k.d();
                        boolean z = !this.k.i();
                        if (z) {
                            a = 4;
                        } else {
                            a = 0;
                        }
                        d |= a;
                        dji.midware.media.e.c(true, f(), "DecoderInput: ptsToDecoder=" + j);
                        this.b.queueInputBuffer(dequeueInputBuffer, 0, i, j, d);
                        dji.midware.media.e.c(true, f(), "send to decoder. File=" + this.k.a(j) + " pts=" + j + " size=" + i + " ex-flag=" + -1 + " deFlag=" + d);
                        if (z) {
                            this.g = null;
                            dji.midware.media.e.c(true, f(), "send EOS to decoder");
                        }
                    }
                } catch (Exception e) {
                    dji.midware.media.e.a(f(), e);
                    throw new b();
                }
            }
            return;
        }
    }

    public void a(f fVar) {
        this.k = fVar;
    }
}
