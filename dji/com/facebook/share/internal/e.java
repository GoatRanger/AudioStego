package com.facebook.share.internal;

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
import java.util.ArrayList;
import java.util.List;

public class e extends j<LikeContent, b> {
    private static final String b = "LikeDialog";
    private static final int c = com.facebook.internal.f.b.Like.a();

    private class a extends a {
        final /* synthetic */ e b;

        private a(e eVar) {
            this.b = eVar;
            super(eVar);
        }

        public boolean a(LikeContent likeContent) {
            return likeContent != null && e.e();
        }

        public com.facebook.internal.b b(final LikeContent likeContent) {
            com.facebook.internal.b d = this.b.d();
            i.a(d, new com.facebook.internal.i.a(this) {
                final /* synthetic */ a b;

                public Bundle a() {
                    return e.b(likeContent);
                }

                public Bundle b() {
                    Log.e(e.b, "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
            }, e.h());
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
        final /* synthetic */ e b;

        private c(e eVar) {
            this.b = eVar;
            super(eVar);
        }

        public boolean a(LikeContent likeContent) {
            return likeContent != null && e.f();
        }

        public com.facebook.internal.b b(LikeContent likeContent) {
            com.facebook.internal.b d = this.b.d();
            i.a(d, e.b(likeContent), e.h());
            return d;
        }
    }

    public static boolean e() {
        return i.a(h());
    }

    public static boolean f() {
        return i.b(h());
    }

    public e(Activity activity) {
        super(activity, c);
    }

    public e(Fragment fragment) {
        this(new o(fragment));
    }

    public e(o oVar) {
        super(oVar, c);
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

    protected void a(f fVar, final h<b> hVar) {
        final m anonymousClass1 = hVar == null ? null : new m(this, hVar) {
            final /* synthetic */ e b;

            public void a(com.facebook.internal.b bVar, Bundle bundle) {
                hVar.onSuccess(new b(bundle));
            }
        };
        fVar.b(a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ e b;

            public boolean a(int i, Intent intent) {
                return q.a(this.b.a(), i, intent, anonymousClass1);
            }
        });
    }

    private static com.facebook.internal.h h() {
        return f.LIKE_DIALOG;
    }

    private static Bundle b(LikeContent likeContent) {
        Bundle bundle = new Bundle();
        bundle.putString("object_id", likeContent.a());
        bundle.putString("object_type", likeContent.b());
        return bundle;
    }
}
