package dji.logic.f;

import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo$ShotFDType;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;

public class b {
    private static final String a = "DJICameraSupportUtil";

    public static boolean a(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) {
            return true;
        }
        return false;
    }

    public static boolean b(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        return cameraType == CameraType.DJICameraTypeFC350;
    }

    public static boolean c(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        return !h(cameraType);
    }

    public static boolean d(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeCV600;
    }

    public static boolean e(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC350;
    }

    public static boolean f(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw;
    }

    public static boolean g(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw;
    }

    public static boolean h(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) {
            return true;
        }
        return false;
    }

    public static boolean i(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeCV600) {
            return true;
        }
        return false;
    }

    public static boolean j(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeCV600) {
            return true;
        }
        return false;
    }

    public static int k(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeCV600) {
            return 74;
        }
        return 84;
    }

    public static boolean l(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) {
            return true;
        }
        return false;
    }

    public static boolean a(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (dataCameraGetPushShotInfo.getShotFDType() == DataCameraGetPushShotInfo$ShotFDType.ZoomShotFD && dataCameraGetPushShotInfo.getZoomFocusType() == ZoomFocusType.AutoZoomFocus) {
            return true;
        }
        return false;
    }

    public static boolean m(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeCV600) {
            return true;
        }
        return false;
    }

    public static boolean n(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeCV600;
    }

    public static boolean o(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = i.getInstance().b();
        }
        boolean a;
        if (DataCameraGetPushShotInfo.getInstance().isGetted()) {
            a = a(DataCameraGetPushShotInfo.getInstance());
        } else {
            a = false;
        }
        if (cameraType == CameraType.DJICameraTypeCV600 || r0) {
            return true;
        }
        return false;
    }
}
