package dji.pilot2.flymonitor.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.dji.frame.c.h;
import com.loopj.android.http.RequestParams;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.flymonitor.model.request.FlyOrderModel;
import dji.pilot2.flymonitor.model.request.FlyRecordUploadModel;
import dji.pilot2.flymonitor.model.request.FlyRecordUploadModel.RecordModel;
import dji.pilot2.flymonitor.model.response.FlyLimitAreaModel.PositionModel;
import dji.pilot2.flymonitor.model.response.FlyUploadIntervalModel;
import dji.pilot2.utils.k;
import dji.pilot2.utils.m;
import dji.thirdparty.afinal.c;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

public class FlyMonitorService extends Service implements dji.pilot2.publics.b.a {
    private static final String A = "warning";
    private static final String B = "broadcast";
    public static final String a = "FlyMonitorService";
    public static final String b = "service_type";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 4;
    public static final int g = 5000;
    public static final int h = 5;
    private static final String y = "limit_area";
    private static final String z = "upload_interval";
    private Looper C;
    private a D;
    private a E;
    private long F;
    private int G;
    private int H;
    private boolean I;
    private boolean J;
    private boolean K;
    private long L;
    private String M;
    private c N;

    private final class a extends Handler {
        final /* synthetic */ FlyMonitorService a;

        public a(FlyMonitorService flyMonitorService, Looper looper) {
            this.a = flyMonitorService;
            super(looper);
        }

