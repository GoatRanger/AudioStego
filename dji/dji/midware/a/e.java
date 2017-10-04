package dji.midware.a;

import dji.midware.a.d.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.thirdparty.a.c;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class e {
    private p a = p.a;
    private int b = -1;
    private ServerSocket c;
    private Map<String, a> d;
    private a e;

    private class a extends Thread {
        public Socket a;
        public InputStream b;
        public OutputStream c;
        public String d;
        public String e;
        final /* synthetic */ e f;

        public a(e eVar, Socket socket, InputStream inputStream, OutputStream outputStream) {
            this.f = eVar;
            this.a = socket;
            this.b = inputStream;
            this.c = outputStream;
            if (socket.getInetAddress() != null && socket.getInetAddress().getAddress() != null && socket.getInetAddress().getAddress().length == 4) {
                byte[] address = socket.getInetAddress().getAddress();
                this.d = String.format("%d.%d.%d.%d:%d", new Object[]{Integer.valueOf(address[0] & 255), Integer.valueOf(address[1] & 255), Integer.valueOf(address[2] & 255), Integer.valueOf(address[3] & 255), Integer.valueOf(socket.getPort())});
            }
        }

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f.c();
            d dVar = new d();
            dVar.a(99, b.DJIBaseCommData_Who.ordinal());
            dVar.a(0, b.DJIBaseCommData_Event.ordinal());
            byte[] b = dVar.b();
            while (a(b, b.length)) {
                try {
                    Thread.sleep(500);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            a();
        }

        private void a() {
            try {
                if (this.c != null) {
                    this.c.close();
                    this.c = null;
                }
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
                if (this.a != null) {
                    this.a.close();
                    this.a = null;
                }
                if (this.f.d.containsKey(this.d)) {
                    this.f.d.remove(this.d);
                    if (this.f.e == this) {
                        this.f.e = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean a(byte[] bArr, int i) {
            try {
                this.c.write(bArr, 0, i);
                this.c.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                a();
                return false;
            }
        }
    }

    public e(int i) {
        this.b = i;
    }

    public void a() {
        new Thread(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        }.start();
    }

    public void b() {
        e();
        c.a().d(this);
    }

    public void a(String str) {
        a b = b(str);
        if (b != null && b != this.e) {
            if (this.e != null && ServiceManager.getInstance().isConnected()) {
                a(a$a.DisConnected);
            }
            this.e = b;
            if (ServiceManager.getInstance().isConnected()) {
                a(a$a.Connected);
            }
        }
    }

    private void c() {
        a$a dji_midware_a_a_a;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ServiceManager.getInstance().isConnected();
        if (ServiceManager.getInstance().isConnected()) {
            dji_midware_a_a_a = a$a.Connected;
        } else {
            dji_midware_a_a_a = a$a.DisConnected;
        }
        a(dji_midware_a_a_a);
    }

    public a b(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : this.d.keySet()) {
            a aVar = (a) this.d.get(str2);
            if (str.equals(aVar.e)) {
                return aVar;
            }
        }
        return null;
    }

    private void a(a$a dji_midware_a_a_a) {
        d dVar = new d();
        dVar.a(5, b.DJIBaseCommData_Who.ordinal());
        dVar.a(dji_midware_a_a_a.ordinal(), b.DJIBaseCommData_Event.ordinal());
        byte[] b = dVar.b();
        a(b, b.length);
    }

    private void d() {
        c.a().a(this);
        try {
            this.d = new HashMap();
            this.c = new ServerSocket(this.b);
            while (true) {
                Socket accept = this.c.accept();
                if (accept != null) {
                    a aVar = new a(this, accept, accept.getInputStream(), accept.getOutputStream());
                    this.d.put(aVar.d, aVar);
                    aVar.start();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        f();
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        String[] strArr = new String[this.d.size()];
        this.d.keySet().toArray(strArr);
        for (Object obj : strArr) {
            ((a) this.d.get(obj)).a();
        }
    }

    private void a(byte[] bArr, int i) {
        if (this.d.size() != 0) {
            if (this.e == null) {
                Iterator it = this.d.keySet().iterator();
                if (it.hasNext()) {
                    this.e = (a) this.d.get((String) it.next());
                }
            }
            this.e.a(bArr, i);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        c();
    }

    public void a(String str, int i) {
        String format = String.format("%s:%d", new Object[]{g.b(), Integer.valueOf(i)});
        if (this.d.containsKey(format)) {
            ((a) this.d.get(format)).e = str;
        }
    }

    public boolean c(String str) {
        return b(str) != null;
    }
}
