package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.k;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

public class o {
    private static b a;
    private static b b;
    private static b c;

    private static class b {
        private boolean a;

        private b() {
            this.a = false;
        }

        public void a(ShareLinkContent shareLinkContent) {
            o.b(shareLinkContent, this);
        }

        public void a(SharePhotoContent sharePhotoContent) {
            o.b(sharePhotoContent, this);
        }

        public void a(ShareVideoContent shareVideoContent) {
            o.b(shareVideoContent, this);
        }

        public void a(ShareOpenGraphContent shareOpenGraphContent) {
            this.a = true;
            o.b(shareOpenGraphContent, this);
        }

        public void a(ShareOpenGraphAction shareOpenGraphAction) {
            o.b(shareOpenGraphAction, this);
        }

        public void a(ShareOpenGraphObject shareOpenGraphObject) {
            o.b(shareOpenGraphObject, this);
        }

        public void a(ShareOpenGraphValueContainer shareOpenGraphValueContainer, boolean z) {
            o.b(shareOpenGraphValueContainer, this, z);
        }

        public void a(SharePhoto sharePhoto) {
            o.e(sharePhoto, this);
        }

        public void a(ShareVideo shareVideo) {
            o.b(shareVideo, this);
        }

        public boolean a() {
            return this.a;
        }
    }

    private static class a extends b {
        private a() {
            super();
        }

        public void a(SharePhoto sharePhoto) {
            o.d(sharePhoto, this);
        }

        public void a(ShareVideoContent shareVideoContent) {
            if (!ah.a(shareVideoContent.j())) {
                throw new k("Cannot share video content with place IDs using the share api");
            } else if (!ah.a(shareVideoContent.i())) {
                throw new k("Cannot share video content with people IDs using the share api");
            } else if (!ah.a(shareVideoContent.k())) {
                throw new k("Cannot share video content with referrer URL using the share api");
            }
        }
    }

    private static class c extends b {
        private c() {
            super();
        }

        public void a(SharePhotoContent sharePhotoContent) {
            throw new k("Cannot share SharePhotoContent via web sharing dialogs");
        }

        public void a(ShareVideoContent shareVideoContent) {
            throw new k("Cannot share ShareVideoContent via web sharing dialogs");
        }

        public void a(SharePhoto sharePhoto) {
            o.f(sharePhoto, this);
        }
    }

    public static void a(ShareContent shareContent) {
        a(shareContent, a());
    }

    public static void b(ShareContent shareContent) {
        a(shareContent, a());
    }

    public static void c(ShareContent shareContent) {
        a(shareContent, c());
    }

    public static void d(ShareContent shareContent) {
        a(shareContent, b());
    }

    private static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private static b b() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private static b c() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private static void a(ShareContent shareContent, b bVar) throws k {
        if (shareContent == null) {
            throw new k("Must provide non-null content to share");
        } else if (shareContent instanceof ShareLinkContent) {
            bVar.a((ShareLinkContent) shareContent);
        } else if (shareContent instanceof SharePhotoContent) {
            bVar.a((SharePhotoContent) shareContent);
        } else if (shareContent instanceof ShareVideoContent) {
            bVar.a((ShareVideoContent) shareContent);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            bVar.a((ShareOpenGraphContent) shareContent);
        }
    }

    private static void b(ShareLinkContent shareLinkContent, b bVar) {
        Uri c = shareLinkContent.c();
        if (c != null && !ah.b(c)) {
            throw new k("Image Url must be an http:// or https:// url");
        }
    }

