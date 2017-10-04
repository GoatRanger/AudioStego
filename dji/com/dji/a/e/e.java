package com.dji.a.e;

import android.os.Handler;
import com.dji.a.a.a;
import com.dji.a.b;
import com.dji.a.f.g;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class e {
    public Handler a = new f(this);
    private final ReentrantLock b = new ReentrantLock();
    private Handler c;
    private int d;
    private b e = null;
    private int f = 0;

    public e(Handler handler, int i, b bVar) {
        this.c = handler;
        this.d = i;
        this.e = bVar;
    }

    private boolean a(HashMap<String, a> hashMap) {
        boolean z = false;
        if (this.e != null) {
            a aVar = new a(this.e.a());
            aVar.a(b.a.LOG_DATA);
            try {
                z = aVar.a(g.a((HashMap) hashMap), this.e);
            } catch (Exception e) {
            }
        }
        return z;
    }
}
