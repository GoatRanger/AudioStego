package dji.thirdparty.c;

import android.support.v4.media.TransportMediator;
import com.f.a.a.g;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.posclient.UpdateOptions;
import dji.thirdparty.g.b.a.a;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xeustechnologies.jtar.TarHeader;

public final class c implements d, e, Cloneable {
    static final int a = 65533;
    private static final byte[] d = new byte[]{TarHeader.LF_NORMAL, TarHeader.LF_LINK, TarHeader.LF_SYMLINK, TarHeader.LF_CHR, TarHeader.LF_BLK, TarHeader.LF_DIR, TarHeader.LF_FIFO, TarHeader.LF_CONTIG, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    s b;
    long c;

    public /* synthetic */ d F() throws IOException {
        return e();
    }

    public /* synthetic */ d b(String str) throws IOException {
        return a(str);
    }

    public /* synthetic */ d b(String str, int i, int i2) throws IOException {
        return a(str, i, i2);
    }

    public /* synthetic */ d b(String str, int i, int i2, Charset charset) throws IOException {
        return a(str, i, i2, charset);
    }

    public /* synthetic */ d b(String str, Charset charset) throws IOException {
        return a(str, charset);
    }

    public /* synthetic */ d c(byte[] bArr, int i, int i2) throws IOException {
        return b(bArr, i, i2);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return D();
    }

    public /* synthetic */ d d(f fVar) throws IOException {
        return a(fVar);
    }

    public /* synthetic */ d d(byte[] bArr) throws IOException {
        return c(bArr);
    }

    public /* synthetic */ d i(int i) throws IOException {
        return f(i);
    }

    public /* synthetic */ d j(int i) throws IOException {
        return e(i);
    }

    public /* synthetic */ d k(int i) throws IOException {
        return d(i);
    }

    public /* synthetic */ d l(int i) throws IOException {
        return c(i);
    }

    public /* synthetic */ d m(int i) throws IOException {
        return b(i);
    }

    public /* synthetic */ d m(long j) throws IOException {
        return l(j);
    }

    public /* synthetic */ d n(int i) throws IOException {
        return a(i);
    }

    public /* synthetic */ d n(long j) throws IOException {
        return k(j);
    }

    public /* synthetic */ d o(long j) throws IOException {
        return j(j);
    }

    public /* synthetic */ d p(long j) throws IOException {
        return i(j);
    }

    public long b() {
        return this.c;
    }

    public c c() {
        return this;
    }

    public OutputStream d() {
        return new OutputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void write(int i) {
                this.a.b((byte) i);
            }

            public void write(byte[] bArr, int i, int i2) {
                this.a.b(bArr, i, i2);
            }

            public void flush() {
            }

            public void close() {
            }

            public String toString() {
                return this + ".outputStream()";
            }
        };
    }

    public c e() {
        return this;
    }

    public d f() {
        return this;
    }

    public boolean g() {
        return this.c == 0;
    }

    public void a(long j) throws EOFException {
        if (this.c < j) {
            throw new EOFException();
        }
    }

    public boolean b(long j) {
        return this.c >= j;
    }

