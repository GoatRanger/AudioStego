package dji.sdksharedlib.hardware.abstractions.c.a;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.midware.data.model.P3.DataCameraGetCameraRotationMode;
import dji.midware.data.model.P3.DataCameraSetCameraRotationMode;
import dji.midware.data.model.P3.DataCameraSetCameraRotationMode.RotationAngleType;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.c.b;
import dji.sdksharedlib.hardware.abstractions.f;

public class a extends b {
    protected boolean v() {
        return true;
    }

    @f(a = "Orientation")
    public void a(CameraOrientation cameraOrientation, e eVar) {
        DataCameraSetCameraRotationMode.getInstance().a(0).a(RotationAngleType.find(cameraOrientation == CameraOrientation.Landscape ? 0 : 1)).start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "Orientation")
    public void x(e eVar) {
        DataCameraGetCameraRotationMode.getInstance().start(new 2(this, eVar));
    }
}
