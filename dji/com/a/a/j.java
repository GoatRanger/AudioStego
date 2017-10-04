package com.a.a;

import android.content.Context;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.usercenter.protocol.c;
import java.util.Collections;
import java.util.Map;

class j extends p {

    static class a extends a<j> {
        public /* synthetic */ p b(Map map) {
            return a(map);
        }

        a(Context context, c cVar, String str) {
            super(context, cVar, "project-settings-plan-" + str, j.class);
        }

        public j a(Map<String, Object> map) {
            return new j(map);
        }
    }

    static j a(Map<String, Object> map) {
        map.put(c.ad, Long.valueOf(System.currentTimeMillis()));
        return new j(map);
    }

    private j(Map<String, Object> map) {
        super(Collections.unmodifiableMap(map));
    }

    long a() {
        return a(c.ad, 0);
    }

    p b() {
        return a("plan");
    }

    p c() {
        p b = b();
        if (b == null) {
            return null;
        }
        return b.a(e.d);
    }

    p d() {
        return a("integrations");
    }
}
