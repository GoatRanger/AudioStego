package dji.midware.a;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class c {
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

    public c(String str, int i) {
        this.b = str;
        this.c = i;
    }

    public void a() {
        new Thread(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                while (true) {
                    try {
                        this.a.a = new Socket(this.a.b, this.a.c);
                        this.a.d = this.a.a.getInputStream();
                        this.a.e = this.a.a.getOutputStream();
                        a.d().a(true, this.a.a.getLocalPort());
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.a.e();
                    }
                    this.a.d();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void b() {
        e();
    }

    private void d() {
        if (this.d != null) {
            try {
                Object obj = new byte[8192];
                Object obj2 = null;
                while (true) {
                    int read = this.d.read(obj);
                    if (read > 0) {
                        Object obj3;
                        Object obj4;
                        if (obj2 != null) {
                            obj3 = new byte[(obj2.length + read)];
                            System.arraycopy(obj2, 0, obj3, 0, obj2.length);
                            System.arraycopy(obj, 0, obj3, obj2.length, read);
                            obj4 = obj3;
                        } else {
                            obj3 = new byte[read];
                            System.arraycopy(obj, 0, obj3, 0, read);
                            obj4 = obj3;
                        }
                        int length = obj4.length;
                        Object obj5 = new byte[30];
                        int i = 0;
                        while (i + 30 <= length) {
                            System.arraycopy(obj4, i, obj5, 0, 30);
                            int i2 = i + 30;
                            d dVar = new d();
                            if (dVar.b(obj5, 30) && dVar.f != 99) {
                                a$a dji_midware_a_a_a = a$a.values()[dVar.g];
                                Log.d("AppEventClient", "EventBus post : " + this.f);
                                if (dji_midware_a_a_a == a$a.Connected) {
                                    this.f = true;
                                } else {
                                    this.f = false;
                                }
                                dji.thirdparty.a.c.a().e(dji_midware_a_a_a);
                                Log.d("AppEventClient", "isConnected : " + this.f);
                            }
                            i = i2;
                        }
                        int i3 = length - i;
                        if (i3 > 0) {
                            obj3 = new byte[i3];
                            System.arraycopy(obj4, i, obj3, 0, i3);
                        } else {
                            obj3 = null;
                        }
                        obj2 = obj3;
                    } else {
                        Thread.sleep(50);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e();
            }
        }
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

    public boolean c() {
        return this.f;
    }
}