    public InputStream h() {
        return new InputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public int read() {
                if (this.a.c > 0) {
                    return this.a.j() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                return this.a.a(bArr, i, i2);
            }

            public int available() {
                return (int) Math.min(this.a.c, UpdateOptions.SOURCE_ANY);
            }

            public void close() {
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public c a(OutputStream outputStream) throws IOException {
        return a(outputStream, 0, this.c);
    }

    public c a(OutputStream outputStream, long j, long j2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        y.a(this.c, j, j2);
        if (j2 != 0) {
            s sVar = this.b;
            while (j >= ((long) (sVar.e - sVar.d))) {
                j -= (long) (sVar.e - sVar.d);
                sVar = sVar.h;
            }
            while (j2 > 0) {
                int i = (int) (((long) sVar.d) + j);
                int min = (int) Math.min((long) (sVar.e - i), j2);
                outputStream.write(sVar.c, i, min);
                j2 -= (long) min;
                sVar = sVar.h;
                j = 0;
            }
        }
        return this;
    }

    public c a(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        y.a(this.c, j, j2);
        if (j2 != 0) {
            cVar.c += j2;
            s sVar = this.b;
            while (j >= ((long) (sVar.e - sVar.d))) {
                j -= (long) (sVar.e - sVar.d);
                sVar = sVar.h;
            }
            while (j2 > 0) {
                s sVar2 = new s(sVar);
                sVar2.d = (int) (((long) sVar2.d) + j);
                sVar2.e = Math.min(sVar2.d + ((int) j2), sVar2.e);
                if (cVar.b == null) {
                    sVar2.i = sVar2;
                    sVar2.h = sVar2;
                    cVar.b = sVar2;
                } else {
                    cVar.b.i.a(sVar2);
                }
                j2 -= (long) (sVar2.e - sVar2.d);
                sVar = sVar.h;
                j = 0;
            }
        }
        return this;
    }

    public c b(OutputStream outputStream) throws IOException {
        return a(outputStream, this.c);
    }

    public c a(OutputStream outputStream, long j) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        y.a(this.c, 0, j);
        s sVar = this.b;
        while (j > 0) {
            s a;
            int min = (int) Math.min(j, (long) (sVar.e - sVar.d));
            outputStream.write(sVar.c, sVar.d, min);
            sVar.d += min;
            this.c -= (long) min;
            j -= (long) min;
            if (sVar.d == sVar.e) {
                a = sVar.a();
                this.b = a;
                t.a(sVar);
            } else {
                a = sVar;
            }
            sVar = a;
        }
        return this;
    }

    public c a(InputStream inputStream) throws IOException {
        a(inputStream, (long) IPositioningSession.NotSet, true);
        return this;
    }

    public c a(InputStream inputStream, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        a(inputStream, j, false);
        return this;
    }

    private void a(InputStream inputStream, long j, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j > 0 || z) {
                s g = g(1);
                int read = inputStream.read(g.c, g.e, (int) Math.min(j, (long) (8192 - g.e)));
                if (read == -1) {
                    break;
                }
                g.e += read;
                this.c += (long) read;
                j -= (long) read;
            } else {
                return;
            }
        }
        if (!z) {
            throw new EOFException();
        }
    }

    public long i() {
        long j = this.c;
        if (j == 0) {
            return 0;
        }
        s sVar = this.b.i;
        if (sVar.e >= 8192 || !sVar.g) {
            return j;
        }
        return j - ((long) (sVar.e - sVar.d));
    }

    public byte j() {
        if (this.c == 0) {
            throw new IllegalStateException("size == 0");
        }
        s sVar = this.b;
        int i = sVar.d;
        int i2 = sVar.e;
        int i3 = i + 1;
        byte b = sVar.c[i];
        this.c--;
        if (i3 == i2) {
            this.b = sVar.a();
            t.a(sVar);
        } else {
            sVar.d = i3;
        }
        return b;
    }

    public byte c(long j) {
        y.a(this.c, j, 1);
        s sVar = this.b;
        while (true) {
            int i = sVar.e - sVar.d;
            if (j < ((long) i)) {
                return sVar.c[sVar.d + ((int) j)];
            }
            j -= (long) i;
            sVar = sVar.h;
        }
    }

    public short k() {
        if (this.c < 2) {
            throw new IllegalStateException("size < 2: " + this.c);
        }
        s sVar = this.b;
        int i = sVar.d;
        int i2 = sVar.e;
        if (i2 - i < 2) {
            return (short) (((j() & 255) << 8) | (j() & 255));
        }
        byte[] bArr = sVar.c;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.c -= 2;
        if (i4 == i2) {
            this.b = sVar.a();
            t.a(sVar);
        } else {
            sVar.d = i4;
        }
        return (short) i;
    }

