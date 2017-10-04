package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.CameraSSDCapacity;
import dji.common.camera.CameraSSDOperationState;
import dji.common.camera.CameraSSDRawVideoResolutionAndFrameRate;
import dji.common.camera.CameraUtils;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.error.DJIError;
import dji.common.util.DJILensFeatureUtils;
import dji.common.util.LatchHelper;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.c.b;

public class f extends b {
    protected LatchHelper H = LatchHelper.getInstance();

    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        this.t = CameraUtils.buildApertureMap();
        this.s = new DJILensFeatureUtils();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        c();
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    protected void c() {
        super.c();
        onEventBackgroundThread(DataCameraGetPushRawParams.getInstance());
        onEventBackgroundThread(DataCameraGetPushShotInfo.getInstance());
    }

    public void onEventBackgroundThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        if (B() && dataCameraGetPushRawParams != null) {
            CameraSSDOperationState find = CameraSSDOperationState.find(dataCameraGetPushRawParams.getDiskStatusValue());
            boolean isDiskConnected = dataCameraGetPushRawParams.isDiskConnected();
            CameraSSDCapacity find2 = CameraSSDCapacity.find(dataCameraGetPushRawParams.getDiskCapacity());
            int diskAvailableTime = dataCameraGetPushRawParams.getDiskAvailableTime();
            long availableCapacity = dataCameraGetPushRawParams.getAvailableCapacity();
            CameraVideoResolution cameraVideoResolution = CameraVideoResolution.Unknown;
            CameraVideoFrameRate cameraVideoFrameRate = CameraVideoFrameRate.Unknown;
            int resolution = dataCameraGetPushRawParams.getResolution();
            int fps = dataCameraGetPushRawParams.getFps();
            switch (resolution) {
                case 4:
                    cameraVideoResolution = CameraVideoResolution.Resolution_1280x720;
                    break;
                case 10:
                    cameraVideoResolution = CameraVideoResolution.Resolution_1920x1080;
                    break;
                case 16:
                    cameraVideoResolution = CameraVideoResolution.Resolution_3840x2160;
                    break;
                case 22:
                    cameraVideoResolution = CameraVideoResolution.Resolution_4096x2160;
                    break;
                case 24:
                    cameraVideoResolution = CameraVideoResolution.Resolution_2704X1520;
                    break;
            }
            switch (fps) {
                case 1:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_24fps;
                    break;
                case 2:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_25fps;
                    break;
                case 3:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_30fps;
                    break;
                case 4:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_48fps;
                    break;
                case 5:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_50fps;
                    break;
                case 6:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_60fps;
                    break;
                case 7:
                    cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_120fps;
                    break;
            }
            CameraSSDRawVideoResolutionAndFrameRate cameraSSDRawVideoResolutionAndFrameRate = new CameraSSDRawVideoResolutionAndFrameRate();
            cameraSSDRawVideoResolutionAndFrameRate.setResolution(cameraVideoResolution);
            cameraSSDRawVideoResolutionAndFrameRate.setFrameRate(cameraVideoFrameRate);
            a(find, c(dji.sdksharedlib.b.b.bB));
            a(Boolean.valueOf(isDiskConnected), c(dji.sdksharedlib.b.b.bC));
            a(find2, c(dji.sdksharedlib.b.b.bD));
            a(Integer.valueOf(diskAvailableTime), c(dji.sdksharedlib.b.b.bE));
            a(Long.valueOf(availableCapacity), c(dji.sdksharedlib.b.b.bF));
            a(cameraSSDRawVideoResolutionAndFrameRate, c(dji.sdksharedlib.b.b.bG));
        }
    }

    protected boolean y() {
        return true;
    }

    protected boolean A() {
        return true;
    }

    protected boolean z() {
        return true;
    }

    protected boolean C() {
        return true;
    }

    protected boolean s() {
        return false;
    }

    protected int a(int i) {
        if (!s()) {
            return i;
        }
        final String[] strArr = new String[1];
        this.H.setUpLatch(1);
        e(new e(this) {
            final /* synthetic */ f b;

            public void a(Object obj) {
                strArr[0] = (String) obj;
                this.b.H.countDownLatch();
            }

            public void a(DJIError dJIError) {
            }
        });
        this.H.waitForLatch(5);
        if (strArr[0] == null || strArr[0].compareTo("01.27.51.34") > 0) {
            return i;
        }
        return 11 - i;
    }
}
