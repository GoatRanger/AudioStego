package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.common.error.DJIGimbalError;
import dji.common.gimbal.DJIGimbalAdvancedSettingsProfile;
import dji.common.gimbal.DJIGimbalCapabilityKey;
import dji.common.gimbal.DJIGimbalControllerMode;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.model.P3.DataBaseGetting;
import dji.midware.data.model.P3.DataBaseSetting;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.e.a.a;
import dji.sdksharedlib.hardware.abstractions.f;

public class d extends i {
    protected void a() {
        super.a();
    }

    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustPitch, Integer.valueOf(-120), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.AdjustYaw, Integer.valueOf(-180), Integer.valueOf(180));
        a(DJIGimbalCapabilityKey.AdjustRoll, false);
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSpeedYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch, Integer.valueOf(0), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerSmoothingYaw, Integer.valueOf(0), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.SmoothTrackAccelerationPitch, Integer.valueOf(1), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.SmoothTrackAccelerationYaw, Integer.valueOf(1), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedPitch, Integer.valueOf(1), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedYaw, Integer.valueOf(1), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandPitch, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandYaw, Integer.valueOf(0), Integer.valueOf(90));
        a(DJIGimbalCapabilityKey.SmoothTrackEnabledPitch, true);
        a(DJIGimbalCapabilityKey.SmoothTrackEnabledYaw, true);
        a(DJIGimbalCapabilityKey.AdvancedSettingsProfile, true);
    }

    @f(a = "AdvancedSettingsProfile")
    public void a(DJIGimbalAdvancedSettingsProfile dJIGimbalAdvancedSettingsProfile, e eVar) {
        if (dJIGimbalAdvancedSettingsProfile == DJIGimbalAdvancedSettingsProfile.Unknown) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            a(dJIGimbalAdvancedSettingsProfile.value(), a.a, eVar);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AdvancedSettingsProfile")
    public void j(e eVar) {
        if (eVar != null) {
            if (this.i == null) {
                eVar.a(DJIGimbalError.COMMON_TIMEOUT);
            } else {
                eVar.a(DJIGimbalAdvancedSettingsProfile.find(this.i.getPresetID()));
            }
        }
    }

    @f(a = "ControllerMode")
    public void a(DJIGimbalControllerMode dJIGimbalControllerMode, e eVar) {
        new DataBaseSetting().a(p.GIMBAL).a(h.a.w.a()).a(DeviceType.GIMBAL).b(dJIGimbalControllerMode.ordinal()).start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerMode")
    public void y(e eVar) {
        DataBaseGetting dataBaseGetting = new DataBaseGetting();
        dataBaseGetting.setCmdSet(p.GIMBAL).setCmdId(h.a.x.a()).setReceiver(DeviceType.GIMBAL).start(new 2(this, dataBaseGetting, eVar));
    }

    @f(a = "SmoothTrackEnabledPitch")
    public void b(boolean z, e eVar) {
        a(z ? 1 : 0, a.o, eVar);
    }

    @f(a = "SmoothTrackEnabledYaw")
    public void c(boolean z, e eVar) {
        a(z ? 1 : 0, a.p, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackEnabledPitch")
    public void h(e eVar) {
        a(a.o, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackEnabledYaw")
    public void i(e eVar) {
        a(a.p, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ToggleSelfie")
    public void x(e eVar) {
        DataSpecialControl.getInstance().selfieGimbal().start(20);
        eVar.a(null);
    }

    @f(a = "InvertControlEnabledYaw")
    public void f(boolean z, e eVar) {
        new DataGimbalSetHandleParams().b(z ? 1 : 0).start(new 3(this, eVar, z));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "InvertControlEnabledYaw")
    public void z(e eVar) {
        DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new 4(this, dataGimbalGetHandleParams, eVar));
    }

    @f(a = "InvertControlEnabledPitch")
    public void g(boolean z, e eVar) {
        new DataGimbalSetHandleParams().c(z ? 1 : 0).start(new 5(this, eVar, z));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "InvertControlEnabledPitch")
    public void A(e eVar) {
        DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new 6(this, dataGimbalGetHandleParams, eVar));
    }

    private void a(String str, boolean z, e eVar) {
        DJISDKCache.getInstance().getValue(new c.a().b(dji.sdksharedlib.b.f.a).a(Integer.valueOf(this.b)).d(str).a(), new 7(this, z, eVar));
    }
}
