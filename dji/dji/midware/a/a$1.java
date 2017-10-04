package dji.midware.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.alipay.e.a.a.c.a.a;
import com.tencent.android.tpush.common.Constants;

class a$1 extends BroadcastReceiver {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        boolean z = extras.getBoolean("isEvent");
        String string = extras.getString(Constants.FLAG_PACKAGE_NAME);
        int i = extras.getInt("port");
        if (z) {
            a.a(this.a).a(string, i);
        } else {
            a.b(this.a).a(string, i);
        }
        if (a.c(this.a) != null && string.equals(a.c(this.a)) && System.currentTimeMillis() - a.d(this.a) < a.b && a.b(this.a).b(string) && a.a(this.a).c(string) && !a.c(this.a).equals(a.b(this.a).c())) {
            a.b(this.a).a(string);
            a.a(this.a).a(string);
        }
    }
}
