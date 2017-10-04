package dji.thirdparty.b;

import dji.thirdparty.b.a.j;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class s {
    private final ag a;
    private final i b;
    private final List<Certificate> c;
    private final List<Certificate> d;

    private s(ag agVar, i iVar, List<Certificate> list, List<Certificate> list2) {
        this.a = agVar;
        this.b = iVar;
        this.c = list;
        this.d = list2;
    }

    public static s a(SSLSession sSLSession) {
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        i forJavaName = i.forJavaName(cipherSuite);
        cipherSuite = sSLSession.getProtocol();
        if (cipherSuite == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        Object[] peerCertificates;
        List a;
        List a2;
        ag forJavaName2 = ag.forJavaName(cipherSuite);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            peerCertificates = null;
        }
        if (peerCertificates != null) {
            a = j.a(peerCertificates);
        } else {
            a = Collections.emptyList();
        }
        Object[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            a2 = j.a(localCertificates);
        } else {
            a2 = Collections.emptyList();
        }
        return new s(forJavaName2, forJavaName, a, a2);
    }

    public static s a(ag agVar, i iVar, List<Certificate> list, List<Certificate> list2) {
        if (iVar != null) {
            return new s(agVar, iVar, j.a((List) list), j.a((List) list2));
        }
        throw new IllegalArgumentException("cipherSuite == null");
    }

    public ag a() {
        return this.a;
    }

    public i b() {
        return this.b;
    }

    public List<Certificate> c() {
        return this.c;
    }

    public Principal d() {
        return !this.c.isEmpty() ? ((X509Certificate) this.c.get(0)).getSubjectX500Principal() : null;
    }

    public List<Certificate> e() {
        return this.d;
    }

    public Principal f() {
        return !this.d.isEmpty() ? ((X509Certificate) this.d.get(0)).getSubjectX500Principal() : null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        if (j.a(this.b, sVar.b) && this.b.equals(sVar.b) && this.c.equals(sVar.c) && this.d.equals(sVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a != null ? this.a.hashCode() : 0) + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
