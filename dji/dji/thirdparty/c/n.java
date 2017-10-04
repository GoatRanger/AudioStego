package dji.thirdparty.c;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class n extends i {
    private final MessageDigest a;

    public static n a(w wVar) {
        return new n(wVar, "MD5");
    }

    public static n b(w wVar) {
        return new n(wVar, "SHA-1");
    }

    public static n c(w wVar) {
        return new n(wVar, "SHA-256");
    }

    private n(w wVar, String str) {
        super(wVar);
        try {
            this.a = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public long a(c cVar, long j) throws IOException {
        long a = super.a(cVar, j);
        if (a != -1) {
            long j2 = cVar.c - a;
            long j3 = cVar.c;
            s sVar = cVar.b;
            while (j3 > cVar.c - a) {
                sVar = sVar.i;
                j3 -= (long) (sVar.e - sVar.d);
            }
            while (j3 < cVar.c) {
                int i = (int) ((j2 + ((long) sVar.d)) - j3);
                this.a.update(sVar.c, i, sVar.e - i);
                j3 += (long) (sVar.e - sVar.d);
                j2 = j3;
            }
        }
        return a;
    }

    public f c() {
        return f.a(this.a.digest());
    }
}
