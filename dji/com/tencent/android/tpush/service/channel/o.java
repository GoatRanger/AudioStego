package com.tencent.android.tpush.service.channel;

import android.support.v4.media.TransportMediator;
import com.f.a.a.f;
import com.f.a.a.g;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.c.d;
import java.util.Random;

public class o {
    private static int e = new Random().nextInt();
    public long a = IPositioningSession.NotSet;
    public long b = IPositioningSession.NotSet;
    public g c = null;
    public p d;
    private int f = 0;
    private short g;

    public o(g gVar, p pVar) {
        this.g = d.a(gVar.getClass());
        this.c = gVar;
        this.d = pVar;
    }

    public o(short s, g gVar, p pVar) {
        this.g = s;
        this.c = gVar;
        this.d = pVar;
    }

    public void a(h hVar) {
        hVar.a(this.g);
        switch (this.g & TransportMediator.KEYCODE_MEDIA_PAUSE) {
            case 7:
                hVar.b((short) 20);
                return;
            default:
                hVar.b((short) 1);
                f fVar = new f();
                fVar.a("UTF-8");
                this.c.writeTo(fVar);
                hVar.a(fVar.b());
                return;
        }
    }

    public boolean a() {
        return (this.g & TransportMediator.KEYCODE_MEDIA_PAUSE) == 7;
    }

    public int b() {
        int i = e + 1;
        e = i;
        this.f = i;
        return this.f;
    }

    public int c() {
        return this.f;
    }

    public String toString() {
        return this.c == null ? "null" : this.c.getClass().getSimpleName() + ":" + this.c + ", " + this.d;
    }
}
