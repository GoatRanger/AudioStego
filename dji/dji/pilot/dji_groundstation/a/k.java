package dji.pilot.dji_groundstation.a;

import android.content.Context;
import com.here.android.mpa.mapping.Map;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k {
    private static final String a = "UIJsonParser";
    private static k b = null;
    private JSONObject c = null;

    public enum a {
        product("product"),
        b(ParamKey.COUNT),
        rowcount("rowcount"),
        columncount("columncount"),
        infos("infos"),
        f(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG),
        title("title"),
        width("width"),
        height("height"),
        x("x"),
        y("y"),
        gravity("gravity"),
        modal("modal"),
        bottomview("bottomview"),
        is_gs_func("is_gs_func"),
        jumpto("jumpto"),
        backto("backto"),
        floorflyc("floorflyc"),
        ceilflyc("ceilflyc");
        
        private String t;

        private a(String str) {
            this.t = "";
            this.t = str;
        }

        public String a() {
            return this.t;
        }
    }

    public static synchronized k getInstance() {
        k kVar;
        synchronized (k.class) {
            if (b == null) {
                b = new k();
            }
            kVar = b;
        }
        return kVar;
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
        if (jSONObject.isNull(a.product.a())) {
            return "";
        }
        try {
            return jSONObject.getString(a.product.a());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String b(JSONObject jSONObject) {
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

    public int c(JSONObject jSONObject) {
        int i = 0;
        if (!(jSONObject == null || jSONObject.isNull(a.b.a()))) {
            try {
                i = jSONObject.getInt(a.b.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int d(JSONObject jSONObject) {
        int i = 0;
        if (!(jSONObject == null || jSONObject.isNull(a.columncount.a()))) {
            try {
                i = jSONObject.getInt(a.columncount.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public double a(JSONObject jSONObject, Context context) {
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

    public double b(JSONObject jSONObject, Context context) {
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

    public int e(JSONObject jSONObject) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.floorflyc.a()))) {
            try {
                i = jSONObject.getInt(a.floorflyc.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int f(JSONObject jSONObject) {
        int i = 10000;
        if (!(jSONObject == null || jSONObject.isNull(a.ceilflyc.a()))) {
            try {
                i = jSONObject.getInt(a.ceilflyc.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int g(JSONObject jSONObject) {
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

    public int h(JSONObject jSONObject) {
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

    public int i(JSONObject jSONObject) {
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

    public boolean j(JSONObject jSONObject) {
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

    public int k(JSONObject jSONObject) {
        int i = 0;
        if (!(jSONObject == null || jSONObject.isNull(a.rowcount.a()))) {
            try {
                i = jSONObject.getInt(a.rowcount.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int c(JSONObject jSONObject, Context context) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.f.a()) || context == null)) {
            try {
                i = context.getResources().getIdentifier(jSONObject.getString(a.f.a()), "drawable", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int d(JSONObject jSONObject, Context context) {
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

    public boolean l(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject == null || jSONObject.isNull(a.is_gs_func.a())) {
            return false;
        }
        try {
            if (jSONObject.getInt(a.is_gs_func.a()) != 1) {
                z = false;
            }
            return z;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String m(JSONObject jSONObject) {
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

    public JSONArray n(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        if (!(jSONObject == null || jSONObject.isNull(a.infos.a()))) {
            try {
                jSONArray = jSONObject.getJSONArray(a.infos.a());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    public int e(JSONObject jSONObject, Context context) {
        int i = -1;
        if (!(jSONObject == null || jSONObject.isNull(a.bottomview.a()) || context == null)) {
            try {
                jSONObject.getString(a.bottomview.a());
                i = context.getResources().getIdentifier(jSONObject.getString(a.bottomview.a()), "layout", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
