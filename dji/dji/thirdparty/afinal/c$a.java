package dji.thirdparty.afinal;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

final class c$a implements X509TrustManager {
    private X509TrustManager a = null;

    public c$a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers == null || trustManagers.length <= 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        this.a = a(trustManagers);
    }

    private X509TrustManager a(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (this.a != null) {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr != null && x509CertificateArr.length > 0) {
            try {
                x509CertificateArr[0].checkValidity();
            } catch (Exception e) {
                throw new CertificateException("Certificate not valid or trusted");
            }
        } else if (this.a != null) {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.a != null ? this.a.getAcceptedIssuers() : new X509Certificate[0];
    }
}