    private static void b(SharePhotoContent sharePhotoContent, b bVar) {
        List<SharePhoto> a = sharePhotoContent.a();
        if (a == null || a.isEmpty()) {
            throw new k("Must specify at least one Photo in SharePhotoContent.");
        } else if (a.size() > 6) {
            throw new k(String.format(Locale.ROOT, "Cannot add more than %d photos.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (SharePhoto a2 : a) {
                bVar.a(a2);
            }
        }
    }

    private static void d(SharePhoto sharePhoto, b bVar) {
        if (sharePhoto == null) {
            throw new k("Cannot share a null SharePhoto");
        }
        Bitmap b = sharePhoto.b();
        Uri c = sharePhoto.c();
        if (b != null) {
            return;
        }
        if (c == null) {
            throw new k("SharePhoto does not have a Bitmap or ImageUrl specified");
        } else if (ah.b(c) && !bVar.a()) {
            throw new k("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        }
    }

    private static void e(SharePhoto sharePhoto, b bVar) {
        d(sharePhoto, bVar);
        if (sharePhoto.b() != null || !ah.b(sharePhoto.c())) {
            ai.c(com.facebook.o.h());
        }
    }

    private static void f(SharePhoto sharePhoto, b bVar) {
        if (sharePhoto == null) {
            throw new k("Cannot share a null SharePhoto");
        }
        Uri c = sharePhoto.c();
        if (c == null || !ah.b(c)) {
            throw new k("SharePhoto must have a non-null imageUrl set to the Uri of an image on the web");
        }
    }

    private static void b(ShareVideoContent shareVideoContent, b bVar) {
        bVar.a(shareVideoContent.d());
        SharePhoto c = shareVideoContent.c();
        if (c != null) {
            bVar.a(c);
        }
    }

    private static void b(ShareVideo shareVideo, b bVar) {
        if (shareVideo == null) {
            throw new k("Cannot share a null ShareVideo");
        }
        Uri b = shareVideo.b();
        if (b == null) {
            throw new k("ShareVideo does not have a LocalUrl specified");
        } else if (!ah.c(b) && !ah.d(b)) {
            throw new k("ShareVideo must reference a video that is on the device");
        }
    }

    private static void b(ShareOpenGraphContent shareOpenGraphContent, b bVar) {
        bVar.a(shareOpenGraphContent.a());
        String b = shareOpenGraphContent.b();
        if (ah.a(b)) {
            throw new k("Must specify a previewPropertyName.");
        } else if (shareOpenGraphContent.a().a(b) == null) {
            throw new k("Property \"" + b + "\" was not found on the action. " + "The name of the preview property must match the name of an " + "action property.");
        }
    }

    private static void b(ShareOpenGraphAction shareOpenGraphAction, b bVar) {
        if (shareOpenGraphAction == null) {
            throw new k("Must specify a non-null ShareOpenGraphAction");
        } else if (ah.a(shareOpenGraphAction.a())) {
            throw new k("ShareOpenGraphAction must have a non-empty actionType");
        } else {
            bVar.a(shareOpenGraphAction, false);
        }
    }

    private static void b(ShareOpenGraphObject shareOpenGraphObject, b bVar) {
        if (shareOpenGraphObject == null) {
            throw new k("Cannot share a null ShareOpenGraphObject");
        }
        bVar.a(shareOpenGraphObject, true);
    }

    private static void b(ShareOpenGraphValueContainer shareOpenGraphValueContainer, b bVar, boolean z) {
        for (String str : shareOpenGraphValueContainer.c()) {
            a(str, z);
            Object a = shareOpenGraphValueContainer.a(str);
            if (a instanceof List) {
                for (Object next : (List) a) {
                    if (next == null) {
                        throw new k("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                    a(next, bVar);
                }
                continue;
            } else {
                a(a, bVar);
            }
        }
    }

    private static void a(String str, boolean z) {
        if (z) {
            String[] split = str.split(":");
            if (split.length < 2) {
                throw new k("Open Graph keys must be namespaced: %s", str);
            }
            for (String isEmpty : split) {
                if (isEmpty.isEmpty()) {
                    throw new k("Invalid key found in Open Graph dictionary: %s", str);
                }
            }
        }
    }

    private static void a(Object obj, b bVar) {
        if (obj instanceof ShareOpenGraphObject) {
            bVar.a((ShareOpenGraphObject) obj);
        } else if (obj instanceof SharePhoto) {
            bVar.a((SharePhoto) obj);
        }
    }
}
