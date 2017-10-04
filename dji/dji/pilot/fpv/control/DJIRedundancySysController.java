package dji.pilot.fpv.control;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycPushRedundancyStatus;
import dji.midware.data.model.P3.DataFlycPushRedundancyStatus.RedundancySwitchInfo;
import dji.midware.data.model.P3.DataFlycRedundancyStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.COLOR_STATUS;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.RS_CMD_TYPE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.publics.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DJIRedundancySysController implements dji.midware.data.params.P3.a {
    public static RedundancyErrorCodeDesc E = null;
    private static DJIRedundancySysController F = null;
    private static final String[] P = new String[]{dji.midware.data.params.P3.a.h};
    private static final int Q = 4096;
    public static boolean a = false;
    private Context G = null;
    private Thread H = null;
    private d I = new d();
    private List<RedundancySwitchInfo> J = new ArrayList();
    private List<IMUStatus> K = new ArrayList();
    private List<IMUStatus> L = new ArrayList();
    private List<IMUStatus> M = new ArrayList();
    private boolean[] N = new boolean[3];
    private volatile int O = 0;
    private Handler R = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ DJIRedundancySysController a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    this.a.l();
                    break;
            }
            return false;
        }
    });
    private int S = 0;
    private volatile int T = Integer.MIN_VALUE;
    private boolean U = false;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[COLOR_STATUS.values().length];

        static {
            try {
                a[COLOR_STATUS.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[COLOR_STATUS.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[COLOR_STATUS.e.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public class RedundancyErrorCodeDesc {
        public List<DevType> devices;
        public List<List<String>> en_2_ch;

        public class DevType {
            public List<Element> element;
            public int id;
            public String name = "";
            public int version;

            public String getName() {
                return this.name;
            }
        }

        public class Element {
            public int code;
            public String detail_ch_tips = "";
            public int free_repair;
            public String ground_action = "";
            public int history_enable;
            public String in_air_used_action = "";
            public String usr_err_tips = "";
            public int usr_show_enable;

            public String getUserErrTips() {
                return this.usr_err_tips;
            }

            public String getGroundAction() {
                return this.ground_action;
            }

            public String getAirAction() {
                return this.in_air_used_action;
            }
        }
    }

    private static final class a implements Comparator<IMUStatus> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((IMUStatus) obj, (IMUStatus) obj2);
        }

        public int a(IMUStatus iMUStatus, IMUStatus iMUStatus2) {
            if (iMUStatus.time > iMUStatus2.time) {
                return -1;
            }
            if (iMUStatus.time < iMUStatus2.time) {
                return 1;
            }
            return 0;
        }
    }

    private static final class b implements Comparator<RedundancySwitchInfo> {
        private b() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((RedundancySwitchInfo) obj, (RedundancySwitchInfo) obj2);
        }

        public int a(RedundancySwitchInfo redundancySwitchInfo, RedundancySwitchInfo redundancySwitchInfo2) {
            if (redundancySwitchInfo.time > redundancySwitchInfo2.time) {
                return -1;
            }
            if (redundancySwitchInfo.time < redundancySwitchInfo2.time) {
                return 1;
            }
            return 0;
        }
    }

    public static class c {
        public int a;
        public String b = "";
        public int c;
        public Element d;
    }

    public static class d {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
    }

    public static DJIRedundancySysController getInstance(Context context) {
        if (F == null) {
            F = new DJIRedundancySysController(context);
        }
        return F;
    }

    private DJIRedundancySysController(Context context) {
        this.G = context;
        this.J = com.dji.frame.c.c.c(this.G.getApplicationContext()).c(RedundancySwitchInfo.class);
        if (this.J == null) {
            this.J = new ArrayList();
        } else {
            Collections.sort(this.J, new b());
            while (this.J.size() > 20) {
                try {
                    com.dji.frame.c.c.c(this.G.getApplicationContext()).f((RedundancySwitchInfo) this.J.remove(this.J.size() - 1));
                } catch (Exception e) {
                }
            }
        }
        List<IMUStatus> c = com.dji.frame.c.c.c(this.G.getApplicationContext()).c(IMUStatus.class);
        if (!(c == null || c.isEmpty())) {
            Collections.sort(c, new a());
            for (IMUStatus iMUStatus : c) {
                if (iMUStatus.imuIndex == 0) {
                    if (this.K.size() < 20) {
                        this.K.add(iMUStatus);
                    } else {
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).f(iMUStatus);
                    }
                } else if (iMUStatus.imuIndex == 1) {
                    if (this.L.size() < 20) {
                        this.L.add(iMUStatus);
                    } else {
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).f(iMUStatus);
                    }
                } else if (iMUStatus.imuIndex == 2) {
                    if (this.M.size() < 20) {
                        this.M.add(iMUStatus);
                    } else {
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).f(iMUStatus);
                    }
                }
            }
        }
        dji.thirdparty.a.c.a().a(this);
        this.H = new Thread(this) {
            final /* synthetic */ DJIRedundancySysController a;
            private int b = 0;
            private boolean c = false;
            private int d = -1;
            private int e = -1;

            {
                this.a = r3;
            }

            public void run() {
                while (true) {
                    if (dji.pilot.publics.e.a.f()) {
                        int i = this.b;
                        this.b = i + 1;
                        if (i % 2 == 0) {
                            new DataFlycGetParams().setInfos(new String[]{"g_status.ns_busy_dev_0"}).start(new dji.midware.e.d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    boolean z;
                                    boolean z2 = true;
                                    int intValue = dji.midware.data.manager.P3.d.read("g_status.ns_busy_dev_0").value.intValue();
                                    this.a.a.I.a = intValue & 3;
                                    this.a.a.I.b = (intValue >> 2) & 3;
                                    this.a.a.I.c = (intValue >> 4) & 3;
                                    this.a.a.I.d = (intValue >> 6) & 3;
                                    this.a.a.I.e = (intValue >> 8) & 3;
                                    this.a.a.I.f = (intValue >> 10) & 3;
                                    this.a.a.I.g = ((intValue >> 12) & 1) == 1;
                                    d a = this.a.a.I;
                                    if (((intValue >> 13) & 1) == 1) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    a.h = z;
                                    a = this.a.a.I;
                                    if (((intValue >> 14) & 1) == 1) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    a.i = z;
                                    d a2 = this.a.a.I;
                                    if (((intValue >> 15) & 1) != 1) {
                                        z2 = false;
                                    }
                                    a2.j = z2;
                                    dji.thirdparty.a.c.a().e(this.a.a.I);
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                        } else {
                            final DataFlycRedundancyStatus instance = DataFlycRedundancyStatus.getInstance();
                            instance.a(RS_CMD_TYPE.b).start(new dji.midware.e.d(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void onSuccess(Object obj) {
                                    dji.thirdparty.a.c.a().e(instance);
                                    if (instance.a() == RS_CMD_TYPE.b) {
                                        for (IMUStatus iMUStatus : instance.c()) {
                                            if (COLOR_STATUS.ofValue(iMUStatus.colorStatus) == COLOR_STATUS.g && iMUStatus.isRealInAir) {
                                                c a = DJIRedundancySysController.a(this.b.a.G, iMUStatus);
                                                if (!(a.d == null || l.a(a.d.in_air_used_action))) {
                                                    if (this.b.c && this.b.d == iMUStatus.imuIndex && this.b.e == iMUStatus.devErrCode) {
                                                        AppPublicReflect.sensorPopWindow(a);
                                                        this.b.c = false;
                                                        this.b.d = -1;
                                                        return;
                                                    }
                                                    this.b.d = iMUStatus.imuIndex;
                                                    this.b.e = iMUStatus.devErrCode;
                                                    this.b.c = true;
                                                    return;
                                                }
                                            }
                                        }
                                        this.b.c = false;
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                        }
                    }
                    try {
                        AnonymousClass1.sleep(1500);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };
        this.H.start();
    }

    public d a() {
        return this.I;
    }

    public void b() {
        dji.thirdparty.a.c.a().d(this);
        F = null;
        if (this.H != null) {
            this.H.interrupt();
            this.H = null;
        }
    }

    public List<RedundancySwitchInfo> c() {
        return this.J;
    }

    public void d() {
        for (int size = this.J.size() - 1; size >= 0; size--) {
            try {
                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.J.remove(size));
            } catch (Exception e) {
            }
        }
    }

    public List<IMUStatus> e() {
        return this.K;
    }

    public void f() {
        for (int size = this.K.size() - 1; size >= 0; size--) {
            try {
                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.K.remove(size));
            } catch (Exception e) {
            }
        }
    }

    public List<IMUStatus> g() {
        return this.L;
    }

    public void h() {
        for (int size = this.L.size() - 1; size >= 0; size--) {
            try {
                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.L.remove(size));
            } catch (Exception e) {
            }
        }
    }

    public List<IMUStatus> i() {
        return this.M;
    }

    public void j() {
        for (int size = this.M.size() - 1; size >= 0; size--) {
            try {
                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.M.remove(size));
            } catch (Exception e) {
            }
        }
    }

    public void a(IMUStatus iMUStatus, boolean z) {
        if (iMUStatus.devErrCode != 0) {
            c a = a(this.G, iMUStatus);
            if (a.d == null || a.d.history_enable != 0) {
                iMUStatus.time = System.currentTimeMillis();
                if (iMUStatus.imuIndex == 0) {
                    if (z) {
                        if (this.K.size() >= 20) {
                            try {
                                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.K.remove(this.K.size() - 1));
                            } catch (Exception e) {
                            }
                        }
                        this.K.add(0, iMUStatus);
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).a(iMUStatus);
                    }
                } else if (iMUStatus.imuIndex == 1) {
                    if (z) {
                        if (this.L.size() >= 20) {
                            try {
                                com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.L.remove(this.L.size() - 1));
                            } catch (Exception e2) {
                            }
                        }
                        this.L.add(0, iMUStatus);
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).a(iMUStatus);
                    }
                } else if (iMUStatus.imuIndex == 2 && z) {
                    if (this.M.size() >= 20) {
                        try {
                            com.dji.frame.c.c.c(this.G.getApplicationContext()).f(this.M.remove(this.M.size() - 1));
                        } catch (Exception e3) {
                        }
                    }
                    this.M.add(0, iMUStatus);
                    com.dji.frame.c.c.c(this.G.getApplicationContext()).a(iMUStatus);
                }
            }
        }
    }

    public void a(int i, boolean z) {
        if (i >= 0 && i < this.N.length) {
            this.N[i] = z;
        }
    }

    public boolean[] k() {
        return this.N;
    }

    public void onEventBackgroundThread(DataFlycPushRedundancyStatus dataFlycPushRedundancyStatus) {
        RS_CMD_TYPE a = dataFlycPushRedundancyStatus.a();
        if (a == RS_CMD_TYPE.d) {
            RedundancySwitchInfo c = dataFlycPushRedundancyStatus.c();
            if (c.resultCode == 6) {
                if (this.J.size() >= 20) {
                    try {
                        com.dji.frame.c.c.c(this.G.getApplicationContext()).f((RedundancySwitchInfo) this.J.remove(this.J.size() - 1));
                    } catch (Exception e) {
                    }
                }
                c.time = System.currentTimeMillis();
                this.J.add(0, c);
                com.dji.frame.c.c.c(this.G.getApplicationContext()).a(c);
                if (c.reqID == 2) {
                    AppPublicReflect.sensorPopWindow(Integer.valueOf(R.string.fpv_redundancy_switch_warning));
                }
            }
        } else if (a == RS_CMD_TYPE.c) {
            a(dataFlycPushRedundancyStatus.b(), true);
        }
    }

    private boolean a(int i) {
        switch (AnonymousClass5.a[COLOR_STATUS.ofValue(i).ordinal()]) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    private void l() {
        if (ServiceManager.getInstance().isConnected()) {
            new DataFlycGetParams().setInfos(P).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIRedundancySysController a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.O = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.h).value.intValue();
                    this.a.R.sendEmptyMessageDelayed(4096, 1000);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.R.sendEmptyMessageDelayed(4096, 200);
                }
            });
        }
    }

    private void m() {
        DataFlycRedundancyStatus.getInstance().a(RS_CMD_TYPE.b).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIRedundancySysController a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.S = 0;
                int i = 1;
                for (IMUStatus iMUStatus : DataFlycRedundancyStatus.getInstance().c()) {
                    int i2;
                    if (this.a.a(iMUStatus.colorStatus)) {
                        i2 = i;
                    } else {
                        i2 = 0;
                    }
                    i = i2;
                }
                if (i == 0 && this.a.O == 1) {
                    AppPublicReflect.sensorPopWindow(Integer.valueOf(R.string.fpv_check_redundancy_failed_when_motor_up));
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.a.S = this.a.S + 1 < 10) {
                    this.a.m();
                } else {
                    this.a.S = 0;
                }
            }
        });
    }

    public void onEventBackgroundThread(o oVar) {
        if (o.a == oVar) {
            this.T = Integer.MIN_VALUE;
            this.R.removeMessages(4096);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (p.a == pVar) {
            this.T = Integer.MIN_VALUE;
            this.R.removeMessages(4096);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dji.pilot.publics.e.a.g()) {
            int flycVersion = dataOsdGetPushCommon.getFlycVersion();
            if (flycVersion != this.T) {
                this.T = flycVersion;
                l();
            }
            boolean isMotorUp = dataOsdGetPushCommon.isMotorUp();
            if (this.U != isMotorUp) {
                this.U = isMotorUp;
                if (this.U) {
                    m();
                }
            }
        }
    }

    public static c a(Context context, IMUStatus iMUStatus) {
        return a(context, iMUStatus.devType, iMUStatus.devErrCode);
    }

    public static c a(Context context, int i, int i2) {
        a(context);
        c cVar = new c();
        cVar.a = i;
        if (E != null) {
            for (DevType devType : E.devices) {
                if (devType != null && devType.id == i) {
                    cVar.b = devType.getName();
                    cVar.c = devType.version;
                    for (Element element : devType.element) {
                        if (element.code == i2) {
                            cVar.d = element;
                            break;
                        }
                    }
                }
            }
        }
        return cVar;
    }

    public static String a(Context context, String str) {
        a(context);
        if (E != null) {
            for (List list : E.en_2_ch) {
                if (((String) list.get(0)).compareTo(str) == 0) {
                    return (String) list.get(1);
                }
            }
        }
        return "";
    }

    private static void a(Context context) {
        if (E == null) {
            E = (RedundancyErrorCodeDesc) h.b(a(context.getResources().openRawResource(R.raw.redundancy_error_code_desc), "utf-8"), RedundancyErrorCodeDesc.class);
        }
        if (E != null && context.getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            for (DevType devType : E.devices) {
                String a = a(devType.name);
                if (!l.a(a)) {
                    devType.name = a;
                }
                for (Element element : devType.element) {
                    String a2 = a(element.usr_err_tips);
                    if (!l.a(a2)) {
                        element.usr_err_tips = a2;
                    }
                    a2 = a(element.ground_action);
                    if (!l.a(a2)) {
                        element.ground_action = a2;
                    }
                    a2 = a(element.in_air_used_action);
                    if (!l.a(a2)) {
                        element.in_air_used_action = a2;
                    }
                }
            }
        }
    }

    private static String a(String str) {
        if (!(E == null || l.a(str))) {
            for (List list : E.en_2_ch) {
                if (((String) list.get(0)).compareTo(str) == 0) {
                    return (String) list.get(1);
                }
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.InputStream r6, java.lang.String r7) {
        /*
        r1 = "";
        if (r7 == 0) goto L_0x000c;
    L_0x0004:
        r0 = "";
        r0 = r7.equals(r0);	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        if (r0 == 0) goto L_0x000e;
    L_0x000c:
        r7 = "utf-8";
    L_0x000e:
        r0 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r2 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r2.<init>(r6, r7);	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r0.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r2 = new java.lang.StringBuffer;	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r2.<init>();	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
    L_0x001d:
        r1 = r0.readLine();	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        if (r1 == 0) goto L_0x0035;
    L_0x0023:
        r3 = r2.append(r1);	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        r4 = "\n";
        r3.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        goto L_0x001d;
    L_0x002d:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        r1.printStackTrace();
    L_0x0034:
        return r0;
    L_0x0035:
        r0 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x002d, IOException -> 0x003a }
        goto L_0x0034;
    L_0x003a:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        r1.printStackTrace();
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.control.DJIRedundancySysController.a(java.io.InputStream, java.lang.String):java.lang.String");
    }
}
