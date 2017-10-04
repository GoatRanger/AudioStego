package dji.thirdparty.c;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class m extends h {
    private final MessageDigest a;

    public static m a(v vVar) {
        return new m(vVar, "MD5");
    }

    public static m b(v vVar) {
        return new m(vVar, "SHA-1");
    }

    public static m c(v vVar) {
        return new m(vVar, "SHA-256");
    }

    private m(v vVar, String str) {
        super(vVar);
        try {
            this.a = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public void a_(c cVar, long j) throws IOException {
        long j2 = 0;
        y.a(cVar.c, 0, j);
        s sVar = cVar.b;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, (long) (sVar.e - sVar.d));
            this.a.update(sVar.c, sVar.d, min);
            j2 += (long) min;
            sVar = sVar.h;
        }
        super.a_(cVar, j);
    }

    public f c() {
        return f.a(this.a.digest());
    }
}
