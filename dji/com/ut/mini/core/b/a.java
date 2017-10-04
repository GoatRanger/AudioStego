package com.ut.mini.core.b;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.b.a.c;
import com.ut.mini.core.d.b;
import com.ut.mini.core.e;
import com.ut.mini.d.n;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends com.ut.mini.core.e.a.a {
    private static a a = null;
    private c b = null;
    private boolean c = false;
    private List<b> d = new LinkedList();

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public synchronized void a(b bVar) {
        if (bVar != null) {
            this.d.add(bVar);
        }
    }

    private synchronized void a(c cVar) {
        this.b = cVar;
        if (this.d != null && this.d.size() > 0) {
            for (b a : this.d) {
                a.a();
            }
        }
    }

    public synchronized boolean b() {
        boolean z;
        if (this.b == null) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public boolean c() {
        return this.c;
    }

    public List<String> a(String str) {
        if (str != null) {
            return a(b.disassemble(str));
        }
        return null;
    }

    public synchronized List<String> a(Map<String, String> map) {
        List<String> linkedList;
        try {
            if (e.a().f() && e.a().c()) {
                linkedList = new LinkedList();
                linkedList.add("stm_d");
            } else if (map == null) {
                linkedList = null;
            } else {
                if (this.b != null) {
                    linkedList = this.b.a((Map) map);
                } else {
                    linkedList = null;
                }
                if (linkedList == null || linkedList.size() == 0) {
                    int i = 0;
                    if (map.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
                        String str = (String) map.get(UTLogFieldsScheme.EVENTID.toString());
                        if (str != null) {
                            try {
                                i = Integer.parseInt(str);
                            } catch (Exception e) {
                            }
                        }
                    }
                    linkedList = new ArrayList();
                    if (i == 1 || i == 61006) {
                        linkedList.add("stm_x");
                    } else if (i > 1000 && i < 2100) {
                        linkedList.add("stm_p");
                    } else if (i > 2100 && i < 2200) {
                        linkedList.add("stm_c");
                    } else if (i == 6699) {
                        linkedList.add("stm_d");
                    } else if (i == 19999) {
                        linkedList.add("stm_d");
                    } else {
                        linkedList.add("stm_nc");
                    }
                }
            }
        } catch (Exception e2) {
            linkedList = null;
        } catch (Throwable th) {
        }
        return linkedList;
    }

    public void a(String str, String str2) {
        boolean z = true;
        if (!n.a(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                if (!jSONObject.has("cec")) {
                    z = false;
                } else if (jSONObject.getInt("cec") != 1) {
                    z = false;
                }
                this.c = z;
                if (jSONObject.has("stms")) {
                    c cVar = new c();
                    cVar.a(jSONObject);
                    a(cVar);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> d() {
        List<String> arrayList = new ArrayList();
        arrayList.add("B01N17");
        return arrayList;
    }

    public void b(String str) {
    }
}
