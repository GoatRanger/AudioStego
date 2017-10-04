package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.e.ab;
import com.e.bc;
import com.e.z;

public class APSService extends Service {
    APSServiceBase a;

    public IBinder onBind(Intent intent) {
        try {
            return this.a.onBind(intent);
        } catch (Throwable th) {
            bc.a(th, "APSService", "onBind");
            return null;
        }
    }

    public void onCreate() {
        onCreate(this);
    }

    public void onCreate(Context context) {
        try {
            Context context2 = context;
            this.a = (APSServiceBase) z.a(context2, bc.a("2.5.0"), "com.amap.api.location.APSServiceWrapper", ab.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
        }
        try {
            if (this.a == null) {
                this.a = new ab(this);
            }
            this.a.onCreate();
        } catch (Throwable th2) {
            bc.a(th2, "APSService", "onCreate");
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.a.onDestroy();
        } catch (Throwable th) {
            bc.a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.a.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            bc.a(th, "APSService", "onStartCommand");
            return super.onStartCommand(intent, i, i2);
        }
    }
}
