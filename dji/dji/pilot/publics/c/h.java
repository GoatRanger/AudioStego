package dji.pilot.publics.c;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.media.MediaPlayer;
import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery.SmartGoHomeStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.pilot.fpv.camera.a.a;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.leftmenu.DJILeftMenu;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k$a;
import dji.thirdparty.a.c;

public class h implements Callback, dji.pilot.fpv.d.c.h, k$a {
    public static boolean r = false;
    private static final int s = 4096;
    private static final long t = 1000;
    private static h u = null;
    private DroneType A = null;
    private int B = -1;
    FLYC_STATE a;
    boolean n = false;
    int o = 0;
    TRIPOD_STATUS p = TRIPOD_STATUS.UNKNOWN;
    SDCardState q;
    private MediaPlayer v;
    private Context w;
    private k x = null;
    private long y = 0;
    private FLIGHT_ACTION z = FLIGHT_ACTION.NONE;

    public static synchronized h getInstance() {
        h hVar;
        synchronized (h.class) {
            hVar = u;
        }
        return hVar;
    }

    public static synchronized h getInstance(Context context) {
        h hVar;
        synchronized (h.class) {
            if (u == null) {
                u = new h(context);
            }
            hVar = u;
        }
        return hVar;
    }

    public h(Context context) {
        this.w = context;
        this.y = System.currentTimeMillis();
        this.x = new k(this, this);
        c.a().a((Object) this);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                if (a.a(this.q)) {
                    a(b.j);
                    break;
                }
                break;
        }
        return true;
    }

    public boolean isFinished() {
        return false;
    }

    public synchronized void a(b bVar) {
        DJILogHelper.getInstance().LOGD("", "DJIVoiceNotifyType=" + bVar);
        if (this.v != null && this.v.isPlaying()) {
            this.v.stop();
            this.v.release();
            this.v = null;
        }
        try {
            this.v = MediaPlayer.create(this.w, bVar.a());
            this.v.setLooping(false);
            this.v.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (NotFoundException e3) {
            e3.printStackTrace();
        }
    }

    public void a() {
        c.a().d((Object) this);
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        switch (1.a[flycState.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                flycState = FLYC_STATE.Atti;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                flycState = FLYC_STATE.GPS_Atti;
                break;
        }
        if (flycState != this.a) {
            this.a = flycState;
            switch (1.a[this.a.ordinal()]) {
                case 1:
                    e.c(dji.pilot.fpv.d.c.h.bb_);
                    break;
                case 11:
                case 12:
                    a(b.b);
                    break;
                case 13:
                case 14:
                    a(b.c);
                    break;
                case 15:
                    a(b.d);
                    break;
            }
        }
        int voltageWarning = dataOsdGetPushCommon.getVoltageWarning();
        if (this.o != voltageWarning) {
            this.o = voltageWarning;
            if (!(dji.pilot.publics.e.a.k() && dji.pilot.publics.e.a.j())) {
                if (voltageWarning == 1) {
                    a(b.g);
                } else if (voltageWarning == 2) {
                    a(b.h);
                }
            }
        }
        FLIGHT_ACTION flightAction = dataOsdGetPushCommon.getFlightAction();
        if (this.z != flightAction) {
            this.z = flightAction;
            switch (1.b[flightAction.ordinal()]) {
                case 1:
                    if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                        a(b.A);
                        break;
                    }
                    break;
                case 2:
                    a(b.u);
                    break;
            }
        }
        DroneType droneType = dataOsdGetPushCommon.getDroneType();
        if (droneType != this.A) {
            this.A = droneType;
            if (this.A != DroneType.NoFlyc) {
                b();
            }
        }
    }

    private void b() {
        if (!r) {
            r = true;
        }
        this.y = System.currentTimeMillis();
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        TRIPOD_STATUS deformStatus = dataFlycGetPushDeformStatus.getDeformStatus();
        if (deformStatus != null && deformStatus != this.p) {
            if (TRIPOD_STATUS.UNKNOWN != deformStatus && b.g(null) && System.currentTimeMillis() - this.y >= 8000) {
                switch (1.c[deformStatus.ordinal()]) {
                    case 1:
                        a(b.n);
                        break;
                    case 2:
                        a(b.m);
                        break;
                }
            }
            this.p = deformStatus;
        }
    }

    public void onEventBackgroundThread(a aVar) {
        switch (1.d[aVar.ordinal()]) {
            case 1:
                a(b.e);
                return;
            case 2:
                dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.j()) {
                    a(b.f);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(b.a aVar) {
        if (aVar == b.a.COMPASS_DISTURB) {
            a(b.l);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        SDCardState sDCardState = dataCameraGetPushStateInfo.getSDCardState();
        if (sDCardState != this.q) {
            this.q = sDCardState;
            if (a.a(sDCardState)) {
                this.x.sendEmptyMessageDelayed(4096, 1000);
                return;
            }
            if (SDCardState.Full == sDCardState) {
                a(b.i);
            }
            this.x.removeMessages(4096);
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if (dataFlycGetPushSmartBattery.getGoHomeStatus() == SmartGoHomeStatus.NON_GOHOME && DataOsdGetPushCommon.getInstance().isMotorUp()) {
            int goHomeCountDown = dataFlycGetPushSmartBattery.getGoHomeCountDown();
            if (goHomeCountDown > 0) {
                if (this.B == -1) {
                    this.B = 0;
                    if (goHomeCountDown >= 8) {
                        a(b.t);
                    }
                }
                if (this.B == 0 && goHomeCountDown <= 5) {
                    this.B = 1;
                }
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (1.e[oVar.ordinal()]) {
            case 2:
                this.B = -1;
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DJILeftMenu.a aVar) {
        if (aVar == DJILeftMenu.a.a) {
            a(b.C);
        } else if (aVar == DJILeftMenu.a.b) {
            a(b.D);
        }
    }
}
