package com.facebook.share;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.h;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.internal.g;
import com.facebook.internal.g.c;
import com.facebook.internal.g.d;
import com.facebook.internal.g.e;
import com.facebook.internal.z;
import com.facebook.k;
import com.facebook.l;
import com.facebook.n;
import com.facebook.share.internal.o;
import com.facebook.share.internal.q;
import com.facebook.share.internal.r;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.v;
import com.facebook.w;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static final String a = "ShareApi";
    private static final String b = "me";
    private static final String c = "photos";
    private static final String d = "%s/%s";
    private static final String e = "UTF-8";
    private String f;
    private String g = b;
    private final ShareContent h;

    public static void a(ShareContent shareContent, h<com.facebook.share.c.a> hVar) {
        new a(shareContent).a((h) hVar);
    }

    public a(ShareContent shareContent) {
        this.h = shareContent;
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String b() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public ShareContent c() {
        return this.h;
    }

    public boolean d() {
        if (c() == null) {
            return false;
        }
        AccessToken a = AccessToken.a();
        if (a == null) {
            return false;
        }
        Set e = a.e();
        if (e == null || !e.contains("publish_actions")) {
            Log.w(a, "The publish_actions permissions are missing, the share will fail unless this app was authorized to publish in another installation.");
        }
        return true;
    }

    public void a(h<com.facebook.share.c.a> hVar) {
        if (d()) {
            ShareContent c = c();
            try {
                o.d(c);
                if (c instanceof ShareLinkContent) {
                    a((ShareLinkContent) c, (h) hVar);
                    return;
                } else if (c instanceof SharePhotoContent) {
                    a((SharePhotoContent) c, (h) hVar);
                    return;
                } else if (c instanceof ShareVideoContent) {
                    a((ShareVideoContent) c, (h) hVar);
                    return;
                } else if (c instanceof ShareOpenGraphContent) {
                    a((ShareOpenGraphContent) c, (h) hVar);
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                q.a((h) hVar, e);
                return;
            }
        }
        q.a((h) hVar, "Insufficient permissions for sharing content via Api.");
    }

    private String c(String str) {
        try {
            return String.format(Locale.ROOT, d, new Object[]{URLEncoder.encode(b(), "UTF-8"), str});
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private void a(Bundle bundle, ShareContent shareContent) {
        Collection i = shareContent.i();
        if (!ah.a(i)) {
            bundle.putString("tags", TextUtils.join(", ", i));
        }
        if (!ah.a(shareContent.j())) {
            bundle.putString("place", shareContent.j());
        }
        if (!ah.a(shareContent.k())) {
            bundle.putString("ref", shareContent.k());
        }
    }

    private void a(ShareOpenGraphContent shareOpenGraphContent, final h<com.facebook.share.c.a> hVar) {
        final b anonymousClass1 = new b(this) {
            final /* synthetic */ a b;

            public void onCompleted(v vVar) {
                JSONObject b = vVar.b();
                q.a(hVar, b == null ? null : b.optString("id"), vVar);
            }
        };
        final ShareOpenGraphAction a = shareOpenGraphContent.a();
        final Bundle b = a.b();
        a(b, (ShareContent) shareOpenGraphContent);
        if (!ah.a(a())) {
            b.putString("message", a());
        }
        final h<com.facebook.share.c.a> hVar2 = hVar;
        a(b, new d(this) {
            final /* synthetic */ a e;

            public void a() {
                try {
                    a.b(b);
                    new GraphRequest(AccessToken.a(), this.e.c(URLEncoder.encode(a.a(), "UTF-8")), b, w.b, anonymousClass1).n();
                } catch (Exception e) {
                    q.a(hVar2, e);
                }
            }

            public void a(k kVar) {
                q.a(hVar2, (Exception) kVar);
            }
        });
    }

    private static void b(Bundle bundle) {
        String string = bundle.getString("image");
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        a(bundle, i, optJSONObject);
                    } else {
                        bundle.putString(String.format(Locale.ROOT, "image[%d][url]", new Object[]{Integer.valueOf(i)}), jSONArray.getString(i));
                    }
                }
                bundle.remove("image");
            } catch (JSONException e) {
                try {
                    a(bundle, 0, new JSONObject(string));
                    bundle.remove("image");
                } catch (JSONException e2) {
                }
            }
        }
    }

    private static void a(Bundle bundle, int i, JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            Object[] objArr = new Object[]{Integer.valueOf(i), (String) keys.next()};
            bundle.putString(String.format(Locale.ROOT, "image[%d][%s]", objArr), jSONObject.get((String) keys.next()).toString());
        }
    }

    private void a(SharePhotoContent sharePhotoContent, h<com.facebook.share.c.a> hVar) {
        final z zVar = new z(Integer.valueOf(0));
        AccessToken a = AccessToken.a();
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final h<com.facebook.share.c.a> hVar2 = hVar;
        AnonymousClass6 anonymousClass6 = new b(this) {
            final /* synthetic */ a e;

            public void onCompleted(v vVar) {
                JSONObject b = vVar.b();
                if (b != null) {
                    arrayList2.add(b);
                }
                if (vVar.a() != null) {
                    arrayList3.add(vVar);
                }
                zVar.a = Integer.valueOf(((Integer) zVar.a).intValue() - 1);
                if (((Integer) zVar.a).intValue() != 0) {
                    return;
                }
                if (!arrayList3.isEmpty()) {
                    q.a(hVar2, null, (v) arrayList3.get(0));
                } else if (!arrayList2.isEmpty()) {
                    q.a(hVar2, ((JSONObject) arrayList2.get(0)).optString("id"), vVar);
                }
            }
        };
        try {
            for (SharePhoto sharePhoto : sharePhotoContent.a()) {
                try {
                    Bundle a2 = a(sharePhoto, sharePhotoContent);
                    Bitmap b = sharePhoto.b();
                    Uri c = sharePhoto.c();
                    String e = sharePhoto.e();
                    if (e == null) {
                        e = a();
                    }
                    if (b != null) {
                        arrayList.add(GraphRequest.a(a, c("photos"), b, e, a2, (b) anonymousClass6));
                    } else if (c != null) {
                        arrayList.add(GraphRequest.a(a, c("photos"), c, e, a2, (b) anonymousClass6));
                    }
                } catch (Exception e2) {
                    q.a((h) hVar, e2);
                    return;
                }
            }
            zVar.a = Integer.valueOf(((Integer) zVar.a).intValue() + arrayList.size());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GraphRequest) it.next()).n();
            }
        } catch (Exception e22) {
            q.a((h) hVar, e22);
        }
    }

    private void a(ShareLinkContent shareLinkContent, final h<com.facebook.share.c.a> hVar) {
        b anonymousClass7 = new b(this) {
            final /* synthetic */ a b;

            public void onCompleted(v vVar) {
                JSONObject b = vVar.b();
                q.a(hVar, b == null ? null : b.optString("id"), vVar);
            }
        };
        Bundle bundle = new Bundle();
        a(bundle, (ShareContent) shareLinkContent);
        bundle.putString("message", a());
        bundle.putString("link", ah.a(shareLinkContent.h()));
        bundle.putString("picture", ah.a(shareLinkContent.c()));
        bundle.putString("name", shareLinkContent.b());
        bundle.putString("description", shareLinkContent.a());
        bundle.putString("ref", shareLinkContent.k());
        new GraphRequest(AccessToken.a(), c("feed"), bundle, w.b, anonymousClass7).n();
    }

    private void a(ShareVideoContent shareVideoContent, h<com.facebook.share.c.a> hVar) {
        try {
            r.a(shareVideoContent, b(), (h) hVar);
        } catch (Exception e) {
            q.a((h) hVar, e);
        }
    }

    private Bundle a(SharePhoto sharePhoto, SharePhotoContent sharePhotoContent) throws JSONException {
        Bundle a = sharePhoto.a();
        if (!(a.containsKey("place") || ah.a(sharePhotoContent.j()))) {
            a.putString("place", sharePhotoContent.j());
        }
        if (!(a.containsKey("tags") || ah.a(sharePhotoContent.i()))) {
            Collection<String> i = sharePhotoContent.i();
            if (!ah.a((Collection) i)) {
                JSONArray jSONArray = new JSONArray();
                for (String str : i) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("tag_uid", str);
                    jSONArray.put(jSONObject);
                }
                a.putString("tags", jSONArray.toString());
            }
        }
        if (!(a.containsKey("ref") || ah.a(sharePhotoContent.k()))) {
            a.putString("ref", sharePhotoContent.k());
        }
        return a;
    }

    private void a(final ArrayList arrayList, final c cVar) {
        final JSONArray jSONArray = new JSONArray();
        a(new com.facebook.internal.g.a<Integer>(this) {
            final /* synthetic */ a c;

            public Iterator<Integer> a() {
                final int size = arrayList.size();
                final z zVar = new z(Integer.valueOf(0));
                return new Iterator<Integer>(this) {
                    final /* synthetic */ AnonymousClass8 c;

                    public /* synthetic */ Object next() {
                        return a();
                    }

                    public boolean hasNext() {
                        return ((Integer) zVar.a).intValue() < size;
                    }

                    public Integer a() {
                        Integer num = (Integer) zVar.a;
                        z zVar = zVar;
                        zVar.a = Integer.valueOf(((Integer) zVar.a).intValue() + 1);
                        return num;
                    }

                    public void remove() {
                    }
                };
            }

            public Object a(Integer num) {
                return arrayList.get(num.intValue());
            }

            public void a(Integer num, Object obj, g.b bVar) {
                try {
                    jSONArray.put(num.intValue(), obj);
                } catch (JSONException e) {
                    String localizedMessage = e.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Error staging object.";
                    }
                    bVar.a(new k(localizedMessage));
                }
            }
        }, new d(this) {
            final /* synthetic */ a c;

            public void a() {
                cVar.a(jSONArray);
            }

            public void a(k kVar) {
                cVar.a(kVar);
            }
        });
    }

    private <T> void a(com.facebook.internal.g.a<T> aVar, d dVar) {
        g.a(aVar, new e(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(Object obj, c cVar) {
                if (obj instanceof ArrayList) {
                    this.a.a((ArrayList) obj, cVar);
                } else if (obj instanceof ShareOpenGraphObject) {
                    this.a.a((ShareOpenGraphObject) obj, cVar);
                } else if (obj instanceof SharePhoto) {
                    this.a.a((SharePhoto) obj, cVar);
                } else {
                    cVar.a(obj);
                }
            }
        }, dVar);
    }

    private void a(final Bundle bundle, d dVar) {
        a(new com.facebook.internal.g.a<String>(this) {
            final /* synthetic */ a b;

            public Iterator<String> a() {
                return bundle.keySet().iterator();
            }

            public Object a(String str) {
                return bundle.get(str);
            }

            public void a(String str, Object obj, g.b bVar) {
                if (!ah.a(bundle, str, obj)) {
                    bVar.a(new k("Unexpected value: " + obj.toString()));
                }
            }
        }, dVar);
    }

    private void a(final ShareOpenGraphObject shareOpenGraphObject, final c cVar) {
        String j = shareOpenGraphObject.j("type");
        if (j == null) {
            j = shareOpenGraphObject.j("og:type");
        }
        if (j == null) {
            cVar.a(new k("Open Graph objects must contain a type value."));
            return;
        }
        final JSONObject jSONObject = new JSONObject();
        com.facebook.internal.g.a anonymousClass12 = new com.facebook.internal.g.a<String>(this) {
            final /* synthetic */ a c;

            public Iterator<String> a() {
                return shareOpenGraphObject.c().iterator();
            }

            public Object a(String str) {
                return shareOpenGraphObject.a(str);
            }

            public void a(String str, Object obj, g.b bVar) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException e) {
                    String localizedMessage = e.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Error staging object.";
                    }
                    bVar.a(new k(localizedMessage));
                }
            }
        };
        final b anonymousClass2 = new b(this) {
            final /* synthetic */ a b;

            public void onCompleted(v vVar) {
                n a = vVar.a();
                String f;
                if (a != null) {
                    f = a.f();
                    if (f == null) {
                        f = "Error staging Open Graph object.";
                    }
                    cVar.a(new l(vVar, f));
                    return;
                }
                JSONObject b = vVar.b();
                if (b == null) {
                    cVar.a(new l(vVar, "Error staging Open Graph object."));
                    return;
                }
                f = b.optString("id");
                if (f == null) {
                    cVar.a(new l(vVar, "Error staging Open Graph object."));
                } else {
                    cVar.a(f);
                }
            }
        };
        final c cVar2 = cVar;
        a(anonymousClass12, new d(this) {
            final /* synthetic */ a e;

            public void a() {
                String jSONObject = jSONObject.toString();
                Bundle bundle = new Bundle();
                bundle.putString("object", jSONObject);
                try {
                    new GraphRequest(AccessToken.a(), this.e.c("objects/" + URLEncoder.encode(j, "UTF-8")), bundle, w.b, anonymousClass2).n();
                } catch (UnsupportedEncodingException e) {
                    jSONObject = e.getLocalizedMessage();
                    if (jSONObject == null) {
                        jSONObject = "Error staging Open Graph object.";
                    }
                    cVar2.a(new k(jSONObject));
                }
            }

            public void a(k kVar) {
                cVar2.a(kVar);
            }
        });
    }

    private void a(final SharePhoto sharePhoto, final c cVar) {
        Bitmap b = sharePhoto.b();
        Uri c = sharePhoto.c();
        if (b == null && c == null) {
            cVar.a(new k("Photos must have an imageURL or bitmap."));
            return;
        }
        b anonymousClass4 = new b(this) {
            final /* synthetic */ a c;

            public void onCompleted(v vVar) {
                n a = vVar.a();
                String f;
                if (a != null) {
                    f = a.f();
                    if (f == null) {
                        f = "Error staging photo.";
                    }
                    cVar.a(new l(vVar, f));
                    return;
                }
                JSONObject b = vVar.b();
                if (b == null) {
                    cVar.a(new k("Error staging photo."));
                    return;
                }
                f = b.optString("uri");
                if (f == null) {
                    cVar.a(new k("Error staging photo."));
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", f);
                    jSONObject.put(ab.ae, sharePhoto.d());
                    cVar.a(jSONObject);
                } catch (JSONException e) {
                    f = e.getLocalizedMessage();
                    if (f == null) {
                        f = "Error staging photo.";
                    }
                    cVar.a(new k(f));
                }
            }
        };
        if (b != null) {
            q.a(AccessToken.a(), b, anonymousClass4).n();
            return;
        }
        try {
            q.a(AccessToken.a(), c, anonymousClass4).n();
        } catch (FileNotFoundException e) {
            String localizedMessage = e.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Error staging photo.";
            }
            cVar.a(new k(localizedMessage));
        }
    }
}
