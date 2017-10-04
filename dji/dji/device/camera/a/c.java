package dji.device.camera.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushShotParams$PanoMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetPanoMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetPhotoMode;
import dji.midware.data.model.P3.DataCameraSetTimeParams;
import dji.midware.e.d;

public class c {
    private static final String i = "LonganPhotoTypeManager";
    private static c j = null;
    private static final int k = 1;
    private static final int l = 2;
    b a = b.SINGLE;
    a b = a.SINGLE_0s;
    int c = 1;
    int d = 0;
    int e = 1;
    int f = 1;
    boolean g = false;
    Handler h = new Handler(new Callback(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.g = false;
                    break;
                case 2:
                    dji.thirdparty.a.c.a().e(this.a);
                    break;
            }
            return false;
        }
    });

    public enum a {
        SINGLE_0s,
        SINGLE_5s,
        SINGLE_10s,
        SINGLE_HDR,
        BURST_3,
        BURST_5,
        BURST_7,
        AEB_3,
        AEB_5,
        PANO_AUTO,
        PANO_SELFIE,
        PANO_MANU,
        PANO_BALL,
        PANO_AUTO180,
        PANO_VERTICAL,
        PANO_SECTORIAL,
        INTERVAL_3s,
        INTERVAL_5s,
        INTERVAL_10s,
        INTERVAL_30s,
        TIMELAPSE,
        STOP
    }

    public enum b {
        NOT_PHOTOING,
        SINGLE,
        BURST,
        AEB,
        PANO,
        INTERVAL,
        TIMELAPSE,
        STOP
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (j == null) {
                j = new c();
            }
            cVar = j;
        }
        return cVar;
    }

    private c() {
    }

    public void a() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
            if (DataCameraGetPushShotParams.getInstance().isGetted()) {
                onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
            }
        }
    }

    public void b() {
        j = null;
        dji.thirdparty.a.c.a().d(this);
    }

    public void a(final int i, int i2) {
        if (i < 0) {
            this.g = true;
            this.h.sendEmptyMessageDelayed(1, 100);
            DataCameraGetPushShotParams$PanoMode dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Auto360;
            if (i == -1) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Auto360;
            } else if (i == -2) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Ball;
            } else if (i == -3) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Self;
            } else if (i == -4) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Manual;
            } else if (i == -5) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.Auto180;
            } else if (i == -6) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.VERTICAL;
            } else if (i == -7) {
                dataCameraGetPushShotParams$PanoMode = DataCameraGetPushShotParams$PanoMode.SECTORIAL;
            }
            DataCameraSetPhotoMode.getInstance().a(b(this.a)).start(new d(this) {
                final /* synthetic */ c c;

                public void onSuccess(Object obj) {
                    DataCameraSetPanoMode.getInstance().a(dataCameraGetPushShotParams$PanoMode).start(new d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            DJILogHelper.getInstance().LOGD(c.i, "set pano mode succedd!" + i);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            DJILogHelper.getInstance().LOGD(c.i, "set pano mode failed!" + aVar);
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
            return;
        }
        int i3;
        this.c = i;
        this.d = i2;
        if (this.a == b.INTERVAL) {
            i3 = 0;
        } else if (this.a == b.TIMELAPSE) {
            i3 = 2;
        } else {
            i3 = 0;
        }
        TYPE type = null;
        if (i2 >= 0) {
            type = b(this.a);
        } else if (i2 == -1) {
            type = TYPE.HDR;
            this.d = 0;
        }
        DataCameraSetPhotoMode.getInstance().a(type).a(this.c).c(this.c).d(this.d).b(i3).start(new d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public b c() {
        return this.a;
    }

    public a d() {
        return this.b;
    }

    public c a(b bVar) {
        if (this.a != bVar) {
            this.a = bVar;
            dji.thirdparty.a.c.a().e(bVar);
        }
        return this;
    }

    public int e() {
        if (this.a == b.AEB) {
            return this.f;
        }
        return this.c;
    }

    public void a(int i) {
        if (this.c != i) {
            this.c = i;
            dji.thirdparty.a.c.a().e(this);
        }
    }

    public int f() {
        return this.d;
    }

    public void b(int i) {
        if (this.d != i) {
            this.d = i;
            dji.thirdparty.a.c.a().e(this);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (!this.g) {
            if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
                TYPE photoType = dataCameraGetPushShotParams.getPhotoType();
                int continuous = dataCameraGetPushShotParams.getContinuous();
                int timeParamsNum = dataCameraGetPushShotParams.getTimeParamsNum();
                this.f = dataCameraGetPushShotParams.getAEBNumber();
                this.d = dataCameraGetPushShotParams.getTimeParamsPeriod();
                if (photoType == TYPE.TIME || photoType == TYPE.STOP) {
                    this.c = timeParamsNum;
                } else if (photoType == TYPE.BURST || photoType == TYPE.AEB) {
                    this.c = continuous;
                }
                b a = a(photoType);
                a aVar = a.SINGLE_0s;
                if (photoType == TYPE.SINGLE) {
                    aVar = a.SINGLE_0s;
                    this.d = 0;
                } else if (photoType == TYPE.HDR) {
                    aVar = a.SINGLE_HDR;
                    this.d = 0;
                } else if (a == b.SINGLE && this.d == 5) {
                    aVar = a.SINGLE_5s;
                } else if (a == b.SINGLE && this.d == 10) {
                    aVar = a.SINGLE_10s;
                } else if (a == b.BURST && this.c == 3) {
                    aVar = a.BURST_3;
                } else if (a == b.BURST && this.c == 5) {
                    aVar = a.BURST_5;
                } else if (a == b.BURST && this.c == 7) {
                    aVar = a.BURST_7;
                } else if (a == b.AEB && this.f == 3) {
                    aVar = a.AEB_3;
                } else if (a == b.AEB && this.f == 5) {
                    aVar = a.AEB_5;
                } else if (a == b.INTERVAL && this.d == 3) {
                    aVar = a.INTERVAL_3s;
                } else if (a == b.INTERVAL && this.d == 5) {
                    aVar = a.INTERVAL_5s;
                } else if (a == b.INTERVAL && this.d == 10) {
                    aVar = a.INTERVAL_10s;
                } else if (a == b.INTERVAL && this.d == 30) {
                    aVar = a.INTERVAL_30s;
                } else if (a == b.PANO) {
                    DataCameraGetPushShotParams$PanoMode panoMode = dataCameraGetPushShotParams.getPanoMode();
                    if (panoMode == DataCameraGetPushShotParams$PanoMode.Auto360) {
                        aVar = a.PANO_AUTO;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.Ball) {
                        aVar = a.PANO_BALL;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.Self) {
                        aVar = a.PANO_SELFIE;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.Manual) {
                        aVar = a.PANO_MANU;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.Auto180) {
                        aVar = a.PANO_AUTO180;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.VERTICAL) {
                        aVar = a.PANO_VERTICAL;
                    } else if (panoMode == DataCameraGetPushShotParams$PanoMode.SECTORIAL) {
                        aVar = a.PANO_SECTORIAL;
                    }
                } else if (a == b.TIMELAPSE) {
                    aVar = a.TIMELAPSE;
                }
                if (aVar != this.b || this.a != a) {
                    this.a = a;
                    this.b = aVar;
                    if (this.h.hasMessages(2)) {
                        this.h.removeMessages(2);
                    }
                    this.h.sendEmptyMessageDelayed(2, 200);
                }
            } else if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD && this.a != b.NOT_PHOTOING) {
                this.a = b.NOT_PHOTOING;
                if (this.h.hasMessages(2)) {
                    this.h.removeMessages(2);
                }
                this.h.sendEmptyMessageDelayed(2, 200);
            }
        }
    }

    public void onEventBackgroundThread(a aVar) {
        if (aVar.d() == dji.device.camera.a.a.a.RECORD) {
            this.a = b.NOT_PHOTOING;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
    }

    public TYPE b(b bVar) {
        switch (bVar) {
            case SINGLE:
                if (this.d != 0) {
                    return TYPE.TIME;
                }
                return TYPE.SINGLE;
            case BURST:
                return TYPE.BURST;
            case AEB:
                return TYPE.AEB;
            case PANO:
                return TYPE.APP_FULLVIEW;
            case INTERVAL:
                return TYPE.TIME;
            case TIMELAPSE:
                return TYPE.TIME;
            default:
                return TYPE.SINGLE;
        }
    }

    public b a(TYPE type) {
        switch (type) {
            case HDR:
            case SINGLE:
                return b.SINGLE;
            case BURST:
                return b.BURST;
            case AEB:
                return b.AEB;
            case APP_FULLVIEW:
                return b.PANO;
            case STOP:
            case TIME:
                if (DataCameraGetPushShotParams.getInstance().getTimeParamsType() == DataCameraSetTimeParams.TYPE.Timelapse.a()) {
                    return b.TIMELAPSE;
                }
                if (this.c == 1) {
                    return b.SINGLE;
                }
                return b.INTERVAL;
            default:
                return b.SINGLE;
        }
    }
}
