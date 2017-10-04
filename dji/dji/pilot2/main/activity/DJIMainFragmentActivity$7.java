package dji.pilot2.main.activity;

import android.content.Intent;
import dji.log.DJILogHelper;
import dji.pilot.battery.service.BatteryCheckService;

class DJIMainFragmentActivity$7 implements Runnable {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$7(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public void run() {
        Intent intent = new Intent(this.a, BatteryCheckService.class);
        intent.putExtra("service_type", 2);
        DJILogHelper.getInstance().LOGI("BatteryCheck", "Get invalid battery list");
        this.a.startService(intent);
    }
}
