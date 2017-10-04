package dji.midware.a;

import android.util.Log;
import dji.midware.usb.P3.UsbAccessoryService;
import dji.thirdparty.a.c;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class f {
    private int a = -1;
    private ServerSocket b;
    private Map<String, a> c;
    private a d;

    private class a extends Thread {
        public Socket a;
        public InputStream b;
        public OutputStream c;
        public String d;
        public String e;
        final /* synthetic */ f f;

        public a(f fVar, Socket socket, InputStream inputStream, OutputStream outputStream) {
            this.f = fVar;
            this.a = socket;
            this.b = inputStream;
            this.c = outputStream;
            if (socket.getInetAddress() != null && socket.getInetAddress().getAddress() != null && socket.getInetAddress().getAddress().length == 4) {
                byte[] address = socket.getInetAddress().getAddress();
                this.d = String.format("%d.%d.%d.%d:%d", new Object[]{Integer.valueOf(address[0] & 255), Integer.valueOf(address[1] & 255), Integer.valueOf(address[2] & 255), Integer.valueOf(address[3] & 255), Integer.valueOf(socket.getPort())});
            }
        }

        public void run() {
            if (this.b != null) {
                c.a().e(b.Connect);
                try {
                    Object obj = new byte[16384];
                    while (this.b != null) {
                        int read = this.b.read(obj);
                        if (read > 0) {
                            Object obj2 = new byte[read];
                            System.arraycopy(obj, 0, obj2, 0, read);
                            if (this.f.d == this) {
                                UsbAccessoryService.getInstance().sendmessage(obj2);
                            }
                        } else {
                            Thread.sleep(50);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a();
        }

        private void a() {
            Log.d("socketclose", "server close 2: " + System.currentTimeMillis());
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
                c.a().e(b.DisConnect);
                if (this.f.c.containsKey(this.d)) {
                    this.f.c.remove(this.d);
                    if (this.f.d == this) {
                        this.f.d = null;
                    }
                }
                Log.d("AoaConnect", "server: one client disconnected : " + this.d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(byte[] bArr, int i) {
            try {
                this.c.write(bArr, 0, i);
                this.c.flush();
            } catch (Exception e) {
                Log.d("socketclose", "server close : " + System.currentTimeMillis());
                e.printStackTrace();
                a();
            }
        }
    }

    public enum b {
        Connect,
        DisConnect
    }

    public int a() {
        return this.c.size();
    }

    public List<String> b() {
        List<String> arrayList = new ArrayList();
        for (String add : this.c.keySet()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void a(String str) {
        a c = c(str);
        if (c != null && c != this.d) {
            this.d = c;
        }
    }

    public void a(String str, int i) {
        String format = String.format("%s:%d", new Object[]{g.b(), Integer.valueOf(i)});
        if (this.c.containsKey(format)) {
            ((a) this.c.get(format)).e = str;
        }
    }

    public String c() {
        if (this.d != null) {
            return this.d.e;
        }
        return null;
    }

    public boolean b(String str) {
        return c(str) != null;
    }

    public a c(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : this.c.keySet()) {
            a aVar = (a) this.c.get(str2);
            if (str.equals(aVar.e)) {
                return aVar;
            }
        }
        return null;
    }

    public f(int i) {
        this.a = i;
    }

    public void d() {
        new Thread(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        }.start();
    }

    public void e() {
        g();
    }

    private void f() {
        try {
            this.c = new HashMap();
            this.b = new ServerSocket(this.a);
            while (true) {
                Socket accept = this.b.accept();
                Log.d("AoaConnect", "one client connected " + accept.getPort());
                if (accept == null) {
                    break;
                }
                a aVar = new a(this, accept, accept.getInputStream(), accept.getOutputStream());
                this.c.put(aVar.d, aVar);
                aVar.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("AoaConnect", "server exception happen: " + e.getMessage());
        }
        g();
    }

    private void g() {
        h();
        try {
            if (this.b != null) {
                this.b.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void h() {
        String[] strArr = new String[this.c.size()];
        this.c.keySet().toArray(strArr);
        for (Object obj : strArr) {
            ((a) this.c.get(obj)).a();
        }
    }

    public void a(byte[] bArr, int i) {
        if (this.c.size() != 0) {
            if (this.d == null) {
                Iterator it = this.c.keySet().iterator();
                if (it.hasNext()) {
                    this.d = (a) this.c.get((String) it.next());
                }
            }
            if (this.d != null) {
                this.d.a(bArr, i);
            }
        }
    }
}
