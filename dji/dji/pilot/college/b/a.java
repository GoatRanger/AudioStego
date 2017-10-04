package dji.pilot.college.b;

import android.content.Context;
import com.dji.frame.c.c;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;

public class a implements b {
    public static void a(Context context, final String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(b.e, new Object[]{str}), new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(65536, z, 0, e$b.a(0, 0, obj, null));
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object a = dji.pilot.college.model.a.a(str);
                dji_pilot_usercenter_protocol_e_a.a(65536, 0, 0, e$b.a(0, 0, str, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(65536, i, 0, e$b.a(0, 0, str, null));
            }
        });
    }
}
