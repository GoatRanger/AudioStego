package dji.pilot.active;

import android.content.Context;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycGetPushActiveRequest;
import dji.midware.data.model.P3.DataFlycSetActiveResult;
import dji.midware.data.model.P3.DataFlycSetActiveResult.DJIActivationState;
import dji.pilot.a.a;
import dji.pilot.publics.model.DJIOnBoardDataModel;
import dji.pilot.publics.model.DJIOnBoardResultDataModel;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.b;

public class d {
    private static String a = "e86aada34f6740568ba62ef372f9955f";
    private static String b = "https://dev.dji.com/api/onboardsdk/activate";
    private String c = "DJIOnboardActiver";
    private Context d;
    private DataFlycGetPushActiveRequest e;
    private boolean f;

    public d(Context context) {
        this.d = context.getApplicationContext();
        c.a().a((Object) this);
        this.e = DataFlycGetPushActiveRequest.getInstance();
    }

    public void a() {
        c.a().d((Object) this);
    }

    public void onEventBackgroundThread(DataFlycGetPushActiveRequest dataFlycGetPushActiveRequest) {
        if (!this.f) {
            this.f = true;
            b();
            DJILogHelper.getInstance().LOGE(this.c, "push activerequest ", false, true);
        }
    }

    private void b() {
        if (DJINetWorkReceiver.a(this.d)) {
            DJILogHelper.getInstance().LOGE(this.c, "push activerequest from net ", false, true);
            b bVar = new b();
            bVar.a("app_id", this.e.getAppId() + "");
            Object dJIOnBoardDataModel = new DJIOnBoardDataModel();
            dJIOnBoardDataModel.app_version = this.e.getAppVersion();
            dJIOnBoardDataModel.app_level = this.e.getAppLevel();
            dJIOnBoardDataModel.bundle_id = this.e.getAppBundleId();
            dJIOnBoardDataModel.serial_number = this.e.getDevSn();
            String a = h.a(dJIOnBoardDataModel);
            DJILogHelper.getInstance().LOGE(this.c, "onboard send " + this.e.getAppId() + "", false, true);
            DJILogHelper.getInstance().LOGE(this.c, "onboard send " + a, false, true);
            bVar.a("data", a(a));
            com.dji.frame.c.c.b(this.d).a(b, bVar, new 1(this));
            return;
        }
        DJILogHelper.getInstance().LOGE(this.c, "push activerequest no net ", false, true);
        a(DJIActivationState.b);
    }

    private String a(String str) {
        DJILogHelper.getInstance().LOGE(this.c, "getAesData2 " + str, false, false);
        String str2 = "";
        try {
            str2 = a.b(str, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DJILogHelper.getInstance().LOGE(this.c, "getAesData2 " + str2, false, false);
        return str2;
    }

    private String b(String str) {
        String str2 = "";
        try {
            str2 = a.a(str, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private void a(DJIActivationState dJIActivationState, DJIOnBoardResultDataModel dJIOnBoardResultDataModel) {
        DJILogHelper.getInstance().LOGE(this.c, "onboard secret_key " + dJIOnBoardResultDataModel.secret_key, false, true);
        DJILogHelper.getInstance().LOGE(this.c, "onboard secret_key level " + this.e.getAppLevel(), false, true);
        DJILogHelper.getInstance().LOGE(this.c, "onboard secret_key id " + this.e.getAppId(), false, true);
        DataFlycSetActiveResult.getInstance().a(dJIActivationState).a(this.e.getAppId()).b(this.e.getAppLevel()).a(dJIOnBoardResultDataModel.secret_key).start(new 2(this));
    }

    private void a(DJIActivationState dJIActivationState) {
        DataFlycSetActiveResult.getInstance().a(dJIActivationState).a(this.e.getAppId()).b(this.e.getAppLevel()).a("").start(new 3(this));
    }
}
