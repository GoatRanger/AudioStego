package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.facebook.h;
import com.facebook.internal.f;
import com.facebook.internal.i;
import com.facebook.internal.j;
import com.facebook.internal.o;
import com.facebook.share.internal.m;
import com.facebook.share.internal.n;
import com.facebook.share.internal.q;
import com.facebook.share.model.AppInviteContent;
import java.util.ArrayList;
import java.util.List;

public class a extends j<AppInviteContent, b> {
    private static final String b = "AppInviteDialog";
    private static final int c = com.facebook.internal.f.b.AppInvite.a();

    private class a extends a {
        final /* synthetic */ a b;

        private a(a aVar) {
            this.b = aVar;
            super(aVar);
        }

        public boolean a(AppInviteContent appInviteContent) {
            return a.i();
        }

        public com.facebook.internal.b b(final AppInviteContent appInviteContent) {
            com.facebook.internal.b d = this.b.d();
            i.a(d, new com.facebook.internal.i.a(this) {
                final /* synthetic */ a b;

                public Bundle a() {
                    return a.b(appInviteContent);
                }

                public Bundle b() {
                    Log.e(a.b, "Attempting to present the AppInviteDialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
            }, a.k());
            return d;
        }
    }

    public static final class b {
        private final Bundle a;

        public b(Bundle bundle) {
            this.a = bundle;
        }

        public Bundle a() {
            return this.a;
        }
    }

    private class c extends a {
        final /* synthetic */ a b;

        private c(a aVar) {
            this.b = aVar;
            super(aVar);
        }

        public boolean a(AppInviteContent appInviteContent) {
            return a.j();
        }

        public com.facebook.internal.b b(AppInviteContent appInviteContent) {
            com.facebook.internal.b d = this.b.d();
            i.a(d, a.b(appInviteContent), a.k());
            return d;
        }
    }

    public static boolean e() {
        return i() || j();
    }

    public static void a(Activity activity, AppInviteContent appInviteContent) {
        new a(activity).b(appInviteContent);
    }

    public static void a(Fragment fragment, AppInviteContent appInviteContent) {
        a(new o(fragment), appInviteContent);
    }

    private static void a(o oVar, AppInviteContent appInviteContent) {
        new a(oVar).b(appInviteContent);
    }

    private static boolean i() {
        return i.a(k());
    }

    private static boolean j() {
        return i.b(k());
    }

    public a(Activity activity) {
        super(activity, c);
    }

    public a(Fragment fragment) {
        this(new o(fragment));
    }

    private a(o oVar) {
        super(oVar, c);
    }

    protected void a(f fVar, final h<b> hVar) {
        m mVar;
        if (hVar == null) {
            mVar = null;
        } else {
            mVar = new m(this, hVar) {
                final /* synthetic */ a b;

                public void a(com.facebook.internal.b bVar, Bundle bundle) {
                    if ("cancel".equalsIgnoreCase(q.a(bundle))) {
                        hVar.onCancel();
                    } else {
                        hVar.onSuccess(new b(bundle));
                    }
                }
            };
        }
        fVar.b(a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ a b;

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
        arrayList.add(new a());
        arrayList.add(new c());
        return arrayList;
    }

    private static com.facebook.internal.h k() {
        return com.facebook.share.internal.a.APP_INVITES_DIALOG;
    }

    private static Bundle b(AppInviteContent appInviteContent) {
        Bundle bundle = new Bundle();
        bundle.putString(n.U, appInviteContent.a());
        bundle.putString(n.V, appInviteContent.b());
        return bundle;
    }
}
