package dji.thirdparty.b.a;

import dji.thirdparty.b.l;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

public final class a {
    private final List<l> a;
    private int b = 0;
    private boolean c;
    private boolean d;

    public a(List<l> list) {
        this.a = list;
    }

    public l a(SSLSocket sSLSocket) throws IOException {
        l lVar;
        int i = this.b;
        int size = this.a.size();
        for (int i2 = i; i2 < size; i2++) {
            lVar = (l) this.a.get(i2);
            if (lVar.a(sSLSocket)) {
                this.b = i2 + 1;
                break;
            }
        }
        lVar = null;
        if (lVar == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.d + ", modes=" + this.a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.c = b(sSLSocket);
        d.b.a(lVar, sSLSocket, this.d);
        return lVar;
    }

    public boolean a(IOException iOException) {
        this.d = true;
        if (!this.c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if ((iOException instanceof SSLHandshakeException) || (iOException instanceof SSLProtocolException)) {
            return true;
        }
        return false;
    }

    private boolean b(SSLSocket sSLSocket) {
        for (int i = this.b; i < this.a.size(); i++) {
            if (((l) this.a.get(i)).a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
