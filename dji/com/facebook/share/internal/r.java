package com.facebook.share.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.h;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.internal.ak;
import com.facebook.k;
import com.facebook.l;
import com.facebook.n;
import com.facebook.o;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.v;
import com.facebook.w;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class r {
    private static final String a = "VideoUploader";
    private static final String b = "upload_phase";
    private static final String c = "start";
    private static final String d = "transfer";
    private static final String e = "finish";
    private static final String f = "title";
    private static final String g = "description";
    private static final String h = "ref";
    private static final String i = "file_size";
    private static final String j = "upload_session_id";
    private static final String k = "video_id";
    private static final String l = "start_offset";
    private static final String m = "end_offset";
    private static final String n = "video_file_chunk";
    private static final String o = "Video upload failed";
    private static final String p = "Unexpected error in server response";
    private static final int q = 8;
    private static final int r = 2;
    private static final int s = 5000;
    private static final int t = 3;
    private static boolean u;
    private static Handler v;
    private static ak w = new ak(8);
    private static Set<d> x = new HashSet();
    private static com.facebook.d y;

    private static abstract class e implements Runnable {
        protected d b;
        protected int c;

        protected abstract Bundle a() throws Exception;

        protected abstract void a(int i);

        protected abstract void a(k kVar);

        protected abstract void a(JSONObject jSONObject) throws JSONException;

        protected abstract Set<Integer> b();

        protected e(d dVar, int i) {
            this.b = dVar;
            this.c = i;
        }

        public void run() {
            if (this.b.m) {
                b(null);
                return;
            }
            try {
                a(a());
            } catch (k e) {
                b(e);
            } catch (Throwable e2) {
                b(new k(r.o, e2));
            }
        }

        protected void a(Bundle bundle) {
            Bundle bundle2 = bundle;
            v m = new GraphRequest(this.b.f, String.format(Locale.ROOT, "%s/videos", new Object[]{this.b.e}), bundle2, w.b, null).m();
            if (m != null) {
                n a = m.a();
                JSONObject b = m.b();
                if (a != null) {
                    if (!b(a.d())) {
                        a(new l(m, r.o));
                        return;
                    }
                    return;
                } else if (b != null) {
                    try {
                        a(b);
                        return;
                    } catch (Throwable e) {
                        b(new k(r.p, e));
                        return;
                    }
                } else {
                    a(new k(r.p));
                    return;
                }
            }
            a(new k(r.p));
        }

        private boolean b(int i) {
            if (this.c >= 2 || !b().contains(Integer.valueOf(i))) {
                return false;
            }
            r.d().postDelayed(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(this.a.c + 1);
                }
            }, (long) (((int) Math.pow(3.0d, (double) this.c)) * 5000));
            return true;
        }

        protected void b(k kVar) {
            a(kVar, null);
        }

        protected void a(final k kVar, final String str) {
            r.d().post(new Runnable(this) {
                final /* synthetic */ e c;

                public void run() {
                    r.b(this.c.b, kVar, str);
                }
            });
        }
    }

    private static class a extends e {
        static final Set<Integer> a = new HashSet<Integer>() {
            {
                add(Integer.valueOf(1363011));
            }
        };

        public a(d dVar, int i) {
            super(dVar, i);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            if (this.b.o != null) {
                bundle.putAll(this.b.o);
            }
            bundle.putString(r.b, r.e);
            bundle.putString(r.j, this.b.h);
            ah.a(bundle, "title", this.b.b);
            ah.a(bundle, "description", this.b.c);
            ah.a(bundle, r.h, this.b.d);
            return bundle;
        }

        protected void a(JSONObject jSONObject) throws JSONException {
            if (jSONObject.getBoolean("success")) {
                a(null, this.b.i);
            } else {
                a(new k(r.p));
            }
        }

        protected void a(k kVar) {
            r.b((Exception) kVar, "Video '%s' failed to finish uploading", this.b.i);
            b(kVar);
        }

        protected Set<Integer> b() {
            return a;
        }

        protected void a(int i) {
            r.d(this.b, i);
        }
    }

    private static class b extends e {
        static final Set<Integer> a = new HashSet<Integer>() {
            {
                add(Integer.valueOf(HorizonalSegmentView.N));
            }
        };

        public b(d dVar, int i) {
            super(dVar, i);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putString(r.b, "start");
            bundle.putLong(r.i, this.b.k);
            return bundle;
        }

        protected void a(JSONObject jSONObject) throws JSONException {
            this.b.h = jSONObject.getString(r.j);
            this.b.i = jSONObject.getString("video_id");
            r.b(this.b, jSONObject.getString(r.l), jSONObject.getString(r.m), 0);
        }

        protected void a(k kVar) {
            r.b((Exception) kVar, "Error starting video upload", new Object[0]);
            b(kVar);
        }

        protected Set<Integer> b() {
            return a;
        }

        protected void a(int i) {
            r.c(this.b, i);
        }
    }

    private static class c extends e {
        static final Set<Integer> a = new HashSet<Integer>() {
            {
                add(Integer.valueOf(1363019));
                add(Integer.valueOf(1363021));
                add(Integer.valueOf(1363030));
                add(Integer.valueOf(1363033));
                add(Integer.valueOf(1363041));
            }
        };
        private String d;
        private String e;

        public c(d dVar, String str, String str2, int i) {
            super(dVar, i);
            this.d = str;
            this.e = str2;
        }

        public Bundle a() throws IOException {
            Bundle bundle = new Bundle();
            bundle.putString(r.b, r.d);
            bundle.putString(r.j, this.b.h);
            bundle.putString(r.l, this.d);
            byte[] a = r.b(this.b, this.d, this.e);
            if (a != null) {
                bundle.putByteArray(r.n, a);
                return bundle;
            }
            throw new k("Error reading video");
        }

        protected void a(JSONObject jSONObject) throws JSONException {
            Object string = jSONObject.getString(r.l);
            Object string2 = jSONObject.getString(r.m);
            if (ah.a(string, string2)) {
                r.d(this.b, 0);
            } else {
                r.b(this.b, string, string2, 0);
            }
        }

        protected void a(k kVar) {
            r.b((Exception) kVar, "Error uploading video '%s'", this.b.i);
            b(kVar);
        }

        protected Set<Integer> b() {
            return a;
        }

        protected void a(int i) {
            r.b(this.b, this.d, this.e, i);
        }
    }

    private static class d {
        public final Uri a;
        public final String b;
        public final String c;
        public final String d;
        public final String e;
        public final AccessToken f;
        public final h<com.facebook.share.c.a> g;
        public String h;
        public String i;
        public InputStream j;
        public long k;
        public String l;
        public boolean m;
        public com.facebook.internal.ak.a n;
        public Bundle o;

        private d(ShareVideoContent shareVideoContent, String str, h<com.facebook.share.c.a> hVar) {
            this.l = "0";
            this.f = AccessToken.a();
            this.a = shareVideoContent.d().b();
            this.b = shareVideoContent.b();
            this.c = shareVideoContent.a();
            this.d = shareVideoContent.k();
            this.e = str;
            this.g = hVar;
            this.o = shareVideoContent.d().a();
            if (!ah.a(shareVideoContent.i())) {
                this.o.putString("tags", TextUtils.join(", ", shareVideoContent.i()));
            }
            if (!ah.a(shareVideoContent.j())) {
                this.o.putString("place", shareVideoContent.j());
            }
            if (!ah.a(shareVideoContent.k())) {
                this.o.putString(r.h, shareVideoContent.k());
            }
        }

        private void a() throws FileNotFoundException {
            try {
                if (ah.d(this.a)) {
                    ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(this.a.getPath()), 268435456);
                    this.k = open.getStatSize();
                    this.j = new AutoCloseInputStream(open);
                } else if (ah.c(this.a)) {
                    this.k = ah.e(this.a);
                    this.j = o.h().getContentResolver().openInputStream(this.a);
                } else {
                    throw new k("Uri must be a content:// or file:// uri");
                }
            } catch (FileNotFoundException e) {
                ah.a(this.j);
                throw e;
            }
        }
    }

    public static synchronized void a(ShareVideoContent shareVideoContent, h<com.facebook.share.c.a> hVar) throws FileNotFoundException {
        synchronized (r.class) {
            a(shareVideoContent, "me", (h) hVar);
        }
    }

    public static synchronized void a(ShareVideoContent shareVideoContent, String str, h<com.facebook.share.c.a> hVar) throws FileNotFoundException {
        synchronized (r.class) {
            if (!u) {
                e();
                u = true;
            }
            ai.a((Object) shareVideoContent, "videoContent");
            ai.a((Object) str, "graphNode");
            Object d = shareVideoContent.d();
            ai.a(d, "videoContent.video");
            ai.a(d.b(), "videoContent.video.localUrl");
            d dVar = new d(shareVideoContent, str, hVar);
            dVar.a();
            x.add(dVar);
            c(dVar, 0);
        }
    }

    private static synchronized void c() {
        synchronized (r.class) {
            for (d dVar : x) {
                dVar.m = true;
            }
        }
    }

    private static synchronized void a(d dVar) {
        synchronized (r.class) {
            x.remove(dVar);
        }
    }

    private static synchronized Handler d() {
        Handler handler;
        synchronized (r.class) {
            if (v == null) {
                v = new Handler(Looper.getMainLooper());
            }
            handler = v;
        }
        return handler;
    }

    private static void b(d dVar, k kVar, String str) {
        a(dVar);
        ah.a(dVar.j);
        if (dVar.g == null) {
            return;
        }
        if (kVar != null) {
            q.a(dVar.g, kVar);
        } else if (dVar.m) {
            q.b(dVar.g);
        } else {
            q.b(dVar.g, str);
        }
    }

    private static void c(d dVar, int i) {
        a(dVar, new b(dVar, i));
    }

    private static void b(d dVar, String str, String str2, int i) {
        a(dVar, new c(dVar, str, str2, i));
    }

    private static void d(d dVar, int i) {
        a(dVar, new a(dVar, i));
    }

    private static synchronized void a(d dVar, Runnable runnable) {
        synchronized (r.class) {
            dVar.n = w.a(runnable);
        }
    }

    private static byte[] b(d dVar, String str, String str2) throws IOException {
        if (ah.a((Object) str, dVar.l)) {
            int read;
            int parseLong = (int) (Long.parseLong(str2) - Long.parseLong(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[Math.min(8192, parseLong)];
            do {
                read = dVar.j.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                    parseLong -= read;
                    if (parseLong == 0) {
                    }
                }
                dVar.l = str2;
                return byteArrayOutputStream.toByteArray();
            } while (parseLong >= 0);
            b(null, "Error reading video chunk. Expected buffer length - '%d'. Actual - '%d'.", Integer.valueOf(parseLong + read), Integer.valueOf(read));
            return null;
        }
        b(null, "Error reading video chunk. Expected chunk '%s'. Requested chunk '%s'.", dVar.l, str);
        return null;
    }

    private static void e() {
        y = new com.facebook.d() {
            protected void a(AccessToken accessToken, AccessToken accessToken2) {
                if (accessToken != null) {
                    if (accessToken2 == null || !ah.a(accessToken2.j(), accessToken.j())) {
                        r.c();
                    }
                }
            }
        };
    }

    private static void b(Exception exception, String str, Object... objArr) {
        Log.e(a, String.format(Locale.ROOT, str, objArr), exception);
    }
}
