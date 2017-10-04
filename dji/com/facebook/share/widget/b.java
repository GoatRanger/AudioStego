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
import com.facebook.share.internal.s;
import com.facebook.share.model.AppGroupCreationContent;
import java.util.ArrayList;
import java.util.List;

public class b extends j<AppGroupCreationContent, a> {
    private static final String b = "game_group_create";
    private static final int c = com.facebook.internal.f.b.AppGroupCreate.a();

    public static final class a {
        private final String a;

        private a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    private class b extends a {
        final /* synthetic */ b b;

        private b(b bVar) {
            this.b = bVar;
            super(bVar);
        }

        public boolean a(AppGroupCreationContent appGroupCreationContent) {
            return true;
        }

        public com.facebook.internal.b b(AppGroupCreationContent appGroupCreationContent) {
            com.facebook.internal.b d = this.b.d();
            i.a(d, b.b, s.a(appGroupCreationContent));
            return d;
        }
    }

    public static boolean e() {
        return true;
    }

    public static void a(Activity activity, AppGroupCreationContent appGroupCreationContent) {
        new b(activity).b(appGroupCreationContent);
    }

    public static void a(Fragment fragment, AppGroupCreationContent appGroupCreationContent) {
        a(new o(fragment), appGroupCreationContent);
    }

    private static void a(o oVar, AppGroupCreationContent appGroupCreationContent) {
        new b(oVar).b(appGroupCreationContent);
    }

    public b(Activity activity) {
        super(activity, c);
    }

    public b(Fragment fragment) {
        this(new o(fragment));
    }

    private b(o oVar) {
        super(oVar, c);
    }

    protected void a(f fVar, final h<a> hVar) {
        final m anonymousClass1 = hVar == null ? null : new m(this, hVar) {
            final /* synthetic */ b b;

            public void a(com.facebook.internal.b bVar, Bundle bundle) {
                hVar.onSuccess(new a(bundle.getString("id")));
            }
        };
        fVar.b(a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ b b;

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
