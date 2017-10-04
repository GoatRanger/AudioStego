package dji.midware.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.R;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.l;
import dji.midware.data.manager.P3.m;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.e.h;
import dji.midware.stat.StatAverage;
import dji.midware.stat.StatService;
import dji.midware.stat.f;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DJIVideoDecoder {
    private static final boolean DEBUG_SYNC = false;
    public static final int InputQueueCapacity = 15;
    private static final int REINIT_CODEC_RETRY_INTERVAL = 2000;
    private static final int REINIT_CODEC_RETRY_NUM = 3;
    private static final int REINIT_CODEC_THRESHOLD = 3000;
    private static final String TAG = "DJIVideoDecoder";
    private static final String TAG_Input = "Decoder_Input";
    private static final String TAG_Server = "Decoder_Server";
    private static boolean TEST_RESTART_MECHANISM = false;
    public static final int connectLosedelay = 2000;
    public static int height = 9;
    private static DataCameraGetPushStateInfo stateInfo;
    private static dji.logic.c.b switchManager;
    public static boolean testDisconnect;
    private static final boolean testToogle = false;
    private static AtomicInteger time_initialized = new AtomicInteger(0);
    public static int width = 16;
    protected boolean DEBUG_CLIENT;
    protected boolean DEBUG_SERVER;
    protected boolean DEBUG_SERVER_VERBOSE;
    private a client;
    private Context context;
    private boolean isLiveStream;
    private boolean isStop;
    Handler jpegRenderHandler;
    private long lastDequeueOutputTime;
    private long lastQueueInCodecTime;
    private long lastReinitCodecTime;
    private int last_input_frame_num;
    public long latestPTS;
    public dji.midware.media.k.b.a listener;
    public Object listenerSync;
    private FileOutputStream liveStreamOutputStream;
    private h mCallback;
    public int outputColorFormat;
    public int outputHeight;
    public int outputWidth;
    private int playbackFrameRate;
    public byte[] pps_header;
    private int reinitCodecRetry;
    private dji.midware.media.h.b.b renderManager;
    private boolean saveLiveStream;
    private b server;
    public byte[] sps_header;
    private d statusMonitor;
    private Surface surfaceDrawTo;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.litchiS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.litchiC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ProductType.OrangeCV600.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ProductType.P34K.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ProductType.Longan.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ProductType.LonganZoom.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ProductType.LonganPro.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ProductType.LonganRaw.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[ProductType.KumquatX.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[ProductType.KumquatS.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[ProductType.Tomato.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[ProductType.Pomato.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[ProductType.PM820.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[ProductType.PM820PRO.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[ProductType.N1.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    private class a {
        final /* synthetic */ DJIVideoDecoder a;
        private HandlerThread b;

        private a(DJIVideoDecoder dJIVideoDecoder) {
            this.a = dJIVideoDecoder;
        }

        @SuppressLint({"NewApi"})
        public synchronized void a() {
            if (this.b != null && this.b.isAlive()) {
                if (this.a.server != null) {
                    this.a.server.removeCallbacksAndMessages(null);
                }
                if (VERSION.SDK_INT >= 18) {
                    this.b.quitSafely();
                } else {
                    this.b.quit();
                }
                try {
                    this.b.join(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                } catch (Exception e) {
                    e.a(DJIVideoDecoder.TAG, e);
                }
                if (this.a.server != null) {
                    this.a.server.k();
                    this.a.server = null;
                }
            }
        }

        public synchronized void b() {
            if (this.b != null && this.b.isAlive()) {
                this.a.server.sendEmptyMessage(10);
            }
        }

        public synchronized void c() {
            if (this.b == null || !this.b.isAlive()) {
                if (this.a.renderManager != null) {
                    this.b = new HandlerThread("DJIDecodeInoutHandlerThread", -1);
                    dji.midware.util.h.a(DJIVideoDecoder.TAG, "DJIDecodeInoutHandlerThread STARTED.");
                    this.b.start();
                    this.a.server = new b(this.a, this.b.getLooper());
                    this.a.server.sendEmptyMessageDelayed(2, 1);
                }
            }
        }

        private synchronized void a(byte[] bArr, int i, int i2, boolean z, long j, long j2, int i3, int i4) {
            e.c(this.a.DEBUG_CLIENT, DJIVideoDecoder.TAG_Input, "cline on queuein before checking inoutThread status");
            if (this.b != null && this.b.isAlive()) {
                e.c(this.a.DEBUG_CLIENT, DJIVideoDecoder.TAG_Input, "cline on queuein after checking inoutThread status");
                if (this.a.server != null) {
                    this.a.server.obtainMessage(1, new c(bArr, i, j2, System.currentTimeMillis(), z, i2, j, i3, i4)).sendToTarget();
                }
                int c = d.c(j2);
                if (c >= 16) {
                    StatService.getInstance().postEvent(f.class, "FrameNum_Abnormal", 1.0f);
                    e.c(this.a.DEBUG_CLIENT, DJIVideoDecoder.TAG_Input, String.format("[FrameNum_Sequence_Abnormal] time=%3.3f index=%d last=%d cur=%d", new Object[]{Double.valueOf(((double) (d.a(j2) % 1000000)) / 1000.0d), Integer.valueOf(d.b(j2)), Integer.valueOf(this.a.last_input_frame_num), Integer.valueOf(c)}));
                    this.a.last_input_frame_num = -1;
                } else {
                    if (this.a.last_input_frame_num != -1) {
                        int access$2100 = ((c - this.a.last_input_frame_num) + 16) % 16;
                        if (access$2100 > 1) {
                            StatService.getInstance().postEvent(f.class, "FrameNum_Skip", (float) access$2100);
                            e.c(this.a.DEBUG_CLIENT, DJIVideoDecoder.TAG_Input, String.format("[FrameNum_Sequence_Skip] time=%3.3f index=%d last=%d cur=%d", new Object[]{Double.valueOf(((double) (d.a(j2) % 1000000)) / 1000.0d), Integer.valueOf(d.b(j2)), Integer.valueOf(this.a.last_input_frame_num), Integer.valueOf(c)}));
                        }
                    }
                    this.a.last_input_frame_num = c;
                }
            }
        }
    }

    private class b extends Handler {
        private static final int f = 0;
        private static final int g = 1;
        private static final int h = 2;
        private static final int i = 10;
        private static final int r = 10000;
        long a;
        int b;
        BufferInfo c = new BufferInfo();
        LinkedList<Long> d = new LinkedList();
        final /* synthetic */ DJIVideoDecoder e;
        private BlockingQueue<c> j = new LinkedBlockingQueue(15);
        private ByteBuffer[] k;
        private ByteBuffer[] l;
        private MediaCodec m = null;
        private boolean n = false;
        private boolean o = false;
        private boolean p = false;
        private int q = 20;
        private int s = 0;
        private long t = System.currentTimeMillis();
        private long u = 0;
        private int v = 0;
        private boolean w = false;

        public b(DJIVideoDecoder dJIVideoDecoder, Looper looper) {
            this.e = dJIVideoDecoder;
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        try {
                            j();
                        } catch (Exception e) {
                            e.a(DJIVideoDecoder.TAG_Server, e);
                        }
                        removeCallbacksAndMessages(null);
                        sendEmptyMessageDelayed(2, 1);
                        return;
                    case 1:
                        e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "server on msg_queuein");
                        try {
                            a(message);
                        } catch (Exception e2) {
                            e.a(DJIVideoDecoder.TAG_Server, e2);
                        }
                        if (!hasMessages(2)) {
                            sendEmptyMessageDelayed(2, 1);
                            return;
                        }
                        return;
                    case 2:
                        try {
                            d();
                        } catch (Exception e22) {
                            e.a(DJIVideoDecoder.TAG_Server, e22);
                            k();
                        }
                        try {
                            c();
                        } catch (Exception e222) {
                            e.a(DJIVideoDecoder.TAG_Server, e222);
                            k();
                        }
                        if (!hasMessages(2)) {
                            sendEmptyMessageDelayed(2, (long) this.e.getDecoderInOutInterval());
                            return;
                        }
                        return;
                    case 10:
                        e.b(DJIVideoDecoder.TAG_Server, "reinit codec");
                        try {
                            this.p = false;
                            this.o = false;
                            this.j.clear();
                        } catch (Exception e2222) {
                            e.a(DJIVideoDecoder.TAG_Server, e2222);
                        }
                        if (!hasMessages(2)) {
                            sendEmptyMessageDelayed(2, 1);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Exception e22222) {
                e.a(DJIVideoDecoder.TAG, e22222);
            }
            e.a(DJIVideoDecoder.TAG, e22222);
        }

        public byte[] a(int i, int i2) {
            if (i.getInstance().e()) {
            }
            int iframeRawId = DJIVideoDecoder.getIframeRawId(i.getInstance().c(), i, i2);
            DJILogHelper.getInstance().LOGD(DJIVideoDecoder.TAG_Server, "onIframe come in " + i + "x" + i2 + " isRemotedSeted=" + i.getInstance().e() + " ptype=" + i.getInstance().c() + " iFrame=" + iframeRawId, false, false);
            if (iframeRawId >= 0) {
                InputStream openRawResource = this.e.context.getResources().openRawResource(iframeRawId);
                iframeRawId = openRawResource.available();
                DJILogHelper.getInstance().LOGD(DJIVideoDecoder.TAG_Server, "iframeId length=" + iframeRawId, false, true);
                byte[] bArr = new byte[iframeRawId];
                openRawResource.read(bArr);
                openRawResource.close();
                return bArr;
            }
            return null;
        }

        private void a(Message message) {
            c cVar = (c) message.obj;
            if (cVar != null) {
                c cVar2;
                dji.midware.util.a.a.getInstance("DJIVideoDecoder.onServerQueuein").a(dji.midware.util.a.a.a, Integer.valueOf(cVar.b));
                dji.midware.util.a.a.getInstance("DJIVideoDecoder.onServerQueuein").a("width", Integer.valueOf(cVar.j));
                dji.midware.util.a.a.getInstance("DJIVideoDecoder.onServerQueuein").a("height", Integer.valueOf(cVar.k));
                dji.midware.util.a.a.getInstance("DJIVideoDecoder.onServerQueuein").a(dji.midware.util.a.a.e, Integer.valueOf(cVar.g ? 1 : 0));
                if (!this.p) {
                    e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "server receives a frame. when iframe is not yet set");
                    if (cVar.h == 1 || cVar.g) {
                        byte[] a = a(cVar.j, cVar.k);
                        if (a != null) {
                            e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "queuein a default iframe");
                            cVar2 = new c(a, a.length, cVar.c, System.currentTimeMillis(), cVar.g, 0, cVar.i - 1, cVar.j, cVar.k);
                            this.j.clear();
                            this.j.offer(cVar2);
                            this.p = true;
                        } else if (cVar.g) {
                            e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "The coming frame is I-frame");
                            this.p = true;
                        } else {
                            e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "The stream is GOP and the coming frame is not I-frame");
                            return;
                        }
                    }
                    e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "the timing for setting iframe has not yet come. frameNum: " + cVar.h + ", isKeyFrame: " + cVar.g);
                    return;
                }
                if (!(cVar.j == 0 || cVar.k == 0 || (cVar.j == DJIVideoDecoder.width && cVar.k == DJIVideoDecoder.height)) || this.e.needReinitKeyFrame()) {
                    DJIVideoDecoder.width = cVar.j;
                    DJIVideoDecoder.height = cVar.k;
                    if (!f()) {
                        e.d(DJIVideoDecoder.TAG_Server, "init decoder for the 1st time or when resolution changes");
                        j();
                    }
                    this.e.reinitCodecRetry = this.e.reinitCodecRetry + 1;
                    if (this.e.mCallback != null) {
                        this.e.mCallback.resetVideoSurface(DJIVideoDecoder.width, DJIVideoDecoder.height);
                    }
                }
                if (this.j.offer(cVar)) {
                    e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "put a frame into the Extended-Queue with index=" + cVar.i);
                } else {
                    cVar2 = (c) this.j.poll();
                    this.j.offer(cVar);
                    StatService.getInstance().postEvent(f.class, "Input_DROP", 1.0f);
                    e.d(DJIVideoDecoder.TAG_Server, "Drop a frame with index=" + cVar2.i + " and append a frame with index=" + cVar.i);
                }
            }
            StatService.getInstance().postEvent(dji.midware.stat.c.class, "Input_Queue_Size_Max", (float) this.j.size());
        }

        private void d() throws Exception {
            c cVar = (c) this.j.peek();
            if (cVar == null) {
                e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "input frame queue is null");
                return;
            }
            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "before check codec status");
            if (this.m == null || !this.n) {
                j();
            }
            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "before codecSync");
            if (this.e.saveLiveStream) {
                try {
                    this.e.liveStreamOutputStream.write(cVar.a, 0, cVar.b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "before dequeueInputBuffer");
            try {
                int dequeueInputBuffer;
                if (VERSION.SDK_INT >= 23) {
                    dequeueInputBuffer = this.m.dequeueInputBuffer(10000);
                } else {
                    dequeueInputBuffer = this.m.dequeueInputBuffer(0);
                }
                e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "after dequeueInputBuffer, inIndex: " + dequeueInputBuffer);
                if (dequeueInputBuffer >= 0) {
                    this.s = 0;
                    try {
                        ByteBuffer byteBuffer = this.k[dequeueInputBuffer];
                        StatService.getInstance().postEvent(dji.midware.stat.b.class, "Input_Native_Buffer_Size", (float) this.k.length);
                        StatService.getInstance().postEvent(dji.midware.stat.e.class, "Input_Codec_FPS", 1.0f);
                        byteBuffer.clear();
                        byteBuffer.rewind();
                        byteBuffer.put(cVar.a);
                        try {
                            cVar.e = System.currentTimeMillis();
                            StatService.getInstance().postEvent(dji.midware.stat.c.class, "Input_Queue_Delay_Max", (float) cVar.a());
                            if (dji.midware.util.a.b.a) {
                                try {
                                    dji.midware.util.a.b.getInstance().b.append(String.format("[DECODER_FEED_INPUT_BUFFER] pts=%d queueing_delay=%d time=%d\n", new Object[]{Long.valueOf(cVar.c), Long.valueOf(r4), Long.valueOf(System.currentTimeMillis())}));
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "before queueToCodec");
                            a(this.m, cVar.a, dequeueInputBuffer, 0, cVar.b, cVar.c, 0);
                            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "after queueToCodec");
                            this.j.poll();
                            this.o = true;
                            return;
                        } catch (Exception e3) {
                            e.b(DJIVideoDecoder.TAG_Server, "queueInputBuffer error");
                            e.a(DJIVideoDecoder.TAG_Server, e3);
                            throw e3;
                        }
                    } catch (Exception e32) {
                        e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "after dequeueInputBuffer, exception: " + e32.getClass().getSimpleName());
                        throw e32;
                    }
                }
                if (VERSION.SDK_INT >= 23) {
                    this.s++;
                    if (this.s >= this.q) {
                        a();
                    }
                }
                e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "after dequeueInputBuffer, cannot get buffer");
                StatService.getInstance().postEvent(dji.midware.stat.e.class, "Input_Native_Buffer_Full_Try", 1.0f);
            } catch (Exception e322) {
                e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "dequeueInputBuffer exception: " + e322.getClass().getSimpleName());
                throw e322;
            }
        }

        public void a() {
            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "reset codec");
            this.j.clear();
            this.p = false;
            this.s = 0;
            this.m.flush();
        }

        private void e() {
            MediaFormat outputFormat = this.m.getOutputFormat();
            e.a(DJIVideoDecoder.TAG_Server, outputFormat.toString());
            dji.midware.media.d.a a = d.a(outputFormat);
            this.e.outputWidth = a.a;
            this.e.outputHeight = a.b;
            this.e.outputColorFormat = outputFormat.getInteger("color-format");
            e.a(DJIVideoDecoder.TAG_Server, String.format("Format changed. color=%d, width=%d, height=%d", new Object[]{Integer.valueOf(this.e.outputColorFormat), Integer.valueOf(this.e.outputWidth), Integer.valueOf(this.e.outputHeight)}));
            Log.e(DJIVideoDecoder.TAG, "dequeueOutputBuffer INFO_OUTPUT_FORMAT_CHANGED");
        }

        private boolean f() {
            return i.getInstance().c() != null && i.getInstance().c() == ProductType.LonganZoom;
        }

        private void g() {
            Log.e(DJIVideoDecoder.TAG_Server, "dequeueOutputBuffer INFO_OUTPUT_BUFFERS_CHANGED");
            if (!f()) {
                long currentTimeMillis = System.currentTimeMillis();
                this.d.addLast(Long.valueOf(currentTimeMillis));
                if (this.d.size() >= 10 && currentTimeMillis - ((Long) this.d.pollFirst()).longValue() < 1000) {
                    DJILogHelper.getInstance().LOGE(DJIVideoDecoder.TAG_Server, "Reset decoder. Get INFO_OUTPUT_BUFFERS_CHANGED more than 10 times within a second.", true, true);
                    this.d.clear();
                    this.e.server.removeCallbacksAndMessages(null);
                    this.e.server.sendEmptyMessage(0);
                } else if (this.l != null) {
                    this.l = this.m.getOutputBuffers();
                    Log.e(DJIVideoDecoder.TAG, "dequeueOutputBuffer INFO_OUTPUT_BUFFERS_CHANGED after");
                }
            }
        }

        private void h() {
            this.v++;
            if (System.currentTimeMillis() - this.u > OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
                this.u = System.currentTimeMillis();
                this.v = 0;
            }
        }

        private void i() {
            this.a = System.currentTimeMillis();
            this.e.lastDequeueOutputTime = this.a;
            this.e.reinitCodecRetry = 0;
            if (this.e.DEBUG_SERVER) {
                Log.i(DJIVideoDecoder.TAG_Server, "decoder outputs a frame at " + System.currentTimeMillis());
            }
            this.e.statusMonitor.c(this.e.statusMonitor.e);
            if (this.e.renderManager == null) {
                Log.i(DJIVideoDecoder.TAG, "mGLRenderManager == null || renderer==null");
                this.m.releaseOutputBuffer(this.b, false);
                return;
            }
            long b;
            this.e.latestPTS = this.c.presentationTimeUs;
            if (this.e.latestPTS != 0) {
                b = (long) d.b(this.e.latestPTS);
                long a = d.a(this.e.latestPTS);
                a = System.currentTimeMillis() - a;
                e.d(DJIVideoDecoder.TAG, "Decoding_delay=" + a + " frameIndex=" + b + " frameNum=" + d.c(this.e.latestPTS) + " comPts=" + this.e.latestPTS + " queueLen=" + this.j.size());
                StatService.getInstance().postEvent(StatAverage.class, "Decoding_delay_avg", (float) a);
                StatService.getInstance().postEvent(dji.midware.stat.c.class, "Decoding_delay_MAX", (float) a);
            }
            h();
            b = System.currentTimeMillis();
            this.m.releaseOutputBuffer(this.b, true);
            if (this.e.mCallback != null) {
                this.e.mCallback.oneFrameComeIn();
            }
            e.c(this.e.DEBUG_SERVER_VERBOSE, DJIVideoDecoder.TAG_Server, "after mCallback.oneFrameComeIn");
            StatService.getInstance().postEvent(StatAverage.class, "Output_dur_avg", (float) (System.currentTimeMillis() - b));
            StatService.getInstance().postEvent(dji.midware.stat.e.class, "Output_FPS", 1.0f);
            if (this.e.DEBUG_SERVER) {
                Log.i(DJIVideoDecoder.TAG_Server, "release output WITH display: time=" + System.currentTimeMillis() + " duration=" + (System.currentTimeMillis() - this.a));
            }
        }

        public boolean b() {
            return this.w;
        }

        private void j() {
            this.e.lastReinitCodecTime = System.currentTimeMillis();
            if (DJIVideoDecoder.TEST_RESTART_MECHANISM) {
                DJIVideoDecoder.time_initialized.addAndGet(1);
                e.a("\n ... time_initialized = " + DJIVideoDecoder.time_initialized + "\n ");
            }
            if (this.e.renderManager == null) {
                e.b(DJIVideoDecoder.TAG_Server, "call initVideoDecoder with renderManager==null");
                return;
            }
            this.w = true;
            if (this.m != null) {
                k();
            }
            DJILogHelper.getInstance().LOGE("", "initVideoDecoder video width = " + DJIVideoDecoder.width + "  height = " + DJIVideoDecoder.height, false, true);
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat(d.c[0], DJIVideoDecoder.width, DJIVideoDecoder.height);
            createVideoFormat.setInteger("color-format", 2130708361);
            createVideoFormat.setInteger("flags", 2);
            if (VERSION.SDK_INT < 21) {
                createVideoFormat.setInteger("color-format", 21);
            }
            try {
                this.m = MediaCodec.createDecoderByType(d.c[0]);
                Log.e("VideoDecoder", "initVideoDecoder create");
                if (!(DJIVideoDecoder.TEST_RESTART_MECHANISM && DJIVideoDecoder.time_initialized.get() % 3 == 0)) {
                    this.m.configure(createVideoFormat, this.e.renderManager.a(), null, 0);
                }
                Log.e("VideoDecoder", "initVideoDecoder configure");
                if (this.m == null) {
                    Log.e("VideoDecoder", "Can't find video info!");
                    return;
                }
                this.m.start();
                this.k = this.m.getInputBuffers();
                this.l = this.m.getOutputBuffers();
                this.n = true;
                this.w = false;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(DJIVideoDecoder.TAG_Server, "start failed, do it again");
            }
        }

        private void a(MediaCodec mediaCodec, byte[] bArr, int i, int i2, int i3, long j, int i4) {
            this.e.lastQueueInCodecTime = System.currentTimeMillis();
            e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "feed into codec: index=" + i);
            dji.midware.util.a.a.getInstance("DJIVideoDecoder.queueToCodec").a(dji.midware.util.a.a.a, Integer.valueOf(i3));
            mediaCodec.queueInputBuffer(i, i2, i3, j, i4);
            if (dji.midware.util.a.c.a) {
                dji.midware.util.a.c.getInstance(dji.midware.util.a.c.b).a(bArr, 0, i3);
            }
        }

        private void k() {
            e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "releaseDecoder() start");
            dji.midware.util.h.a(DJIVideoDecoder.TAG_Server, "releaseDecoder() start");
            if (this.m != null) {
                try {
                    this.m.flush();
                } catch (Exception e) {
                    e.a(DJIVideoDecoder.TAG_Server, e);
                }
                try {
                    this.m.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    this.m = null;
                }
            }
            this.n = false;
            this.o = false;
            this.p = false;
            if (this.j != null) {
                this.j.clear();
            }
            dji.midware.util.h.a(DJIVideoDecoder.TAG_Server, "releaseDecoder() end");
        }

        public void c() throws Exception {
            if (DJIVideoDecoder.TEST_RESTART_MECHANISM && DJIVideoDecoder.time_initialized.get() % 3 == 1 && System.currentTimeMillis() - this.t > OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
                e.a(DJIVideoDecoder.TAG, "\n ... decoder thread quits");
            } else if (this.m != null && this.n && this.o) {
                this.b = -1;
                try {
                    this.b = this.m.dequeueOutputBuffer(this.c, 0);
                    e.c(this.e.DEBUG_SERVER, DJIVideoDecoder.TAG_Server, "Decoder output outputBufferIndex = " + this.b);
                    if (this.b >= 0) {
                        i();
                    } else if (this.b == -3) {
                        g();
                    } else if (this.b == -2) {
                        e();
                    }
                } catch (Exception e) {
                    Log.e(DJIVideoDecoder.TAG_Server, "dequeueOutputBuffer error");
                    throw e;
                }
            }
        }
    }

    private static class c {
        public byte[] a;
        public int b;
        public long c;
        public long d;
        public long e;
        public long f;
        public boolean g;
        public int h;
        public long i;
        public int j;
        public int k;

        public c(byte[] bArr, int i, long j, long j2, boolean z, int i2, long j3, int i3, int i4) {
            this.a = bArr;
            this.b = i;
            this.c = j;
            this.d = j2;
            this.g = z;
            this.h = i2;
            this.i = j3;
            this.j = i3;
            this.k = i4;
        }

        public long a() {
            return this.e - this.d;
        }

        public long b() {
            return this.f - this.e;
        }

        public long c() {
            return this.f - this.e;
        }
    }

    private class d {
        private static final int f = 1;
        private static final int g = 3;
        protected l a;
        protected e b;
        protected m c;
        final /* synthetic */ DJIVideoDecoder d;
        private int e;
        private Handler h;

        private d(DJIVideoDecoder dJIVideoDecoder) {
            this.d = dJIVideoDecoder;
            this.e = 2000;
            this.a = l.NoVideo;
            this.b = e.Healthy;
            this.c = m.ConnectLose;
            this.h = new Handler(new Callback(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public boolean handleMessage(Message message) {
                    if (!this.a.d.isStop) {
                        switch (message.what) {
                            case 1:
                                this.a.a(m.ConnectLose);
                                dji.thirdparty.a.c.a().e(this.a.c);
                                break;
                            case 3:
                                this.a.a(l.NoVideo);
                                dji.thirdparty.a.c.a().e(this.a.a);
                                break;
                            default:
                                break;
                        }
                    }
                    return false;
                }
            });
        }

        private void a() {
            if (this.h != null) {
                this.h.removeCallbacksAndMessages(null);
                this.h = null;
            }
        }

        private boolean b() {
            return this.a == l.HasVideo;
        }

        private boolean c() {
            return this.c == m.ConnectOK;
        }

        private void a(m mVar) {
            this.c = mVar;
            d();
        }

        private void a(l lVar) {
            this.a = lVar;
            d();
        }

        private void d() {
            e eVar;
            if (DJIVideoDecoder.TEST_RESTART_MECHANISM) {
                e.a(DJIVideoDecoder.TAG, "check: curEvent=" + this.c + " curVideoEvent=" + this.a);
            }
            if (this.c == m.ConnectLose && this.a == l.HasVideo) {
                eVar = e.Unhealthy;
            } else {
                eVar = e.Healthy;
            }
            if (eVar != this.b) {
                this.b = eVar;
                dji.thirdparty.a.c.a().e(this.b);
            }
        }

        private void a(int i) {
            this.e = i;
            b(this.e);
            d(this.e);
        }

        private void e() {
            b(this.e);
            d(this.e);
        }

        private void b(int i) {
            if (this.d.isLiveStream) {
                if (this.h != null) {
                    this.h.removeMessages(1);
                }
                if (this.c == m.ConnectOK && this.h != null) {
                    this.h.sendEmptyMessageDelayed(1, (long) i);
                }
            }
        }

        private void c(int i) {
            if (this.d.isLiveStream) {
                if (this.c != m.ConnectOK) {
                    a(m.ConnectOK);
                    dji.thirdparty.a.c.a().e(this.c);
                }
                b(i);
            }
        }

        private void f() {
            if (this.h != null) {
                this.h.removeMessages(1);
                this.h.removeMessages(3);
            }
        }

        private void d(int i) {
            if (this.d.isLiveStream) {
                if (this.h != null) {
                    this.h.removeMessages(3);
                }
                if (this.a == l.HasVideo && this.h != null) {
                    this.h.sendEmptyMessageDelayed(3, (long) i);
                }
            }
        }

        private void e(int i) {
            if (this.d.isLiveStream) {
                if (this.a != l.HasVideo) {
                    a(l.HasVideo);
                    dji.thirdparty.a.c.a().e(this.a);
                }
                d(i);
            }
        }
    }

    public enum e {
        Unhealthy,
        Healthy
    }

    public int getPlaybackFrameRate() {
        return this.playbackFrameRate;
    }

    public void setPlaybackFrameRate(int i) {
        this.playbackFrameRate = i;
    }

    private boolean needReinitKeyFrame() {
        ProductType c = i.getInstance().c();
        DataCameraGetPushStateInfo.getInstance().getCameraType();
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        if (c != ProductType.Grape2 && c != ProductType.PM820 && c != ProductType.PM820PRO) {
            return false;
        }
        if (mode != MODE.RECORD && mode != MODE.TAKEPHOTO) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.reinitCodecRetry > 3 || currentTimeMillis - this.lastReinitCodecTime < 2000 || this.lastQueueInCodecTime - this.lastDequeueOutputTime <= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
            return false;
        }
        return true;
    }

    public boolean needLowFrequencyForSmoothing() {
        ProductType c = i.getInstance().c();
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        return (cameraType == CameraType.DJICameraTypeGD600 && mode == MODE.PLAYBACK) || (c == ProductType.Longan && mode == MODE.NEW_PLAYBACK);
    }

    private int getDecoderInOutInterval() {
        ProductType c = i.getInstance().c();
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        if (cameraType == CameraType.DJICameraTypeGD600 && mode == MODE.PLAYBACK) {
            return 33;
        }
        if (c == ProductType.Longan && mode == MODE.NEW_PLAYBACK) {
            return 30;
        }
        return 1;
    }

    public void resetCodec() {
        this.server.a();
    }

    public DJIVideoDecoder(Context context, dji.midware.media.h.b.b bVar) {
        this(context, true, bVar);
    }

    public DJIVideoDecoder(Context context, boolean z, dji.midware.media.h.b.b bVar) {
        this.DEBUG_CLIENT = false;
        this.DEBUG_SERVER = false;
        this.DEBUG_SERVER_VERBOSE = false;
        this.client = new a();
        this.isStop = false;
        this.renderManager = null;
        this.listener = null;
        this.listenerSync = new Object();
        this.outputWidth = 0;
        this.outputHeight = 0;
        this.sps_header = null;
        this.pps_header = null;
        this.last_input_frame_num = -1;
        this.outputColorFormat = -1;
        this.playbackFrameRate = -1;
        this.statusMonitor = new d();
        this.isLiveStream = true;
        this.saveLiveStream = false;
        e.a(TAG, "new a DJIVideoDecoder = " + toString());
        this.context = context;
        this.isLiveStream = z;
        ServiceManager.getInstance().a(this);
        this.statusMonitor.b(5000);
        this.statusMonitor.d(5000);
        setSurface(bVar);
    }

    public boolean isDecoderOK() {
        return this.statusMonitor.c();
    }

    public boolean isHasVideoData() {
        return this.statusMonitor.b();
    }

    public void resetToManager() {
        ServiceManager.getInstance().a(this);
    }

    public void resetKeyFrame() {
        this.client.b();
    }

    public void setRecvDataCallBack(h hVar) {
        this.mCallback = hVar;
    }

    public static int getIframeRawId(ProductType productType, int i, int i2) {
        if (stateInfo == null) {
            stateInfo = DataCameraGetPushStateInfo.getInstance();
        }
        if (switchManager == null) {
            switchManager = dji.logic.c.b.getInstance();
        }
        int i3 = R.raw.iframe_1280x720_ins;
        switch (AnonymousClass2.a[productType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                if (i == 960) {
                    return R.raw.iframe_960x720_3s;
                }
                return R.raw.iframe_1280x720_3s;
            case 4:
                switch (i) {
                    case 640:
                        return R.raw.iframe_640x480;
                    case 848:
                        return R.raw.iframe_848x480;
                    default:
                        return R.raw.iframe_1280x720_3s;
                }
            case 5:
                if (stateInfo.getVerstion() < 4) {
                    return R.raw.iframe_1280x720_ins;
                }
                return -1;
            case 6:
                if (i == 960) {
                    return R.raw.iframe_960x720_osmo_gop;
                }
                if (i == 1280) {
                    return R.raw.iframe_1280x720_osmo_gop;
                }
                if (i == 640) {
                    return R.raw.iframe_640x368_osmo_gop;
                }
                return -1;
            case 7:
            case 8:
                return R.raw.iframe_1280x720_ins;
            case 9:
            case 10:
                if (switchManager.b()) {
                    return R.raw.iframe_1280x720_wm220;
                }
                return -1;
            case 11:
                return R.raw.iframe_1280x720_p4;
            case 12:
                switch (i) {
                    case 960:
                        return R.raw.iframe_p4p_720_4x3;
                    case 1088:
                        return R.raw.iframe_p4p_720_3x2;
                    case 1280:
                        return R.raw.iframe_p4p_720_16x9;
                    case 1344:
                        return R.raw.iframe_p4p_1344x720;
                    default:
                        return R.raw.iframe_p4p_720_16x9;
                }
            case 13:
            case 14:
                CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
                if (i == 720 && i2 == 480) {
                    return R.raw.iframe_720x480_m600;
                }
                if (i == 1280 && i2 == 720) {
                    if (cameraType == CameraType.DJICameraTypeGD600) {
                        return R.raw.iframe_gd600_1280x720;
                    }
                    return R.raw.iframe_1280x720_m600;
                } else if (i == 1920 && i2 == dji.midware.media.h.b.a.e) {
                    return R.raw.iframe_1920x1080_m600;
                } else {
                    if (i == dji.midware.media.h.b.a.e && i2 == 720) {
                        return R.raw.iframe_1080x720_gd600;
                    }
                    return -1;
                }
            case 15:
                if (DataCameraGetPushStateInfo.getInstance().getCameraType() != CameraType.DJICameraTypeGD600) {
                    return R.raw.iframe_1280x720_ins;
                }
                if (i == 1280 && i2 == 720) {
                    return R.raw.iframe_gd600_1280x720;
                }
                return R.raw.iframe_1080x720_gd600;
            default:
                return R.raw.iframe_1280x720_ins;
        }
    }

    public void queueInputBuffer(byte[] bArr, int i, long j, int i2, boolean z, long j2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.statusMonitor.e(this.statusMonitor.e);
        StatService.getInstance().postEvent(dji.midware.stat.e.class, "Input_Receiver_FPS", 1.0f);
        if (testDisconnect || this.renderManager == null || (this.server != null && this.server.b())) {
            if (this.DEBUG_CLIENT) {
                DJILogHelper.getInstance().LOGD(TAG_Input, String.format("testDisconnect=%s, renderManager=%s", new Object[]{Boolean.valueOf(testDisconnect), this.renderManager}), false, false);
            }
        } else if (!this.isStop) {
            if (i3 >= 0 && i3 + i4 <= bArr.length) {
                this.sps_header = Arrays.copyOfRange(bArr, i3, i3 + i4);
            }
            if (i5 > 0 && i5 + i6 <= bArr.length) {
                this.pps_header = Arrays.copyOfRange(bArr, i5, i5 + i6);
            }
            if (this.DEBUG_CLIENT) {
                e.d(TAG_Input, "queueInputBuffer an input frame. frameNum=" + i2 + " frameIndex=" + j2 + " at " + System.currentTimeMillis());
            }
            this.client.a(bArr, i, i2, z, j2, j, i7, i8);
        } else if (this.DEBUG_CLIENT) {
            DJILogHelper.getInstance().LOGD(TAG_Input, String.format("isStop=%s", new Object[]{Boolean.valueOf(this.isStop)}), false, true);
        }
    }

    public void displayJpegFrame(byte[] bArr, int i, int i2) {
        if (this.surfaceDrawTo != null || this.renderManager == null) {
            e.b(TAG, "displayJpegFrame renderManager==null");
            return;
        }
        this.surfaceDrawTo = this.renderManager.a();
        if (this.jpegRenderHandler != null || this.context == null) {
            e.b(TAG, "displayJpegFrame context==null");
            return;
        }
        this.jpegRenderHandler = new Handler(this.context.getMainLooper());
        final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, new Options());
        if (decodeByteArray == null) {
            e.b(TAG, "displayJpegFrame bitmap decoding failed");
        } else {
            this.jpegRenderHandler.post(new Runnable(this) {
                final /* synthetic */ DJIVideoDecoder b;

                public void run() {
                    Canvas lockCanvas = this.b.surfaceDrawTo.lockCanvas(null);
                    if (lockCanvas == null) {
                        e.b(DJIVideoDecoder.TAG, "displayJpegFrame handler lockCanvas failed");
                    } else {
                        lockCanvas.drawBitmap(decodeByteArray, null, new Rect(0, 0, lockCanvas.getWidth(), lockCanvas.getHeight()), null);
                        this.b.surfaceDrawTo.unlockCanvasAndPost(lockCanvas);
                    }
                    decodeByteArray.recycle();
                }
            });
        }
    }

    public void ConnectStatus(int i) {
        if (i == 0) {
            DJILogHelper.getInstance().LOGD("", " ADB_CONN_ERR连接失败或者错误");
        }
        if (i == 1) {
            DJILogHelper.getInstance().LOGD("", " ADB_CONN_ACCEPT连接成功");
        }
    }

    public void pauseStatusCheck() {
        this.statusMonitor.f();
    }

    public void freshDecodeStatus(int i) {
        this.statusMonitor.d(i);
    }

    public void setConnectLosedelay(int i) {
        this.statusMonitor.a(i);
    }

    public void resumeStatusCheck() {
        this.statusMonitor.e();
    }

    public void debugLOG(String str) {
        DJILogHelper.getInstance().LOGD("", "JNI:" + str);
    }

    public int getVideoWidth() {
        return width;
    }

    public int getVideoHeight() {
        return height;
    }

    public void release() {
        this.isStop = true;
        this.statusMonitor.a();
        ServiceManager.getInstance().a(null);
        this.client.a();
        this.mCallback = null;
        dji.midware.util.h.a(TAG, "stopVideoDecoder()");
    }

    public void recvTimeout() {
        Log.i(TAG, "recvTimeout()");
    }

    public void setSurface(dji.midware.media.h.b.b bVar) {
        if (this.renderManager != null) {
            this.client.a();
            this.renderManager = null;
        }
        if (bVar != null) {
            this.renderManager = bVar;
            this.client.c();
            Log.e(TAG, "start DJIDecodeInoutThread() create");
        }
    }

    public void setVideoDataListener(dji.midware.media.k.b.a aVar) {
        synchronized (this.listenerSync) {
            this.listener = aVar;
            if (this.renderManager != null) {
                this.renderManager.a(aVar);
            }
        }
    }

    public boolean isSurfaceAvailable() {
        return this.renderManager != null;
    }
}
