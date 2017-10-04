package dji.midware.util.a;

import android.content.Context;
import android.os.SystemClock;
import dji.midware.media.e;
import dji.midware.stat.StatService;
import dji.midware.util.c;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class d {
    public static String a = "TestUtil";
    public static String b = "GPIO_TAG";
    public static boolean c = false;
    public static boolean d = false;
    public static final boolean e = false;
    private static int f = 0;
    private static DataOutputStream g = null;
    private static DataInputStream h = null;
    private static int i = 0;

    public static void a() {
        StatService.OPEN = true;
    }

    public static void b() {
        a();
        b.a = true;
    }

    public static void c() {
    }

    public static void d() {
    }

    public static void a(Context context) {
    }

    public static void a(byte[] bArr) {
        int parseInt;
        Exception e;
        if (f == 0) {
            e.d(b, "testing GPIO");
        }
        f++;
        if (f % 100 == 0) {
            i = 1;
        } else {
            i = 0;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            g = new DataOutputStream(new FileOutputStream("/sys/dwc3_test/test_gpio"));
            g.writeBytes(i + "");
            g.close();
        } catch (Exception e2) {
            e.a(b, e2);
        }
        try {
            h = new DataInputStream(new FileInputStream("/sys/dwc3_test/test_gpio"));
            try {
                parseInt = Integer.parseInt(h.readLine());
            } catch (Exception e22) {
                e.a(b, e22);
                parseInt = -1;
            }
            try {
                h.close();
            } catch (Exception e3) {
                e = e3;
                e.a(b, e);
                if (parseInt != i) {
                    e.d(b, "test_gpio=" + parseInt + " frame_count = " + f + " content=" + c.i(Arrays.copyOf(bArr, 18)) + " duration=" + (SystemClock.uptimeMillis() - uptimeMillis));
                }
                e.b(b, String.format("test_gpio_r %d !=test_gpio_w %d. frame_count=%d", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(i), Integer.valueOf(f)}));
                return;
            }
        } catch (Exception e222) {
            Exception exception = e222;
            parseInt = -1;
            e = exception;
            e.a(b, e);
            if (parseInt != i) {
                e.b(b, String.format("test_gpio_r %d !=test_gpio_w %d. frame_count=%d", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(i), Integer.valueOf(f)}));
                return;
            }
            e.d(b, "test_gpio=" + parseInt + " frame_count = " + f + " content=" + c.i(Arrays.copyOf(bArr, 18)) + " duration=" + (SystemClock.uptimeMillis() - uptimeMillis));
        }
        if (parseInt != i) {
            e.b(b, String.format("test_gpio_r %d !=test_gpio_w %d. frame_count=%d", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(i), Integer.valueOf(f)}));
            return;
        }
        e.d(b, "test_gpio=" + parseInt + " frame_count = " + f + " content=" + c.i(Arrays.copyOf(bArr, 18)) + " duration=" + (SystemClock.uptimeMillis() - uptimeMillis));
    }

    public static void a(String str, byte[] bArr, int i) {
        c.getInstance(str).a(bArr, 0, i);
    }
}
