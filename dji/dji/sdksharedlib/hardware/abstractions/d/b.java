package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.error.DJIFlightControllerError;
import dji.common.flightcontroller.DJIRTKPositioningSolution;
import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRTKPushStatus;
import dji.midware.data.params.P3.a;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;

public class b extends d {
    private final String[] a = new String[]{a.w, a.x, a.y, "g_status.acc_gyro[0].cali_cnt_0", "g_status.acc_gyro[1].cali_cnt_0", "g_status.acc_gyro[2].cali_cnt_0", a.t, a.u, a.v};

    protected void g_() {
        a((Object) Boolean.valueOf(false), c("IsLandingGearMovable"));
        a((Object) Boolean.valueOf(true), c(e.i));
        a((Object) Boolean.valueOf(false), c(e.f));
        a((Object) Integer.valueOf(3), c(e.e));
        a((Object) Boolean.valueOf(false), c(e.s));
    }

    protected void a() {
        super.a();
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartIMUCalibrationWithID")
    public void a(dji.sdksharedlib.hardware.abstractions.b.e eVar, int i) {
        int i2 = 1;
        Integer num = (Integer) DJISDKCache.getInstance().getAvailableValue(new c.a().b(e.a).a(Integer.valueOf(0)).d(e.e).a()).e();
        if (i < 0 || i >= num.intValue()) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
            return;
        }
        int i3;
        String[] strArr = new String[]{a.k, a.l, a.m};
        Number[] numberArr = new Number[3];
        if (i == 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        numberArr[0] = Integer.valueOf(i3);
        numberArr[1] = Integer.valueOf(i == 1 ? 1 : 0);
        if (i != 2) {
            i2 = 0;
        }
        numberArr[2] = Integer.valueOf(i2);
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(strArr);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartIMUCalibration")
    public void h(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        String[] strArr = new String[]{a.k, a.l, a.m};
        Number[] numberArr = new Number[]{Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1)};
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(strArr);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 2(this, eVar));
    }

    protected void a(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
            new DataFlycGetParams().setInfos(this.a).start(new 3(this));
        }
    }

    public void onEventBackgroundThread(DataRTKPushStatus dataRTKPushStatus) {
        boolean z;
        boolean z2 = true;
        a((Object) b(dataRTKPushStatus.a()), c(e.aN));
        a((Object) DJIRTKPositioningSolution.find(dataRTKPushStatus.b()), c(e.bj));
        a((Object) Boolean.valueOf((dataRTKPushStatus.c() & 1) == 1), c(e.ba));
        a((Object) Integer.valueOf(dataRTKPushStatus.c() >>> 1), c(e.aZ));
        if ((dataRTKPushStatus.d() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aY));
        if ((dataRTKPushStatus.d() >>> 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aX));
        if ((dataRTKPushStatus.e() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bc));
        if ((dataRTKPushStatus.e() >>> 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bb));
        if ((dataRTKPushStatus.f() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bg));
        a((Object) Integer.valueOf(dataRTKPushStatus.f() >>> 1), c(e.bf));
        if ((dataRTKPushStatus.g() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.be));
        if ((dataRTKPushStatus.g() >>> 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bd));
        if ((dataRTKPushStatus.h() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bi));
        if ((dataRTKPushStatus.h() >>> 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.bh));
        if ((dataRTKPushStatus.i() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aS));
        a((Object) Integer.valueOf(dataRTKPushStatus.i() >>> 1), c(e.aR));
        if ((dataRTKPushStatus.j() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aQ));
        if ((dataRTKPushStatus.j() >>> 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aP));
        if ((dataRTKPushStatus.k() & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        a((Object) Boolean.valueOf(z), c(e.aU));
        if ((dataRTKPushStatus.k() >>> 1) != 1) {
            z2 = false;
        }
        a((Object) Boolean.valueOf(z2), c(e.aT));
        a((Object) Float.valueOf(dataRTKPushStatus.n()), c(e.aH));
        a((Object) Float.valueOf(dataRTKPushStatus.q()), c(e.aO));
        a((Object) Boolean.valueOf(dataRTKPushStatus.v()), c(e.aM));
        a((Object) Boolean.valueOf(dataRTKPushStatus.u()), c(e.aL));
        a((Object) Float.valueOf(dataRTKPushStatus.r()), c(e.aK));
        a((Object) Double.valueOf(dataRTKPushStatus.o()), c(e.aV));
        a((Object) Double.valueOf(dataRTKPushStatus.p()), c(e.aW));
        a((Object) Double.valueOf(dataRTKPushStatus.l()), c(e.aI));
        a((Object) Double.valueOf(dataRTKPushStatus.m()), c(e.aJ));
    }

    private DJIError b(int i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return DJIFlightControllerError.RTK_START_ERROR;
            case 2:
                return DJIFlightControllerError.RTK_CONNECTION_BROKEN;
            case 3:
                return DJIFlightControllerError.RTK_BS_ANTENNA_ERROR;
            case 4:
                return DJIFlightControllerError.RTK_BS_COORDINATE_RESETED;
            default:
                return DJIError.COMMON_UNKNOWN;
        }
    }
}
