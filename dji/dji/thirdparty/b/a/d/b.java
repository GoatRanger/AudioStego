package dji.thirdparty.b.a.d;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class b {
    private static final int a = 9;
    private final f b;

    public b(f fVar) {
        this.b = fVar;
    }

    public List<Certificate> a(List<Certificate> list) throws SSLPeerUnverifiedException {
        Deque arrayDeque = new ArrayDeque(list);
        List<Certificate> arrayList = new ArrayList();
        arrayList.add(arrayDeque.removeFirst());
        int i = 0;
        Object obj = null;
        while (i < 9) {
            Object obj2;
            X509Certificate x509Certificate = (X509Certificate) arrayList.get(arrayList.size() - 1);
            X509Certificate a = this.b.a(x509Certificate);
            if (a != null) {
                if (arrayList.size() > 1 || !x509Certificate.equals(a)) {
                    arrayList.add(a);
                }
                if (a(a, a)) {
                    return arrayList;
                }
                obj2 = 1;
            } else {
                Iterator it = arrayDeque.iterator();
                while (it.hasNext()) {
                    a = (X509Certificate) it.next();
                    if (a(x509Certificate, a)) {
                        it.remove();
                        arrayList.add(a);
                        obj2 = obj;
                    }
                }
                if (obj != null) {
                    return arrayList;
                }
                throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
            }
            i++;
            obj = obj2;
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }

    private boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }
}
