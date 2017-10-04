package dji.pilot.usercenter.b;

import dji.thirdparty.afinal.f.a;
import org.json.JSONException;
import org.json.JSONObject;

class f$1 extends a<String> {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        try {
            if (new JSONObject(str).getInt("status") != 0) {
                this.a.a(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th, int i, String str) {
    }
}
