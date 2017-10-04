package dji.pilot2.main.activity;

import android.content.Intent;
import com.dji.frame.c.a;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCenterGetBoardNumber;
import dji.midware.e.d;
import dji.pilot.battery.service.BatteryCheckService;

class DJIMainFragmentActivity$3 implements d {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$3(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public void onSuccess(Object obj) {
        String boardNumber = DataCenterGetBoardNumber.getInstance().getBoardNumber();
        byte[] bArr = (byte[]) DataCenterGetBoardNumber.getInstance().getRecData().clone();
        String a = a.a(bArr);
        DJILogHelper.getInstance().LOGI("BatteryCheck", "battery sn: " + boardNumber, true, false);
        DJILogHelper.getInstance().LOGI("BatteryCheck", "battery md5: " + a.a(bArr), true, false);
        if (boardNumber == null || boardNumber.equals("") || boardNumber.length() <= 3) {
            DJIMainFragmentActivity.b(this.a, false);
            return;
        }
        String str;
        if (boardNumber == null || boardNumber.length() <= 3) {
            str = boardNumber;
        } else {
            str = boardNumber.substring(3);
        }
        if (!str.equals(DJIMainFragmentActivity.o(this.a))) {
            DJIMainFragmentActivity.a(this.a, str);
            DJIMainFragmentActivity.b(this.a, false);
            Intent intent = new Intent(DJIMainFragmentActivity.c(this.a), BatteryCheckService.class);
            intent.putExtra("service_type", 1);
            intent.putExtra(BatteryCheckService.b, str);
            intent.putExtra(BatteryCheckService.c, a);
            DJILogHelper.getInstance().LOGI("BatteryCheck", "Check battery service");
            this.a.startService(intent);
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
