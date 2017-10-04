package dji.thirdparty.b.a;

import dji.thirdparty.c.h;
import dji.thirdparty.c.v;
import java.io.IOException;

class c extends h {
    private boolean a;

    public c(v vVar) {
        super(vVar);
    }

    public void a_(dji.thirdparty.c.c cVar, long j) throws IOException {
        if (this.a) {
            cVar.h(j);
            return;
        }
        try {
            super.a_(cVar, j);
        } catch (IOException e) {
            this.a = true;
            a(e);
        }
    }

    public void flush() throws IOException {
        if (!this.a) {
            try {
                super.flush();
            } catch (IOException e) {
                this.a = true;
                a(e);
            }
        }
    }

    public void close() throws IOException {
        if (!this.a) {
            try {
                super.close();
            } catch (IOException e) {
                this.a = true;
                a(e);
            }
        }
    }

    protected void a(IOException iOException) {
    }
}
