package com.ut.mini.core.e;

import com.ut.mini.core.e;
import com.ut.mini.d.n;
import com.ut.mini.internal.UTTeamWork;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class a extends com.ut.mini.core.e.a.a {
    private static a a = new a();

    public static a a() {
        return a;
    }

    private a() {
    }

    public void a(String str, String str2) {
        if (!n.a(str2)) {
            com.ut.mini.base.a.a(str2);
            e.a().g();
        }
    }

    public List<String> d() {
        List<String> arrayList = new ArrayList();
        arrayList.add("B01N4");
        return arrayList;
    }

    public void b() {
        com.ut.mini.base.a.a(com.ut.mini.base.a.a);
        e.a().h();
        e.a().a(null);
    }

    public void a(Map<String, String> map) {
        if (map != null && map.containsKey(UTTeamWork.DEBUG_API_URL) && map.containsKey(UTTeamWork.DEBUG_KEY)) {
            String str = (String) map.get(UTTeamWork.DEBUG_API_URL);
            String str2 = (String) map.get(UTTeamWork.DEBUG_KEY);
            if (!(n.a(str) || n.a(str2))) {
                com.ut.mini.base.a.a(str);
                e.a().g();
                e.a().a(str2);
            }
            if (map.containsKey("debug_sampling_option")) {
                e.a().b();
            }
        }
    }
}
