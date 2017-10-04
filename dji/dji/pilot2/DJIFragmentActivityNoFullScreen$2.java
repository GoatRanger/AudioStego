package dji.pilot2;

import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraControlUpgrade;
import dji.midware.data.model.P3.DataCameraControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.control.a;

class DJIFragmentActivityNoFullScreen$2 implements Callback {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$2(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public boolean handleMessage(Message message) {
        boolean z = true;
        switch (message.what) {
            case 1:
                DJIFragmentActivityNoFullScreen.a(this.a, message.arg1, message.arg2, ((Integer) message.obj).intValue());
                break;
            case 100:
                this.a.onEventMainThread(DataCameraGetPushUpgradeStatus.getInstance());
                this.a.onEventMainThread(a.getInstance(this.a).h);
                DJILogHelper.getInstance().LOGD(DJIFragmentActivityNoFullScreen.e(), "base activity DJIUpgradeNoticeEvent");
                break;
            case 200:
                DJIFragmentActivityNoFullScreen.d(this.a);
                if (DJIFragmentActivityNoFullScreen.e(this.a) != 0) {
                    DJIFragmentActivityNoFullScreen.f(this.a).a(this.a.getString(R.string.app_upgrade_success, new Object[]{Integer.valueOf(DJIFragmentActivityNoFullScreen.e(this.a))}));
                    DJIFragmentActivityNoFullScreen.g(this.a).sendEmptyMessageDelayed(200, 1000);
                    break;
                }
                DJIFragmentActivityNoFullScreen.a(this.a, 5);
                DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Restart).start(new d(this) {
                    final /* synthetic */ DJIFragmentActivityNoFullScreen$2 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        DJIFragmentActivityNoFullScreen.g(this.a.a).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                DJIFragmentActivityNoFullScreen.f(this.a.a.a).dismiss();
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD(DJIFragmentActivityNoFullScreen.e(), "restart failed", false, true);
                    }
                });
                break;
            case 210:
                DJIFragmentActivityNoFullScreen.f(this.a).dismiss();
                break;
            case 300:
                DJIFragmentActivityNoFullScreen.h(this.a);
                break;
            case 400:
                DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen = this.a;
                if (DJIFragmentActivityNoFullScreen.i(this.a)) {
                    z = false;
                }
                DJIFragmentActivityNoFullScreen.a(dJIFragmentActivityNoFullScreen, z);
                DJIFragmentActivityNoFullScreen.h(this.a);
                DJIFragmentActivityNoFullScreen.g(this.a).sendEmptyMessageDelayed(400, 2000);
                break;
        }
        return false;
    }
}
