package com.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.alipay.sdk.j.f;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AmapLoc;
import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class ar {
    a a = null;
    private String b = null;
    private Context c = null;
    private boolean d = true;
    private cs e = null;
    private ServiceConnection f = null;
    private ServiceConnection g = null;
    private Intent h = new Intent();
    private String i = "com.autonavi.minimap";
    private String j = "com.amap.api.service.AMapService";
    private String k = "com.autonavi.minimap.LBSConnectionService";
    private boolean l = false;
    private boolean m = false;
    private String n = "invaid type";
    private String o = "empty appkey";
    private String p = "refused";
    private String q = f.b;

    public interface a {
        void a(int i);
    }

    ar(Context context) {
        this.c = context;
        try {
            this.b = cy.a(bb.a(cu.f(context).getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            bc.a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    private AmapLoc a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        byte[] b;
        String str;
        JSONObject jSONObject;
        AmapLoc amapLoc;
        if (bundle.containsKey(d.M)) {
            try {
                b = bb.b(cy.b(bundle.getString(d.M)), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n");
            } catch (Throwable th) {
                bc.a(th, "ConnectionServiceManager", "parseData part");
            }
            if (bundle.containsKey("result")) {
                return null;
            }
            try {
                str = new String(bb.a(b, cy.b(bundle.getString("result"))), "utf-8");
                if (str != null) {
                    return null;
                }
                jSONObject = new JSONObject(str);
                if (jSONObject.has("error")) {
                    amapLoc = new AmapLoc(jSONObject);
                    amapLoc.setProvider("lbs");
                    amapLoc.setLocationType(7);
                    if ("WGS84".equals(amapLoc.getType()) && bc.a(amapLoc.getLat(), amapLoc.getLon())) {
                        DPoint a = cr.a(this.c, amapLoc.getLon(), amapLoc.getLat());
                        amapLoc.setLat(a.getLatitude());
                        amapLoc.setLon(a.getLongitude());
                    }
                    return amapLoc;
                }
                String string;
                string = jSONObject.getString("error");
                if (this.n.equals(string)) {
                    this.d = false;
                }
                if (this.o.equals(string)) {
                    this.d = false;
                }
                if (this.p.equals(string)) {
                    this.d = false;
                }
                return this.q.equals(string) ? null : null;
            } catch (Throwable th2) {
                bc.a(th2, ar.class.getName(), "parseData");
                return null;
            }
        }
        b = null;
        if (bundle.containsKey("result")) {
            return null;
        }
        str = new String(bb.a(b, cy.b(bundle.getString("result"))), "utf-8");
        if (str != null) {
            return null;
        }
        jSONObject = new JSONObject(str);
        if (jSONObject.has("error")) {
            amapLoc = new AmapLoc(jSONObject);
            amapLoc.setProvider("lbs");
            amapLoc.setLocationType(7);
            DPoint a2 = cr.a(this.c, amapLoc.getLon(), amapLoc.getLat());
            amapLoc.setLat(a2.getLatitude());
            amapLoc.setLon(a2.getLongitude());
            return amapLoc;
        }
        string = jSONObject.getString("error");
        if (this.n.equals(string)) {
            this.d = false;
        }
        if (this.o.equals(string)) {
            this.d = false;
        }
        if (this.p.equals(string)) {
            this.d = false;
        }
        if (this.q.equals(string)) {
        }
    }

    public void a() {
        c();
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        if (this.a != null) {
            this.a.a(-1);
        }
        this.d = true;
        this.l = false;
        this.m = false;
    }

    public void a(final a aVar) {
        try {
            this.a = aVar;
            if (this.f == null) {
                this.f = new ServiceConnection(this) {
                    final /* synthetic */ ar b;

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        this.b.e = com.e.cs.a.a(iBinder);
                        aVar.a(0);
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        this.b.e = null;
                    }
                };
            }
            if (this.g == null) {
                this.g = new ServiceConnection(this) {
                    final /* synthetic */ ar a;

                    {
                        this.a = r1;
                    }

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
        } catch (Throwable th) {
            bc.a(th, "ConnectionServiceManager", "init");
        }
    }

    boolean b() {
        try {
            if (bo.m()) {
                this.h.putExtra("appkey", this.b);
                this.h.setComponent(new ComponentName(this.i, this.j));
                this.l = this.c.bindService(this.h, this.f, 1);
            }
            if (bo.n()) {
                Intent intent = new Intent();
                intent.putExtra("appkey", this.b);
                intent.setComponent(new ComponentName(this.i, this.k));
                this.m = this.c.bindService(intent, this.g, 1);
            }
            return this.l && this.m;
        } catch (Throwable th) {
            return false;
        }
    }

    void c() {
        try {
            if (this.l) {
                this.c.unbindService(this.f);
            }
            if (this.m) {
                this.c.unbindService(this.g);
            }
        } catch (Throwable e) {
            bc.a(e, "ConnectionServiceManager", "unbindService");
        } catch (Throwable e2) {
            bc.a(e2, "ConnectionServiceManager", "unbindService");
        }
        this.e = null;
    }

    AmapLoc d() {
        AmapLoc amapLoc = null;
        try {
            if (this.d && this.l) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "corse");
                bundle.putString("appkey", this.b);
                this.e.a(bundle);
                if (bundle.size() >= 1) {
                    amapLoc = a(bundle);
                }
            }
        } catch (Throwable e) {
            bc.a(e, "ConnectionServiceManager", "sendCommand");
        } catch (Throwable e2) {
            bc.a(e2, "ConnectionServiceManager", "sendCommand");
        }
        return amapLoc;
    }
}
