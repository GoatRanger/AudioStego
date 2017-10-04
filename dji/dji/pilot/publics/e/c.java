package dji.pilot.publics.e;

import dji.log.DJILogHelper;
import dji.logic.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataRcGetPushConnectStatus;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.pilot.fpv.d.b;

public class c {
    public static boolean a() {
        ProductType c = i.getInstance().c();
        return c == ProductType.litchiC || c == ProductType.litchiS || c == ProductType.P34K || a.c(c) || a(DataCameraGetPushStateInfo.getInstance().getCameraType());
    }

    public static boolean a(CameraType cameraType) {
        return b.j(cameraType) || cameraType == CameraType.DJICameraTypeCV600 || CameraType.DJICameraTypeFC6310 == cameraType || cameraType == CameraType.DJICameraTypeGD600;
    }

    public static MODE b() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.P34K || a.c(c) || (!d.a(c) && a(DataCameraGetPushStateInfo.getInstance().getCameraType()))) {
            return MODE.PLAYBACK;
        }
        DJILogHelper.getInstance().LOGD("", "pbversion:" + DataCameraGetPushStateInfo.getInstance().getVerstion(), false, true);
        if (d()) {
            return MODE.PLAYBACK;
        }
        return MODE.NEW_PLAYBACK;
    }

    public static boolean c() {
        if (!ServiceManager.getInstance().isRemoteOK() || d() || e() || !f() || g()) {
            return false;
        }
        return true;
    }

    public static boolean d() {
        return DataCameraGetPushStateInfo.getInstance().getVerstion() < 1;
    }

    public static boolean e() {
        return !DataDm368_gGetPushCheckStatus.getInstance().getHDMIConnectStatus();
    }

    public static boolean f() {
        return dji.pilot.c.d.b != DataRcSetMaster.MODE.b;
    }

    public static boolean g() {
        return DataRcGetPushConnectStatus.getInstance().isSlaveConnected();
    }
}
