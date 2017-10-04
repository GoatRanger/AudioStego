package dji.pilot.fpv.camera.a;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.e.d;

public class a {
    private static final boolean a = false;
    private static final String b = "Camera";

    public static final void a(String str, String str2) {
        if (d.a(str)) {
            str = "Camera";
        }
        DJILogHelper.getInstance().LOGD(str, str2, false, true);
    }

    public static boolean a(SDCardState sDCardState) {
        return SDCardState.Invalid == sDCardState || SDCardState.Illegal == sDCardState || SDCardState.Unknow == sDCardState;
    }

    public static boolean a(CameraType cameraType) {
        return b.j(cameraType) || CameraType.DJICameraTypeCV600 == cameraType;
    }

    public static boolean b(CameraType cameraType) {
        return CameraType.DJICameraTypeFC260 == cameraType || CameraType.DJICameraTypeFC300S == cameraType || CameraType.DJICameraTypeFC300XW == cameraType || CameraType.DJICameraTypeFC220S == cameraType || CameraType.DJICameraTypeFC6310 == cameraType || a(cameraType);
    }

    public static boolean a() {
        int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
        if (((videoFormat >= 14 && videoFormat <= 23) || videoFormat == 28 || videoFormat == 29) && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            return true;
        }
        return false;
    }

    public static boolean c(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        return cameraType == CameraType.DJICameraTypeFC550Raw;
    }

    public static boolean d(CameraType cameraType) {
        return CameraType.DJICameraTypeFC6310 == cameraType;
    }

    private a() {
    }

    public static boolean b() {
        CameraType b = i.getInstance().b();
        return b.c(b) && b != CameraType.DJICameraTypeGD600;
    }

    public static boolean c() {
        return i.getInstance().b() == CameraType.DJICameraTypeGD600;
    }
}
