package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryLowCellVoltageOperation;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.a;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.e;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class l extends a {
    public static final String a = "g_config.voltage.battery_cell_0";
    private static final String h = "g_config.voltage.level_1_protect_0";
    private static final String i = "g_config.voltage.level_2_protect_0";
    private static final String j = "g_config.voltage.level_1_protect_type_0";
    private static final String k = "g_config.voltage.level_2_protect_type_0";
    private int l;
    private int m;

    public l() {
        this.l = 0;
        this.m = 0;
        this.b = false;
    }

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getVoltage()), b.d(a.l));
    }

    private void a(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new DataFlycGetParams().setInfos(new String[]{h, i}).start(new 1(this, countDownLatch));
        if (i > 0) {
            try {
                countDownLatch.await((long) i, TimeUnit.MILLISECONDS);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        countDownLatch.await();
    }

    @e(a = "NumberOfCells")
    public void o(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.voltage.battery_cell_0"}).start(new 4(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "NumberOfCells")
    public void a(int i, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_EXECUTIONFAILED);
        } else if (i < 3 || i > 12) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            dataFlycSetParams.a("g_config.voltage.battery_cell_0");
            dataFlycSetParams.a(Integer.valueOf(i));
            dataFlycSetParams.start(new 5(this, eVar));
        }
    }

    @e(a = "Level1CellVoltageThreshold")
    public void a(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{h}).start(new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Level1CellVoltageThreshold")
    public void b(int i, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (i < 3600 || i > 4000) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            new Thread(new 7(this, i, eVar)).start();
        }
    }

    @e(a = "Level2CellVoltageThreshold")
    public void b(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{i}).start(new 8(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Level2CellVoltageThreshold")
    public void c(int i, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (i < com.alipay.sdk.c.a.a || i > 3800) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            new Thread(new 9(this, i, eVar)).start();
        }
    }

    @e(a = "Level1CellVoltageOperation")
    public void c(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.voltage.level_1_protect_type_0"}).start(new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Level1CellVoltageOperation")
    public void a(DJIBatteryLowCellVoltageOperation dJIBatteryLowCellVoltageOperation, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_EXECUTIONFAILED);
            return;
        }
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.voltage.level_1_protect_type_0");
        dataFlycSetParams.a(Integer.valueOf(dJIBatteryLowCellVoltageOperation.value()));
        dataFlycSetParams.start(new 11(this, eVar));
    }

    @e(a = "Level2CellVoltageOperation")
    public void d(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.voltage.level_2_protect_type_0"}).start(new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Level2CellVoltageOperation")
    public void b(DJIBatteryLowCellVoltageOperation dJIBatteryLowCellVoltageOperation, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_EXECUTIONFAILED);
            return;
        }
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.voltage.level_2_protect_type_0");
        dataFlycSetParams.a(Integer.valueOf(dJIBatteryLowCellVoltageOperation.value()));
        dataFlycSetParams.start(new 3(this, eVar));
    }

    public void e() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        super.e();
    }
}
