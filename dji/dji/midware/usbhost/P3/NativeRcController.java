package dji.midware.usbhost.P3;

import android.util.Log;
import android.view.Surface;

public class NativeRcController {
    static boolean a;

    public static native void native_rc_exit();

    public static native void native_rc_init();

    public static native int native_rc_sendto_serial(byte[] bArr, int i);

    public static native int native_rc_setIframe(byte[] bArr, int i);

    public static native int native_rc_setPrdType(int i);

    public static native int native_rc_set_cb_obj(Object obj);

    public static native int native_rc_set_iep(int i, int i2);

    public static native int native_rc_set_sre(int i);

    public static native void native_rc_start_dec(Surface surface);

    public static native int native_rc_stop_dec();

    static {
        a = true;
        try {
            Log.d("NativeRcController", "x try to load libusbdec.so");
            System.loadLibrary("usbdec");
        } catch (UnsatisfiedLinkError e) {
            Log.e("NativeRcController", "Couldn't load libusbdec.so");
            a = false;
        }
    }

    public static void a() {
    }

    public static void b() {
        if (a) {
            native_rc_init();
        }
    }

    public static void c() {
        if (a) {
            native_rc_exit();
        }
    }

    public static void a(Surface surface) {
        if (a) {
            native_rc_start_dec(surface);
        }
    }

    public static void d() {
        if (a) {
            native_rc_stop_dec();
        }
    }

    public static void a(byte[] bArr, int i) {
        if (a) {
            native_rc_sendto_serial(bArr, i);
        }
    }

    public static void b(byte[] bArr, int i) {
        if (a) {
            native_rc_setIframe(bArr, i);
        }
    }

    public static void a(int i) {
        if (a) {
            native_rc_setPrdType(i);
        }
    }

    public static void b(int i) {
        if (a) {
            native_rc_set_sre(i);
        }
    }

    public static void a(int i, int i2) {
        if (a) {
            native_rc_set_iep(i, i2);
        }
    }

    public static void a(Object obj) {
        if (a) {
            native_rc_set_cb_obj(obj);
        }
    }
}
