package com.flurry.sdk;

import com.flurry.sdk.iv.c;
import java.io.InputStream;
import java.io.OutputStream;

public class iu<RequestObjectType, ResponseObjectType> extends iv {
    private a<RequestObjectType, ResponseObjectType> a;
    private RequestObjectType b;
    private ResponseObjectType c;
    private jh<RequestObjectType> d;
    private jh<ResponseObjectType> e;

    public interface a<RequestObjectType, ResponseObjectType> {
        void a(iu<RequestObjectType, ResponseObjectType> iuVar, ResponseObjectType responseObjectType);
    }

    public void a(RequestObjectType requestObjectType) {
        this.b = requestObjectType;
    }

    public void a(jh<RequestObjectType> jhVar) {
        this.d = jhVar;
    }

    public void b(jh<ResponseObjectType> jhVar) {
        this.e = jhVar;
    }

    public void a(a<RequestObjectType, ResponseObjectType> aVar) {
        this.a = aVar;
    }

    public void a() {
        n();
        super.a();
    }

    private void n() {
        a(new c(this) {
            final /* synthetic */ iu a;

            {
                this.a = r1;
            }

            public void a(iv ivVar, OutputStream outputStream) throws Exception {
                if (this.a.b != null && this.a.d != null) {
                    this.a.d.a(outputStream, this.a.b);
                }
            }

            public void a(iv ivVar, InputStream inputStream) throws Exception {
                if (ivVar.e() && this.a.e != null) {
                    this.a.c = this.a.e.b(inputStream);
                }
            }

            public void a(iv ivVar) {
                this.a.o();
            }
        });
    }

    private void o() {
        if (this.a != null && !c()) {
            this.a.a(this, this.c);
        }
    }
}
