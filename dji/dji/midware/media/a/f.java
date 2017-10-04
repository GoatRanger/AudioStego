package dji.midware.media.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.midware.media.a.g.b;
import dji.midware.media.e;
import dji.midware.media.k.a.h;
import dji.midware.media.k.a.i;
import java.util.Vector;

public class f implements h {
    i a;

    public class a implements b {
        public int a;
        final /* synthetic */ f b;

        public a(f fVar) {
            this.b = fVar;
        }

        public void b() {
        }

        public void a(Exception exception) {
        }

        public void a(int i) {
            e.a("File " + this.a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i);
        }

        public void a() {
        }
    }

    public void a() {
    }

    public void a(String str, String str2, i iVar) {
        this.a = iVar;
        try {
            new dji.midware.media.e.f().g(str.replace(".h264", ".info"));
            Vector vector = new Vector();
            dji.midware.media.e.f fVar = new dji.midware.media.e.f();
            fVar.m(0);
            fVar.l(10000);
            fVar = new dji.midware.media.e.f();
            fVar.m(15000);
            fVar.l(25000);
            g.getInstance().a(vector, dji.midware.media.e.e.a(), new b(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a.a();
                }

                public void b() {
                    this.a.a.b();
                }

                public void a(int i) {
                    this.a.a.a(i);
                }

                public void a(Exception exception) {
                    this.a.a.a(exception);
                }
            });
        } catch (Exception e) {
            e.a(e);
            iVar.a(e);
        }
    }

    public void b() {
    }

    public void a(i iVar) {
    }

    public String c() {
        return null;
    }

    public int d() {
        return 0;
    }

    public boolean e() {
        return false;
    }
}
