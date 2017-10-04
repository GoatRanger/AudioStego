package com.nokia.maps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

public final class dt {
    private static dt a;
    private Gson b = new GsonBuilder().registerTypeAdapter(dh.class, new a()).registerTypeAdapter(dl.class, new c()).registerTypeAdapter(dr.class, new e()).registerTypeAdapter(dg.class, new b()).registerTypeAdapter(dk.class, new d()).registerTypeAdapter(dq.class, new f()).registerTypeAdapter(PlacesTilesLink.class, new g()).create();

    static class a implements InstanceCreator<dh> {
        a() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dh a(Type type) {
            return new dh();
        }
    }

    static class b implements InstanceCreator<dg> {
        b() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dg a(Type type) {
            return new dg();
        }
    }

    static class c implements InstanceCreator<dl> {
        c() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dl a(Type type) {
            return new dl();
        }
    }

    static class d implements InstanceCreator<dk> {
        d() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dk a(Type type) {
            return new dk();
        }
    }

    static class e implements InstanceCreator<dr> {
        e() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dr a(Type type) {
            return new dr();
        }
    }

    static class f implements InstanceCreator<dq> {
        f() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public dq a(Type type) {
            return new dq();
        }
    }

    static class g implements InstanceCreator<PlacesTilesLink> {
        g() {
        }

        public /* synthetic */ Object createInstance(Type type) {
            return a(type);
        }

        public PlacesTilesLink a(Type type) {
            return new PlacesTilesLink();
        }
    }

    public static synchronized dt a() {
        dt dtVar;
        synchronized (dt.class) {
            if (a == null) {
                a = new dt();
            }
            dtVar = a;
        }
        return dtVar;
    }

    private dt() {
    }

    public synchronized <T> T a(String str, Class<T> cls) {
        return this.b.fromJson(str, (Class) cls);
    }

    public synchronized String a(Object obj) {
        return this.b.toJson(obj);
    }
}
