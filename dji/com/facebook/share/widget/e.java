package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import com.facebook.internal.f;
import com.facebook.internal.f.b;
import com.facebook.internal.h;
import com.facebook.internal.i;
import com.facebook.internal.j;
import com.facebook.share.c;
import com.facebook.share.internal.l;
import com.facebook.share.internal.o;
import com.facebook.share.internal.q;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;

public final class e extends j<ShareContent, com.facebook.share.c.a> implements c {
    private static final int b = b.Message.a();
    private boolean c;

    private class a extends a {
        final /* synthetic */ e b;

        private a(e eVar) {
            this.b = eVar;
            super(eVar);
        }

        public boolean a(ShareContent shareContent) {
            return shareContent != null && e.a(shareContent.getClass());
        }

        public com.facebook.internal.b b(final ShareContent shareContent) {
            o.a(shareContent);
            final com.facebook.internal.b d = this.b.d();
            final boolean d_ = this.b.d_();
            this.b.b();
            i.a(d, new com.facebook.internal.i.a(this) {
                final /* synthetic */ a d;

                public Bundle a() {
                    return com.facebook.share.internal.i.a(d.c(), shareContent, d_);
                }

                public Bundle b() {
                    return com.facebook.share.internal.c.a(d.c(), shareContent, d_);
                }
            }, e.c(shareContent.getClass()));
            return d;
        }
    }

    public static void a(Activity activity, ShareContent shareContent) {
        new e(activity).b(shareContent);
    }

    public static void a(Fragment fragment, ShareContent shareContent) {
        a(new com.facebook.internal.o(fragment), shareContent);
    }

    private static void a(com.facebook.internal.o oVar, ShareContent shareContent) {
        new e(oVar).b(shareContent);
    }

    public static boolean a(Class<? extends ShareContent> cls) {
        h c = c(cls);
        return c != null && i.a(c);
    }

    public e(Activity activity) {
        super(activity, b);
        this.c = false;
        q.a(b);
    }

    public e(Fragment fragment) {
        this(new com.facebook.internal.o(fragment));
    }

    private e(com.facebook.internal.o oVar) {
        super(oVar, b);
        this.c = false;
        q.a(b);
    }

    e(Activity activity, int i) {
        super(activity, i);
        this.c = false;
        q.a(i);
    }

    e(Fragment fragment, int i) {
        this(new com.facebook.internal.o(fragment), i);
    }

    private e(com.facebook.internal.o oVar, int i) {
        super(oVar, i);
        this.c = false;
        q.a(i);
    }

    protected void a(f fVar, com.facebook.h<com.facebook.share.c.a> hVar) {
        q.a(a(), (com.facebook.f) fVar, (com.facebook.h) hVar);
    }

    public boolean d_() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    protected com.facebook.internal.b d() {
        return new com.facebook.internal.b(a());
    }

    protected List<a> c() {
        List arrayList = new ArrayList();
        arrayList.add(new a());
        return arrayList;
    }

    private static h c(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return com.facebook.share.internal.h.MESSAGE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return com.facebook.share.internal.h.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return com.facebook.share.internal.h.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return l.OG_MESSAGE_DIALOG;
        }
        return null;
    }
}
