package dji.setting.ui.vision;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.e.d;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class b implements a {
    private static final Object g = new Object();
    private final ArrayList<String> f = new ArrayList(3);
    private final Handler h = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    this.a.a();
                    break;
            }
            return true;
        }
    });

    private static final class a {
        private static final b a = new b();

        private a() {
        }
    }

    public void a(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            synchronized (g) {
                int i = 0;
                while (i < strArr.length) {
                    if (!(strArr[i] == null || this.f.contains(strArr[i]))) {
                        this.f.add(strArr[i]);
                    }
                    i++;
                }
            }
            this.h.removeMessages(4096);
            this.h.sendEmptyMessageDelayed(4096, 200);
        }
    }

    private void a() {
        String[] strArr = null;
        synchronized (g) {
            int size = this.f.size();
            if (size > 0) {
                strArr = new String[size];
                this.f.toArray(strArr);
                this.f.clear();
            }
        }
        if (strArr != null) {
            new DataFlycGetParams().setInfos(strArr).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    for (String aVar : strArr) {
                        c.a().e(new dji.setting.ui.vision.a.a(aVar));
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    public static b getInstance() {
        return a.a;
    }
}
