package dji.midware.b;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import dji.log.DJILogHelper;
import dji.midware.b.a.d;
import dji.midware.b.a.e;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.k;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdSetLED;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

public class c implements d, k {
    private static final String b = "BluetoothLeService";
    private static final int c = 10;
    private static c d = null;
    private static final String i = "android.bluetooth.adapter.action.STATE_CHANGED";
    private static Context j;
    final DataOsdSetLED a;
    private g e = g.getInstance();
    private a f = null;
    private Timer g;
    private a h;
    private boolean k = true;

    private class a extends BroadcastReceiver {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            if (intExtra == 10) {
                DJILogHelper.getInstance().LOGD(c.b, "onReceive:STATE_OFF", false, true);
                dji.thirdparty.a.c.a().e(b.OFF);
                this.a.l();
            } else if (intExtra == 12) {
                DJILogHelper.getInstance().LOGD(c.b, "onReceive:STATE_ON", false, true);
                dji.thirdparty.a.c.a().e(b.ON);
                this.a.k();
            }
        }
    }

    public enum b {
        ON,
        OFF
    }

    private void j() {
        this.h = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(i);
        j.registerReceiver(this.h, intentFilter);
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c();
            }
            cVar = d;
        }
        return cVar;
    }

    public static void a(Context context) {
        if (VERSION.SDK_INT >= 19) {
            j = context;
            a.a(context);
        }
    }

    private c() {
        if (VERSION.SDK_INT >= 19) {
            this.f = a.getInstance();
        }
        this.a = new DataOsdSetLED();
    }

    private void k() {
        a(10);
    }

    private void l() {
        if (!this.f.a()) {
            onDisconnect();
            destroy();
        }
    }

    public void a(int i) {
        if (VERSION.SDK_INT >= 19) {
            j();
            this.f.a((d) this);
            if (this.f.a()) {
                this.f.a(i);
            }
        }
    }

    public void a() {
        this.f.a(new e(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onScanResultUpdate(ArrayList<b> arrayList) {
                if (!this.a.isConnected() && !arrayList.isEmpty()) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        b bVar = (b) it.next();
                        if (this.a.f.i().equals(bVar.a)) {
                            this.a.a(bVar.a);
                            return;
                        }
                    }
                }
            }
        });
        this.f.a(10);
    }

    public boolean b() {
        return this.f.b();
    }

    public boolean c() {
        return this.f.a();
    }

    public void d() {
        this.f.d();
    }

    public void b(int i) {
        if (this.f.a()) {
            this.f.a(i);
        } else {
            b();
        }
    }

    public void a(String str) {
        this.f.b(str);
    }

    public void a(String str, dji.midware.b.a.b bVar) {
        this.f.a(str, bVar);
    }

    public void e() {
        this.f.l();
    }

    public void a(dji.midware.b.a.b bVar) {
        this.f.a(bVar);
    }

    public a f() {
        return this.f;
    }

    public void sendmessage(byte[] bArr) {
        if (this.k) {
            try {
                Thread.sleep(1500);
                this.k = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        int length = bArr.length;
        while (length > 0) {
            int i2;
            if (length >= 20) {
                i2 = 20;
            } else {
                i2 = length;
            }
            this.f.b(dji.midware.util.c.e(bArr, i, i2));
            length -= i2;
            i = i2 + i;
        }
    }

    public void a(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 2) {
            onConnect();
        } else if (i2 == 0) {
            onDisconnect();
        }
    }

    @TargetApi(18)
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        this.e.parse(value, 0, value.length);
    }

    public void g() {
        this.f.q();
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public boolean isConnected() {
        return this.f.m();
    }

    public void onDisconnect() {
        this.k = true;
        dji.midware.f.a.getInstance().a(dji.midware.f.b.f);
        dji.thirdparty.a.c.a().e(p.a);
    }

    public void onConnect() {
        this.k = true;
        dji.logic.a.a.getInstance().a();
        dji.logic.a.a.getInstance().c();
        dji.midware.f.a.getInstance().a(dji.midware.f.b.g);
        dji.thirdparty.a.c.a().e(p.b);
    }

    public void destroy() {
        if (this.f != null) {
            this.f.o();
        }
    }

    public static void h() {
        if (d != null) {
            d.destroy();
        }
    }

    public boolean isOK() {
        if (this.f != null) {
            return this.f.m();
        }
        return false;
    }

    public boolean isRemoteOK() {
        return isConnected();
    }

    public boolean i() {
        return this.f.i().equals(this.f.j());
    }

    public void setDataMode(boolean z) {
    }

    public void pauseRecvThread() {
    }

    public void resumeRecvThread() {
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }

    public void pauseService(boolean z) {
    }

    public void a(boolean z) {
        this.f.a(z);
    }
}
