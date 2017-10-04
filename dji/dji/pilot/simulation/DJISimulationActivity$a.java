package dji.pilot.simulation;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetSimFlyStatus.FlySimStatus;
import dji.pilot.usercenter.protocol.d;
import java.lang.ref.WeakReference;
import java.util.Locale;

final class DJISimulationActivity$a extends Handler {
    private final WeakReference<DJISimulationActivity> a;

    public DJISimulationActivity$a(DJISimulationActivity dJISimulationActivity) {
        this.a = new WeakReference(dJISimulationActivity);
    }

    public void handleMessage(Message message) {
        DJISimulationActivity dJISimulationActivity = (DJISimulationActivity) this.a.get();
        if (dJISimulationActivity != null && !dJISimulationActivity.isFinishing()) {
            switch (message.what) {
                case 0:
                    DJISimulationActivity.a(dJISimulationActivity).setEnabled(true);
                    if (message.arg1 == 0) {
                        DJISimulationActivity.a(dJISimulationActivity, DJISimulationActivity.b(dJISimulationActivity).getFlySimStatus());
                        DJISimulationActivity.d(dJISimulationActivity).setText(DJISimulationActivity.c(dJISimulationActivity).toString());
                        DJISimulationActivity.e(dJISimulationActivity).setEnabled(true);
                        if (DJISimulationActivity.c(dJISimulationActivity) == FlySimStatus.NORMAL) {
                            DJISimulationActivity.a(dJISimulationActivity, true);
                        }
                        DJISimulationActivity.a(dJISimulationActivity, 0);
                        return;
                    }
                    DJISimulationActivity.f(dJISimulationActivity);
                    Toast.makeText(dJISimulationActivity.getApplicationContext(), "getStatus fail by[" + ((a) message.obj).toString() + d.H, 0).show();
                    if (DJISimulationActivity.g(dJISimulationActivity) < 4) {
                        DJISimulationActivity.h(dJISimulationActivity);
                        return;
                    }
                    return;
                case 1:
                    DJISimulationActivity.e(dJISimulationActivity).setEnabled(true);
                    if (message.arg1 == 0) {
                        DJISimulationActivity.a(dJISimulationActivity, 0);
                        Toast.makeText(dJISimulationActivity.getApplicationContext(), "start success", 0).show();
                        return;
                    }
                    DJISimulationActivity.f(dJISimulationActivity);
                    Toast.makeText(dJISimulationActivity.getApplicationContext(), "start fail by[" + ((a) message.obj).toString() + d.H, 0).show();
                    if (DJISimulationActivity.g(dJISimulationActivity) < 4) {
                        DJISimulationActivity.a(dJISimulationActivity, true);
                        return;
                    }
                    return;
                case 2:
                    removeMessages(3);
                    if (message.arg1 == 0) {
                        Toast.makeText(dJISimulationActivity.getApplicationContext(), "exit success", 0).show();
                        return;
                    }
                    Toast.makeText(dJISimulationActivity.getApplicationContext(), "exit fail by[" + ((a) message.obj).toString() + d.H, 0).show();
                    return;
                case 3:
                    DJISimulationActivity.j(dJISimulationActivity).setText(String.format(Locale.US, "A:%1$d;T:%2$d;E:%3$d;R:%4$d", new Object[]{Integer.valueOf(DJISimulationActivity.i(dJISimulationActivity).getAileron()), Integer.valueOf(DJISimulationActivity.i(dJISimulationActivity).getThrottle()), Integer.valueOf(DJISimulationActivity.i(dJISimulationActivity).getElevator()), Integer.valueOf(DJISimulationActivity.i(dJISimulationActivity).getRudder())}));
                    return;
                default:
                    return;
            }
        }
    }
}
