package dji.setting.ui.flyc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import java.util.HashSet;

public class a {
    private static final int b = 0;
    private static final int c = 1;
    private HashSet<String> a = new HashSet();
    private Handler d = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ a a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.b((String[]) message.obj);
                    return;
                case 1:
                    this.a.c();
                    return;
                default:
                    return;
            }
        }
    };

    public static class a {
        public String a;

        public a(String str) {
            this.a = str;
        }
    }

    public static class b {
        public String a;

        public b(String str) {
            this.a = str;
        }
    }

    private static class c {
        private static a a = new a();

        private c() {
        }
    }

    public static boolean a() {
        if (dji.pilot.publics.e.a.d(i.getInstance().c())) {
            return true;
        }
        return false;
    }

    public static a b() {
        return c.a;
    }

    public void a(String... strArr) {
        Message obtainMessage = this.d.obtainMessage(0);
        obtainMessage.obj = strArr;
        this.d.sendMessage(obtainMessage);
    }

    public void a(String[] strArr, int i) {
    }

    private void b(String[] strArr) {
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                if (!this.a.contains(strArr[i])) {
                    this.a.add(strArr[i]);
                }
            }
            if (this.d.hasMessages(1)) {
                this.d.removeMessages(1);
            }
            this.d.sendMessageDelayed(this.d.obtainMessage(1), 200);
        }
    }

    private void c() {
        final String[] strArr = new String[this.a.size()];
        this.a.toArray(strArr);
        DataFlycGetParams.getInstance().setInfos(strArr).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                for (String aVar : strArr) {
                    dji.thirdparty.a.c.a().e(new a(aVar));
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                for (String bVar : strArr) {
                    dji.thirdparty.a.c.a().e(new b(bVar));
                }
            }
        });
        this.a.clear();
    }

    public boolean a(Context context) {
        if (!DataOsdGetPushCommon.getInstance().isGetted()) {
            Toast.makeText(context.getApplicationContext(), R.string.setting_ui_rc_tip_disconnect, 0).show();
            return true;
        } else if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
            return false;
        } else {
            new dji.setting.ui.flyc.imu.a(context).show();
            return true;
        }
    }
}
