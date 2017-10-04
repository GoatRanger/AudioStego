package dji.pilot.flightrecord;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.gs.e.b;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataAppOperation;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushDebugInfo;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.natives.FREncrypt;
import dji.pilot.fpv.control.s;
import dji.pilot.fpv.d.c.g;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.h;
import dji.pilot.fpv.model.i;
import dji.pilot.fpv.model.j;
import dji.pilot.fpv.model.l;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.model.DJIProductListModel.DJIProductModel;
import dji.pilot.publics.model.DJIProductVerModel.DJIVerModel;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import dji.thirdparty.a.c;
import java.util.Iterator;

public class DJIRecordService extends Service implements g, d {
    public static f a = null;
    public static boolean b = false;
    private long N;
    private float O = 0.0f;
    private float P = 0.0f;
    private float Q = 0.0f;
    private h R = new h();
    private int S = 0;
    private int T;
    private int U;
    private int V;
    private int W = 0;
    private i X;
    private float Y;
    private final String c = getClass().getSimpleName();
    private b d = new b(0.0d, 0.0d);
    private b e = new b(0.0d, 0.0d);
    private float f = 0.0f;
    private float g = 0.0f;
    private int h = -1;
    private boolean i = false;
    private boolean j = true;
    private volatile boolean k = false;
    private boolean l = true;
    private s m = s.getInstance();
    private a n;
    private b o = new b(0.0d, 0.0d);
    private b p = new b(0.0d, 0.0d);
    private b q = new b(0.0d, 0.0d);
    private float r = 0.0f;
    private float s = 0.0f;
    private int t;
    private float u;

    private class a extends Thread {
        final /* synthetic */ DJIRecordService a;
        private boolean b;

        private a(DJIRecordService dJIRecordService) {
            this.a = dJIRecordService;
            this.b = true;
        }

        public void a() {
            this.b = false;
            interrupt();
        }

