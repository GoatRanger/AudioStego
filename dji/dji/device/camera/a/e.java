package dji.device.camera.a;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.midware.e.d;
import dji.pilot.set.h;
import dji.thirdparty.a.c;

public class e {
    private static final String j = "LonganVideoRecordModeController";
    private static e k = null;
    private static final int l = 1;
    int[] a;
    int[] b;
    int c = 0;
    int d = 50;
    int e = 0;
    int f = 1;
    int g = 0;
    int h = 0;
    Handler i = new Handler(new Callback(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d(e.j, "MSG_POST_EVENT");
                    c.a().e(this.a);
                    break;
            }
            return false;
        }
    });
    private a m = a.AUTO;
    private Context n;
    private int o;
    private int p;

    public enum a {
        NONE,
        AUTO,
        TIMELAPSE,
        SLOWMOTION,
        SLOWMOTION_720,
        SLOWMOTION_1080
    }

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (k == null) {
                k = new e();
            }
            eVar = k;
        }
        return eVar;
    }

    e() {
    }

    public void a(Context context) {
        if (!c.a().c(this)) {
            c.a().a(this);
            this.n = context;
            this.a = dji.pilot.set.longan.a.a;
            this.b = dji.pilot.set.longan.a.b;
            if (DataCameraGetPushShotParams.getInstance().isGetted()) {
                onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
                DJILogHelper.getInstance().LOGD("init log", "record init post");
            }
        }
    }

    public void a() {
        c.a().d(this);
        this.m = null;
        k = null;
    }

    public a b() {
        return this.m;
    }

    public e a(a aVar) {
        if (aVar != null) {
            DJILogHelper.getInstance().LOGI(j, "set mode" + aVar);
            DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
            a aVar2 = this.m;
            int videoFormat = instance.getVideoFormat();
            int videoFps = instance.getVideoFps();
            if (aVar == a.SLOWMOTION_1080 || aVar == a.SLOWMOTION_720) {
                a(videoFormat, videoFps);
                if (aVar == a.SLOWMOTION_1080) {
                    a(10, 7, aVar);
                } else if (aVar == a.SLOWMOTION_720) {
                    a(4, 8, aVar);
                }
            } else {
                b(aVar);
            }
        }
        return this;
    }

    public e a(int i) {
        this.c = i;
        return this;
    }

    public e b(int i) {
        this.d = i;
        return this;
    }

    public e c(int i) {
        this.e = i;
        return this;
    }

    public e d(int i) {
        this.f = i;
        return this;
    }

    public e a(short s) {
        this.g = s;
        return this;
    }

    public e b(short s) {
        this.h = s;
        return this;
    }

    public e a(int i, int i2, int i3) {
        this.f = i;
        this.g = i2;
        this.h = i3;
        return this;
    }

    private void a(int i, int i2) {
        h.a(this.n, "petyrzhan_longan_last_ratio", i);
        h.a(this.n, "petyrzhan_longan_last_fps", i2);
    }

    private int e() {
        return h.a(this.n, "petyrzhan_longan_last_ratio");
    }

    private int f() {
        return h.a(this.n, "petyrzhan_longan_last_fps");
    }

    private void a(int i, int i2, final a aVar) {
        Log.d(j, "set video format####ratio:" + i + "fps:" + i2);
        DataCameraSetVideoFormat dataCameraSetVideoFormat = new DataCameraSetVideoFormat();
        dataCameraSetVideoFormat.a();
        dataCameraSetVideoFormat.a(i);
        dataCameraSetVideoFormat.b(i2);
        dataCameraSetVideoFormat.start(new d(this) {
            final /* synthetic */ e b;

            public void onSuccess(Object obj) {
                Log.i(e.j, "success");
                this.b.b(aVar);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.i(e.j, "onFailure" + aVar);
            }
        });
    }

    private void b(final a aVar) {
        if (aVar != a.NONE) {
            DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
            switch (aVar) {
                case AUTO:
                    dataCameraSetVideoRecordMode.a(0, 0, 0);
                    break;
                case TIMELAPSE:
                    dataCameraSetVideoRecordMode.a(1, this.d, this.e).a(this.c).c(this.f).d(this.g).f(this.h).b(dji.pilot.set.a.g(this.n));
                    DJILogHelper.getInstance().LOGD(j, "DJIMethod : setCameraVideoRecordMode (302)mode" + aVar + "index:" + this.f + "mTimelapseMode:" + this.c + "interval:" + this.d + "duration" + this.e + "mYawangle:" + this.g + "pitchangle:" + this.h, false, true);
                    break;
                case SLOWMOTION_720:
                    dataCameraSetVideoRecordMode.a(2, 0, 0);
                    break;
                case SLOWMOTION_1080:
                    dataCameraSetVideoRecordMode.a(2, 0, 0);
                    break;
            }
            dataCameraSetVideoRecordMode.a(new d(this) {
                final /* synthetic */ e b;

                public void onSuccess(Object obj) {
                    Log.i(e.j, "DataCameraSetVideoRecordMode success--" + aVar);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.i(e.j, "DataCameraSetVideoRecordMode fails:" + aVar + aVar);
                }
            }, 300, 5);
        }
    }

    private void g() {
        int e = e();
        int f = f();
        a(e, f, this.m);
        Log.d(j, "set setOptimseResulution ####ratio:" + e + "fps:" + f);
    }

    private boolean b(int i, int i2) {
        int[] iArr;
        if (DataCameraGetPushShotParams.getInstance().getVideoStandard() == 1) {
            iArr = this.b;
        } else {
            iArr = this.a;
        }
        for (int i3 : r0) {
            if (i3 == (i * 10) + i2) {
                return true;
            }
        }
        return false;
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        h();
    }

    public void onEventMainThread() {
    }

    private void h() {
        DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
        int videoFormat = instance.getVideoFormat();
        int videoFps = instance.getVideoFps();
        int videoRecordMode = instance.getVideoRecordMode();
        a aVar = this.m;
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            this.m = a.NONE;
        } else if (videoRecordMode == 0) {
            this.m = a.AUTO;
        } else if (videoRecordMode == 1) {
            this.m = a.TIMELAPSE;
        } else if (videoRecordMode == 2) {
            this.m = a.SLOWMOTION_1080;
            if (videoFormat == 4 && videoFps == 8) {
                this.m = a.SLOWMOTION_720;
            } else if (videoFormat == 10 && videoFps == 7) {
                this.m = a.SLOWMOTION_1080;
            }
        }
        if (this.i.hasMessages(1)) {
            this.i.removeMessages(1);
            aVar = a.NONE;
        }
        if (this.m != aVar) {
            Log.d(j, "push mode:" + this.m + "old mode:" + aVar);
            this.i.sendEmptyMessageDelayed(1, 50);
        }
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }
}
