package dji.thirdparty.b;

import dji.thirdparty.b.a.j;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class l {
    public static final l a = new a(true).a(d).a(ag.TLS_1_2, ag.TLS_1_1, ag.TLS_1_0).a(true).c();
    public static final l b = new a(a).a(ag.TLS_1_0).a(true).c();
    public static final l c = new a(false).c();
    private static final i[] d = new i[]{i.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, i.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, i.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, i.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, i.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, i.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, i.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, i.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, i.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, i.TLS_RSA_WITH_AES_128_GCM_SHA256, i.TLS_RSA_WITH_AES_128_CBC_SHA, i.TLS_RSA_WITH_AES_256_CBC_SHA, i.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    private final boolean e;
    private final boolean f;
    private final String[] g;
    private final String[] h;

    public static final class a {
        private boolean a;
        private String[] b;
        private String[] c;
        private boolean d;

        a(boolean z) {
            this.a = z;
        }

        public a(l lVar) {
            this.a = lVar.e;
            this.b = lVar.g;
            this.c = lVar.h;
            this.d = lVar.f;
        }

        public a a() {
            if (this.a) {
                this.b = null;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(i... iVarArr) {
            if (this.a) {
                String[] strArr = new String[iVarArr.length];
                for (int i = 0; i < iVarArr.length; i++) {
                    strArr[i] = iVarArr[i].aS;
                }
                return a(strArr);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.b = (String[]) strArr.clone();
                return this;
            }
        }

        public a b() {
            if (this.a) {
                this.c = null;
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(ag... agVarArr) {
            if (this.a) {
                String[] strArr = new String[agVarArr.length];
                for (int i = 0; i < agVarArr.length; i++) {
                    strArr[i] = agVarArr[i].e;
                }
                return b(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a b(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.c = (String[]) strArr.clone();
                return this;
            }
        }

        public a a(boolean z) {
            if (this.a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public l c() {
            return new l();
        }
    }

    private l(a aVar) {
        this.e = aVar.a;
        this.g = aVar.b;
        this.h = aVar.c;
        this.f = aVar.d;
    }

    public boolean a() {
        return this.e;
    }

    public List<i> b() {
        if (this.g == null) {
            return null;
        }
        Object[] objArr = new i[this.g.length];
        for (int i = 0; i < this.g.length; i++) {
            objArr[i] = i.forJavaName(this.g[i]);
        }
        return j.a(objArr);
    }

    public List<ag> c() {
        if (this.h == null) {
            return null;
        }
        Object[] objArr = new ag[this.h.length];
        for (int i = 0; i < this.h.length; i++) {
            objArr[i] = ag.forJavaName(this.h[i]);
        }
        return j.a(objArr);
    }

    public boolean d() {
        return this.f;
    }

    void a(SSLSocket sSLSocket, boolean z) {
        l b = b(sSLSocket, z);
        if (b.h != null) {
            sSLSocket.setEnabledProtocols(b.h);
        }
        if (b.g != null) {
            sSLSocket.setEnabledCipherSuites(b.g);
        }
    }

    private l b(SSLSocket sSLSocket, boolean z) {
        String[] strArr;
        String[] strArr2;
        if (this.g != null) {
            strArr = (String[]) j.a(String.class, this.g, sSLSocket.getEnabledCipherSuites());
        } else {
            strArr = sSLSocket.getEnabledCipherSuites();
        }
        if (this.h != null) {
            strArr2 = (String[]) j.a(String.class, this.h, sSLSocket.getEnabledProtocols());
        } else {
            strArr2 = sSLSocket.getEnabledProtocols();
        }
        if (z && j.a(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV")) {
            strArr = j.b(strArr, "TLS_FALLBACK_SCSV");
        }
        return new a(this).a(strArr).b(strArr2).c();
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.e) {
            return false;
        }
        if (this.h != null && !a(this.h, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.g == null || a(this.g, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    private static boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String a : strArr) {
            if (j.a(strArr2, a)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        l lVar = (l) obj;
        if (this.e != lVar.e) {
            return false;
        }
        if (!this.e || (Arrays.equals(this.g, lVar.g) && Arrays.equals(this.h, lVar.h) && this.f == lVar.f)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.e) {
            return 17;
        }
        return (this.f ? 0 : 1) + ((((Arrays.hashCode(this.g) + 527) * 31) + Arrays.hashCode(this.h)) * 31);
    }

    public String toString() {
        if (!this.e) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.g != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.h != null ? c().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f + ")";
    }
}
