package dji.midware.media.e;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetVideoParams;
import dji.midware.data.model.P3.DataCameraGetVideoParams.FPS_Drone;
import dji.midware.data.model.P3.DataCameraGetVideoParams.Resolution_Drone;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.media.d;
import dji.midware.media.e;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.thirdparty.a.c;
import java.util.Date;

public class g {
    private static final String a = "VideoRecordInfoSetter";
    private f b = null;
    private String c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    public g(f fVar, Date date, int i, int i2, String str, String str2) {
        this.c = str;
        this.b = fVar;
        this.b.a(date);
        this.b.b("");
        this.b.c(DJIUsbAccessoryReceiver.c);
        this.b.d("");
        this.b.m(0);
        this.b.l(-1);
        this.b.f(i);
        this.b.g(i2);
        this.b.h(d.c());
        this.b.o(0);
        this.b.h(str2);
        this.b.c(Integer.valueOf(1));
        fVar.e(str);
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
        }
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
        }
        onEventBackgroundThread(i.getInstance().c());
        DataCameraGetVideoParams.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.onEventBackgroundThread((DataCameraGetVideoParams) obj);
            }

            public void onFailure(a aVar) {
            }
        });
        c.a().a(this);
        b();
        e.d(a, "initilized");
    }

    public void a() {
        c.a().d(this);
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (!this.i) {
            this.i = true;
            e.d(a, dji.pilot.fpv.d.d.dI);
            this.b.a(productType);
            b();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (!this.e) {
            this.e = true;
            e.d(a, "DataCameraGetPushStateInfo");
            this.b.a(dataCameraGetPushStateInfo.getCameraType());
            b();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (!this.d) {
            this.d = true;
            e.d(a, "DataCameraGetPushShotParams");
            this.b.a(dataCameraGetPushShotParams.getApertureSize());
            this.b.a(dataCameraGetPushShotParams.getShutterString());
            this.b.b(dataCameraGetPushShotParams.getWhiteBalance());
            this.b.a(dataCameraGetPushShotParams.getExposureMode());
            this.b.c(dataCameraGetPushShotParams.getISO());
            b();
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!this.g && dataOsdGetPushCommon.getGpsNum() > 0) {
            this.g = true;
            e.d(a, "DataOsdGetPushCommon");
            this.b.c((double) dataOsdGetPushCommon.getHeight());
            this.b.d((double) dataOsdGetPushCommon.getHeight());
            this.b.b(dataOsdGetPushCommon.getLatitude());
            this.b.a(dataOsdGetPushCommon.getLongitude());
            b();
        }
    }

    public void onEventBackgroundThread(DataCameraGetVideoParams dataCameraGetVideoParams) {
        if (!this.f) {
            this.f = true;
            e.d(a, "DataCameraGetVideoParams");
            this.b.k(dataCameraGetVideoParams.getFolderId());
            this.b.j(dataCameraGetVideoParams.getFileId());
            this.b.a(dataCameraGetVideoParams.getUuid());
            this.b.i(FPS_Drone.find(dataCameraGetVideoParams.getFps()).fps());
            int ratio = dataCameraGetVideoParams.getRatio();
            this.b.n(ratio);
            e.d(a, "ratio=" + ratio);
            Resolution_Drone find = Resolution_Drone.find(ratio);
            if (!find.equals(Resolution_Drone.OTHER)) {
                this.b.d(find.width());
                this.b.e(find.height());
            }
            b();
        }
    }

    public synchronized void b() {
        e.d(a, "now start to save the file");
        this.b.f(e.a() + this.c + ".info");
        e.d(a, "the file has been saved");
    }

    public void a(int i, int i2) {
        DJILogHelper.getInstance().LOGD("", "add sync point");
        this.b.a(Integer.valueOf(i));
        this.b.b(Integer.valueOf(i2));
        b();
    }

    public void a(int i) {
        this.b.l(i);
        b();
    }
}
