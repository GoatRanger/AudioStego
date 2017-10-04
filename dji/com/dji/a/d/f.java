package com.dji.a.d;

import android.os.Handler;
import android.os.Message;
import com.dji.a.a;

class f extends Handler {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void handleMessage(Message message) {
        a.c.b("ContentValues", "sendService:" + message.what + " returned.The result is " + message.arg1);
        this.a.a(message.what, false);
    }
}
