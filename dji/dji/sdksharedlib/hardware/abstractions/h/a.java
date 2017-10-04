package dji.sdksharedlib.hardware.abstractions.h;

import android.util.Log;
import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCBatteryInfo;
import dji.common.remotecontroller.DJIRCControlMode;
import dji.common.remotecontroller.DJIRCGPSData;
import dji.common.remotecontroller.DJIRCGimbalControlDirection;
import dji.common.remotecontroller.DJIRCHardwareState;
import dji.common.remotecontroller.DJIRCHardwareState.DJIRCHardwareFlightModeSwitchState;
import dji.common.remotecontroller.DJIRCHardwareState.DJIRCHardwareTransformationSwitchState;
import dji.common.remotecontroller.DJIRCRemoteFocusState.DJIRCRemoteFocusControlDirection;
import dji.common.remotecontroller.DJIRCRemoteFocusState.DJIRCRemoteFocusControlType;
import dji.common.remotecontroller.JoinMasterParams;
import dji.common.remotecontroller.RCCustomButtonTagParam;
import dji.common.remotecontroller.RemoteControllerModeParam;
import dji.log.DJILog;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.P3.DataRcDeleteMaster;
import dji.midware.data.model.P3.DataRcDeleteSlave;
import dji.midware.data.model.P3.DataRcGetConnectMaster;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetCustomFuction;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcGetGimbalSpeed;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetName;
import dji.midware.data.model.P3.DataRcGetPassword;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcGetSearchMasters;
import dji.midware.data.model.P3.DataRcGetSearchMode;
import dji.midware.data.model.P3.DataRcGetSlaveList;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcGetWheelGain;
import dji.midware.data.model.P3.DataRcRequestGimbalCtrPermission;
import dji.midware.data.model.P3.DataRcSetConnectMaster;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetCustomFuction;
import dji.midware.data.model.P3.DataRcSetFrequency;
import dji.midware.data.model.P3.DataRcSetFrequency.FreqMode;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode.MODE;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataRcSetName;
import dji.midware.data.model.P3.DataRcSetPassword;
import dji.midware.data.model.P3.DataRcSetSearchMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.SlaveCustomModel;
import dji.midware.data.model.P3.DataRcSetWheelGain;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class a extends b {
    private static final int a = 4;
    private static final String e = "DJISDKCacheRemoteControllerAbstraction";
    protected DJIRCHardwareState b = new DJIRCHardwareState();
    protected boolean c = false;
    protected boolean d = false;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        d();
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        onEventBackgroundThread(DataRcGetPushParams.getInstance());
        onEventBackgroundThread(DataRcGetPushGpsInfo.getInstance());
        onEventBackgroundThread(DataRcGetPushBatteryInfo.getInstance());
        onEventBackgroundThread(DataRcGetPushFollowFocus.getInstance());
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        super.e();
    }

    protected Class<? extends d> b() {
        return i.class;
    }

    protected void a() {
        a(i.class, getClass());
    }

    private static boolean g(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ExitRCToAircraftPairingMode")
    public void a(e eVar) {
        DataRcSetFrequency.getInstance().a(FreqMode.c).start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCCustomButtonTag")
    public void a(RCCustomButtonTagParam rCCustomButtonTagParam, e eVar) {
        if (this.b.customButton1.isPresent || this.b.customButton2.isPresent) {
            DataRcSetCustomFuction.getInstance().a(rCCustomButtonTagParam.tag1).b(rCCustomButtonTagParam.tag1).start(new 12(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCCustomButtonTag")
    public void b(e eVar) {
        if (this.b.customButton1.isPresent || this.b.customButton2.isPresent) {
            DataRcGetCustomFuction.getInstance().start(new 23(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCControlGimbalDirection")
    public void a(DJIRCGimbalControlDirection dJIRCGimbalControlDirection, e eVar) {
        if (dJIRCGimbalControlDirection != null) {
            DataRcSetGimbalControlMode instance = DataRcSetGimbalControlMode.getInstance();
            instance.a(MODE.find(dJIRCGimbalControlDirection.value()));
            instance.start(new 29(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCControlGimbalDirection")
    public void c(e eVar) {
        DataRcGetGimbalControlMode instance = DataRcGetGimbalControlMode.getInstance();
        instance.start(new 30(this, eVar, instance));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCWheelGimbalSpeed")
    public void a(Short sh, e eVar) {
        DataRcSetWheelGain.getInstance().a(sh.intValue()).start(new 31(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCWheelGimbalSpeed")
    public void d(e eVar) {
        DataRcGetWheelGain.getInstance().start(new 32(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCControlMode")
    public void a(DJIRCControlMode dJIRCControlMode, e eVar) {
        if (dJIRCControlMode == null) {
            if (eVar != null) {
                eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
            }
        } else if (!dJIRCControlMode.controlStyle._equals(4)) {
            DataRcSetControlMode.getInstance().a(ControlMode.find(dJIRCControlMode.controlStyle.value())).start(new 33(this, eVar));
        } else if (dJIRCControlMode.controlStyle._equals(4)) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 4; i++) {
                int i2;
                ChannelCustomModel channelCustomModel = new ChannelCustomModel();
                channelCustomModel.b = dJIRCControlMode.controlChannel[i].channel.value();
                if (dJIRCControlMode.controlChannel[i].isReverse) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                channelCustomModel.a = i2;
                arrayList.add(channelCustomModel);
            }
            DataRcSetControlMode.getInstance().a(ControlMode.d).a(arrayList).start(new 34(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCControlMode")
    public void e(e eVar) {
        DataRcGetControlMode.getInstance().start(new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCPassword")
    public void a(String str, e eVar) {
        if (str != null && str.length() == 4 && g(str)) {
            DataRcSetPassword.getInstance().a(Integer.parseInt(str)).start(new 3(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCPassword")
    public void f(e eVar) {
        DataRcGetPassword.getInstance().start(new 4(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCName")
    public void g(e eVar) {
        DataRcGetName.getInstance().start(new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RCName")
    public void b(String str, e eVar) {
        if (str != null && dji.midware.util.c.b(str).length <= 6) {
            DataRcSetName.getInstance().a(str).start(new 6(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SlaveGimbalControlRight")
    public void h(e eVar) {
        if (this.c) {
            DataRcRequestGimbalCtrPermission.getInstance().start(new 7(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SlaveJoystickControlGimbalSpeed")
    public void i(e eVar) {
        if (this.c) {
            DataRcGetGimbalSpeed instance = DataRcGetGimbalSpeed.getInstance();
            instance.start(new 8(this, eVar, instance));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SlaveControlMode")
    public void j(e eVar) {
        if (this.c) {
            DataRcGetSlaveMode.getInstance().start(new 9(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SlaveControlMode")
    public void b(DJIRCControlMode dJIRCControlMode, e eVar) {
        if (dJIRCControlMode == null) {
            if (eVar != null) {
                eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
            }
        } else if (this.c) {
            DataRcSetSlaveMode.ControlMode controlMode;
            switch (28.a[dJIRCControlMode.controlStyle.ordinal()]) {
                case 1:
                    controlMode = DataRcSetSlaveMode.ControlMode.b;
                    break;
                case 2:
                    controlMode = DataRcSetSlaveMode.ControlMode.a;
                    break;
                default:
                    controlMode = DataRcSetSlaveMode.ControlMode.a;
                    break;
            }
            DJISDKCache.getInstance();
            if (controlMode.a(0)) {
                DataRcSetSlaveMode.getInstance().a(controlMode).start(new 10(this, eVar));
                return;
            }
            int length = dJIRCControlMode.controlChannel.length;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                int i2;
                SlaveCustomModel slaveCustomModel = new SlaveCustomModel();
                if (dJIRCControlMode.controlChannel[i].isReverse) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                slaveCustomModel.a = i2;
                slaveCustomModel.b = dJIRCControlMode.controlChannel[i].channel.value();
                arrayList.add(slaveCustomModel);
            }
            DataRcSetSlaveMode.getInstance().a(controlMode).a(arrayList).start(new 11(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "RemoveMaster")
    public void a(e eVar, Integer num) {
        if (this.c) {
            DataRcDeleteMaster.getInstance().setID(num.intValue()).start(new 13(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "JoinedMasterNameAndPassword")
    public void k(e eVar) {
        if (this.c) {
            DataRcGetConnectMaster.getInstance().start(new 14(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "RemoveSlave")
    public void b(e eVar, Integer num) {
        if (this.c) {
            DataRcDeleteSlave.getInstance().setID(num.intValue()).start(new 15(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SlaveList")
    public void l(e eVar) {
        if (this.c) {
            DataRcGetSlaveList.getInstance().start(new 16(this, new ArrayList(), eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopMasterRCSearch")
    public void m(e eVar) {
        if (this.c) {
            DataRcSetSearchMode.getInstance().a(false).start(new 17(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MasterRCSearchState")
    public void n(e eVar) {
        if (this.c) {
            DataRcGetSearchMode.getInstance().start(new 18(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartMasterRCSearch")
    public void o(e eVar) {
        DataRcSetSearchMode.getInstance().a(true).start(new 19(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "JoinMaster")
    public void a(e eVar, JoinMasterParams joinMasterParams) {
        if (joinMasterParams.masterName == null || joinMasterParams.masterPassword == null) {
            if (eVar != null) {
                eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
            }
        } else if (this.c) {
            RcModel rcModel = new RcModel();
            rcModel.id = joinMasterParams.hostId;
            rcModel.isOpen = true;
            rcModel.name = joinMasterParams.masterName;
            rcModel.password = Integer.parseInt(joinMasterParams.masterPassword);
            Log.e(e, "pwd to int " + Integer.parseInt(joinMasterParams.masterPassword));
            DataRcSetConnectMaster.getInstance().a(rcModel).start(new 20(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AvailableMasters")
    public void p(e eVar) {
        if (this.c) {
            DataRcGetSearchMasters.getInstance().start(new 21(this, new ArrayList(), eVar));
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void q(e eVar) {
        DataOsdActiveStatus.getInstance().setType(dji.midware.data.model.b.a.b.b).start(new 22(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FirmwareVersion")
    public void r(e eVar) {
        if (dji.internal.version.c.getInstance().c() != null) {
            Object b = dji.internal.version.c.getInstance().c().b();
            if (eVar == null) {
                return;
            }
            if (b != null) {
                eVar.a(b);
            } else {
                eVar.a(DJIRemoteControllerError.UNABLE_TO_GET_FIRMWARE_VERSION);
            }
        } else if (eVar != null) {
            eVar.a(DJIRemoteControllerError.UNABLE_TO_GET_FIRMWARE_VERSION);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "EnterRCToAircraftPairingMode")
    public void s(e eVar) {
        DataRcSetFrequency.getInstance().a(FreqMode.b).start(new 24(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RCToAircraftPairingState")
    public void t(e eVar) {
        DataRcSetFrequency.getInstance().a(FreqMode.a).start(new 25(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "RemoteControllerMode")
    public void a(RemoteControllerModeParam remoteControllerModeParam, e eVar) {
        DJILog.d(e, "set workmode start", true, true);
        if (remoteControllerModeParam == null) {
            DJILog.d(e, "set workmode 0", true, true);
            if (eVar != null) {
                eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
            }
        } else if (this.c) {
            DataRcSetMaster.getInstance().a(DataRcSetMaster.MODE.find(DataRcSetMaster.MODE.find(remoteControllerModeParam.workMode.value()).a())).start(new 26(this, eVar));
        } else {
            DJILog.d(e, "set workmode 1", true, true);
            if (eVar != null) {
                eVar.a(DJIRemoteControllerError.getDJIError(dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerMode")
    public void u(e eVar) {
        DataRcGetMaster.getInstance().start(new 27(this, eVar));
    }

    protected dji.sdksharedlib.b.c a(String str) {
        return new dji.sdksharedlib.b.c.a().b(i.a).d(str).a();
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        boolean z;
        boolean z2 = true;
        a((Object) Integer.valueOf(dataRcGetPushParams.getAileron() - 1024), a(i.e));
        a((Object) Integer.valueOf(dataRcGetPushParams.getElevator() - 1024), a(i.f));
        a((Object) Integer.valueOf(dataRcGetPushParams.getRudder() - 1024), a(i.c));
        a((Object) Integer.valueOf(dataRcGetPushParams.getThrottle() - 1024), a(i.d));
        a((Object) Integer.valueOf(dataRcGetPushParams.getGyroValue() - 1024), a(i.g));
        a((Object) Boolean.valueOf(dataRcGetPushParams.isWheelBtnDown()), a(i.h));
        a((Object) Integer.valueOf(dataRcGetPushParams.getWheelOffset()), a(i.k));
        a((Object) Boolean.valueOf(dataRcGetPushParams.isWheelChanged()), a(i.i));
        a((Object) Boolean.valueOf(dataRcGetPushParams.isWheelPositive()), a(i.j));
        a((Object) DJIRCHardwareTransformationSwitchState.find(dataRcGetPushParams.getFootStool() ? 1 : 0), a(i.l));
        if (c()) {
            switch (dataRcGetPushParams.getMode()) {
                case 0:
                    a((Object) DJIRCHardwareFlightModeSwitchState.S, a(i.m));
                    break;
                case 1:
                    a((Object) DJIRCHardwareFlightModeSwitchState.P, a(i.m));
                    break;
                case 2:
                    a((Object) DJIRCHardwareFlightModeSwitchState.A, a(i.m));
                    break;
                default:
                    break;
            }
        }
        a((Object) DJIRCHardwareFlightModeSwitchState.find(dataRcGetPushParams.getMode()), a(i.m));
        a((Object) Boolean.valueOf(dataRcGetPushParams.isGoHomeButtonPressed()), a(i.n));
        a((Object) Boolean.valueOf(dataRcGetPushParams.getRecordStatus()), a(i.o));
        a((Object) Boolean.valueOf(dataRcGetPushParams.getShutterStatus()), a(i.p));
        if (c()) {
            a((Object) Boolean.valueOf(dataRcGetPushParams.getPlayback() == 1), a(i.u));
        } else {
            a((Object) Boolean.valueOf(dataRcGetPushParams.getPlayback() == 1), a(i.u));
        }
        if (dataRcGetPushParams.getCustom1() == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), a(i.q));
        if (dataRcGetPushParams.getCustom2() != 1) {
            z2 = false;
        }
        a((Object) Boolean.valueOf(z2), a(i.r));
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        DJIRCGPSData dJIRCGPSData = new DJIRCGPSData();
        dJIRCGPSData.time.hour = (byte) dataRcGetPushGpsInfo.getHour();
        dJIRCGPSData.time.minute = (byte) dataRcGetPushGpsInfo.getMinute();
        dJIRCGPSData.time.second = (byte) dataRcGetPushGpsInfo.getSecond();
        dJIRCGPSData.time.year = dataRcGetPushGpsInfo.getYear();
        dJIRCGPSData.time.month = (byte) dataRcGetPushGpsInfo.getMonth();
        dJIRCGPSData.time.day = (byte) dataRcGetPushGpsInfo.getDay();
        dJIRCGPSData.latitude = dataRcGetPushGpsInfo.getLatitude();
        dJIRCGPSData.longitude = dataRcGetPushGpsInfo.getLongitude();
        dJIRCGPSData.speedEast = (float) dataRcGetPushGpsInfo.getXSpeed();
        dJIRCGPSData.speedNorth = (float) dataRcGetPushGpsInfo.getYSpeed();
        dJIRCGPSData.satelliteCount = dataRcGetPushGpsInfo.getGpsNum();
        dJIRCGPSData.accuracy = dataRcGetPushGpsInfo.getAccuracy().floatValue();
        dJIRCGPSData.isValid = dataRcGetPushGpsInfo.getGpsStatus();
        a((Object) dJIRCGPSData, a(i.v));
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        DJIRCBatteryInfo dJIRCBatteryInfo = new DJIRCBatteryInfo();
        dJIRCBatteryInfo.remainingEnergyInMAh = dataRcGetPushBatteryInfo.getBatteryVolume();
        dJIRCBatteryInfo.remainingEnergyInPercent = dataRcGetPushBatteryInfo.getBattery();
        a((Object) dJIRCBatteryInfo, a(i.w));
    }

    public void onEventBackgroundThread(DataRcGetPushFollowFocus dataRcGetPushFollowFocus) {
        boolean z = true;
        if (dataRcGetPushFollowFocus.getCurCtrlStatus() != 1) {
            z = false;
        }
        a((Object) Boolean.valueOf(z), a(i.x));
        switch (28.b[dataRcGetPushFollowFocus.getCtrlType().ordinal()]) {
            case 1:
                a((Object) DJIRCRemoteFocusControlType.Aperture, a(i.y));
                break;
            case 2:
                a((Object) DJIRCRemoteFocusControlType.FocalLength, a(i.y));
                break;
            case 3:
                a((Object) DJIRCRemoteFocusControlType.Unknown, a(i.y));
                break;
        }
        switch (28.c[dataRcGetPushFollowFocus.getCtrlDirection().ordinal()]) {
            case 1:
                a((Object) DJIRCRemoteFocusControlDirection.Clockwise, a(i.z));
                return;
            case 2:
                a((Object) DJIRCRemoteFocusControlDirection.CounterClockwise, a(i.z));
                return;
            case 3:
                a((Object) DJIRCRemoteFocusControlDirection.Unknown, a(i.z));
                return;
            default:
                return;
        }
    }

    protected String d(String str) {
        return com.dji.frame.c.a.b(str).substring(0, 8);
    }

    protected boolean c() {
        return dji.midware.data.manager.P3.i.getInstance().c() == ProductType.Tomato;
    }

    protected void d() {
        a((Object) Boolean.valueOf(this.c), c(i.W));
        a((Object) Boolean.valueOf(this.d), c(i.X));
    }
}
