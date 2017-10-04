package com.flurry.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.msgpack.core.MessagePack.Code;
import org.xeustechnologies.jtar.TarHeader;

public class ir<ObjectType> {
    private static final String a = ir.class.getSimpleName();
    private static final byte[] b = new byte[]{(byte) 113, (byte) -92, (byte) -8, (byte) 125, (byte) 121, (byte) 107, (byte) -65, Code.TRUE, (byte) -74, (byte) -114, Code.NEGFIXINT_PREFIX, (byte) 0, Code.EXT8, (byte) -87, Code.ARRAY32, Code.EXT16, (byte) -6, Code.UINT8, TarHeader.LF_CHR, (byte) 126, (byte) -104, TarHeader.LF_LINK, (byte) 79, Code.UINT8, (byte) 118, (byte) -84, (byte) 99, Code.UINT8, (byte) -14, (byte) -126, (byte) -27, Code.NIL};
    private String c;
    private jh<ObjectType> d;

    public static void a(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            int length2 = b.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) ((bArr[i] ^ b[i % length2]) ^ ((i * 31) % 251));
            }
        }
    }

    public static void b(byte[] bArr) {
        a(bArr);
    }

    public static int c(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        if ifVar = new if();
        ifVar.update(bArr);
        return ifVar.b();
    }

    public ir(String str, jh<ObjectType> jhVar) {
        this.c = str;
        this.d = jhVar;
    }

    public byte[] a(ObjectType objectType) throws IOException {
        if (objectType == null) {
            throw new IOException("Encoding: " + this.c + ": Nothing to encode");
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.d.a(byteArrayOutputStream, objectType);
        Object toByteArray = byteArrayOutputStream.toByteArray();
        in.a(3, a, "Encoding " + this.c + ": " + new String(toByteArray));
        jh jfVar = new jf(new jd());
        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        jfVar.a(byteArrayOutputStream2, toByteArray);
        byte[] toByteArray2 = byteArrayOutputStream2.toByteArray();
        a(toByteArray2);
        return toByteArray2;
    }

    public ObjectType d(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IOException("Decoding: " + this.c + ": Nothing to decode");
        }
        b(bArr);
        byte[] bArr2 = (byte[]) new jf(new jd()).b(new ByteArrayInputStream(bArr));
        in.a(3, a, "Decoding: " + this.c + ": " + new String(bArr2));
        return this.d.b(new ByteArrayInputStream(bArr2));
    }
}
