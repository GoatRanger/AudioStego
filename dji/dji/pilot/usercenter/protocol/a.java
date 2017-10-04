package dji.pilot.usercenter.protocol;

import android.content.Context;
import com.dji.frame.c.c;
import dji.pilot.usercenter.mode.b;
import java.util.List;
import java.util.Locale;

public class a implements e {
    public static void a(Context context, String str, final int i, final int i2, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aL, new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}), new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(20480, z, 0, e$b.a(i, i2, null, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(20480, j2, j, 0, e$b.a(i, i2, null, null));
            }

            public void a(String str) {
                Object a = dji.pilot.usercenter.protocol.a.a.a(str);
                dji_pilot_usercenter_protocol_e_a.a(20480, 0, 0, e$b.a(i, i2, null, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(20480, i, 0, e$b.a(i, i2, null, null));
            }
        });
    }

    public static void a(Context context, String str, final b bVar, final boolean z, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, e.aM, new Object[]{str, bVar.c, Long.valueOf(bVar.f), String.valueOf(z)}), new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
                int i;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                dji_pilot_usercenter_protocol_e_a.a((int) e.av, z, 0, e$b.a(i, 0, bVar, null));
            }

            public void a(long j, long j2) {
                int i;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                dji_pilot_usercenter_protocol_e_a.a((int) e.av, j2, j, 0, e$b.a(i, 0, bVar, null));
            }

            public void a(String str) {
                int i;
                Object b = dji.pilot.usercenter.protocol.a.a.b(str);
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                dji_pilot_usercenter_protocol_e_a.a((int) e.av, 0, 0, e$b.a(i, 0, bVar, b), b);
            }

            public void a(Throwable th, int i, String str) {
                int i2;
                if (z) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                dji_pilot_usercenter_protocol_e_a.a((int) e.av, i, 0, e$b.a(i2, 0, bVar, null));
            }
        });
    }

    public static void a(Context context, List<b> list, String str, e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).b(String.format(Locale.US, e.aN, new Object[]{str}), new dji.thirdparty.afinal.f.b(), new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }
}
