package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public abstract class ad implements ServiceConnection {
    private final Context a;
    private final Handler b;
    private a c;
    private boolean d;
    private Messenger e;
    private int f;
    private int g;
    private final String h;
    private final int i;

    public interface a {
        void a(Bundle bundle);
    }

    protected abstract void a(Bundle bundle);

    public ad(Context context, int i, int i2, int i3, String str) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.a = context;
        this.f = i;
        this.g = i2;
        this.h = str;
        this.i = i3;
        this.b = new Handler(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.a(message);
            }
        };
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    protected Context a() {
        return this.a;
    }

    public boolean b() {
        if (this.d || ab.b(this.i) == -1) {
            return false;
        }
        Intent b = ab.b(this.a);
        if (b == null) {
            return false;
        }
        this.d = true;
        this.a.bindService(b, this, 1);
        return true;
    }

    public void c() {
        this.d = false;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.e = new Messenger(iBinder);
        d();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.e = null;
        try {
            this.a.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
        b(null);
    }

    private void d() {
        Bundle bundle = new Bundle();
        bundle.putString(ab.V, this.h);
        a(bundle);
        Message obtain = Message.obtain(null, this.f);
        obtain.arg1 = this.i;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.b);
        try {
            this.e.send(obtain);
        } catch (RemoteException e) {
            b(null);
        }
    }

    protected void a(Message message) {
        if (message.what == this.g) {
            Bundle data = message.getData();
            if (data.getString(ab.ag) != null) {
                b(null);
            } else {
                b(data);
            }
            this.a.unbindService(this);
        }
    }

    private void b(Bundle bundle) {
        if (this.d) {
            this.d = false;
            a aVar = this.c;
            if (aVar != null) {
                aVar.a(bundle);
            }
        }
    }
}
