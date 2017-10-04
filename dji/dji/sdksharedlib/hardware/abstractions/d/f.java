package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.flightcontroller.DJILandingGearMode;
import dji.common.flightcontroller.DJILandingGearStatus;
import dji.common.util.CallbackUtils;
import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.hardware.abstractions.a;
import dji.sdksharedlib.hardware.abstractions.b;

public class f extends d {
    protected void g_() {
        a((Object) Boolean.valueOf(true), c("IsLandingGearMovable"));
        a((Object) Boolean.valueOf(false), c(e.i));
        a((Object) Boolean.valueOf(false), c(e.f));
        a((Object) Integer.valueOf(1), c(e.e));
        a((Object) Boolean.valueOf(false), c(e.s));
    }

    protected void a() {
        super.a();
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        if (dataFlycGetPushDeformStatus.getRecDataLen() != 0) {
            Object obj;
            DJILandingGearMode dJILandingGearMode = DJILandingGearMode.Normal;
            if (dataFlycGetPushDeformStatus.isDeformProtected()) {
                obj = DJILandingGearMode.Auto;
            } else {
                obj = DJILandingGearMode.find(dataFlycGetPushDeformStatus.getDeformMode().value());
            }
            a(obj, c(e.bv));
            a((Object) DJILandingGearStatus.find(dataFlycGetPushDeformStatus.getDeformStatus().value()), c(e.bu));
        }
    }

    @a(a = "EnterTransportMode")
    public void i(b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.FAIL_TO_ENTER_TRANSPORT_MODE_WHEN_MOTORS_ON);
        } else if (DataCameraGetPushStateInfo.getInstance().getConnectState()) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.FLIGHT_CONTROLLER_COULD_NOT_ENTER_TRANSPORT_MODE);
        } else {
            a(FLYC_COMMAND.PackMode, eVar);
        }
    }

    @a(a = "ExitTransportMode")
    public void E(b.e eVar) {
        a(FLYC_COMMAND.UnPackMode, eVar);
    }

    @a(a = "DeployLandingGear")
    public void F(b.e eVar) {
        a(FLYC_COMMAND.DownDeform, eVar);
    }

    @a(a = "RetractLandingGear")
    public void G(b.e eVar) {
        a(FLYC_COMMAND.UpDeform, eVar);
    }

    @a(a = "TurnOnAutoLandingGear")
    public void H(b.e eVar) {
        String str = "g_config.gear_cfg.auto_control_enable_0";
        d.read(str);
        new DataFlycSetParams().a(str, Integer.valueOf(1)).start(new 1(this, eVar));
    }

    @a(a = "TurnOffAutoLandingGear")
    public void I(b.e eVar) {
        String str = "g_config.gear_cfg.auto_control_enable_0";
        d.read(str);
        new DataFlycSetParams().a(str, Integer.valueOf(0)).start(new 2(this, eVar));
    }
}
