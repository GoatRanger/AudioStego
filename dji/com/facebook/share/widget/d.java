package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.h;
import com.facebook.internal.f;
import com.facebook.internal.i;
import com.facebook.internal.j;
import com.facebook.internal.o;
import com.facebook.share.internal.m;
import com.facebook.share.internal.q;
import java.util.ArrayList;
import java.util.List;

public class d extends j<String, a> {
    private static final String b = "game_group_join";
    private static final int c = com.facebook.internal.f.b.AppGroupJoin.a();

    public static final class a {
        private final Bundle a;

        private a(Bundle bundle) {
            this.a = bundle;
        }

        public Bundle a() {
            return this.a;
        }
    }

    private class b extends a {
        final /* synthetic */ d b;

        private b(d dVar) {
            this.b = dVar;
            super(dVar);
        }

        public boolean a(String str) {
            return true;
        }

        public com.facebook.internal.b b(String str) {
            com.facebook.internal.b d = this.b.d();
            Bundle bundle = new Bundle();
            bundle.putString("id", str);
            i.a(d, d.b, bundle);
            return d;
        }
    }

    public static boolean e() {
        return true;
    }

    public static void a(Activity activity, String str) {
        new d(activity).b(str);
    }

    public static void a(Fragment fragment, String str) {
        a(new o(fragment), str);
    }

    private static void a(o oVar, String str) {
        new d(oVar).b(str);
    }

    public d(Activity activity) {
        super(activity, c);
    }

    public d(Fragment fragment) {
        this(new o(fragment));
    }

    private d(o oVar) {
        super(oVar, c);
    }

    protected void a(f fVar, final h<a> hVar) {
        final m anonymousClass1 = hVar == null ? null : new m(this, hVar) {
            final /* synthetic */ d b;

            public void a(com.facebook.internal.b bVar, Bundle bundle) {
                hVar.onSuccess(new a(bundle));
            }
        };
        fVar.b(a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ d b;

            public boolean a(int i, Intent intent) {
                return q.a(this.b.a(), i, intent, anonymousClass1);
            }
        });
    }

    protected com.facebook.internal.b d() {
        return new com.facebook.internal.b(a());
    }

    protected List<a> c() {
        List arrayList = new ArrayList();
        arrayList.add(new b());
        return arrayList;
    }
}
