package com.facebook.internal;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

class v {

    private static final class a implements Entry<String, Object> {
        private final String a;
        private final Object b;

        public /* synthetic */ Object getKey() {
            return a();
        }

        a(String str, Object obj) {
            this.a = str;
            this.b = obj;
        }

        @SuppressLint({"FieldGetter"})
        public String a() {
            return this.a;
        }

        public Object getValue() {
            return this.b;
        }

        public Object setValue(Object obj) {
            throw new UnsupportedOperationException("JSONObjectEntry is immutable");
        }
    }

    v() {
    }

    static void a(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            keys.next();
            keys.remove();
        }
    }

    static boolean a(JSONObject jSONObject, Object obj) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            Object opt = jSONObject.opt((String) keys.next());
            if (opt != null && opt.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    static Set<Entry<String, Object>> b(JSONObject jSONObject) {
        Set hashSet = new HashSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashSet.add(new a(str, jSONObject.opt(str)));
        }
        return hashSet;
    }

    static Set<String> c(JSONObject jSONObject) {
        Set hashSet = new HashSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            hashSet.add(keys.next());
        }
        return hashSet;
    }

    static void a(JSONObject jSONObject, Map<String, Object> map) {
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.putOpt((String) entry.getKey(), entry.getValue());
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    static Collection<Object> d(JSONObject jSONObject) {
        Collection arrayList = new ArrayList();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            arrayList.add(jSONObject.opt((String) keys.next()));
        }
        return arrayList;
    }
}
