package dji.pilot2.publics.a;

import android.util.Log;
import dji.thirdparty.afinal.f.a;

class a$5 extends a<String> {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        Log.d("fly_unlimit", str);
        a.a(this.a).edit().putBoolean("need_upload", false).apply();
        a.a(this.a, false);
    }

    public void a(Throwable th, int i, String str) {
        Log.d("fly_unlimit", str);
        a.a(this.a, false);
    }
}
