package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.h;
import com.facebook.internal.i;
import com.facebook.internal.j;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.o;
import com.facebook.share.internal.p;
import com.facebook.share.internal.q;
import com.facebook.share.internal.s;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;

public final class f extends j<ShareContent, com.facebook.share.c.a> implements com.facebook.share.c {
    private static final String b = "feed";
    private static final String c = "share";
    private static final String d = "share_open_graph";
    private static final int e = com.facebook.internal.f.b.Share.a();
    private boolean f;
    private boolean g;

    private class a extends a {
        final /* synthetic */ f b;

        private a(f fVar) {
            this.b = fVar;
            super(fVar);
        }

        public Object a() {
            return b.FEED;
        }

        public boolean a(ShareContent shareContent) {
            return (shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareFeedContent);
        }

        public com.facebook.internal.b b(ShareContent shareContent) {
            Bundle b;
            this.b.a(this.b.b(), shareContent, b.FEED);
            com.facebook.internal.b d = this.b.d();
            if (shareContent instanceof ShareLinkContent) {
                ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
                o.c(shareLinkContent);
                b = s.b(shareLinkContent);
            } else {
                b = s.a((ShareFeedContent) shareContent);
            }
            i.a(d, f.b, b);
            return d;
        }
    }

    public enum b {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    private class c extends a {
        final /* synthetic */ f b;

        private c(f fVar) {
            this.b = fVar;
            super(fVar);
        }

        public Object a() {
            return b.NATIVE;
        }

        public boolean a(ShareContent shareContent) {
            return shareContent != null && f.e(shareContent.getClass());
        }

        public com.facebook.internal.b b(final ShareContent shareContent) {
            this.b.a(this.b.b(), shareContent, b.NATIVE);
            o.b(shareContent);
            final com.facebook.internal.b d = this.b.d();
            final boolean d_ = this.b.d_();
            i.a(d, new com.facebook.internal.i.a(this) {
                final /* synthetic */ c d;

                public Bundle a() {
                    return com.facebook.share.internal.i.a(d.c(), shareContent, d_);
                }

                public Bundle b() {
                    return com.facebook.share.internal.c.a(d.c(), shareContent, d_);
                }
            }, f.g(shareContent.getClass()));
            return d;
        }
    }

    private class d extends a {
        final /* synthetic */ f b;

        private d(f fVar) {
            this.b = fVar;
            super(fVar);
        }

        public Object a() {
            return b.WEB;
        }

        public boolean a(ShareContent shareContent) {
            return shareContent != null && f.f(shareContent.getClass());
        }

        public com.facebook.internal.b b(ShareContent shareContent) {
            Bundle a;
            this.b.a(this.b.b(), shareContent, b.WEB);
            com.facebook.internal.b d = this.b.d();
            o.c(shareContent);
            if (shareContent instanceof ShareLinkContent) {
                a = s.a((ShareLinkContent) shareContent);
            } else {
                a = s.a((ShareOpenGraphContent) shareContent);
            }
            i.a(d, c(shareContent), a);
            return d;
        }

        private String c(ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return f.d;
            }
            return null;
        }
    }

    public static void a(Activity activity, ShareContent shareContent) {
        new f(activity).b(shareContent);
    }

    public static void a(Fragment fragment, ShareContent shareContent) {
        a(new com.facebook.internal.o(fragment), shareContent);
    }

    private static void a(com.facebook.internal.o oVar, ShareContent shareContent) {
        new f(oVar).b(shareContent);
    }

    public static boolean a(Class<? extends ShareContent> cls) {
        return f(cls) || e(cls);
    }

    private static boolean e(Class<? extends ShareContent> cls) {
        h g = g(cls);
        return g != null && i.a(g);
    }

    private static boolean f(Class<? extends ShareContent> cls) {
        return ShareLinkContent.class.isAssignableFrom(cls) || ShareOpenGraphContent.class.isAssignableFrom(cls);
    }

    public f(Activity activity) {
        super(activity, e);
        this.f = false;
        this.g = true;
        q.a(e);
    }

    public f(Fragment fragment) {
        this(new com.facebook.internal.o(fragment));
    }

    private f(com.facebook.internal.o oVar) {
        super(oVar, e);
        this.f = false;
        this.g = true;
        q.a(e);
    }

    f(Activity activity, int i) {
        super(activity, i);
        this.f = false;
        this.g = true;
        q.a(i);
    }

    f(Fragment fragment, int i) {
        this(new com.facebook.internal.o(fragment), i);
    }

    private f(com.facebook.internal.o oVar, int i) {
        super(oVar, i);
        this.f = false;
        this.g = true;
        q.a(i);
    }

    protected void a(com.facebook.internal.f fVar, com.facebook.h<com.facebook.share.c.a> hVar) {
        q.a(a(), (com.facebook.f) fVar, (com.facebook.h) hVar);
    }

    public boolean d_() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean a(ShareContent shareContent, b bVar) {
        if (bVar == b.AUTOMATIC) {
            bVar = a;
        }
        return a((Object) shareContent, (Object) bVar);
    }

    public void b(ShareContent shareContent, b bVar) {
        this.g = bVar == b.AUTOMATIC;
        if (this.g) {
            bVar = a;
        }
        b(shareContent, bVar);
    }

    protected com.facebook.internal.b d() {
        return new com.facebook.internal.b(a());
    }

    protected List<a> c() {
        List arrayList = new ArrayList();
        arrayList.add(new c());
        arrayList.add(new a());
        arrayList.add(new d());
        return arrayList;
    }

    private static h g(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return p.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return p.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return p.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return com.facebook.share.internal.j.OG_ACTION_DIALOG;
        }
        return null;
    }

    private void a(Context context, ShareContent shareContent, b bVar) {
        String str;
        String str2;
        if (this.g) {
            bVar = b.AUTOMATIC;
        }
        switch (bVar) {
            case AUTOMATIC:
                str = com.facebook.internal.a.ab;
                break;
            case WEB:
                str = com.facebook.internal.a.Z;
                break;
            case NATIVE:
                str = com.facebook.internal.a.aa;
                break;
            default:
                str = "unknown";
                break;
        }
        h g = g(shareContent.getClass());
        if (g == p.SHARE_DIALOG) {
            str2 = "status";
        } else if (g == p.PHOTOS) {
            str2 = "photo";
        } else if (g == p.VIDEO) {
            str2 = "video";
        } else if (g == com.facebook.share.internal.j.OG_ACTION_DIALOG) {
            str2 = com.facebook.internal.a.ah;
        } else {
            str2 = "unknown";
        }
        com.facebook.a.b c = com.facebook.a.b.c(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str);
        bundle.putString(com.facebook.internal.a.ad, str2);
        c.a("fb_share_dialog_show", null, bundle);
    }
}
