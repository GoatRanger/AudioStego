package dji.thirdparty.b.a.b;

import dji.thirdparty.b.ad;
import dji.thirdparty.b.z;
import java.io.IOException;
import java.net.ProtocolException;

public final class q {
    public static final int a = 307;
    public static final int b = 308;
    public static final int c = 100;
    public final z d;
    public final int e;
    public final String f;

    public q(z zVar, int i, String str) {
        this.d = zVar;
        this.e = i;
        this.f = str;
    }

    public static q a(ad adVar) {
        return new q(adVar.b(), adVar.c(), adVar.e());
    }

    public static q a(String str) throws IOException {
        z zVar;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int charAt = str.charAt(7) - 48;
            if (charAt == 0) {
                zVar = z.HTTP_1_0;
            } else if (charAt == 1) {
                zVar = z.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            zVar = z.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        if (str.length() < i + 3) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            String str2;
            int parseInt = Integer.parseInt(str.substring(i, i + 3));
            String str3 = "";
            if (str.length() <= i + 3) {
                str2 = str3;
            } else if (str.charAt(i + 3) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            } else {
                str2 = str.substring(i + 4);
            }
            return new q(zVar, parseInt, str2);
        } catch (NumberFormatException e) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d == z.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        stringBuilder.append(' ').append(this.e);
        if (this.f != null) {
            stringBuilder.append(' ').append(this.f);
        }
        return stringBuilder.toString();
    }
}