        public void run() {
            if (DJIRecordService.a != null) {
                if (this.a.j) {
                    this.a.X = new i(this.a, DJIRecordService.a, true);
                } else {
                    this.a.X = new i(this.a, DJIRecordService.a);
                }
                this.a.W = 0;
                this.a.S = 0;
                while (this.b) {
                    this.a.f();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                DJILogHelper.getInstance().LOGD("", "RecordFlight thread end", true, true);
                this.a.X.a();
                this.a.e();
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onCreate() {
        b();
        DJILogHelper.getInstance().LOGD(this.c, "record service up", true, true);
        dji.sdksharedlib.a.a.g((d) this, e.al, e.am, e.R, e.S);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    private void b() {
        c.a().a((Object) this);
        FREncrypt.loadLibrary();
        FREncrypt.verifySign();
    }

    public void onDestroy() {
        c.a().d((Object) this);
        dji.sdksharedlib.a.a.a((d) this);
        super.onDestroy();
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        i();
    }

    private void a(DataOsdGetPushCommon dataOsdGetPushCommon) {
        float height;
        float f = 0.0f;
        if (dataOsdGetPushCommon.getFlycVersion() >= 8 && DataOsdGetPushHome.getInstance().isGetted()) {
            height = DataOsdGetPushHome.getInstance().getHeight();
            f = height * 10.0f;
            if (!f.c(height)) {
                f = this.d.d;
            }
        }
        b bVar = new b(dataOsdGetPushCommon.getLatitude(), dataOsdGetPushCommon.getLongitude(), f);
        if (bVar.a() && !this.d.a(bVar)) {
            if (this.d.a()) {
                this.d = bVar;
            } else {
                this.d = bVar;
            }
        }
        height = ((float) dataOsdGetPushCommon.getYaw()) * 0.1f;
        if (height != this.f) {
            this.f = height;
        }
        this.h = dataOsdGetPushCommon.groundOrSky();
        boolean isMotorUp = dataOsdGetPushCommon.isMotorUp();
        if (isMotorUp != this.i) {
            DJILogHelper.getInstance().LOGD(this.c, "飞机电机状态： " + isMotorUp + "飞机是否在空中" + this.h, true, true);
            this.i = isMotorUp;
            if (!isMotorUp) {
                this.l = true;
                a(false);
            } else if (this.h != 2) {
                new Thread(new Runnable(this) {
                    final /* synthetic */ DJIRecordService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        DJILogHelper.getInstance().LOGD("", "飞机在地面或无法判断，新建飞行记录", true, true);
                        this.a.a(true);
                    }
                }).start();
            } else {
                DJILogHelper.getInstance().LOGD("", "飞机在空中，需要拼接飞行记录", true, true);
                new Thread(new Runnable(this) {
                    final /* synthetic */ DJIRecordService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.m.a != null) {
                            this.a.j = true;
                            DJILogHelper.getInstance().LOGD("", "获取infoModel成功，开始拼接飞行记录", true, true);
                            this.a.m.a();
                            this.a.d();
                            return;
                        }
                        DJILogHelper.getInstance().LOGD("", "获取infoModel失败，无法拼接飞行记录，执行新建飞行记录操作", true, true);
                        this.a.a(true);
                    }
                }).start();
            }
        }
    }

    private void a(DataOsdGetPushDebugInfo dataOsdGetPushDebugInfo) {
        if (a != null && this.k) {
            byte[] recData = dataOsdGetPushDebugInfo.getRecData();
            if (recData != null && recData.length > 0) {
                this.X.a(dji.pilot.fpv.model.h.a.OFDM_DEBUG, recData, a);
            }
        }
    }

    private void a(DJIErrorPopView.b bVar) {
        if (a != null && this.k) {
            dji.pilot.fpv.model.h.a aVar;
            if (bVar.a == DJIErrorPopView.d.NOTIFY) {
                aVar = dji.pilot.fpv.model.h.a.APP_TIP;
            } else {
                aVar = dji.pilot.fpv.model.h.a.APP_WARN;
            }
            String str = bVar.c;
            if (bVar.b != 0) {
                str = getResources().getString(bVar.b);
            }
            if (str == null) {
                str = bVar.e;
                if (bVar.d != 0) {
                    str = getResources().getString(bVar.d);
                }
            } else if (bVar.e != null) {
                str = str + "__" + bVar.e;
            } else if (bVar.d != 0) {
                str = str + "__" + getResources().getString(bVar.d);
            }
            this.X.a(aVar, dji.midware.util.c.b(str), a);
        }
    }

    private void c() {
        if (a != null && this.k) {
            this.X.a(dji.pilot.fpv.model.h.a.VISION_WARN, dji.midware.util.c.b(getString(dji.pilot.visual.a.c.getInstance().r())), a);
        }
    }

    private void a(DataAppOperation dataAppOperation) {
        if (a != null && this.k) {
            byte[] recData = dataAppOperation.getRecData();
            if (recData != null && recData.length != 0) {
                this.X.a(dji.pilot.fpv.model.h.a.APP_OPERATION, recData, a);
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            this.k = true;
            if (this.n != null) {
                this.n.a();
                this.n = null;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.n = new a();
            DJILogHelper.getInstance().LOGD("", "开始记录飞行记录", true, true);
            a = new f();
            a.E = this.d.b;
            a.D = this.d.c;
            a.C = System.currentTimeMillis();
            a.P = DJIGlobalService.f;
            a.S = DJIGlobalService.j;
            a.R = DJIGlobalService.e;
            a.Q = dji.midware.data.manager.P3.i.getInstance().c().value();
            a.T = DJIGlobalService.g;
            a.U = DJIGlobalService.h;
            a.V = DJIGlobalService.i;
            a.Y = this.d.d;
            a.Z = 0;
            a.aa = 0;
            DJILogHelper.getInstance().LOGD("", "RecordFlight start timer", true, true);
            this.n.start();
        } else if (this.n != null) {
            this.n.a();
            this.n = null;
        }
    }

    private void d() {
        this.k = true;
        if (this.n != null) {
            this.n.a();
            this.n = null;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        a = this.m.a;
        this.t = this.m.b;
        this.u = this.m.c;
        this.N = this.m.d;
        this.O = this.m.e;
        this.P = this.m.f;
        this.Q = this.m.g;
        if (a == null) {
            DJILogHelper.getInstance().LOGD("", "拼接飞行记录异常： infoModel=null", true, true);
            return;
        }
        this.n = new a();
        DJILogHelper.getInstance().LOGD("", "拼接飞行记录完成", true, true);
        this.n.start();
    }

    private void e() {
        this.k = false;
        if (a != null) {
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.N);
            if (this.u > 30000.0f || this.u < 0.0f || ((this.u == 0.0f && this.O <= 0.0f) || currentTimeMillis <= 1000 || h())) {
                i.a((Context) this, a);
                DJILogHelper.getInstance().LOGD("", "RecordFlight end delete ", false, true);
            } else {
                if (a.P.equals("")) {
                    a.P = DJIGlobalService.f;
                    a.S = DJIGlobalService.j;
                    a.R = DJIGlobalService.e;
                    a.T = DJIGlobalService.g;
                    a.U = DJIGlobalService.h;
                    a.V = DJIGlobalService.i;
                }
                a.B = this.t;
                a.F = this.u;
                a.G = currentTimeMillis;
                a.H = this.O;
                a.I = this.P;
                a.J = this.Q;
                i.c(this, a);
                DJILogHelper.getInstance().LOGD("", "RecordFlight end totalTime " + a.G, false, true);
                b = true;
            }
        }
        if (a != null) {
            DJILogHelper.getInstance().LOGD("", "RecordFlight end totalDis=" + a.F + " totalTime=" + a.G, false, true);
        }
        if (this.l) {
            this.m.a = null;
            if (a != null && a.G >= 1000) {
                dji.pilot.fpv.d.e.a((long) a.G);
                dji.pilot.fpv.d.e.a(g.E, g.w, "" + ((int) Math.rint((double) a.F)));
                dji.pilot.fpv.d.e.a(g.F, g.x, "" + ((int) Math.rint((double) a.H)));
                dji.pilot.fpv.d.e.a(g.G, "", "");
            }
        } else {
            DJILogHelper.getInstance().LOGD("", "飞行记录异常退出，记录info信息", true, true);
            g();
        }
        this.N = 0;
        this.u = 0.0f;
        this.O = 0.0f;
        this.P = 0.0f;
        this.Q = 0.0f;
        this.t = 0;
        this.T = 0;
        this.o.b = 0.0d;
        this.o.c = 0.0d;
        a = null;
        this.i = false;
    }

    private void f() {
        this.p.b = DataOsdGetPushCommon.getInstance().getLatitude();
        this.p.c = DataOsdGetPushCommon.getInstance().getLongitude();
        if (!this.p.a(this.o) || !this.q.a(this.e) || this.r != this.f || this.s != this.g) {
            f fVar;
            this.q = this.e;
            this.r = this.f;
            this.s = this.g;
            this.R.a();
            this.R.b = null;
            this.R.g = null;
            this.R.e = null;
            this.R.j = null;
            this.R.f = null;
            this.R.m = null;
            this.R.n = null;
            this.R.o = null;
            this.R.p = null;
            this.R.a.setRecData(DataOsdGetPushCommon.getInstance().getRecData());
            this.R.c.setRecData(DataGimbalGetPushParams.getInstance().getRecData());
            this.R.d.setRecData(DataRcGetPushParams.getInstance().getRecData());
            this.R.o = new l(false);
            if (this.S % 10 == 4) {
                this.R.e = new DataFlycGetPushDeformStatus(false);
                this.R.e.setRecData(DataFlycGetPushDeformStatus.getInstance().getRecData());
            } else if (this.S % 10 == 9) {
                this.R.b = new DataOsdGetPushHome(false);
                this.R.b.setRecData(DataOsdGetPushHome.getInstance().getRecData());
                this.R.g = new DataFlycGetPushSmartBattery(false);
                this.R.g.setRecData(DataFlycGetPushSmartBattery.getInstance().getRecData());
                this.R.f = new DataCenterGetPushBatteryCommon(false);
                this.R.f.setRecData(DataCenterGetPushBatteryCommon.getInstance().getRecData());
                this.R.j = new DataRcGetPushGpsInfo(false);
                this.R.j.setRecData(DataRcGetPushGpsInfo.getInstance().getRecData());
                this.R.e = new DataFlycGetPushDeformStatus(false);
                this.R.e.setRecData(DataFlycGetPushDeformStatus.getInstance().getRecData());
                this.R.m = new dji.pilot.fpv.model.c(false);
                a(this.R.m);
            }
            if (this.S == 0 || this.S % 100 == 99) {
                this.R.p = new dji.pilot.fpv.model.g(false);
            }
            long currentTimeMillis = System.currentTimeMillis();
            this.R.k.a(currentTimeMillis);
            this.R.k.b(this.Y);
            float ySpeed = ((float) DataOsdGetPushCommon.getInstance().getYSpeed()) * 0.1f;
            float xSpeed = ((float) DataOsdGetPushCommon.getInstance().getXSpeed()) * 0.1f;
            ySpeed = (float) Math.sqrt((double) ((ySpeed * ySpeed) + (xSpeed * xSpeed)));
            xSpeed = ((float) DataOsdGetPushCommon.getInstance().getZSpeed()) * 0.1f;
            float height = ((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f;
            if (ySpeed > this.P && f.a(ySpeed)) {
                this.P = ySpeed;
            }
            if (xSpeed > this.Q && f.b(xSpeed)) {
                this.Q = xSpeed;
            }
            if (height > this.O) {
                this.O = height;
            }
            this.R.k.a(ySpeed);
            if (!this.o.a(this.p) && this.p.a()) {
                if (this.o.a()) {
                    this.u = (float) (dji.gs.utils.a.a(this.p.b, this.p.c, this.o.b, this.o.c) + ((double) this.u));
                }
                this.o.b = this.p.b;
                this.o.c = this.p.c;
            }
            if (this.N == 0) {
                this.N = currentTimeMillis;
                DJILogHelper.getInstance().LOGD("", "RecordFlight add totalTime " + this.N, false, true);
            }
            if (dji.pilot.fpv.control.b.u) {
                dji.pilot.fpv.control.b.u = false;
                this.R.k.a(1);
                fVar = a;
                fVar.K++;
                a();
            }
            this.R.k.b(this.U);
            if (this.U != 0) {
                if (this.U == 2) {
                    fVar = a;
                    fVar.L += (long) this.V;
                }
                this.U = 0;
            }
            if (this.W >= 6 || this.S % 10 != 9) {
                this.R.l = null;
                this.R.n = null;
            } else {
                if (this.R.l == null) {
                    this.R.l = new j(false);
                }
                this.R.l.a(dji.midware.data.manager.P3.i.getInstance().c().value());
                this.R.l.a(DJIGlobalService.f);
                this.R.l.b(DJIGlobalService.e);
                this.R.l.a(DJIGlobalService.j);
                this.R.l.c(DJIGlobalService.g);
                this.R.l.d(DJIGlobalService.h);
                this.R.l.e(DJIGlobalService.i);
                if (this.R.n == null) {
                    this.R.n = new dji.pilot.fpv.model.e(false);
                }
                a(this.R.n);
                this.W++;
            }
            if (this.j) {
                this.j = false;
                this.X.a(a);
                this.X.a(this.R, a);
            } else {
                this.X.a(this.R, a);
            }
            this.t++;
            this.S++;
        }
    }

    private void g() {
        this.m.a = a;
        this.m.b = this.t;
        this.m.c = this.u;
        this.m.d = this.N;
        this.m.e = this.O;
        this.m.f = this.P;
        this.m.g = this.Q;
        DJILogHelper.getInstance().LOGD("", "tmpRecord---totalTime:" + this.N + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + "totalLine: " + this.t + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + "totalDistance:" + this.u, false, true);
    }

    public void a() {
        Intent intent = new Intent();
        intent.putExtra("type", 0);
        intent.setAction("android.intent.action.record");
        sendBroadcast(intent);
    }

    private void a(dji.pilot.fpv.model.c cVar) {
        Location e = dji.a.a.getInstance().e();
        if (e != null) {
            cVar.a = e.getLatitude();
            cVar.b = e.getLongitude();
            cVar.c = e.getAccuracy();
        }
    }

    private void a(dji.pilot.fpv.model.e eVar) {
        Iterator it = dji.pilot.publics.control.a.h.getModelListAll(dji.midware.data.manager.P3.i.getInstance().c()).iterator();
        while (it.hasNext()) {
            DJIVerModel dJIVerModel = (DJIVerModel) it.next();
            eVar.a(Integer.parseInt(dJIVerModel.code.substring(0, 2)), Integer.parseInt(dJIVerModel.code.substring(2, 4)), dJIVerModel.version);
        }
        DJIProductModel a = dji.pilot.publics.c.d.getInstance().a(dji.midware.data.manager.P3.i.getInstance().c());
        if (a.verModel != null) {
            it = a.verModel.getIgnoreList().iterator();
            while (it.hasNext()) {
                dJIVerModel = (DJIVerModel) it.next();
                int parseInt = Integer.parseInt(dJIVerModel.code.substring(0, 2));
                int parseInt2 = Integer.parseInt(dJIVerModel.code.substring(2, 4));
                if (dji.pilot.publics.e.d.a(dJIVerModel.version)) {
                    DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
                    dataCommonGetVersion.setDeviceType(DeviceType.find(parseInt)).setDeviceModel(parseInt2).start(new dji.midware.e.d(this) {
                        final /* synthetic */ DJIRecordService a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                    if (dataCommonGetVersion.isGetted()) {
                        dJIVerModel.version = dataCommonGetVersion.getFirmVer(".");
                    }
                }
                eVar.a(parseInt, parseInt2, dJIVerModel.version);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!dji.pilot2.simulator.d.h() && !h()) {
            a(dataOsdGetPushCommon);
        }
    }

    private static boolean h() {
        return !dji.pilot.c.a.w && DataOsdGetPushHome.getInstance().isFlycInSimulationMode();
    }

    public void onEventBackgroundThread(dji.pilot.visual.a.g.c cVar) {
        if (cVar == dji.pilot.visual.a.g.c.INVALID) {
            c();
        }
    }

    public void onEventBackgroundThread(DataAppOperation dataAppOperation) {
        a(dataAppOperation);
    }

    public void onEventBackgroundThread(DJIErrorPopView.b bVar) {
        if (bVar != null && bVar.g == DJIErrorPopView.f.INSERT) {
            a(bVar);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dataOsdGetPushHome.isHomeRecord()) {
            b bVar = new b(dataOsdGetPushHome.getLatitude(), dataOsdGetPushHome.getLongitude());
            if (bVar.a() && !this.e.a(bVar) && dji.gs.utils.c.a(bVar, this.e) > 1.0f) {
                this.e = bVar;
            }
        }
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        float yawAngle = ((((float) dataGimbalGetPushParams.getYawAngle()) * 0.1f) + this.f) + 180.0f;
        if (yawAngle != this.g) {
            this.g = yawAngle;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        int videoRecordTime = dataCameraGetPushStateInfo.getVideoRecordTime();
        if (!(this.T == videoRecordTime || this.T != 0 || videoRecordTime == 0)) {
            this.U = 1;
            DJILogHelper.getInstance().LOGD("", "isRecording=1", false, true);
        }
        if (!(this.T == videoRecordTime || this.T == 0 || videoRecordTime != 0)) {
            this.V = this.T;
            this.U = 2;
        }
        this.T = videoRecordTime;
    }

    public void onEventBackgroundThread(DataOsdGetPushDebugInfo dataOsdGetPushDebugInfo) {
        a(dataOsdGetPushDebugInfo);
    }

    public void onEventBackgroundThread(o oVar) {
        switch (oVar) {
            case ConnectLose:
                if (this.k) {
                    this.l = false;
                    a(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void i() {
        this.Y = dji.pilot.fpv.d.b.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.al)), dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.am)), dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.R)), dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.S)));
    }
}
