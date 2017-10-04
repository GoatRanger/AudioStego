package com.here.network;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

class NetworkSSLContextFactory {
    private static NetworkSSLContextFactory s_instance = null;
    private final String LOGTAG = "NetworkSSLContextFactory";
    private CertificateFactory m_certificateFactory = null;
    private String m_certificatesPath = null;
    private SSLContext m_sslContext = null;
    private TrustManagerFactory m_trustManager;

    private NetworkSSLContextFactory() {
        try {
            this.m_certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (Exception e) {
            Log.e("NetworkSSLContextFactory", "X509 CertificateFactory failed to create" + e);
        }
        Log.d("NetworkSSLContextFactory", "NetworkSSLContextFactory created successfully");
    }

    private void generateSSlContext() {
        if (this.m_certificateFactory == null) {
            Log.w("NetworkSSLContextFactory", "generateSSlContext failed since certificateFactory is null");
            return;
        }
        Log.d("NetworkSSLContextFactory", "generateSSlContext BEGIN");
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            List listFiles = getListFiles(new File(this.m_certificatesPath));
            Log.d("NetworkSSLContextFactory", "The number of files " + listFiles.size() + " found in " + this.m_certificatesPath);
            for (int i = 0; i < listFiles.size(); i++) {
                File file = (File) listFiles.get(i);
                Certificate loadCertificate = loadCertificate(file);
                if (loadCertificate != null) {
                    instance.setCertificateEntry(file.getName(), loadCertificate);
                } else {
                    Log.e("NetworkSSLContextFactory", "invalid certificate file " + file.getName());
                }
            }
            Log.d("NetworkSSLContextFactory", "The number of valid certificates " + instance.size());
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            this.m_sslContext = SSLContext.getInstance("TLS");
            this.m_sslContext.init(null, instance2.getTrustManagers(), null);
        } catch (Exception e) {
            Log.e("NetworkSSLContextFactory", "failed to generate ssl context " + e);
        }
        Log.d("NetworkSSLContextFactory", "generateSSlContext END " + this.m_sslContext);
    }

    private Certificate loadCertificate(File file) {
        if (file.exists()) {
            try {
                InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                Certificate generateCertificate = this.m_certificateFactory.generateCertificate(bufferedInputStream);
                if (generateCertificate instanceof X509Certificate) {
                    X509Certificate x509Certificate = (X509Certificate) generateCertificate;
                }
                bufferedInputStream.close();
                return generateCertificate;
            } catch (Exception e) {
                Log.e("NetworkSSLContextFactory", "Load certificate failed " + e);
                return null;
            }
        }
        Log.e("NetworkSSLContextFactory", "certificate file " + file.getName() + "does not exist");
        return null;
    }

    private List<File> getListFiles(File file) {
        List arrayList = new ArrayList();
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                arrayList.addAll(getListFiles(file2));
            } else if (file2.length() > 1) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    public static synchronized NetworkSSLContextFactory getInstance() {
        NetworkSSLContextFactory networkSSLContextFactory;
        synchronized (NetworkSSLContextFactory.class) {
            if (s_instance == null) {
                s_instance = new NetworkSSLContextFactory();
            }
            networkSSLContextFactory = s_instance;
        }
        return networkSSLContextFactory;
    }

    public SSLContext getSSLContext(String str) {
        if (str == null) {
            Log.w("NetworkSSLContextFactory", "getSSLContext certificatesPath is null");
            return null;
        } else if (str.isEmpty()) {
            Log.w("NetworkSSLContextFactory", "getSSLContext certificatesPath is empty");
            return null;
        } else {
            synchronized (this) {
                Object obj = null;
                if (this.m_certificatesPath == null) {
                    obj = 1;
                } else if (this.m_certificatesPath.compareToIgnoreCase(str) != 0) {
                    int i = 1;
                }
                if (obj != null) {
                    this.m_certificatesPath = str;
                    generateSSlContext();
                }
            }
            return this.m_sslContext;
        }
    }
}