    public int l() {
        if (this.c < 4) {
            throw new IllegalStateException("size < 4: " + this.c);
        }
        s sVar = this.b;
        int i = sVar.d;
        int i2 = sVar.e;
        if (i2 - i < 4) {
            return ((((j() & 255) << 24) | ((j() & 255) << 16)) | ((j() & 255) << 8)) | (j() & 255);
        }
        byte[] bArr = sVar.c;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.c -= 4;
        if (i4 == i2) {
            this.b = sVar.a();
            t.a(sVar);
            return i;
        }
        sVar.d = i4;
        return i;
    }

    public long m() {
        if (this.c < 8) {
            throw new IllegalStateException("size < 8: " + this.c);
        }
        s sVar = this.b;
        int i = sVar.d;
        int i2 = sVar.e;
        if (i2 - i < 8) {
            return ((((long) l()) & 4294967295L) << 32) | (((long) l()) & 4294967295L);
        }
        byte[] bArr = sVar.c;
        int i3 = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        i = i3 + 1;
        long j2 = ((((long) bArr[i3]) & 255) << 48) | j;
        int i4 = i + 1;
        i = i4 + 1;
        j2 = (j2 | ((((long) bArr[i]) & 255) << 40)) | ((((long) bArr[i4]) & 255) << 32);
        i4 = i + 1;
        i = i4 + 1;
        j2 = (j2 | ((((long) bArr[i]) & 255) << 24)) | ((((long) bArr[i4]) & 255) << 16);
        i4 = i + 1;
        int i5 = i4 + 1;
        long j3 = (((long) bArr[i4]) & 255) | (j2 | ((((long) bArr[i]) & 255) << 8));
        this.c -= 8;
        if (i5 == i2) {
            this.b = sVar.a();
            t.a(sVar);
            return j3;
        }
        sVar.d = i5;
        return j3;
    }

    public short n() {
        return y.a(k());
    }

    public int o() {
        return y.a(l());
    }

    public long p() {
        return y.a(m());
    }

    public long q() {
        if (this.c == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        Object obj = null;
        Object obj2 = null;
        long j2 = -7;
        do {
            s sVar = this.b;
            byte[] bArr = sVar.c;
            int i2 = sVar.d;
            int i3 = sVar.e;
            while (i2 < i3) {
                int i4 = bArr[i2];
                if (i4 >= (byte) 48 && i4 <= (byte) 57) {
                    int i5 = 48 - i4;
                    if (j < -922337203685477580L || (j == -922337203685477580L && ((long) i5) < j2)) {
                        c b = new c().k(j).b(i4);
                        if (obj == null) {
                            b.j();
                        }
                        throw new NumberFormatException("Number too large: " + b.t());
                    }
                    j = (j * 10) + ((long) i5);
                } else if (i4 != 45 || i != 0) {
                    if (i != 0) {
                        obj2 = 1;
                        if (i2 != i3) {
                            this.b = sVar.a();
                            t.a(sVar);
                        } else {
                            sVar.d = i2;
                        }
                        if (obj2 == null) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(i4));
                    }
                } else {
                    obj = 1;
                    j2--;
                }
                i2++;
                i++;
            }
            if (i2 != i3) {
                sVar.d = i2;
            } else {
                this.b = sVar.a();
                t.a(sVar);
            }
            if (obj2 == null) {
                break;
            }
        } while (this.b != null);
        this.c -= (long) i;
        if (obj != null) {
            return j;
        }
        return -j;
    }

