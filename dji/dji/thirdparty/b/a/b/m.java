package dji.thirdparty.b.a.b;

import dji.thirdparty.b.ab;
import dji.thirdparty.b.u;
import java.net.Proxy.Type;

public final class m {
    private m() {
    }

    static String a(ab abVar, Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(abVar.b());
        stringBuilder.append(' ');
        if (b(abVar, type)) {
            stringBuilder.append(abVar.a());
        } else {
            stringBuilder.append(a(abVar.a()));
        }
        stringBuilder.append(" HTTP/1.1");
        return stringBuilder.toString();
    }

    private static boolean b(ab abVar, Type type) {
        return !abVar.h() && type == Type.HTTP;
    }

    public static String a(u uVar) {
        String l = uVar.l();
        String o = uVar.o();
        return o != null ? l + '?' + o : l;
    }
}
