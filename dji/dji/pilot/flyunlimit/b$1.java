package dji.pilot.flyunlimit;

import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.pilot.flyunlimit.b.c;
import dji.pilot.flyunlimit.jsonbean.DJIFlyUnlimitArea;
import dji.thirdparty.afinal.f.a;
import dji.thirdparty.gson.reflect.TypeToken;
import java.util.List;

class b$1 extends a<String> {
    final /* synthetic */ c a;
    final /* synthetic */ b b;

    b$1(b bVar, c cVar) {
        this.b = bVar;
        this.a = cVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        if (!l.a(str)) {
            b.a(this.b, h.a(str, new TypeToken<List<DJIFlyUnlimitArea>>(this) {
                final /* synthetic */ b$1 a;

                {
                    this.a = r1;
                }
            }));
            if (this.a != null) {
                this.a.a(true);
            }
        } else if (this.a != null) {
            this.a.a(false);
        }
    }

    public void a(Throwable th, int i, String str) {
        if (this.a != null) {
            this.a.a(false);
        }
    }
}
