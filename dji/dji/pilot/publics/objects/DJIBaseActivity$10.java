package dji.pilot.publics.objects;

import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraControlUpgrade;
import dji.midware.data.model.P3.DataCameraControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.control.a;

class DJIBaseActivity$10 implements Callback {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$10(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public boolean handleMessage(Message message) {
        boolean z = true;
        switch (message.what) {
            case 1:
                DJIBaseActivity.access$200(this.a, message.arg1, message.arg2, ((Integer) message.obj).intValue());
                break;
            case 100:
                this.a.onEventMainThread(DataCameraGetPushUpgradeStatus.getInstance());
                this.a.onEventMainThread(a.getInstance(this.a).h);
                break;
            case 200:
                DJIBaseActivity.access$310(this.a);
                if (DJIBaseActivity.access$300(this.a) != 0) {
                    DJIBaseActivity.access$400(this.a).a(this.a.getString(R.string.app_upgrade_success, new Object[]{Integer.valueOf(DJIBaseActivity.access$300(this.a))}));
                    this.a.handler.sendEmptyMessageDelayed(200, 1000);
                    break;
                }
                DJIBaseActivity.access$302(this.a, 5);
                DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Restart).start(new d(this) {
                    final /* synthetic */ DJIBaseActivity$10 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.handler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                DJIBaseActivity.access$400(this.a.a.a).dismiss();
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD(this.a.a.TAG, "restart failed", false, true);
                    }
                });
                break;
            case 210:
                DJIBaseActivity.access$400(this.a).dismiss();
                break;
            case 300:
                DJIBaseActivity.access$500(this.a);
                break;
            case 400:
                DJIBaseActivity dJIBaseActivity = this.a;
                if (DJIBaseActivity.access$600(this.a)) {
                    z = false;
                }
                DJIBaseActivity.access$602(dJIBaseActivity, z);
                DJIBaseActivity.access$500(this.a);
                this.a.handler.sendEmptyMessageDelayed(400, 2000);
                break;
        }
        return false;
    }
}
