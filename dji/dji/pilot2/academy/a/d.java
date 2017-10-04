package dji.pilot2.academy.a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.protocol.e;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;
import dji.pilot2.academy.model.AcademyVideoInfo;
import dji.pilot2.utils.k;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class d implements e {
    public static final int a = 1000;
    public Map<String, AcademyVideoInfo> b;
    private e$a c;
    private a d;
    private e$a e;
    private boolean f;

    private static final class a extends Handler {
        private final WeakReference<d> a;

        public a(d dVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dVar);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            d dVar = (d) this.a.get();
            if (dVar != null) {
                switch (message.what) {
                    case 1000:
                        dVar.c(1, message.arg1, message.obj);
                        return;
                    case 65536:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler SUCCESS");
                        dVar.a(message.arg1, message.arg2, message.obj);
                        return;
                    case 65537:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler FAIL");
                        dVar.b(message.arg1, message.arg2, message.obj);
                        return;
                    case e.aC /*65538*/:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler START");
                        int i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        dVar.a(i, z, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b {
        private static final d a = new d();

        private b() {
        }
    }

    private d() {
        this.d = new a(this);
        this.f = false;
        this.b = new HashMap();
        this.c = new e$a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.d.obtainMessage(65536, i, 0, obj).sendToTarget();
            }

            public void a(int i, boolean z, int i2, Object obj) {
                this.a.d.obtainMessage(e.aC, i, z ? 1 : 0, obj).sendToTarget();
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.d.obtainMessage(65537, i, i2, obj).sendToTarget();
            }
        };
    }

    public void a(e$a dji_pilot_usercenter_protocol_e_a) {
        this.e = dji_pilot_usercenter_protocol_e_a;
    }

    public void a() {
        this.e = null;
    }

    public void a(Context context, String str) {
        String b = k.b(context, str);
        DJILogHelper.getInstance().LOGI("bob", "getAcademyVideoInfos url = " + b);
        if (this.b.containsKey(b)) {
            AcademyVideoInfo academyVideoInfo = (AcademyVideoInfo) this.b.get(b);
            if (System.currentTimeMillis() - academyVideoInfo.mGetTime < 500) {
                this.d.obtainMessage(1000, 1, 0, academyVideoInfo).sendToTarget();
                return;
            }
        }
        this.f = true;
        dji.pilot2.academy.b.a.a(context, b, null, this.c, 1);
    }

    public boolean b() {
        return this.f;
    }

    private void a(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess cmdId" + i);
        if (obj instanceof e$b) {
            DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess arg instanceof ProtocolResult " + i);
            e$b dji_pilot_usercenter_protocol_e_b = (e$b) obj;
            if (dji_pilot_usercenter_protocol_e_b.d instanceof AcademyVideoInfo) {
                String str = (String) dji_pilot_usercenter_protocol_e_b.c;
                Object obj2 = (AcademyVideoInfo) dji_pilot_usercenter_protocol_e_b.d;
                obj2.mGetTime = System.currentTimeMillis();
                this.b.put(str, obj2);
                if (this.e != null) {
                    this.e.a(i, i2, 0, Integer.valueOf(0), obj2);
                }
            } else {
                DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess not AcademyVideoInfo");
            }
        } else {
            DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess not ProtocolResult");
        }
        this.f = false;
    }

    private void b(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultFail ");
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            if (this.e != null) {
                this.e.a(i, i2, 0, obj);
            }
        }
        this.f = false;
    }

    private void a(int i, boolean z, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultStart ");
        if (obj instanceof e$b) {
            e$b dji_pilot_usercenter_protocol_e_b = (e$b) obj;
            if (this.e != null) {
                this.e.a(i, z, 0, obj);
            }
        }
    }

    private void c(int i, int i2, Object obj) {
        if (this.e != null) {
            this.e.a(i, i2, 0, Integer.valueOf(0), obj);
        }
    }

    public static d getInstance() {
        return b.a;
    }
}
