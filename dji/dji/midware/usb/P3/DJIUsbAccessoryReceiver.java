package dji.midware.usb.P3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.a.a;
import dji.midware.a.a$a;
import dji.midware.data.manager.P3.p;
import dji.thirdparty.a.c;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DJIUsbAccessoryReceiver extends BroadcastReceiver {
    public static final String ACTION_USB_ACCESSORY_ATTACHED = "com.dji.v3.accessory.USB_ACCESSORY_ATTACHED";
    public static final String a = "com.dji.v3.accessory.USB";
    public static final String b = "T600";
    public static final String c = "DJI";
    private final String d = getClass().getSimpleName();
    private UsbManager e;
    private Context f;
    private UsbAccessory g;
    private ParcelFileDescriptor h;
    private InputStream i;
    private OutputStream j;
    private boolean k = true;
    private Handler l = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            if (b.getInstance().b()) {
                DJIUsbAccessoryReceiver.this.l.sendEmptyMessageDelayed(0, 2000);
            } else {
                if (DJIUsbAccessoryReceiver.this.g == null && DJIUsbAccessoryReceiver.this.k && DJIUsbAccessoryReceiver.this.k()) {
                    DJIUsbAccessoryReceiver.this.j();
                }
                DJIUsbAccessoryReceiver.this.l.sendEmptyMessageDelayed(0, 2000);
            }
            return false;
        }
    });

    public void a(Context context) {
        this.f = context;
        this.e = (UsbManager) context.getSystemService("usb");
        a("start accessory receiver");
        a.d().a(context);
        if (a.d().b()) {
            c.a().a(this);
        } else {
            this.l.sendEmptyMessageDelayed(0, 2000);
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a(action);
        if (b.getInstance().b() && !action.equals("android.hardware.usb.action.USB_ACCESSORY_DETACHED")) {
            a("*****in WifiService connect");
        } else if (action.equals(a)) {
            if (this.e.hasPermission(this.g)) {
                a("has usbAccessory permission");
                j();
                return;
            }
            a("no usbAccessory permission");
        } else if (action.equals(ACTION_USB_ACCESSORY_ATTACHED)) {
            h();
        } else if (action.equals("android.hardware.usb.action.USB_ACCESSORY_ATTACHED")) {
            h();
        } else if (action.equals("android.hardware.usb.action.USB_ACCESSORY_DETACHED")) {
            this.k = true;
            a();
            h();
            if (this.g == null) {
                e();
                this.l.sendEmptyMessageDelayed(0, 1000);
            }
        }
    }

    private synchronized void h() {
        if (this.h == null && k()) {
            j();
        }
    }

    protected void a() {
        b.getInstance().d();
        if (!b.getInstance().b()) {
            f();
            UsbAccessoryService.getInstance().onDisconnect();
            c.a().e(p.a);
            a("receiver disconnected");
        }
    }

    private synchronized void i() {
        try {
            this.l.removeMessages(0);
            this.k = false;
            this.i = a.d().f();
            this.j = a.d().g();
            UsbAccessoryService.getInstance().c();
            c.a().e(p.b);
        } catch (Exception e) {
            a("aoa connect error " + e.getMessage());
        }
    }

    private synchronized void j() {
        try {
            a("openAccessory: " + this.g);
            this.h = this.e.openAccessory(this.g);
            if (this.h != null) {
                this.l.removeMessages(0);
                this.k = false;
                FileDescriptor fileDescriptor = this.h.getFileDescriptor();
                this.i = new FileInputStream(fileDescriptor);
                this.j = new FileOutputStream(fileDescriptor);
                a("mFileDescriptor: mInputStream=" + this.i);
                a("mFileDescriptor: FileDescriptor=" + fileDescriptor.valid());
                UsbAccessoryService.getInstance().c();
                c.a().e(p.b);
            } else {
                a("mFileDescriptor: null");
            }
        } catch (Exception e) {
            a("aoa connect error " + e.getMessage());
        }
    }

    private synchronized boolean k() {
        boolean z = true;
        synchronized (this) {
            if (!a.d().b()) {
                UsbAccessory[] accessoryList = this.e.getAccessoryList();
                if (accessoryList != null && accessoryList.length > 0) {
                    UsbAccessory usbAccessory = accessoryList[0];
                    if (usbAccessory == null) {
                        a("accessory null");
                        z = false;
                    } else {
                        String model = usbAccessory.getModel();
                        String manufacturer = usbAccessory.getManufacturer();
                        a("getModel: " + model);
                        a("getManufacturer: " + manufacturer);
                        if (!b.equals(model) || !c.equals(manufacturer)) {
                            a("不匹配 ");
                        } else if (this.e.hasPermission(usbAccessory)) {
                            this.g = usbAccessory;
                            a("hasPermission ");
                        } else {
                            a("requestPermission ");
                        }
                    }
                }
                z = false;
            }
        }
        return z;
    }

    protected InputStream b() {
        return this.i;
    }

    protected OutputStream c() {
        return this.j;
    }

    protected void d() {
        b("receiver destroy");
        e();
        f();
    }

    protected void e() {
        if (this.l != null) {
            this.l.removeMessages(0);
        }
    }

    protected void f() {
        b.getInstance().d();
        if (!b.getInstance().b()) {
            try {
                if (this.j != null) {
                    if (!a.d().b()) {
                        this.j.close();
                    }
                    this.j = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (this.i != null) {
                    if (!a.d().b()) {
                        this.i.close();
                    }
                    this.i = null;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                if (this.h != null) {
                    this.h.close();
                    this.h = null;
                }
            } catch (IOException e22) {
                e22.printStackTrace();
            }
            this.g = null;
            if (this.l != null) {
                e();
                this.l.sendEmptyMessageDelayed(0, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            }
            b("receiver destroySession");
        }
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGE(this.d, str, true, true);
    }

    private void b(String str) {
        DJILogHelper.getInstance().LOGE(this.d, str, false, false);
    }

    public boolean g() {
        if (a.d().b()) {
            return a.d().k();
        }
        return this.h != null;
    }

    public void onEventBackgroundThread(a$a dji_midware_a_a_a) {
        Log.d("AppEventClient", "event:" + dji_midware_a_a_a);
        if (!a.d().c() || !a.d().b()) {
            return;
        }
        if (dji_midware_a_a_a == a$a.Connected) {
            i();
            Log.d("AppEventClient", "event: connectedToAoaBright");
            return;
        }
        a();
        Log.d("AppEventClient", "event: disconnected");
    }
}
