package com.dji.a.e;

import com.dji.a.f.d;
import com.dji.a.f.e;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private final String a;
    private final a b = a.a;
    private final b c;
    private com.dji.a.b.a d = com.dji.a.b.a.LOG_DATA;

    public enum a {
        a
    }

    public enum b {
        MSGPACK;

        public abstract String a();
    }

    public a(String str) {
        this.a = str;
        this.c = b.MSGPACK;
    }

    public boolean a(byte[] bArr, com.dji.a.b bVar) {
        try {
            URL url = new URL(this.a);
            e eVar = new e();
            eVar.a(a(bArr));
            eVar.a(bVar.e());
            eVar.b(bVar.e());
            eVar.a(url, this.b, bArr, this.c);
            return a(eVar);
        } catch (Throwable e) {
            throw new d("Error while sending  report via Http " + this.b.name(), e);
        }
    }

    private boolean a(e eVar) {
        if (eVar.b() != 200) {
            return false;
        }
        try {
            String str = new String(eVar.a(), "utf-8");
            if (new JSONObject(str).getBoolean("success")) {
                return true;
            }
            com.dji.a.a.c.c(com.dji.a.a.a, "Error in parse res data=" + str);
            return false;
        } catch (JSONException e) {
            com.dji.a.a.c.c(com.dji.a.a.a, "Error in parse res data error=" + e.toString());
            return false;
        } catch (UnsupportedEncodingException e2) {
            com.dji.a.a.c.c(com.dji.a.a.a, "Error in parse res data error=" + e2.toString());
            return false;
        }
    }

    private HashMap<String, String> a(byte[] bArr) {
        HashMap<String, String> d = com.dji.a.a.a().d();
        d.put(dji.pilot.college.b.b.o, com.dji.a.a.a().h());
        d.put("type", this.d.a());
        d.put("sign", d.a(bArr, com.dji.a.a.a().g()));
        return d;
    }

    public void a(com.dji.a.b.a aVar) {
        this.d = aVar;
    }
}
