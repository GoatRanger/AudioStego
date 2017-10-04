package dji.midware.a;

import android.util.Log;
import dji.thirdparty.a.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class b {
    private Socket a;
    private String b;
    private int c;
    private InputStream d;
    private OutputStream e;
    private boolean f = false;

    public enum a {
        Connected,
        DisConnected
    }

    public b(String str, int i) {
        this.b = str;
        this.c = i;
    }

    public void a() {
        c.a().a(this);
        new Thread(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    Log.d("AoaConnect", "client start");
                    this.a.a = new Socket(this.a.b, this.a.c);
                    Log.d("AoaConnect", "client connected");
                    this.a.d = this.a.a.getInputStream();
                    this.a.e = this.a.a.getOutputStream();
                    c.a().e(a.Connected);
                    a.d().a(false, this.a.a.getLocalPort());
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.e();
                }
            }
        }.start();
        f();
    }

    public void b() {
        e();
        c.a().d(this);
    }

    private void e() {
        try {
            if (this.d != null) {
                this.d.close();
                this.d = null;
            }
            if (this.e != null) {
                this.e.close();
                this.e = null;
            }
            if (this.a != null) {
                this.a.close();
                this.a = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        new Thread(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                byte[] bArr = new byte[8192];
                while (true) {
                    if (this.a.f || this.a.d == null) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            this.a.d.read(bArr);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    private void g() {
        if (this.d != null) {
            try {
                byte[] bArr = new byte[8192];
                int read = this.d.read(bArr);
                while (read != -1) {
                    read = this.d.read(bArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
                e();
            }
        }
    }

    public InputStream c() {
        return this.d;
    }

    public OutputStream d() {
        return this.e;
    }

    public void onEventBackgroundThread(a$a dji_midware_a_a_a) {
        if (dji_midware_a_a_a == a$a.Connected) {
            this.f = true;
        } else {
            this.f = false;
        }
    }
}
