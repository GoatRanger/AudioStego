package dji.thirdparty.b;

import dji.thirdparty.b.a.d.f;
import dji.thirdparty.b.a.j;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class g {
    public static final g a = new a().a();
    private final List<b> b;
    private final f c;

    public static final class a {
        private final List<b> a = new ArrayList();
        private f b;

        a(g gVar) {
            this.a.addAll(gVar.b);
            this.b = gVar.c;
        }

        public a a(f fVar) {
            this.b = fVar;
            return this;
        }

        public a a(String str, String... strArr) {
            if (str == null) {
                throw new IllegalArgumentException("pattern == null");
            }
            for (String bVar : strArr) {
                this.a.add(new b(str, bVar));
            }
            return this;
        }

        public g a() {
            return new g();
        }
    }

    static final class b {
        final String a;
        final String b;
        final dji.thirdparty.c.f c;

        b(String str, String str2) {
            this.a = str;
            if (str2.startsWith("sha1/")) {
                this.b = "sha1/";
                this.c = dji.thirdparty.c.f.b(str2.substring("sha1/".length()));
            } else if (str2.startsWith("sha256/")) {
                this.b = "sha256/";
                this.c = dji.thirdparty.c.f.b(str2.substring("sha256/".length()));
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
            if (this.c == null) {
                throw new IllegalArgumentException("pins must be base64: " + str2);
            }
        }

        boolean a(String str) {
            boolean z = false;
            if (this.a.equals(str)) {
                return true;
            }
            int indexOf = str.indexOf(46);
            if (this.a.startsWith("*.")) {
                if (str.regionMatches(false, indexOf + 1, this.a, 2, this.a.length() - 2)) {
                    z = true;
                }
            }
            return z;
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && this.a.equals(((b) obj).a) && this.b.equals(((b) obj).b) && this.c.equals(((b) obj).c);
        }

        public int hashCode() {
            return ((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return this.b + this.c.b();
        }
    }

    private g(a aVar) {
        this.b = j.a(aVar.a);
        this.c = aVar.b;
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List a = a(str);
        if (!a.isEmpty()) {
            int i;
            if (this.c != null) {
                list = new dji.thirdparty.b.a.d.b(this.c).a(list);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i2);
                int size2 = a.size();
                int i3 = 0;
                Object obj = null;
                Object obj2 = null;
                while (i3 < size2) {
                    b bVar = (b) a.get(i3);
                    if (bVar.b.equals("sha256/")) {
                        if (obj == null) {
                            obj = b(x509Certificate);
                        }
                        if (bVar.c.equals(obj)) {
                            return;
                        }
                    } else if (bVar.b.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = a(x509Certificate);
                        }
                        if (bVar.c.equals(obj2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
                    }
                    Object obj3 = obj;
                    i3++;
                    obj2 = obj2;
                    obj = obj3;
                }
            }
            StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (i = 0; i < size3; i++) {
                Certificate certificate = (X509Certificate) list.get(i);
                append.append("\n    ").append(a(certificate)).append(": ").append(certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(":");
            size3 = a.size();
            for (i = 0; i < size3; i++) {
                append.append("\n    ").append((b) a.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    public void a(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        a(str, Arrays.asList(certificateArr));
    }

    List<b> a(String str) {
        List<b> emptyList = Collections.emptyList();
        for (b bVar : this.b) {
            if (bVar.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(bVar);
            }
        }
        return emptyList;
    }

    a a() {
        return new a(this);
    }

    public static String a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + b((X509Certificate) certificate).b();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static dji.thirdparty.c.f a(X509Certificate x509Certificate) {
        return j.a(dji.thirdparty.c.f.a(x509Certificate.getPublicKey().getEncoded()));
    }

    static dji.thirdparty.c.f b(X509Certificate x509Certificate) {
        return j.b(dji.thirdparty.c.f.a(x509Certificate.getPublicKey().getEncoded()));
    }
}
