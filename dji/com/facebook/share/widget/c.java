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
import com.facebook.share.internal.n;
import com.facebook.share.internal.q;
import com.facebook.share.internal.s;
import com.facebook.share.model.GameRequestContent;
import java.util.ArrayList;
import java.util.List;

public class c extends j<GameRequestContent, a> {
    private static final String b = "apprequests";
    private static final int c = com.facebook.internal.f.b.GameRequest.a();

    public static final class a {
        String a;
        List<String> b;

        private a(Bundle bundle) {
            this.a = bundle.getString(n.r);
            this.b = new ArrayList();
            while (true) {
                if (bundle.containsKey(String.format(n.s, new Object[]{Integer.valueOf(this.b.size())}))) {
                    this.b.add(bundle.getString(String.format(n.s, new Object[]{Integer.valueOf(this.b.size())})));
                } else {
                    return;
                }
            }
        }

        public String a() {
            return this.a;
        }

        public List<String> b() {
            return this.b;
        }
    }

    private class b extends a {
        final /* synthetic */ c b;

        private b(c cVar) {
            this.b = cVar;
            super(cVar);
        }

        public boolean a(GameRequestContent gameRequestContent) {
            return true;
        }

        public com.facebook.internal.b b(GameRequestContent gameRequestContent) {
            com.facebook.share.internal.b.a(gameRequestContent);
            com.facebook.internal.b d = this.b.d();
            i.a(d, c.b, s.a(gameRequestContent));
            return d;
        }
    }

    public static boolean e() {
        return true;
    }

    public static void a(Activity activity, GameRequestContent gameRequestContent) {
        new c(activity).b(gameRequestContent);
    }

    public static void a(Fragment fragment, GameRequestContent gameRequestContent) {
        a(new o(fragment), gameRequestContent);
    }

    private static void a(o oVar, GameRequestContent gameRequestContent) {
        new c(oVar).b(gameRequestContent);
    }

    public c(Activity activity) {
        super(activity, c);
    }

    public c(Fragment fragment) {
        this(new o(fragment));
    }

    private c(o oVar) {
        super(oVar, c);
    }

    protected void a(f fVar, final h<a> hVar) {
        m mVar;
        if (hVar == null) {
            mVar = null;
        } else {
            mVar = new m(this, hVar) {
                final /* synthetic */ c b;

                public void a(com.facebook.internal.b bVar, Bundle bundle) {
                    if (bundle != null) {
                        hVar.onSuccess(new a(bundle));
                    } else {
                        a(bVar);
                    }
                }
            };
        }
        fVar.b(a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ c b;

            public boolean a(int i, Intent intent) {
                return q.a(this.b.a(), i, intent, mVar);
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
