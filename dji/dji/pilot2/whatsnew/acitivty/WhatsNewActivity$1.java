package dji.pilot2.whatsnew.acitivty;

import android.util.Log;
import dji.pilot.usercenter.protocol.e$a;

class WhatsNewActivity$1 implements e$a {
    final /* synthetic */ WhatsNewActivity a;

    WhatsNewActivity$1(WhatsNewActivity whatsNewActivity) {
        this.a = whatsNewActivity;
    }

    public void a(int i, int i2, int i3, Object obj, Object obj2) {
        WhatsNewActivity.a(this.a, i, i2, obj2);
        Log.i("zhang", "success flightdata");
    }

    public void a(int i, int i2, int i3, Object obj) {
        WhatsNewActivity.a(this.a, i, i2, i3, obj);
        Log.i("zhang", "fail flightdata");
    }

    public void a(int i, long j, long j2, int i2, Object obj) {
        WhatsNewActivity.a(this.a, i, j, j2, obj);
        Log.i("zhang", "current:" + j);
    }

    public void a(int i, boolean z, int i2, Object obj) {
    }
}
