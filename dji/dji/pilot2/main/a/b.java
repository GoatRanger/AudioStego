package dji.pilot2.main.a;

import android.content.Context;
import com.dji.frame.c.c;
import dji.pilot.publics.objects.g;
import dji.pilot2.publics.b.a;
import dji.pilot2.utils.k;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static final String a = "connected";
    public static final String b = "never_connected";
    private static final String c = "http://www.djiexplore.com/cms_config/product_learnmore_%1$s_%2$s";
    private static final String d = "value";

    private static String a() {
        String str = "en";
        if (k.s().equals(a.p)) {
            str = "cn";
        }
        String str2 = a.r;
        if (k.r().equals(a.q)) {
            str2 = "pad";
        }
        return String.format(c, new Object[]{str, str2});
    }

    public static void a(final Context context) {
        try {
            c.b(context).a(a(), new dji.thirdparty.afinal.f.a<String>() {
                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    b.b(str, context);
                }

                public void a(Throwable th, int i, String str) {
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static void b(String str, Context context) {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).optString(d));
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    JSONObject optJSONObject = jSONObject.optJSONObject(obj);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString(a);
                        String optString2 = optJSONObject.optString(b);
                        if (optString.contains("http")) {
                            g.a(context, obj + a, optString);
                        }
                        if (optString2.contains("http")) {
                            g.a(context, obj + b, optString2);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
