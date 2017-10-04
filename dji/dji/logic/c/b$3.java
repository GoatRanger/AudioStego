package dji.logic.c;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.wifi.WifiManager;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiSwitchSDR;
import dji.midware.e.d;

class b$3 implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ b b;

    b$3(b bVar, Context context) {
        this.b = bVar;
        this.a = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DataWifiSwitchSDR.getInstance().start(new d(this) {
            final /* synthetic */ b$3 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                ((WifiManager) this.a.a.getSystemService("wifi")).setWifiEnabled(false);
                b.a(this.a.b, true);
                b.a(this.a.b).sendEmptyMessageDelayed(1, 10000);
            }

            public void onFailure(a aVar) {
            }
        });
    }
}
