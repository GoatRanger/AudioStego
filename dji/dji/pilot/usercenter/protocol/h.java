package dji.pilot.usercenter.protocol;

import android.content.Context;
import com.dji.frame.c.c;
import dji.pilot.usercenter.protocol.a.e;
import dji.thirdparty.afinal.f.a;

public class h implements e {
    public static void a(Context context, final int i, final int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(null, new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ap, z, 0, e$b.a(i, i2, null, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ap, j2, j, 0, e$b.a(i, i2, null, null));
            }

            public void a(String str) {
                Object a = e.a(str);
                dji_pilot_usercenter_protocol_e_a.a((int) e.ap, 0, 0, e$b.a(i, i2, null, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ap, i, 0, e$b.a(i, i2, null, null));
            }
        });
    }

    public static void a(Context context, final int i, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(null, new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.aq, z, 0, e$b.a(i, 0, null, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.aq, j2, j, 0, e$b.a(i, 0, null, null));
            }

            public void a(String str) {
                Object b = e.b(str);
                dji_pilot_usercenter_protocol_e_a.a((int) e.aq, 0, 0, e$b.a(i, 0, null, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.aq, i, 0, e$b.a(i, 0, null, null));
            }
        });
    }

    public static void b(Context context, final int i, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(null, new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ar, z, 0, e$b.a(i, 0, null, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ar, j2, j, 0, e$b.a(i, 0, null, null));
            }

            public void a(String str) {
                Object c = e.c(str);
                dji_pilot_usercenter_protocol_e_a.a((int) e.ar, 0, 0, e$b.a(i, 0, null, c), c);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.ar, i, 0, e$b.a(i, 0, null, null));
            }
        });
    }

    public static void c(Context context, final int i, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(null, new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.as, z, 0, e$b.a(i, 0, null, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.as, j2, j, 0, e$b.a(i, 0, null, null));
            }

            public void a(String str) {
                Object c = e.c(str);
                dji_pilot_usercenter_protocol_e_a.a((int) e.as, 0, 0, e$b.a(i, 0, null, c), c);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) e.as, i, 0, e$b.a(i, 0, null, null));
            }
        });
    }
}
