package dji.thirdparty.b.a.d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public final class a implements f {
    private final X509TrustManager a;
    private final Method b;

    public a(X509TrustManager x509TrustManager, Method method) {
        this.b = method;
        this.a = x509TrustManager;
    }

    public X509Certificate a(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.b.invoke(this.a, new Object[]{x509Certificate});
            return trustAnchor != null ? trustAnchor.getTrustedCert() : null;
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        } catch (InvocationTargetException e2) {
            return null;
        }
    }

    public static f a(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});
            declaredMethod.setAccessible(true);
            return new a(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
