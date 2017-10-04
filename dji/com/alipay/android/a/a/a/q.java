package com.alipay.android.a.a.a;

import com.google.api.client.http.UrlEncodedParser;
import com.loopj.android.http.AsyncHttpClient;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public final class q extends d {
    private n g;

    public q(n nVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, UrlEncodedParser.CONTENT_TYPE, z);
        this.g = nVar;
    }

    public final Object a() {
        Throwable e;
        aa vVar = new v(this.g.a());
        vVar.a(this.b);
        vVar.a(this.e);
        vVar.a(this.f);
        vVar.a("id", String.valueOf(this.d));
        vVar.a("operationType", this.c);
        vVar.a(AsyncHttpClient.ENCODING_GZIP, String.valueOf(this.g.d()));
        vVar.a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> b = this.g.c().b();
        if (!(b == null || b.isEmpty())) {
            for (Header a : b) {
                vVar.a(a);
            }
        }
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(vVar.toString());
        try {
            ab abVar = (ab) this.g.b().a(vVar).get();
            if (abVar != null) {
                return abVar.b();
            }
            throw new c(Integer.valueOf(9), "response is null");
        } catch (Throwable e2) {
            throw new c(Integer.valueOf(13), "", e2);
        } catch (Throwable e22) {
            Throwable th = e22;
            e22 = th.getCause();
            if (e22 == null || !(e22 instanceof a)) {
                throw new c(Integer.valueOf(9), "", th);
            }
            a aVar = (a) e22;
            int a2 = aVar.a();
            switch (a2) {
                case 1:
                    a2 = 2;
                    break;
                case 2:
                    a2 = 3;
                    break;
                case 3:
                    a2 = 4;
                    break;
                case 4:
                    a2 = 5;
                    break;
                case 5:
                    a2 = 6;
                    break;
                case 6:
                    a2 = 7;
                    break;
                case 7:
                    a2 = 8;
                    break;
                case 8:
                    a2 = 15;
                    break;
                case 9:
                    a2 = 16;
                    break;
            }
            throw new c(Integer.valueOf(a2), aVar.b());
        } catch (Throwable e222) {
            throw new c(Integer.valueOf(13), "", e222);
        }
    }
}
