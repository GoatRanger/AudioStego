package dji.device.camera.settings;

import dji.device.camera.view.focus.a.c;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetIso;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetAperture;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetPhotoMode;
import dji.midware.data.model.P3.DataCameraSetShutterSpeed;
import dji.midware.data.model.P3.DataCameraSetWhiteBalance;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.d;
import dji.sdksharedlib.b.b;

public class a {
    private static final String b = "DJICameraSettingManager";
    private static a c = null;
    protected TYPE a = TYPE.SINGLE;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public void a(final int i) {
        DataCameraSetIso dataCameraSetIso = new DataCameraSetIso();
        dataCameraSetIso.a(true);
        dataCameraSetIso.a(DataCameraGetIso.TYPE.find(i));
        dataCameraSetIso.start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                DataCameraGetPushStateInfo.getInstance().getMode();
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraSetISO success,iso send value:" + i + "get value:" + DataCameraGetPushShotParams.getInstance().getISO(), false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraISO fail " + aVar, false, true);
            }
        });
    }

    public void b(int i) {
        DataCameraSetWhiteBalance dataCameraSetWhiteBalance = new DataCameraSetWhiteBalance();
        dataCameraSetWhiteBalance.a(i);
        dataCameraSetWhiteBalance.start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.b, "succeed", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "sendWBCmd fail " + aVar, false, true);
            }
        });
    }

    public void a(boolean z, final int i, final int i2) {
        DataCameraSetShutterSpeed dataCameraSetShutterSpeed = new DataCameraSetShutterSpeed();
        dataCameraSetShutterSpeed.a(z, i, i2);
        dataCameraSetShutterSpeed.start(new d(this) {
            final /* synthetic */ a c;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraShutter success, value:" + i + "decimal:" + i2, false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraShutter fail " + aVar, false, true);
            }
        });
    }

    public void c(final int i) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a(b.J);
        dataBaseCameraSetting.a(i);
        dataBaseCameraSetting.a(0, 1);
        dataBaseCameraSetting.start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraEV success,value:" + i, false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraEV fail " + aVar, false, true);
            }
        });
    }

    public void d(final int i) {
        if (c.getInstance().b() == 0) {
            c.getInstance().a(0);
        } else {
            new DataCameraSetAperture().a((short) i).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(a.b, "sendApertureCmd success,value:" + i, false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(a.b, "sendApertureCmd fail " + aVar + i, false, true);
                }
            });
        }
    }

    public void e(final int i) {
        DJILogHelper.getInstance().LOGD("", "DataCameraMode " + i, false, true);
        new DataCameraSetExposureMode().a(i).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraMode success value:" + i, false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "DataCameraMode fail " + aVar, false, true);
            }
        });
    }

    public void a(TYPE type, int i, int i2) {
        DJILogHelper.getInstance().LOGD(b, "type:" + type + "number:" + i + "interval:" + i2);
        DataSpecialControl.getInstance().setPhotoType(type, i, i2).start(20);
    }

    public void a(final TYPE type, int i, int i2, int i3) {
        DataCameraSetPhotoMode.getInstance().a(type).a(i).c(i2).d(i3).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode success value=" + type.a(), false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode fail " + aVar, false, true);
            }
        });
    }

    public void a(final MODE mode) {
        DataCameraSetMode.getInstance().a(mode).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.b, "switch camera mode succeed:" + mode);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.b, "current mode:" + DataCameraGetPushStateInfo.getInstance().getMode());
                DJILogHelper.getInstance().LOGD(a.b, "switch camera mode failed:" + aVar);
            }
        });
    }

    public void b(boolean z, int i, int i2) {
        DJILogHelper.getInstance().LOGD(b, "isstart" + z + "type" + i + "interval" + i2);
        DataSpecialControl.getInstance().setRecordType(z, i, i2).start(20);
    }

    public void c(boolean z, int i, int i2) {
    }
}
