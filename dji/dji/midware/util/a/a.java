package dji.midware.util.a;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class a {
    public static final String a = "byte_rate";
    public static final String b = "total_byte_count";
    public static final String c = "width";
    public static final String d = "height";
    public static final String e = "key_frame_num";
    public static final String f = "sps_pps_num";
    private static final int g = 0;
    private static Map<String, a> h = new LinkedHashMap();
    private static Map<String, Map<String, Object>> i;
    private static a j;
    private static HandlerThread k;
    private static Handler l;
    private String m;
    private LinkedHashMap<String, Object> n = new LinkedHashMap();

    public interface a {
        void a(Map<String, Map<String, Object>> map);
    }

    public static a getInstance(String str) {
        a aVar = (a) h.get(str);
        if (aVar != null) {
            return aVar;
        }
        aVar = new a(str);
        h.put(str, aVar);
        return aVar;
    }

    public static int a() {
        return h.size();
    }

    public static Set<String> b() {
        return h.keySet();
    }

    public static ArrayList<a> c() {
        return new ArrayList(h.values());
    }

    private static void c(Map<String, Map<String, Object>> map) {
        if (j != null) {
            j.a(map);
        }
    }

    public static void d() {
        for (a aVar : h.values()) {
            if (aVar != null) {
                aVar.g();
            }
        }
        h.clear();
    }

    public static void a(a aVar) {
        if (!(k == null && l == null)) {
            e();
        }
        j = aVar;
        k = new HandlerThread("stream data observing thread");
        k.start();
        l = new Handler(k.getLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        a.i = new LinkedHashMap();
                        for (a aVar : a.h.values()) {
                            if (aVar != null) {
                                Map a = aVar.l();
                                if (a.j != null) {
                                    a.i.put(aVar.m, a);
                                }
                            }
                        }
                        a.c(a.i);
                        sendEmptyMessageDelayed(0, 1000);
                        return;
                    default:
                        return;
                }
            }
        };
        l.sendEmptyMessage(0);
    }

    public static void e() {
        if (l != null) {
            l.removeCallbacksAndMessages(null);
            l = null;
        }
        if (k != null) {
            if (k.isAlive()) {
                if (VERSION.SDK_INT >= 18) {
                    k.quitSafely();
                } else {
                    k.quit();
                }
            }
            k = null;
        }
        j = null;
    }

    public static boolean f() {
        return (k == null || !k.isAlive() || l == null) ? false : true;
    }

    private a(String str) {
        this.m = str;
    }

    public void a(String str, Object obj) {
        if (f()) {
            if ("width".equals(str) || "height".equals(str)) {
                if (obj instanceof Number) {
                    this.n.put(str, obj);
                }
            } else if (obj instanceof Number) {
                Object obj2 = this.n.get(str);
                if (obj2 == null || !(obj2 instanceof Number)) {
                    this.n.put(str, obj);
                } else {
                    this.n.put(str, Integer.valueOf(((Number) obj2).intValue() + ((Number) obj).intValue()));
                }
            }
            if (a.equals(str)) {
                a(b, obj);
            }
        }
    }

    private void k() {
        if (this.n != null) {
            for (String str : this.n.keySet()) {
                if (!b.equals(str) && (this.n.get(str) instanceof Number)) {
                    this.n.put(str, Integer.valueOf(0));
                }
            }
        }
    }

    private Map<String, Object> l() {
        if (this.n == null) {
            return null;
        }
        Map<String, Object> map = (Map) this.n.clone();
        k();
        return map;
    }

    public void g() {
        this.n = null;
        h.remove(this.m);
    }
}
