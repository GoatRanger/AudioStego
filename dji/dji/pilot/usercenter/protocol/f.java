package dji.pilot.usercenter.protocol;

import android.content.Context;
import com.dji.frame.c.c;
import dji.thirdparty.afinal.f.a;
import java.util.Locale;

public class f implements e {
    public static void a(Context context, final int i, int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aI, new Object[]{e.aS, Integer.valueOf(i), Integer.valueOf(i2)}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(4096, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(4096, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a(4096, i, 0, null, dji.pilot.usercenter.protocol.a.c.a(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(4096, i, i, null);
            }
        });
    }

    public static void b(Context context, final int i, int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aI, new Object[]{e.aT, Integer.valueOf(i), Integer.valueOf(i2)}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(4097, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(4097, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a(4097, i, 0, null, dji.pilot.usercenter.protocol.a.c.a(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(4097, i, i, null);
            }
        });
    }

    public static void a(Context context, final int i, int i2, String str, final int i3, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aK, new Object[]{"photos", Integer.valueOf(i), Integer.valueOf(i2), str}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(4098, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(4098, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a(4098, i, i3, null, dji.pilot.usercenter.protocol.a.c.c(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(4098, i, i, null);
            }
        });
    }

    public static void c(Context context, final int i, int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aJ, new Object[]{e.aS, Integer.valueOf(i), Integer.valueOf(i2)}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(8192, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(8192, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a(8192, i, 0, null, dji.pilot.usercenter.protocol.a.c.d(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(8192, i, i, null);
            }
        });
    }

    public static void d(Context context, final int i, int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aJ, new Object[]{e.aT, Integer.valueOf(i), Integer.valueOf(i2)}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.an, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.an, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.an, i, 0, null, dji.pilot.usercenter.protocol.a.c.e(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.an, i, i, null);
            }
        });
    }

    public static void b(Context context, final int i, int i2, String str, final int i3, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aK, new Object[]{"videos", Integer.valueOf(i), Integer.valueOf(i2), str}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(8194, z, i, null);
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(8194, j2, j, i, null);
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a(8194, i, i3, null, dji.pilot.usercenter.protocol.a.c.f(str));
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(8194, i, i, null);
            }
        });
    }
}
