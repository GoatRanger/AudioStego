package dji.midware.usbhost.P3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.p;
import dji.thirdparty.a.c;
import java.util.HashMap;

public class DJIUsbReceiver extends BroadcastReceiver {
    public static final String a = "com.dji.host.USB";
    private static final int b = 1351;
    private static final int c = 4098;
    private final String d = getClass().getSimpleName();
    private UsbManager e;
    private UsbDevice f;
    private Context g;
    private UsbDeviceConnection h;
    private UsbEndpoint i;
    private UsbEndpoint j;
    private UsbEndpoint k;
    private UsbInterface l;

    public void a(Context context) {
        this.g = context;
        this.e = (UsbManager) context.getSystemService("usb");
        if (i()) {
            h();
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a(action);
        if (action.equals(a)) {
            if (this.e.hasPermission(this.f)) {
                a("has usbhost permission");
                h();
                return;
            }
            a("no usbhost permission");
        } else if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
            if (i()) {
                h();
            }
        } else if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
            g();
        }
    }

    private void g() {
        if (this.h != null) {
            this.h.releaseInterface(this.l);
            this.h.close();
            this.h = null;
        }
        c.a().e(p.a);
    }

    private void h() {
        if (this.h == null) {
            UsbDeviceConnection openDevice = this.e.openDevice(this.f);
            if (openDevice == null) {
                return;
            }
            if (this.f.getInterfaceCount() <= 0) {
                System.out.println("error ,could't find usb interface !!");
                return;
            }
            a(String.format("UsbRunnable getInterfaceCount %d", new Object[]{Integer.valueOf(this.f.getInterfaceCount())}));
            this.l = this.f.getInterface(0);
            if (openDevice.claimInterface(this.l, true)) {
                this.h = openDevice;
                a(this.l);
                a.getInstance().b();
                c.a().e(p.b);
                return;
            }
            openDevice.close();
        }
    }

    private boolean i() {
        HashMap deviceList = this.e.getDeviceList();
        System.out.println("usbdevice size=" + deviceList.size() + "");
        if (deviceList.size() <= 0) {
            this.f = null;
            return false;
        }
        a("usbdevice size=" + deviceList.size() + "");
        for (UsbDevice usbDevice : deviceList.values()) {
            a("VID=" + usbDevice.getVendorId() + " PID=" + usbDevice.getProductId() + "");
            if (usbDevice.getVendorId() == b && usbDevice.getProductId() == 4098) {
                this.f = usbDevice;
                if (this.e.hasPermission(this.f)) {
                    return true;
                }
                this.e.requestPermission(this.f, PendingIntent.getBroadcast(this.g, 0, new Intent(a), 0));
            }
        }
        return false;
    }

    private void a(UsbInterface usbInterface) {
        int i = 0;
        a(String.format("UsbRunnable getEndpointCount %d", new Object[]{Integer.valueOf(usbInterface.getEndpointCount())}));
        while (i < usbInterface.getEndpointCount()) {
            UsbEndpoint endpoint = usbInterface.getEndpoint(i);
            if (endpoint.getType() == 2) {
                int address = usbInterface.getEndpoint(i).getAddress();
                b("endpoint getAddress=" + address);
                if (address == 134) {
                    this.i = endpoint;
                    a("get vodEndpoint");
                } else if (address == 136) {
                    this.j = endpoint;
                    a("get osdEndpoint");
                } else if (address == 4) {
                    this.k = endpoint;
                    a("get outEndpoint");
                }
            }
            i++;
        }
    }

    protected boolean a() {
        return this.h != null;
    }

    protected synchronized UsbDeviceConnection b() {
        return this.h;
    }

    protected UsbEndpoint c() {
        return this.i;
    }

    protected UsbEndpoint d() {
        return this.j;
    }

    protected UsbEndpoint e() {
        return this.k;
    }

    protected void f() {
        if (this.h != null) {
            this.h.releaseInterface(this.l);
            this.h.close();
            this.h = null;
        }
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGE(this.d, str, false, true);
    }

    private void b(String str) {
        DJILogHelper.getInstance().LOGE(this.d, str, false, false);
    }
}
