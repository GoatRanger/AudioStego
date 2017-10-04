package dji.pilot.flyunlimit;

import android.util.Base64;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycUploadUnlimitAreas;
import dji.midware.e.d;
import java.util.concurrent.Semaphore;

class b$8 extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ d b;
    final /* synthetic */ b c;

    b$8(b bVar, String str, d dVar) {
        this.c = bVar;
        this.a = str;
        this.b = dVar;
    }

    public void run() {
        final DataFlycUploadUnlimitAreas instance = DataFlycUploadUnlimitAreas.getInstance();
        final Semaphore semaphore = new Semaphore(0);
        Object decode = Base64.decode(this.a, 0);
        Object obj = new byte[128];
        int length = decode.length % 128;
        int length2 = (decode.length / 128) + (length == 0 ? 1 : 2);
        b.a(this.c, false);
        int i = 0;
        while (i < length2) {
            instance.a(i);
            if (i == length2 - 1) {
                instance.a(null);
            } else if (i != length2 - 2) {
                System.arraycopy(decode, i * 128, obj, 0, 128);
                instance.a(obj);
            } else if (length == 0) {
                System.arraycopy(decode, i * 128, obj, 0, 128);
                instance.a(obj);
            } else {
                Object obj2 = new byte[length];
                System.arraycopy(decode, i * 128, obj2, 0, length);
                instance.a(obj2);
            }
            instance.start(new d(this) {
                final /* synthetic */ b$8 c;

                public void onSuccess(Object obj) {
                    if (instance.a() != 0) {
                        b.a(this.c.c, true);
                    }
                    semaphore.release();
                }

                public void onFailure(a aVar) {
                    semaphore.release();
                    b.a(this.c.c, true);
                }
            });
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!b.c(this.c)) {
                i++;
            } else if (this.b != null) {
                this.b.onFailure(null);
                return;
            } else {
                return;
            }
        }
        if (this.b != null) {
            this.b.onSuccess(null);
            if (b.d(this.c) != null) {
                b.e(this.c).postDelayed(new Runnable(this) {
                    final /* synthetic */ b$8 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (b.d(this.a.c) != null) {
                            b.d(this.a.c).l().G();
                        }
                    }
                }, 500);
            }
        }
    }
}
