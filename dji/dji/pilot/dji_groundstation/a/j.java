package dji.pilot.dji_groundstation.a;

import android.content.Context;
import com.here.android.mpa.mapping.Map;
import com.sina.weibo.sdk.component.ShareRequestParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private static final String a = "SmartModeUIJsonParser";
    private static j b = null;
    private JSONObject c = null;

    public enum a {
        mode("mode"),
        view("view"),
        title("title"),
        lefttop("lefttop"),
        righttop("righttop"),
        backto("backto"),
        g(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG),
        jumpto("jumpto"),
        color("color"),
        bgcolor("bgcolor"),
        buttons("buttons"),
        width("width"),
        height("height"),
        x("x"),
        y("y"),
        gravity("gravity"),
        modal("modal");
        
        private String r;

        private a(String str) {
            this.r = "";
            this.r = str;
        }

        public String a() {
            return this.r;
        }
    }

    public static synchronized j getInstance() {
        j jVar;
        synchronized (j.class) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }

    public Object a(String str, JSONObject jSONObject) {
        Object obj = null;
        if (!(jSONObject == null || jSONObject.isNull(str))) {
            try {
                obj = jSONObject.get(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public JSONObject a(String str) {
        if (str == null) {
            this.c = null;
        } else {
            this.c = null;
            try {
                this.c = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                return this.c;
            }
        }
        return this.c;
    }

    public String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        if (jSONObject.isNull(a.mode.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.mode.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int a(JSONObject jSONObject, Context context) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.view.a()) || context == null)) {
            try {
                jSONObject.getString(a.view.a());
                i = context.getResources().getIdentifier(jSONObject.getString(a.view.a()), "layout", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    private JSONObject o(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        if (!(jSONObject == null || jSONObject.isNull(a.lefttop.a()))) {
            try {
                jSONObject2 = jSONObject.getJSONObject(a.lefttop.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    public int b(JSONObject jSONObject, Context context) {
        JSONObject o = o(jSONObject);
        if (o == null) {
            return 0;
        }
        return i(o, context);
    }

    public int c(JSONObject jSONObject, Context context) {
        JSONObject o = o(jSONObject);
        if (o == null) {
            return 0;
        }
        return f(o, context);
    }

    public String b(JSONObject jSONObject) {
        JSONObject o = o(jSONObject);
        if (o == null) {
            return "";
        }
        return i(o);
    }

    public String c(JSONObject jSONObject) {
        JSONObject o = o(jSONObject);
        if (o == null) {
            return "#ffffff";
        }
        return g(o);
    }

    private JSONObject p(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        if (!(jSONObject == null || jSONObject.isNull(a.righttop.a()))) {
            try {
                jSONObject2 = jSONObject.getJSONObject(a.righttop.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    public int d(JSONObject jSONObject, Context context) {
        JSONObject p = p(jSONObject);
        if (p == null) {
            return 0;
        }
        return i(p, context);
    }

    public int e(JSONObject jSONObject, Context context) {
        JSONObject p = p(jSONObject);
        if (p == null) {
            return 0;
        }
        return f(p, context);
    }

    public String d(JSONObject jSONObject) {
        JSONObject p = p(jSONObject);
        if (p == null) {
            return "#ffffff";
        }
        return g(p);
    }

    public String e(JSONObject jSONObject) {
        JSONObject p = p(jSONObject);
        if (p == null) {
            return "";
        }
        return i(p);
    }

    public JSONArray f(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        if (!(jSONObject == null || jSONObject.isNull(a.buttons.a()))) {
            try {
                jSONArray = jSONObject.getJSONArray(a.buttons.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    public String g(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "#ffffff";
        }
        if (jSONObject.isNull(a.color.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.color.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "#ffffff";
        }
    }

    public String h(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        if (jSONObject.isNull(a.bgcolor.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.bgcolor.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private int i(JSONObject jSONObject, Context context) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.g.a()) || context == null)) {
            try {
                i = context.getResources().getIdentifier(jSONObject.getString(a.g.a()), "drawable", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int f(JSONObject jSONObject, Context context) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.title.a()) || context == null)) {
            try {
                i = context.getResources().getIdentifier(jSONObject.getString(a.title.a()), "string", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public String i(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        if (jSONObject.isNull(a.jumpto.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.jumpto.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public double g(JSONObject jSONObject, Context context) {
        if (context == null) {
            return 0.0d;
        }
        if (jSONObject == null) {
            return 0.0d;
        }
        if (jSONObject.isNull(a.height.a())) {
            return 0.0d;
        }
        try {
            double d = jSONObject.getDouble(a.height.a());
            if (0.0d < d && 1.0d >= d) {
                return d * ((double) f.c(context));
            }
            if (d == Map.MOVE_PRESERVE_ZOOM_LEVEL || d == -2.0d) {
                return d;
            }
            return f.a(d, context);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public int j(JSONObject jSONObject) {
        int i = 0;
        if (!(jSONObject == null || jSONObject.isNull(a.x.a()))) {
            try {
                i = jSONObject.getInt(a.x.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int k(JSONObject jSONObject) {
        int i = 0;
        if (!(jSONObject == null || jSONObject.isNull(a.y.a()))) {
            try {
                i = jSONObject.getInt(a.y.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int l(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return 17;
        }
        if (jSONObject.isNull(a.gravity.a())) {
            return 17;
        }
        try {
            for (String str : jSONObject.getString(a.gravity.a()).split("\\|")) {
                if (str.equals("left")) {
                    i |= 3;
                } else if (str.equals("top")) {
                    i |= 48;
                } else if (str.equals("bottom")) {
                    i |= 80;
                } else if (str.equals("right")) {
                    i |= 5;
                } else if (str.equals("center")) {
                    i |= 17;
                } else if (str.equals("center_horizontal")) {
                    i |= 1;
                } else if (str.equals("center_vertical")) {
                    i |= 16;
                }
            }
            return i;
        } catch (JSONException e) {
            e.printStackTrace();
            return 17;
        }
    }

    public boolean m(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject == null || jSONObject.isNull(a.modal.a())) {
            return false;
        }
        try {
            if (jSONObject.getInt(a.modal.a()) != 1) {
                z = false;
            }
            return z;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String n(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        if (jSONObject.isNull(a.backto.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.backto.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public double h(JSONObject jSONObject, Context context) {
        if (context == null) {
            return 0.0d;
        }
        if (jSONObject == null) {
            return 0.0d;
        }
        if (jSONObject.isNull(a.width.a())) {
            return 0.0d;
        }
        try {
            double d = jSONObject.getDouble(a.width.a());
            if (0.0d < d && 1.0d >= d) {
                return d * ((double) f.b(context));
            }
            if (d == Map.MOVE_PRESERVE_ZOOM_LEVEL || d == -2.0d) {
                return d;
            }
            return f.a(d, context);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }
}
