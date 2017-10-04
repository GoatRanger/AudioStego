package dji.pilot.publics.control.upgrade;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import dji.midware.data.forbid.DJIFlyForbidController;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import java.io.File;

public class c {
    private a a;

    public interface b {
        void a(d dVar);

        void a(d dVar, int i);

        void a(d dVar, int i, String str);

        void b(d dVar);
    }

    private class a extends Handler {
        final /* synthetic */ c a;

        public a(c cVar, Looper looper) {
            this.a = cVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (message.obj != null && message.obj.getClass() == d.class) {
                        this.a.b((d) message.obj);
                        return;
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    public class c implements FTPDataTransferListener {
        final /* synthetic */ c a;
        private d b;
        private long c;

        public c(c cVar, d dVar) {
            this.a = cVar;
            this.b = dVar;
        }

        public void aborted() {
            if (this.b != null || this.b.l != null) {
                this.b.j = dji.pilot.publics.control.upgrade.d.a.FAILS;
                this.b.l.a(this.b, 0, "ftp upload fails");
            }
        }

        public void completed() {
            if (this.b != null || this.b.l != null) {
                this.b.j = dji.pilot.publics.control.upgrade.d.a.SUCCESS;
                this.b.l.a(this.b, 0);
            }
        }

        public void failed() {
            if (this.b != null || this.b.l != null) {
                this.b.j = dji.pilot.publics.control.upgrade.d.a.FAILS;
                this.b.l.a(this.b, 0, "ftp upload fails");
            }
        }

        public void started() {
            this.c = System.currentTimeMillis();
            if (this.b != null || this.b.l != null) {
                this.b.j = dji.pilot.publics.control.upgrade.d.a.START;
                this.b.l.a(this.b);
            }
        }

        public void transferred(int i) {
            if (this.b.j != null) {
                this.b.j = dji.pilot.publics.control.upgrade.d.a.UPLOADING;
                d dVar = this.b;
                dVar.k += (long) i;
                if (this.b.l != null && System.currentTimeMillis() - this.c > 800) {
                    this.b.l.b(this.b);
                    this.c = System.currentTimeMillis();
                }
            }
        }
    }

    private static final class d {
        private static final c a = new c();

        private d() {
        }
    }

    public static void a() {
        FTPClient fTPClient = new FTPClient();
        try {
            fTPClient.connect("127.0.0.1");
            fTPClient.login("as", "asas");
            fTPClient.upload(new File("E:\\test.txt"));
            fTPClient.disconnect(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static c getInstance() {
        return d.a;
    }

    private c() {
        HandlerThread handlerThread = new HandlerThread("ftp_upload_manager");
        handlerThread.start();
        this.a = new a(this, handlerThread.getLooper());
    }

    public static boolean a(String str, String str2, String str3) {
        boolean z = true;
        if (dji.pilot.publics.e.d.a(str)) {
            return false;
        }
        FTPClient fTPClient = new FTPClient();
        try {
            fTPClient.connect(str);
            if (dji.pilot.publics.e.d.a(str2) || dji.pilot.publics.e.d.a(str3)) {
                fTPClient.login("anonymous", DJIFlyForbidController.DJI_DATA_SOURCE);
            } else {
                fTPClient.login(str2, str3);
            }
            fTPClient.disconnect(true);
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public void a(d dVar) {
        this.a.sendMessage(this.a.obtainMessage(0, dVar));
    }

    private void b(d dVar) {
        String str;
        dVar.j = dji.pilot.publics.control.upgrade.d.a.NONE;
        FTPClient fTPClient = new FTPClient();
        String str2 = null;
        if (dVar.a.contains(dji.pilot.usercenter.protocol.d.t)) {
            String[] split = dVar.a.split(dji.pilot.usercenter.protocol.d.t);
            str = split[0];
            str2 = split[1];
        } else {
            str = dVar.a;
        }
        try {
            fTPClient.connect(str);
            if (dji.pilot.publics.e.d.a(dVar.g) || dji.pilot.publics.e.d.a(dVar.h)) {
                fTPClient.login("anonymous", DJIFlyForbidController.DJI_DATA_SOURCE);
            } else {
                fTPClient.login(dVar.g, dVar.h);
            }
            if (str2 != null) {
                fTPClient.changeDirectory(str2);
            }
            fTPClient.upload(new File(dVar.c), new c(this, dVar));
            fTPClient.disconnect(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dVar.j != dji.pilot.publics.control.upgrade.d.a.SUCCESS && dVar.j != dji.pilot.publics.control.upgrade.d.a.FAILS) {
            dVar.j = dji.pilot.publics.control.upgrade.d.a.FAILS;
            if (dVar.l != null) {
                dVar.j = dji.pilot.publics.control.upgrade.d.a.FAILS;
                dVar.l.a(dVar, 0, "upload exception happen");
            }
        }
    }
}
