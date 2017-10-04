package com.facebook.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookContentProvider;
import com.facebook.k;
import com.facebook.o;
import dji.pilot.usercenter.protocol.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class aa {
    static final String a = "com.facebook.NativeAppCallAttachmentStore.files";
    private static final String b = aa.class.getName();
    private static File c;

    public static final class a {
        private final UUID a;
        private final String b;
        private final String c;
        private Bitmap d;
        private Uri e;
        private boolean f;
        private boolean g;

        private a(UUID uuid, Bitmap bitmap, Uri uri) {
            boolean z = true;
            this.a = uuid;
            this.d = bitmap;
            this.e = uri;
            if (uri != null) {
                String scheme = uri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.f = true;
                    if (uri.getAuthority() == null || uri.getAuthority().startsWith("media")) {
                        z = false;
                    }
                    this.g = z;
                } else if (d.A.equalsIgnoreCase(uri.getScheme())) {
                    this.g = true;
                } else if (!ah.b(uri)) {
                    throw new k("Unsupported scheme for media Uri : " + scheme);
                }
            } else if (bitmap != null) {
                this.g = true;
            } else {
                throw new k("Cannot share media without a bitmap or Uri set");
            }
            this.c = !this.g ? null : UUID.randomUUID().toString();
            this.b = !this.g ? this.e.toString() : FacebookContentProvider.a(o.k(), uuid, this.c);
        }

        public String a() {
            return this.b;
        }
    }

    private aa() {
    }

    public static a a(UUID uuid, Bitmap bitmap) {
        ai.a((Object) uuid, "callId");
        ai.a((Object) bitmap, "attachmentBitmap");
        return new a(uuid, bitmap, null);
    }

    public static a a(UUID uuid, Uri uri) {
        ai.a((Object) uuid, "callId");
        ai.a((Object) uri, "attachmentUri");
        return new a(uuid, null, uri);
    }

    private static void a(Bitmap bitmap, File file) throws IOException {
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            ah.a(fileOutputStream);
        }
    }

    private static void a(Uri uri, boolean z, File file) throws IOException {
        InputStream openInputStream;
        Closeable fileOutputStream = new FileOutputStream(file);
        if (z) {
            openInputStream = o.h().getContentResolver().openInputStream(uri);
        } else {
            try {
                openInputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                ah.a(fileOutputStream);
            }
        }
        ah.a(openInputStream, (OutputStream) fileOutputStream);
        ah.a(fileOutputStream);
    }

    public static void a(Collection<a> collection) {
        if (collection != null && collection.size() != 0) {
            if (c == null) {
                c();
            }
            b();
            List<File> arrayList = new ArrayList();
            try {
                for (a aVar : collection) {
                    if (aVar.g) {
                        File a = a(aVar.a, aVar.c, true);
                        arrayList.add(a);
                        if (aVar.d != null) {
                            a(aVar.d, a);
                        } else if (aVar.e != null) {
                            a(aVar.e, aVar.f, a);
                        }
                    }
                }
            } catch (Throwable e) {
                Throwable th = e;
                Log.e(b, "Got unexpected exception:" + th);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception e2) {
                    }
                }
                throw new k(th);
            }
        }
    }

    public static void a(UUID uuid) {
        File a = a(uuid, false);
        if (a != null) {
            ah.a(a);
        }
    }

    public static File a(UUID uuid, String str) throws FileNotFoundException {
        if (ah.a(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return a(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File a() {
        File file;
        synchronized (aa.class) {
            if (c == null) {
                c = new File(o.h().getCacheDir(), a);
            }
            file = c;
        }
        return file;
    }

    static File b() {
        File a = a();
        a.mkdirs();
        return a;
    }

    static File a(UUID uuid, boolean z) {
        if (c == null) {
            return null;
        }
        File file = new File(c, uuid.toString());
        if (!z || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    static File a(UUID uuid, String str, boolean z) throws IOException {
        File a = a(uuid, z);
        if (a == null) {
            return null;
        }
        try {
            return new File(a, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void c() {
        ah.a(a());
    }
}