    public long r() {
        if (this.c == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        Object obj = null;
        do {
            s sVar = this.b;
            byte[] bArr = sVar.c;
            int i2 = sVar.d;
            int i3 = sVar.e;
            int i4 = i2;
            while (i4 < i3) {
                byte b = bArr[i4];
                if (b >= TarHeader.LF_NORMAL && b <= (byte) 57) {
                    i2 = b - 48;
                } else if (b >= (byte) 97 && b <= (byte) 102) {
                    i2 = (b - 97) + 10;
                } else if (b < (byte) 65 || b > (byte) 70) {
                    if (i != 0) {
                        obj = 1;
                        if (i4 != i3) {
                            this.b = sVar.a();
                            t.a(sVar);
                        } else {
                            sVar.d = i4;
                        }
                        if (obj == null) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                    }
                } else {
                    i2 = (b - 65) + 10;
                }
                if ((-1152921504606846976L & j) != 0) {
                    throw new NumberFormatException("Number too large: " + new c().l(j).b((int) b).t());
                }
                i++;
                i4++;
                j = ((long) i2) | (j << 4);
            }
            if (i4 != i3) {
                sVar.d = i4;
            } else {
                this.b = sVar.a();
                t.a(sVar);
            }
            if (obj == null) {
                break;
            }
        } while (this.b != null);
        this.c -= (long) i;
        return j;
    }

    public f s() {
        return new f(x());
    }

    public f d(long j) throws EOFException {
        return new f(g(j));
    }

    public void b(c cVar, long j) throws EOFException {
        if (this.c < j) {
            cVar.a_(this, this.c);
            throw new EOFException();
        } else {
            cVar.a_(this, j);
        }
    }

    public long a(v vVar) throws IOException {
        long j = this.c;
        if (j > 0) {
            vVar.a_(this, j);
        }
        return j;
    }

    public String t() {
        try {
            return a(this.c, y.a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String e(long j) throws EOFException {
        return a(j, y.a);
    }

    public String a(Charset charset) {
        try {
            return a(this.c, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String a(long j, Charset charset) throws EOFException {
        y.a(this.c, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > UpdateOptions.SOURCE_ANY) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            s sVar = this.b;
            if (((long) sVar.d) + j > ((long) sVar.e)) {
                return new String(g(j), charset);
            }
            String str = new String(sVar.c, sVar.d, (int) j, charset);
            sVar.d = (int) (((long) sVar.d) + j);
            this.c -= j;
            if (sVar.d != sVar.e) {
                return str;
            }
            this.b = sVar.a();
            t.a(sVar);
            return str;
        }
    }

    public String u() throws EOFException {
        long a = a((byte) 10);
        if (a == -1) {
            return this.c != 0 ? e(this.c) : null;
        } else {
            return f(a);
        }
    }

    public String v() throws EOFException {
        long a = a((byte) 10);
        if (a != -1) {
            return f(a);
        }
        c cVar = new c();
        a(cVar, 0, Math.min(32, this.c));
        throw new EOFException("\\n not found: size=" + b() + " content=" + cVar.s().g() + "...");
    }

    String f(long j) throws EOFException {
        if (j <= 0 || c(j - 1) != g.SIMPLE_LIST) {
            String e = e(j);
            h(1);
            return e;
        }
        e = e(j - 1);
        h(2);
        return e;
    }

    public int w() throws EOFException {
        if (this.c == 0) {
            throw new EOFException();
        }
        int i;
        int i2;
        int i3;
        byte c = c(0);
        if ((c & 128) == 0) {
            i = 0;
            i2 = c & TransportMediator.KEYCODE_MEDIA_PAUSE;
            i3 = 1;
        } else if ((c & a.fw_) == 192) {
            i2 = c & 31;
            i3 = 2;
            i = 128;
        } else if ((c & 240) == a.fw_) {
            i2 = c & 15;
            i3 = 3;
            i = 2048;
        } else if ((c & 248) == 240) {
            i2 = c & 7;
            i3 = 4;
            i = 65536;
        } else {
            h(1);
            return a;
        }
        if (this.c < ((long) i3)) {
            throw new EOFException("size < " + i3 + ": " + this.c + " (to read code point prefixed 0x" + Integer.toHexString(c) + ")");
        }
        int i4 = i2;
        i2 = 1;
        while (i2 < i3) {
            c = c((long) i2);
            if ((c & 192) == 128) {
                i2++;
                i4 = (c & 63) | (i4 << 6);
            } else {
                h((long) i2);
                return a;
            }
        }
        h((long) i3);
        return i4 > 1114111 ? a : (i4 < 55296 || i4 > 57343) ? i4 < i ? a : i4 : a;
    }

    public byte[] x() {
        try {
            return g(this.c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] g(long j) throws EOFException {
        y.a(this.c, 0, j);
        if (j > UpdateOptions.SOURCE_ANY) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        b(bArr);
        return bArr;
    }

    public int a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public void b(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int a = a(bArr, i, bArr.length - i);
            if (a == -1) {
                throw new EOFException();
            }
            i += a;
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        y.a((long) bArr.length, (long) i, (long) i2);
        s sVar = this.b;
        if (sVar == null) {
            return -1;
        }
        int min = Math.min(i2, sVar.e - sVar.d);
        System.arraycopy(sVar.c, sVar.d, bArr, i, min);
        sVar.d += min;
        this.c -= (long) min;
        if (sVar.d != sVar.e) {
            return min;
        }
        this.b = sVar.a();
        t.a(sVar);
        return min;
    }

    public void y() {
        try {
            h(this.c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public void h(long j) throws EOFException {
        while (j > 0) {
            if (this.b == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.b.e - this.b.d));
            this.c -= (long) min;
            j -= (long) min;
            s sVar = this.b;
            sVar.d = min + sVar.d;
            if (this.b.d == this.b.e) {
                s sVar2 = this.b;
                this.b = sVar2.a();
                t.a(sVar2);
            }
        }
    }

    public c a(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        fVar.a(this);
        return this;
    }

    public c a(String str) {
        return a(str, 0, str.length());
    }

    public c a(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            while (i < i2) {
                int i3;
                char charAt = str.charAt(i);
                if (charAt < '') {
                    int i4;
                    s g = g(1);
                    byte[] bArr = g.c;
                    int i5 = g.e - i;
                    int min = Math.min(i2, 8192 - i5);
                    i3 = i + 1;
                    bArr[i5 + i] = (byte) charAt;
                    while (i3 < min) {
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '') {
                            break;
                        }
                        i4 = i3 + 1;
                        bArr[i3 + i5] = (byte) charAt2;
                        i3 = i4;
                    }
                    i4 = (i3 + i5) - g.e;
                    g.e += i4;
                    this.c += (long) i4;
                } else if (charAt < 'ࠀ') {
                    b((charAt >> 6) | 192);
                    b((charAt & 63) | 128);
                    i3 = i + 1;
                } else if (charAt < '?' || charAt > '?') {
                    b((charAt >> 12) | a.fw_);
                    b(((charAt >> 6) & 63) | 128);
                    b((charAt & 63) | 128);
                    i3 = i + 1;
                } else {
                    i3 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > '?' || i3 < 56320 || i3 > 57343) {
                        b(63);
                        i++;
                    } else {
                        i3 = ((i3 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        b((i3 >> 18) | 240);
                        b(((i3 >> 12) & 63) | 128);
                        b(((i3 >> 6) & 63) | 128);
                        b((i3 & 63) | 128);
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return this;
        }
    }

    public c a(int i) {
        if (i < 128) {
            b(i);
        } else if (i < 2048) {
            b((i >> 6) | 192);
            b((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                b((i >> 12) | a.fw_);
                b(((i >> 6) & 63) | 128);
                b((i & 63) | 128);
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
        } else if (i <= 1114111) {
            b((i >> 18) | 240);
            b(((i >> 12) & 63) | 128);
            b(((i >> 6) & 63) | 128);
            b((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public c a(String str, Charset charset) {
        return a(str, 0, str.length(), charset);
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(y.a)) {
            return a(str);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return b(bytes, 0, bytes.length);
        }
    }

    public c c(byte[] bArr) {
        if (bArr != null) {
            return b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public c b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        y.a((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            s g = g(1);
            int min = Math.min(i3 - i, 8192 - g.e);
            System.arraycopy(bArr, i, g.c, g.e, min);
            i += min;
            g.e = min + g.e;
        }
        this.c += (long) i2;
        return this;
    }

    public long a(w wVar) throws IOException {
        if (wVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a = wVar.a(this, 8192);
            if (a == -1) {
                return j;
            }
            j += a;
        }
    }

    public d a(w wVar, long j) throws IOException {
        while (j > 0) {
            long a = wVar.a(this, j);
            if (a == -1) {
                throw new EOFException();
            }
            j -= a;
        }
        return this;
    }

    public c b(int i) {
        s g = g(1);
        byte[] bArr = g.c;
        int i2 = g.e;
        g.e = i2 + 1;
        bArr[i2] = (byte) i;
        this.c++;
        return this;
    }

    public c c(int i) {
        s g = g(2);
        byte[] bArr = g.c;
        int i2 = g.e;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        g.e = i2;
        this.c += 2;
        return this;
    }

    public c d(int i) {
        return c(y.a((short) i));
    }

    public c e(int i) {
        s g = g(4);
        byte[] bArr = g.c;
        int i2 = g.e;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        g.e = i2;
        this.c += 4;
        return this;
    }

    public c f(int i) {
        return e(y.a(i));
    }

    public c i(long j) {
        s g = g(8);
        byte[] bArr = g.c;
        int i = g.e;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 40) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 32) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 16) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 8) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) (j & 255));
        g.e = i;
        this.c += 8;
        return this;
    }

    public c j(long j) {
        return i(y.a(j));
    }

    public c k(long j) {
        if (j == 0) {
            return b(48);
        }
        long j2;
        Object obj;
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return a("-9223372036854775808");
            }
            obj = 1;
        } else {
            obj = null;
            j2 = j;
        }
        int i = j2 < 100000000 ? j2 < 10000 ? j2 < 100 ? j2 < 10 ? 1 : 2 : j2 < 1000 ? 3 : 4 : j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8 : j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        if (obj != null) {
            i++;
        }
        s g = g(i);
        byte[] bArr = g.c;
        int i2 = g.e + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = d[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (obj != null) {
            bArr[i2 - 1] = (byte) 45;
        }
        g.e += i;
        this.c = ((long) i) + this.c;
        return this;
    }

    public c l(long j) {
        if (j == 0) {
            return b(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        s g = g(numberOfTrailingZeros);
        byte[] bArr = g.c;
        int i = g.e;
        for (int i2 = (g.e + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = d[(int) (15 & j)];
            j >>>= 4;
        }
        g.e += numberOfTrailingZeros;
        this.c = ((long) numberOfTrailingZeros) + this.c;
        return this;
    }

    s g(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.b == null) {
            this.b = t.a();
            s sVar = this.b;
            s sVar2 = this.b;
            r0 = this.b;
            sVar2.i = r0;
            sVar.h = r0;
            return r0;
        } else {
            r0 = this.b.i;
            if (r0.e + i > 8192 || !r0.g) {
                return r0.a(t.a());
            }
            return r0;
        }
    }

    public void a_(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            y.a(cVar.c, 0, j);
            while (j > 0) {
                s sVar;
                if (j < ((long) (cVar.b.e - cVar.b.d))) {
                    sVar = this.b != null ? this.b.i : null;
                    if (sVar != null && sVar.g) {
                        if ((((long) sVar.e) + j) - ((long) (sVar.f ? 0 : sVar.d)) <= 8192) {
                            cVar.b.a(sVar, (int) j);
                            cVar.c -= j;
                            this.c += j;
                            return;
                        }
                    }
                    cVar.b = cVar.b.a((int) j);
                }
                s sVar2 = cVar.b;
                long j2 = (long) (sVar2.e - sVar2.d);
                cVar.b = sVar2.a();
                if (this.b == null) {
                    this.b = sVar2;
                    sVar2 = this.b;
                    sVar = this.b;
                    s sVar3 = this.b;
                    sVar.i = sVar3;
                    sVar2.h = sVar3;
                } else {
                    this.b.i.a(sVar2).b();
                }
                cVar.c -= j2;
                this.c += j2;
                j -= j2;
            }
        }
    }

    public long a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c == 0) {
            return -1;
        } else {
            if (j > this.c) {
                j = this.c;
            }
            cVar.a_(this, j);
            return j;
        }
    }

    public long a(byte b) {
        return a(b, 0);
    }

    public long a(byte b, long j) {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        s sVar = this.b;
        if (sVar == null) {
            return -1;
        }
        s sVar2;
        if (this.c - j >= j) {
            sVar2 = sVar;
            while (true) {
                long j3 = ((long) (sVar2.e - sVar2.d)) + j2;
                if (j3 >= j) {
                    break;
                }
                sVar2 = sVar2.h;
                j2 = j3;
            }
        } else {
            j2 = this.c;
            sVar2 = sVar;
            while (j2 > j) {
                sVar2 = sVar2.i;
                j2 -= (long) (sVar2.e - sVar2.d);
            }
        }
        while (j2 < this.c) {
            byte[] bArr = sVar2.c;
            int i = sVar2.e;
            for (int i2 = (int) ((((long) sVar2.d) + j) - j2); i2 < i; i2++) {
                if (bArr[i2] == b) {
                    return j2 + ((long) (i2 - sVar2.d));
                }
            }
            j2 += (long) (sVar2.e - sVar2.d);
            sVar2 = sVar2.h;
            j = j2;
        }
        return -1;
    }

    public long b(f fVar) throws IOException {
        return a(fVar, 0);
    }

    public long a(f fVar, long j) throws IOException {
        if (fVar.j() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        } else if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        } else {
            s sVar = this.b;
            if (sVar == null) {
                return -1;
            }
            long j2;
            s sVar2;
            long j3;
            if (this.c - j >= j) {
                j2 = 0;
                sVar2 = sVar;
                while (true) {
                    j3 = ((long) (sVar2.e - sVar2.d)) + j2;
                    if (j3 >= j) {
                        break;
                    }
                    sVar2 = sVar2.h;
                    j2 = j3;
                }
            } else {
                j2 = this.c;
                sVar2 = sVar;
                while (j2 > j) {
                    sVar2 = sVar2.i;
                    j2 -= (long) (sVar2.e - sVar2.d);
                }
            }
            byte b = fVar.b(0);
            int j4 = fVar.j();
            long j5 = (this.c - ((long) j4)) + 1;
            long j6 = j2;
            s sVar3 = sVar2;
            while (j6 < j5) {
                byte[] bArr = sVar3.c;
                int min = (int) Math.min((long) sVar3.e, (((long) sVar3.d) + j5) - j6);
                int i = (int) ((((long) sVar3.d) + j) - j6);
                while (i < min) {
                    if (bArr[i] == b && a(sVar3, i + 1, fVar, 1, j4)) {
                        return ((long) (i - sVar3.d)) + j6;
                    }
                    i++;
                }
                j3 = ((long) (sVar3.e - sVar3.d)) + j6;
                sVar3 = sVar3.h;
                j6 = j3;
                j = j3;
            }
            return -1;
        }
    }

    public long c(f fVar) {
        return b(fVar, 0);
    }

    public long b(f fVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        s sVar = this.b;
        if (sVar == null) {
            return -1;
        }
        long j2;
        s sVar2;
        if (this.c - j >= j) {
            j2 = 0;
            sVar2 = sVar;
            while (true) {
                long j3 = ((long) (sVar2.e - sVar2.d)) + j2;
                if (j3 >= j) {
                    break;
                }
                sVar2 = sVar2.h;
                j2 = j3;
            }
        } else {
            j2 = this.c;
            sVar2 = sVar;
            while (j2 > j) {
                sVar2 = sVar2.i;
                j2 -= (long) (sVar2.e - sVar2.d);
            }
        }
        byte[] bArr;
        int i;
        int i2;
        byte b;
        if (fVar.j() == 2) {
            byte b2 = fVar.b(0);
            byte b3 = fVar.b(1);
            while (j2 < this.c) {
                bArr = sVar2.c;
                i = sVar2.e;
                for (i2 = (int) ((((long) sVar2.d) + j) - j2); i2 < i; i2++) {
                    b = bArr[i2];
                    if (b == b2 || b == b3) {
                        return j2 + ((long) (i2 - sVar2.d));
                    }
                }
                j2 += (long) (sVar2.e - sVar2.d);
                sVar2 = sVar2.h;
                j = j2;
            }
        } else {
            byte[] l = fVar.l();
            while (j2 < this.c) {
                bArr = sVar2.c;
                i2 = (int) ((((long) sVar2.d) + j) - j2);
                i = sVar2.e;
                for (int i3 = i2; i3 < i; i3++) {
                    b = bArr[i3];
                    for (byte b4 : l) {
                        if (b == b4) {
                            return j2 + ((long) (i3 - sVar2.d));
                        }
                    }
                }
                j2 += (long) (sVar2.e - sVar2.d);
                sVar2 = sVar2.h;
                j = j2;
            }
        }
        return -1;
    }

    boolean a(long j, f fVar) {
        int j2 = fVar.j();
        if (this.c - j < ((long) j2)) {
            return false;
        }
        for (int i = 0; i < j2; i++) {
            if (c(((long) i) + j) != fVar.b(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(s sVar, int i, f fVar, int i2, int i3) {
        int i4 = sVar.e;
        byte[] bArr = sVar.c;
        int i5 = i;
        s sVar2 = sVar;
        while (i2 < i3) {
            if (i5 == i4) {
                sVar2 = sVar2.h;
                bArr = sVar2.c;
                i5 = sVar2.d;
                i4 = sVar2.e;
            }
            if (bArr[i5] != fVar.b(i2)) {
                return false;
            }
            i5++;
            i2++;
        }
        return true;
    }

    public void flush() {
    }

    public void close() {
    }

    public x a() {
        return x.b;
    }

    List<Integer> z() {
        if (this.b == null) {
            return Collections.emptyList();
        }
        List<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.b.e - this.b.d));
        for (s sVar = this.b.h; sVar != this.b; sVar = sVar.h) {
            arrayList.add(Integer.valueOf(sVar.e - sVar.d));
        }
        return arrayList;
    }

    public f A() {
        return c("MD5");
    }

    public f B() {
        return c("SHA-1");
    }

    public f C() {
        return c("SHA-256");
    }

    private f c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(this.b.c, this.b.d, this.b.e - this.b.d);
            for (s sVar = this.b.h; sVar != this.b; sVar = sVar.h) {
                instance.update(sVar.c, sVar.d, sVar.e - sVar.d);
            }
            return f.a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.c != cVar.c) {
            return false;
        }
        if (this.c == 0) {
            return true;
        }
        s sVar = this.b;
        s sVar2 = cVar.b;
        int i = sVar.d;
        int i2 = sVar2.d;
        while (j < this.c) {
            long min = (long) Math.min(sVar.e - i, sVar2.e - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = sVar.c[i];
                i = i2 + 1;
                if (b != sVar2.c[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == sVar.e) {
                sVar = sVar.h;
                i = sVar.d;
            }
            if (i2 == sVar2.e) {
                sVar2 = sVar2.h;
                i2 = sVar2.d;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        s sVar = this.b;
        if (sVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = sVar.d;
            while (i2 < sVar.e) {
                int i3 = sVar.c[i2] + (i * 31);
                i2++;
                i = i3;
            }
            sVar = sVar.h;
        } while (sVar != this.b);
        return i;
    }

    public String toString() {
        if (this.c == 0) {
            return "Buffer[size=0]";
        }
        if (this.c <= 16) {
            f s = D().s();
            return String.format("Buffer[size=%s data=%s]", new Object[]{Long.valueOf(this.c), s.g()});
        }
        return String.format("Buffer[size=%s md5=%s]", new Object[]{Long.valueOf(this.c), A().g()});
    }

    public c D() {
        c cVar = new c();
        if (this.c == 0) {
            return cVar;
        }
        cVar.b = new s(this.b);
        s sVar = cVar.b;
        s sVar2 = cVar.b;
        s sVar3 = cVar.b;
        sVar2.i = sVar3;
        sVar.h = sVar3;
        for (sVar = this.b.h; sVar != this.b; sVar = sVar.h) {
            cVar.b.i.a(new s(sVar));
        }
        cVar.c = this.c;
        return cVar;
    }

    public f E() {
        if (this.c <= UpdateOptions.SOURCE_ANY) {
            return h((int) this.c);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.c);
    }

    public f h(int i) {
        if (i == 0) {
            return f.b;
        }
        return new u(this, i);
    }
}
