package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.ParcelableResourceWithMimeType;
import com.facebook.h;
import com.facebook.internal.aa;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.internal.ah.d;
import com.facebook.internal.b;
import com.facebook.internal.f;
import com.facebook.k;
import com.facebook.l;
import com.facebook.m;
import com.facebook.n;
import com.facebook.o;
import com.facebook.share.c.a;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView.e;
import com.facebook.v;
import com.facebook.w;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class q {
    public static final String a = "me/photos";
    private static final String b = "me/staging_resources";
    private static final String c = "file";

    public static void a(h<a> hVar, Exception exception) {
        if (exception instanceof k) {
            a((h) hVar, (k) exception);
        } else {
            a((h) hVar, "Error preparing share content: " + exception.getLocalizedMessage());
        }
    }

    public static void a(h<a> hVar, String str) {
        c(hVar, str);
    }

    public static void a(h<a> hVar, String str, v vVar) {
        n a = vVar.a();
        if (a != null) {
            String f = a.f();
            if (ah.a(f)) {
                f = "Unexpected error sharing.";
            }
            a((h) hVar, vVar, f);
            return;
        }
        b((h) hVar, str);
    }

    public static String a(Bundle bundle) {
        if (bundle.containsKey(ab.E)) {
            return bundle.getString(ab.E);
        }
        return bundle.getString(ab.C);
    }

    public static String b(Bundle bundle) {
        if (bundle.containsKey(n.ae)) {
            return bundle.getString(n.ae);
        }
        if (bundle.containsKey(n.ad)) {
            return bundle.getString(n.ad);
        }
        return bundle.getString(n.q);
    }

    public static boolean a(int i, int i2, Intent intent, m mVar) {
        b a = a(i, i2, intent);
        if (a == null) {
            return false;
        }
        aa.a(a.c());
        if (mVar == null) {
            return true;
        }
        k a2 = ab.a(ab.g(intent));
        if (a2 == null) {
            mVar.a(a, ab.e(intent));
            return true;
        } else if (a2 instanceof m) {
            mVar.a(a);
            return true;
        } else {
            mVar.a(a, a2);
            return true;
        }
    }

    public static m a(final h<a> hVar) {
        return new m(hVar) {
            public void a(b bVar, Bundle bundle) {
                if (bundle != null) {
                    String a = q.a(bundle);
                    if (a == null || "post".equalsIgnoreCase(a)) {
                        q.b(hVar, q.b(bundle));
                    } else if ("cancel".equalsIgnoreCase(a)) {
                        q.b(hVar);
                    } else {
                        q.a(hVar, new k(ab.aq));
                    }
                }
            }

            public void a(b bVar) {
                q.b(hVar);
            }

            public void a(b bVar, k kVar) {
                q.a(hVar, kVar);
            }
        };
    }

    private static b a(int i, int i2, Intent intent) {
        UUID b = ab.b(intent);
        if (b == null) {
            return null;
        }
        return b.a(b, i);
    }

    public static void a(final int i) {
        f.a(i, new f.a() {
            public boolean a(int i, Intent intent) {
                return q.a(i, i, intent, q.a(null));
            }
        });
    }

    public static void a(final int i, com.facebook.f fVar, final h<a> hVar) {
        if (fVar instanceof f) {
            ((f) fVar).b(i, new f.a() {
                public boolean a(int i, Intent intent) {
                    return q.a(i, i, intent, q.a(hVar));
                }
            });
            return;
        }
        throw new k("Unexpected CallbackManager, please use the provided Factory.");
    }

    public static List<String> a(SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            List a = sharePhotoContent.a();
            if (a != null) {
                Collection a2 = ah.a(a, new d<SharePhoto, aa.a>() {
                    public aa.a a(SharePhoto sharePhoto) {
                        return q.b(uuid, sharePhoto);
                    }
                });
                List<String> a3 = ah.a((List) a2, new d<aa.a, String>() {
                    public String a(aa.a aVar) {
                        return aVar.a();
                    }
                });
                aa.a(a2);
                return a3;
            }
        }
        return null;
    }

    public static String a(ShareVideoContent shareVideoContent, UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.d() == null) {
            return null;
        }
        aa.a a = aa.a(uuid, shareVideoContent.d().b());
        Collection arrayList = new ArrayList(1);
        arrayList.add(a);
        aa.a(arrayList);
        return a.a();
    }

    public static JSONObject a(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        ShareOpenGraphAction a = shareOpenGraphContent.a();
        final Collection arrayList = new ArrayList();
        JSONObject a2 = k.a(a, new k.a() {
            public JSONObject a(SharePhoto sharePhoto) {
                aa.a a = q.b(uuid, sharePhoto);
                if (a == null) {
                    return null;
                }
                arrayList.add(a);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", a.a());
                    if (!sharePhoto.d()) {
                        return jSONObject;
                    }
                    jSONObject.put(ab.ae, true);
                    return jSONObject;
                } catch (Throwable e) {
                    throw new k("Unable to attach images", e);
                }
            }
        });
        aa.a(arrayList);
        if (shareOpenGraphContent.j() != null && ah.a(a2.optString("place"))) {
            a2.put("place", shareOpenGraphContent.j());
        }
        if (shareOpenGraphContent.i() != null) {
            JSONArray optJSONArray = a2.optJSONArray("tags");
            if (optJSONArray == null) {
                arrayList = new HashSet();
            } else {
                Object b = ah.b(optJSONArray);
            }
            for (String add : shareOpenGraphContent.i()) {
                arrayList.add(add);
            }
            a2.put("tags", new ArrayList(arrayList));
        }
        return a2;
    }

    public static JSONObject a(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return k.a(shareOpenGraphContent.a(), new k.a() {
            public JSONObject a(SharePhoto sharePhoto) {
                Uri c = sharePhoto.c();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", c.toString());
                    return jSONObject;
                } catch (Throwable e) {
                    throw new k("Unable to attach images", e);
                }
            }
        });
    }

    public static JSONArray a(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = a((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = a((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    public static JSONObject a(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                Object a;
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    a = a((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    JSONArray a2 = a((JSONArray) obj, true);
                } else {
                    a = obj;
                }
                Pair a3 = a(string);
                String str = (String) a3.first;
                String str2 = (String) a3.second;
                if (z) {
                    if (str != null && str.equals("fbsdk")) {
                        jSONObject2.put(string, a);
                    } else if (str == null || str.equals("og")) {
                        jSONObject2.put(str2, a);
                    } else {
                        jSONObject3.put(str2, a);
                    }
                } else if (str == null || !str.equals("fb")) {
                    jSONObject2.put(str2, a);
                } else {
                    jSONObject2.put(string, a);
                }
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("data", jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException e) {
            throw new k("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> a(String str) {
        Object obj = null;
        int indexOf = str.indexOf(58);
        if (indexOf != -1 && str.length() > indexOf + 1) {
            obj = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        }
        return new Pair(obj, str);
    }

    private static aa.a b(UUID uuid, SharePhoto sharePhoto) {
        Bitmap b = sharePhoto.b();
        Uri c = sharePhoto.c();
        if (b != null) {
            return aa.a(uuid, b);
        }
        if (c != null) {
            return aa.a(uuid, c);
        }
        return null;
    }

    static void b(h<a> hVar) {
        a("cancelled", null);
        if (hVar != null) {
            hVar.onCancel();
        }
    }

    static void b(h<a> hVar, String str) {
        a(com.facebook.internal.a.T, null);
        if (hVar != null) {
            hVar.onSuccess(new a(str));
        }
    }

    static void a(h<a> hVar, v vVar, String str) {
        a("error", str);
        if (hVar != null) {
            hVar.onError(new l(vVar, str));
        }
    }

    static void c(h<a> hVar, String str) {
        a("error", str);
        if (hVar != null) {
            hVar.onError(new k(str));
        }
    }

    static void a(h<a> hVar, k kVar) {
        a("error", kVar.getMessage());
        if (hVar != null) {
            hVar.onError(kVar);
        }
    }

    private static void a(String str, String str2) {
        com.facebook.a.b c = com.facebook.a.b.c(o.h());
        Bundle bundle = new Bundle();
        bundle.putString(com.facebook.internal.a.S, str);
        if (str2 != null) {
            bundle.putString(com.facebook.internal.a.X, str2);
        }
        c.a(com.facebook.internal.a.aj, null, bundle);
    }

    public static GraphRequest a(AccessToken accessToken, Bitmap bitmap, GraphRequest.b bVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", bitmap);
        return new GraphRequest(accessToken, b, bundle, w.b, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, File file, GraphRequest.b bVar) throws FileNotFoundException {
        Parcelable parcelableResourceWithMimeType = new ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, 268435456), "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, b, bundle, w.b, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, Uri uri, GraphRequest.b bVar) throws FileNotFoundException {
        if (ah.d(uri)) {
            return a(accessToken, new File(uri.getPath()), bVar);
        }
        if (ah.c(uri)) {
            Parcelable parcelableResourceWithMimeType = new ParcelableResourceWithMimeType((Parcelable) uri, "image/png");
            Bundle bundle = new Bundle(1);
            bundle.putParcelable("file", parcelableResourceWithMimeType);
            return new GraphRequest(accessToken, b, bundle, w.b, bVar);
        }
        throw new k("The image Uri must be either a file:// or content:// Uri");
    }

    @Nullable
    public static e a(e eVar, e eVar2) {
        if (eVar == eVar2) {
            return eVar;
        }
        if (eVar == e.UNKNOWN) {
            return eVar2;
        }
        return eVar2 != e.UNKNOWN ? null : eVar;
    }
}
