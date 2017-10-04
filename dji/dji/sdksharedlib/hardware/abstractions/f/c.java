package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.airlink.AirLinkUtils;
import dji.common.error.DJIError;
import dji.common.handheld.DJIHandheldButtonStatus;
import dji.common.handheld.DJIHandheldPowerMode;
import dji.common.handheld.DJIHandheldTriggerStatus;
import dji.common.handheld.JoystickHorizontalDirection;
import dji.common.handheld.JoystickVerticalDirection;
import dji.midware.data.model.P3.DataCameraGetPushShutterCmd;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.sdksharedlib.b.g;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import java.util.Timer;
import java.util.TimerTask;

public class c extends a {
    private long a = 0;
    private long b = 0;
    private long c = 0;
    private Timer d;
    private TimerTask e;

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        b();
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
    }

    protected void b() {
        onEventBackgroundThread(DataGimbalGetPushParams.getInstance());
        onEventBackgroundThread(DataCameraGetPushShutterCmd.getInstance());
    }

    protected void a() {
        super.a();
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams != null) {
            if (dataGimbalGetPushParams.getMode().value() == 0) {
                b((Object) Boolean.valueOf(true), g.b);
            } else {
                b((Object) Boolean.valueOf(false), g.b);
            }
            if (dataGimbalGetPushParams.isSingleClick() && (this.a == 0 || System.currentTimeMillis() - this.a >= 1000)) {
                b((Object) DJIHandheldTriggerStatus.SingleClick, g.f);
                this.a = System.currentTimeMillis();
                b((Object) DJIHandheldTriggerStatus.Idle, g.f);
            }
            if (dataGimbalGetPushParams.isDoubleClick() && (this.b == 0 || System.currentTimeMillis() - this.b >= 1000)) {
                b((Object) DJIHandheldTriggerStatus.DoubleClick, g.f);
                this.b = System.currentTimeMillis();
                b((Object) DJIHandheldTriggerStatus.Idle, g.f);
            }
            if (dataGimbalGetPushParams.isTripleClick() && (this.c == 0 || System.currentTimeMillis() - this.c >= 1000)) {
                b((Object) DJIHandheldTriggerStatus.TripleClick, g.f);
                this.c = System.currentTimeMillis();
                b((Object) DJIHandheldTriggerStatus.Idle, g.f);
            }
            b(JoystickHorizontalDirection.values()[dataGimbalGetPushParams.getJoystickHorDirection()], g.h);
            b(JoystickVerticalDirection.values()[dataGimbalGetPushParams.getJoystickVerDirection()], g.g);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShutterCmd dataCameraGetPushShutterCmd) {
        if (dataCameraGetPushShutterCmd.isGetted()) {
            DJIHandheldButtonStatus dJIHandheldButtonStatus = DJIHandheldButtonStatus.values()[dataCameraGetPushShutterCmd.getShutterType()];
            b((Object) dJIHandheldButtonStatus, g.e);
            if (dJIHandheldButtonStatus == DJIHandheldButtonStatus.ShutterButtonPressed || dJIHandheldButtonStatus == DJIHandheldButtonStatus.RecordButtonPressed) {
                if (this.e != null) {
                    this.e.cancel();
                }
                if (this.d != null) {
                    this.d.cancel();
                }
                b((Object) DJIHandheldButtonStatus.Idle, g.e);
                return;
            }
            if (this.e != null) {
                this.e.cancel();
            }
            if (this.d != null) {
                this.d.cancel();
            }
            this.e = new 1(this);
            this.d = new Timer();
            this.d.schedule(this.e, 1000);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "HandheldPowerMode")
    public void a(DJIHandheldPowerMode dJIHandheldPowerMode, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void a(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "HandheldName")
    public void a(String str, e eVar) {
        if (!AirLinkUtils.verifySSID(str) || eVar == null) {
            DataWifiSetSSID dataWifiSetSSID = new DataWifiSetSSID();
            dataWifiSetSSID.a(true);
            dataWifiSetSSID.a(str.getBytes()).start(new 2(this, eVar));
            return;
        }
        eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "HandheldName")
    public void c(e eVar) {
        if (eVar != null) {
            eVar.a(dji.midware.b.c.getInstance().f().k());
        }
    }
}
