package dji.midware.data.manager.P3;

import dji.midware.data.a.a.b;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.p;

public class a {
    public static b synSendCmd(c cVar) {
        b a;
        if (ServiceManager.getInstance().isConnected()) {
            a = a(ServiceManager.getInstance().b(cVar.m).a(cVar), cVar, cVar.v);
            if (a != null) {
                return a;
            }
            cVar.w--;
            if (cVar.w > 0) {
                return synSendCmd(cVar);
            }
            a = new b(null);
            a.o = dji.midware.data.config.P3.a.TIMEOUT.a();
            return a;
        }
        a = new b(null);
        a.o = dji.midware.data.config.P3.a.NOCONNECT.a();
        return a;
    }

    public static void asynSendCmd(c cVar) {
        if (cVar.m == p.GIMBAL.a()) {
            ServiceManager.getInstance().sendmessage(cVar.r);
        } else {
            ServiceManager.getInstance().sendmessage(cVar.r);
        }
    }

    private static b a(dji.midware.data.b.a.c.a aVar, c cVar, int i) {
        synchronized (aVar) {
            try {
                ServiceManager.getInstance().sendmessage(cVar.r);
                aVar.wait((long) i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (aVar.a) {
            return (b) aVar.b;
        }
        return null;
    }
}