        public void handleMessage(Message message) {
            HttpEntity httpEntity = null;
            String z;
            String a;
            switch (message.what) {
                case 1:
                    if (message.obj != null && (message.obj instanceof PositionModel)) {
                        PositionModel positionModel = (PositionModel) message.obj;
                        String str = k.y() + d.t + positionModel.latitude + d.t + positionModel.longitude;
                        DJILogHelper.getInstance().LOGI(FlyMonitorService.a, "FLY_RECORD_CONFIG request url: " + str);
                        this.a.N.a(str, new 1(this));
                        return;
                    }
                    return;
                case 2:
                    if (message.obj != null && (message.obj instanceof FlyOrderModel)) {
                        HttpEntity stringEntity;
                        Object obj = (FlyOrderModel) message.obj;
                        z = k.z();
                        a = h.a(obj);
                        DJILogHelper.getInstance().LOGI(FlyMonitorService.a, "FLY_RECORD_ORDER request: " + a);
                        try {
                            stringEntity = new StringEntity(a, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            stringEntity = null;
                        }
                        this.a.N.a(z, stringEntity, RequestParams.APPLICATION_JSON, new 2(this));
                        return;
                    }
                    return;
                case 4:
                    if (message.obj != null && (message.obj instanceof RecordModel)) {
                        Object flyRecordUploadModel = new FlyRecordUploadModel();
                        RecordModel recordModel = (RecordModel) message.obj;
                        if (this.a.E == null || this.a.E.a(recordModel.latitude, recordModel.longitude) == null) {
                            recordModel.is_illegal = 0;
                        } else {
                            recordModel.is_illegal = 1;
                        }
                        flyRecordUploadModel.records.add(recordModel);
                        if (recordModel.status == 1) {
                            DJILogHelper.getInstance().LOGE(FlyMonitorService.a, "LANDING!!!!!!!");
                        }
                        a = k.A();
                        z = h.a(flyRecordUploadModel);
                        DJILogHelper.getInstance().LOGI(FlyMonitorService.a, "FLY_RECORD_UPLOAD request: " + z);
                        try {
                            httpEntity = new StringEntity(z, "UTF-8");
                        } catch (UnsupportedEncodingException e2) {
                            e2.printStackTrace();
                        }
                        this.a.N.a(a, httpEntity, RequestParams.APPLICATION_JSON, new 3(this));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private enum b {
        ON_LAND,
        TAKE_OFF,
        PUT_DOWN,
        FLYING
    }

    public void onCreate() {
        a();
        HandlerThread handlerThread = new HandlerThread(a, 10);
        handlerThread.start();
        this.C = handlerThread.getLooper();
        this.D = new a(this, this.C);
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void a() {
        this.F = 0;
        this.G = 5000;
        this.I = false;
        this.J = false;
        this.K = false;
        this.M = null;
        this.E = new a(this);
        this.N = new c();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        DJILogHelper.getInstance().LOGI(a, "StartCommand");
        if (intent != null) {
            Message obtainMessage = this.D.obtainMessage();
            obtainMessage.arg1 = i2;
            obtainMessage.what = intent.getIntExtra("service_type", 0);
            this.D.sendMessage(obtainMessage);
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.onDestroy();
    }

    private b b() {
        b bVar;
        boolean isMotorUp = DataOsdGetPushCommon.getInstance().isMotorUp();
        if (!this.I && !isMotorUp) {
            bVar = b.ON_LAND;
        } else if (!this.I && isMotorUp) {
            bVar = b.TAKE_OFF;
        } else if (!this.I || isMotorUp) {
            bVar = b.FLYING;
        } else {
            bVar = b.PUT_DOWN;
        }
        this.I = isMotorUp;
        return bVar;
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.F >= ((long) this.G)) {
            double latitude = dataOsdGetPushCommon.getLatitude();
            double longitude = dataOsdGetPushCommon.getLongitude();
            if (this.K) {
                try {
                    dji.gs.e.b bVar = new dji.gs.e.b(latitude, longitude);
                    if (!(bVar == null || bVar == null)) {
                        latitude = bVar.b;
                        longitude = bVar.c;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) {
                this.F = currentTimeMillis;
                if (!this.J && this.H < 5) {
                    this.H++;
                    Message obtainMessage = this.D.obtainMessage();
                    obtainMessage.what = 1;
                    obtainMessage.obj = new PositionModel(latitude, longitude);
                    this.D.sendMessage(obtainMessage);
                }
                if (this.K && !DataOsdGetPushHome.getInstance().isFlycInSimulationMode()) {
                    b b = b();
                    if (b == b.ON_LAND) {
                        return;
                    }
                    Message obtainMessage2;
                    if (b == b.TAKE_OFF) {
                        this.M = UUID.randomUUID().toString();
                        FlyOrderModel flyOrderModel = new FlyOrderModel();
                        this.L = System.currentTimeMillis();
                        flyOrderModel.setBeginAtTime(this.L);
                        String j = f.getInstance().j();
                        String k = f.getInstance().k();
                        String o = f.getInstance().o();
                        if (!m.c(j)) {
                            flyOrderModel.setUserInfo(f.getInstance().m(), j);
                        } else if (!m.c(k)) {
                            flyOrderModel.setUserInfo(f.getInstance().m(), "tel:" + k);
                        } else if (m.c(o)) {
                            flyOrderModel.setUserInfo(f.getInstance().m(), "");
                        } else {
                            flyOrderModel.setUserInfo(f.getInstance().m(), "uid:" + o);
                        }
                        flyOrderModel.setFlyingCenter(latitude, longitude);
                        flyOrderModel.setOrderNumber(this.M);
                        flyOrderModel.setDroneInfo(dji.pilot.publics.c.d.getInstance().b(i.getInstance().c()), DJIGlobalService.f);
                        obtainMessage2 = this.D.obtainMessage();
                        obtainMessage2.what = 2;
                        obtainMessage2.obj = flyOrderModel;
                        this.D.sendMessage(obtainMessage2);
                    } else if (b == b.PUT_DOWN || b == b.FLYING) {
                        RecordModel recordModel = new RecordModel();
                        double xSpeed = 0.1d * ((double) dataOsdGetPushCommon.getXSpeed());
                        double ySpeed = 0.1d * ((double) dataOsdGetPushCommon.getYSpeed());
                        double zSpeed = 0.1d * ((double) dataOsdGetPushCommon.getZSpeed());
                        recordModel.speed = Math.sqrt(((xSpeed * xSpeed) + (ySpeed * ySpeed)) + (zSpeed * zSpeed));
                        recordModel.droneID = DJIGlobalService.f;
                        recordModel.yaw = (int) ((0.1d * ((double) dataOsdGetPushCommon.getYaw())) + 180.0d);
                        recordModel.orderID = this.M;
                        recordModel.latitude = latitude;
                        recordModel.longitude = longitude;
                        recordModel.userName = f.getInstance().m();
                        recordModel.date = System.currentTimeMillis();
                        recordModel.altitude = 0.1d * ((double) dataOsdGetPushCommon.getHeight());
                        recordModel.flightTime = dataOsdGetPushCommon.getFlyTime();
                        recordModel.status = b == b.FLYING ? 0 : 1;
                        recordModel.droneType = dji.pilot.publics.c.d.getInstance().b(i.getInstance().c());
                        if (this.D.hasMessages(4)) {
                            this.D.removeMessages(4);
                        }
                        obtainMessage2 = this.D.obtainMessage();
                        obtainMessage2.what = 4;
                        obtainMessage2.obj = recordModel;
                        this.D.sendMessage(obtainMessage2);
                    }
                }
            }
        }
    }

    private void a(String str, String str2) {
        if (str != null && !str.equals("")) {
            DJILogHelper.getInstance().LOGE(a, "handle type: " + str);
            if (y.equals(str)) {
                this.E.a(str2);
            } else if (z.equals(str)) {
                this.G = ((FlyUploadIntervalModel) h.b(str2, FlyUploadIntervalModel.class)).data;
                DJILogHelper.getInstance().LOGI(a, "Upload interval updated: " + this.G);
            } else if (!A.equals(str) && B.equals(str)) {
            }
        }
    }
}
