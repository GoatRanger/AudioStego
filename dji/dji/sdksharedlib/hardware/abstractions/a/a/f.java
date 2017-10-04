package dji.sdksharedlib.hardware.abstractions.a.a;

import android.util.Log;
import dji.common.LBAirLinkEncodeMode;
import dji.common.VideoDataChannel;
import dji.common.error.DJIAirLinkError;
import dji.common.error.DJIError;
import dji.common.util.LatchHelper;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.usb.P3.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.thirdparty.a.c;

public class f {
    private static final String d = "DJILB2Helper";
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int k = 3;
    public LBAirLinkEncodeMode a;
    public int b;
    public int c;
    private b e;
    private final int f = -1;
    private int j;
    private final LatchHelper l = LatchHelper.getInstance();
    private DataDm368GetPushStatus m = DataDm368GetPushStatus.getInstance();

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[LBAirLinkEncodeMode.values().length];

        static {
            try {
                a[LBAirLinkEncodeMode.Single.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[LBAirLinkEncodeMode.Dual.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public f(b bVar) {
        this.e = bVar;
    }

    public void a() {
        c.a().a(this);
        this.b = -1;
        this.c = -1;
        this.e.a = VideoDataChannel.Unknown;
        this.j = 0;
        d();
    }

    public void b() {
        c.a().d(this);
    }

    public void onEventBackgroundThread(b bVar) {
        if (!this.e.c()) {
            return;
        }
        if (bVar == b.b) {
            if (this.a != LBAirLinkEncodeMode.Single) {
                a(LBAirLinkEncodeMode.Single);
            }
        } else if (bVar == b.c && this.a != LBAirLinkEncodeMode.Dual) {
            a(LBAirLinkEncodeMode.Dual);
        }
    }

    public void a(LBAirLinkEncodeMode lBAirLinkEncodeMode) {
        if (lBAirLinkEncodeMode != this.a) {
            this.a = lBAirLinkEncodeMode;
            if (this.e != null) {
                switch (AnonymousClass3.a[lBAirLinkEncodeMode.ordinal()]) {
                    case 1:
                        Log.i(d, "in onEncodeModeChange Single");
                        f();
                        return;
                    case 2:
                        Log.i(d, "in onEncodeModeChange Dual");
                        g();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void f() {
        if (!(this.e.a == VideoDataChannel.HDGimbal || this.e.a == VideoDataChannel.FPVCamera)) {
            this.e.a(VideoDataChannel.HDGimbal);
        }
        Log.i(d, "encode mode change to single");
        if (this.b != -1) {
            this.a = LBAirLinkEncodeMode.Single;
            e();
            Log.i(d, "only update video channel");
        } else if (h() == null) {
            e();
            Log.i(d, "lb percentage refresh and update video channel");
        }
    }

    private void g() {
        if (!(this.e.a == VideoDataChannel.AV || this.e.a == VideoDataChannel.HDMI)) {
            this.e.a(VideoDataChannel.AV);
        }
        Log.i(d, "encode mode change to dual");
        this.e.b(100, new e(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(Object obj) {
            }

            public void a(DJIError dJIError) {
            }
        });
        this.a = LBAirLinkEncodeMode.Dual;
        e();
    }

    public void a(int i) {
        Log.i(d, "in onLBPercentChange");
        if (i != this.b) {
            this.b = i;
            e();
        }
    }

    public void b(int i) {
        if (i != this.c) {
            this.c = i;
            e();
        }
    }

    public DJIError c() {
        Log.i(d, "in refresh cache");
        DJIError[] dJIErrorArr = new DJIError[1];
        if (this.e == null) {
            return DJIAirLinkError.COMMON_DISCONNECTED;
        }
        if (!this.e.b()) {
            return DJIAirLinkError.COMMON_UNSUPPORTED;
        }
        if (this.e.c()) {
            this.a = LBAirLinkEncodeMode.find(this.m.getEncodeMode());
            this.c = this.m.getDualEncodeModePercentage();
        } else {
            this.a = LBAirLinkEncodeMode.Single;
        }
        if (this.a == LBAirLinkEncodeMode.Single) {
            Log.i(d, "in refresh cache 1");
            dJIErrorArr[0] = h();
            if (dJIErrorArr[0] != null) {
                Log.i(d, "in refresh cache 2");
                return dJIErrorArr[0];
            }
        }
        Log.i(d, "in refresh cache 3");
        dJIErrorArr[0] = h();
        if (dJIErrorArr[0] != null) {
            Log.i(d, "in refresh cache 2");
            return dJIErrorArr[0];
        }
        return dJIErrorArr[0];
    }

    private DJIError h() {
        Log.i(d, "in refresh LB percent cache");
        if (this.e == null) {
            return null;
        }
        final DJIError[] dJIErrorArr = new DJIError[]{null};
        this.l.setUpLatch(1);
        this.e.h(new e(this) {
            final /* synthetic */ f b;

            public void a(Object obj) {
                if (obj instanceof Integer) {
                    this.b.b = ((Integer) obj).intValue() / 10;
                    this.b.l.countDownLatch();
                }
            }

            public void a(DJIError dJIError) {
                dJIErrorArr[0] = dJIError;
                this.b.l.countDownLatch();
            }
        });
        this.l.waitForLatch(5);
        return dJIErrorArr[0];
    }

    public void d() {
        if (this.e != null) {
            Log.i(d, "in refreshCacheAndUpdateVideoChannel()");
            DJIError c = c();
            if (c != null && this.j < 3) {
                this.j++;
                d();
            } else if (c != null && this.j >= 3) {
                this.j = 0;
            } else if (c == null) {
                Log.i(d, "before updateVideoChannel");
                e();
            }
        }
    }

    public void e() {
        if (this.e != null) {
            Log.i(d, "in updateVideoChannel");
            Log.i(d, "current encode mode: " + this.a);
            Log.i(d, "current dual percent: " + this.c);
            Log.i(d, "current single percent: " + this.b);
            Log.i(d, "current video channel: " + this.e.a);
            VideoDataChannel videoDataChannel = this.e.a;
            if (this.a == LBAirLinkEncodeMode.Single) {
                if (!((videoDataChannel != VideoDataChannel.AV && videoDataChannel != VideoDataChannel.HDMI && videoDataChannel != VideoDataChannel.Unknown) || this.b == 0 || this.b == 10)) {
                    this.e.a(VideoDataChannel.FPVCamera);
                    Log.i(d, "in updateVideoChannel 1");
                }
                if (this.b == 0) {
                    this.e.a(VideoDataChannel.HDGimbal);
                    Log.i(d, "in updateVideoChannel 2");
                } else if (this.b == 10) {
                    this.e.a(VideoDataChannel.FPVCamera);
                    Log.i(d, "in updateVideoChannel 3");
                }
            } else if (this.a == LBAirLinkEncodeMode.Dual) {
                if (!((videoDataChannel != VideoDataChannel.FPVCamera && videoDataChannel != VideoDataChannel.HDGimbal && videoDataChannel != VideoDataChannel.Unknown) || this.c == 0 || this.c == 10)) {
                    this.e.a(VideoDataChannel.AV);
                    Log.i(d, "in updateVideoChannel 4");
                }
                if (this.c == 0) {
                    Log.i(d, "cache 0, set to AV");
                    this.e.a(VideoDataChannel.AV);
                    Log.i(d, "in updateVideoChannel 5");
                } else if (this.c == 10) {
                    Log.i(d, "cache 10, set to HDMI");
                    this.e.a(VideoDataChannel.HDMI);
                    Log.i(d, "in updateVideoChannel 6");
                }
            }
            Log.i(d, "in updateVideoChannel current channel:" + this.e.a);
        }
    }
}
