package dji.pilot2;

import android.util.Log;
import dji.pilot.publics.objects.g;
import dji.pilot2.publics.object.DJINotificationDialog;
import dji.thirdparty.afinal.f.a;
import org.json.JSONException;
import org.json.JSONObject;

class DJIFragmentActivityNoFullScreen$9 extends a<String> {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$9(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        String b;
        Log.i("zyx", str);
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null) {
                boolean z = jSONObject.getBoolean("have_alert");
                boolean z2 = jSONObject.getBoolean("learn_more");
                int i = jSONObject.getInt("id");
                int b2 = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_id", 0);
                if (z) {
                    String string = jSONObject.getString("alert_content");
                    str2 = "";
                    if (z2) {
                        str2 = jSONObject.getString(dji.pilot2.main.a.a.j);
                    }
                    if (b2 != i) {
                        g.a(DJIFragmentActivityNoFullScreen.b(this.a), "notice_id", i);
                        g.a(DJIFragmentActivityNoFullScreen.b(this.a), "notice_content", string);
                        g.a(DJIFragmentActivityNoFullScreen.b(this.a), "notice_url", str2);
                        this.a.cU_.a(string, z2, str2);
                    } else if (!DJINotificationDialog.a) {
                        this.a.cU_.a(string, z2, str2);
                    }
                } else if (!DJINotificationDialog.a) {
                    str2 = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_content", "");
                    b = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_url", "");
                    if (!str2.isEmpty()) {
                        this.a.cU_.a(str2, z2, b);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (!DJINotificationDialog.a) {
                str2 = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_content", "");
                b = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_url", "");
                if (!str2.isEmpty()) {
                    this.a.cU_.a(str2, false, b);
                }
            }
        }
    }

    public void a(Throwable th, int i, String str) {
        if (!DJINotificationDialog.a) {
            String b = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_content", "");
            String b2 = g.b(DJIFragmentActivityNoFullScreen.b(this.a), "notice_url", "");
            if (!b.isEmpty()) {
                this.a.cU_.a(b, false, b2);
            }
        }
    }
}
