package dji.pilot.fpv.control;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class DJIGenSettingDataManager$a extends Handler {
    private final WeakReference<DJIGenSettingDataManager> a;

    public DJIGenSettingDataManager$a(DJIGenSettingDataManager dJIGenSettingDataManager) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIGenSettingDataManager);
    }

    public void handleMessage(Message message) {
        DJIGenSettingDataManager dJIGenSettingDataManager = (DJIGenSettingDataManager) this.a.get();
        if (dJIGenSettingDataManager != null) {
            switch (message.what) {
                case 4096:
                    DJIGenSettingDataManager.a(dJIGenSettingDataManager, message.arg1, message.arg2, message.obj);
                    return;
                case 4097:
                    DJIGenSettingDataManager.b(dJIGenSettingDataManager, message.arg1, message.arg2, message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
